package ensamblador.planificacionDinamica.tomasulo;

import java.util.Observable;
import java.util.Vector;

import ensamblador.componentes.bus.BusCBD;
import ensamblador.componentes.estacionReserva.EstacionReserva;
import ensamblador.componentes.fus.estacionTomasulo.ERTomasulo;
import ensamblador.componentes.fus.estacionTomasulo.EstadoEstacion;

import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.estado.DesambiguarMem;
import ensamblador.instrucciones.Instruccion;
import ensamblador.planificacionDinamica.CoprocesadorFP;
import ensamblador.planificacionDinamica.tomasulo.actions.AvanzarCicloAccion;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.ERsOcupadasException;
import ensamblador.util.excepcion.FUsOcupadasException;

public class Tomasulo extends CoprocesadorFP {
	
	
	private Vector<EstacionReserva> ers;

	private BusCBD bus;	
	private Etapa ejecucion;
	private Etapa escritura;
	private DesambiguarMem desambiguarMem;
	
	
	
	public Tomasulo(Vector<Vector> todasERs) {
		super();
		if(Tipos.TIPOS_ER!=todasERs.size()){
			//lanzar exception creaccion incorrecta de FUS
		}		
		bus=new BusCBD();		

		this.riesgos.setConfiguracion("Tomasulo");
		ejecucion=new Etapa();
		escritura=new Etapa();
		
		int latencia;
		int numER;		
		ers=new Vector<EstacionReserva>();
		Vector<Integer> infoER; /*tipo, numER, latencia*/
		EstacionReserva er=null;
		String nombreER;
		int tipo;		
		EstacionReserva estacionReserva;

		
		for(int i=0; i<todasERs.size();i++){	

			infoER=todasERs.elementAt(i);

			tipo=(infoER.elementAt(0)).intValue();

			numER=(infoER.elementAt(1)).intValue();
			latencia=(infoER.elementAt(2)).intValue();
			estacionReserva=new EstacionReserva(tipo, numER, latencia);
			ers.add(estacionReserva);
			estacionReserva.addObserver(this);
			bus.addObserver(ers.elementAt(i));
			
		}
		
		
		desambiguarMem=DesambiguarMem.getDesambiguarMem();
		desambiguarMem.inicializar();
	}
	
	
	public DesambiguarMem getDesambiguarMem() {
		return desambiguarMem;
	}




	public ERTomasulo ocuparER(int tipo) throws ERsOcupadasException{
		/*Accedemos a las unidades funcionales
		 * que son del tipo que queremos*/
		EstacionReserva er;
		ERTomasulo fu=null;
		for(int i=0;i< ers.size();i++){
			er=ers.elementAt(i);
			if((er.getTipo()==tipo) && (er.hayLibres())){
				try{
				fu=er.ocupar();
				}catch(FUsOcupadasException e){
					e.toString();
				}

				return fu;
			}
		}
	
		//habria que lanzar una exception
		// que indicaria q no hay unid fun libres
		// y que se capturaria en el met q llama a este	
		throw new ERsOcupadasException("No hay unidades libres del tipo solicitado");
	
	}
	
	
	public void desocupar(ERTomasulo fu) {
		EstacionReserva er;
		for(int i=0;i<ers.size();i++){
			er=ers.elementAt(i);
			if(er.getTipo()==fu.getTipo()){
				er.desocupar(fu);
			}
		}
		
	}
	
	
	
	
	public boolean hayERLibre(int tipo){
		
		EstacionReserva er;
		for(int i=0;i<ers.size();i++){
			er=ers.elementAt(i);
			if(er.getTipo()==tipo){
				return er.hayLibres();
			}
		}
		return false;
	}
		
	public Vector<EstacionReserva> getERs() {
		return ers;
	}
	

	public void setFus(Vector<EstacionReserva> ers) {		
		this.ers = ers;
		setChanged();
		notifyObservers(this.ers);
	}
	

	public void update(Observable o, Object arg) {


		Vector<ERTomasulo> fus=new Vector<ERTomasulo>();
		Vector<ERTomasulo> fusER;
		for(int i=0;i<ers.size();i++){
			fusER=ers.elementAt(i).getFus();			
			for(int j=0;j< fusER.size();j++){
				fus.addElement(fusER.elementAt(j));
			}			
		}
		setChanged();
		notifyObservers(fus);
	}




	public void actualizarER(ERTomasulo fu, String operacion, Palabra vj, Palabra vk, String qj, String qk, String a){
		EstadoEstacion estado=(EstadoEstacion)fu.getEstado();
		if(operacion==null){
			estado.setOp(new String());
		}else{
			estado.setOp(operacion);
		}
		
		if(vj==null){
			estado.setVj(null);
		}else{
			estado.setVj(vj);
		}
		
		if(vk==null){
			estado.setVk(null);
		}else{
			estado.setVk(vk);
		}
		
		if(qj==null){
			estado.setQj(new String());
		}else{
			estado.setQj(qj);
		}
		
		if(qk==null){
			estado.setQk(new String());
		}else{
			estado.setQk(qk);
		}
		
		if(a==null){
			estado.setA(new String());
		}else{
			estado.setA(a);
		}
		
		
		fu.setEstado(estado); //para q realice las notificaciones

		setChanged();
		notifyObservers(this.ers);
	}
	

	public boolean isOperacionFP(int tipo){
		switch(tipo){

		case Tipos.FP_ADD_ER:
			return true;
		case Tipos.FP_DIV_ER:
			return true;
		case Tipos.FP_MULT_ER:
			return true;
		}
		return false;
	}
	public boolean isLoad(int tipo){
		if(tipo==Tipos.LOAD_ER){
			return true;
		}
		return false;
	}
	
	public boolean isStore(int tipo){
		if(tipo==Tipos.STORE_ER){
			return true;
		}
		return false;
	}
	
	public boolean isFULibre(int tipo){
		EstacionReserva er=null;
		for(int i=0;i<ers.size();i++){
			er=ers.elementAt(i);
			if(er.getTipo()==tipo){
				return (er.isFuLibre().equals(new String()));
			}
		}
		return false;
	}
	public void ocuparFU(int tipo, String nombreER){
		EstacionReserva er=null;
		for(int i=0;i<ers.size();i++){
			er=ers.elementAt(i);
			if(er.getTipo()==tipo){
				er.setFuLibre(nombreER);
			}
		}
		
	}
	
	public void liberarFU(int tipo){
		EstacionReserva er=null;
		for(int i=0;i<ers.size();i++){
			er=ers.elementAt(i);
			if(er.getTipo()==tipo){
				er.setFuLibre(new String());
			}
		}
	}

	public boolean esEnFU(int tipo, String nombre){
		EstacionReserva er=null;
		for(int i=0;i<ers.size();i++){
			er=ers.elementAt(i);
			if(er.getTipo()==tipo){
				return (er.getEstacionReservaEnFU().equals(nombre));
			}
		}
		return false;
	}
	

	public BusCBD getBus() {
		return bus;
	}




	public Etapa getEjecucion() {
		return ejecucion;
	}




	public void setEjecucion(Etapa ejecucion) {
		this.ejecucion = ejecucion;
	}




	public Etapa getEscritura() {
		return escritura;
	}




	public void setEscritura(Etapa escritura) {
		this.escritura = escritura;
	}




	@Override
	public boolean estaParado() {
		if((getEmision().size()==0) &&(getEjecucion().getTamanho()==0) && (getEscritura().getTamanho()==0)){

			return true;
		}
		return false;
	}




	@Override
	public boolean estaEjecutando(int pc, Codigo instrucciones) {
		
		Vector<ERTomasulo> instr=this.getEjecucion().getEstacionesReserva();
		instr.addAll(this.getEscritura().getEstacionesReserva());
		
		for(int i=0; i<instr.size();i++){
			if(instrucciones.getPcInst(instr.elementAt(i).getEstadoInstruccion().getInstruccion())==pc){
				return true;
			}
		
		}
		
		return false;
	}




	
	@Override
	protected Accion getAccion(long pcActual, Instruccion instruccion) {
		AvanzarCicloAccion avanzarCiclo=new AvanzarCicloAccion(pcActual, instruccion, this);
		return avanzarCiclo;
	}



	
	

		
}
