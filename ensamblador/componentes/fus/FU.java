package ensamblador.componentes.fus;

import ensamblador.componentes.Componente;

public abstract class FU extends Componente {


	protected EstadoFU estado;
	protected int tipo;
	
	public FU(int latencia, int tipo, String nombre) {
		super(latencia, nombre);
		this.tipo=tipo;
	}
	
	public EstadoFU getEstado() {
		return estado;
	}
	public void setEstado(EstadoFU estado) {
		this.estado = estado;
		setChanged();
		notifyObservers(this);

	}
	
	

	public abstract void ocupar();
	public abstract void desocupar();
	

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
		setChanged();
		notifyObservers(this);
	}

	}
