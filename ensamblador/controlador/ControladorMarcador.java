package ensamblador.controlador;

import java.util.Vector;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ensamblador.componentes.fus.fusMarcador.EstadoFUMarcador;
import ensamblador.componentes.fus.fusMarcador.FUMarcador;
import ensamblador.datos.Codigo;
import ensamblador.datos.Palabra;

import ensamblador.planificacionDinamica.marcador.MarcadorProcesador;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.vista.VistaAlgoritmos;

public class ControladorMarcador extends ControladorDinam {


	protected Codigo instrucc;
	protected Vector fus;
	public ControladorMarcador(Vector fus) {
		super();
		instrucc=Codigo.getInstacia();
		this.fus=fus;
		procesador=new MarcadorProcesador(instrucc, fus);
	}


	
	public void anhadirObservador(VistaAlgoritmos observador){

		super.anhadirObservador(observador);
		procesador.registrarObservadores(observador);
	}
	
	
	
	public TableModel updateFUS(Vector fus, JTable tabla){
		FUMarcador fu;

		DefaultTableModel model=(DefaultTableModel)tabla.getModel();

		for (int i=0;i<fus.size();i++){
			fu=(FUMarcador)fus.elementAt(i);

			model.setValueAt(fu.getNombre(), i, 0);
			model.setValueAt(fu.getEstado().isOcupada(), i, 1);			
			model.setValueAt(((EstadoFUMarcador)fu.getEstado()).getOp(), i, 2);
			model.setValueAt(((EstadoFUMarcador)fu.getEstado()).getFi(), i ,3);
			model.setValueAt(((EstadoFUMarcador)fu.getEstado()).getFj(), i, 4);
			model.setValueAt(((EstadoFUMarcador)fu.getEstado()).getFk(), i, 5);
			model.setValueAt(((EstadoFUMarcador)fu.getEstado()).getQj(), i, 6);
			model.setValueAt(((EstadoFUMarcador)fu.getEstado()).getQk(), i, 7);
			if(((EstadoFUMarcador)fu.getEstado()).isRj()){
				model.setValueAt(Boolean.TRUE, i, 8);
			}else{
				model.setValueAt(Boolean.FALSE, i, 8);
			}
			if(((EstadoFUMarcador)fu.getEstado()).isRk()){
				model.setValueAt(Boolean.TRUE, i, 9);
			}else{
				model.setValueAt(Boolean.FALSE, i, 9);
			}

			
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
