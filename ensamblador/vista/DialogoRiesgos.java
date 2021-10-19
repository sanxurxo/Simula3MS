/*
 * Marcador.java
 *
 * Created on 3 de diciembre de 2006, 19:08
 */

package ensamblador.vista;


/**
 *
 * @author  rake
 */
public class DialogoRiesgos extends javax.swing.JDialog {
    
	
    public DialogoRiesgos(java.awt.Frame parent, boolean modal, String riesgos) {
        super(parent, modal);
        initComponents();
        labelRiesgos.setText(riesgos);
     
    }
    
    
    private void initComponents() {
        labelRiesgos = new javax.swing.JLabel();
        botonAceptar = new javax.swing.JButton();
        panel=new javax.swing.JPanel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Riesgos");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });


          
          this.add(panel);
          panel.setLayout(new javax.swing.BoxLayout(panel, javax.swing.BoxLayout.Y_AXIS));
          panel.add(labelRiesgos);
          panel.add(botonAceptar);
          this.setSize(250, 150);
          pack();
          
    }
    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {
    
    	this.setVisible(false);
        	
     	this.dispose();     	
    }

    
    private javax.swing.JLabel labelRiesgos;
    private javax.swing.JButton botonAceptar;
    private javax.swing.JPanel panel;

    
}
