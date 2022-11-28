package ensamblador.vista;

import java.util.Observable;
import java.util.Observer;

import ensamblador.Ensamblador;
import ensamblador.controlador.Controlador;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.registros.Pc;

public abstract class Vista extends javax.swing.JFrame implements Observer, Tipos {
	public Controlador controlador;
	protected Palabra breakpoint;
	protected Ensamblador editor;
	protected Lenguaje lenguaje;
	
	public Vista(Ensamblador edit)  {
		editor=edit;
		lenguaje = Lenguaje.getInstancia();
	}
	public Vista(){}
	
	protected void initComponents(){
		jTabbedPane1=new javax.swing.JTabbedPane();
    	Panel = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        panelReg = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        $pc = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        EPC = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        CAUSE = new javax.swing.JLabel();
        STATUS = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        BadVaddr = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        HI = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        LO = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        $zero = new javax.swing.JLabel();
        $t0 = new javax.swing.JLabel();
        $s0 = new javax.swing.JLabel();
        $t8 = new javax.swing.JLabel();
        $at = new javax.swing.JLabel();
        $t1 = new javax.swing.JLabel();
        $s1 = new javax.swing.JLabel();
        $t9 = new javax.swing.JLabel();
        $v0 = new javax.swing.JLabel();
        $t2 = new javax.swing.JLabel();
        $s2 = new javax.swing.JLabel();
        $k0 = new javax.swing.JLabel();
        $v1 = new javax.swing.JLabel();
        $t3 = new javax.swing.JLabel();
        $s3 = new javax.swing.JLabel();
        $k1 = new javax.swing.JLabel();
        $a0 = new javax.swing.JLabel();
        $t4 = new javax.swing.JLabel();
        $s4 = new javax.swing.JLabel();
        $gp = new javax.swing.JLabel();
        $a1 = new javax.swing.JLabel();
        $t5 = new javax.swing.JLabel();
        $s5 = new javax.swing.JLabel();
        $sp = new javax.swing.JLabel();
        $a2 = new javax.swing.JLabel();
        $t6 = new javax.swing.JLabel();
        $s6 = new javax.swing.JLabel();
        $fp = new javax.swing.JLabel();
        $a3 = new javax.swing.JLabel();
        $t7 = new javax.swing.JLabel();
        $s7 = new javax.swing.JLabel();
        $ra = new javax.swing.JLabel();
        jPanel30 = new javax.swing.JPanel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        $f0 = new javax.swing.JLabel();
        $f8 = new javax.swing.JLabel();
        $f16 = new javax.swing.JLabel();
        $f24 = new javax.swing.JLabel();
        $f1 = new javax.swing.JLabel();
        $f9 = new javax.swing.JLabel();
        $f17 = new javax.swing.JLabel();
        $f25 = new javax.swing.JLabel();
        $f2 = new javax.swing.JLabel();
        $f10 = new javax.swing.JLabel();
        $f18 = new javax.swing.JLabel();
        $f26 = new javax.swing.JLabel();
        $f3 = new javax.swing.JLabel();
        $f11 = new javax.swing.JLabel();
        $f19 = new javax.swing.JLabel();
        $f27 = new javax.swing.JLabel();
        $f4 = new javax.swing.JLabel();
        $f12 = new javax.swing.JLabel();
        $f20 = new javax.swing.JLabel();
        $f28 = new javax.swing.JLabel();
        $f5 = new javax.swing.JLabel();
        $f13 = new javax.swing.JLabel();
        $f21 = new javax.swing.JLabel();
        $f29 = new javax.swing.JLabel();
        $f6 = new javax.swing.JLabel();
        $f14 = new javax.swing.JLabel();
        $f22 = new javax.swing.JLabel();
        $f30 = new javax.swing.JLabel();
        $f7 = new javax.swing.JLabel();
        $f15 = new javax.swing.JLabel();
        $f23 = new javax.swing.JLabel();
        $f31 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        segDatos = new javax.swing.JEditorPane();
        jPanel17 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        caminoDatos = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        segTexto = new javax.swing.JTextPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        botonVolverEdit = new javax.swing.JButton();
        botonPasoAnt = new javax.swing.JButton();
        botonPasoSig = new javax.swing.JButton();
        botonCicloAnt = new javax.swing.JButton();
        botonCicloSig = new javax.swing.JButton();
        botonEjecutar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        checkBreakpoint = new javax.swing.JCheckBox();       
        jPanel13 = new javax.swing.JPanel();
        ciclos = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();

        getContentPane().setLayout(new java.awt.BorderLayout(0, 5));

        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }
        });
        
        
        
        Panel.setLayout(new javax.swing.BoxLayout(Panel, javax.swing.BoxLayout.X_AXIS));

        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.Y_AXIS));

        jPanel16.setMaximumSize(new java.awt.Dimension(420, 33187));
        jPanel16.setMinimumSize(new java.awt.Dimension(420, 427));
        jPanel16.setPreferredSize(new java.awt.Dimension(420, 425));
 
        initRegistros();
        initSegDatos();

        
        
        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        jPanel17.add(jPanel15);

        Panel.add(jPanel17);

        
   
        initCaminoDatos();               

        initSegTexto();
        
        

        Panel.add(jPanel24);

        getContentPane().add(Panel, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        initBotones();
        
        

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel27, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel28, java.awt.BorderLayout.WEST);
       pack();
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

	        ciclos.setFont(new java.awt.Font("Dialog", 1, 18));
	        ciclos.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        ciclos.setText(lenguaje.getString("CICLOS"));
	        jPanel13.add(ciclos);

	        jPanel3.add(jPanel13);
	}
	
	protected void initSegTexto(){		
		jPanel26.setLayout(new javax.swing.BoxLayout(jPanel26, javax.swing.BoxLayout.X_AXIS));

        jPanel26.setBorder(new javax.swing.border.TitledBorder(null, lenguaje.getString("segmentoDeTexto"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel26.setMaximumSize(new java.awt.Dimension(1180, 32767));
        jPanel26.setMinimumSize(new java.awt.Dimension(150, 37));
        jPanel26.setPreferredSize(new java.awt.Dimension(650, 35));
        jScrollPane2.setBorder(null);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(1180, 32767));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(650, 22));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(650, 15));
        jScrollPane2.setAutoscrolls(true);
        segTexto.setBackground(new java.awt.Color(238, 238, 238));
        segTexto.setEditable(false);
        segTexto.setMinimumSize(new java.awt.Dimension(650, 15));
        segTexto.setPreferredSize(new java.awt.Dimension(650, 15));
        jScrollPane2.setViewportView(segTexto);

        jPanel26.add(jScrollPane2);

        jPanel24.add(jPanel26);

	}
	
	protected void initCaminoDatos(){
		//deberia ser abstract
		jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.Y_AXIS));
		
	}
	protected void initSegDatos(){
		  jPanel25.setLayout(new java.awt.GridLayout(1, 0));
	        
	        jPanel25.setBorder(new javax.swing.border.TitledBorder(null, lenguaje.getString("segmentoDeDatos"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
	        jPanel25.setMaximumSize(new java.awt.Dimension(460, 32767));
	        jPanel25.setMinimumSize(new java.awt.Dimension(460, 47));
	        jPanel25.setPreferredSize(new java.awt.Dimension(460, 44));
	        jPanel25.setFocusable(false);
	        jScrollPane1.setBorder(null);
	        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	        segDatos.setBackground(new java.awt.Color(238, 238, 238));
	        segDatos.setEditable(false);
	        segDatos.setFocusable(false);
	        segDatos.setFont(new java.awt.Font("Dialog", 0, 10));
	        jScrollPane1.setViewportView(segDatos);

	        jPanel25.add(jScrollPane1);

	        jPanel16.add(jPanel25);

	        Panel.add(jPanel16);
	}
	
	
	protected void initRegistros(){
		 panelReg.setLayout(new java.awt.GridLayout(1, 0, -200, 0));

	        panelReg.setBorder(new javax.swing.border.CompoundBorder());
	        panelReg.setMaximumSize(new java.awt.Dimension(420, 430));
	        panelReg.setMinimumSize(new java.awt.Dimension(420, 430));
	        panelReg.setPreferredSize(new java.awt.Dimension(420, 430));
	        panelReg.setFocusable(false);
	        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

	        jPanel4.setBorder(new javax.swing.border.TitledBorder(null, lenguaje.getString("registros2"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
	        jPanel4.setMaximumSize(new java.awt.Dimension(420, 250));
	        jPanel4.setMinimumSize(new java.awt.Dimension(420, 250));
	        jPanel4.setPreferredSize(new java.awt.Dimension(420, 250));
	        
	  
	        jPanel31.setLayout(new java.awt.GridLayout(0, 5, -50, -7));

	        $pc.setFont(new java.awt.Font("Dialog", 0, 10));
	        $pc.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        $pc.setText("PC=00400000");
	        jPanel31.add($pc);

	        jPanel31.add(jLabel12);

	        EPC.setFont(new java.awt.Font("Dialog", 0, 10));
	        EPC.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        EPC.setText("EPC=00000000");
	        EPC.setToolTipText("");
	        jPanel31.add(EPC);
	        
	        jPanel31.add(jLabel13);

	        CAUSE.setFont(new java.awt.Font("Dialog", 0, 10));
	        CAUSE.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        CAUSE.setText("Cause=00000000   ");
	        jPanel31.add(CAUSE);

	        STATUS.setFont(new java.awt.Font("Dialog", 0, 10));
	        STATUS.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        STATUS.setText("Status=0000ff03");
	        jPanel31.add(STATUS);

	        jPanel31.add(jLabel21);

	        BadVaddr.setFont(new java.awt.Font("Dialog", 0, 10));
	        BadVaddr.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        BadVaddr.setText("BadVaddr=00000000 ");
	        jPanel31.add(BadVaddr);

	        jPanel31.add(jLabel25);

	        jLabel22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jPanel31.add(jLabel22);

	        HI.setFont(new java.awt.Font("Dialog", 0, 10));
	        HI.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        HI.setText("HI=00000000");
	        jPanel31.add(HI);

	        jPanel31.add(jLabel24);

	        LO.setFont(new java.awt.Font("Dialog", 0, 10));
	        LO.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        LO.setText("LO=00000000");
	        jPanel31.add(LO);

	        jLabel11.setFont(new java.awt.Font("Dialog", 1, 10));
	        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jPanel31.add(jLabel11);

	        jPanel31.add(jLabel26);

	        jPanel31.add(jLabel5);

	        jPanel31.add(jLabel15);

	        jPanel31.add(jLabel20);

	        jPanel31.add(jLabel23);

	        jPanel31.add(jLabel27);

	        jPanel4.add(jPanel31);

	        
	        
	    
	        
	        jPanel32.setLayout(new java.awt.GridLayout(0, 4));

	        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
	        jPanel32.add(jLabel14);

	        jLabel3.setFont(new java.awt.Font("Dialog", 1, 10));
	        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        jLabel3.setText(lenguaje.getString("registros"));
	        jPanel32.add(jLabel3);

	        jLabel4.setFont(new java.awt.Font("Dialog", 1, 10));
	        jLabel4.setText(lenguaje.getString("generales"));
	        jPanel32.add(jLabel4);

	        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        jPanel32.add(jLabel8);

	        $zero.setFont(new java.awt.Font("Dialog", 0, 10));
	        $zero.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $zero.setText("$zero=00000000");
	        jPanel32.add($zero);

	        $t0.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t0.setText("$t0=00000000");
	        jPanel32.add($t0);

	        $s0.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s0.setText("$s0=00000000");
	        jPanel32.add($s0);

	        $t8.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t8.setText("$t8=00000000");
	        jPanel32.add($t8);

	        $at.setFont(new java.awt.Font("Dialog", 0, 10));
	        $at.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $at.setText("$at=00000000");
	        jPanel32.add($at);

	        $t1.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t1.setText("$t1=00000000");
	        jPanel32.add($t1);

	        $s1.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s1.setText("$s1=00000000");
	        jPanel32.add($s1);

	        $t9.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t9.setText("$t9=00000000");
	        jPanel32.add($t9);

	        $v0.setFont(new java.awt.Font("Dialog", 0, 10));
	        $v0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $v0.setText("$v0=00000000");
	        jPanel32.add($v0);

	        $t2.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t2.setText("$t2=00000000");
	        jPanel32.add($t2);

	        $s2.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s2.setText("$s2=00000000");
	        jPanel32.add($s2);

	        $k0.setFont(new java.awt.Font("Dialog", 0, 10));
	        $k0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $k0.setText("$k0=00000000");
	        jPanel32.add($k0);

	        $v1.setFont(new java.awt.Font("Dialog", 0, 10));
	        $v1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $v1.setText("$v1=00000000");
	        jPanel32.add($v1);

	        $t3.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t3.setText("$t3=00000000");
	        jPanel32.add($t3);

	        $s3.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s3.setText("$s3=00000000");
	        jPanel32.add($s3);

	        $k1.setFont(new java.awt.Font("Dialog", 0, 10));
	        $k1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $k1.setText("$k1=00000000");
	        jPanel32.add($k1);

	        $a0.setFont(new java.awt.Font("Dialog", 0, 10));
	        $a0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $a0.setText("$a0=00000000");
	        jPanel32.add($a0);

	        $t4.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t4.setText("$t4=00000000");
	        jPanel32.add($t4);

	        $s4.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s4.setText("$s4=00000000");
	        jPanel32.add($s4);

	        $gp.setFont(new java.awt.Font("Dialog", 0, 10));
	        $gp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $gp.setText("$gp=00000000");
	        jPanel32.add($gp);

	        $a1.setFont(new java.awt.Font("Dialog", 0, 10));
	        $a1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $a1.setText("$a1=00000000");
	        jPanel32.add($a1);

	        $t5.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t5.setText("$t5=00000000");
	        jPanel32.add($t5);

	        $s5.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s5.setText("$s5=00000000");
	        jPanel32.add($s5);

	        $sp.setFont(new java.awt.Font("Dialog", 0, 10));
	        $sp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $sp.setText("$sp=70000000");
	        jPanel32.add($sp);

	        $a2.setFont(new java.awt.Font("Dialog", 0, 10));
	        $a2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $a2.setText("$a2=00000000");
	        jPanel32.add($a2);

	        $t6.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t6.setText("$t6=00000000");
	        jPanel32.add($t6);

	        $s6.setFont(new java.awt.Font("Dialog", 0, 10));
	        $s6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s6.setText("$s6=00000000");
	        jPanel32.add($s6);

	        $fp.setFont(new java.awt.Font("Dialog", 0, 10));
	        $fp.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $fp.setText("$fp=00000000");
	        jPanel32.add($fp);

	        $a3.setFont(new java.awt.Font("Dialog", 0, 10));
	        $a3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $a3.setText("$a3=00000000");
	        jPanel32.add($a3);

	        $t7.setFont(new java.awt.Font("Dialog", 0, 10));
	        $t7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $t7.setText("$t7=00000000");
	        jPanel32.add($t7);

	        $s7.setFont(new java.awt.Font("Dialtype filter textog", 0, 10));
	        $s7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $s7.setText("$s7=00000000");
	        jPanel32.add($s7);

	        $ra.setFont(new java.awt.Font("Dialog", 0, 10));
	        $ra.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $ra.setText("$ra=00000000");
	        jPanel32.add($ra);

	        jPanel4.add(jPanel32);

	        jPanel30.setLayout(new java.awt.GridLayout(0, 4));

	        jPanel30.add(jLabel16);

	        jPanel30.add(jLabel17);

	        jPanel30.add(jLabel18);

	        jPanel30.add(jLabel19);
	        
	        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        jPanel30.add(jLabel7);

	        jLabel9.setFont(new java.awt.Font("Dialog", 1, 10));
	        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        jLabel9.setText(lenguaje.getString("registrosDe"));
	        jPanel30.add(jLabel9);

	        jLabel10.setFont(new java.awt.Font("Dialog", 1, 10));
	        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
	        jLabel10.setText(lenguaje.getString("puntoFlotante"));
	        jPanel30.add(jLabel10);

	        jPanel30.add(jLabel6);

	        $f0.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f0.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f0.setText("$f0=00000000");
	        jPanel30.add($f0);

	        $f8.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f8.setText("$f8=00000000");
	        jPanel30.add($f8);

	        $f16.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f16.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f16.setText("$f16=00000000");
	        jPanel30.add($f16);

	        $f24.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f24.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f24.setText("$f24=00000000");
	        jPanel30.add($f24);

	        $f1.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f1.setText("$f1=00000000");
	        jPanel30.add($f1);

	        $f9.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f9.setText("$f9=00000000");
	        jPanel30.add($f9);

	        $f17.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f17.setText("$f17=00000000");
	        jPanel30.add($f17);

	        $f25.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f25.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f25.setText("$f25=00000000");
	        jPanel30.add($f25);

	        $f2.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f2.setText("$f2=00000000");
	        jPanel30.add($f2);

	        $f10.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f10.setText("$f10=00000000");
	        jPanel30.add($f10);

	        $f18.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f18.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f18.setText("$f18=00000000");
	        jPanel30.add($f18);

	        $f26.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f26.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f26.setText("$f26=00000000");
	        jPanel30.add($f26);

	        $f3.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f3.setText("$f3=00000000");
	        jPanel30.add($f3);

	        $f11.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f11.setText("$f11=00000000");
	        jPanel30.add($f11);

	        $f19.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f19.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f19.setText("$f19=00000000");
	        jPanel30.add($f19);

	        $f27.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f27.setText("$f27=00000000");
	        jPanel30.add($f27);

	        $f4.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f4.setText("$f4=00000000");
	        jPanel30.add($f4);

	        $f12.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f12.setText("$f12=00000000");
	        jPanel30.add($f12);

	        $f20.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f20.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f20.setText("$f20=00000000");
	        jPanel30.add($f20);

	        $f28.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f28.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f28.setText("$f28=00000000");
	        jPanel30.add($f28);

	        $f5.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f5.setText("$f5=00000000");
	        jPanel30.add($f5);

	        $f13.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f13.setText("$f13=00000000");
	        jPanel30.add($f13);

	        $f21.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f21.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f21.setText("$f21=00000000");
	        jPanel30.add($f21);

	        $f29.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f29.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f29.setText("$f29=00000000");
	        jPanel30.add($f29);

	        $f6.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f6.setText("$f6=00000000");
	        jPanel30.add($f6);

	        $f14.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f14.setText("$f14=00000000");
	        jPanel30.add($f14);

	        $f22.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f22.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f22.setText("$f22=00000000");
	        jPanel30.add($f22);

	        $f30.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f30.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f30.setText("$f30=00000000");
	        jPanel30.add($f30);

	        $f7.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f7.setText("$f7=00000000");
	        jPanel30.add($f7);

	        $f15.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f15.setText("$f15=00000000");
	        jPanel30.add($f15);

	        $f23.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f23.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f23.setText("$f23=00000000");
	        jPanel30.add($f23);

	        $f31.setFont(new java.awt.Font("Dialog", 0, 10));
	        $f31.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
	        $f31.setText("$f31=00000000");
	        jPanel30.add($f31);

	        jPanel4.add(jPanel30);

	        panelReg.add(jPanel4);

	        jPanel16.add(panelReg);
	        jPanel16.setFocusable(false);
	}
	
	
	  /**
     *Metodo encargado de controlar los cambios en el comboBox
     *@param ItemEvent evt
     *@return void
     **/
    protected void comboBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBreakpointItemStateChanged
        String[] pcs=controlador.getPc();
        
        if(evt.getStateChange()==java.awt.event.ItemEvent.SELECTED)
        {
            String brk=(String)comboBreakpoint.getSelectedItem();
            int p;
            for(p=0; p<pcs.length;p++)
            {
                if(pcs[p].equals(brk))
                    break;
            }
                       
            breakpoint=new Palabra(INICIO_PC.getDec()+p*4); 
        }              
        
        if(controlador.getDecimalPC()<breakpoint.getDec())
        {
             botonCicloSig.setEnabled(true);
             botonEjecutar.setEnabled(true);             

        }
        else
        {
            botonCicloSig.setEnabled(false);
            botonEjecutar.setEnabled(false);            
            botonCicloAnt.setEnabled(false);
            if(controlador.getDecimalPC()==breakpoint.getDec())
              botonCicloAnt.setEnabled(true);   
        }
    
        
        
    }
    protected void comboBreakpointActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    /**
     *Metodo que contola los cambios en el checkBox
     *@param ItemEvent evt
     *@return void
     **/
    protected void checkBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {
        String[] pcs=controlador.getPc();
            
        if(evt.getStateChange()==java.awt.event.ItemEvent.SELECTED)
        {            
            comboBreakpoint.setEnabled(true);
            comboBreakpoint.removeAllItems();
            int pos=((int)(Pc.getRegistro().getPalabra().getDec()-INICIO_PC.getDec())/4); 
            for(int i=pos+1; i<pcs.length;i++)            
                comboBreakpoint.addItem(pcs[i]);                        
            comboBreakpoint.repaint();
            
            comboBreakpoint.setSelectedIndex(comboBreakpoint.getItemCount()-1);           
            String brk=(String)comboBreakpoint.getSelectedItem();
            int p;
            for(p=0; p<pcs.length;p++)
            {
                if(pcs[p].equals(brk))
                    break;
            }
                       
            // breakpoint=new Palabra(INICIO_PC.getDec()+(comboBreakpoint.getItemCount()-1)*4);
             breakpoint= new Palabra(INICIO_PC.getDec()+(controlador.tamanhoCodigo()-1)*4);                                      
            
            if(controlador.getDecimalPC()>=breakpoint.getDec())
            {
                botonCicloSig.setEnabled(false);
                botonEjecutar.setEnabled(false);
            }
            if(controlador.getDecimalPC()>breakpoint.getDec())
                botonCicloAnt.setEnabled(false);             
            
        }
        else
        {
            comboBreakpoint.setEnabled(false);            
            int tamanho=controlador.getPc().length;
            breakpoint=new Palabra(INICIO_PC.getDec()+tamanho*4);
            botonCicloSig.setEnabled(true);
            botonEjecutar.setEnabled(true);
            if(Pc.getRegistro().getPalabra().getDec()>INICIO_PC.getDec())               
 /**/               botonCicloAnt.setEnabled(true);  
        }
        
         
    
    }

    
	
    public void actualizarCiclos(int numCiclos){
		   if(numCiclos>=1000){
			   ciclos.setFont(new java.awt.Font("Dialog", 1, 26));
		   }else{
			   ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
		   }
		   ciclos.setText(new Integer(numCiclos).toString());
	   }
    
    /**
     *Metodo que vuelve a la ventana del editor
     *@param ActionEvent evt
     *@return void
     **/
    protected abstract void botonVolverEditActionPerformed(java.awt.event.ActionEvent evt);
    /**
     *Metodo que avanza la ejecucin hacia el ciclo siguiente
     *@param ActionEvent evt
     *@return void
     **/
    protected abstract void botonCicloSigActionPerformed(java.awt.event.ActionEvent evt);
    
    
    /**
     *Metodo que hace retroceder la ejecucion hasta el ciclo anterior
     *@param ItemEvent evt
     *@return void
     **/
    protected abstract void botonCicloAntActionPerformed(java.awt.event.ActionEvent evt);
    /**
     *Metodo que completa la ejecucion del codigo
     *@param ActionEvent evt
     *@return void
     **/
    protected abstract void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt);
    
    
    
	
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
	 /**
     *Metodo que deshabilita todos los botones
     *@param sin parametros
     *@return void
     **/
	public void deshabilitaBotones(){
	        botonCicloSig.setEnabled(false);
	        botonCicloAnt.setEnabled(false);
	        botonPasoSig.setEnabled(false);
	        botonPasoAnt.setEnabled(false);
	        botonEjecutar.setEnabled(false);
	        segTexto.setFocusable(false);
	        segTexto.setFocusable(true);
	        segTexto.requestFocus(true);
	}
	
	  /**
     *Cierra la herramienta
     *@param WindowEvent evt
     *@return void
     **/
    protected void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }
    
    
    
    
    
    protected javax.swing.JLabel $a0;
    protected javax.swing.JLabel $a1;
    protected javax.swing.JLabel $a2;
    protected javax.swing.JLabel $a3;
    protected javax.swing.JLabel $at;
    protected javax.swing.JLabel $f0;
    protected javax.swing.JLabel $f1;
    protected javax.swing.JLabel $f10;
    protected javax.swing.JLabel $f11;
    protected javax.swing.JLabel $f12;
    protected javax.swing.JLabel $f13;
    protected javax.swing.JLabel $f14;
    protected javax.swing.JLabel $f15;
    protected javax.swing.JLabel $f16;
    protected javax.swing.JLabel $f17;
    protected javax.swing.JLabel $f18;
    protected javax.swing.JLabel $f19;
    protected javax.swing.JLabel $f2;
    protected javax.swing.JLabel $f20;
    protected javax.swing.JLabel $f21;
    protected javax.swing.JLabel $f22;
    protected javax.swing.JLabel $f23;
    protected javax.swing.JLabel $f24;
    protected javax.swing.JLabel $f25;
    protected javax.swing.JLabel $f26;
    protected javax.swing.JLabel $f27;
    protected javax.swing.JLabel $f28;
    protected javax.swing.JLabel $f29;
    protected javax.swing.JLabel $f3;
    protected javax.swing.JLabel $f30;
    protected javax.swing.JLabel $f31;
    protected javax.swing.JLabel $f4;
    protected javax.swing.JLabel $f5;
    protected javax.swing.JLabel $f6;
    protected javax.swing.JLabel $f7;
    protected javax.swing.JLabel $f8;
    protected javax.swing.JLabel $f9;
    protected javax.swing.JLabel $gp;
    protected javax.swing.JLabel $k0;
    protected javax.swing.JLabel $k1;
    protected javax.swing.JLabel $pc;
    protected javax.swing.JLabel $ra;
    protected javax.swing.JLabel $s0;
    protected javax.swing.JLabel $s1;
    protected javax.swing.JLabel $s2;
    protected javax.swing.JLabel $s3;
    protected javax.swing.JLabel $s4;
    protected javax.swing.JLabel $s5;
    protected javax.swing.JLabel $s6;
    protected javax.swing.JLabel $s7;
    protected javax.swing.JLabel $fp;
    protected javax.swing.JLabel $t9;
    protected javax.swing.JLabel $sp;
    protected javax.swing.JLabel $t0;
    protected javax.swing.JLabel $t1;
    protected javax.swing.JLabel $t2;
    protected javax.swing.JLabel $t3;
    protected javax.swing.JLabel $t4;
    protected javax.swing.JLabel $t5;
    protected javax.swing.JLabel $t6;
    protected javax.swing.JLabel $t7;
    protected javax.swing.JLabel $t8;
    protected javax.swing.JLabel $v0;
    javax.swing.JLabel $v1;
    protected javax.swing.JLabel $zero;
    protected javax.swing.JLabel BadVaddr;
    protected javax.swing.JLabel CAUSE;
    protected javax.swing.JLabel EPC;
    protected javax.swing.JLabel HI;
    protected javax.swing.JLabel LO;
    protected javax.swing.JPanel Panel;
    protected javax.swing.JLabel STATUS;
    protected javax.swing.JButton botonCicloAnt;
    protected javax.swing.JButton botonCicloSig;
    protected javax.swing.JButton botonEjecutar;
    protected javax.swing.JButton botonPasoAnt;
    protected javax.swing.JButton botonPasoSig;
    protected javax.swing.JButton botonVolverEdit;
    protected javax.swing.JLabel caminoDatos;
    protected javax.swing.JCheckBox checkBreakpoint;
    protected javax.swing.JLabel ciclos;
    protected javax.swing.JComboBox comboBreakpoint;
    protected javax.swing.JLabel jLabel10;
    protected javax.swing.JLabel jLabel11;
    protected javax.swing.JLabel jLabel12;
    protected javax.swing.JLabel jLabel13;
    protected javax.swing.JLabel jLabel14;
    protected javax.swing.JLabel jLabel15;
    protected javax.swing.JLabel jLabel16;
    protected javax.swing.JLabel jLabel17;
    protected javax.swing.JLabel jLabel18;
    protected javax.swing.JLabel jLabel19;
    protected javax.swing.JLabel jLabel20;
    protected javax.swing.JLabel jLabel21;
    protected javax.swing.JLabel jLabel22;
    protected javax.swing.JLabel jLabel23;
    protected javax.swing.JLabel jLabel24;
    protected javax.swing.JLabel jLabel25;
    protected javax.swing.JLabel jLabel26;
    protected javax.swing.JLabel jLabel27;
    protected javax.swing.JLabel jLabel28;
    protected javax.swing.JLabel jLabel3;
    protected javax.swing.JLabel jLabel4;
    protected javax.swing.JLabel jLabel5;
    protected javax.swing.JLabel jLabel6;
    protected javax.swing.JLabel jLabel7;
    protected javax.swing.JLabel jLabel8;
    protected javax.swing.JLabel jLabel9;
    protected javax.swing.JPanel jPanel1;
    protected javax.swing.JPanel jPanel13;
    protected javax.swing.JPanel jPanel15;
    protected javax.swing.JPanel jPanel16;
    protected javax.swing.JPanel jPanel17;
    protected javax.swing.JPanel jPanel2;
    protected javax.swing.JPanel jPanel24;
    protected javax.swing.JPanel jPanel25;
    protected javax.swing.JPanel jPanel26;
    protected javax.swing.JPanel jPanel27;
    protected javax.swing.JPanel jPanel28;
    protected javax.swing.JPanel jPanel3;
    protected javax.swing.JPanel jPanel30;
    protected javax.swing.JPanel jPanel31;
    protected javax.swing.JPanel jPanel32;
    protected javax.swing.JPanel jPanel4;
    protected javax.swing.JPanel jPanel5;
    protected javax.swing.JTabbedPane jTabbedPane1;
    protected javax.swing.JScrollPane jScrollPane1;
    protected javax.swing.JScrollPane jScrollPane2;
    protected javax.swing.JPanel panelReg;
    protected javax.swing.JEditorPane segDatos;
    protected javax.swing.JTextPane segTexto;
    protected javax.swing.JPanel panelCamino;
}
