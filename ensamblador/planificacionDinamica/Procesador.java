package ensamblador.planificacionDinamica;

import java.util.Observer;

import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;
import ensamblador.estado.Riesgos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Nop;
import ensamblador.instrucciones.Syscall;
import ensamblador.planificacionDinamica.entero.Pipeline;
import ensamblador.registros.Registro;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.SyscallException;

public abstract class Procesador {
	protected Codigo instrucciones;
	protected CoprocesadorFP coProcFP;
	protected Pipeline coProcEntero;
	private  Instruccion instruccion=null;	
	protected Riesgos riesgos=null;
	private int ciclos;
	private long pcAnterior=0;
	private Observer observador=null;
	
	public Procesador(Codigo instrucciones) {
		super();
		
		this.instrucciones=instrucciones;
		this.coProcEntero=Pipeline.getInstacia();		
		this.riesgos=Riesgos.getRiesgos();		
		this.riesgos.inicializar();
		this.setCiclos(0);
	
	}
	
	public void inicializar(){
		this.coProcEntero=Pipeline.getInstacia();		
//		this.riesgos=Riesgos.getRiesgos();		
//		this.riesgos.inicializar();
		this.setCiclos(0);
		this.registrarObservadores(this.observador);
	}
	
	public void registrarObservadores(Observer observer){
		this.observador=observer;
		
		this.coProcFP.getEstadoRegistros().addObserver(observer);
		this.coProcFP.getEstadoInstrucciones().addObserver(observer);
		this.coProcFP.addObserver(observer);
		//Es necesario anhadir a las estaciones de reserva y 
		//los buffers como observadores del bus
	}
	
	public boolean estaEjecutando(String pc){	
		Palabra pcPalabra=new Palabra(pc, true);
		int elPc=(int)pcPalabra.getDec();
		Palabra pcAnterior=new Palabra(this.getPcAnterior());

		if(pcPalabra.getDec()==pcAnterior.getDec()){
		
			return true;
		}
		
		if(coProcEntero.estaEjecutando(elPc, instrucciones)){
			return true;
		}
		if(coProcFP.estaEjecutando(elPc, instrucciones)){
			return true;
		}
		if(coProcFP.getInstruccionDecodif()!=null){
			Instruccion ins=coProcFP.getInstruccionDecodif().getInstruccion();
			if(ins!=null){
				if(instrucciones.getPcInst(ins)==elPc){
					return true;
				}
			}
		}
		return false;
	}
	
	
	
	public int ejecutar(Registro Pc, Palabra breakpoint)throws EjecucionException{
		
		try{
			while((!instrucciones.estaFueraRango((int)Pc.getPalabra().getDec())) ||
					(!coProcEntero.estaParado()) || (!coProcFP.estaParado())){
				ejecutarCiclo(Pc.getPalabra().getDec(), breakpoint);			

				if(this.getCiclos()>=500){
					
					throw new EjecucionException(16);
				}
		
			}
		}
		catch(EjecucionException e){
			throw e;
		}
		return ciclos;
	}
	
	public int getCiclos(){
		return ciclos;
	}
	
	
	
	public boolean estaEnBreakpoint(Palabra breakpoint, long pcActual){
		if(pcActual>=breakpoint.getDec()){
			return true;
		}
		return false;
	}
	
	public void ejecutarCiclo(long pcActual, Palabra breakpoint) throws EjecucionException{		
		
		//HAY Q MIRAR CUANDO HEMOS ACABADO
		//SERIA CUANDO YA NO HUBIERA INSTR EJECUCIONES	
		this.setCiclos(this.getCiclos()+1);
		riesgos.setCiclos(riesgos.getCiclos()+1);

		if((instrucciones.estaFueraRango((int)pcActual)) || (estaEnBreakpoint(breakpoint, pcActual))){
			instruccion=null;
		} else{
			instruccion=instrucciones.obtener((int) pcActual);
		}
		try{			
			if(instruccion!=null){
				if(instruccion instanceof Syscall){

					coProcFP.ejecutar(pcActual, null);			

					if(coProcEntero.estaParado() && coProcFP.estaParado()){
						setPcAnterior(pcActual);
						analizaExcep(instruccion.ejecutar());							
					}else{										
						coProcEntero.ejecutarCiclo(new Nop(),(int) pcActual);
						riesgos.setNumNOPs(riesgos.getNumNOPs()+1);
					}
					riesgos.setNumInstrucciones(riesgos.getNumInstrucciones()+1);
						
					
				}else{
	
					if(avanzoEjecucion(pcActual)){
						if(instruccion.isInstruccionFP()){							
							coProcFP.ejecutar(pcActual, instruccion);
							coProcEntero.ejecutarCiclo(new Nop(),(int) pcActual);
						}else{														
							coProcFP.ejecutar(pcActual, null);
							coProcEntero.ejecutarCiclo(instruccion,(int) pcActual);
						}
						riesgos.setNumInstrucciones(riesgos.getNumInstrucciones()+1);
					}else{
						if(instruccion.isInstruccionFP()){							
							coProcFP.ejecutar(pcActual, instruccion);
							coProcEntero.ejecutarCiclo(new Nop(),(int) pcActual);
						}else{							
							coProcFP.ejecutar(pcActual, null);
							coProcEntero.ejecutarCiclo(new Nop(),(int) pcActual);
							riesgos.setNumNOPs(riesgos.getNumNOPs()+1);
						}
					}
					
					
					if(instruccion.isInstruccionFP() && (!coProcFP.isPipelineDetenido()) && (coProcFP.isEjecutarIF())){				
						instruccion.ejecutarIF();

					}
					
					if((!instruccion.isInstruccionFP()) && (!coProcFP.isPipelineDetenido()) && (coProcFP.isEjecutarIF())){
						instruccion.ejecutarIF();
						//			coProcFP.setNumInstruccion(coProcFP.getNumInstruccion()+1);
					}
					if(coProcEntero.isEjecutandoSalto() || coProcFP.isEjecutandoSalto()){
						coProcFP.setPipelineDetenido(true);
						riesgos.setRiesgosControl(riesgos.getRiesgosControl()+1);
					}else{
						coProcFP.setPipelineDetenido(false);
					}
					setPcAnterior(pcActual);	
				}
				
			}else{
				

				coProcFP.ejecutar(pcActual, null);
				coProcEntero.ejecutarCiclo(new Nop(),(int) pcActual);
				
				if(coProcEntero.isEjecutandoSalto() || coProcFP.isEjecutandoSalto()){
					coProcFP.setPipelineDetenido(true);
					riesgos.setRiesgosControl(riesgos.getRiesgosControl()+1);
				}else{
					coProcFP.setPipelineDetenido(false);
				}
				
				if(coProcEntero.estaParado() && coProcFP.estaParado()){
					throw new EjecucionException(15);
				}
				
			}		
			
		}catch (EjecucionException e){
			
			throw e;
			
		}
		
	}
	
	
	
	
	
	private boolean avanzoEjecucion(long pcActual){
		if(pcActual==getPcAnterior()){
			return false;
		}
		return true;
	}
	
	protected abstract Accion getAccion(long pcActual, Instruccion instruccion, CoprocesadorFP coprocFP);
	
	
	protected Instruccion getInstruccionActual(long pcActual){
		Instruccion instruccion=null;
		if(!instrucciones.estaFueraRango((int)pcActual)){		
			instruccion=instrucciones.obtener((int) pcActual);
		} 
		return instruccion;
	}
	
	
	public Riesgos getRiesgos() {
		return riesgos;
	}
	
	
	public long getPcAnterior() {
		return pcAnterior;
	}
	
	
	public void setPcAnterior(long pcAnterior) {
		this.pcAnterior = pcAnterior;
	}
	
	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
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
	
	
}
