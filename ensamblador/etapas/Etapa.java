package ensamblador.etapas;


public abstract class Etapa {
	
	public abstract void inicializar();
	
	public abstract int avanzar(Etapa etapa);
	
	public abstract int ejecutar();
	
	public abstract void actualizarEtapas();

}
