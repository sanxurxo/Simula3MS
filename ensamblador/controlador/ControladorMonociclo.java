package ensamblador.controlador;

import java.util.Vector;

import ensamblador.ObservaRegistros;
import ensamblador.datos.Codigo;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;

import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Syscall;
import ensamblador.procesadorMonociclo.ProcesadorMonociclo;
import ensamblador.registros.Pc;
import ensamblador.util.excepcion.EjecucionException;

public class ControladorMonociclo extends Controlador {


	private int posicionDato=0;
	protected ProcesadorMonociclo procesador;

	
	public ControladorMonociclo() {
		super();		
		posicionDato=0;
		datos=new Vector<String>();
		instrucc=Codigo.getInstacia();
		procesador=new ProcesadorMonociclo(instrucc);
		regPC=Pc.getRegistro();
		memoria=Memoria.getMemoria();
		pila=Pila.getPila();
		observaReg=new ObservaRegistros();
	}

	
	public int getCiclos(){
		return procesador.getCiclos();
	}
	
	@Override
	public int ejecutar(int ciclo) {
		
		return 0;
	}

	@Override
	public boolean ejecutarCiclo(Palabra breakpoint) throws EjecucionException {
		try{
			
    		procesador.ejecutarCiclo(getDecimalPC(), breakpoint);
    	}catch(EjecucionException e){
    		throw e;
		}
		return true;
		
	}
	public String getImagenActual(){
		
		return procesador.getImagenActual();
	}

	@Override
	public boolean ejecutarCicloAnterior(Palabra breakpoint) throws EjecucionException {
		long tope=this.getCiclos();
		setPosicionDato(0);
		
		this.inicializar();
		Palabra pcActual=getPalabraPC();
		
		procesador.inicializar();
		

		while(this.getCiclos()<(tope-1)){

			try{
				ejecutarCiclo(breakpoint);
			}catch(EjecucionException e){
				switch(e.getTipo())
	            {
	                case 5:/*Introduce entero*/
	                	registrandoDato(getDato(getPosicionDato()), Tipos.INTEGER);
	                    setPosicionDato(getPosicionDato()+1);	                    
	                    break;
	                case 6:/*Introduce string*/
	                	registrandoDato(getDato(getPosicionDato()), Tipos.STRING);
	                    setPosicionDato(getPosicionDato()+1);
	                    break;
	                case 12:/*Introduce un float*/
	                	registrandoDato(getDato(getPosicionDato()), Tipos.FLOAT);
	                    setPosicionDato(getPosicionDato()+1);
	                        break;
	                       
	                case 13:/*Introduce un double*/
	                	registrandoDato(getDato(getPosicionDato()), Tipos.DOUBLE);
	                    setPosicionDato(getPosicionDato()+1);
	                        break;
	            }
			}
			
		}
		vaciarValoresFuturos(getPosicionDato());
		return true;
	}

	@Override
	public int ejecutar(Palabra breakpoint) throws EjecucionException {
		try{
    		return procesador.ejecutar(Pc.getRegistro(), breakpoint);
    	}catch(EjecucionException e){
    		throw e;
    	}    	
	}

	public int getPosicionDato() {
		return posicionDato;
	}

	public void setPosicionDato(int posicionDato) {
		this.posicionDato = posicionDato;
	}

	public String getDato(int posicion){
		return datos.elementAt(posicion);
	}
	
	public void vaciarValoresFuturos(int posicion){
		for(int i=posicion; i<datos.size();i++){
			datos.remove(i);
		}
	}
//	protected void registrandoDato(String dato, int tipo){
//		switch(tipo){
//		case Tipos.STRING:
//			Syscall.readString(dato);
//			break;
//		case Tipos.INTEGER:
//			Syscall.readInt(dato);
//			break;
//		case Tipos.FLOAT:
//			 Syscall.readFloat(dato);
//			break;
//		case Tipos.DOUBLE:
//			Syscall.readDouble(dato);
//			break;
//		}
//	}
	
}
