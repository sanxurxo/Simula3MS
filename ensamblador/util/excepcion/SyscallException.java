package ensamblador.util.excepcion;

public class SyscallException extends EjecucionException {

	private String texto;
	public SyscallException(int tipo, String texto) {
		super(tipo);
		this.texto=texto;
		// TODO Auto-generated constructor stub
	}
	
	public String imprimir(){
		return this.texto;
	}

}
