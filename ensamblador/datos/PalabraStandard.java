package ensamblador.datos;

public class PalabraStandard {

	/**
	 *Metodo que devuelve el valor de la palabra en decimal
	 *@param sin parametros
	 *@return long decimal
	 **/
	public long getDec(){
		return -1;
	}

	/**
	 *Metodo que devuelve el valor de la palabra en hexadecimal
	 *@param sin parametros
	 *@return String hexadecimal
	 **/
	public String getHex(){
		return null;
	}

	/**
	 *Metodo que devuelve el valor de la palabra en binario
	 *@param sin parametros
	 *@return String binario
	 **/
	public String getBin(){
		return null;
	}

	/**
	 *Metodo que devuelve el valor de la palabra en binario para punto flotante
	 *@param sin parametros
	 *@return String binario en formato punto flotante
	 **/
	public String getFloat(){
		return null;
		
	}

	/**
	 *Metodo que devuelve el valor de la palabra en decimal para punto flotante
	 *@param sin parametros
	 *@return double decimal en formato punto flotante
	 **/
	public double getFrac(){
		return -1;
	}
	
	public void agregarPalabras(Palabra p1, Palabra p2){}
	public Palabra obtenerPalabra(int posicion){return null;}

}