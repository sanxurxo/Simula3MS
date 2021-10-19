package ensamblador.unidadFuncional;

import java.awt.Color;

import ensamblador.instrucciones.Nop;
import ensamblador.instrucciones.Instruccion;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Tipos;

public abstract class UFuncional {
	protected int tipo;
	protected int latencia;
	protected EstadoInstruccion[] instrucciones;
	private Lenguaje lenguaje;
	
	public UFuncional(int tipo, int latencia) {
		this.tipo = tipo;
		this.latencia = latencia;
		lenguaje = Lenguaje.getInstancia();
		this.inicializar();
	}
	
	public void inicializar() {
		this.instrucciones = new EstadoInstruccion[latencia];
		for(int i=0; i<latencia; i++) {
			instrucciones[i] = new EstadoInstruccion(new Nop());
		}
	}

	public int getLatencia() {
		return latencia;
	}

	public void setLatencia(int latencia) {
		this.latencia = latencia;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
	public EstadoInstruccion[] getInstrucciones() {
		return instrucciones;
	}
	
	public void anhadirInstruccion(EstadoInstruccion inst, int ciclo) {
		instrucciones[0] = inst;
		instrucciones[0].setCiclo(ciclo);
	}
	
	
	public Instruccion getInstruccion(int pos) {
		return instrucciones[pos].getInstruccion();
	}
	
	public boolean estaVacia() {
		for(EstadoInstruccion inst:instrucciones) {
			if (! inst.getInstruccion().toString().equals("nop"))
				return false;
		}
		return true;
	}
	
	public boolean contieneInstEnFin(EstadoInstruccion inst) {
		if(instrucciones[instrucciones.length-1].getInstruccion().toString().equals(inst.getInstruccion().toString())) {
			return true;
		}
		return false;
	}
	
	
	public void avanzarCiclo() {
		for(int i=instrucciones.length-1; i>0; i--) {
			instrucciones[i] = instrucciones[i-1];
		}
		instrucciones[0] = new EstadoInstruccion(new Nop());
	}
	
	public EstadoInstruccion getInstFin() {
		return instrucciones[instrucciones.length-1];
	}
	
	public int ejecutar() {
		if(instrucciones[0].getEjecutada()) {
			return -1;
		}
		this.instrucciones[0].setEjecutada(true);
		return this.instrucciones[0].getInstruccion().ejecutarEX();
	}
	
	public Color getColor() {
		switch(tipo) {		
		case Tipos.INTEGER_FU:
			return Color.BLUE;
		case Tipos.FP_ADD_FU:
			return Color.ORANGE;
		case Tipos.FP_MULT_FU:
			return Color.GREEN;
		case Tipos.FP_DIV_FU:
			return Color.RED;
		default:
			return Color.GRAY;
		}
	}
	
	public abstract boolean sePuedeInsertar();
	
	public String toString() {
		String res = "";
		switch(tipo) {
		case Tipos.INTEGER_FU:
			res = lenguaje.getString("U.F.entera");
			break;
		case Tipos.FP_ADD_FU:
			res = lenguaje.getString("U.F.sumaPF");
			break;
		case Tipos.FP_MULT_FU:
			res = lenguaje.getString("U.F.multPF");
			break;
		case Tipos.FP_DIV_FU:
			res = lenguaje.getString("U.F.divPF");
			break;
		}
		return res;
	}
	
}
