package ensamblador.estado;

import java.util.Hashtable;
import java.util.Observable;

public class EstadoRegistros extends Observable {

	private static Hashtable<String, String> registros=null;
	private static EstadoRegistros estadoRegistros=new EstadoRegistros();
	
	private EstadoRegistros() {				
		registros=new Hashtable<String, String>();
	}
	
	public static EstadoRegistros getEstadoRegistro(){
		if(registros==null){
			estadoRegistros=new EstadoRegistros();
		}
		return estadoRegistros;
	}
	
	public void inicializar(){
		estadoRegistros=new EstadoRegistros();
		setChanged();
		notifyObservers(registros);
	}
	
	public void registrar(String registro, String unidadFuncional){
		registros.put(registro.toString().toLowerCase(), unidadFuncional);
		setChanged();
		notifyObservers(registros);
	}
	
	public void marcarCambios(){
		setChanged();
		notifyObservers(registros);
	}
	
	public boolean estaRegistrado(String registro){

		return registros.containsKey(registro.toString().toLowerCase());
	}
	
	public void desregistrar(String registro){

		registros.remove(registro.toString().toLowerCase());
		setChanged();
		notifyObservers(registros);
	}
	

}
