package ensamblador.unidadFuncional;

import ensamblador.instrucciones.Nop;

public class UFuncionalSegm extends UFuncional {

	public UFuncionalSegm(int tipo, int latencia) {
		super(tipo, latencia);
	}
	
	
	@Override
	public boolean sePuedeInsertar() {
		if(instrucciones[0].getInstruccion().toString().equals("nop")) {
			return true;
		}
		return false;
	}

}
