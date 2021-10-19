/*
 * UnidadAnticipacion.java
 *
 * Created on 19 de noviembre de 2006, 19:16
 */

package ensamblador.etapas;

import ensamblador.instrucciones.Instruccion;
//import ensamblador.registros.Registro;
import ensamblador.registros.Status;
import ensamblador.instrucciones.Beq;
import ensamblador.instrucciones.Bne;
import ensamblador.instrucciones.Carga;
//import ensamblador.instrucciones.SaltoCondicional;
import ensamblador.instrucciones.Jr;
import ensamblador.instrucciones.Bc1t;
import ensamblador.instrucciones.Bc1f;
import ensamblador.datos.Tipos;
import ensamblador.unidadFuncional.UFuncional;
import ensamblador.unidadFuncional.EstadoInstruccion;

import java.util.Vector;

public class UnidadDeteccionRiesgos implements Tipos {
    
    private static UnidadDeteccionRiesgos unidadDeteccionRiesgos = null;
    
    /** Creates a new instance of UnidadAnticipacion */
    private UnidadDeteccionRiesgos() {};        
    
    public static UnidadDeteccionRiesgos getInstancia()
    {    	     
    	if(unidadDeteccionRiesgos == null){        
    		unidadDeteccionRiesgos = new UnidadDeteccionRiesgos();        
    	}        
    	return unidadDeteccionRiesgos;      
    }
    
    public boolean detener() {
        Instruccion instID, instEX;
        EstadoInstruccion eiFlotanteUltimaEtapaEX, eiEnteroUltimaEtapaEX;
        Vector<UFuncional> unidFuncionales = new Vector<UFuncional>(); 
        boolean continuar = false;
        
        instID = ID.getInstancia().getEstadoInstruccion().getInstruccion();
        if ((instID instanceof Jr) && (!instID.getRegRS().estaLibre())) {
	    	return true;         
        }
        
        if ((instID instanceof Beq) || (instID instanceof Bne)) {
            if ((!instID.getRegRS().estaLibre()) || (!instID.getRegRT().estaLibre())) {
	            return true;
            }
        }
        
        if(((instID instanceof Bc1t) || (instID instanceof Bc1f)) && (!Status.getRegistro().estaLibre())) {
	    	return true;
        }
        
        unidFuncionales = EX.getInstancia().getUnidFuncionales();

        //si despues de una carga viene una operacion q lee ese valor se necesita un ciclo de detencion,
        //pues el resultado de la carga no se puede anticipar hasta la etapa de WB
        for(UFuncional uf:unidFuncionales) {
        	for(EstadoInstruccion ei:uf.getInstrucciones()) {
        		instEX = ei.getInstruccion();

        		if(instEX instanceof Carga) {
        			if((instEX.regModificable() == instID.getRegRS()) || (instEX.regModificable() == instID.getRegRT())) {
        				return true;
        			}
        		}
        	}
        }

        
      //el resultado de las operaciones q se resuelven en EX no se puede anticipar hasta q 
      //la instruccion correspondiente avance a MEM
        
      //Es decir, se detiene el pipeline siempre que la inst depende de otra q aun esta en EX,
      //a menos que sea de la q va a avanzar a MEM en el siguiente ciclo, ya que en este caso 
      //se anticipa y ya no hay riesgo
      eiEnteroUltimaEtapaEX = EX.getInstancia().getEstadoInstEntera();
      eiFlotanteUltimaEtapaEX = EX.getInstancia().getEstadoInstFlotante();
      for(UFuncional uf:unidFuncionales) {
      	for(EstadoInstruccion ei:uf.getInstrucciones()) {
      		if((ei == eiEnteroUltimaEtapaEX) || (ei == eiFlotanteUltimaEtapaEX)) {
      			continue;
      		}
      		if((ei.getInstruccion().regModificable() != null) && 
      				((ei.getInstruccion().regModificable() == instID.getRegRS()) || (ei.getInstruccion().regModificable() == instID.getRegRT()))) {        		
      			return true;		
      		}
      	}
      }
      return false;
      }
    
}
