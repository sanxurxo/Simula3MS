package ensamblador.planificacionDinamica.marcador;

import java.util.Vector;

import ensamblador.datos.Codigo;
import ensamblador.estado.Riesgos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.planificacionDinamica.CoprocesadorFP;
import ensamblador.planificacionDinamica.Procesador;
import ensamblador.planificacionDinamica.marcador.actions.AvanzarCicloAccion;
import ensamblador.util.action.Accion;

public class MarcadorProcesador extends Procesador{
	
	private Vector FUs;
	
	
	public MarcadorProcesador(Codigo instrucciones, Vector FUs) {
		super(instrucciones);
		this.FUs=FUs;
		this.coProcFP = new Marcador(this.FUs);	/*Hay q crear las FU, obtener los Estado de FU e inicializar el vector*/
	
		
	}
	
	public void inicializar(){
		this.riesgos=Riesgos.getRiesgos();		
		this.riesgos.inicializar();
		this.coProcFP= new Marcador(this.FUs);
		super.inicializar();
	}
	
	

	protected Accion getAccion(long pcActual, Instruccion instruccion, CoprocesadorFP coprocFP){
		AvanzarCicloAccion avanzarCiclo=new AvanzarCicloAccion(pcActual, instruccion, (Marcador)coprocFP);
		return avanzarCiclo;
	}
	

}
