
package ensamblador.datos;

import java.util.Vector;

/**EntradaSalida.java
 *Subclase de SegDatos que implementa el funcionamiento de la memoria
 **/
public class EntradaSalida extends SegDatos {
    
    private static EntradaSalida es=null;
    private long inicio=INICIO_ES.getDec();
    private long fin=FIN_ES.getDec();
    private Palabra entSal[]=null;
    private int receiverControl=(int)(Tipos.ReceiverControl.getDec()-inicio);
    private int receiverData=(int)(Tipos.ReceiverData.getDec()-inicio)/4;
    private int transmitterControl=(int)(Tipos.TransmitterControl.getDec()-inicio)/4;
    private int transmitterData=(int)(Tipos.TransmitterData.getDec()-inicio)/4; 
    
    private String monitor=new String();
    private boolean monitBol=false;

    /*Los valores de estas posiciones de memoria se almacenan en el array entSal.
     * En el aparecen desde la posicion 0 a la 3, las 4 posiciones de memoria, 
     * ordenadas por su direccion*/
    
/**Constructor de Memoria
 *@param Sin parametros
 **/
    private EntradaSalida() {
    	entSal=new Palabra[4];  
    	for (int i=0;i<=(int)(fin-inicio)/4;i++){
    		if(i!=Tipos.TRANSMITER_CONTROL){    		    		
    			entSal[i]=new Palabra("00000000", true);
    		}else{
    			entSal[i]=new Palabra("00000001", true);
    		}
    	}
    	
    }
    
/**Metodo estatico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return EntradaSalida entradaSalida
 **/  
    public static EntradaSalida getEntradaSalida()
    {
    	if(es==null){
    		es=new EntradaSalida();
    	}
    	return es;        
    }
    

    public Palabra getValor(long posicion){
    	Palabra p;
    	if(dentroRango(posicion)){
    		int direc=(int)(posicion-this.inicio)/4;
    		p=entSal[direc];
    		iniciarEntrada(posicion);
    		return p;
    	}
    	return null;
    }

    public void setValor(long posicion, Palabra palabra){
    	if(dentroRango(posicion)){
    		int direc=(int)(posicion-this.inicio)/4;
    		
    		entSal[direc]= palabra;    		
    		if(direc==3){    	
    			this.setMonitor(palabra.getAscii().substring(0, 1));/*Es necesario el substring pq 
    			el getAscii de palabra anhade caracteres raros*/
    		}
    	}
    	    	
    	this.setChanged();
    	notifyObservers(this);
    }
    
    public Palabra getReceiverControl(){
    	 return entSal[receiverControl];
    }
    
    public Palabra getReceiverData(){
    iniciarEntrada(this.receiverData);
   	 return entSal[receiverData];
    }
    
    public Palabra getTransmitterControl(){
   	 return entSal[transmitterControl];
    }
    public Palabra getTransmitterData(){
   	 return entSal[transmitterData];
    }
    
    
    public void setReceiverControl(Palabra palabra){   	 	
    	entSal[receiverControl]= palabra;
    	this.setChanged();
    	notifyObservers(this);
    }
   
   public void setReceiverData(Palabra palabra){
	   entSal[receiverData]= palabra;
	   this.setChanged();
   		notifyObservers(this);
   }
   
   public void setTransmitterControl(Palabra palabra){
	   
	   entSal[transmitterControl]= palabra;
	  
	   this.setChanged();
   	notifyObservers(this);
   }
   public void setTransmitterData(Palabra palabra){
	   entSal[transmitterData]= palabra;
	 //  this.setMonitor(palabra.getHex()); 
	   this.setChanged();
   	notifyObservers(this);
   }
   
    
    public boolean dentroRango(long posicion){
    	
    	Palabra pos=new Palabra(posicion);
    	if((pos.getHex().compareTo(INICIO_ES.getHex())>=0) && (pos.getHex().compareTo(FIN_ES.getHex())<=0)){
    	//if((posicion>=this.inicio)&&(posicion<=this.fin)){
    		return true;
    	}
    	return false;
    }

	@Override
	public Palabra getByte(long direccion, int desp) {
		
		long direcFinal=direccion+desp;
		Palabra pala=getValor(direcFinal);
		if(pala!=null){
	       String palabra=pala.getHex();
	       desp=((int)(direccion+desp))%4;
		           palabra=palabra.substring(2,palabra.length());
	        palabra=palabra.substring(palabra.length()-2-2*(desp%4), palabra.length()-2*(desp%4));    
	        Palabra pal=new Palabra(palabra,true);
	        iniciarEntrada(direccion);
	        return pal;
		}
		return null;
	}

	@Override
	public void setByte(long direccion, int desp, Palabra palabra) {
			int despPal=desp/4;
			int elByte=desp%4;
			long direcFinal=direccion+desp;
			String valor=palabra.getHex();
			Palabra dest;
			String valorDest;
			String resul=new String();
			Palabra prueba=new Palabra(direcFinal);
		
			if((dest=this.getValor(direcFinal))!=null){
					int posInic=(4-elByte)*2;
					int posFin=posInic+2;
					
					valorDest=dest.getHex();
					resul=valorDest.substring(0, posInic);
		
					resul+=palabra.getHex().substring(posInic, posFin);
		
					resul+=valorDest.substring(posFin, valorDest.length());
		
					this.setValor(direcFinal, new Palabra(resul, true));
			        this.setChanged();
			    	notifyObservers(this);
			}else{
				System.out.println("NO!! ACCESO NO VALIDO");
	//acceso a mem no valido. Si esta bien sb esto no puede pasar.		
			}
		
//		
//		String valor_final=new String();   
//		
//		
//			System.out.println("en setByte::direccion="+(new Palabra(direccion)).getHex()+" desp="+desp+" palabra="+palabra.getHex());
//	        Palabra valor_mem=(Palabra)this.getValor(direccion);                
//	        String valor_fin=valor_mem.getHex().substring(2,valor_mem.getHex().length());
//	        String pal=palabra.getHex().substring(2,valor_mem.getHex().length());
//	        valor_final=valor_fin.substring(0,valor_fin.length()-2-2*desp);
//	        valor_final+=pal.substring(pal.length()-2, pal.length());
//	        valor_final+=valor_fin.substring(valor_fin.length()-2*desp,valor_fin.length());
//	        this.setValor(direccion, new Palabra(valor_final,true));
//	        

		
	}

	@Override
	public Vector visualizar() {
		Vector<Palabra> vector=new Vector<Palabra>();
		for(int i=0; i<entSal.length;i++){
			vector.addElement(entSal[i]);
		}
		return vector;
	}

	public String getMonitor(){
	if(monitBol==true){
			monitBol=false;
			return monitor;
		}else{
			return new String();
		}		
	}
	
	public void setMonitor(String s){
		monitor=s;
		monitBol=true;
	}
	

	private void iniciarEntrada(long posicion){
		
		if(((posicion-this.inicio)/4)==this.receiverData){
			Palabra p=entSal[0];
			p.setBit(0, "0");
			entSal[0]=p;
			entSal[1]=new Palabra("00000000", true);
		}
		this.setChanged();
    	notifyObservers(this);
	}
	@Override
	public void inicializar() {
		entSal=new Palabra[4];
		for (int i=0;i<=(int)(fin-inicio)/4;i++){
    		if(i!=Tipos.TRANSMITER_CONTROL){    		    		
    			entSal[i]=new Palabra("00000000", true);
    		}else{
    			entSal[i]=new Palabra("00000001", true);
    		}
    	}
		this.setChanged();
		notifyObservers(this);
	}

	@Override
	public void setPalabra(long direccion, int desp, Palabra palabra) {
		this.setValor(direccion+desp, palabra);
		this.setChanged();
    	notifyObservers(this);
	}
	
	public void setInterrupcion(){
		Palabra p=this.getReceiverControl();
		p.setBit(1, "1");
		this.setReceiverControl(p);
//		p=this.getTransmitterControl();
//		p.setBit(1, "1");
//		this.setTransmitterControl(p);
		
		this.setChanged();
		this.notifyObservers();
	}
	
	
}
