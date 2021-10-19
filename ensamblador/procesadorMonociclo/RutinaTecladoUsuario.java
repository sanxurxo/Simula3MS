package ensamblador.procesadorMonociclo;

import ensamblador.datos.Etiquetas;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Instruccion;
import ensamblador.util.excepcion.DuplicatedLabelException;

public class RutinaTecladoUsuario extends RutinaTeclado {

	
	private RutinaTecladoUsuario(){
		super(true);
	}
	
	public static void inicializar(){
		rutinaTeclado=null;
	}
	
	public static RutinaTeclado getInstancia(){
		if(rutinaTeclado==null){
			rutinaTeclado=new RutinaTecladoUsuario();
	
		}
		return rutinaTeclado;
	}
	
	public void setInstruccion(Instruccion instruccion){
		
		pcs.add(base.getHex());
		instrucciones.put(base.getHex(), instruccion);
		base=base.sumar(4);
	}
	
	public void setEtiqueta(String etiqueta, int posicion) throws DuplicatedLabelException{
		long direccion=base.getDec()+posicion*4;
		Palabra pos=new Palabra(direccion);
		
       	 Etiquetas.getEtiquetas().anhadirEtiquetaRutina(etiqueta.substring(0, etiqueta.indexOf(":")), pos.getHex());
        
	}
}
