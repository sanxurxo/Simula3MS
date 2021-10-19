package ensamblador;

import javax.swing.*;
import java.util.StringTokenizer;
import java.util.regex.*;
import java.lang.*;

import ensamblador.datos.Lenguaje;

/**VentanaMulticiclo.java
 *Clase que controla el funcionamiento del dialogo de multiciclo 
 **/
public class VentanaMulticiclo extends JDialog {
    Ensamblador ens;
    JTextArea area;
    int entero1=1, entero2=1, entero3=1;
    Lenguaje lenguaje;
    
/**Constructor de del dialogo de Busqueda
 *@param Ensamblador padre, JTextArea editor, boolean modo, int opcion
 **/
   public VentanaMulticiclo(Ensamblador parent, JTextArea Editor, boolean modal) {        
       
        super(parent, modal);
        ens=(Ensamblador)parent;
        area=Editor;
        lenguaje = lenguaje.getInstancia();
        initComponents();    
        setSize(200, 200);
        Text1.requestFocus();
        Text2.requestFocus();
        Text3.requestFocus();
    }
    
   
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel1 = new javax.swing.JPanel();
        Label1 = new javax.swing.JLabel();
        Text1 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        Label2 = new javax.swing.JLabel();
        Text2 = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        Label3 = new javax.swing.JLabel();
        Text3 = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        BotonAceptar = new javax.swing.JButton();
        BotonCancelar = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.GridLayout(4, 1));

        setTitle(lenguaje.getString("MULTICICLO"));
        Label1.setText(lenguaje.getString("Suma/Resta"));
        jPanel1.add(Label1);

        Text1.setText("1");
        Text1.setPreferredSize(new java.awt.Dimension(30, 20));
        Text1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text1ActionPerformed(evt);
            }
        });

        jPanel1.add(Text1);

        getContentPane().add(jPanel1);

        Label2.setText(lenguaje.getString("Multiplicacion"));
        jPanel2.add(Label2);

        Text2.setText("1");
        Text2.setPreferredSize(new java.awt.Dimension(30, 20));
        Text2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text2ActionPerformed(evt);
            }
        });

        jPanel2.add(Text2);

        getContentPane().add(jPanel2);

        Label3.setText(lenguaje.getString("Division"));
        jPanel3.add(Label3);

        Text3.setText("1");
        Text3.setPreferredSize(new java.awt.Dimension(30, 20));
        Text3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Text3ActionPerformed(evt);
            }
        });

        jPanel3.add(Text3);

        getContentPane().add(jPanel3);

        BotonAceptar.setText(lenguaje.getString("aceptar"));
        BotonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAceptarActionPerformed(evt);
            }
        });

        jPanel4.add(BotonAceptar);

        BotonCancelar.setText(lenguaje.getString("cancelar"));
        BotonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCancelarActionPerformed(evt);
            }
        });

        jPanel4.add(BotonCancelar);

        getContentPane().add(jPanel4);

    }//GEN-END:initComponents

    private void Text3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text3ActionPerformed
        if (Text3.getText().trim().length()>0)
        {
            entero3 = Integer.valueOf(Text3.getText()).intValue();
            if(entero3<0 || entero3>20)
                ens.entero_tres=1;
            else
                ens.entero_tres=entero3;
        }
    }//GEN-LAST:event_Text3ActionPerformed

    private void Text2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text2ActionPerformed
        if (Text2.getText().trim().length()>0)
        {
            entero2 = Integer.valueOf(Text2.getText()).intValue();
            if(entero2<0 || entero2>20)
                ens.entero_dos=1;
            else
                ens.entero_dos=entero2;
        }
    }//GEN-LAST:event_Text2ActionPerformed

    private void Text1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Text1ActionPerformed
        if (Text1.getText().trim().length()>0)
        {
            entero1 = Integer.valueOf(Text1.getText()).intValue();
            if(entero1<0 || entero1>20)
                ens.entero_uno=1;
            else
                ens.entero_uno=entero1;
        }
    }//GEN-LAST:event_Text1ActionPerformed

    private void BotonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCancelarActionPerformed
        setVisible(false);
        dispose();    
    }//GEN-LAST:event_BotonCancelarActionPerformed

    private void BotonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAceptarActionPerformed
         setVisible(false);
         Text1ActionPerformed(evt);
         Text2ActionPerformed(evt);
         Text3ActionPerformed(evt);
    }//GEN-LAST:event_BotonAceptarActionPerformed
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonAceptar;
    private javax.swing.JButton BotonCancelar;
    private javax.swing.JLabel Label1;
    private javax.swing.JLabel Label2;
    private javax.swing.JLabel Label3;
    private javax.swing.JTextField Text1;
    private javax.swing.JTextField Text2;
    private javax.swing.JTextField Text3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration//GEN-END:variables
    
}
