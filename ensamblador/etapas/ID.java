package ensamblador.etapas;

import ensamblador.unidadFuncional.EstadoInstruccion;
import ensamblador.instrucciones.Nop;

public class ID extends Etapa {
	
	private EstadoInstruccion estadoInstruccion; 
	private static ID id= null;
	
	private ID() {
		estadoInstruccion = new EstadoInstruccion(new Nop());
	}
	
	public static ID getInstancia()
    {
    	if(id == null){
    		id = new ID();
    	}
        return id;
    }
	
	@Override
	public void inicializar() {
		estadoInstruccion = new EstadoInstruccion(new Nop());
	}
	
	public EstadoInstruccion getEstadoInstruccion() {
		return this.estadoInstruccion;
	}

	@Override
	public int avanzar(Etapa etapa) {
		this.estadoInstruccion = ((IF)etapa).getEstadoInstruccion();
		return -1;
	}

	@Override
	public int ejecutar() {
		
		if(!estadoInstruccion.getInstruccion().hayRiesgo())
			return estadoInstruccion.getInstruccion().ejecutarID();
		estadoInstruccion.getInstruccion().setSaltoTomado(false);
		return -1;
	}

	@Override
	public void actualizarEtapas() {
		boolean anhadirBurb = false;
		if(estadoInstruccion.getEtapas().size()>0) {
			for(String et:estadoInstruccion.getEtapas()) {
				if(et.equals("  ID")) {
					anhadirBurb = true;					
					break;
				}
			}
			if(anhadirBurb) {
				estadoInstruccion.anhadirEtapa(" burb");
			}
			else {
				estadoInstruccion.anhadirEtapa("  ID");
			}
		}
	}
}
