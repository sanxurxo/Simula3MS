package ensamblador.componentes.fus;

import java.util.Observable;

public abstract class EstadoFU extends Observable {
	protected String op=new String();
	protected String Qj=new String();
	protected String Qk=new String();
	protected String nombre=new String();
	protected boolean ocupada=false;
	
	
	protected EstadoFU(String nombre) {		
		this.nombre=nombre;
	}
	
	public void setOp(String op) {
		this.op = op;
	}

	public String getQj() {
		return Qj;
	}

	public void setQj(String qj) {
		Qj = qj;
	}

	public String getQk() {
		return Qk;
	}

	public void setQk(String qk) {
		Qk = qk;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;

	}

	public boolean isOcupada() {
		return ocupada;
	}

	public void setOcupada(boolean ocupada) {
		this.ocupada = ocupada;

	}
}
