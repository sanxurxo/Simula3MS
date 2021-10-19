package ensamblador.etapas;

import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Nop;
import ensamblador.unidadFuncional.EstadoInstruccion;
import ensamblador.unidadFuncional.UFuncional;

public class WB extends Etapa {

	private EstadoInstruccion estadoInstEntera;
	private EstadoInstruccion estadoInstFlotante;
	
	private static WB wb = null;
	
	private WB() {
		estadoInstEntera = new EstadoInstruccion(new Nop());
		estadoInstFlotante = new EstadoInstruccion(new Nop());
	}
	
	public static WB getInstancia()
    {
    	if(wb == null){
    		wb = new WB();
    	}
        return wb;
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
		this.estadoInstEntera = ((MEM)etapa).getEstadoInstrucciones()[0];
		this.estadoInstFlotante = ((MEM)etapa).getEstadoInstrucciones()[1];
		return -1;
	}

	@Override
	public int ejecutar() {
		int aux1 = estadoInstEntera.getInstruccion().ejecutarWB();
		int aux2 = estadoInstFlotante.getInstruccion().ejecutarWB();

		return ((aux1 != -1) ? aux1 : aux2);
	}

	@Override
	public void actualizarEtapas() {
		if(estadoInstEntera.getEtapas().size()>0) {
			if(estadoInstEntera.getUltimaEtapa().equals(" WB")) {
				estadoInstEntera.anhadirEtapa(" burb");
			}
			else {
				estadoInstEntera.anhadirEtapa(" WB");
			}
		}
		
		if(estadoInstFlotante.getEtapas().size()>0) {
			if(estadoInstFlotante.getUltimaEtapa().equals(" WB")) {
				estadoInstFlotante.anhadirEtapa(" burb");
			}
			else {
				estadoInstFlotante.anhadirEtapa(" WB");
			}
		}
	}
}
