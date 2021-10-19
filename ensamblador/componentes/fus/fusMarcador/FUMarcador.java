package ensamblador.componentes.fus.fusMarcador;

import ensamblador.componentes.fus.FU;


public class FUMarcador extends FU {


	
	public FUMarcador(int latencia, int tipo, String nombre) {
		super(latencia, tipo, nombre);
		estado=new FULibreMarcador(nombre);
		estado.addObserver(this);
	}
	
	public void ocupar(){
		estado=new FUOcupadaMarcador(this.getNombre());
		setChanged();
		notifyObservers(this);
	}

	public void desocupar(){
		estado=new FULibreMarcador(this.getNombre());
		posicionActual=0;
		setChanged();
		notifyObservers(this);
	}


}
