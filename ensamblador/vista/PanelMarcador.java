package ensamblador.vista;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import ensamblador.componentes.fus.fusMarcador.EstadoFUMarcador;

public class PanelMarcador extends PanelAlgoritmo {
	private int numFUs;
	public PanelMarcador(int numFUs) {
		this.numFUs=numFUs;
		initComponentes();
		
	}
	
	
	private void initComponentes(){
		
		
		
		
		tableInstrucciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		
		DefaultTableModel modeloInstrucciones=new javax.swing.table.DefaultTableModel(
				new Object [3][5],
				new String [] {
						lenguaje.getString("instruccion"), lenguaje.getString("emision"), lenguaje.getString("lectura"), 
						lenguaje.getString("ejecucion"), lenguaje.getString("escritura")
				}
		) {
			Class[] types = new Class [] {
					java.lang.String.class, Boolean.class, Boolean.class, Boolean.class, Boolean.class
			};
			boolean[] canEdit = new boolean [] {
					false, false, false, false, false
			};
			
			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		};	        
		
		tableInstrucciones.setModel(modeloInstrucciones);
		
		TableColumnModel columnModel=tableInstrucciones.getColumnModel();
		TableColumn column=columnModel.getColumn(0);
		column.setMinWidth(250);
		
		
		tableFUs.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		DefaultTableModel modeloFUs=new javax.swing.table.DefaultTableModel(        
				new Object [this.numFUs][10],
				new String [] {
						lenguaje.getString("nombre"), lenguaje.getString("ocupada"), lenguaje.getString("operacion"), "Fi", "Fj", "Fk", "Qj", "Qk", "Rj", "Rk"
				}
		) {
			Class[] types = new Class [] {
					java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Boolean.class, java.lang.Boolean.class
			};
			boolean[] canEdit = new boolean [] {
					false, false, false, false, false, false, false, false, false, false
			};
			
			public Class getColumnClass(int columnIndex) {
				return types [columnIndex];
			}
			
			public boolean isCellEditable(int rowIndex, int columnIndex) {
				return canEdit [columnIndex];
			}
		};      
		tableFUs.setModel(modeloFUs);
		TableColumnModel modeloColumna=tableFUs.getColumnModel();
		TableColumn columna1=modeloColumna.getColumn(1);
		columna1.setMaxWidth(70);    
		
		TableColumn columna3=modeloColumna.getColumn(3);
		columna3.setMaxWidth(60);
		
		TableColumn columna4=modeloColumna.getColumn(4);
		columna4.setMaxWidth(60);
		
		TableColumn columna5=modeloColumna.getColumn(5);
		columna5.setMaxWidth(60);
		
		TableColumn columna6=modeloColumna.getColumn(6);
		columna6.setMaxWidth(60);
		
		TableColumn columna7=modeloColumna.getColumn(7);
		columna7.setMaxWidth(60);
		
		TableColumn columna8=modeloColumna.getColumn(8);
		columna8.setMaxWidth(50);
		
		TableColumn columna9=modeloColumna.getColumn(9);
		columna9.setMaxWidth(50);
		jScrollPane5.setViewportView(tableFUs);
		
				
		
		jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), lenguaje.getString("estadoFU")));
	}
	

	public void inicializarTablas() {
		JTable fu=this.getTableFUs();
		TableModel modelo=fu.getModel();
		for(int i=0;i<modelo.getRowCount();i++){
			modelo.setValueAt(new String(), i, 0);
			modelo.setValueAt(Boolean.FALSE, i, 1);			
			modelo.setValueAt(new String(), i, 2);
			modelo.setValueAt(new String(), i, 3);
			modelo.setValueAt(new String(), i, 4);
			modelo.setValueAt(new String(), i, 5);
			modelo.setValueAt(new String(), i, 6);
			modelo.setValueAt(new String(), i, 7);
			
			modelo.setValueAt(Boolean.FALSE, i, 8);
			modelo.setValueAt(Boolean.FALSE, i, 9);
		}
	}
}
