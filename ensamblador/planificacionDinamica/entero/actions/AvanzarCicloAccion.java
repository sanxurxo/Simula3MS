package ensamblador.planificacionDinamica.entero.actions;

import ensamblador.datos.UnidadAnticipacion;
import ensamblador.datos.UnidadDeteccionRiesgos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Jr;
import ensamblador.instrucciones.Nop;
import ensamblador.planificacionDinamica.entero.Pipeline;
import ensamblador.registros.Ra;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.EjecucionException;

public class AvanzarCicloAccion extends Accion {

	private Instruccion instruccion;
	private Pipeline pipeline;    
	
	private UnidadDeteccionRiesgos unidadDeteccionRiesgos = UnidadDeteccionRiesgos.getInstancia();
	private int avance = 0;
	private Instruccion instID=new Nop(), instIF=new Nop(), instEX=new Nop(), instMEM=new Nop(), instWB=new Nop();
	private int excep;
	private int cicloActual;
	
	public AvanzarCicloAccion(Instruccion instruccion, Pipeline pipeline, int cicloActual) {
		super();		
		
		this.pipeline=pipeline;
		this.instruccion=instruccion;
		this.cicloActual=cicloActual;
		UnidadAnticipacion unidadAnticipacion = UnidadAnticipacion.getInstancia();
		UnidadDeteccionRiesgos unidadDeteccionRiesgos = UnidadDeteccionRiesgos.getInstancia();
		int avance = 0;
		pipeline.setEjecutandoSalto(false);
		
	}
	
	@Override
	public void ejecutar() throws EjecucionException{
		avance = avanzar();
		try{
			
			if(avance!=2){
				instWB=pipeline.getInstruccion(4);     
				excep=instWB.ejecutarWB();            
				analizaExcep(excep);
				
				instMEM=pipeline.getInstruccion(3);
				excep=instMEM.ejecutarMEM();            
				analizaExcep(excep);
				
				instEX=pipeline.getInstruccion(2);
				excep=instEX.ejecutarEX();            
				analizaExcep(excep); 
				
				instID=pipeline.getInstruccion(1);
				if(instID.isSalto()){
					//    	pipeline.setEjecutandoSalto(true); /*Si esta aqui es q ya sale del riesgo de control por eso no hace falta*/

				}
				if (!(instID instanceof Jr))               
					excep=instID.ejecutarID();                 
				else                   
					if (Ra.getRegistro().estaLibre())
						excep=instID.ejecutarID();                       
				
				analizaExcep(excep);
				
				if(instID.regModificable()!=null){
					instID.regModificable().setCicloModificado(this.cicloActual);
				}
				
				instIF=pipeline.getInstruccion(0);
				if(instIF.isSalto()){
	
					pipeline.setEjecutandoSalto(true);
				}

				if((avance==0) && (!pipeline.getInstruccion(1).isSalto())){
	//				System.out.println("se ejecutaIF entero");
					//       	pipeline.setEjecutarIF(true);            	
				}else{
		//			System.out.println("no se ejecutaIF entero");
					//      	pipeline.setEjecutarIF(false);
				}
				
			}
			else
			{
				excep=instruccion.ejecutar();
				analizaExcep(excep);            
			
			}   
		}catch(EjecucionException e){
			throw e;
		}
	}
	
	
	
	private void analizaExcep(int excep) throws EjecucionException{
		if(excep!=-1){
			throw new EjecucionException(excep);
		}

	}
	private int avanzar(){
		

			if (!unidadDeteccionRiesgos.detener()) {
		
				for (int i=4; i>0; i--) {
					pipeline.setInstruccion(i, pipeline.getInstruccion(i-1));					
				}
				pipeline.setInstruccion(0,instruccion);
				avance = 0;       //avance=0-->avance de todo el pipeline
			}
			else {

				for (int i=4; i>2; i--) {
					pipeline.setInstruccion(i, pipeline.getInstruccion(i-1));					
				}
				pipeline.setInstruccion(2, new Nop());
				avance = 1;         //avance=1-->se inserta burbuja en etapa EX
			}
		return avance;		
	}
	
	
	
	
	
}
