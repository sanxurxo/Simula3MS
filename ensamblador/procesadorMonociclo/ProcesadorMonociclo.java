package ensamblador.procesadorMonociclo;

import java.util.Observer;

import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Syscall;
import ensamblador.registros.Registro;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.SyscallException;

public class ProcesadorMonociclo{
	protected Codigo instrucciones;	
	private  Instruccion instruccion=null;		
	private int ciclos;	
	private Observer observador=null;
	protected ThreadEjecutar threadEjecutar;
	public ProcesadorMonociclo(Codigo instrucciones){
		super();
		this.instrucciones=instrucciones;
		this.setCiclos(0);
	}
	
	public void inicializar(){
		this.setCiclos(0);
		this.registrarObservadores(this.observador);
	}

	public void registrarObservadores(Observer observer){
		this.observador=observer;
	}
	
	public int getCiclos(){
		return ciclos;
	}
	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}
	
	
	public int ejecutar(Registro Pc, Palabra breakpoint)throws EjecucionException{
		
		try{
			while(!instrucciones.estaFueraRango((int)Pc.getPalabra().getDec())){
				ejecutarCiclo(Pc.getPalabra().getDec(), breakpoint);			
				
				if(this.getCiclos()>=500000){				
					throw new EjecucionException(16);
				}		
			}
			if(instrucciones.estaFueraRango((int)Pc.getPalabra().getDec())){
				throw new EjecucionException(15);
			}
		}
		catch(EjecucionException e){
			throw e;
		}
		return ciclos;

		
		
	}
	
	public String getImagenActual(){
		
		if(instruccion!=null){		
			return instruccion.visualizar(ciclos);
		}
		return new String();
	}
	
	public void ejecutarCiclo(long pcActual, Palabra breakpoint) throws EjecucionException{
		int excep;
		
		if((instrucciones.estaFueraRango((int)pcActual)) || (estaEnBreakpoint(breakpoint, pcActual))){
			throw new EjecucionException(15);
		} else{	
			instruccion=instrucciones.obtener((int) pcActual);		
			this.setCiclos(this.getCiclos()+1);
			try{			
				if(instruccion!=null){
					excep=instruccion.ejecutar();
					analizaExcep(excep);
				}
			}catch (EjecucionException e){			
				throw e;			
			}
		}
		
	}
	
	
	public boolean estaEnBreakpoint(Palabra breakpoint, long pcActual){
		if(pcActual>=breakpoint.getDec()){
			return true;
		}
		return false;
	}
	private void analizaExcep(int excep) throws EjecucionException{
		if(excep!=-1){
			if(esSyscallException(excep)){
				throw new SyscallException(excep, Syscall.print(excep));
			}else{
				throw new EjecucionException(excep);
			}
		}
	}
	
	private boolean esSyscallException(int tipo) {

		if((tipo==4) || (tipo==7) ||(tipo==10) ||(tipo==11)){
			return true;
		}
		return false;
	}
	
	public void iniciarThreadCodigo(){
		threadEjecutar=new ThreadEjecutarCodigo(this);
	}
	
	public boolean isThreadParado(){
		if(threadEjecutar==null){
			return true;
		}
		if(threadEjecutar.isAlive()){
			return false;
		}
		return true;
	}

}
