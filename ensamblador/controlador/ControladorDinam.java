package ensamblador.controlador;

import java.util.Hashtable;
import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ensamblador.ObservaRegistros;
import ensamblador.datos.Codigo;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Tipos;
import ensamblador.estado.EstadoInstruccion;
import ensamblador.estado.Riesgos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Syscall;
import ensamblador.planificacionDinamica.Procesador;
import ensamblador.registros.A0;
import ensamblador.registros.A1;
import ensamblador.registros.A2;
import ensamblador.registros.A3;
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
import ensamblador.vista.VistaAlgoritmos;

public abstract class ControladorDinam extends Controlador{

	
	protected Codigo instrucc;
	protected Pc regPC;
	protected Memoria memoria;
	protected Pila pila;
	protected ObservaRegistros observaReg;
	protected Procesador procesador;
	private Vector<String> datos;
	private int posicionDato=0;
	
	public ControladorDinam() {			
		super();
		posicionDato=0;
		datos=new Vector<String>();
		instrucc=Codigo.getInstacia();
		regPC=Pc.getRegistro();
		memoria=Memoria.getMemoria();
		pila=Pila.getPila();
		observaReg=new ObservaRegistros();
	
	}

	
	//
	public boolean estaEjecutando(String pc){
		return procesador.estaEjecutando(pc);
	}
	//
	public Riesgos getRiesgos(){
		return procesador.getRiesgos();
	}
	
	//***
	public void anhadirObservador(VistaAlgoritmos observador){
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
        Ra.getRegistro().inicializar();
        Pc.getRegistro().inicializar();
        Hi.getRegistro().inicializar();
        Lo.getRegistro().inicializar();
        Status.getRegistro().inicializar();

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
	
	//public TableModel updateEstadoRegistros(Hashtable tabla, TableModel model){
	public Vector<JTable> updateEstadoRegistros(Hashtable tabla, Vector<JTable> tablas){	
		String cadena=new String();
		String f="f";
		int numero=0;
		TableModel model;
		for(int j=0; j<tablas.size();j++){
			model=tablas.elementAt(j).getModel();
			int tamanho=tablas.elementAt(j).getColumnCount();
			model.setValueAt("Qi", 0,0);
			for(int i=1;i<tamanho;i++){
				numero=j*14+(i-1+j)*2;
				cadena=f + numero;		

				if(tabla.containsKey(cadena)){

					model.setValueAt(tabla.get(cadena), 0,i);

				}else{
					model.setValueAt(new String(), 0,i);
				}				
				
			}		
			tablas.elementAt(j).setModel(model);
		}
		return tablas;
	}
	
	
	public abstract TableModel updateFUS(Vector fus, JTable tabla);
	
	public void updateInstrucciones(Vector<EstadoInstruccion> instrucciones, JTable tabla){
		
		EstadoInstruccion estado;
		DefaultTableModel model=(DefaultTableModel)tabla.getModel();
		
		while(model.getRowCount()<instrucciones.size()){

			model.addRow(new Object[0]);
		}
		int i=0;
		for(i=0;i<instrucciones.size();i++){
			estado=instrucciones.elementAt(i);

			model.setValueAt(estado.getInstruccion().toString(), i, 0);
			int j;
			for(j=0;j<=estado.getStatus();j++){
				model.setValueAt(Boolean.TRUE,i, j+1);
			}
			for(int k=j+1; k<model.getColumnCount();k++){
				model.setValueAt(Boolean.FALSE,i, k);
			}
			
		}
		if(i<model.getRowCount()){
			for(int j=i;j<model.getRowCount(); j++){
				model.setValueAt(new String(),j,0);
				for(int k=1;k<model.getColumnCount();k++){
					model.setValueAt(Boolean.FALSE,j, k);
				}
			}
		}	
	}

	
	
	protected TableModel aumentarModel(TableModel model, int filas){
		int columnas=model.getColumnCount();
		Object[][] tabla=new Object[filas][columnas];
		String [] cabecera=new String[columnas];
		
		for(int i=0;i<columnas;i++){
			cabecera[i]=model.getColumnName(i);
		
		}
		
		TableModel modelo=new DefaultTableModel(tabla, cabecera);	
		return modelo;
	
	}
	
	public int ejecutar(Palabra breakpoint) throws EjecucionException{
		
		try{
    		return procesador.ejecutar(Pc.getRegistro(), breakpoint);
    	}catch(EjecucionException e){
    		throw e;
    	}    	
	}
	
	public int getCiclos(){
		return procesador.getCiclos();
	}
	
	
//	public void readDato(String dato, int tipo){
//		datos.addElement(dato);
//		registrandoDato(dato, tipo);		
//	}
//	
//	private void registrandoDato(String dato, int tipo){
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
	
	public String getDato(int posicion){
		return datos.elementAt(posicion);
	}
	public void vaciarValoresFuturos(int posicion){
		for(int i=posicion; i<datos.size();i++){
			datos.remove(i);
		}
	}
		
	public boolean ejecutarCicloAnterior(Palabra breakpoint) throws EjecucionException{
		

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
	public abstract boolean ejecutarCiclo(Palabra breakpoint) throws EjecucionException;

	public int getPosicionDato() {
		return posicionDato;
	}

	public void setPosicionDato(int posicionDato) {
		this.posicionDato = posicionDato;
	}
	
	@Override
	//Este metodo no es necesario para la planificacion dinamica pero
	//se mantiene porque el MVC no esta bien hecho en segmentado ya que la vista controla el 
	//numero de ciclo
	public int ejecutar(int ciclo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
