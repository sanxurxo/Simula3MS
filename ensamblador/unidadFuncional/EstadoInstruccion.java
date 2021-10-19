package ensamblador.unidadFuncional;

import ensamblador.instrucciones.Instruccion;
import java.util.Vector;

public class EstadoInstruccion {
	private Instruccion instruccion;
	private int ciclo;
	private boolean ejecutada;
	private Vector<String> etapas;
	
	public EstadoInstruccion(Instruccion inst) {
		this.instruccion = inst;
		this.ciclo = -1;
		this.ejecutada = false;
		this.etapas = new Vector<String>();
	}
	

	public int getCiclo() {
		return ciclo;
	}

	public void setCiclo(int ciclo) {
		this.ciclo = ciclo;
	}
	
	public boolean getEjecutada() {
		return ejecutada;
	}
	
	public void setEjecutada(boolean ejecutada) {
		this.ejecutada = ejecutada;
	}

	public Instruccion getInstruccion() {
		return instruccion;
	}

	public void setInstruccion(Instruccion instruccion) {
		this.instruccion = instruccion;
	}
	
	public void anhadirEtapa(String et) {
		etapas.add(et);
	}
	
	public String getUltimaEtapa() {
		return etapas.lastElement();
	}
	
	public String getEtapa(int pos) {
		return etapas.elementAt(pos);
	}
	
	public Vector<String> getEtapas() {
		return etapas;
	}
}
