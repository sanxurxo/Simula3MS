
package ensamblador.etapas;

//import ensamblador.registros.Pc;
import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Bc1f;
import ensamblador.instrucciones.Bc1t;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Jr;
import ensamblador.instrucciones.Nop;
import ensamblador.instrucciones.SaltoCondicional;
import ensamblador.instrucciones.Syscall;
//import ensamblador.instrucciones.Nop;
import ensamblador.registros.Ra;
import ensamblador.registros.Status;
import ensamblador.unidadFuncional.UFuncional;
import ensamblador.unidadFuncional.EstadoInstruccion;
//import ensamblador.datos.UnidadAnticipacion;
import ensamblador.datos.Tipos;

import java.util.*;

/**Pipeline.java
 *Clase que almacena las instrucciones que se estan ejecutando en cada momento en un procesador segmentado
 **/
public class PipelineFlotante implements Tipos{
    
    private static PipelineFlotante pipeline = null;
    private IF etapaIF;
    private ID etapaID;
    private EX etapaEX;
    private MEM etapaMEM;
    private WB etapaWB;
    private Codigo codigo;
    private UnidadDeteccionRiesgos unidadDeteccionRiesgos;
    private int salto;
    private boolean saltoTomado;
    private boolean activarIFFlush=false;
        
    
/**Constructor de Pipeline
 *@param Sin parametros
 **/
    private PipelineFlotante() {
    	etapaIF = IF.getInstancia();
    	etapaID = ID.getInstancia();
    	etapaEX = EX.getInstancia();
    	etapaMEM = MEM.getInstancia();
    	etapaWB = WB.getInstancia();
    	
    	codigo = Codigo.getInstacia();
    	unidadDeteccionRiesgos = UnidadDeteccionRiesgos.getInstancia();    	
    }
      
/**Metodo statico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Pipeline pipeline
 **/ 
    public static PipelineFlotante getInstancia()
    {    	     
    	if(pipeline == null){        
    		pipeline = new PipelineFlotante();        
    	}        
    	return pipeline;      
    }
    
/**Metodo que inicializa la unica instancia del pipeline
 *@param Sin parametros
 *@return void
 **/
    public void inicializar(int salto, Vector<UFuncional> unidFuncionales)     //salto_retardado-->0, salto_fijo-->1
    {
        this.salto = salto;
        etapaIF.inicializar();
        etapaID.inicializar();
        etapaEX.inicializar();
        etapaEX.setUnidFuncionales(unidFuncionales);
        etapaMEM.inicializar();
        etapaWB.inicializar();
    }
    
    public Vector<Instruccion> getInstrucciones()
    {
    	Vector<Instruccion> instrucciones = new Vector<Instruccion>();
    	EstadoInstruccion[] ei;
    	instrucciones.add(etapaIF.getEstadoInstruccion().getInstruccion());
    	instrucciones.add(etapaID.getEstadoInstruccion().getInstruccion());
    	for(UFuncional uf:etapaEX.getUnidFuncionales()) {
			ei = uf.getInstrucciones();
			for(EstadoInstruccion e:ei) {
				instrucciones.add(e.getInstruccion());
			}
		}
    	instrucciones.add(etapaMEM.getEstadoInstrucciones()[0].getInstruccion());
    	instrucciones.add(etapaMEM.getEstadoInstrucciones()[1].getInstruccion());
    	instrucciones.add(etapaWB.getEstadoInstrucciones()[0].getInstruccion());
    	instrucciones.add(etapaWB.getEstadoInstrucciones()[1].getInstruccion());
        return instrucciones;
    }    
    
    
/**Metodo que indica la posicion de una instruccion en el pipeline dada su direccion 
 *@param String direccion
 *@return int posicion
 **/
    public int posPipeline(String dir)
    {
        int dirIns=(int)(new Palabra(dir,true)).getDec();
        Vector<Instruccion> instrucciones = getInstrucciones();
        for (int i=0; i<instrucciones.size();i++)
        {
            if ((codigo.getPcInst(instrucciones.elementAt(i)))==dirIns)           
                return i;                        
        }
        return -1;
    }
    
    //con salto fijo, si se toma el salto, se activa IF_Flush
    public boolean activarIFFlush(Instruccion inst){
        if((salto==SALTO_FIJO) && (saltoTomado))
            activarIFFlush = true;
        else
        	activarIFFlush = false;
        return activarIFFlush;
    }
    
    public boolean activarIFFlush() {
    	if(etapaID.getEstadoInstruccion().getInstruccion().toString().startsWith("j")) 
    		activarIFFlush = true;
    	return activarIFFlush;
    }
    
/**Metodo para avanzar el pipeline
 *@param Instruccion instruccion que se anhade al pipeline
 *@return int tipo de avance
 **/
    public int avanzarPipeline(Instruccion inst, int ciclo)
    {
    	int avance = 0;
    	int errorEX = -1, errorMEM = -1;

    	//con salto fijo, si se toma el salto, se elimina la inst del hueco de retardo
        if(activarIFFlush()) {
        	etapaIF.inicializar();
        }
        
        if (!(inst.toString().equals("syscall")))
        {
            if (!unidadDeteccionRiesgos.detener()) {
            	
            	etapaWB.avanzar(etapaMEM);
            	etapaWB.ejecutar();
            	            	            	
            	etapaMEM.avanzar(etapaEX);
            	errorMEM = etapaMEM.ejecutar();
            	            	                        
            	etapaEX.moverCiclo(etapaMEM);
            	etapaEX.recordarCiclo(ciclo);
            	avance = etapaEX.avanzar(etapaID);
            	errorEX = etapaEX.ejecutar();
            	
            	if(avance == -1){              		
            		etapaID.avanzar(etapaIF);
            		etapaID.ejecutar();            		            		
                       	                  	  
            		saltoTomado = etapaID.getEstadoInstruccion().getInstruccion().esSaltoTomado();                              
            		            	                		
            	    etapaIF.anhadirInstruccion(inst);
            	    etapaIF.avanzar(etapaIF);
            	    if((!saltoTomado)) {
            	    	if(etapaID.getEstadoInstruccion().getInstruccion() instanceof SaltoCondicional) {;}
            	    	else {
            	    		etapaIF.ejecutar();
            	    	}
            	    }
            	}
            	avance = 0;       //avance=0-->avance de todo el pipeline
            }
            else {   
            	etapaWB.avanzar(etapaMEM);
            	etapaWB.ejecutar();
            	            	
            	etapaMEM.avanzar(etapaEX);
            	errorMEM = etapaMEM.ejecutar();
            	            	
            	etapaEX.moverCiclo(etapaMEM);
            	errorEX = etapaEX.ejecutar();
            	
            	etapaID.ejecutar();
				saltoTomado = etapaID.getEstadoInstruccion().getInstruccion().esSaltoTomado();  
				
				avance = 1;         //avance=1-->se inserta burbuja en etapa EX
            }
        }
        else {
            if (!estaParado()) {      
                if (!unidadDeteccionRiesgos.detener()) {
                	etapaWB.avanzar(etapaMEM);
                	etapaWB.ejecutar();
                	            	            	
                	etapaMEM.avanzar(etapaEX);
                	errorMEM = etapaMEM.ejecutar();
                	            	                        
                	etapaEX.moverCiclo(etapaMEM);
                	etapaEX.recordarCiclo(ciclo);
                	avance = etapaEX.avanzar(etapaID);
                	errorEX = etapaEX.ejecutar();
                	
                	if(avance == -1){              		
                		etapaID.avanzar(etapaIF);
                		etapaID.ejecutar();            		            		
                           	                  	  
                		saltoTomado = etapaID.getEstadoInstruccion().getInstruccion().esSaltoTomado();                              
                		            	                		
                	    etapaIF.anhadirInstruccion(new Nop());
                	    etapaIF.avanzar(etapaIF);
                	}
                	avance = 2;      //avance=2-->se inserta burbuja en etapa IF
                }
                else {
                	etapaWB.avanzar(etapaMEM);
                	etapaWB.ejecutar();
                	            	
                	etapaMEM.avanzar(etapaEX);
                	errorMEM = etapaMEM.ejecutar();
                	            	
                	etapaEX.moverCiclo(etapaMEM);
                	errorEX = etapaEX.ejecutar();
                	
                	etapaID.ejecutar();
    				saltoTomado = etapaID.getEstadoInstruccion().getInstruccion().esSaltoTomado();    
    				
    				avance = 1;         //avance=1-->se inserta burbuja en etapa EX
                }
            }
            else {
            	etapaWB.avanzar(etapaMEM);
            	etapaWB.ejecutar();
            	errorEX =  inst.ejecutar(); 
            	
            	avance = 3;         //avance=3-->ejcucion de SYSCALL
            }             
        }
        
        activarIFFlush(inst);
        construirDiagMult();
        return ((errorMEM != -1) ? errorMEM : errorEX);
    }
    
           
/**Metodo que indica si el pipeline esta parado
 *@param Sin parametros
 *@return boolean
 **/
    public boolean estaParado()
    {
    	Vector<Instruccion> instrucciones = getInstrucciones();
    	instrucciones.removeElementAt(instrucciones.size()-1);	//pues la ultima inst ya esta ejecutada
    	instrucciones.removeElementAt(instrucciones.size()-1);
    	for(Instruccion inst:instrucciones) {
    		if(!(inst.toString().equals("nop"))) {
    			return false;
    		}
    	}
    	return true;
    }
     
    
    public void construirDiagMult() {
    	etapaIF.actualizarEtapas();
    	etapaID.actualizarEtapas();
    	etapaEX.actualizarEtapas();
    	etapaMEM.actualizarEtapas();
    	etapaWB.actualizarEtapas();
    }
    
    
    public Vector<Vector<String>> getDiagMult() 
    {        
        Vector<EstadoInstruccion> estInstMult = getEstadoInstMult();
        Vector<Vector<String>> estInst = new Vector<Vector<String>>();
    	for (EstadoInstruccion ei:estInstMult)
    	{
    		estInst.add(ei.getEtapas());
    	}
    	return estInst;
    }
    
    
    public Vector<Instruccion> getInstMult()
    {    	
    	Vector<EstadoInstruccion> estInstMult = getEstadoInstMult();
    	Vector<Instruccion> instMult = new Vector<Instruccion>();
    	for (EstadoInstruccion ei:estInstMult)
    	{
    		instMult.add(ei.getInstruccion());
    	}
    	return instMult;
    }
    
    private Vector<EstadoInstruccion> getEstadoInstMult()
    {
    	Vector<EstadoInstruccion> estInstMult = new Vector<EstadoInstruccion>();
    	EstadoInstruccion[] ei;
    	if(!(etapaWB.getEstadoInstrucciones()[0].getInstruccion().toString().equals("nop"))) {
    		estInstMult.add(etapaWB.getEstadoInstrucciones()[0]);
    	}
    	if(!(etapaWB.getEstadoInstrucciones()[1].getInstruccion().toString().equals("nop"))) {
    		estInstMult.add(etapaWB.getEstadoInstrucciones()[1]);
    	}
    	if(!(etapaMEM.getEstadoInstrucciones()[0].getInstruccion().toString().equals("nop"))) {
    		estInstMult.add(etapaMEM.getEstadoInstrucciones()[0]);
    	}
    	if(!(etapaMEM.getEstadoInstrucciones()[1].getInstruccion().toString().equals("nop"))) {
    		estInstMult.add(etapaMEM.getEstadoInstrucciones()[1]);
    	}
    	for(UFuncional uf:etapaEX.getUnidFuncionales()) {
			ei = uf.getInstrucciones();
			for(EstadoInstruccion e:ei) {
				if(!(e.getInstruccion().toString().equals("nop"))) {
					estInstMult.add(e);
				}
			}
		}
    	
    	ordenarPorCiclo(estInstMult);
    	//antes de ID e IF pq el ciclo se actualiza al entrar en EX; ID e IF seran las del final siempre
    	
    	if(!(etapaID.getEstadoInstruccion().getInstruccion().toString().equals("nop"))) {
    		estInstMult.add(etapaID.getEstadoInstruccion());
        }
    	if(!(etapaIF.getEstadoInstruccion().getInstruccion().toString().equals("nop"))) {
    		estInstMult.add(etapaIF.getEstadoInstruccion());
    	}    	
    	
        return estInstMult;
    }
    
    
    private void ordenarPorCiclo(Vector<EstadoInstruccion> estInstMult)
    {
    	EstadoInstruccion ei;
    	int j;
    	
    	for(int i=1; i< estInstMult.size(); i++)
    	{
    		ei = estInstMult.elementAt(i);
    		j = i-1;
    		while ((j>=0) && (estInstMult.elementAt(j).getCiclo() > ei.getCiclo()))
    		{
    			estInstMult.set(j+1, estInstMult.elementAt(j));
    			j = j-1;
    		}
    		estInstMult.set(j+1, ei);
    	}
    }
    
}
