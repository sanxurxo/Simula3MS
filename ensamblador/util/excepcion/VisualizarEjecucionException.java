package ensamblador.util.excepcion;

public class VisualizarEjecucionException extends EjecucionException {

	private int tipo;
	private String pcRutina;
	public VisualizarEjecucionException(int tipo, String pcRutina) {
		super(tipo);
		this.tipo=tipo;
		this.pcRutina=pcRutina;
		
	}
	
	public String getPc(){
		return this.pcRutina;
	}
	
}
