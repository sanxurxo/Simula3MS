package ensamblador.unidadFuncional;

import ensamblador.instrucciones.Nop;

public class UFuncionalEntera extends UFuncional {

	public UFuncionalEntera(int tipo, int latencia) {
		super(tipo, latencia);
	}
	
	
	public void avanzarCiclo() {
		instrucciones[0] = new EstadoInstruccion(new Nop());
	}
	
	@Override
	public boolean sePuedeInsertar() {
		if(instrucciones[0].getInstruccion().toString().equals("nop")) {
			return true;
		}
		return false;
	}

}
