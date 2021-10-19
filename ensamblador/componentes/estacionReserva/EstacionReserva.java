package ensamblador.componentes.estacionReserva;

import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import ensamblador.componentes.bus.BusCBD;
import ensamblador.componentes.fus.estacionTomasulo.ERTomasulo;
import ensamblador.componentes.fus.estacionTomasulo.EstadoEstacion;

import ensamblador.datos.Lenguaje;
import ensamblador.datos.Tipos;
import ensamblador.estado.Riesgos;
import ensamblador.registros.Registro;
import ensamblador.util.excepcion.FUsOcupadasException;

public class EstacionReserva extends Observable implements Observer {
	private Lenguaje lenguaje;
	private Riesgos riesgos=null;
	private int tipo;
	private int numOcupadas=0;
	private String fuLibre=new String();
	private Vector<ERTomasulo> fus;
	public EstacionReserva(int tipo, int numERs, int latencia) {
		lenguaje = Lenguaje.getInstancia();
		this.riesgos=Riesgos.getRiesgos();
		this.tipo=tipo;
		String tipoER=new String();
		String nombre;
		ERTomasulo fu;
		fus=new Vector<ERTomasulo>();
		switch(tipo){
		case Tipos.INTEGER_ER:

		case Tipos.FP_ADD_ER:			
			tipoER=lenguaje.getString("ER_ADD");
			this.riesgos.setAdd(numERs);
			this.riesgos.setLatAdd(latencia);
			break;
		case Tipos.FP_DIV_ER:
			tipoER=lenguaje.getString("ER_DIV");
			this.riesgos.setDiv(numERs);
			this.riesgos.setLatDiv(latencia);
			break;
		case Tipos.FP_MULT_ER:
			tipoER=lenguaje.getString("ER_MULT");
			this.riesgos.setMult(numERs);
			this.riesgos.setLatMult(latencia);
			break;
		case Tipos.LOAD_ER:
			tipoER=lenguaje.getString("ER_LOAD");
			this.riesgos.setCarga(numERs);			
			break;
		case Tipos.STORE_ER:
			tipoER=lenguaje.getString("ER_STORE");
			this.riesgos.setAlmacenamiento(numERs);
			break;
		}
		
		for(int i=0; i<numERs; i++){			
			nombre=tipoER+i;
			fu=new ERTomasulo(latencia, tipo, nombre);
			fu.addObserver(this);
			fus.add(fu);
		}
	}
	
	public boolean hayLibres(){
		if(numOcupadas<fus.size()){
			return true;
		}
		return false;
	}
	
	public void actualizar(){
		setChanged();
		notifyObservers();
	}
	
	public ERTomasulo ocupar() throws FUsOcupadasException{
		ERTomasulo fu;
		for(int i=0;i<fus.size();i++){
			fu=fus.elementAt(i);
			if((fu.getEstado().isOcupada()==false)){
				fu.ocupar();
				
				
				numOcupadas++;
				return fu;
			}
		}
		throw new FUsOcupadasException("No hay unidades libres del tipo solicitado");
	}
	
	public void desocupar(ERTomasulo unidadFuncional){
		unidadFuncional.desocupar();
		numOcupadas--;
	}
	
	public int getTipo(){
		return tipo;
	}
	
	/*Esta libre si devuelve null
	 * si no devuelve el nombre de la ER que esta ocupandola*/
	public String isFuLibre() {
		return fuLibre;
	}
	
	public void setFuLibre(String fuLibre) {
		this.fuLibre = fuLibre;
	}
	
	public String getEstacionReservaEnFU(){
		return this.fuLibre;
	}
	
	public void update(Observable o, Object arg) {

		if(o instanceof BusCBD){
			BusCBD bus=(BusCBD)o;
			ERTomasulo fu;
			Registro regRS=null;
			Registro regRT=null;
			EstadoEstacion estado;
			
			for(int i=0;i<this.getFus().size();i++){
				fu=fus.elementAt(i);			
				estado=(EstadoEstacion)fu.getEstado();
			
				if(fu.getEstadoInstruccion()!=null){
					regRS=fu.getEstadoInstruccion().getInstruccion().getRegRS();
					regRT=fu.getEstadoInstruccion().getInstruccion().getRegRT();
					if(estado.getQj().equals(bus.getEstacion())){
			
						if(regRS!=null){
							if(fu.getEstadoInstruccion().getInstruccion().getRegRS().toString().equals(bus.getRegistro())){
								estado.setQj(new String());
								fu.getEstadoInstruccion().getInstruccion().getRegRS().setQi(new String());
								estado.setVj(bus.getResultado());
						
							}
						}
						if(regRT!=null){
							if(fu.getEstadoInstruccion().getInstruccion().getRegRT().toString().equals(bus.getRegistro())){
								estado.setQj(new String());
								fu.getEstadoInstruccion().getInstruccion().getRegRT().setQi(new String());
								estado.setVk(bus.getResultado());
						
							}
						}
						fu.setEstado(estado);
						
					}
					
					if(estado.getQk().equals(bus.getEstacion())){
			
						if(regRS!=null){
							if(fu.getEstadoInstruccion().getInstruccion().getRegRS().toString().equals(bus.getRegistro())){
								estado.setQk(new String());					
								fu.getEstadoInstruccion().getInstruccion().getRegRS().setQi(new String());
								estado.setVj(bus.getResultado());
						
							}
						}
						if(regRS!=null){
							if(fu.getEstadoInstruccion().getInstruccion().getRegRT().toString().equals(bus.getRegistro())){
								estado.setQk(new String());
								fu.getEstadoInstruccion().getInstruccion().getRegRT().setQi(new String());
								estado.setVk(bus.getResultado());
					
							}
						}
						fu.setEstado(estado);
					}
				}
			}
		}
		setChanged();
		notifyObservers(this);
	}
	
	public Vector<ERTomasulo> getFus() {
		return fus;
	}
	
}
