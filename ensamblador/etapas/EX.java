package ensamblador.etapas;

import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Nop;
import ensamblador.unidadFuncional.EstadoInstruccion;
import ensamblador.unidadFuncional.UFuncional;
import ensamblador.datos.Tipos;
import java.util.Vector;


public class EX extends Etapa {
	
	private Vector<UFuncional> unidFuncionales;
	private int ciclo;
	private static EX ex = null;
	
	private EX() {
		unidFuncionales = new Vector<UFuncional>();
		this.ciclo = -1;
	}
	
	public static EX getInstancia()
    {
    	if(ex == null){
    		ex = new EX();
    	}
        return ex;
    }
	
	@Override
	public void inicializar() {
		unidFuncionales = new Vector<UFuncional>();
		this.ciclo = -1;
	}
	
	public void setUnidFuncionales(Vector<UFuncional> unidFuncionales) {
		this.unidFuncionales = unidFuncionales;
	}
	
	public Vector<UFuncional> getUnidFuncionales() {
		return unidFuncionales;
	}
	
	public int getNumeroUF() {
		return unidFuncionales.size();
	}

	public void moverCiclo(MEM mem) {
		for(UFuncional uf:unidFuncionales) {
			if((uf.contieneInstEnFin(mem.getEstadoInstrucciones()[0])) || (uf.contieneInstEnFin(mem.getEstadoInstrucciones()[1]))
					|| (uf.contieneInstEnFin(new EstadoInstruccion(new Nop())))) {
				uf.avanzarCiclo();
			}
		}
	}
	
	public void recordarCiclo(int ciclo) {
		this.ciclo = ciclo;
	}
	
	//metodo que devuelve la instruccion entera que pasa a MEM
	public EstadoInstruccion getEstadoInstEntera() {
		return unidFuncionales.elementAt(0).getInstFin();
	}
	
	//metodo que devuelve la instruccion flotante que pasa a MEM
	public EstadoInstruccion getEstadoInstFlotante() {
		EstadoInstruccion inst = new EstadoInstruccion(new Nop());
		EstadoInstruccion instAux = new EstadoInstruccion(new Nop());
		int latencia = 0;
		boolean avanza = true;

		
		for(UFuncional uf:unidFuncionales) {
			
			if((uf.getTipo() != Tipos.INTEGER_FU) && (!uf.getInstFin().getInstruccion().toString().equals("nop")) 
					&& (uf.getLatencia()>latencia)) {
				instAux = uf.getInstFin();
				avanza = true;
				if(instAux.getInstruccion().regModificable()!=null) {
				for(UFuncional uf2:unidFuncionales) {
					for(EstadoInstruccion ei:uf2.getInstrucciones()) {

						if((ei.getInstruccion().regModificable() == instAux.getInstruccion().regModificable()) && 
								(ei.getCiclo() < instAux.getCiclo())) {
							avanza = false;							
						}
						if(!avanza) {
							break;
						}						
					}					
				}
				}
				if(avanza) {
					inst = instAux;							
					latencia = uf.getLatencia();
				}
			}
		}
		
		return inst;
	}
	
	@Override	
	public int avanzar(Etapa etapa) {
		EstadoInstruccion inst = ((ID)etapa).getEstadoInstruccion();
		for(UFuncional uf:unidFuncionales) {
		
			if((inst.getInstruccion().getTipoFU() == uf.getTipo()) && (uf.sePuedeInsertar())) {
				uf.anhadirInstruccion(inst, ciclo);	
				return -1;
			}
		}
		return 0;
	}
	
	@Override
	public int ejecutar() {
		int aux = -1;		
		for(UFuncional uf:unidFuncionales) {
			aux = uf.ejecutar();			
			if(aux != -1)
				return aux;
		}
		return aux;
	}

	@Override
	public void actualizarEtapas() {
		EstadoInstruccion estInst[];
		String ultEtapa;
		int j;
		for(UFuncional uf:unidFuncionales) {
			estInst = uf.getInstrucciones();
			for(int i=0; i<estInst.length; i++) {

				if(estInst[i].getEtapas().size()>0) {
					for(j=0; j<estInst[i].getEtapas().size(); j++){

						if(estInst[i].getEtapa(j).equals(" EX"+new Integer(i+1).toString())) {
							break;
						}
					}
					if(j ==	 estInst[i].getEtapas().size()) {

						estInst[i].anhadirEtapa(" EX"+new Integer(i+1).toString());				
					}
					else {

						estInst[i].anhadirEtapa(" burb");
					}				
				}
			}
		}			
	}
}
