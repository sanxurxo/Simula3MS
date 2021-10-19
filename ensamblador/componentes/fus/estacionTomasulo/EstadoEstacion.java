package ensamblador.componentes.fus.estacionTomasulo;

import ensamblador.componentes.fus.EstadoFU;
import ensamblador.datos.Palabra;



public class EstadoEstacion extends EstadoFU {

	
	protected String A=new String();
	protected Palabra Vj=null;
	protected Palabra Vk=null;	
	protected long cicloModif=0;
	
	protected EstadoEstacion(String nombre) {
		super(nombre);		
	}

	public String getA() {
		return A;
	}

	public void setA(String a) {
		A = a;
	}

	public String getOp() {
		return op;
	}


	public String getStringVj(){
		if(Vj==null){
			return new String();
		}
		else{
			return Vj.getHex();
		}
			
	}
	
	public String getStringVk(){
		if(Vk==null){
			return new String();
		}else{
			return Vk.getHex();
		}
		
	}
	
	public Palabra getVj() {
		return Vj;
	}

	public void setVj(Palabra vj) {
		Vj = vj;
	}

	public Palabra getVk() {
		return Vk;
	}

	public void setVk(Palabra vk) {
		Vk = vk;
	}

	public long getCicloModif() {
		return cicloModif;
	}

	public void setCicloModif(long cicloModif) {	
		this.cicloModif = cicloModif;	
	}

}
