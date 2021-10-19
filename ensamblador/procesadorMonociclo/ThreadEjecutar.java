package ensamblador.procesadorMonociclo;

import ensamblador.datos.Codigo;
import ensamblador.datos.InfoThread;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Registro;
import ensamblador.util.excepcion.FinCodigoException;

public abstract class ThreadEjecutar extends Thread{

	Registro pc;
	protected Palabra breakpoint;
	int exception=-1;
	protected Codigo instrucciones;
	protected Instruccion instruccion;
	protected int ciclos;
	protected ProcesadorMonociclo procesador;
	protected InfoThread infoThread;
	protected boolean ejecutar=true;
	

	
	public void iniciar(Registro pc, Palabra breakpoint, int ciclos){
		this.pc=pc;
		this.breakpoint=breakpoint;
		this.ciclos=ciclos;
		instrucciones=Codigo.getInstacia();
		infoThread=InfoThread.getInstancia();
		this.setEjecutar(true);
	}
	public void run(){
		this.ejecutar(pc, this.breakpoint);
	}
	
	public int getCiclos(){
		return this.ciclos;
	}
	
	public void setCiclos(int ciclos){
	
		this.ciclos=ciclos;
	}
	
	
	public abstract int ejecutar(Registro Pc, Palabra breakpoint);
	
	public void setEjecutar(boolean ejecutar){
		this.ejecutar=ejecutar;
	}
	
	public boolean getEjecutar(){
		return this.ejecutar;
	}
	
	public abstract void ejecutarCiclo(Palabra pcActual, Palabra breakpoint) throws FinCodigoException;
	
	
	public void setException(int except){
		if(this.exception==-1){
	
			this.exception=except;
		}
	}
	
	public boolean isRutina(Palabra pc){
		if(pc.getDec()<(new Palabra("0x80000080", true)).getDec()){
			return false;
		}
		return true;
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
	
	public abstract String getImagenActual();
	
	
}
