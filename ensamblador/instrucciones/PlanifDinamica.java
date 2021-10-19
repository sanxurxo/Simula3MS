package ensamblador.instrucciones;

import ensamblador.datos.Palabra;

public interface PlanifDinamica {

	public int ejecutarMarcador();

	public int ejecutarTomasulo(Palabra rs, Palabra rt, String estacion);
	    
	public Palabra WBTomasulo(String estacion);
}
