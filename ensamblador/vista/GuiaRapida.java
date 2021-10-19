/*
 * Marcador.java
 *
 * Created on 3 de diciembre de 2006, 19:08
 */

package ensamblador.vista;

import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Vector;
import ensamblador.Ensamblador;
import ensamblador.datos.Lenguaje;

/**
 *
 * @author  rake
 */
public class GuiaRapida extends javax.swing.JDialog {
    
	
	private Lenguaje lenguaje;
	
    
    public GuiaRapida() {
        super();
    
        lenguaje = Lenguaje.getInstancia();
    
        initComponents();
            
        this.setVisible(true);     
    }
    
    private void initComponents() {
    	InputStream is=this.getClass().getResourceAsStream(lenguaje.getString("rutaGuiaRapida"));
    	BufferedReader in=new BufferedReader(new InputStreamReader(is));
    	String line;
    	StringBuffer text=new StringBuffer();
    	try {
			while ( (line=in.readLine()) != null ) {
			    text.append(line+"\n");
			 }
			in.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    	this.setTitle(lenguaje.getString("guiaRapida"));
    		editorPane=new javax.swing.JEditorPane("text/html", text.toString());
    		editorPane.setEditable(false);
    		editorPane.setCaretPosition(0);
//    		String s = null;
//    	       try {
//    	    	   
//    	            s = "file:"+lenguaje.getString("rutaGuiaRapida");
//    	            System.out.println("la ruta es ----"+s);
//    	            
//    	            
//    	            URL helpURL = new URL(s);
//    	            try {
//    	                editorPane.setPage(helpURL);
//    	            } catch (IOException e) {
//    	                System.err.println("Attempted to read a bad URL: " + helpURL);
//    	            }
//    	        } catch (Exception e) {
//    	            System.err.println("Couldn't create help URL: " + s);
//    	        }

    		editorScrollPane = new javax.swing.JScrollPane(editorPane);
    			editorScrollPane.setVerticalScrollBarPolicy(
    					javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
    			editorScrollPane.setPreferredSize(new Dimension(500, 810));
    			this.getContentPane().add(editorScrollPane);
            pack();
    }

    
    
    private javax.swing.JEditorPane editorPane;
    private javax.swing.JScrollPane editorScrollPane;
}
