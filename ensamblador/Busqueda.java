
package ensamblador;

import javax.swing.*;

import ensamblador.datos.Lenguaje;
import java.util.StringTokenizer;
import java.util.regex.*;
import java.lang.*;

/**Busqueda.java
 *Clase que controla el funcionamiento del dialogo de busqueda 
 **/
public class Busqueda extends JDialog {
    Ensamblador ens;
    JTextArea area;
    Lenguaje lenguaje;
    
/**Constructor de del dialogo de Busqueda
 *@param Ensamblador padre, JTextArea editor, boolean modo, int opcion
 **/
    public Busqueda(Ensamblador parent, JTextArea Editor, boolean modal, int opcion) {        
       
        super(parent, modal);
        ens=(Ensamblador)parent;
        area=Editor;
        lenguaje = Lenguaje.getInstancia();
        initComponents();    
        setSize(300, 280);
        switch (opcion)
        {
        case 1:
            PanelBuscar.setSelectedComponent(PanelBuscarLinea);
            TextLinea.requestFocus();
            break;
        case 2:
            PanelBuscar.setSelectedComponent(PanelBuscarTexto);   
            BuscarTexto.requestFocus();
            break;            
        case 3:
            PanelBuscar.setSelectedComponent(PanelReemplazar);
            TextBuscar.requestFocus();
            break;
        }
    }
        
    private void initComponents() {//GEN-BEGIN:initComponents
        PanelBuscar = new javax.swing.JTabbedPane();
        PanelBuscarLinea = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        TextLinea = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        LabelLinea = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        BotonBuscarLinea = new javax.swing.JButton();
        BotonCerrar = new javax.swing.JButton();
        PanelBuscarTexto = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        BuscarTexto = new javax.swing.JTextField();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        LabelTexto = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        BotonBSiguienteT = new javax.swing.JButton();
        BotonCerrarT = new javax.swing.JButton();
        PanelReemplazar = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        jPanel25 = new javax.swing.JPanel();
        jPanel26 = new javax.swing.JPanel();
        TextBuscar = new javax.swing.JTextField();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        LabelBuscar = new javax.swing.JLabel();
        jPanel31 = new javax.swing.JPanel();
        jPanel32 = new javax.swing.JPanel();
        TextReemplazar = new javax.swing.JTextField();
        jPanel33 = new javax.swing.JPanel();
        jPanel34 = new javax.swing.JPanel();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        LabelReemplazar = new javax.swing.JLabel();
        jPanel22 = new javax.swing.JPanel();
        BotonBuscarS = new javax.swing.JButton();
        BotonReemplazar = new javax.swing.JButton();
        jPanel23 = new javax.swing.JPanel();
        BotonReemTodos = new javax.swing.JButton();
        BotonCerrarR = new javax.swing.JButton();

        getContentPane().setLayout(new java.awt.CardLayout());

        setTitle(lenguaje.getString("busqueda"));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        PanelBuscar.setTabLayoutPolicy(javax.swing.JTabbedPane.SCROLL_TAB_LAYOUT);
        PanelBuscar.setFocusCycleRoot(true);
        PanelBuscarLinea.setLayout(new java.awt.GridLayout(4, 0));

        PanelBuscarLinea.add(jPanel1);

        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        TextLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextLineaActionPerformed(evt);
            }
        });

        jPanel6.add(TextLinea);

        jPanel2.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel2.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel2.add(jPanel9, java.awt.BorderLayout.EAST);

        LabelLinea.setText(lenguaje.getString("linea2"));
        jPanel10.add(LabelLinea);

        jPanel2.add(jPanel10, java.awt.BorderLayout.WEST);

        PanelBuscarLinea.add(jPanel2);

        PanelBuscarLinea.add(jPanel3);

        BotonBuscarLinea.setText(lenguaje.getString("buscar3"));
        BotonBuscarLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarLineaActionPerformed(evt);
            }
        });

        jPanel4.add(BotonBuscarLinea);

        BotonCerrar.setText(lenguaje.getString("cerrar2"));
        BotonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarActionPerformed(evt);
            }
        });

        jPanel4.add(BotonCerrar);

        PanelBuscarLinea.add(jPanel4);

        PanelBuscar.addTab(lenguaje.getString("buscarLinea"), PanelBuscarLinea);

        PanelBuscarTexto.setLayout(new java.awt.GridLayout(4, 0));

        PanelBuscarTexto.add(jPanel5);

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        BuscarTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BuscarTextoActionPerformed(evt);
            }
        });

        jPanel12.add(BuscarTexto);

        jPanel11.add(jPanel12, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel13, java.awt.BorderLayout.NORTH);

        jPanel11.add(jPanel14, java.awt.BorderLayout.SOUTH);

        jPanel11.add(jPanel15, java.awt.BorderLayout.EAST);

        LabelTexto.setText(lenguaje.getString("texto2"));
        jPanel16.add(LabelTexto);

        jPanel11.add(jPanel16, java.awt.BorderLayout.WEST);

        PanelBuscarTexto.add(jPanel11);

        PanelBuscarTexto.add(jPanel17);

        BotonBSiguienteT.setText(lenguaje.getString("buscarSiguiente"));
        BotonBSiguienteT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBSiguienteTActionPerformed(evt);
            }
        });

        jPanel18.add(BotonBSiguienteT);

        BotonCerrarT.setText(lenguaje.getString("cerrar2"));
        BotonCerrarT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarActionPerformed(evt);
            }
        });

        jPanel18.add(BotonCerrarT);

        PanelBuscarTexto.add(jPanel18);

        PanelBuscar.addTab(lenguaje.getString("buscarTexto"), PanelBuscarTexto);

        PanelReemplazar.setLayout(new java.awt.GridLayout(5, 0));

        PanelReemplazar.add(jPanel19);

        jPanel25.setLayout(new java.awt.BorderLayout());

        jPanel26.setLayout(new java.awt.GridLayout(1, 0));

        TextBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextBuscarActionPerformed(evt);
            }
        });

        jPanel26.add(TextBuscar);

        jPanel25.add(jPanel26, java.awt.BorderLayout.CENTER);

        jPanel25.add(jPanel27, java.awt.BorderLayout.NORTH);

        jPanel25.add(jPanel28, java.awt.BorderLayout.SOUTH);

        jPanel25.add(jPanel29, java.awt.BorderLayout.EAST);

        LabelBuscar.setText(lenguaje.getString("buscar2"));
        jPanel30.add(LabelBuscar);

        jPanel25.add(jPanel30, java.awt.BorderLayout.WEST);

        PanelReemplazar.add(jPanel25);

        jPanel31.setLayout(new java.awt.BorderLayout());

        jPanel32.setLayout(new java.awt.GridLayout(1, 0));

        TextReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TextReemplazarActionPerformed(evt);
            }
        });

        jPanel32.add(TextReemplazar);

        jPanel31.add(jPanel32, java.awt.BorderLayout.CENTER);

        jPanel31.add(jPanel33, java.awt.BorderLayout.NORTH);

        jPanel31.add(jPanel34, java.awt.BorderLayout.SOUTH);

        jPanel31.add(jPanel35, java.awt.BorderLayout.EAST);

        LabelReemplazar.setText(lenguaje.getString("reemplazar2"));
        jPanel36.add(LabelReemplazar);

        jPanel31.add(jPanel36, java.awt.BorderLayout.WEST);

        PanelReemplazar.add(jPanel31);

        BotonBuscarS.setText(lenguaje.getString("buscarSiguiente"));
        BotonBuscarS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonBuscarSActionPerformed(evt);
            }
        });

        jPanel22.add(BotonBuscarS);

        BotonReemplazar.setText(lenguaje.getString("reemplazar"));
        BotonReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonReemplazarActionPerformed(evt);
            }
        });

        jPanel22.add(BotonReemplazar);

        PanelReemplazar.add(jPanel22);

        BotonReemTodos.setText(lenguaje.getString("reemplazarTodos"));
        BotonReemTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonReemTodosActionPerformed(evt);
            }
        });

        jPanel23.add(BotonReemTodos);

        BotonCerrarR.setText(lenguaje.getString("cerrar2"));
        BotonCerrarR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonCerrarActionPerformed(evt);
            }
        });

        jPanel23.add(BotonCerrarR);

        PanelReemplazar.add(jPanel23);

        PanelBuscar.addTab(lenguaje.getString("reemplazar"), PanelReemplazar);

        getContentPane().add(PanelBuscar, "card2");

        pack();
    }//GEN-END:initComponents

 /**Metodo para el escuchador del boton de buscar linea
 *@param event evento
 *@return void
 **/    
    private void BotonBuscarLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarLineaActionPerformed
       int posicion=irLinea(new Integer(TextLinea.getText()).intValue()); 
        ens.Editor.setFocusable(true);
        ens.setFocusableWindowState(true);
        ens.Editor.requestFocus();
        ens.Editor.select(posicion, posicion);        
    }//GEN-LAST:event_BotonBuscarLineaActionPerformed

/**Metodo para el escuchador del item nuevo
 *@param event evento
 *@return void
 **/    
    private void TextLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextLineaActionPerformed
           if (TextLinea.getText().trim().length()>0)
            BotonBuscarLinea.doClick();
    }//GEN-LAST:event_TextLineaActionPerformed

/**Metodo para el escuchador del boton cerrar
 *@param event evento
 *@return void
 **/    
    private void BotonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonCerrarActionPerformed
        setVisible(false);
        dispose();    
    }//GEN-LAST:event_BotonCerrarActionPerformed

/**Metodo para el escuchador del boton reemplazar todos
 *@param event evento
 *@return void
 **/    
    private void BotonReemTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonReemTodosActionPerformed
        int pos=1;
        while (pos !=-1) 
        {
           pos = BuscarTexto(TextBuscar.getText());
            
            BotonReemplazar.doClick();            
               
        }
    }//GEN-LAST:event_BotonReemTodosActionPerformed

/**Metodo para el escuchador del boton reemplazar texto
 *@param event evento
 *@return void
 **/    
    private void TextReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextReemplazarActionPerformed
            if (TextReemplazar.getText().trim().length()>0)
            BotonBuscarS.doClick();
    }//GEN-LAST:event_TextReemplazarActionPerformed

/**Metodo para el escuchador del boton buscar texto
 *@param event evento
 *@return void
 **/    
    private void TextBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TextBuscarActionPerformed
          if (TextBuscar.getText().trim().length()>0)
            BotonBuscarS.doClick();
    }//GEN-LAST:event_TextBuscarActionPerformed

/**Metodo para el escuchador del boton cerrar
 *@param event evento
 *@return void
 **/    
    private void BuscarTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BuscarTextoActionPerformed
         if (BuscarTexto.getText().trim().length()>0)
            BotonBSiguienteT.doClick();
    }//GEN-LAST:event_BuscarTextoActionPerformed

/**Metodo para el escuchador del boton reemplazar
 *@param event evento
 *@return void
 **/    
    private void BotonReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonReemplazarActionPerformed
        int principio=ens.Editor.getSelectionStart();
        int fin=ens.Editor.getSelectionEnd();
        if (fin > principio)
            ens.Editor.replaceRange(TextReemplazar.getText(), principio, fin);
        ens.Editor.select(principio, principio+TextReemplazar.getText().length());
    }//GEN-LAST:event_BotonReemplazarActionPerformed

/**Metodo para el escuchador del boton buscar siguiente
 *@param event evento
 *@return void
 **/    
    private void BotonBuscarSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBuscarSActionPerformed
        BuscarTexto(TextBuscar.getText());
    }//GEN-LAST:event_BotonBuscarSActionPerformed

/**Metodo para el escuchador del boton buscar siguiente
 *@param event evento
 *@return void
 **/    
    private void BotonBSiguienteTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonBSiguienteTActionPerformed
        BuscarTexto(BuscarTexto.getText());
    }//GEN-LAST:event_BotonBSiguienteTActionPerformed
        
/**Metodo para el escuchador de cerrar el dialogo
 *@param event evento
 *@return void
 **/    
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
       BotonCerrar.doClick();
    }//GEN-LAST:event_closeDialog
    
/**Metodo que busca un subtexto
 *@param String subtexto
 *@return int posicion de inicio del subtexto en un texto
 **/    
    public int BuscarTexto(String textoBuscado)
    {
        String txt, str;      
        txt=ens.Editor.getText();
        str=textoBuscado;
        ens.Editor.requestFocus();
        int sel;
        if (ens.Editor.getSelectionStart() != ens.Editor.getSelectionEnd())
        {            
            sel = area.getSelectionStart()+1;
        }
        else
            sel=0;
        int pos=txt.indexOf(str, sel);
        if (pos>0)
        {
            area.select(pos, pos + str.length());      
            ens.Editor.select(pos, pos + str.length());
        }
        return pos;
    }
    
/**Metodo para ir a una linea
 *@param int numero de linea
 *@return int posicion en un texto
 **/    
    public int irLinea(int linea)
    {
        int pos=0;
        String lineas[]= (ens.Editor.getText()).split("\\n");
        
        for(int z=0;(z<linea-1 && z<lineas.length); z++)        
             pos += (lineas[z]).length() + 1; 
        
        return pos;
    }
    
   
     // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BotonBSiguienteT;
    private javax.swing.JButton BotonBuscarLinea;
    private javax.swing.JButton BotonBuscarS;
    private javax.swing.JButton BotonCerrar;
    private javax.swing.JButton BotonCerrarR;
    private javax.swing.JButton BotonCerrarT;
    private javax.swing.JButton BotonReemTodos;
    private javax.swing.JButton BotonReemplazar;
    private javax.swing.JTextField BuscarTexto;
    private javax.swing.JLabel LabelBuscar;
    private javax.swing.JLabel LabelLinea;
    private javax.swing.JLabel LabelReemplazar;
    private javax.swing.JLabel LabelTexto;
    private javax.swing.JTabbedPane PanelBuscar;
    private javax.swing.JPanel PanelBuscarLinea;
    private javax.swing.JPanel PanelBuscarTexto;
    private javax.swing.JPanel PanelReemplazar;
    private javax.swing.JTextField TextBuscar;
    private javax.swing.JTextField TextLinea;
    private javax.swing.JTextField TextReemplazar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration
   //GEN-END:variables
    
}
