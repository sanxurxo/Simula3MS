package ensamblador.componentes.fus.fusMarcador;

public class FUOcupadaMarcador extends EstadoFUMarcador {
	
	public FUOcupadaMarcador(String nombre) {
		super(nombre);
		ocupada=true;
	
	}
	public boolean getOcupada(){
		return true;
	}
	
}