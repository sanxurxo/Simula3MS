package ensamblador.componentes.fus.fusMarcador;

import ensamblador.componentes.fus.EstadoFU;


public abstract class EstadoFUMarcador extends EstadoFU {

		
	protected String Fi=new String();
	protected String Fj=new String();
	protected String Fk=new String();
	protected boolean Rj=false;
	protected boolean Rk=false;
	
	
		
	protected EstadoFUMarcador(String nombre) {
		super(nombre);		

	}

	public String getFi() {
		return Fi;
	}

	public void setFi(String fi) {
		Fi = fi;
	}

	public String getFj() {
		return Fj;
	}

	public void setFj(String fj) {
		Fj = fj;

	}

	public String getFk() {
		return Fk;
	}

	public void setFk(String fk) {
		Fk = fk;

	}

	
	public String getOp() {
		return op;
	}

	public boolean isRj() {
		return Rj;
	}

	public void setRj(boolean rj) {
		Rj = rj;

	}

	public boolean isRk() {
		return Rk;
	}

	public void setRk(boolean rk) {
		Rk = rk;

	}

	
}
