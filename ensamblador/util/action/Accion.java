package ensamblador.util.action;

import ensamblador.util.excepcion.EjecucionException;

public abstract class Accion {

	public Accion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public abstract void ejecutar() throws EjecucionException;
}
