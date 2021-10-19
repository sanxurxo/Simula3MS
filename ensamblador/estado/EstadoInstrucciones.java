package ensamblador.estado;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ensamblador.datos.Tipos;

public class EstadoInstrucciones extends Observable implements Observer{

	private static Hashtable<Integer, EstadoInstruccion> instrucciones=null;
	private static EstadoInstrucciones estadoInstrucciones=null;

	
	private EstadoInstrucciones() {
		instrucciones = new Hashtable<Integer, EstadoInstruccion>();
	}
	
	public void inicializar(){
		estadoInstrucciones=new EstadoInstrucciones();
	}
	
	public static EstadoInstrucciones getEstadoInstrucciones(){
		if(estadoInstrucciones==null){
			estadoInstrucciones=new EstadoInstrucciones();
		}
		return estadoInstrucciones;
	}
	
	public void registrar(int numInstruccion, EstadoInstruccion estado){
		estado.addObserver(this);
		instrucciones.put(new Integer(numInstruccion), estado);		
		
	}
	public int tamanho(){
		return instrucciones.size();
	}
	
	public EstadoInstruccion getEstado(int numInstruccion){
		return instrucciones.get(new Integer(numInstruccion));
	}
	

	public void marcarCambios(){
		setChanged();
		notifyObservers(getEstados());
		
	}
	
	//En el caso de que se haya registrado
	//pero no se haya empezado a ejecutar (status=inicio)
	//devuelve true
	public boolean isRegistradaInicio(int numInstruccion){
		EstadoInstruccion estado;
		if((estado=instrucciones.get(numInstruccion))!=null){
			if(estado.getStatus()==Tipos.INICIO){
				return true;
			}
		}
		return false;
	}
	
	private Vector<EstadoInstruccion> getEstados(){
		Vector<EstadoInstruccion> estados=new Vector<EstadoInstruccion>();
		int pos=0;

		for(int i=0;i<instrucciones.size();i++){
			if(instrucciones.containsKey(new Integer(pos))){
				estados.add(instrucciones.get(new Integer(pos)));				
			}
			pos++;
		}
		
		return estados;
	}
	public void update(Observable o, Object arg) {
//		setChanged();
//		notifyObservers(instrucciones.elements());
		
	}
	
	
	
}
