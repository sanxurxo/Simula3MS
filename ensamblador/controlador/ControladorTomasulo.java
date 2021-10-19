package ensamblador.controlador;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ensamblador.componentes.fus.estacionTomasulo.ERTomasulo;
import ensamblador.componentes.fus.estacionTomasulo.EstadoEstacion;
import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;

import ensamblador.planificacionDinamica.tomasulo.TomasuloProcesador;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.vista.VistaAlgoritmos;

public class ControladorTomasulo extends ControladorDinam{


	private Codigo instrucc;

	protected Vector estacionesReserva;
	
	public ControladorTomasulo(Vector estacionesReserva) {
		super();
		instrucc=Codigo.getInstacia();
		this.estacionesReserva=estacionesReserva;
		procesador=new TomasuloProcesador(instrucc, estacionesReserva);
	}
	

	
	
	public void anhadirObservador(VistaAlgoritmos observador){
		super.anhadirObservador(observador);
		procesador.registrarObservadores(observador);
	}
	
	
	public TableModel updateFUS(Vector fus, JTable tabla){
		ERTomasulo fu;
		DefaultTableModel model=(DefaultTableModel)tabla.getModel();
		for (int i=0;i<fus.size();i++){
			fu=(ERTomasulo)fus.elementAt(i);

			model.setValueAt(fu.getNombre(), i, 0);
			model.setValueAt(fu.getEstado().isOcupada(), i, 1);			
			model.setValueAt(((EstadoEstacion)fu.getEstado()).getOp(), i, 2);
			model.setValueAt(((EstadoEstacion)fu.getEstado()).getStringVj(), i ,3);
			model.setValueAt(((EstadoEstacion)fu.getEstado()).getStringVk(), i, 4);			
			model.setValueAt(((EstadoEstacion)fu.getEstado()).getQj(), i, 5);
			model.setValueAt(((EstadoEstacion)fu.getEstado()).getQk(), i, 6);
			model.setValueAt(((EstadoEstacion)fu.getEstado()).getA(), i, 7);
				
		}
		return model;		
	}

	
	public boolean ejecutarCiclo(Palabra breakpoint) throws EjecucionException{

		
	    long posPc=getDecimalPC();
	        	try{
	        		procesador.ejecutarCiclo(getDecimalPC(), breakpoint);
	        	}catch(EjecucionException e){
	        		throw e;
	    		}
	    		return true;

	}
	
}
