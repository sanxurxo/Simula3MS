package ensamblador.componentes.fus.estacionTomasulo;

import ensamblador.componentes.fus.FU;


public class ERTomasulo extends FU{

	public ERTomasulo(int latencia, int tipo, String nombre) {
		super(latencia, tipo, nombre);
		estado=new ERLibreTomasulo(nombre);
		estado.addObserver(this);
	}
	public void ocupar(){
		estado=new EROcupadaTomasulo(this.getNombre());
		setChanged();
		notifyObservers(this);
	}

	public void desocupar(){
		estado=new ERLibreTomasulo(this.getNombre());
		posicionActual=0;
		setChanged();
		notifyObservers(this);
	}


	

}
