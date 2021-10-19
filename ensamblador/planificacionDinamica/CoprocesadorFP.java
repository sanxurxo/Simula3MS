package ensamblador.planificacionDinamica;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ensamblador.datos.Codigo;
import ensamblador.estado.EstadoInstruccion;
import ensamblador.estado.EstadoInstrucciones;
import ensamblador.estado.EstadoRegistros;
import ensamblador.estado.Riesgos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.EjecucionException;

public abstract class CoprocesadorFP extends Observable implements Observer{
	
	protected EstadoRegistros estadoRegistros;
	protected EstadoInstrucciones estadoInstrucciones;
	protected Vector<EstadoInstruccion> emision;

	protected boolean pipelineDetenido=false;
	protected int numInstruccion=0;
	protected boolean ejecutarIF=true;
	protected boolean ejecutandoSalto=false;
	private EstadoInstruccion instruccionDecodif=null;
	private long pcAnterior=0;
	protected Riesgos riesgos;
	protected boolean leido=true;
	
	
	
	public CoprocesadorFP() {
		super();
		riesgos=Riesgos.getRiesgos();
		riesgos.inicializar();
		estadoRegistros=EstadoRegistros.getEstadoRegistro();
		estadoRegistros.inicializar();
		estadoRegistros.marcarCambios();
		estadoInstrucciones=EstadoInstrucciones.getEstadoInstrucciones();
		estadoInstrucciones.inicializar();
		estadoInstrucciones.marcarCambios();
		emision=new Vector<EstadoInstruccion>();	
	}
	
	public Riesgos getRiesgos() {
		return riesgos;
	}

	public EstadoRegistros getEstadoRegistros() {
		return estadoRegistros;
	}

	public void setEstadoRegistros(EstadoRegistros estadoRegistros) {
		this.estadoRegistros = estadoRegistros;
	}

	public void ejecutar(long pcActual, Instruccion instruccion) throws EjecucionException{
		Accion avanzarCiclo=getAccion(pcActual, instruccion);
		avanzarCiclo.ejecutar();
	}
	
	protected abstract Accion getAccion(long pcActual, Instruccion instruccion); 

	public abstract boolean estaParado(); 
	
	public abstract boolean estaEjecutando(int pc, Codigo instrucciones);

	
	public void analizaExcep(int excep) throws EjecucionException{
		if(excep!=-1){
			throw new EjecucionException(excep);
		}
	}



	public Vector<EstadoInstruccion> getEmision() {
		return emision;
	}




	public void setEmision(Vector<EstadoInstruccion> emision) {
		this.emision = emision;
	}



	public EstadoInstrucciones getEstadoInstrucciones() {
		return estadoInstrucciones;
	}
	
	public boolean isPipelineDetenido(){
		return pipelineDetenido;
	}
	
	public void setPipelineDetenido(boolean pipelineDetenido){
		this.pipelineDetenido=pipelineDetenido;
	}




	public int getNumInstruccion() {
		return numInstruccion;
	}




	public void setNumInstruccion(int numInstruccion) {
		this.numInstruccion = numInstruccion;
	}

	public boolean isEjecutarIF() {
		return ejecutarIF;
	}

	public void setEjecutarIF(boolean ejecutarIF) {
		this.ejecutarIF = ejecutarIF;
	}

	public boolean isEjecutandoSalto() {
		return ejecutandoSalto;
	}

	public void setEjecutandoSalto(boolean ejecutandoSalto) {
		this.ejecutandoSalto = ejecutandoSalto;
	}
	
	public void mirarSalto(Instruccion instruccion){
		if(instruccion!=null){
			if(instruccion.isSalto()){

				setEjecutandoSalto(true);
				setPipelineDetenido(true);
			}
		}
	}

	public EstadoInstruccion getInstruccionDecodif() {
		if(estadoInstrucciones.tamanho()<= getNumInstruccion()){
			return null;
		}
		return estadoInstrucciones.getEstado(getNumInstruccion());

	}

	public void setInstruccionDecodif(EstadoInstruccion instruccionDecodif) {
		
		this.instruccionDecodif = instruccionDecodif;
	}

	public long getPcAnterior() {
		return pcAnterior;
	}

	public void setPcAnterior(long pcAnterior) {
		this.pcAnterior = pcAnterior;
	}

	public boolean isLeido() {
		return leido;
	}


	
}
