package ensamblador.controlador;

import java.util.Hashtable;
import java.util.Observable;
import java.util.Vector;

import ensamblador.datos.EntradaSalida;
import ensamblador.datos.InfoThread;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.procesadorMonociclo.ProcesadorMonoES;
import ensamblador.util.excepcion.AnhadirCaracterException;
import ensamblador.util.excepcion.DelCaracterException;
import ensamblador.util.excepcion.EnterException;
import ensamblador.util.excepcion.EntradaException;
import ensamblador.vista.Vista;

public abstract class ControladorMonocicloES extends ControladorMonociclo {

	protected EntradaSalida entradaSalida;
	protected int tipoES=-1;
	protected String cadenaES;
	protected InfoThread infoThread;
	protected Hashtable<Integer, Palabra> entradas;
	protected Vector<Integer> ciclosEntradas;
	
	
	public void anhadirObservador(Vista observador){
		
		super.anhadirObservador(observador);
		entradaSalida.addObserver(observador);
		infoThread.addObserver(observador);
//		infoThread.addObserver(this);
	}
	

	public void actualizarEntrada(Palabra dato) throws EntradaException{
		
		
		Palabra p=entradaSalida.getReceiverControl();
		
		p.setBit(0, "1");
		
		
		entradaSalida.setReceiverControl(p);
		entradaSalida.setReceiverData(dato);	
		if(this.isEsperandoEntrada()){
		
			/*Enter*/
			if(dato.getHex().equals("0x0000000a")){ 				
				this.readDato(cadenaES, this.getEsperandoEntrada());
				this.setEsperandoEntrada(-1);
				this.cadenaES=new String();
				throw new EnterException();
			}else if(dato.getHex().equals("0x00000028")){/*DEL*/				
				if(cadenaES.length()>0){
					cadenaES=new String(cadenaES.substring(0, cadenaES.length()-1));
					System.out.println(cadenaES);
					throw new DelCaracterException();
				}
								
			} else{				
				cadenaES=cadenaES+dato.getAscii().substring(0, 1);				
				throw new AnhadirCaracterException();
				
			}			
		}
		this.anhadirEntrada(dato);	
	}
	public void inicializar(){
		super.inicializar();
		entradaSalida.inicializar();	
		
		tipoES=-1;
	}
	
    public void actualizarSalida(char c){
    	int ult=(int)c;        
        Palabra p=new Palabra(new Long(ult));            
        this.entradaSalida.setTransmitterData(p);                    
    }
    
    public int readDato(String dato, int tipo){
    	super.readDato(dato, tipo);
    	if(dato.length()>0){
    		switch(tipo){
    		case Tipos.STRING:
    			try{
    				actualizarEntrada(new Palabra(dato.substring(dato.length()-1)));
    			}catch(EntradaException e){}
    			break;
    		case Tipos.INTEGER:
    			int i=0;
    			try{
    				i=new Integer(dato.substring(dato.length()-1)).intValue();
    				try{
    					actualizarEntrada(new Palabra(i));
    				}catch(EntradaException e){}
    			}catch(NumberFormatException e){}						
    			break;
    		case Tipos.FLOAT:
    			try{
    				long l= new Long(dato.substring(dato.length()-1)).longValue();
    				try{
    					actualizarEntrada(new Palabra(l));
    				}catch(EntradaException e){}
    			}catch(NumberFormatException e){}
    			break;
    		case Tipos.DOUBLE:
    			try{
    				double d=new Double(dato.substring(dato.length()-1)).doubleValue();
    				try{
    					actualizarEntrada(new Palabra(d));
    				}catch(EntradaException e){}
    			}catch(NumberFormatException e){}
    			break;
    		}
    	}
    	return 0;
    }
    
    public void setEsperandoEntrada(int tipo){
    	tipoES=tipo;
    }
    
    public boolean isEsperandoEntrada(){
    	if((tipoES==Tipos.STRING)||(tipoES==Tipos.INTEGER)||(tipoES==Tipos.FLOAT)||(tipoES==Tipos.DOUBLE)){
    		return true;
    	}
    	return false;
    }
    
    public int getEsperandoEntrada(){
    	return tipoES;
    }
    
    public void pararEjecucion(){


    	((ProcesadorMonoES)procesador).parar();
    }
    
	public void update(Observable o, Object arg) {
		if(o instanceof InfoThread){
			procesador.setCiclos(((InfoThread)o).getCiclos());
		}
		
	}
    
//	En este metodo se almacena en entradas y en ciclosEntrada el ciclo en el que tiene lugar la interrupcion  
	public void anhadirEntrada(Palabra dato){
		Integer ciclo=new Integer(((ProcesadorMonoES)procesador).getCiclos());
		if(!entradas.containsKey(ciclo)){ //Si ya esta es que estamos haciendo ciclo anterior y no hace falta volver a guardarlo
			while(!((ProcesadorMonoES)procesador).isThreadParado()){
				/*es necesario asegurar que se ha detenido el thread antes de
				 * mirar el ciclo en el que se ha producido la interrupcion
				 * para almacenarlo para cicloAnterior*/

			} 
			entradas.put(ciclo, dato);	
			ciclosEntradas.add(ciclo);
		}
	
	}
	
	public void vaciarEntradasFuturas(Integer ciclo){
		Vector<Integer> v=new Vector<Integer>();
		for(int i=0;i<ciclosEntradas.size();i++){
			if(ciclosEntradas.elementAt(i).intValue()<ciclo.intValue()){
				v.add(ciclosEntradas.elementAt(i));

			}else{
				entradas.remove(ciclosEntradas.elementAt(i));
			}
		}
		ciclosEntradas=v;
	}
	
    
}
