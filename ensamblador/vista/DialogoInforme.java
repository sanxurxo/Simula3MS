/*
 * DialogInforme.java
 *
 * Created on 2 de enero de 2007, 12:22
 */

package ensamblador.vista;

import java.awt.Dimension;
import java.awt.FileDialog;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

import ChartDirector.Chart;
import ChartDirector.ChartViewer;
import ChartDirector.PieChart;
import ChartDirector.TextBox;
import ensamblador.estado.Riesgos;
import ensamblador.datos.Lenguaje;

/**
 *
 * @author  rake
 */
public class DialogoInforme extends javax.swing.JDialog {
	private Riesgos riesgos;
	private Lenguaje lenguaje;
 	ChartViewer viewer =null;
	private Image laImagen;
	private Image imagen;
    
    public DialogoInforme(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        lenguaje = Lenguaje.getInstancia();
        this.riesgos=Riesgos.getRiesgos();
        initComponents();       
        this.setVisible(true);

        
    }
    
    private void initComponents() {
    	jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        botonGuardar = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        botonCerrar = new javax.swing.JButton();
        l=new JLabel();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        jPanel1.setLayout(new java.awt.GridLayout());

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel3.setLayout(new javax.swing.BoxLayout(jPanel3, javax.swing.BoxLayout.X_AXIS));

        botonGuardar.setText(lenguaje.getString("guardar"));

        org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(183, Short.MAX_VALUE)
                .add(botonGuardar)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(49, Short.MAX_VALUE)
                .add(botonGuardar)
                .add(47, 47, 47))
        );
        jPanel3.add(jPanel5);

        botonCerrar.setText(lenguaje.getString("cerrar"));

        org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .add(botonCerrar)
                .addContainerGap(184, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(50, Short.MAX_VALUE)
                .add(botonCerrar)
                .add(46, 46, 46))
        );
        jPanel3.add(jPanel6);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);
     
        
        
        
        imagen = createChart(0, riesgos.toStringConfiguracion(), riesgos.toStringCPI(), riesgos.toStringResumen());
    
    
        l.setIcon(new ImageIcon(this.getLaImagen()));
        jPanel1.add(l);
        botonGuardar.setText(lenguaje.getString("guardar"));
        botonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonGuardarActionPerformed(evt);
            }
        });
        
        botonCerrar.setText(lenguaje.getString("cerrar"));
        botonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCerrarActionPerformed(evt);
            }
        });
        
        
   
        this.setMaximumSize(new Dimension(710,530));
        this.setPreferredSize(new Dimension(710,530));
        this.setMinimumSize(new Dimension(710,530));
        
    	pack();
    }
    
    
    
    public String toString() { return lenguaje.getString("informe"); }
    
    public int getNoOfCharts() { return 1; }

    
    public Image createChart(int index, String config, String cpi, String resumen)
    {
    	
    	ChartViewer viewer = new ChartViewer();
        // The data for the pie chart
        double[] data = {riesgos.getRiesgosControl(), riesgos.getRiesgosEstructurales(), 
        		riesgos.getRiesgosRAW()+riesgos.getRiesgoCarga(), riesgos.getRiesgosWAR(), riesgos.getRiesgosWAW()};

        // The labels for the pie chart
        String[] labels = {lenguaje.getString("riesgosDeControl"), lenguaje.getString("riesgosEstructurales"), 
        		lenguaje.getString("riesgosRAW"), lenguaje.getString("riesgosWAR"), 
        		lenguaje.getString("riesgosWAW")};

        // Create a PieChart object of size 560 x 270 pixels, with a golden
        // background and a 1 pixel 3D border
        //PieChart c = new PieChart(390, 200, Chart.silverColor(), -1, 1);
        PieChart c = new PieChart(720, 400, Chart.silverColor(), -1, 1);
        // Add a title box using 15 pts Times Bold Italic font and metallic pink
        // background color
        c.addTitle("\n"+lenguaje.getString("informe"), "Times New Roman Bold Italic", 15
            ).setBackground(Chart.silverColor());

        // Set the center of the pie at (280, 135) and the radius to 110 pixels
        c.setPieSize(200, 225, 25);

        // Draw the pie in 3D with 20 pixels 3D depth
        c.set3D(20);

        // Use the side label layout method
        c.setLabelLayout(Chart.SideLayout);
        
//      modify the label format for the sectors to $nnnK (pp.pp%)
        c.setLabelFormat("{percent}% {label} ");

        
        // Set the label box background color the same as the sector color, with
        // glass effect, and with 5 pixels rounded corners
        TextBox t = c.setLabelStyle();
        t.setBackground(Chart.SameAsMainColor, Chart.Transparent, Chart.glassEffect());
        t.setRoundedCorners(0);
        c.addText(50, 50,config).setFontStyle("Arial Bold");
      
        cpi="           CPI"+cpi;
        resumen="          "+lenguaje.getString("RESUMEN")+"\n"+resumen;
        c.addText(450, 50, cpi).setFontStyle("Arial Bold");
        c.addText(450, 200, resumen).setFontStyle("Arial Bold");
        // Set the border color of the sector the same color as the fill color. Set
        // the line color of the join line to black (0x0)
        c.setLineColor(Chart.SameAsMainColor, 0x000000);

        // Set the start angle to 135 degrees may improve layout when there are many
        // small sectors at the end of the data array (that is, data sorted in
        // descending order). It is because this makes the small sectors position
        // near the horizontal axis, where the text label has the least tendency to
        // overlap. For data sorted in ascending order, a start angle of 45 degrees
        // can be used instead.
        c.setStartAngle(135);

        // Set the pie data and the pie labels
        c.setData(data, labels);

        // output the chart
        //viewer.setImage(c.makeImage());
//        this.getContentPane().add(viewer);
        //
            viewer.setImageMap(c.getHTMLImageMap("clickable", "",
            "title='{label}: US${value}K ({percent}%)'"));
              
        this.setLaImagen(c.makeImage());
        return c.makeImage();
}

    
    
    private void botonCerrarActionPerformed(java.awt.event.ActionEvent evt) {
    	this.dispose();
    }

    private void botonGuardarActionPerformed(java.awt.event.ActionEvent evt) {
    	String nombreFich=new String();
        FileDialog Dialogo=new FileDialog(this, lenguaje.getString("guardarComo"), FileDialog.SAVE);
        Dialogo.setVisible(true);
        if (Dialogo.getFile()==null)
            return;
        nombreFich=Dialogo.getDirectory() + File.separator + Dialogo.getFile()+".jpg";
        
        try{
            FileOutputStream fout=new FileOutputStream(nombreFich);            
            ImageIO.write((BufferedImage)this.getLaImagen(), "JPEG" /* format desired */, new File(nombreFich) /* target */ );
        }catch(Exception e){}    
       
    }
    
    
    private void formWindowClosing(java.awt.event.WindowEvent evt) {
		 this.dispose();
   }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DialogoInforme(new javax.swing.JFrame(), true).setVisible(true);
            }
        });
    }
    

    private javax.swing.JButton botonCerrar;
    private javax.swing.JButton botonGuardar;
 
    private javax.swing.JPanel jPanel1;
 
    private javax.swing.JPanel jPanel3;
 
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JLabel l;

	public Image getLaImagen() {
		return laImagen;
	}

	public void setLaImagen(Image laImagen) {
		this.laImagen = laImagen;
	}
    
}
