package ensamblador.controlador;

import java.util.Vector;

import ensamblador.ObservaRegistros;
import ensamblador.datos.Codigo;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Syscall;
import ensamblador.planificacionDinamica.Procesador;
import ensamblador.registros.A0;
import ensamblador.registros.A1;
import ensamblador.registros.A2;
import ensamblador.registros.A3;
import ensamblador.registros.Cause;
import ensamblador.registros.EPC;
import ensamblador.registros.F0;
import ensamblador.registros.F1;
import ensamblador.registros.F10;
import ensamblador.registros.F11;
import ensamblador.registros.F12;
import ensamblador.registros.F13;
import ensamblador.registros.F14;
import ensamblador.registros.F15;
import ensamblador.registros.F16;
import ensamblador.registros.F17;
import ensamblador.registros.F18;
import ensamblador.registros.F19;
import ensamblador.registros.F2;
import ensamblador.registros.F20;
import ensamblador.registros.F21;
import ensamblador.registros.F22;
import ensamblador.registros.F23;
import ensamblador.registros.F24;
import ensamblador.registros.F25;
import ensamblador.registros.F26;
import ensamblador.registros.F27;
import ensamblador.registros.F28;
import ensamblador.registros.F29;
import ensamblador.registros.F3;
import ensamblador.registros.F30;
import ensamblador.registros.F31;
import ensamblador.registros.F4;
import ensamblador.registros.F5;
import ensamblador.registros.F6;
import ensamblador.registros.F7;
import ensamblador.registros.F8;
import ensamblador.registros.F9;
import ensamblador.registros.Hi;
import ensamblador.registros.K0;
import ensamblador.registros.K1;
import ensamblador.registros.Lo;
import ensamblador.registros.Pc;
import ensamblador.registros.Ra;
import ensamblador.registros.S0;
import ensamblador.registros.S1;
import ensamblador.registros.S2;
import ensamblador.registros.S3;
import ensamblador.registros.S4;
import ensamblador.registros.S5;
import ensamblador.registros.S6;
import ensamblador.registros.S7;
import ensamblador.registros.FP;
import ensamblador.registros.T9;
import ensamblador.registros.Status;
import ensamblador.registros.T0;
import ensamblador.registros.T1;
import ensamblador.registros.T2;
import ensamblador.registros.T3;
import ensamblador.registros.T4;
import ensamblador.registros.T5;
import ensamblador.registros.T6;
import ensamblador.registros.T7;
import ensamblador.registros.T8;
import ensamblador.registros.V0;
import ensamblador.registros.V1;
import ensamblador.registros.Zero;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.vista.Vista;

public abstract class Controlador {

	protected Codigo instrucc;
	protected Pc regPC;
	protected Memoria memoria;
	protected Pila pila;
	protected ObservaRegistros observaReg;
	protected Procesador procesador;
	protected Vector<String> datos;
	private int posicionDato=0;
	
	public Controlador() {			
		super();
		posicionDato=0;
		datos=new Vector<String>();
		instrucc=Codigo.getInstacia();
		regPC=Pc.getRegistro();
		memoria=Memoria.getMemoria();
		pila=Pila.getPila();
		observaReg=new ObservaRegistros();
		
		

		
	}
	
	
	public void anhadirObservador(Vista observador){
		memoria.addObserver(observador);
		pila.addObserver(observador);
		observaReg.addObserver(observador);
	}
	
	public Vector visualizarMemoria(){
		return memoria.visualizar();
	}
	
	public Vector visualizarPila(){
		return pila.visualizar();
	}
	public String visualizarCodigo(){
		return instrucc.visualizar();
	}
	
	public int tamanhoCodigo(){
		return instrucc.tamanho();
	}
	public String[] getPc(){
		return instrucc.getPc();
	}
	public long getDecimalPC(){
		return regPC.getPalabra().getDec();
	}
	public String getHexadecimalPC(){
		return regPC.getPalabra().getHex();
	}
	
	public Palabra getPalabraPC(){
		return regPC.getPalabra();
	}
	
	public long getValorBasePC(){
		return regPC.valorBase();
	}
	
	public void inicializar(){
		memoria.inicializando();
        pila.inicializar();
        Zero.getRegistro().inicializar();
        V0.getRegistro().inicializar();
        V1.getRegistro().inicializar();
        A0.getRegistro().inicializar();
        A1.getRegistro().inicializar();
        A2.getRegistro().inicializar();
        A3.getRegistro().inicializar();
        T0.getRegistro().inicializar();
        T1.getRegistro().inicializar();
        T2.getRegistro().inicializar();
        T3.getRegistro().inicializar();
        T4.getRegistro().inicializar();
        T5.getRegistro().inicializar();
        T6.getRegistro().inicializar();
        T7.getRegistro().inicializar();
        T8.getRegistro().inicializar();
        S0.getRegistro().inicializar();
        S1.getRegistro().inicializar();
        S2.getRegistro().inicializar();
        S3.getRegistro().inicializar();
        S4.getRegistro().inicializar();
        S5.getRegistro().inicializar();
        S6.getRegistro().inicializar();
        S7.getRegistro().inicializar();
        FP.getRegistro().inicializar();
        T9.getRegistro().inicializar();
        K0.getRegistro().inicializar();
        K1.getRegistro().inicializar();
        Ra.getRegistro().inicializar();
        Pc.getRegistro().inicializar();
        Hi.getRegistro().inicializar();
        Lo.getRegistro().inicializar();
        Status.getRegistro().inicializar();
        EPC.getRegistro().inicializar();
        Cause.getRegistro().inicializar();
        
        F0.getRegistro().inicializar();
        F1.getRegistro().inicializar();
        F2.getRegistro().inicializar();
        F3.getRegistro().inicializar();
        F4.getRegistro().inicializar();
        F5.getRegistro().inicializar();
        F6.getRegistro().inicializar();
        F7.getRegistro().inicializar();
        F8.getRegistro().inicializar();
        F9.getRegistro().inicializar();
        F10.getRegistro().inicializar();
        F11.getRegistro().inicializar();
        F12.getRegistro().inicializar();
        F13.getRegistro().inicializar();
        F14.getRegistro().inicializar();
        F15.getRegistro().inicializar();
        F16.getRegistro().inicializar();
        F17.getRegistro().inicializar();
        F18.getRegistro().inicializar();
        F19.getRegistro().inicializar();
        F20.getRegistro().inicializar();
        F21.getRegistro().inicializar();
        F22.getRegistro().inicializar();
        F23.getRegistro().inicializar();
        F24.getRegistro().inicializar();
        F25.getRegistro().inicializar();
        F26.getRegistro().inicializar();
        F27.getRegistro().inicializar();
        F28.getRegistro().inicializar();
        F29.getRegistro().inicializar();
        F30.getRegistro().inicializar();
        F31.getRegistro().inicializar();
	}
	
	public Instruccion getInstruccionActual(){
		return instrucc.obtener((int)regPC.getPalabra().getDec());
	}
	public Instruccion getInstruccion(int pc){
		return instrucc.obtener(pc);
	}
	
	public int readDato(String dato, int tipo){
		datos.addElement(dato);
		return registrandoDato(dato, tipo);		
	}
	
	protected int registrandoDato (String dato, int tipo){
		
		switch(tipo){
		case Tipos.STRING:
			return Syscall.readString(dato);
			
		case Tipos.INTEGER:
			return Syscall.readInt(dato);
			
		case Tipos.FLOAT:
			return Syscall.readFloat(dato);
			
		case Tipos.DOUBLE:
			return Syscall.readDouble(dato);
			
		}
		return -1;
	}
	
	public abstract int ejecutar(int ciclo) ;
	public abstract boolean ejecutarCiclo(Palabra breakpoint) throws EjecucionException;
	public abstract boolean ejecutarCicloAnterior(Palabra breakpoint) throws EjecucionException;
	public abstract int ejecutar(Palabra breakpoint) throws EjecucionException;
}
