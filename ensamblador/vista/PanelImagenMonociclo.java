
package ensamblador.vista;

import java.awt.*;
import javax.swing.*;
import ensamblador.etapas.EX;
import ensamblador.etapas.ID;
import ensamblador.etapas.IF;
import ensamblador.etapas.MEM;
import ensamblador.etapas.WB;
import ensamblador.unidadFuncional.UFuncional;
import ensamblador.unidadFuncional.UFuncionalSegm;

/**PanelImagenMult.java
 *Clase que se encarga de la visualizacion de la representacion multciclo del camino de datos segmentado 
 **/
public class PanelImagenMonociclo extends JPanel {

	private Image imagen;
	private Dimension tamano;	    

	public PanelImagenMonociclo(Image imagen)	
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
