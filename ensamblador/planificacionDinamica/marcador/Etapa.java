package ensamblador.planificacionDinamica.marcador;

import java.util.Vector;
import ensamblador.componentes.fus.fusMarcador.FUMarcador;



public class Etapa {

	private Vector<FUMarcador> fus;
	public Etapa() {		
		fus=new Vector<FUMarcador>();
	}
	
	
	 public Vector<FUMarcador> getFus() {
		 Vector<FUMarcador> copia=new Vector<FUMarcador>();
		 for(int i=0;i<fus.size();i++){
			 copia.addElement(fus.elementAt(i));
		 }
		return copia;
	}


	public void setFus(Vector<FUMarcador> fus) {
		this.fus = fus;
	}

	/**
     *metodo que anhade una unidad funcional que
     * se encuentra en ese etapa
     *@param Instruccion instruccion
     *return void
     **/	
	public void anhadirFU(FUMarcador fu){
		this.fus.addElement(fu);
	}
	
	 /**
     *metodo que quita una unidad funcional de una etapa
     *@param Instruccion instruccion
     *return true si se ha encontrado la instruccion
     **/
	public boolean removeFU(FUMarcador fu){
		return this.fus.remove(fu);
	}
	
	public int getTamanho(){
		return fus.size();
	}
	
}
