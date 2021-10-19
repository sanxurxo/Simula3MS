package ensamblador.instrucciones;

import java.awt.Color;

import ensamblador.datos.Palabra;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Status;

public class Rfe extends Instruccion {

	public Rfe(){
		inicia();
		string.append("rfe");
	}

	public static String esValida(String param[]){
	        if(param.length!=0)
	            return "Instruccion no valida";
	        return null;
	}
	
	@Override
	public Color colorSegm() {
		  return new java.awt.Color(140,140,140);
	}

	 public void inicia(){
	       String imagenes="monociclo.gif";
	       super.inicIma(imagenes);
	 }
	@Override
	public int ejecutar() {
		 imagen_etapa=new StringBuffer();
	     boolean_pc=true;
	     Pc.getRegistro().incrementar(4);        
	     imagen_etapa.append(direccion_multi).append("caminodatos");
	     
	     /*Actualizando el registro status*/
			/*Moviendo de previo a antiguo (bits 3,2-->1,0)*/
			Status.getRegistro().getPalabra().setBit(1, Status.getRegistro().getPalabra().getBit(3));
			Status.getRegistro().getPalabra().setBit(0, Status.getRegistro().getPalabra().getBit(2));		
			/*Moviendo de actual a previo (bits 4,5-->3,2)*/
			Status.getRegistro().getPalabra().setBit(3, Status.getRegistro().getPalabra().getBit(4));
			Status.getRegistro().getPalabra().setBit(2, Status.getRegistro().getPalabra().getBit(5));		

			//bit 1
			/*1-->indica que el programa se estaba ejecutando en modo usuario cuando se produjo la interrupcion
			 *0-->indica que el programa se estaba ejecutando en modo kernel cuando se produjo la interrupcion*/
			//bit 0
			/*0-->indica que las interrupciones estan deshabilitadas
			 * 1-->/*indica que las interrupciones estan habilitadas*/
			
			
			// Esta era la actualizacion original, mv siempre los bits a la izda, e introduciendo valores nuevos en 
			//0 y 1. La cambiamos y ahora se produce un desplazamiento a la izda al llamar a la subrutina y un desplazamiento
			//a la dcha al ejecutar la rfe.			
//			/*Actualizando actual*/
//			
//			Status.getRegistro().getPalabra().setBit(1, "1");
//			
//			Status.getRegistro().getPalabra().setBit(0, "1"); 


			
			Status.getRegistro().notificar();
	     
	     
	     return -1;
	}

	@Override
	public int ejecutarEX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ejecutarID() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ejecutarIF() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ejecutarMEM() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int ejecutarWB() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int estilo() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int etapa3() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int etapa4() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int etapa5() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCodificacion() {
		//010000 1 0000000000000000000 010000
		return (new Palabra("0x42000010", true)).getHex();
	}

	@Override
	public Registro getRegRS() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Registro getRegRT() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String imagenSegmentado(int etapa){
        return "nop.gif";//???????????????????????
    }

	@Override
	public int numEtapas() {
		
		return 1;
	}

	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return 60;
	}

	@Override
	public Registro regModificable() {
		// TODO Auto-generated method stub
		return null;
	}

}
