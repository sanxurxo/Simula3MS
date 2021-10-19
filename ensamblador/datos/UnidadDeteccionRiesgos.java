/*
 * UnidadAnticipacion.java
 *
 * Created on 19 de noviembre de 2006, 19:16
 */

package ensamblador.datos;

import ensamblador.datos.Pipeline;
import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Registro;
import ensamblador.instrucciones.Carga;
import ensamblador.instrucciones.Nop;
import ensamblador.instrucciones.SaltoCondicional;
import ensamblador.instrucciones.Jr;
import ensamblador.datos.Tipos;
import ensamblador.estado.Riesgos;

public class UnidadDeteccionRiesgos implements Tipos {
    private Riesgos riesgos=Riesgos.getRiesgos();
    private static UnidadDeteccionRiesgos unidadDeteccionRiesgos = new UnidadDeteccionRiesgos();
    //private Instruccion[] instPipeline = Pipeline.getInstacia().getInstrucciones();
    
    /** Creates a new instance of UnidadAnticipacion */
    private UnidadDeteccionRiesgos() {};
    
    public static UnidadDeteccionRiesgos getInstancia() {
        return unidadDeteccionRiesgos;
    }
    
    public boolean detener() {
        Instruccion[] instPipeline = Pipeline.getInstacia().getInstrucciones();
        
        if (instPipeline[ETAPA_ID] instanceof Jr) {
            if (!instPipeline[ETAPA_ID].getRegRS().estaLibre()) {
                return true;
            }
        }
        
        if (instPipeline[ETAPA_ID] instanceof SaltoCondicional) {
            if ((!instPipeline[ETAPA_ID].getRegRS().estaLibre()) || (!instPipeline[ETAPA_ID].getRegRT().estaLibre())) {            	
                return true;
            }
        }
        

        

        if (instPipeline[ETAPA_EX] instanceof Carga) {
            if((instPipeline[ETAPA_EX].regModificable() == instPipeline[ETAPA_ID].getRegRS()) || 
                (instPipeline[ETAPA_EX].regModificable() == instPipeline[ETAPA_ID].getRegRT())) {
            		riesgos.setRiesgoCarga(riesgos.getRiesgoCarga()+1);
                    return true;
            }
        }
        
        return false;
    }
    
    
}
