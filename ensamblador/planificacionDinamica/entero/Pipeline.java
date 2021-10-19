
package ensamblador.planificacionDinamica.entero;

import ensamblador.datos.Codigo;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Nop;
import ensamblador.planificacionDinamica.entero.actions.AvanzarCicloAccion;
import ensamblador.util.excepcion.EjecucionException;

/**Pipeline.java
 *Clase que almacena las instrucciones que se estan ejecutando en cada momento en un procesador segmentado
 **/
public class Pipeline {
    
    private Instruccion instrucciones[] = new Instruccion[5];
    private static Pipeline pipeline =null;
    private boolean pipelineDetenido=false;
    private boolean ejecutarIF=true;
    private boolean ejecutandoSalto=false;
    
/**Constructor de Pipeline
 *@param Sin parametros
 **/
    private Pipeline() {
    	instrucciones=new Instruccion[5];
        for(int i=0;i<5;i++)
            instrucciones[i]=new Nop();
    }
      
    
/**Metodo statico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Pipeline pipeline
 **/ 
    public static Pipeline getInstacia()
    {
    	if(pipeline==null){
    		pipeline=new Pipeline();
    	}
        return pipeline;
    }
   
    
    public void ejecutarCiclo(Instruccion instruccion, int cicloActual) throws EjecucionException{
    	AvanzarCicloAccion accion=new AvanzarCicloAccion(instruccion, this, cicloActual);
    	accion.ejecutar();
           
    }
    

    public boolean estaParado(){
        int i;
        for(i=0;i<=4;i++)
            if (!(instrucciones[i].toString().equals("nop"))){
            	return false;
            }
        return true;
    }
    
  
    public Instruccion getInstruccion(int posicion){
    	return instrucciones[posicion];
    }
    public void setInstruccion(int posicion, Instruccion instruccion){
    	instrucciones[posicion]=instruccion;
    }
    public Instruccion[] getInstrucciones(){
    	return instrucciones;
    }

	/**
	 * Metodos q indican si se
	 * ha insertado alguna burbuja
	 * en el pipeline, es decir,
	 * esta detenido pq no se puede insertar
	 * nada en if 
	 */
    public boolean isPipelineDetenido() {
		return pipelineDetenido;
	}

	public void setPipelineDetenido(boolean pipelineDetenido) {
		this.pipelineDetenido = pipelineDetenido;
	}

	public boolean isEjecutarIF() {
		return ejecutarIF;
	}

	public void setEjecutarIF(boolean ejecutarIF) {
		this.ejecutarIF = ejecutarIF;
	}

	public boolean isEjecutandoSalto() {
		return ejecutandoSalto;
	}

	public void setEjecutandoSalto(boolean ejecutandoSalto) {
		this.ejecutandoSalto = ejecutandoSalto;
	}
   
	public boolean estaEjecutando(int pc, Codigo instrucciones){
		for(int i=0;i<getInstrucciones().length ;i++){
			if(instrucciones.getPcInst(getInstruccion(i))==pc){
				return true;
			}
		}
		return false;
	}    
}
