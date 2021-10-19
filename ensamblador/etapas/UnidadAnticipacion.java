/*
 * UnidadAnticipacion.java
 *
 * Created on 19 de noviembre de 2006, 19:16
 */

package ensamblador.etapas;


import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Registro;
import ensamblador.datos.Tipos;

public class UnidadAnticipacion implements Tipos {
    
    private static UnidadAnticipacion unidadAnticipacion = null;
    
    
    /** Creates a new instance of UnidadAnticipacion */
    private UnidadAnticipacion() {};
    
    public static UnidadAnticipacion getInstancia()
    {    	     
    	if(unidadAnticipacion == null){        
    		unidadAnticipacion = new UnidadAnticipacion();        
    	}        
    	return unidadAnticipacion;      
    }
    
    public boolean[] anticipar() {
        boolean ant[] = new boolean[4];
        Instruccion instEX, instMEM, instWB;
        Registro regMEM = null;
        Registro regWB = null;
        
        for (int i=0; i<ant.length; i++) {
            ant[i] = false;
        }
        
        instEX = EX.getInstancia().getEstadoInstEntera().getInstruccion();
        instMEM = MEM.getInstancia().getEstadoInstrucciones()[0].getInstruccion();
        instWB = WB.getInstancia().getEstadoInstrucciones()[0].getInstruccion();
        if (instMEM.regModificable() != null) {
            regMEM = instMEM.regModificable();
                        
            if (regMEM == instEX.getRegRS()) {
                ant[ANT_RS_MEM] = true;
            }
            if (regMEM == instEX.getRegRT()) {
                ant[ANT_RT_MEM] = true;
            }
        }
        
        if (instWB.regModificable() != null) {
            regWB = instWB.regModificable();
            
            if ((regMEM != instEX.getRegRS()) && (regWB == instEX.getRegRS())) {
                ant[ANT_RS_WB] = true;
            }
            if ((regMEM != instEX.getRegRT()) && (regWB == instEX.getRegRT())) {
                ant[ANT_RT_WB] = true;
            }   
        } 
        return ant;
    }    
    
}
