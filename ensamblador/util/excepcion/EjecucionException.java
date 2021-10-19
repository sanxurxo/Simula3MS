package ensamblador.util.excepcion;

public class EjecucionException extends ModelException {

	private int tipo;
	public EjecucionException(int tipo) {
		super();
		this.tipo=tipo;
	}
	
	public int getTipo() {
		return tipo;
	}	
}
