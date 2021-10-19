package ensamblador.unidadFuncional;

public class UFuncionalNoSegm extends UFuncional {

	public UFuncionalNoSegm(int tipo, int latencia) {
		super(tipo, latencia);
	}
	
	
	@Override
	public boolean sePuedeInsertar() {
		if(this.estaVacia()) {
			return true;
		}
		return false;
	}

}
