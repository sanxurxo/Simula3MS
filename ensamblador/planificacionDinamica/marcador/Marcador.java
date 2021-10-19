package ensamblador.planificacionDinamica.marcador;

import java.util.Observable;
import java.util.Vector;

import ensamblador.componentes.bus.DataTrunks;
import ensamblador.componentes.fus.fusMarcador.EstadoFUMarcador;
import ensamblador.componentes.fus.fusMarcador.FUMarcador;
import ensamblador.datos.Codigo;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.planificacionDinamica.CoprocesadorFP;
import ensamblador.planificacionDinamica.marcador.actions.AvanzarCicloAccion;
import ensamblador.util.action.Accion;
import ensamblador.util.excepcion.FUsOcupadasException;

public class Marcador extends CoprocesadorFP {
	
	private Lenguaje lenguaje;
	private Vector<FUMarcador> fus;
	private Etapa lectura;
	private Etapa ejecucion;
	private Etapa escritura;
	private DataTrunks dataTrunks=null;
	
	public Marcador(Vector<Vector> todasFUs) {
		super();
		lenguaje = Lenguaje.getInstancia();
		dataTrunks=DataTrunks.getDataTrunks();
		if(Tipos.TIPOS_FU!=todasFUs.size()){
			//lanzar exception creaccion incorrecta de FUS
		}		
	

		this.riesgos.setConfiguracion("Marcador");
		lectura=new Etapa();
		ejecucion=new Etapa();
		escritura=new Etapa();
		
		int latencia;
		int numFU;		
		fus=new Vector<FUMarcador>();
		Vector<Integer> infoFU;
		FUMarcador fu=null;
		String nombreFU;
		
		
		for(int i=0; i<todasFUs.size();i++){	

			infoFU=todasFUs.elementAt(i);

			numFU=(infoFU.elementAt(0)).intValue();
			latencia=(infoFU.elementAt(1)).intValue();
			for(int j=0;j<numFU;j++){							
				switch (i){
				case Tipos.INTEGER_FU:
					nombreFU=lenguaje.getString("FU_INTEGER")+ j ;
					fu=new FUMarcador(latencia, Tipos.INTEGER_FU, nombreFU);
					this.riesgos.setInteger(this.riesgos.getInteger()+1);
					this.riesgos.setLatInteger(latencia);
					break;
				case Tipos.FP_ADD_FU:
					nombreFU=lenguaje.getString("FU_ADD") + j ;
					fu=new FUMarcador(latencia, Tipos.FP_ADD_FU, nombreFU);
					this.riesgos.setAdd(this.riesgos.getAdd()+1);
					this.riesgos.setLatAdd(latencia);
					break;
				case Tipos.FP_MULT_FU:
					nombreFU=lenguaje.getString("FU_MULT") + j ;
					fu=new FUMarcador(latencia, Tipos.FP_MULT_FU, nombreFU);
					this.riesgos.setMult(this.riesgos.getMult()+1);
					this.riesgos.setLatMult(latencia);
					break;
				case Tipos.FP_DIV_FU:
					nombreFU=lenguaje.getString("FU_DIV") + j ;
					fu=new FUMarcador(latencia, Tipos.FP_DIV_FU, nombreFU);
					this.riesgos.setDiv(this.riesgos.getDiv()+1);
					this.riesgos.setLatDiv(latencia);
					break;
				}
				fu.addObserver(this);
				
				fus.addElement(fu);
			}
		}
	}
	


	
	public FUMarcador ocuparFU(int tipo) throws FUsOcupadasException{
		/*Accedemos a las unidades funcionales
		 * que son del tipo que queremos*/
		FUMarcador fu;
		for(int i=0;i< fus.size();i++){
			fu=fus.elementAt(i);
			if((fu.getTipo()==tipo) && (fu.getEstado().isOcupada()==false)){
				fu.ocupar();
		
				return fu;
			}
		}
	
		//habria que lanzar una exception
		// que indicaria q no hay unid fun libres
		// y que se capturaria en el met q llama a este	
		throw new FUsOcupadasException("No hay unidades libres del tipo solicitado");
	
	}
	
	
	public void desocupar(FUMarcador unidadFuncional) {
		
		FUMarcador fu;
		EstadoFUMarcador estado;
		String nombre=unidadFuncional.getEstado().getNombre();
		for(int i=0; i< fus.size(); i++){
			fu=fus.elementAt(i);
			estado=(EstadoFUMarcador)fu.getEstado();
			if(estado.isOcupada()){
				if(estado.getQj().equals(nombre)){
					estado.setRj(true);
					estado.setQj(new String());
				}
				if(estado.getQk().equals(nombre)){
					estado.setRk(true);
					estado.setQk(new String());
				}							
			}
		}
		unidadFuncional.desocupar();		
	}
	
		
	public EstadoFUMarcador estaEnFU(String reg, int f, String nombreFU) {
		EstadoFUMarcador estado;
		for(int i=0;i<fus.size();i++){
			estado=(EstadoFUMarcador)fus.elementAt(i).getEstado();
			if(estado.isOcupada()){
				//si no es la fu q estamos usando
				if(reg!=null){
					if(!estado.getNombre().equals(nombreFU)){				
						if(f==Tipos.FI){					
							if((estado.getFi().equals(reg))){
								return estado;
							}
						}
						if(f==Tipos.FJ){
							if((estado.getFj().equals(reg))){
								return estado;
							}
						} 
						if(f==Tipos.FK){					
							if((estado.getFk().equals(reg))){
								return estado;
							}
						}
						
					}
				}
			}
		}		
		return null;
	}
	
	
	public boolean ocupadaFU(int tipo){
		
		FUMarcador fu;
		EstadoFUMarcador estado;
		/*Miramos si hay alguna del tipo que queremos
		 * que este libre*/
		for(int i=0;i<fus.size();i++){
			fu=fus.elementAt(i);
			estado=(EstadoFUMarcador)fu.getEstado();
			
			/*Si hay alguna fu libre del tipo que se solicita
			 * devuelve false, si no devuelve true */
			if((fu.getTipo()== tipo) && (estado.isOcupada()==false)){
				return false;
			}					
		}
		return true;
	}
	
	public Vector<FUMarcador> getFus() {
		return fus;
	}
	
	public FUMarcador getFU(String nombre){
		FUMarcador fu;
		for (int i=0;i<fus.size();i++){
			fu=fus.elementAt(i);
			if(fu.getNombre().equals(nombre)){
				return fu;
			}
		}
		return null;
	}

	public void setFus(Vector<FUMarcador> fus) {		
		this.fus = fus;
		setChanged();
		notifyObservers(this.fus);
	
	}
	
	
	public Etapa getLectura() {
		return lectura;
	}



	

	public void actualizarFU(FUMarcador fu, String operacion, String fi, String fj, String fk, String qj, String qk){
		EstadoFUMarcador estado=(EstadoFUMarcador)fu.getEstado();
		if(operacion==null){
			estado.setOp(new String());
		}else{
			estado.setOp(operacion);
		}
		
		if(fi==null){
			estado.setFi(new String());
		}else{
			estado.setFi(fi);
		}
		
		if(fj==null){
			estado.setFj(new String());
		}else{
			estado.setFj(fj);
		}
		
		if(fk==null){
			estado.setFk(new String());
		}else{
			estado.setFk(fk);
		}
		
		if(qj==null){// || qj.equals(new String())){
			estado.setQj(new String());
		}else{
			estado.setQj(qj);
		}
		
		if(qk==null){// || qj.equals(new String())){
			estado.setQk(new String());
		}else{
			estado.setQk(qk);
		}
		
		if(estado.getQj().equals(new String())){
			estado.setRj(true);
		}else{
			estado.setRj(false);
		}
		
		if(estado.getQk().equals(new String())){
			estado.setRk(true);
		}else{
			estado.setRk(false);
		}
		fu.setEstado(estado); //para q realice las notificaciones
		setChanged();
		notifyObservers(this.fus);
	}
	
	public void setLectura(Etapa lectura) {
		this.lectura = lectura;
	}




	public void update(Observable o, Object arg) {
		setChanged();
		notifyObservers(this.fus);
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
		if((getEmision().size()==0) && (getLectura().getTamanho()==0) && (getEjecucion().getTamanho()==0) && 
				(getEscritura().getTamanho()==0)){
		
			return true;
		}
		return false;
	}


	public boolean estaEjecutando(int pc, Codigo instrucciones) {
		Vector<FUMarcador> instr=this.getEjecucion().getFus();
		instr.addAll(this.getEscritura().getFus());
		instr.addAll(this.getLectura().getFus());
		
		for(int i=0; i<instr.size();i++){
			if(instrucciones.getPcInst(instr.elementAt(i).getEstadoInstruccion().getInstruccion())==pc){
				return true;
			}
		
		}
		
		return false;
	}


	protected Accion getAccion(long pcActual, Instruccion instruccion){
		AvanzarCicloAccion avanzarCiclo=new AvanzarCicloAccion(pcActual, instruccion, this);
		return avanzarCiclo;
	}

//
//He cambiado esto pero lo dejo comentado por si acaso me he olvidado de algo
// Hay demasiados aspectos y puede que se me haya olvidado algo
//	public boolean busLibre(int tipo){
//		switch (tipo){
//		case Tipos.INTEGER_FU:
//			if(!dataTrunks.isBusInt()){
//				dataTrunks.setBusInt(true);
//				return true;
//			}
//			return false;
//			
//		case Tipos.FP_ADD_FU:
//			if(!dataTrunks.isBusAdd()){
//				dataTrunks.setBusAdd(true);
//				return true;
//			}
//			return false;
//			
//		case Tipos.FP_MULT_FU:
//			if(!dataTrunks.isBusMul()){
//				dataTrunks.setBusMul(true);
//				return true;
//			}
//			return false;
//			
//		case Tipos.FP_DIV_FU:
//			if(!dataTrunks.isBusDiv()){
//				dataTrunks.setBusDiv(false);
//				return true;
//			}
//			return false;	
//		}
//		return false;
//	}


	public boolean busLibre(int tipo){
		switch (tipo){
		case Tipos.INTEGER_FU:
			if(!dataTrunks.isBusInt()){
				dataTrunks.setBusInt(true);
				return true;
			}
			return false;
			
		case Tipos.FP_ADD_FU:
			if(!dataTrunks.isBusAdd()){
				dataTrunks.setBusAdd(true);
				return true;
			}
			return false;
			
		case Tipos.FP_MULT_FU:
			if(!dataTrunks.isBusMul()){
				dataTrunks.setBusMul(true);
				return true;
			}
			return false;
			
		case Tipos.FP_DIV_FU:
			if(!dataTrunks.isBusDiv()){
				dataTrunks.setBusDiv(true);
				return true;
			}
			return false;	
		}
		return false;
	}
	
	//Anhado tambien este metodo para liberar el bus
	//en el caso de q inicialmente se solicite pero despues no se use
	
	public void liberaBus(int tipo){
		switch (tipo){
		case Tipos.INTEGER_FU:
			dataTrunks.setBusInt(false);
			break;
		case Tipos.FP_ADD_FU:
			dataTrunks.setBusAdd(false);
			break;	
		case Tipos.FP_MULT_FU:
			dataTrunks.setBusMul(false);
			break;
		case Tipos.FP_DIV_FU:
			dataTrunks.setBusDiv(false);
			break;
		}
		
	}
	
	
	public void liberarBuses(){
		dataTrunks.iniciarBuses();
	}
}
