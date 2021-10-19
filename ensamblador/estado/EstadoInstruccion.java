package ensamblador.estado;

import java.util.Observable;

import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Instruccion;

public class EstadoInstruccion extends Observable {
	private Instruccion instruccion;
	private Long pcEmision;
	private int status;
	

	public EstadoInstruccion(Instruccion instruccion, Long pc) {
		this.instruccion=instruccion;
		this.pcEmision=pc;
		this.status=Tipos.INICIO;		
	}


	public int getStatus() {
		return status;
	}


	public void setStatus(int status) {
		this.status = status;
		setChanged();
		notifyObservers();
	}


	public Instruccion getInstruccion() {
		return instruccion;
	}


	public Long getPcEmision() {
		return pcEmision;
	}



	public String toString(){
		return "Instruccion--> "+instruccion.toString() +" pc = "+pcEmision+ " estado= "+status; 
		
	}

}
