package ensamblador.componentes.bus;

import java.util.Observable;

import ensamblador.datos.Palabra;

public class BusCBD extends Observable {
	
	private String estacion=new String();
	private String registro=new String();
	private Palabra resultado=null;
	private boolean ocupado=false;
	private boolean modificado=false;
	
	public BusCBD() {
		super();	
		estacion=new String();
		registro=new String();
		resultado=new Palabra(0);
	}
	
	public void setActualizacion(String estacion, String registro, Palabra resultado){
		this.estacion=estacion;
		this.registro=registro;
		this.resultado=resultado;
		this.modificado=true;	
		
	}
	public void notificar(){
	
		if(this.modificado){
	
			setChanged();
			notifyObservers(this);
			setOcupado(false);
			this.modificado=false;
		}
	}

	public boolean isOcupado() {
		return ocupado;
	}

	public void setOcupado(boolean ocupado) {
		this.ocupado = ocupado;
	}

	public String getEstacion() {
		return estacion;
	}

	public String getRegistro() {
		return registro;
	}

	public Palabra getResultado() {
		return resultado;
	}
	
	
}
