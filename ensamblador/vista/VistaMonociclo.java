/**
 *Monociclo.java
 *Clase encargada de visualizar el procesador monociclo
 **/

package ensamblador.vista;

import java.awt.Color;
import java.awt.Insets;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import ensamblador.Ensamblador;
import ensamblador.LimitedStyledDocument;
import ensamblador.ObservaRegistros;
import ensamblador.controlador.ControladorMonociclo;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Instruccion;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Sp;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.SyscallException;

public class VistaMonociclo extends Vista implements Observer, Tipos {
	
  
    Instruccion ins;
    int i=0;
  
    int contador;

    Registro registro;
    int num_ciclos;
    private String la_memoria;
    private String la_pila;
    protected Vector los_datos;
    LimitedStyledDocument lpd;
    static int MAX_CHARACTERS;
    Hashtable actions;
    String newline;
    Palabra p;
    ObservaRegistros ObReg=new ObservaRegistros();

    /**
     *Constructor de la clase monociclo
     **/
    public VistaMonociclo(){}
    public VistaMonociclo(Ensamblador edit) {
    	super(edit);    	
    	controlador=new ControladorMonociclo();
    	breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
    	 
    	initComponents();     
        inicializando();
        
      
        setTitle(lenguaje.getString("MONOCICLO")+"  "+editor.getNombreF());        
  
        this.setSize(1124, 740);  
        controlador.anhadirObservador(this);
        
        setMem(obtDatos(controlador.visualizarMemoria()));
        setPil(obtDatos(controlador.visualizarPila()));
        visualizarDatos();
        MAX_CHARACTERS=controlador.tamanhoCodigo()*1000;        
        this.subrayar("0");
    

        $sp.setText("$sp="+Sp.getRegistro().getPalabra().getHex().substring(2,10));       
        breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
        los_datos=new Vector();
        
  }
 
    /**
     *Metodo encargado de inicializar los componentes
     *@param sin paramentros
     *@return voids
     **/
    protected void initComponents() {
    	comboBreakpoint = new JComboBox(controlador.getPc());
        super.initComponents();              
    }
    
 

    protected void initCaminoDatos(){
        jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.Y_AXIS));
        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        jPanel5.setAlignmentX(0.9F);
        jPanel5.setMaximumSize(new java.awt.Dimension(650, 420));
        jPanel5.setMinimumSize(new java.awt.Dimension(650, 420));
        jPanel5.setPreferredSize(new java.awt.Dimension(650, 420));
        caminoDatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(lenguaje.getString("rutaMonociclo")+"monociclo.gif")));
        jPanel5.add(caminoDatos);

        jLabel28.setText("\n\n");
        jPanel5.add(jLabel28);


        jPanel24.add(jPanel5);

    }
    
    
    
//    /**
//     *Metodo encargado de controlar los cambios en el comboBox
//     *@param ItemEvent evt
//     *@return void
//     **/
    protected void comboBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {

    	super.comboBreakpointItemStateChanged(evt);
        subrayar(new Palabra(Pc.getRegistro().getPalabra().getDec() -4).getHex());
    }

   /**
    *Metodo que contola los cambios en el checkBox
     *@param ItemEvent evt
     *@return void
     **/
    protected void checkBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBreakpointItemStateChanged
    	super.checkBreakpointItemStateChanged(evt);
        subrayar(new Palabra(Pc.getRegistro().getPalabra().getDec() -4).getHex());
    }    
    /**
     *Metodo que inicializa el valor de todos los componentes
     *@param sin parametros
     *@return void
     **/
    public void inicializando()
    {
        setMem("");
        setPil("");
        controlador.inicializar();        
        
    }
    
    /**
     *Metodo que hace retroceder la ejecucion hasta el ciclo anterior
     *@param ItemEvent evt
     *@return void
     **/
    protected void botonCicloAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCicloAntActionPerformed
     
    	
    	 botonCicloSig.setEnabled(true);
         botonEjecutar.setEnabled(true);
      
         try{
             if(controlador.ejecutarCicloAnterior(breakpoint)){
            	 this.actualizarCiclos(((ControladorMonociclo)controlador).getCiclos());
                    if(((ControladorMonociclo)controlador).getCiclos()==0){
                 	   botonCicloAnt.setEnabled(false);
                    }


                  p=controlador.getPalabraPC();
                  subrayar(p.sumar(-4).getHex());
                  caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(((ControladorMonociclo)controlador).getImagenActual().toLowerCase())));     
                                      
             }
             }catch(EjecucionException e){        	
             	this.analizaExcep(e.getTipo(), e);
             }
         
     	
             

    }

    /**
     *Metodo que completa la ejecucion del codigo
     *@param ActionEvent evt
     *@return void
     **/
    protected void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
       
    	try{    		
    	
    		num_ciclos=controlador.ejecutar(breakpoint);
            p=controlador.getPalabraPC();
            subrayar(p.sumar(-4).getHex());
            caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(((ControladorMonociclo)controlador).getImagenActual().toLowerCase())));
            ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
            ciclos.setText(new Integer(((ControladorMonociclo)controlador).getCiclos()).toString());
        

    	 }catch(EjecucionException e){
    		 analizaExcep(e.getTipo(), e);

         }
        

    	

    }

    /**
     *Metodo que vuelve a la ventana del editor
     *@param ActionEvent evt
     *@return void
     **/
    protected void botonVolverEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverEditActionPerformed
        this.dispose();
        editor.setVisible(true);        
     
    }

    /**
     *Metodo que avanza la ejecucin hacia el ciclo siguiente
     *@param ActionEvent evt
     *@return void
     **/
    protected void botonCicloSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCicloSigActionPerformed
       
    	 botonCicloAnt.setEnabled(true);
         
         try{
        	p=controlador.getPalabraPC();
         if(controlador.ejecutarCiclo(breakpoint)){
        	 

        	 this.actualizarCiclos(((ControladorMonociclo)controlador).getCiclos());

                subrayar(p.getHex());              
 
                caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(((ControladorMonociclo)controlador).getImagenActual().toLowerCase())));
         }
         }catch(EjecucionException e){

         	this.analizaExcep(e.getTipo(), e);
         }
    	
       
    }
    
    /**
     *Metodo que destaca la instruccion que esta siendo ejecutada
     *@param String valorPC
     *@return void
     **/    
    public void subrayar(String valorPC)
    {           
        int inicio;
        int fin;
        segTexto.setText(controlador.visualizarCodigo());
       
        if(((ControladorMonociclo)controlador).getCiclos()>0){
        	
            inicio=segTexto.getText().indexOf(valorPC);
            fin=segTexto.getText().indexOf("\n",inicio);            
            
        }
        else{
            inicio=1;
            fin=1;
        }
        lpd = new LimitedStyledDocument(MAX_CHARACTERS);
    
        segTexto.setDocument(lpd);
        segTexto.setCaretPosition(0);
        segTexto.setMargin(new Insets(5,5,5,5));             
        initDocument(inicio, fin);      
        segTexto.select(inicio,inicio);        
    }
    
    /**
     *Metodo que inicializa el documento del segmento de texto
     *@param int inicio, int fin
     *return void
     **/
    protected void initDocument(int inicio, int fin) {
    	String initString =controlador.visualizarCodigo();
		
    	String[] styleNames = inicEstilos();
            String texto=controlador.visualizarCodigo();
            int breakP_ini=0;
            Palabra ultimoBreak= new Palabra(INICIO_PC.getDec()+(controlador.tamanhoCodigo()-1)*4);        
                
            if(checkBreakpoint.isSelected() && (breakpoint.getDec()<=ultimoBreak.getDec()))       
                breakP_ini=texto.indexOf(breakpoint.getHex());
            
            else
            {
                breakpoint=new Palabra(INICIO_PC.getDec()+controlador.tamanhoCodigo()*4);        
                breakP_ini=texto.length();
            }
             try {
                if(((ControladorMonociclo)controlador).getCiclos()>1)
                    lpd.insertString(lpd.getLength(), texto.substring(0, inicio-1),segTexto.getStyle(styleNames[0]));
                if(((ControladorMonociclo)controlador).getCiclos()>0)
                {
                    if(((ControladorMonociclo)controlador).getCiclos()==1)
                        lpd.insertString(lpd.getLength(), texto.substring(inicio-4,fin-1),segTexto.getStyle(styleNames[1]));
                    else
                        lpd.insertString(lpd.getLength(), texto.substring(inicio-1,fin-1),segTexto.getStyle(styleNames[1]));
                }
                if(fin<breakP_ini-1)
                    lpd.insertString(lpd.getLength(), texto.substring(fin, breakP_ini-1),segTexto.getStyle(styleNames[0]));
             if(checkBreakpoint.isSelected() && (ultimoBreak.getDec()>=breakpoint.getDec()))             
             {
                   breakP_ini=texto.indexOf(breakpoint.getHex());
                   if(breakpoint.getDec()>(comboBreakpoint.getItemCount()-1))
                    lpd.insertString(lpd.getLength(),  texto.substring(breakP_ini-1, texto.length()),segTexto.getStyle(styleNames[2]));            
             }            
                
    	} catch (BadLocationException ble) {
                System.err.println("No se pudo insertar texto inicial");
        }
     }
    
    
    
    /**
     *Metodo que inicializa los estilos asociados al documento de segmento de texto
     *@param sin parametros
     *@return String[] estilos
     **/
      protected String[] inicEstilos() {	
	String NombreEstilos[] = { "default", "selec", "break", "blueCurs" };

	Style def = segTexto.addStyle(NombreEstilos[0], segTexto.getLogicalStyle());
	StyleConstants.setFontSize(def, 12);
	StyleConstants.setFontFamily(def, "Dialog");
        StyleConstants.setForeground(def, Color.BLACK);

	Style s = segTexto.addStyle(NombreEstilos[1], def);
	StyleConstants.setBold(s, true);
        StyleConstants.setForeground(s, Color.BLUE);

	s = segTexto.addStyle(NombreEstilos[2], def);
	StyleConstants.setItalic(s, true);
    StyleConstants.setForeground(s, Color.GRAY);	

    s = segTexto.addStyle(NombreEstilos[3], def);
	StyleConstants.setItalic(s, true);
        StyleConstants.setForeground(s, Color.BLUE);

	return NombreEstilos;
    }
    
      /**
       *Metodo que se encarga de dar formato a la informacin del segmento de datos
       *@param Vector args
       *@return String datos
       **/
    public String obtDatos(Vector args)
    {
        StringBuffer texto=new StringBuffer();
        for(int i=0;i<args.size();i++)
        {
            Vector aux=(Vector)args.elementAt(i);
            texto.append("[").append((String)aux.elementAt(0)).append("]").append("\t");
            int j=1;
            int k=1;            
            for(j=1;j<aux.size();j++)
            {
                if ((k%5)==0)
                {
                    texto.append("          \t");
                    k++;
                }
                texto.append((String)aux.elementAt(j)).append("\t");              
                k++;
            }
            texto.append("\n");            
                    
        }    
        return texto.toString();
    }
    
    /**
     *Metodo que se encarga de dar formato a la pila
     *@param Vector arg
     *@return String pila
     **/
    public String obtPila(Vector args)
    {
       String texto=new String();
        for(int i=0;i<args.size();i++)
        {
            Vector aux=(Vector)args.elementAt(i);      
       int j;
            for(j=0;j<aux.size();j++)
            {
                texto="\t"+(String)aux.elementAt(j)+texto;           
            }
            if(i==(args.size()-1))
                texto="["+Sp.getRegistro().getPalabra().getHex()+"]"+texto;
            else
                texto="\n"+"            "+texto;                                           
        }    
       return texto;
    }
    
    
    /**
     *Metodo que se encarga de visualizar los datos
     *@param sin parametros
     *@return void
     **/
    public void visualizarDatos()
    {
        String datos=getMem()+getPil();
        segDatos.setText(datos);
        segDatos.requestFocus();
        segDatos.select(0,0);
        segTexto.requestFocus();
    }
    
    /**
     *Metodo que actualiza el segmento de texto con la pila y la memoria
     *@param String memoria
     *@return void
     **/
    public void setMem(String mem)
    {
        la_memoria="\t\t"+lenguaje.getString("memoria")+"\n\n";
        if(mem.length()>0)
            la_memoria+=mem;  
        else
            la_memoria+="[0x10010000]";
    }
    
    /**
     *Metodo que devuelve el valor de la memoria
     *@param sin parametros
     *@return String memoria
     **/
    public String getMem()
    {
        return la_memoria;
    }
    
    /**
     *Metodo que modifica el valor de la pila
     *@param String pila_nueva
     *@return void
     **/
    public void setPil(String p)
    {
        la_pila="\n\t\t"+lenguaje.getString("pila")+"\n\n";
        if(p.length()>0)
            la_pila+=p;        
        else
            la_pila+="["+Sp.getRegistro().getPalabra().getHex()+"]";          
    }
    
    /**
     *Metodo que devuelve el valor de la pila
     *@param sin parametros
     *@return String pila
     **/
    public String getPil()
    {
        return la_pila;
    }
    
    /**
     *Metodo que visualiza las modificaciones en los registros
     *@param String valor, int nReg
     *@return void
     **/
    public void visualizaReg(String valor, int nReg)
    {
        valor=valor.substring(2,valor.length());
        switch(nReg)
        {
            case REG_ZERO:
                $zero.setText("$zero="+valor);
                break;
            case REG_AT:
                $at.setText("$at="+valor);
                break;
            case REG_V0:
                $v0.setText("$v0="+valor);
                break;
            case REG_V1:
                $v1.setText("$v1="+valor);
                break;
            case REG_A0:
                $a0.setText("$a0="+valor);
                break;
            case REG_A1:
                $a1.setText("$a1="+valor);
                break;
            case REG_A2:
                $a2.setText("$a2="+valor);
                break;
            case REG_A3:
                $a3.setText("$a3="+valor);
                break;
            case REG_T0:
                $t0.setText("$t0="+valor);
                break;
            case REG_T1:
                $t1.setText("$t1="+valor);
                break;
            case REG_T2:
                $t2.setText("$t2="+valor);
                break;
            case REG_T3:
                $t3.setText("$t3="+valor);
                break;
            case REG_T4:
                $t4.setText("$t4="+valor);
                break;
            case REG_T5:
                $t5.setText("$t5="+valor);
                break;
            case REG_T6:
                $t6.setText("$t6="+valor);
                break;
            case REG_T7:
                $t7.setText("$t7="+valor);
                break;
            case REG_S0:
                $s0.setText("$s0="+valor);
                break;
            case REG_S1:
                $s1.setText("$s1="+valor);
                break;
            case REG_S2:
                $s2.setText("$s2="+valor);
                break;
            case REG_S3:
                $s3.setText("$s3="+valor);
                break;
            case REG_S4:
                $s4.setText("$s4="+valor);
                break;
            case REG_S5:
                $s5.setText("$s5="+valor);
                break;
            case REG_S6:
                $s6.setText("$s6="+valor);
                break;
            case REG_S7:
                $s7.setText("$s7="+valor);
                break;                               
            case REG_T8:
                $t8.setText("$t8="+valor);
                break;
            case REG_T9:
                $t9.setText("$t9="+valor);
                break; 
            case REG_K0:;
                $k0.setText("$k0="+valor);
                break; 
            case REG_K1:
                $k1.setText("$k1="+valor);
                break;                 
            case REG_SP:
                $sp.setText("$sp="+valor);
                break;
            case REG_FP:
                $fp.setText("$fp="+valor);
                break;
            case REG_RA:
                $ra.setText("$ra="+valor);
                break;
            default:
            case REG_PC:
                $pc.setText("PC="+valor);
                break;      
            case REG_HI:
                HI.setText("HI="+valor);
                break;
            case REG_LO:
                 LO.setText("LO="+valor);
                 break;
            case REG_STATUS:
                STATUS.setText("Status="+valor);
                break;
	case REG_CAUSE:
            	CAUSE.setText("CAUSE="+valor);
            	////???
                break;
            case REG_EPC:
                EPC.setText("EPC="+valor);
                break;
        }
        segTexto.requestFocus();
    }
    
     /**
     *Metodo que visualiza las modificaciones en los registros de punto flotante
     *@param String valor, int nReg
     *@return void
     **/
    
    public void visualizaRegF(String valor, int nReg)
    {
        valor=valor.substring(2,valor.length());
        switch(nReg)
        {
            case REG_F0:
                $f0.setText("$f0="+valor);
                break;
            case REG_F1:
                $f1.setText("$f1="+valor);
                break;
            case REG_F2:
                $f2.setText("$f2="+valor);
                break;
            case REG_F3:
                $f3.setText("$f3="+valor);
                break;
            case REG_F4:
                $f4.setText("$f4="+valor);
                break;
            case REG_F5:
                $f5.setText("$f5="+valor);
                break;
            case REG_F6:
                $f6.setText("$f6="+valor);
                break;
            case REG_F7:
                $f7.setText("$f7="+valor);
                break;
            case REG_F8:
                $f8.setText("$f8="+valor);
                break;
            case REG_F9:
                $f9.setText("$f9="+valor);
                break;
            case REG_F10:
                $f10.setText("$f10="+valor);
                break;
            case REG_F11:
                $f11.setText("$f11="+valor);
                break;
            case REG_F12:
                $f12.setText("$f12="+valor);
                break;
            case REG_F13:
                $f13.setText("$f13="+valor);
                break;
            case REG_F14:
                $f14.setText("$f14="+valor);
                break;
            case REG_F15:
                $f15.setText("$f15="+valor);
                break;
            case REG_F16:
                $f16.setText("$f16="+valor);
                break;
            case REG_F17:
                $f17.setText("$f17="+valor);
                break;
            case REG_F18:
                $f18.setText("$f18="+valor);
                break;
            case REG_F19:
                $f19.setText("$f19="+valor);
                break;
            case REG_F20:
                $f20.setText("$f20="+valor);
                break;
            case REG_F21:
                $f21.setText("$f21="+valor);
                break;
            case REG_F22:
                $f22.setText("$f22="+valor);
                break;                               
            case REG_F23:
                $f23.setText("$f23="+valor);
                break;
            case REG_F24:
                $f24.setText("$f24="+valor);
                break;                               
            case REG_F25:
                $f25.setText("$f25="+valor);
                break;
            case REG_F26:
                $f26.setText("$f26="+valor);
                break;
            case REG_F27:
                $f27.setText("$f27="+valor);
                break;
            case REG_F28:
                $f28.setText("$f28="+valor);
                break;      
            case REG_F29:
                $f29.setText("$f29="+valor);
                break;
            case REG_F30:
                $f30.setText("$f30="+valor);
                break;
            case REG_F31:
                $f31.setText("$f31="+valor);
                break;    
        }
        segTexto.requestFocus();
    }
    
    /**
     *Metodo que informa de un cambio en un objeto observado
     *@param Observable observado, Object cambio
     *@return void
     **/
    
    public void update(Observable p, Object arg)
    {                
        if(!(arg instanceof Vector)){        	
            Registro r=(Registro) arg;
            if(r.letraReg()=='F')
                visualizaRegF(r.valorHex(),r.numReg());
            else
                visualizaReg(r.valorHex(),r.numReg());        	
        }
        else{
            String datos;
            if(p instanceof Pila){
                datos=obtPila((Vector)arg);
                setPil(datos);
            }
            else{
            	
            	datos=obtDatos((Vector)arg);
            	setMem(datos);
            }
            visualizarDatos();
        }
    }
    
    
    
    /**
     *Metodo que informa de que se ha producido una excepcion
     *@param int opcion
     *@return int error
     **/
    public void analizaExcep(int opcion, EjecucionException e){
   
    	String ultCarac;
    	int ult;
    	String salida;
    	int tipoError=javax.swing.JOptionPane.WARNING_MESSAGE;
        ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
        ciclos.setText(new Integer(((ControladorMonociclo)controlador).getCiclos()).toString());
        if(opcion!=-1){
        	Palabra p=controlador.getPalabraPC();
        	
            subrayar(p.sumar(-4).getHex());      
            String error=new String();
            switch(opcion)
            {
                case 0:
                    error=lenguaje.getString("error0");
                    tipoError=javax.swing.JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, "EXCEPCION", tipoError);
                    break;
                    
                case 1:
                    error=lenguaje.getString("error1");
                    tipoError=javax.swing.JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, lenguaje.getString("excepcion"), tipoError);
                    deshabilitaBotones();
                    break;
                    
                case 2:
                	error=lenguaje.getString("error2");
                    tipoError=javax.swing.JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, lenguaje.getString("excepcion"), tipoError);
                    deshabilitaBotones();
                    break;
                    
                case 3:
                	error=lenguaje.getString("error3");
                    tipoError=javax.swing.JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, lenguaje.getString("excepcion"), tipoError);
                    deshabilitaBotones();
                    break;

                case 4:                    
                    tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;
                    
             
                    JOptionPane.showMessageDialog(this, ((SyscallException)e).imprimir(), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 5:                    
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String re=JOptionPane.showInputDialog(this, lenguaje.getString("question5"), lenguaje.getString("entrada"),tipoError);
                     if(re!=null) {
                    	 controlador.readDato(re, Tipos.INTEGER);
                    }
                    break;
                    
                case 6: /*Lectura de un string*/
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    int errLec;
                    String res=JOptionPane.showInputDialog(this,lenguaje.getString("question6"),lenguaje.getString("entrada"),tipoError);                    
                    if(res!=null){
                    	errLec = controlador.readDato(res, Tipos.STRING);
                    	this.analizaExcep(errLec, new EjecucionException(errLec));
                    }
                    break;
                    
                case 7:

                    
                    tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;

                    JOptionPane.showMessageDialog(this, ((SyscallException)e).imprimir(), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 8:
                    tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog(this, lenguaje.getString("information8"), lenguaje.getString("resultado"), tipoError);                     
                    break;
                    
                case 9:
                    breakpoint.modificar(Pc.getRegistro().getPalabra().getDec());
                    botonCicloSig.setEnabled(false);
                    botonEjecutar.setEnabled(false);
                    break;
                   
                case 10:
                    tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog(this, ((SyscallException)e).imprimir(), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 11:

                	tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog(this, ((SyscallException)e).imprimir(), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 12:
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String resu=JOptionPane.showInputDialog(this, lenguaje.getString("question12"),lenguaje.getString("entrada"),tipoError);
                     if(resu!=null)
                    {
                    	 controlador.readDato(resu, Tipos.FLOAT);
                    }
                    break;
                   
                case 13:
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String resul=JOptionPane.showInputDialog(this,lenguaje.getString("question13"),lenguaje.getString("entrada"),tipoError);
                     if(resul!=null)
                    {
                    	 controlador.readDato(resul, Tipos.DOUBLE);
                    }
                    break;
                case 14:
                	error=lenguaje.getString("error14");
                	tipoError=javax.swing.JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, lenguaje.getString("excepcion"), tipoError);
                    deshabilitaBotones();
                    break;
                case 15: //se acabo la ejecucion
                	contador++;
                	num_ciclos+=contador;
                	ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
                    ciclos.setText(new Integer(((ControladorMonociclo)controlador).getCiclos()).toString());
                	deshabilitaBotones();
                	botonCicloAnt.setEnabled(true);
                	break;
                case 16:
                	error=lenguaje.getString("error16");
                	tipoError=javax.swing.JOptionPane.ERROR_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, lenguaje.getString("excepcion"), tipoError);
                    deshabilitaBotones();
                    break;


                    
                default:
                    break;
            }
            
        }
       
    }
    
    

    /*Este metodo deberia estar en la superclase Vista, 
     * pero es necesario modificar el controlador y procesador
     * de segmentado para que cuente con el metodo getCiclos*/    
    public void rehabilitaBotones(){
    	if((((ControladorMonociclo)controlador).getCiclos()>0)){
    		botonCicloAnt.setEnabled(true);    		
    	}else{
			botonCicloAnt.setEnabled(false);
		}

    	
    	
    	if(((controlador.getPalabraPC().getDec()-Tipos.INICIO_PC.getDec())/4)<(controlador.tamanhoCodigo())){
          botonCicloSig.setEnabled(true);
          botonEjecutar.setEnabled(true);
    	}
    }
    
    
    	
    
    
    	
    	
    
    

    
    
}

