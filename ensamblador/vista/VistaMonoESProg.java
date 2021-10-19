package ensamblador.vista;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.Observable;
import java.util.Vector;

import javax.swing.JComboBox;

import ensamblador.Ensamblador;
import ensamblador.controlador.ControladorMonoESProg;
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

public class VistaMonoESProg extends VistaMonociclo {
	 private EntradaSalida entradaSalida=null;
	 private PanelES panelES=null;
	 private PanelMonitor panelMonitor=null;
	 private javax.swing.JPanel jPanelESyMon;
	 protected String[] estilos;
	
	 public VistaMonoESProg(Ensamblador edit){		
		 editor=edit;
		 lenguaje = Lenguaje.getInstancia();
		 controlador=new ControladorMonoESProg();
		 breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
		 initComponents();
		 
		 setTitle(lenguaje.getString("MONOCICLO")+"  "+editor.getNombreF());
		 this.setSize(1124, 740);  
	     controlador.anhadirObservador(this);
	     setMem(obtDatos(controlador.visualizarMemoria()));
	        setPil(obtDatos(controlador.visualizarPila()));
	     visualizarDatos();
	     MAX_CHARACTERS=controlador.tamanhoCodigo()*100;        
	     this.subrayar("0");
	     $sp.setText("$sp="+Sp.getRegistro().getPalabra().getHex().substring(2,10));       
	     breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
	     los_datos=new Vector();
	     estilos=inicEstilos();
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
	 public void inicializando()
	    {
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
	    	char keyCod=(char)evt.getKeyCode();
	    	int keyCode=(int)keyCod;
	    	String carc=new String();
	    	
	    	if(!evt.isShiftDown()){
	    		 if(!(keyCode>=32&&keyCode<=57||keyCode==10))
	    		 	keyCode = keyCode+32;
	    	}
	    	try{
	    
	    		((ControladorMonoESProg)controlador).actualizarEntrada(new Palabra(keyCode));
	    		
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
	    }
	 
	
	   
	 
	   public void update(Observable p, Object arg){
		   String s;
		   if(p instanceof InfoThread){
			   actualizarCiclos(((InfoThread)p).getCiclos());
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
//		   ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
//	        ciclos.setText(new Integer(((ControladorMonociclo)controlador).getCiclos()).toString());
	        Palabra p=controlador.getPalabraPC();
	        subrayar(p.sumar(-4).getHex()); 
	   switch(opcion){
	
	   case -1:
		   this.rehabilitaBotones();
		   break;
	   
           case 0:
        	   panelMonitor.setWarning(lenguaje.getString("error0"));
           break;
           
       case 1:
    	   panelMonitor.setError(lenguaje.getString("error1"));
           
           deshabilitaBotones();           
           
           
           break;
           
       case 2:
    	   panelMonitor.setError(lenguaje.getString("error2"));
           
           deshabilitaBotones();
           
           break;
           
       case 3:
    	   panelMonitor.setError(lenguaje.getString("error3"));           
           deshabilitaBotones();

           break;

           
       case 5:
    	   panelMonitor.setText("\n"+lenguaje.getString("question5"));
    	   ((ControladorMonoESProg)controlador).setEsperandoEntrada(Tipos.INTEGER);
    	   this.deshabilitaBotones();

           break;
           
       case 6:
    	   panelMonitor.setText("\n"+lenguaje.getString("question6"));
    	   ((ControladorMonoESProg)controlador).setEsperandoEntrada(Tipos.STRING);
    	   this.deshabilitaBotones();

           break;
       
       case 12:
    	   panelMonitor.setText("\n"+lenguaje.getString("question12"));
    	   ((ControladorMonoESProg)controlador).setEsperandoEntrada(Tipos.FLOAT);
    	   this.deshabilitaBotones();

           break;
          
       case 13:
    	   panelMonitor.setText("\n"+lenguaje.getString("question13"));
    	   ((ControladorMonoESProg)controlador).setEsperandoEntrada(Tipos.DOUBLE);
    	   this.deshabilitaBotones();
           break;
           
		   case 4:
		   case 7:
		   case 10:
		   case 11:
			   salida=((SyscallException)e).imprimir();
			   int l=salida.length();
			   ((ControladorMonoESProg)controlador).actualizarSalida(salida.charAt(salida.length()-1));
			   panelMonitor.setText(salida);
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
			botonCiclos.setText(null);
			botonCiclos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/stop.gif")));
			this.botonCiclos.setEnabled(true);
		}
	   
		public void botonCiclosActionPerformed() {
			this.botonCiclos.setEnabled(false);
			this.botonCiclos.setIcon(null);
			this.rehabilitaBotones();
			
			((ControladorMonoESProg)controlador).pararEjecucion();
			this.actualizarCiclos(((ControladorMonoESProg)controlador).getCiclos());
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
		
		 /**
	     *Metodo que hace retroceder la ejecucion hasta el ciclo anterior
	     *@param ItemEvent evt
	     *@return void
	     **/
	    protected void botonCicloAntActionPerformed(java.awt.event.ActionEvent evt) {
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

		
		
protected javax.swing.JButton botonCiclos;	   
	   
}
