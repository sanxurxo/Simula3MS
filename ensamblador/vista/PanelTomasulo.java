package ensamblador.vista;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;



public class PanelTomasulo extends PanelAlgoritmo {
	private int numERs;
	
	public PanelTomasulo(int numERs) {

		this.numERs=numERs;
		initComponentes();

	}

	
	private void initComponentes(){
	

		  tableInstrucciones.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
		  DefaultTableModel modeloInstrucciones=new javax.swing.table.DefaultTableModel(
		            new Object [3][4],
		            new String [] {	
		            		lenguaje.getString("instruccion"), lenguaje.getString("emision"), 
							lenguaje.getString("ejecucion"), lenguaje.getString("escritura")	                
	            }
	        ) {
	            Class[] types = new Class [] {
	                java.lang.String.class, Boolean.class, Boolean.class, Boolean.class
	            };
	            boolean[] canEdit = new boolean [] {
	                false, false, false, false
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
            new Object [this.numERs][8],
            new String [] {
                lenguaje.getString("nombre"), lenguaje.getString("ocupada"), lenguaje.getString("operacion"), "Vj", "Vk", "Qj", "Qk", "A"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Boolean.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
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
        columna1.setMaxWidth(60);
        columna1.setMinWidth(60);
        
        TableColumn columna2=modeloColumna.getColumn(2);
        columna2.setMaxWidth(80);
        columna2.setMinWidth(80);
        
        TableColumn columna3=modeloColumna.getColumn(3);
        columna3.setMinWidth(90);
        TableColumn columna4=modeloColumna.getColumn(4);
        columna4.setMinWidth(90);
        TableColumn columna7=modeloColumna.getColumn(7);
        columna7.setMinWidth(90);
        
        jScrollPane5.setViewportView(tableFUs);


        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createCompoundBorder(), lenguaje.getString("estadoER")));        
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
		}
				
	}
	   
}
