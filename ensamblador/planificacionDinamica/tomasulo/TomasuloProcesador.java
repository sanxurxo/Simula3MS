package ensamblador.planificacionDinamica.tomasulo;

import java.util.Vector;

import ensamblador.datos.Codigo;
import ensamblador.estado.Riesgos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.planificacionDinamica.CoprocesadorFP;
import ensamblador.planificacionDinamica.Procesador;
import ensamblador.planificacionDinamica.tomasulo.actions.AvanzarCicloAccion;
import ensamblador.util.action.Accion;

public class TomasuloProcesador extends Procesador{
	
	private Codigo instrucciones;


	private Vector ERs;
	public TomasuloProcesador(Codigo instrucciones, Vector ERs) {
		super(instrucciones);

		this.ERs=ERs;
		this.coProcFP = new Tomasulo(this.ERs);	
		
	}
	
	public void inicializar(){
		this.riesgos=Riesgos.getRiesgos();		
		this.riesgos.inicializar();
		
		this.coProcFP=new Tomasulo(this.ERs);
		super.inicializar();
		
	}
	
	
	protected Accion getAccion(long pcActual, Instruccion instruccion, CoprocesadorFP coprocFP) {
		AvanzarCicloAccion avanzarCiclo=new AvanzarCicloAccion(pcActual, instruccion, (Tomasulo)coprocFP);
		return avanzarCiclo;
	}
	
}
