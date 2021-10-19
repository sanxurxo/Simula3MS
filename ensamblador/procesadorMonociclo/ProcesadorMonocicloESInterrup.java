package ensamblador.procesadorMonociclo;

import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Syscall;
import ensamblador.registros.Cause;
import ensamblador.registros.Registro;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.FinCodigoException;
import ensamblador.util.excepcion.FinRutinaException;
import ensamblador.util.excepcion.SyscallException;

public class ProcesadorMonocicloESInterrup extends ProcesadorMonoES{


	private boolean ejecutandoRutina=false;
	public ProcesadorMonocicloESInterrup(Codigo instrucciones){
		super(instrucciones);		
		this.instrucciones=instrucciones;
		this.setCiclos(0);
		this.threadEjecutar=new ThreadEjecutarCodigo(this);
		this.ejecutandoRutina=false;
		
	}
	
	public void inicializar(){
		super.inicializar();
		this.threadEjecutar=new ThreadEjecutarCodigo(this);
	}
	
	public void parar(){
		this.threadEjecutar.setEjecutar(false);
		this.ejecutandoRutina=false;
	}
	
	
	public void anhadirRutinaInterrupcion(){
		
	
		if(!this.ejecutandoRutina){
			this.ejecutandoRutina=true;		
			this.threadEjecutar=new ThreadEjecutarRutina(this);

		}
	}
	
public String getImagenActual(){		
		return this.threadEjecutar.getImagenActual();
	}
	
	public int ejecutar(Registro Pc, Palabra breakpoint)throws EjecucionException{		
		threadEjecutar.iniciar(Pc, breakpoint,this.getCiclos());
		threadEjecutar.start();
		threadEjecutar.getException();
		this.setCiclos(threadEjecutar.getCiclos());
		return this.getCiclos();
	}
	
	public void iniciarThreadCodigo(){
		threadEjecutar=new ThreadEjecutarCodigo(this);
		this.ejecutandoRutina=false;
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
	public void ejecutarCiclo(long pcActual, Palabra breakpoint) throws EjecucionException{
	
		try{
		
			threadEjecutar.ejecutarCiclo(new Palabra(pcActual), breakpoint);		
			this.setCiclos(this.getCiclos()+1);

			
			this.analizaExcep(threadEjecutar.getException());

			threadEjecutar.setException(-1);
		}catch(FinRutinaException e){
			this.setCiclos(this.getCiclos()+1);
			this.threadEjecutar=new ThreadEjecutarCodigo(this);			
		}
		catch(FinCodigoException e){			
			throw new EjecucionException(15);
		}
		
	}

}
