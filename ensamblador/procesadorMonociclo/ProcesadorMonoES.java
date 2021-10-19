package ensamblador.procesadorMonociclo;

import java.util.Observer;

import ensamblador.datos.Codigo;
import ensamblador.instrucciones.Instruccion;

public abstract class ProcesadorMonoES extends ProcesadorMonociclo {
	protected Codigo instrucciones;	
	protected  Instruccion instruccion=null;		
	protected int ciclos;	
	protected Observer observador=null;

	
	public ProcesadorMonoES(Codigo instrucciones) {
		super(instrucciones);
		
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
	public abstract void parar();
}
