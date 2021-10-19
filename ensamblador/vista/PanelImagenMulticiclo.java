
package ensamblador.vista;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;
import java.util.*;
//import ensamblador.datos.*;
import ensamblador.etapas.PipelineFlotante;
import ensamblador.instrucciones.*;

/**PanelImagenMult.java
 *Clase que se encarga de la visualizacion de la representacion multciclo del camino de datos segmentado 
 **/
public class PanelImagenMulticiclo extends JPanel {
	private Image imagen;
	private Dimension tamano;	    

	public PanelImagenMulticiclo(Image imagen)	
	{	
		this.imagen=imagen;	    
		tamano=new Dimension(imagen.getWidth(null),imagen.getHeight(null));	    
		setSize(tamano);  	    
	}    
	    	
	public void paintComponent(Graphics g)	
	{	
		g.drawImage(imagen,0,0,this);	    
	} 	  
	    	
	public Dimension getPreferredSize()	
	{	
		return tamano;    
	}      
}
      
