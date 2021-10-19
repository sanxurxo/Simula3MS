package ensamblador.procesadorMonociclo;

import ensamblador.datos.Codigo;
import ensamblador.datos.InfoThread;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Registro;
import ensamblador.util.excepcion.FinCodigoException;

public class ThreadEjecutarCodigo extends ThreadEjecutar{

	Registro pc;
	Palabra breakpoint;
	int exception=-1;
	protected Codigo instrucciones;
	private Instruccion instruccion;
	
	private InfoThread infoThread;
	private boolean ejecutar=true;
	public ThreadEjecutarCodigo(ProcesadorMonociclo procesador){
		super();

		this.procesador=procesador;
		instrucciones=Codigo.getInstacia();
		infoThread=InfoThread.getInstancia();
		this.setPriority(Thread.MIN_PRIORITY);
	}
	
	
	

	
	public int ejecutar(Registro pc, Palabra breakpoint){

			while((!instrucciones.estaFueraRango((int)pc.getPalabra().getDec())) && (exception==-1) &&(ejecutar)){
				try{					
				ejecutarCiclo(pc.getPalabra(), breakpoint);			
				}catch(FinCodigoException e){
					this.setException(15);
				}
			}
			
			if((instrucciones.estaFueraRango((int)pc.getPalabra().getDec())) && (!this.isRutina(pc.getPalabra()))){
				this.setException(15);
				
			}
			if(!this.isRutina(pc.getPalabra())){
				procesador.iniciarThreadCodigo();
			}
						
			procesador.setCiclos(this.getCiclos());
			infoThread.setCiclos(this.getCiclos());
			infoThread.setException(this.getException());
			infoThread.notificar();
			
		return this.getCiclos();
		
	}
	
	public void setEjecutar(boolean ejecutar){
		this.ejecutar=ejecutar;
	}

	
	public void ejecutarCiclo(Palabra pcActual, Palabra breakpoint)throws FinCodigoException{
	int excep;
	
	if((instrucciones.estaFueraRango((int)pcActual.getDec())) || (estaEnBreakpoint(breakpoint, pcActual.getDec()))){
		
		this.setException(15);
		procesador.iniciarThreadCodigo();
		throw new FinCodigoException();

	} else{
		
		instruccion=instrucciones.obtener((int) pcActual.getDec());		
		this.setCiclos(this.getCiclos()+1);
			
			if(instruccion!=null){
	
				excep=instruccion.ejecutar();
				this.setException(excep);				
			}

	}
	
}
	
	
	public void setException(int except){
		if(this.exception==-1){
	
			this.exception=except;
		}
	}
	
	public int getException() {
		return this.exception;

	}
	
	private boolean esSyscallException(int tipo) {
		if((tipo==4) || (tipo==7) ||(tipo==10) ||(tipo==11)){
			return true;
		}
		return false;
	}
	
	
	
	public boolean estaEnBreakpoint(Palabra breakpoint, long pcActual){
		if(pcActual>=breakpoint.getDec()){
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
