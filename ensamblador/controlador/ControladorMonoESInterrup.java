package ensamblador.controlador;

import java.util.Hashtable;
import java.util.Observer;
import java.util.Vector;

import ensamblador.datos.EntradaSalida;
import ensamblador.datos.InfoThread;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.procesadorMonociclo.ProcesadorMonocicloESInterrup;
import ensamblador.procesadorMonociclo.RutinaTeclado;
import ensamblador.registros.EPC;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.EntradaException;
import ensamblador.util.excepcion.VisualizarEjecucionException;
import ensamblador.vista.Vista;

public class ControladorMonoESInterrup extends ControladorMonocicloES implements Observer{

//	private EntradaSalida entradaSalida;
//	protected int tipoES=-1;
//	protected String cadenaES;
//	protected InfoThread infoThread;
//	protected Hashtable<Integer, Palabra> entradas;
//	protected Vector<Integer> ciclosEntradas;
	public ControladorMonoESInterrup(){
		super();
		entradaSalida=EntradaSalida.getEntradaSalida();
		entradaSalida.setInterrupcion();
		cadenaES=new String();
		
		procesador=new ProcesadorMonocicloESInterrup(instrucc);
		
		infoThread=InfoThread.getInstancia();
		
		entradas=new Hashtable<Integer, Palabra>();
		
		ciclosEntradas=new Vector<Integer>();
		
				
	}
	
	public void inicializar(){
		super.inicializar();
		entradaSalida.setInterrupcion();
	}
	
	public void actualizarEntrada(Palabra dato) throws EntradaException{
		super.actualizarEntrada(dato);
	//	anhadirEntrada(dato);
	}

	public void anhadirObservador(Vista observador){
//		super.anhadirObservador(observador);
//		entradaSalida.addObserver(observador);
//		infoThread.addObserver(observador);
		super.anhadirObservador(observador);
		infoThread.addObserver(this);
	}
	
	public Palabra getPalabraEPC(){
		return EPC.getRegistro().getPalabra();
	}
	
	public void anhadirInterrupcionTeclado(){	
		
			
		((ProcesadorMonocicloESInterrup)procesador).parar();
		
		((ProcesadorMonocicloESInterrup)procesador).anhadirRutinaInterrupcion();
		
		
		
	
	}
	
	
//	public void actualizarEntrada(Palabra dato) throws EntradaException{
//		entradaSalida.setReceiverControl(new Palabra("0x00000001",true));
//		entradaSalida.setReceiverData(dato);
//		
//		if(this.isEsperandoEntrada()){
//			/*Enter*/
//			if(dato.getHex().equals("0x0000000a")){ 				
//				this.readDato(cadenaES, this.getEsperandoEntrada());
//				this.setEsperandoEntrada(-1);
//				this.cadenaES=new String();
//				throw new EnterException();
//			}else if(dato.getHex().equals("0x00000028")){/*DEL*/
//				
//				if(cadenaES.length()>0){
//					cadenaES=new String(cadenaES.substring(0, cadenaES.length()-1));
//					System.out.println(cadenaES);
//					throw new DelCaracterException();
//				}
//								
//			} else{				
//				cadenaES=cadenaES+dato.getAscii().substring(0, 1);				
//				throw new AnhadirCaracterException();
//				
//			}			
//		}
//		this.anhadirEntrada(dato);
//	
//		
//	}

//	public void inicializar(){
//		super.inicializar();
//		entradaSalida.inicializar();		
//		tipoES=-1;
//	}
//	
//    public void actualizarSalida(char c){
//    	int ult=(int)c;        
//        Palabra p=new Palabra(new Long(ult));        
//    
//        this.entradaSalida.setTransmitterData(p);                    
//    }
//    
//    public void readDato(String dato, int tipo){
//    	super.readDato(dato, tipo);
//    	if(dato.length()>0){
//    		switch(tipo){
//    		case Tipos.STRING:
//    			try{
//    				actualizarEntrada(new Palabra(dato.substring(dato.length()-1)));
//    			}catch(EntradaException e){}
//    			break;
//    		case Tipos.INTEGER:
//    			int i=0;
//    			try{
//    				i=new Integer(dato.substring(dato.length()-1)).intValue();
//    				try{
//    					actualizarEntrada(new Palabra(i));
//    				}catch(EntradaException e){}
//    			}catch(NumberFormatException e){}						
//    			break;
//    		case Tipos.FLOAT:
//    			try{
//    				long l= new Long(dato.substring(dato.length()-1)).longValue();
//    				try{
//    					actualizarEntrada(new Palabra(l));
//    				}catch(EntradaException e){}
//    			}catch(NumberFormatException e){}
//    			break;
//    		case Tipos.DOUBLE:
//    			try{
//    				double d=new Double(dato.substring(dato.length()-1)).doubleValue();
//    				try{
//    					actualizarEntrada(new Palabra(d));
//    				}catch(EntradaException e){}
//    			}catch(NumberFormatException e){}
//    			break;
//    		}
//    	}
//    }
//    
    
    @Override
	public boolean ejecutarCicloAnterior(Palabra breakpoint) throws EjecucionException {
    	
    	long tope=this.getCiclos();
		String pcAnterior=new String();
		setPosicionDato(0);
		procesador.inicializar();
		entradaSalida.inicializar();
		this.inicializar();
		Palabra pcActual=getPalabraPC();
		
	//	Pc.getRegistro().setPalabra(new Palabra("0x40000000",true));
		
//		if(tope-1>0){
//		if(entradas.containsKey(new Integer(this.getCiclos()))){
//			entradaSalida.inicializar();
//			this.anhadirInterrupcionTeclado();
//			try {
//				this.actualizarEntrada(entradas.get(new Integer(this.getCiclos())));
//			} catch (EntradaException e) {					
//				//e.printStackTrace();
//			}
//		}
//		}
		while(this.getCiclos()<(tope-1)){
			pcAnterior=this.getPalabraPC().getHex();
			
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
			if(entradas.containsKey(new Integer(this.getCiclos()))){
				pcAnterior=this.getPalabraPC().getHex();
				entradaSalida.inicializar();
				this.anhadirInterrupcionTeclado();
				try {
					this.actualizarEntrada(entradas.get(new Integer(this.getCiclos())));
				} catch (EntradaException e) {					
					//e.printStackTrace();
				}
			}
			
		}
		vaciarValoresFuturos(getPosicionDato());
		this.vaciarEntradasFuturas(new Integer(this.getCiclos()));
		
		if(isEjecutandoRutina()){
			throw new VisualizarEjecucionException(18, pcAnterior);
		}else{
			throw new VisualizarEjecucionException(19, pcAnterior);
		}

	}
    
    
    
    public boolean isEjecutandoRutina(){
    	if(this.getPalabraPC().getHex().compareTo((new Palabra("0x80000080",true)).getHex())>0){
    		return true;
    	}
    	return false;
    }
    
    
    
    
    
//    public void setEsperandoEntrada(int tipo){
//    	tipoES=tipo;
//    }
//    
//    public boolean isEsperandoEntrada(){
//    	if((tipoES==Tipos.STRING)||(tipoES==Tipos.INTEGER)||(tipoES==Tipos.FLOAT)||(tipoES==Tipos.DOUBLE)){
//    		return true;
//    	}
//    	return false;
//    }
//    
//    public int getEsperandoEntrada(){
//    	return tipoES;
//    }

//    public void pararEjecucion(){
//
//
//    	((ProcesadorMonocicloESInterrup)procesador).parar();
//    }
//    
//	public void update(Observable o, Object arg) {
//		if(o instanceof InfoThread){
//			procesador.setCiclos(((InfoThread)o).getCiclos());
//		}
//		
//	}
	
	public String visualizarRutina(){
		return RutinaTeclado.getInstancia().toString();
	}
	
	public int getTamanhoRutina(){
		return RutinaTeclado.getInstancia().getTamanho();
	}
	

	
//	//En este metodo se almacena en entradas y en ciclosEntrada el ciclo en el que tiene lugar la interrupcion  
//	public void anhadirEntrada(Palabra dato){
//		Integer ciclo=new Integer(((ProcesadorMonocicloESInterrup)procesador).getCiclos());
//		if(!entradas.containsKey(ciclo)){ //Si ya esta es que estamos haciendo ciclo anterior y no hace falta volver a guardarlo
//			while(!((ProcesadorMonocicloESInterrup)procesador).isThreadParado()){
//				/*es necesario asegurar que se ha detenido el thread antes de
//				 * mirar el ciclo en el que se ha producido la interrupcion
//				 * para almacenarlo para cicloAnterior*/
//
//			} 
//			entradas.put(ciclo, dato);	
//			ciclosEntradas.add(ciclo);
//		}
//	
//	}
//	
//	public void vaciarEntradasFuturas(Integer ciclo){
//		Vector<Integer> v=new Vector<Integer>();
//		for(int i=0;i<ciclosEntradas.size();i++){
//			if(ciclosEntradas.elementAt(i).intValue()<=ciclo.intValue()){
//				v.add(ciclosEntradas.elementAt(i));
//			}else{
//				entradas.remove(ciclosEntradas.elementAt(i));
//			}
//		}
//		ciclosEntradas=v;
//	}
//	
}
