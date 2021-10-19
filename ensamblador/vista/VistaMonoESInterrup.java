package ensamblador.vista;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import ensamblador.Ensamblador;
import ensamblador.LimitedStyledDocument;
import ensamblador.controlador.ControladorMonoESInterrup;
import ensamblador.controlador.ControladorMonociclo;
import ensamblador.datos.EntradaSalida;
import ensamblador.datos.InfoThread;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.registros.Sp;
import ensamblador.util.excepcion.AnhadirCaracterException;
import ensamblador.util.excepcion.DelCaracterException;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.EnterException;
import ensamblador.util.excepcion.EntradaException;
import ensamblador.util.excepcion.SyscallException;
import ensamblador.util.excepcion.VisualizarEjecucionException;


public class VistaMonoESInterrup extends VistaMonociclo {
	private EntradaSalida entradaSalida=null;
	private PanelES panelES=null;
	private PanelMonitor panelMonitor=null;
	private javax.swing.JPanel jPanelESyMon;
	private Interrupcion vistaInterrupcion;
	protected String[] nombresEstilos;

	protected String[] estilos;
	// public ControladorMonoESInterrup controlador=null;
	public VistaMonoESInterrup(Ensamblador edit){		
		editor=edit;
		lenguaje = Lenguaje.getInstancia();
		controlador=new ControladorMonoESInterrup();
		vistaInterrupcion=new Interrupcion((ControladorMonoESInterrup)controlador);
		vistaInterrupcion.setVisible(false);
		breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
		setMem(obtDatos(controlador.visualizarMemoria()));
		setPil(obtDatos(controlador.visualizarPila()));
		setTitle(lenguaje.getString("MONOCICLO")+"  "+editor.getNombreF());
		controlador.anhadirObservador(this);
		initComponents();
		this.setSize(1124, 740);  


		visualizarDatos();
		MAX_CHARACTERS=controlador.tamanhoCodigo()*100;        
		this.subrayar("0");
		$sp.setText("$sp="+Sp.getRegistro().getPalabra().getHex().substring(2,10));       
		breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
		los_datos=new Vector();
		estilos=inicEstilos();

		nombresEstilos=this.inicEstilos();	
	}

	protected void initComponents(){

		botonCiclos=new javax.swing.JButton();
		comboBreakpoint = new JComboBox(controlador.getPc());
		panelES=new PanelES();
		panelMonitor=new PanelMonitor();
		panelES.setFocusable(false);
		panelMonitor.setFocusable(false);
		jPanelESyMon=new javax.swing.JPanel();

		super.initComponents();

		inicializando();


		segTexto.setFocusable(true);
		segTexto.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent evt){
				teclaPulsada(evt);
			}
		});

	}
	public void inicializando(){
		setMem("");
		setPil("");
		controlador.inicializar();
	}


	protected void initCaminoDatos(){
		jPanelESyMon.setLayout(new java.awt.GridLayout());                
		jPanelESyMon.setLayout(new java.awt.GridLayout(0, 1));

		jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.Y_AXIS));

		jTabbedPane1.setMaximumSize(new java.awt.Dimension(655, 430));
		jTabbedPane1.setMinimumSize(new java.awt.Dimension(655, 430));
		jTabbedPane1.setPreferredSize(new java.awt.Dimension(655, 430));


		jPanel5.setLayout(new javax.swing.BoxLayout(jPanel5, javax.swing.BoxLayout.Y_AXIS));

		jPanel5.setAlignmentX(0.9F);
		caminoDatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
		caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(lenguaje.getString("rutaMonociclo")+"monociclo.gif")));
		caminoDatos.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
		jPanel5.add(caminoDatos);
		jPanel5.setFocusable(true);
		jPanelESyMon.add(panelES);
		jPanelESyMon.add(panelMonitor);
		jTabbedPane1.addTab(lenguaje.getString("caminoDeDatos"), jPanel5);
		jTabbedPane1.addTab(lenguaje.getString("entradaSalida"), jPanelESyMon);
		jTabbedPane1.setSelectedIndex(1);

		jLabel28.setText("\n\n");
		jPanel5.add(jLabel28);

		jPanel24.add(jTabbedPane1);

	}

	public void teclaPulsada(KeyEvent evt){
		if(!this.ejecutandoRutina(controlador.getPalabraPC().getHex())){
			char keyCod=(char)evt.getKeyCode();
			int keyCode=(int)keyCod;
			String carc=new String();

			if(!evt.isShiftDown()){
				if(!(keyCode>=32&&keyCode<=57||keyCode==10))
					keyCode = keyCode+32;
			}
			try{

				((ControladorMonoESInterrup)controlador).anhadirInterrupcionTeclado();
				((ControladorMonoESInterrup)controlador).actualizarEntrada(new Palabra(keyCode));


			}catch(EntradaException e){
				if(e instanceof EnterException){					
					this.rehabilitaBotones();
				}
				if(e instanceof AnhadirCaracterException){	 					
					carc=String.valueOf(keyCod);
					panelMonitor.setCaracter(carc);
				}
				if(e instanceof DelCaracterException){	    						
					panelMonitor.delUltimoCaracter();	    	
				}
			}

			this.ejecutarRutina();
			this.botonCicloSig.setEnabled(true);
			this.botonEjecutar.setEnabled(true);
		}
	}

	public void actualizaSegTexto(){

	}



	public void update(Observable p, Object arg){
		String s;
		if(p instanceof InfoThread){
			this.rehabilitaBotones();


			//actualizarCiclos(((InfoThread)p).getCiclos());
			if(((InfoThread)p).hayException()){

				try{
					((InfoThread)p).getException();
				}catch(EjecucionException e){		

					this.analizaExcep(e.getTipo(), e);   
				}

			} else{			   

				this.rehabilitaBotones();
				Palabra pal=controlador.getPalabraPC();
				subrayar(pal.sumar(-4).getHex());
			}

		}else  if(p instanceof EntradaSalida){
			this.panelES.actualizarValores(((EntradaSalida)p).visualizar());
			s=((EntradaSalida)p).getMonitor();

			if(!s.equals(new String())){
				this.panelMonitor.setText(s,"cursiva");
			}
		}else{
			super.update(p, arg);
		}
	}

	public void analizaExcep(int opcion, EjecucionException e){
		String salida;
		//subrayar(controlador.getHexadecimalPC());
		this.actualizarCiclos(new Integer(((ControladorMonociclo)controlador).getCiclos()));
//		ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
//		ciclos.setText(new Integer(((ControladorMonociclo)controlador).getCiclos()).toString());
		Palabra p=controlador.getPalabraPC();
		//	subrayar(p.sumar(-4).getHex()); 
		switch(opcion){

		case -1:
			subrayar(p.sumar(-4).getHex());
			this.rehabilitaBotones();
			break;

		case 0:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setWarning(lenguaje.getString("error0"));
			break;

		case 1:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setError(lenguaje.getString("error1"));

			deshabilitaBotones();           


			break;

		case 2:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setError(lenguaje.getString("error2"));

			deshabilitaBotones();

			break;

		case 3:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setError(lenguaje.getString("error3"));           
			deshabilitaBotones();

			break;


		case 5:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setText("\n"+lenguaje.getString("question5"));
			((ControladorMonoESInterrup)controlador).setEsperandoEntrada(Tipos.INTEGER);
			this.deshabilitaBotones();
//			tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
//			String re=JOptionPane.showInputDialog(this,"Introduce un entero","ENTRADA",tipoError);
//			if(re!=null) {
//			controlador.readDato(re, Tipos.INTEGER);
//			}
			break;

		case 6:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setText("\n"+lenguaje.getString("question6"));
			((ControladorMonoESInterrup)controlador).setEsperandoEntrada(Tipos.STRING);
			this.deshabilitaBotones();
//			tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
//			String res=JOptionPane.showInputDialog(this,"Introduce una cadena","ENTRADA",tipoError);
//			if(res!=null)
//			{
//			controlador.readDato(res, Tipos.STRING);
//			}
			break;

		case 12:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setText("\n"+lenguaje.getString("question12"));
			((ControladorMonoESInterrup)controlador).setEsperandoEntrada(Tipos.FLOAT);
			this.deshabilitaBotones();
//			tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
//			String resu=JOptionPane.showInputDialog(this,"Introduce un float","ENTRADA",tipoError);
//			if(resu!=null)
//			{
//			controlador.readDato(resu, Tipos.FLOAT);
//			}
			break;

		case 13:
			subrayar(p.sumar(-4).getHex());
			panelMonitor.setText("\n"+lenguaje.getString("question13"));
			((ControladorMonoESInterrup)controlador).setEsperandoEntrada(Tipos.DOUBLE);
			this.deshabilitaBotones();
//			tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
//			String resul=JOptionPane.showInputDialog(this,"Introduce un double","ENTRADA",tipoError);
//			if(resul!=null)
//			{
//			controlador.readDato(resul, Tipos.DOUBLE);
//			}
			break;
		case 4:
		case 7:
		case 10:
		case 11:
			subrayar(p.sumar(-4).getHex());
			salida=((SyscallException)e).imprimir();
			((ControladorMonoESInterrup)controlador).actualizarSalida(salida.charAt(salida.length()-1));
			panelMonitor.setText(salida);
			this.rehabilitaBotones();
			break;

		case 17: //la rutina finalizo su ejecucion
			subrayar(p.sumar(-4).getHex());
			this.rehabilitaBotones();
			break;

		case 18: //ejecutando ciclo anterior y el ciclo actual corresponde a una interrupcion		

			this.ejecutarRutina();
			this.subrayar(((VisualizarEjecucionException)e).getPc());
			this.rehabilitaBotones();
			break;
		case 19: //ejecutando ciclo anterior y el ciclo actual corresponde al codigo del programa a ejecutar		

			this.subrayar(((VisualizarEjecucionException)e).getPc());
			this.rehabilitaBotones();
			break;
		default:

			super.analizaExcep(opcion, e);
		break;

		}
	}


	protected void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
		this.activarBotonCiclos();
		this.deshabilitaBotones();
		super.botonEjecutarActionPerformed(evt);
	}


	public void subrayar(String valorPC){
		if(!ejecutandoRutina(valorPC)){
			subrayarCodigo(valorPC);			   
			vistaInterrupcion.setVisible(false);
		}else{
			vistaInterrupcion.setVisible(true);
			vistaInterrupcion.subrayarRutina(valorPC);
			desactivarSegTexto();
		}

	}




	/**
	 *Metodo que destaca la instruccion que esta siendo ejecutada
	 *@param String valorPC
	 *@return void
	 **/    
	public void subrayarCodigo(String valorPC)
	{           
		int inicio;
		int fin;
		segTexto.setText(controlador.visualizarCodigo());

		if(((ControladorMonociclo)controlador).getCiclos()>0){

			inicio=segTexto.getText().indexOf(valorPC);
			fin=segTexto.getText().indexOf("\n",inicio);            

		}
		else{
			inicio=0;
			fin=0;
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
		if(inicio<1){
			inicio=1;
		}
		if(fin<1){
			fin=1;
		}
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
				//if(breakpoint.getDec()>(comboBreakpoint.getItemCount()-1))
				lpd.insertString(lpd.getLength(),  texto.substring(breakP_ini-1, texto.length()),segTexto.getStyle(styleNames[2]));            
			}            

		} catch (BadLocationException ble) {
			System.err.println("No se pudo insertar texto inicial");
		}
	}






















	public boolean ejecutandoRutina(String valorPC){
		if(valorPC.compareTo((new Palabra("0x80000080", true).getHex()))>=0){
			return true;
		}
		return false;
	}

	protected void initBotones(){
		jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 0));
		jPanel3.setFocusable(false);
		botonVolverEdit.setFont(new java.awt.Font("Dialog", 1, 11));
		botonVolverEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Volver_editor.gif")));
		botonVolverEdit.setText(lenguaje.getString("volverAlEditor"));
		botonVolverEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonVolverEdit.setMaximumSize(new java.awt.Dimension(80, 26));
		botonVolverEdit.setMinimumSize(new java.awt.Dimension(10, 26));
		botonVolverEdit.setPreferredSize(new java.awt.Dimension(10, 26));
		botonVolverEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		botonVolverEdit.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botonVolverEditActionPerformed(evt);
			}
		});

		jPanel3.add(botonVolverEdit);

		botonPasoAnt.setFont(new java.awt.Font("Dialog", 1, 11));
		botonPasoAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/paso_ant.gif")));
		botonPasoAnt.setText(lenguaje.getString("pasoAnterior"));
		botonPasoAnt.setAlignmentY(0.0F);
		botonPasoAnt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonPasoAnt.setMargin(new java.awt.Insets(0, 0, 0, 0));
		botonPasoAnt.setMaximumSize(new java.awt.Dimension(70, 26));
		botonPasoAnt.setMinimumSize(new java.awt.Dimension(10, 26));
		botonPasoAnt.setPreferredSize(new java.awt.Dimension(10, 26));
		botonPasoAnt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		botonPasoAnt.setEnabled(false);
		jPanel3.add(botonPasoAnt);

		botonPasoSig.setFont(new java.awt.Font("Dialog", 1, 11));
		botonPasoSig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/paso_sig.gif")));
		botonPasoSig.setText(lenguaje.getString("pasoSiguiente"));
		botonPasoSig.setAlignmentY(0.0F);
		botonPasoSig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonPasoSig.setMargin(new java.awt.Insets(0, 0, 0, 0));
		botonPasoSig.setMaximumSize(new java.awt.Dimension(70, 26));
		botonPasoSig.setMinimumSize(new java.awt.Dimension(10, 26));
		botonPasoSig.setPreferredSize(new java.awt.Dimension(20, 26));
		botonPasoSig.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		botonPasoSig.setEnabled(false);
		jPanel3.add(botonPasoSig);

		botonCicloAnt.setFont(new java.awt.Font("Dialog", 1, 11));
		botonCicloAnt.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/ciclo_ant.gif")));
		botonCicloAnt.setText(lenguaje.getString("cicloAnterior"));
		botonCicloAnt.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonCicloAnt.setMargin(new java.awt.Insets(0, 0, 0, 0));
		botonCicloAnt.setMaximumSize(new java.awt.Dimension(70, 26));
		botonCicloAnt.setMinimumSize(new java.awt.Dimension(30, 26));
		botonCicloAnt.setPreferredSize(new java.awt.Dimension(10, 26));
		botonCicloAnt.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		botonCicloAnt.setEnabled(false);
		botonCicloAnt.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botonCicloAntActionPerformed(evt);
			}
		});

		jPanel3.add(botonCicloAnt);

		botonCicloSig.setFont(new java.awt.Font("Dialog", 1, 11));
		botonCicloSig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/ciclo_sig.gif")));
		botonCicloSig.setText(lenguaje.getString("cicloSiguiente"));
		botonCicloSig.setAlignmentX(0.5F);
		botonCicloSig.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonCicloSig.setIconTextGap(0);
		botonCicloSig.setMargin(new java.awt.Insets(2, 4, 2, 0));
		botonCicloSig.setMaximumSize(new java.awt.Dimension(80, 60));
		botonCicloSig.setMinimumSize(new java.awt.Dimension(30, 60));
		botonCicloSig.setPreferredSize(new java.awt.Dimension(10, 60));
		botonCicloSig.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		botonCicloSig.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botonCicloSigActionPerformed(evt);
			}
		});

		jPanel3.add(botonCicloSig);

		botonEjecutar.setFont(new java.awt.Font("Dialog", 1, 11));
		botonEjecutar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Ejecutar.gif")));
		botonEjecutar.setText(lenguaje.getString("ejecutar"));
		botonEjecutar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonEjecutar.setMaximumSize(new java.awt.Dimension(80, 54));
		botonEjecutar.setMinimumSize(new java.awt.Dimension(10, 54));
		botonEjecutar.setPreferredSize(new java.awt.Dimension(10, 54));
		botonEjecutar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
		botonEjecutar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				botonEjecutarActionPerformed(evt);
			}
		});

		jPanel3.add(botonEjecutar);

		jPanel1.setLayout(new java.awt.GridLayout(0, 1));

		checkBreakpoint.setText(lenguaje.getString("breakpoint"));
		checkBreakpoint.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		checkBreakpoint.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				checkBreakpointItemStateChanged(evt);
			}
		});

		jPanel1.add(checkBreakpoint);

		comboBreakpoint.setEnabled(false);
		comboBreakpoint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboBreakpointActionPerformed(evt);
			}
		});
		comboBreakpoint.addItemListener(new java.awt.event.ItemListener() {
			public void itemStateChanged(java.awt.event.ItemEvent evt) {
				comboBreakpointItemStateChanged(evt);
			}
		});

		jPanel1.add(comboBreakpoint);

		jPanel3.add(jPanel1);

		jPanel13.setLayout(new java.awt.GridLayout(1, 0));

		botonCiclos.setFont(new java.awt.Font("Dialog", 1, 11));
		//  botonCiclos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/stop.gif")));
		botonCiclos.setText(lenguaje.getString("CICLOS"));
		botonCiclos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
		botonCiclos.setMaximumSize(new java.awt.Dimension(80, 54));
		botonCiclos.setMinimumSize(new java.awt.Dimension(10, 54));
		botonCiclos.setPreferredSize(new java.awt.Dimension(10, 54));
		botonCiclos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);

		botonCiclos.addMouseListener(new java.awt.event.MouseListener(){

			public void mouseClicked(MouseEvent e) {
				botonCiclosActionPerformed();

			}

			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			public void mousePressed(MouseEvent e) {
				botonCiclosActionPerformed();

			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}		           
		});
		botonCiclos.addActionListener(new java.awt.event.ActionListener() {		        	
			public void actionPerformed(java.awt.event.ActionEvent evt) {		            	

				botonCiclosActionPerformed();
			}		           
		});
		botonCiclos.setEnabled(false);


		jPanel13.add(botonCiclos);

		jPanel3.add(jPanel13);
	}

	private void activarBotonCiclos(){
		//this.botonCiclos.setFont(new java.awt.Font("Dialog", 1, 36));
		botonCiclos.setText(null);
		botonCiclos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/stop.gif")));
		//this.botonCiclos.setText("Stop");
		this.botonCiclos.setEnabled(true);
	}

	public void botonCiclosActionPerformed() {
		this.botonCiclos.setEnabled(false);
		this.botonCiclos.setIcon(null);
		//this.rehabilitaBotones();

		((ControladorMonoESInterrup)controlador).pararEjecucion();
		this.actualizarCiclos(((ControladorMonoESInterrup)controlador).getCiclos());
		this.rehabilitaBotones();


	}

	public void actualizarCiclos(int numCiclos){
		this.botonCiclos.setIcon(null);
		this.botonCiclos.setEnabled(false);

		if(numCiclos>=1000){
			botonCiclos.setFont(new java.awt.Font("Dialog", 1, 20));
		}else{
			botonCiclos.setFont(new java.awt.Font("Dialog", 1, 32));
		}
		botonCiclos.setText(new Integer(numCiclos).toString());
	}


	public void ejecutarRutina(){
		String s=((ControladorMonoESInterrup)controlador).visualizarRutina();

		vistaInterrupcion.setTexto(s);
		vistaInterrupcion.setVisible(true);
		vistaInterrupcion.setAlwaysOnTop(true);
		desactivarSegTexto();

		this.botonCicloSig.setEnabled(true);
		this.botonEjecutar.setEnabled(true);
	}

	public void desactivarSegTexto(){
		String epc;
		int inicio=1;
		int fin=1;
		this.estilos=super.inicEstilos();
		segTexto.setText(controlador.visualizarCodigo());
		String texto=segTexto.getText();

		epc=((ControladorMonoESInterrup)controlador).getPalabraEPC().getHex();
		inicio=segTexto.getText().indexOf(epc);
		if(inicio==-1){
			inicio=1;
			fin=1;
		}else{			
			fin=segTexto.getText().indexOf("\n", inicio);
		}
		if(inicio==0){
			inicio++;
		}

		lpd=new LimitedStyledDocument(MAX_CHARACTERS);
		segTexto.setDocument(lpd);
		segTexto.setCaretPosition(0);
		segTexto.setMargin(new Insets(5,5,5,5));

		try {

			Style def = segTexto.addStyle("default", segTexto.getLogicalStyle());
			StyleConstants.setFontSize(def, 12);
			StyleConstants.setFontFamily(def, "Dialog");
			StyleConstants.setForeground(def, Color.BLACK);

			Style st1 = segTexto.addStyle("break", def);
			StyleConstants.setItalic(st1, true);
			StyleConstants.setForeground(st1, Color.GRAY);	

			Style st2 = segTexto.addStyle("blueCurs", def);
			StyleConstants.setItalic(st2, true);
			StyleConstants.setForeground(st2, Color.BLUE);

			String s=texto.substring(0, inicio-1);
			lpd.insertString(lpd.getLength(),s ,segTexto.getStyle("break"));

			if(fin>inicio){
				s=texto.substring(inicio, fin-1);
				lpd.insertString(lpd.getLength(),s ,segTexto.getStyle("blueCurs"));
			}


			s=texto.substring(fin, texto.length());
			lpd.insertString(lpd.getLength(),s ,segTexto.getStyle("break"));
		} catch (BadLocationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		segTexto.select(inicio,inicio);  
	}






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








	/*Este metodo deberia estar en la superclase Vista, 
	 * pero es necesario modificar el controlador y procesador
	 * de segmentado para que cuente con el metodo getCiclos*/    
	public void rehabilitaBotones(){
		if((((ControladorMonociclo)controlador).getCiclos()>0)){
			botonCicloAnt.setEnabled(true);    		
		}else{
			botonCicloAnt.setEnabled(false);
		}


		if(((controlador.getPalabraPC().getDec()-Tipos.INICIO_PC.getDec())/4)<(controlador.tamanhoCodigo())
				|| (controlador.getPalabraPC().getHex().compareTo(new Palabra("0x80000080",true).getHex()))>=0){
			botonCicloSig.setEnabled(true);
			botonEjecutar.setEnabled(true);
			this.actualizarCiclos(((ControladorMonociclo)controlador).getCiclos());
		}
	}









	/**
	 *Metodo que hace retroceder la ejecucion hasta el ciclo anterior
	 *@param ItemEvent evt
	 *@return void
	 **/
	protected void botonCicloAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCicloAntActionPerformed


		botonCicloSig.setEnabled(true);
		botonEjecutar.setEnabled(true);

		this.panelMonitor.inicializar();
		try{
			controlador.ejecutarCicloAnterior(breakpoint);
		}catch(EjecucionException e){
			if(e.getTipo()==19){            
				this.rehabilitaBotones();
				caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(((ControladorMonociclo)controlador).getImagenActual().toLowerCase())));
			}
			this.analizaExcep(e.getTipo(), e);
		}

	}


	  protected void botonVolverEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverEditActionPerformed
		  super.botonVolverEditActionPerformed(evt);
		  this.vistaInterrupcion.setVisible(false);
	  }








	protected javax.swing.JButton botonCiclos;	   

}
