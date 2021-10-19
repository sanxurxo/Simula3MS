/*
 * UnidadAnticipacion.java
 *
 * Created on 19 de noviembre de 2006, 19:16
 */

package ensamblador.datos;

import ensamblador.datos.Pipeline;
import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Registro;
import ensamblador.datos.Tipos;

public class UnidadAnticipacion implements Tipos {
    
    //private Instruccion[] instPipeline = Pipeline.getInstacia().getInstrucciones();
    private static UnidadAnticipacion unidadAnticipacion = new UnidadAnticipacion();
    
    
    /** Creates a new instance of UnidadAnticipacion */
    private UnidadAnticipacion() {};
    
    public static UnidadAnticipacion getInstancia() {
        return unidadAnticipacion;
    }
    
    public boolean[] anticipar() {
        Instruccion[] instPipeline = Pipeline.getInstacia().getInstrucciones();
        boolean ant[] = new boolean[4];
        Registro regMEM = null;
        Registro regWB = null;
        
        for (int i=0; i<ant.length; i++) {
            ant[i] = false;
        }
        
        if (instPipeline[ETAPA_MEM].regModificable() != null) {
            regMEM = instPipeline[ETAPA_MEM].regModificable();
            
            if (regMEM == instPipeline[ETAPA_EX].getRegRS()) {
                ant[ANT_RS_MEM] = true;
            }
            if (regMEM == instPipeline[ETAPA_EX].getRegRT()) {
                ant[ANT_RT_MEM] = true;
            }
        }
        
        if (instPipeline[ETAPA_WB].regModificable() != null) {
            regWB = instPipeline[ETAPA_WB].regModificable();
            
            if ((regMEM != instPipeline[ETAPA_EX].getRegRS()) && (regWB == instPipeline[ETAPA_EX].getRegRS())) {
                ant[ANT_RS_WB] = true;
            }
            if ((regMEM != instPipeline[ETAPA_EX].getRegRT()) && (regWB == instPipeline[ETAPA_EX].getRegRT())) {
                ant[ANT_RT_WB] = true;
            }   
        } 
        return ant;
    }
    
    
}
