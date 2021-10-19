package ensamblador.procesadorMonociclo;

import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;
import ensamblador.registros.Registro;
import ensamblador.util.excepcion.EjecucionException;

public class ProcesadorMonocicloESProg extends ProcesadorMonoES{
	
	public ProcesadorMonocicloESProg(Codigo instrucciones){
		super(instrucciones);
		this.instrucciones=instrucciones;
		this.setCiclos(0);
	}
	

	
	public void parar(){
		this.threadEjecutar.setEjecutar(false);
	}
	
	public int ejecutar(Registro Pc, Palabra breakpoint)throws EjecucionException{
	threadEjecutar=new ThreadEjecutarCodigo(this);
	threadEjecutar.iniciar(Pc, breakpoint, this.getCiclos());
	threadEjecutar.start();
	threadEjecutar.getException();

	this.setCiclos(threadEjecutar.getCiclos());
	return this.getCiclos();
		
	}
	
	

}
