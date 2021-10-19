package ensamblador.datos;

import java.util.Observable;

import ensamblador.instrucciones.Syscall;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.SyscallException;

public class InfoThread extends Observable{

	private int ciclos;
	private int exception;
	private static InfoThread infoThread;
	private InfoThread(){
		ciclos=0;
		exception=-1;
		infoThread=null;
	}
	
	public static InfoThread getInstancia(){
		if(infoThread==null){
			infoThread=new InfoThread();			
		}
		return infoThread;
		
	}
	
	public void setCiclos(int ciclos){
		this.ciclos=ciclos;
	}
	
	public int getCiclos(){
		return ciclos;
	}
	public void setException(int exception){
		this.exception=exception;
	}
	
	public boolean hayException(){
		if(this.exception!=-1){
			return true;
		}
		return true;
	}
	
	public void getException() throws EjecucionException{
	
		if(this.exception!=-1){
			if(esSyscallException(exception)){
				throw new SyscallException(exception, Syscall.print(exception));
			}else{
				throw new EjecucionException(exception);
			}
		}
	}
	
	private boolean esSyscallException(int tipo) {
		if((tipo==4) || (tipo==7) ||(tipo==10) ||(tipo==11)){
			return true;
		}
		return false;
	}
	
	
	public void notificar(){
		this.setChanged();
		this.notifyObservers();
	}
}
