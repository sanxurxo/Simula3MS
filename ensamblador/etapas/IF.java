package ensamblador.etapas;

import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Nop;
import ensamblador.unidadFuncional.EstadoInstruccion;

public class IF extends Etapa {
	 
	private EstadoInstruccion estadoInstruccion;
	private static IF etapa1 = null;
	
	private IF() {
		estadoInstruccion = new EstadoInstruccion(new Nop());
	}
	
	public static IF getInstancia()
    {
    	if(etapa1 == null){
    		etapa1 = new IF();
    	}
        return etapa1;
    }
	
	@Override
	public void inicializar() {
		estadoInstruccion = new EstadoInstruccion(new Nop());
	}
	
	public EstadoInstruccion getEstadoInstruccion() {
		return this.estadoInstruccion;
	}
	
	public void anhadirInstruccion(Instruccion inst) {
		this.estadoInstruccion = new EstadoInstruccion(inst);
	}
	
	@Override
	public int avanzar(Etapa etapa) {
		this.estadoInstruccion = ((IF)etapa).getEstadoInstruccion();
		return -1;
	}

	@Override
	public int ejecutar() {
		return estadoInstruccion.getInstruccion().ejecutarIF();
	}

	@Override
	public void actualizarEtapas() {
		if(estadoInstruccion.getEtapas().size()==0) {
			estadoInstruccion.anhadirEtapa("  IF");
		}
		else {
			estadoInstruccion.anhadirEtapa(" burb");
		}
	}
}
