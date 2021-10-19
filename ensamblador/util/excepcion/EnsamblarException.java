package ensamblador.util.excepcion;

public class EnsamblarException extends ModelException {

	String mensaje;
	
	public EnsamblarException(String mensaje){
		this.mensaje=mensaje;
	}

	public String getMensaje(){
		return this.mensaje;
	}
	
}
