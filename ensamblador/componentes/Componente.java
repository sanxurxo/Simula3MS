package ensamblador.componentes;

import java.util.Observable;
import java.util.Observer;

import ensamblador.estado.EstadoInstruccion;

public abstract class Componente extends Observable implements Observer {


	
	protected EstadoInstruccion instruccion;
	protected int latencia;
	protected int posicionActual;
	
	protected String nombre;
	
	public Componente(int latencia, String nombre) {
		
		this.latencia=latencia;
				
		this.nombre=nombre;
		posicionActual=0;
		instruccion=null;

	}
	
	public int getPosicionActual() {
		return posicionActual;
	}

	public void setPosicionActual(int posicionActual) {
		this.posicionActual = posicionActual;

	}

	public int getLatencia() {
		return latencia;
	}

	public void setLatencia(int latencia) {
		this.latencia = latencia;

	}


	public EstadoInstruccion getEstadoInstruccion() {
		return instruccion;
	}
	public void setEstadoInstruccion(EstadoInstruccion instruccion) {
		this.instruccion = instruccion;
		setChanged();
		notifyObservers(this);
	}
	


	public abstract void ocupar();
	public abstract void desocupar();
	

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
		setChanged();
		notifyObservers(this);
	}

	public void update(Observable o, Object arg) {

		setChanged();
		notifyObservers(this);		
	}

}
