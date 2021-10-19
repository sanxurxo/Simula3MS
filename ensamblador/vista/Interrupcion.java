package ensamblador.vista;

import java.awt.Color;
import java.awt.Insets;

import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import ensamblador.LimitedStyledDocument;
import ensamblador.controlador.ControladorMonoESInterrup;
import ensamblador.controlador.ControladorMonociclo;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;

public class Interrupcion extends javax.swing.JFrame{
	LimitedStyledDocument lpd;
	String nombreEstilos[] = { "default", "selec", "break", "blueCurs" };
	private ControladorMonoESInterrup controlador;
	protected static int MAX_CHARACTERS;
	Lenguaje lenguaje;
	
	public Interrupcion(ControladorMonoESInterrup controlador){
		super();
		lenguaje = Lenguaje.getInstancia();
		initComponentes();
		inicEstilos(nombreEstilos);
		this.controlador=controlador; 
		this.MAX_CHARACTERS=controlador.getTamanhoRutina()*1000;
	}
	
	public void initComponentes(){
		this.setSize(490, 490);
		this.setTitle(lenguaje.getString("rutina"));
		rutina=new javax.swing.JTextPane();
		scroll=new javax.swing.JScrollPane();
		rutina.setBackground(new java.awt.Color(238,238,238));
		rutina.setEditable(false);
		

	
		scroll.setBorder(null);
		scroll.setAutoscrolls(true);
		scroll.setViewportView(rutina);
		
		this.getContentPane().add(scroll);
	}

	public void setTexto(String texto){
		lpd = new LimitedStyledDocument(texto.length()*10);
		rutina.setDocument(lpd);
	    rutina.setCaretPosition(0);
	    rutina.setMargin(new Insets(5,5,5,5));
//		Style def = rutina.addStyle("default", rutina.getLogicalStyle());
//		StyleConstants.setFontSize(def, 12);
//		StyleConstants.setFontFamily(def, "Dialog");
//        StyleConstants.setForeground(def, Color.BLACK);
//		
		try{
			lpd.insertString(lpd.getLength(), texto.substring(0, texto.length()-1),rutina.getStyle(nombreEstilos[0]));
		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto inicial");
		}    	    	    	   

	}
	
	
	private String[] inicEstilos(String[] nombreEstilos) {	
		

		Style def = rutina.addStyle(nombreEstilos[0], rutina.getLogicalStyle());
		StyleConstants.setFontSize(def, 12);
		StyleConstants.setFontFamily(def, "Dialog");
		StyleConstants.setForeground(def, Color.GREEN);

		Style s = rutina.addStyle(nombreEstilos[1], def);
		StyleConstants.setBold(s, true);
		StyleConstants.setForeground(s, Color.BLUE);

		s = rutina.addStyle(nombreEstilos[2], def);
		StyleConstants.setItalic(s, true);
		StyleConstants.setForeground(s, Color.GRAY);	

		s = rutina.addStyle(nombreEstilos[3], def);
		StyleConstants.setItalic(s, true);
		StyleConstants.setForeground(s, Color.BLUE);

		return nombreEstilos;
	}
	
	
	
	
	/**
     *Metodo que destaca la instruccion que esta siendo ejecutada
     *@param String valorPC
     *@return void
     **/    
    public void subrayarRutina(String valorPC){           
        int inicio;
        int fin;
        
        String texto=controlador.visualizarRutina();
        if(controlador.getCiclos()>0){
        	
            inicio=texto.indexOf(valorPC);
            fin=texto.indexOf("\n",inicio);            
            
        }
        else{
            inicio=0;
            fin=0;
        }
        lpd = new LimitedStyledDocument(MAX_CHARACTERS);
    
        rutina.setDocument(lpd);
        rutina.setCaretPosition(0);
        rutina.setMargin(new Insets(5,5,5,5));          
        
//        System.out.println("texto de la rutina-->"+texto);
//        System.out.println("valorPc-->"+valorPC+ " inicio-->"+inicio+" fin-->"+fin);
        initDocument(inicio, fin);      
        rutina.select(inicio,inicio);        
    }
    
    /**
     *Metodo que inicializa el documento del segmento de texto
     *@param int inicio, int fin
     *return void
     **/
    protected void initDocument(int inicio, int fin) {
    	if(inicio<1){
    		inicio=1;
    	}
    	if(fin<1){
    		fin=1;
    	}
    	

    	
    	

		Style def = rutina.addStyle("default", rutina.getLogicalStyle());
		StyleConstants.setFontSize(def, 12);
		StyleConstants.setFontFamily(def, "Dialog");
		StyleConstants.setForeground(def, Color.BLACK);

		Style s = rutina.addStyle("selec", def);
		StyleConstants.setBold(s, true);
		StyleConstants.setForeground(s, Color.BLUE);

		s = rutina.addStyle("break", def);
		StyleConstants.setItalic(s, true);
		StyleConstants.setForeground(s, Color.GRAY);	

		s = rutina.addStyle("blueCurs", def);
		StyleConstants.setItalic(s, true);
		StyleConstants.setForeground(s, Color.BLUE);

    	
    	
    	
    	
    	
    	
    	
    	
            String texto=controlador.visualizarRutina();
      
             try {
              //  if(((ControladorMonociclo)controlador).getCiclos()>1)
            	 
            	 	
                    lpd.insertString(lpd.getLength(), texto.substring(0, inicio-1),rutina.getStyle("default"));
                    if(fin>inicio){
                    
                    	lpd.insertString(lpd.getLength(), texto.substring(inicio-1,fin),rutina.getStyle("selec"));
                    }
                    if(fin<texto.length()){
                    
                    	lpd.insertString(lpd.getLength(), texto.substring(fin, texto.length()-1),rutina.getStyle("default"));	
                    }
                
    	} catch (BadLocationException ble) {
                System.err.println("No se pudo insertar texto inicial");
        }
     }
    
	private javax.swing.JScrollPane scroll;
	private javax.swing.JTextPane rutina;
}
