package ensamblador.vista;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import ensamblador.LimitedStyledDocument;
import ensamblador.datos.Lenguaje;

public class PanelMonitor extends javax.swing.JPanel {
	LimitedStyledDocument lpd;
	Lenguaje lenguaje;
	
	public PanelMonitor() {
		super();
    	lenguaje = Lenguaje.getInstancia();
        initComponents();
		
	}

	private void initComponents() {
		   jScrollPane1 = new javax.swing.JScrollPane();
	        jEditor = new javax.swing.JTextPane();

	        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.X_AXIS));
	        
	        setBorder(javax.swing.BorderFactory.createTitledBorder(null, lenguaje.getString("monitor"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION));
	        jScrollPane1.setBorder(null);	        
	        jEditor.setBorder(null);
	        jEditor.setBackground(javax.swing.UIManager.getDefaults().getColor("Panel.background"));
	        jEditor.setEditable(false);
	        jScrollPane1.setViewportView(jEditor);

	        add(jScrollPane1);
	        
	        lpd = new LimitedStyledDocument(10000);
	        
	        jEditor.setDocument(lpd);
	        jEditor.setCaretPosition(0);
	        jEditor.setMargin(new Insets(5,5,5,5));             
	    	String NombreEstilos[] = { "default", "error", "warning", "cursiva" };

	    	Style def = jEditor.addStyle(NombreEstilos[0], jEditor.getLogicalStyle());
	    	StyleConstants.setFontSize(def, 12);
	    	StyleConstants.setFontFamily(def, "Dialog");
	            StyleConstants.setForeground(def, Color.BLACK);

	    	Style s = jEditor.addStyle(NombreEstilos[1], def);
	    	StyleConstants.setBold(s, true);
	        StyleConstants.setForeground(s, Color.RED);
	            

	    	s = jEditor.addStyle(NombreEstilos[2], def);	    	
	        StyleConstants.setForeground(s, Color.YELLOW);
	            
	        s = jEditor.addStyle(NombreEstilos[3], def);
		    StyleConstants.setItalic(s, true);
		    StyleConstants.setBold(s, true);
	        StyleConstants.setForeground(s, Color.BLACK);
	            
	}
	
	public void setText(String text){
		
		try{
			lpd.insertString(lpd.getLength(), text+"\n", jEditor.getStyle("default"));
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}

	}
	public void inicializar(){
		try{
			while(lpd.getLength()>0){
				lpd.remove(lpd.getLength()-1, 1);
			}
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}
	}

	public void setText(String text, String estilo){
		
		try{
			lpd.insertString(lpd.getLength(), text, jEditor.getStyle(estilo));
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}

	}

	public void setCaracter(String c){
		try{
			lpd.insertString(lpd.getLength(), c, jEditor.getStyle("default"));
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}
	}
	public void setError(String error){
		try{
			lpd.insertString(lpd.getLength(), error+"\n", jEditor.getStyle("error"));
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}
	}
	public void setWarning(String warning){
		try{
			lpd.insertString(lpd.getLength(), warning+"\n", jEditor.getStyle("warning"));
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}
	}
	
	public void  delUltimoCaracter(){
		try{
			lpd.remove(lpd.getLength()-1, 1);
			jEditor.select(jEditor.getText().length()-1, jEditor.getText().length()-1);
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto");
		}
	}
	
	
	
	 private javax.swing.JTextPane jEditor;
	 private javax.swing.JScrollPane jScrollPane1;
}
