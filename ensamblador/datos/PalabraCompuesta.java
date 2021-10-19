package ensamblador.datos;

import java.util.Vector;


public class PalabraCompuesta extends Palabra {

	private Vector<Palabra> palabras;
	
	public PalabraCompuesta(String p, boolean hexa) {
		super(p, hexa);
		palabras=new Vector<Palabra>();
		// TODO Auto-generated constructor stub
	}
	public PalabraCompuesta(String p) {
		super(p);
		palabras=new Vector<Palabra>();
		// TODO Auto-generated constructor stub
	}
	
	public PalabraCompuesta(long p) {
		super(p);
		palabras=new Vector<Palabra>();
		// TODO Auto-generated constructor stub
	}
	
	public PalabraCompuesta(double p) {
		super(p);
		palabras=new Vector<Palabra>();
		// TODO Auto-generated constructor stub
	}

	public void agregarPalabras(Palabra p1, Palabra p2){
		palabras.add(0, p1);
		palabras.add(1,p2);
	}
	
	public Palabra obtenerPalabra(int posicion){
		return palabras.elementAt(posicion);
	}
	
	public String getHex(){
		if(palabras.size()>0){
			return palabras.elementAt(0).getHex();
		}
		return new String();
	}
}
