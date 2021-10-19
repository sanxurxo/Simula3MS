package ensamblador.etapas;

import ensamblador.instrucciones.Nop;
import ensamblador.unidadFuncional.EstadoInstruccion;

public class MEM extends Etapa {

	private EstadoInstruccion estadoInstEntera;
	private EstadoInstruccion estadoInstFlotante;
	
	private static MEM mem = null;
	
	private MEM() {
		estadoInstEntera = new EstadoInstruccion(new Nop());
		estadoInstFlotante = new EstadoInstruccion(new Nop());
	}
	
	public static MEM getInstancia()
    {
    	if(mem == null){
    		mem = new MEM();
    	}
        return mem;
    }
	
	@Override
	public void inicializar() {
		estadoInstEntera = new EstadoInstruccion(new Nop());
		estadoInstFlotante = new EstadoInstruccion(new Nop());
	}
	
	public EstadoInstruccion[] getEstadoInstrucciones() {
		EstadoInstruccion[] instrucciones = {this.estadoInstEntera, this.estadoInstFlotante};
		return instrucciones;
	}

	@Override
	public int avanzar(Etapa etapa) {
		this.estadoInstEntera = ((EX)etapa).getEstadoInstEntera();
		this.estadoInstFlotante = ((EX)etapa).getEstadoInstFlotante();
		return -1;
	}

	@Override
	public int ejecutar() {
		int aux1 = estadoInstEntera.getInstruccion().ejecutarMEM();
		int aux2 = estadoInstFlotante.getInstruccion().ejecutarMEM();		
		return ((aux1 != -1) ? aux1 : aux2);
	}

	@Override
	public void actualizarEtapas() {
		if(estadoInstEntera.getEtapas().size()>0) {
			if(estadoInstEntera.getUltimaEtapa().equals("MEM")) {
				estadoInstEntera.anhadirEtapa(" burb");
			}
			else {
				estadoInstEntera.anhadirEtapa("MEM");
			}
		}
		
		if(estadoInstFlotante.getEtapas().size()>0) {
			if(estadoInstFlotante.getUltimaEtapa().equals("MEM")) {
				estadoInstFlotante.anhadirEtapa(" burb");
			}
			else {
				estadoInstFlotante.anhadirEtapa("MEM");
			}
		}
	}
}
