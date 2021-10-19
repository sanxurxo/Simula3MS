package ensamblador.procesadorMonociclo;

import ensamblador.datos.InfoThread;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Cause;
import ensamblador.registros.EPC;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Status;
import ensamblador.util.excepcion.FinCodigoException;
import ensamblador.util.excepcion.FinRutinaException;
import ensamblador.util.excepcion.ModelException;

public class ThreadEjecutarRutina extends ThreadEjecutar{

	Registro pc;
	Palabra breakpoint;
	int exception=-1;
	private Instruccion instruccion;	
	
	private InfoThread infoThread;
	private boolean ejecutar=true;
	protected Rutina rutina;
	public ThreadEjecutarRutina(ProcesadorMonociclo procesador){
		rutina=RutinaTeclado.getInstancia();
		this.procesador=procesador;
		infoThread=InfoThread.getInstancia();
		EPC.getRegistro().setPalabra(Pc.getRegistro().getPalabra().sumar(-4));
		EPC.getRegistro().notificar();
		Pc.getRegistro().setPalabra(new Palabra("0x80000080",true));
		Pc.getRegistro().notificar();
		this.actualizarStatus("0", "0");
		
		Cause.getRegistro().setPalabra(new Palabra("0x0000000", true));
		Cause.getRegistro().notificar();
		
		ejecutar=true;

		this.setPriority(Thread.MIN_PRIORITY);
	}



	public int ejecutar(Registro pc, Palabra breakpoint){
		while((rutina.estaEnRango(pc.getPalabra().getHex()))&&(ejecutar)){		
			try{
				ejecutarCiclo(pc.getPalabra(), breakpoint);
			}catch(FinCodigoException e){
				this.setException(17);
			}
		}

		if(rutina.estaEnRango(pc.getPalabra().getHex())){
					
		}
		if(ejecutar){
		
		}
		if(this.acaboRutina()){
			
			Cause.getRegistro().setPalabra(new Palabra("0x0000000", true));
			Cause.getRegistro().notificar();
			procesador.iniciarThreadCodigo();
		}
		if(!rutina.estaEnRango(pc.getPalabra().getHex())){
			this.setException(17);
		}
		procesador.setCiclos(this.getCiclos());

		
		infoThread.setCiclos(this.getCiclos());
		infoThread.setException(this.getException());
		infoThread.notificar();
		return this.getCiclos();
	}

	public void actualizarStatus(String usuKer, String interrup){
		/*Actualizando el registro status*/
		/*Moviendo de previo a antiguo (bits 3,2-->5,4)*/
		Status.getRegistro().getPalabra().setBit(5, Status.getRegistro().getPalabra().getBit(3));
		Status.getRegistro().getPalabra().setBit(4, Status.getRegistro().getPalabra().getBit(2));		
		/*Moviendo de actual a previo (bits 1,0-->3,2)*/
		Status.getRegistro().getPalabra().setBit(3, Status.getRegistro().getPalabra().getBit(1));
		Status.getRegistro().getPalabra().setBit(2, Status.getRegistro().getPalabra().getBit(0));		
		/*Actualizando actual*/
		Status.getRegistro().getPalabra().setBit(1, usuKer); 
		/*1-->indica que el programa se estaba ejecutando en modo usuario cuando se produjo la interrupcion
		 *0-->indica que el programa se estaba ejecutando en modo kernel cuando se produjo la interrupcion*/
		
		Status.getRegistro().getPalabra().setBit(0, interrup); 
		/*0-->indica que las interrupciones estan deshabilitadas
		 * 1-->/*indica que las interrupciones estan dhabilitadas*/
		
		Status.getRegistro().notificar();
	}
	

	public void ejecutarCiclo(Palabra pcActual, Palabra breakpoint)throws FinCodigoException {
		
		
		int excep;
		try{			
			instruccion=rutina.getInstruccion(pcActual.getHex());			
		}catch(ModelException e){
			this.setException(17);
			throw new FinCodigoException();
		
		}
		if(instruccion!=null){		
			this.setCiclos(this.getCiclos()+1);
			excep=instruccion.ejecutar();			
			this.setException(excep);
			
			if(acaboRutina()){				
				Cause.getRegistro().setPalabra(new Palabra("0x0000000", true));
				Cause.getRegistro().notificar();
				procesador.iniciarThreadCodigo();
				throw new FinRutinaException();
			}			
		}		
	}


	public boolean acaboRutina(){
		//Cause.getRegistro().setPalabra(new Palabra("0x0000060", true));
		long pc=Pc.getRegistro().getPalabra().getDec();
		long inicRutina=new Palabra("0x80000080",true).getDec();
		long inicPc=new Palabra("0x00400000",true).getDec();
		if((pc<inicRutina) &&(pc>=inicPc)){		
			return true;
		}
		return false;
	}
	
public String getImagenActual(){
		
		
		if(instruccion!=null){	
			return instruccion.visualizar(ciclos);
		}
		return new String();
	}
	
}
