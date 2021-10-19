package ensamblador.registros;

import java.util.Vector;

import ensamblador.datos.Palabra;

public abstract class Registro32 extends Registro {

	protected Palabra palabra1=new Palabra("00000000", true);
	protected Palabra palabra2=new Palabra("00000000", true);
	protected Palabra palabra3=new Palabra("00000000", true);


	public void inicializar(){
		this.palabra1=new Palabra("00000000", true);
		this.palabra2=new Palabra("00000000", true);
		this.palabra3=new Palabra("00000000", true);
		super.inicializar();
	}





	/**
	 *Metodo que devuelve el valor en hexadecimal de la palabra almacenada en el registro
	 *@param numero de la palabra (0-3)
	 *@return String valor
	 **/
	public String valorHex(int  opcion){
		switch(opcion){
		case 0:
			return valor.getHex();
		case 1:
			return palabra1.getHex();
		case 2:
			return palabra2.getHex();
		case 3:
			return palabra3.getHex();
		}
		return null;	        
	}
	/**
	 *Metodo que devuelve el valor en decimal de la palabra almacenada en el registro
	 *@param numero de la palabra (0-3)
	 *@return long valor
	 **/
	public long valorDec(int opcion){
		switch(opcion){
		case 0:
			return valor.getDec();
		case 1:
			return palabra1.getDec();
		case 2:
			return palabra2.getDec();
		case 3:
			return palabra3.getDec();
		}
		return -1;
	}

	/**
	 *Metodo que devuelve el valor en decimal de la palabra almacenada en el registro de pto flotante
	 *@param numero de la palabra (0-3)
	 *@return double valor
	 **/
	public double valorFrac(int opcion){
		switch(opcion){
		case 0:
			return valor.getFrac();
		case 1:
			return palabra1.getFrac();
		case 2:
			return palabra2.getFrac();
		case 3:
			return palabra3.getFrac();
		}
		return -1;

	}










	/**
	 *Metodo que modifica el valor del registro
	 *@param long entero, numero de palabra (0-3)
	 *@return void
	 **/
	public void modificar(long entero, int numPalabra){
		switch(numPalabra){
		case 0:
			this.valor=new Palabra(entero);
		case 1:
			this.palabra1=new Palabra(entero);
		case 2:
			this.palabra2=new Palabra(entero);
		case 3:
			this.palabra3=new Palabra(entero);
		}	    	
//		this.valoresSegm.add(new Palabra(entero));
		setChanged();
	}

	/**
	 *Metodo que modifica el valor del registro
	 *@param double numero, numero de palabra (0-3)
	 *@return void
	 **/
	public void modificar(double numero, int numPalabra)
	{
		switch(numPalabra){
		case 0:
			this.valor=new Palabra(numero);
		case 1:
			this.palabra1=new Palabra(numero);
		case 2:
			this.palabra2=new Palabra(numero);
		case 3:
			this.palabra3=new Palabra(numero);
		}	    	

//		this.valoresSegm.add(new Palabra(numero));
		setChanged();
	}

	/**
	 *Metodo que modifica el valor del registro
	 *@param String hexadecimal, numero de palabra (0-3)
	 *@return void
	 **/
	public void modificar(String hex, int numPalabra)
	{
		switch(numPalabra){
		case 0:
			this.valor=new Palabra(hex, true);
		case 1:
			this.palabra1=new Palabra(hex, true);
		case 2:
			this.palabra2=new Palabra(hex, true);
		case 3:
			this.palabra3=new Palabra(hex, true);
		}	    	

		//this.valor=new Palabra(hex, true);
		//this.valoresSegm.add(new Palabra(hex, true));    
		setChanged();
	}


	/**
	 *Metodo que sustituye la palabra almacenada en el registro por la que se le pasa
	 *@param Palabra palabra, numero de palabra (0-3)
	 *@return void
	 **/
	public void setPalabra(Palabra palabra, int numPalabra)
	{
		switch(numPalabra){
		case 0:
			this.valor=palabra;
		case 1:
			this.palabra1=palabra;
		case 2:
			this.palabra2=palabra;
		case 3:
			this.palabra3=palabra;
		}	    	

//		this.valoresSegm.add(palabra);
		setChanged();
	}
	/**
	 *Metodo que devuelve la palabra almacenada en el registro.
	 *@param numero de palabra (0-3)
	 *@return Palabra palabra
	 **/
	public Palabra getPalabra(int numPalabra)
	{
		switch(numPalabra){
		case 0:
			return valor;
		case 1:
			return palabra1;
		case 2:
			return palabra2;
		case 3:
			return palabra3;
		}
		return null;	        

	}	

}
