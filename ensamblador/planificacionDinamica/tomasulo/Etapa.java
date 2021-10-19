package ensamblador.planificacionDinamica.tomasulo;

import java.util.Vector;
import ensamblador.componentes.fus.estacionTomasulo.ERTomasulo;


public class Etapa {

	private Vector<ERTomasulo> estacionesReserva;
	public Etapa() {		
		estacionesReserva=new Vector<ERTomasulo>();
	}
	
	
	 public Vector<ERTomasulo> getEstacionesReserva() {
		 Vector<ERTomasulo> copia= new Vector<ERTomasulo>();
		 for(int i=0;i<estacionesReserva.size();i++){
			 copia.addElement(estacionesReserva.elementAt(i));
		 }
		return copia;
	}

	public void vaciarEtapa(){
		this.estacionesReserva=new Vector<ERTomasulo>();
	}

	public void setEstacionesReserva(Vector<ERTomasulo> estacionesReserva) {
		this.estacionesReserva = estacionesReserva;
	}

	/**
     *metodo que anhade una estacion de Reserva que
     * se encuentra en ese etapa
     *@param Instruccion instruccion
     *return void
     **/	
	public void anhadirEstacionReserva(ERTomasulo estacionReserva){
		this.estacionesReserva.addElement(estacionReserva);
	}
	
	 /**
     *metodo que quita una estacion de reserva de una etapa
     *@param Instruccion instruccion
     *return true si se ha encontrado la instruccion
     **/
	public boolean removeEstacionReserva(ERTomasulo estacionReserva){		
		return this.estacionesReserva.remove(estacionReserva);		
	}
	
	public int getTamanho(){
		return estacionesReserva.size();
	}
	
}
