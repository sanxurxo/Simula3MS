/**
 *Registros.java
 *Clase abstracta recoge las caracteristicas comunes a todos los registros
 **/
package ensamblador.registros;

import java.util.Observable;
import java.util.Vector;

import ensamblador.datos.Palabra;

public abstract class Registro extends Observable{
    protected Palabra valor=new Palabra("00000000", true);
    protected int numReferencias;
    protected Vector valoresSegm=new Vector();
    protected Vector<String> qi=new Vector<String>();
    protected long cicloModificado=0;
  
      /**
       *Metodo que avisa a los observadores de alguna modificacion
       *@param sin parametros
       *@return void
       **/
    public void visualizaCambios()
    {
    	if(valoresSegm.size()>0){
    		valoresSegm.remove(0);
    	}
    	
    	setChanged();
        notifyObservers(this);
    }
    
    /**
     *Metodo que devuelve el valor en hexadecimal de la palabra almacenada en el registro
     *@param sin parametro
     *@return String valor
     **/
    public String valorHex()
    {
        return valor.getHex();
    }
     /**
     *Metodo que devuelve el valor en decimal de la palabra almacenada en el registro
     *@param sin parametro
     *@return long valor
     **/
    public long valorDec()
    {
        return valor.getDec();
    }
    
     /**
     *Metodo que devuelve el valor en decimal de la palabra almacenada en el registro de pto flotante
     *@param sin parametro
     *@return double valor
     **/
    public double valorFrac()
    {
        return valor.getFrac();
    }
    
    /**
     *Metodo que inicializa el valor del registro
     *@param sin parametros
     *@return void
     **/
    public void inicializar()
    {
    	this.cicloModificado=0;
    	this.qi=new Vector<String>();
        this.valor=new Palabra("00000000", true); 
        this.valoresSegm=new Vector();
        this.valoresSegm.add(new Palabra("00000000", true));
        this.numReferencias=0;
        setChanged();
        notifyObservers(this);        
    }
    
    public Palabra getPalabraSeg()
    {        
    	Palabra v =new Palabra(0);
    	if(this.valoresSegm.size()>0){
    		v= (Palabra)this.valoresSegm.elementAt(0);
    	}
        return v;
    }

public void borrarPalabraSeg() {
    	if(valoresSegm.size()>0) {
    		valoresSegm.remove(0);
    	}
    }

//    metodo que actualiza el valor del reg despues de un riesgo WAW.
//    Al no ser el valor del registro valido se copia el valor de valoresSegm,
//    ya que contiene los valores anteriores
    public void actualizarWAW() {
    
    	this.valor = (Palabra)this.valoresSegm.elementAt(1);
    }
    
    
    /**
     *Metodo que modifica el valor del registro
     *@param long entero
     *@return void
     **/
    public void modificar(long entero)
    {
        this.valor=new Palabra(entero);
        this.valoresSegm.add(new Palabra(entero));
        setChanged();
    }
    
     /**
     *Metodo que modifica el valor del registro
     *@param double numero
     *@return void
     **/
    public void modificar(double numero)
    {
        this.valor=new Palabra(numero);  
        this.valoresSegm.add(new Palabra(numero));
        setChanged();
    }
    
    /**
     *Metodo que modifica el valor del registro
     *@param String hexadecimal
     *@return void
     **/
    public void modificar(String hex)
    {
        this.valor=new Palabra(hex, true);
        this.valoresSegm.add(new Palabra(hex, true));    
        setChanged();
    }
    
    /**
     *Metodo que incrementa el valor del registro
     *@param long decimal
     *@return void
     **/
    public void incrementar(long decimal)
    {
        this.valor=valor.sumar(decimal);
        this.valoresSegm.add(valor);
        setChanged();
    }
    
    /**
     *Metodo que sustituye la palabra almacenada en el registro por la que se le pasa
     *@param Palabra palabra
     *@return void
     **/
    public void setPalabra(Palabra palabra)
    {
        this.valor=palabra;
        this.valoresSegm.add(palabra);
        
        setChanged();
    }
    /**
     *Metodo que devuelve la palabra almacenada en el registro.
     *@param sin parametros
     *@return Palabra palabra
     **/
    public Palabra getPalabra()
    {
        return this.valor;
    }

      /**
     *Metodo que devuelve el bit de signo del numero almacenado en el registro
     *@param sin parametros
     *@return int valor del bit de signo
     **/
    public int getBit(){
        int num;
        String str;
        Integer in;
        str=this.valor.getFloat();
        str=str.substring(0,1);
        num=Integer.valueOf(str).intValue();
        return num;
    }
    
    /**
     *Metodo que devuelve la palabra que habia en ese registro pero con el nuevo bit de signo
     *@param int bit de signo de la nueva palabra
     *@return Palabra con el nuevo bit ya modificado
     **/
    public Palabra setBit(int i){
        Palabra pal=new Palabra("pal");
        String s,str;
        Integer in;
        str=this.valor.getFloat();
        in=new Integer(i);
        s=in.toString();
        str=s+str.substring(1,str.length());
        pal=new Palabra(pal.float_a_dec(str));
        return pal;
    }
    
    /**
     *Metodo que devuelve el numero del registro que sigue al actual
     *@param sin parametros
     *@return int numero del siguiente registro
     **/
    public int getSiguiente()
    {
        int num=this.numReg();
        num++;
        return num;
    }
    /**
     *Metodo que incrementa el numero de referencias a un registro
     *@param sin parametros
     *@return void
     **/
      public void ocupar()
    {
        numReferencias++;
    }
        
    /**
     *Metodo que decrementa el numero de referencias a un registro
     *@param sin parametros
     *@return void
     **/      
    public void liberar()
    {
        numReferencias--;
    }
    
    /**
     *Metodo que libera al registro si no lo estan referenciando
     *@param sin parametros
     *@return boolean libre
     ***/
    public boolean estaLibre()
    {
        if (numReferencias==0)
            return true;
        return false;
    }
    
    /**
     *Mtodo que indica que numero esra asociado al registro
     *@param sin parametros
     *@return int numReg     
     **/
    public abstract int numReg();
    public abstract char letraReg();
    public abstract String toString();

	
    /**
     *Mtodo que indica en el algoritmo de Tomasulo si este
     *registro esta siendo modificado por alguna estacion de reserva
     *o algun buffer
     *@param sin parametros
     *@return int numEstacion buffer que lo modifica (0 = alguna lo ha cogido)     
     **/
    public String getQi() {
    	if(qi.size()>0){
    	
    		return qi.elementAt(qi.size()-1);
    	}
    	return new String();
	}
    
    public boolean qiVacio(){
    	if(qi.size()==0){
    		return true;
    	}
    	return false;
    }

	public void setQi(String qi) {
		if(!qi.equals(new String())){
			if(this.qi.size()>0){
				this.qi.remove(this.qi.size()-1);
			}
			this.qi.add(this.qi.size(), qi);
		}
	}
	
	public void removeQi(String qi){
		this.qi.remove(qi);
	}

	public long getCicloModificado() {
		return cicloModificado;
	}

	public void setCicloModificado(long cicloModificado) {
		this.cicloModificado = cicloModificado;
	}
    
	public void notificar(){
		this.setChanged();
		this.notifyObservers();		
		
	}
    
}
