/*
 * Marcador.java
 *
 * Created on 3 de diciembre de 2006, 19:08
 */

package ensamblador.vista;

import java.util.Vector;
import ensamblador.Ensamblador;
import ensamblador.datos.Lenguaje;

/**
 *
 * @author  rake
 */
public class DialogoMarcador extends javax.swing.JDialog {
    
	private Ensamblador ensamblador;
	private Lenguaje lenguaje;
	
    
    public DialogoMarcador(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        this.ensamblador=(Ensamblador)parent;
        lenguaje = Lenguaje.getInstancia();
        this.ensamblador.setVisible(true);
        initComponents();
        setValoresFus(ensamblador);        
        this.setVisible(true);     
    }
    
    private void setValoresFus(Ensamblador ensamblador){
    	Vector<Vector> fus=new Vector<Vector>();
    	Vector<Integer> entero=new Vector<Integer>();
    	Vector<Integer> add=new Vector<Integer>();
    	Vector<Integer> mult=new Vector<Integer>();
    	Vector<Integer> div=new Vector<Integer>();
    	entero.addElement(new Integer(comboInt.getSelectedItem().toString()));
    	entero.addElement(new Integer(comboLatInt.getSelectedItem().toString()));
    	add.addElement(new Integer(comboAddFP.getSelectedItem().toString()));
    	add.addElement(new Integer(comboLatAdd.getSelectedItem().toString()));
    	mult.addElement(new Integer(comboMultFP.getSelectedItem().toString()));
    	mult.addElement(new Integer(comboLatMult.getSelectedItem().toString()));
    	div.addElement(new Integer(comboDivFP.getSelectedItem().toString()));
    	div.addElement(new Integer(comboLatDiv.getSelectedItem().toString()));
    	fus.addElement(entero);
    	fus.addElement(add);
    	fus.addElement(mult);
    	fus.addElement(div);
    	ensamblador.setFus(fus);
    }
    
    // <editor-fold defaultstate="collapsed" desc=" Generated Code ">//GEN-BEGIN:initComponents
    private void initComponents() {
    	labelInt = new javax.swing.JLabel();
        labelAddFP = new javax.swing.JLabel();
        labelMultFP = new javax.swing.JLabel();
        labelDivFP = new javax.swing.JLabel();
        comboInt = new javax.swing.JComboBox();
        comboAddFP = new javax.swing.JComboBox();
        comboMultFP = new javax.swing.JComboBox();
        comboDivFP = new javax.swing.JComboBox();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();
        labelUnidades = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();       
        comboLatInt = new javax.swing.JComboBox();
        comboLatAdd = new javax.swing.JComboBox();
        comboLatMult = new javax.swing.JComboBox();
        comboLatDiv = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle(lenguaje.getString("MARCADOR"));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        
        labelInt.setText("Int");
        
        labelAddFP.setText("Add FP");

        labelMultFP.setText("Mult FP");

        labelDivFP.setText("Div FP");

        comboInt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2"}));
        comboInt.setSelectedItem("1");
        
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

        comboLatInt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1"}));
        
        comboLatAdd.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        comboLatAdd.setSelectedItem("2");
        
        comboLatMult.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        comboLatMult.setSelectedItem("4");
        
        comboLatDiv.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        comboLatDiv.setSelectedItem("7");
        
        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .addContainerGap()
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                        	.add(labelInt)
                            .add(labelAddFP)
                            .add(labelMultFP)
                            .add(labelDivFP))
                        .add(37, 37, 37)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                        	.add(comboInt, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        	.add(comboAddFP, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .add(comboMultFP, 0, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)                            
                            .add(comboDivFP, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 61, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(org.jdesktop.layout.GroupLayout.LEADING, labelUnidades)))
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 36, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .add(botonAceptar)
                        .add(27, 27, 27)))
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(layout.createSequentialGroup()
                        .add(49, 49, 49)
                        .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                            .add(jLabel1)
                            .add(comboLatDiv, 0, 62, Short.MAX_VALUE)
                            .add(comboLatMult, 0, 62, Short.MAX_VALUE)
                            .add(comboLatInt, 0, 62, Short.MAX_VALUE)
                            .add(comboLatAdd, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 62, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                    .add(layout.createSequentialGroup()
                        .add(13, 13, 13)
                        .add(botonCancelar)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 11, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .add(37, 37, 37))
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
                    .add(labelInt)
                    .add(comboInt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(comboLatInt, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
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
                .add(24, 24, 24)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(botonAceptar)
                    .add(botonCancelar))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        pack();
    }

    
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
    	setValoresFus(this.ensamblador);
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
    private javax.swing.JComboBox comboInt;
    private javax.swing.JComboBox comboAddFP;
    private javax.swing.JComboBox comboMultFP;
    private javax.swing.JComboBox comboDivFP;
    private javax.swing.JComboBox comboLatInt;
    private javax.swing.JComboBox comboLatAdd;    
    private javax.swing.JComboBox comboLatMult;
    private javax.swing.JComboBox comboLatDiv;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel labelInt;
    private javax.swing.JLabel labelAddFP;
    private javax.swing.JLabel labelDivFP;
    private javax.swing.JLabel labelMultFP;
    private javax.swing.JLabel labelUnidades;
 
    
}
