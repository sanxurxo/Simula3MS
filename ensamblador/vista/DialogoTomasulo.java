/*
 * Marcador.java
 *
 * Created on 3 de diciembre de 2006, 19:08
 */

package ensamblador.vista;

import java.util.Vector;

import ensamblador.Ensamblador;
import ensamblador.datos.Tipos;
import ensamblador.datos.Lenguaje;

/**
 *
 * @author  rake
 */
public class DialogoTomasulo extends javax.swing.JDialog {
	private Ensamblador ensamblador;
	Lenguaje lenguaje;
	
    /** Creates new form Marcador */
    public DialogoTomasulo(java.awt.Frame parent, boolean modal) {
    	 super(parent, modal);
         this.ensamblador=(Ensamblador)parent;
         lenguaje = Lenguaje.getInstancia();
         this.ensamblador.setVisible(true);
         initComponents();
         setValoresErs(ensamblador);
         this.setVisible(true);
    }
    
    
    private void setValoresErs(Ensamblador ensamblador){
    	Vector<Vector> fus=new Vector<Vector>();
    	Vector<Integer> add=new Vector<Integer>();
    	Vector<Integer> mult=new Vector<Integer>();
    	Vector<Integer> div=new Vector<Integer>();
    	Vector<Integer> load=new Vector<Integer>();
    	Vector<Integer> store=new Vector<Integer>();
    	add.addElement(Tipos.FP_ADD_ER);
    	add.addElement(new Integer(comboAddFP.getSelectedItem().toString()));
    	add.addElement(new Integer(comboLatAdd.getSelectedItem().toString()));
    	mult.addElement(Tipos.FP_MULT_ER);
    	mult.addElement(new Integer(comboMultFP.getSelectedItem().toString()));
    	mult.addElement(new Integer(comboLatMult.getSelectedItem().toString()));
    	div.addElement(Tipos.FP_DIV_ER);
    	div.addElement(new Integer(comboDivFP.getSelectedItem().toString()));
    	div.addElement(new Integer(comboLatDiv.getSelectedItem().toString()));
    	load.addElement(Tipos.LOAD_ER);
    	load.addElement(new Integer(comboLoadFP.getSelectedItem().toString()));
    	load.addElement(new Integer(comboLatLoad.getSelectedItem().toString()));
    	store.addElement(Tipos.STORE_ER);
    	store.addElement(new Integer(comboStoreFP.getSelectedItem().toString()));
    	store.addElement(new Integer(comboLatStore.getSelectedItem().toString()));
    	
    	fus.addElement(add);
    	fus.addElement(mult);
    	fus.addElement(div);
    	fus.addElement(load);
    	fus.addElement(store);
    	ensamblador.setFus(fus);
    }
    
    
    private void initComponents() {
        labelAddFP = new javax.swing.JLabel();
        labelMultFP = new javax.swing.JLabel();
        labelDivFP = new javax.swing.JLabel();
        comboAddFP = new javax.swing.JComboBox();
        comboMultFP = new javax.swing.JComboBox();
        comboDivFP = new javax.swing.JComboBox();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        labelUnidades = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        comboLatAdd = new javax.swing.JComboBox();
        comboLatMult = new javax.swing.JComboBox();
        comboLatDiv = new javax.swing.JComboBox();
        labelLoadFP = new javax.swing.JLabel();
        labelStoreFP = new javax.swing.JLabel();
        comboLoadFP = new javax.swing.JComboBox();
        comboLatLoad = new javax.swing.JComboBox();
        comboStoreFP = new javax.swing.JComboBox();
        comboLatStore = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(lenguaje.getString("TOMASULO"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        labelAddFP.setText("Add FP");

        labelMultFP.setText("Mult FP");

        labelDivFP.setText("Div FP");

        comboAddFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        comboAddFP.setSelectedItem("2");
        
        comboMultFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        comboMultFP.setSelectedItem("2");
        
        comboDivFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));
        comboDivFP.setSelectedItem("1");
        
        botonAceptar.setText(lenguaje.getString("aceptar"));
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCancelar.setText(lenguaje.getString("cancelar"));
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });
        
        labelUnidades.setText(lenguaje.getString("unidades"));

        jLabel1.setText(lenguaje.getString("latencia"));

        comboLatAdd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        comboLatAdd.setSelectedItem("2");
        
        comboLatMult.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        comboLatMult.setSelectedItem("4");
        
        comboLatDiv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        comboLatDiv.setSelectedItem("7");
        
        labelLoadFP.setText("Load FP");

        labelStoreFP.setText("Store FP");

        comboLoadFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        comboLatLoad.setModel(new javax.swing.DefaultComboBoxModel(new String[] {"2"}));

        comboStoreFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4" }));

        comboLatStore.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1" }));

        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                    .add(botonAceptar)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(labelAddFP)
                            .add(labelMultFP)
                            .add(labelDivFP)
                            .add(labelLoadFP)
                            .add(labelStoreFP))
                        .add(33, 33, 33)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, comboStoreFP, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, comboLoadFP, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, comboMultFP, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, comboAddFP, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.TRAILING, comboDivFP, 0, 61, Short.MAX_VALUE)
                            .add(labelUnidades))))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(49, 49, 49)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(comboLatStore, 0, 62, Short.MAX_VALUE)
                            .add(comboLatLoad, 0, 62, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, jLabel1)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, comboLatDiv, 0, 62, Short.MAX_VALUE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, comboLatMult, 0, 62, Short.MAX_VALUE)
                            .add(comboLatAdd, 0, 62, Short.MAX_VALUE)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .add(24, 24, 24)
                        .add(botonCancelar)))
                .add(28, 28, 28))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelUnidades)
                    .add(jLabel1))
                .add(18, 18, 18)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelAddFP)
                    .add(comboAddFP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboLatAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelMultFP)
                    .add(comboMultFP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboLatMult, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelDivFP)
                    .add(comboDivFP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboLatDiv, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelLoadFP)
                    .add(comboLoadFP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboLatLoad, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(labelStoreFP)
                    .add(comboStoreFP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboLatStore, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .add(23, 23, 23)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(botonCancelar)
                    .add(botonAceptar))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        pack();
    }

    private void formWindowClosing(java.awt.event.WindowEvent evt) {

    }

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
    	setValoresErs(this.ensamblador);
    	this.setVisible(false);
    	this.setEnabled(false);    	
     	this.dispose();     	
    }
    
    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {    	
    	this.setVisible(false);
    	this.setEnabled(false);
    	this.dispose();
    	
    }

    
    
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JComboBox comboLatLoad;
    private javax.swing.JComboBox comboLatStore;
    private javax.swing.JComboBox comboLoadFP;
    private javax.swing.JComboBox comboStoreFP;
    private javax.swing.JComboBox comboAddFP;
    private javax.swing.JComboBox comboMultFP;
    private javax.swing.JComboBox comboDivFP;
    private javax.swing.JComboBox comboLatAdd;
    private javax.swing.JComboBox comboLatMult;
    private javax.swing.JComboBox comboLatDiv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelAddFP;
    private javax.swing.JLabel labelDivFP;
    private javax.swing.JLabel labelLoadFP;
    private javax.swing.JLabel labelMultFP;
    private javax.swing.JLabel labelStoreFP;
    private javax.swing.JLabel labelUnidades;

    
}
