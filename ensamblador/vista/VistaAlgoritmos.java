package ensamblador.vista;

import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.util.Hashtable;
import java.util.Observable;
import java.util.Observer;
import java.util.Vector;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;

import ensamblador.Ensamblador;
import ensamblador.LimitedStyledDocument;
import ensamblador.ObservaRegistros;

import ensamblador.controlador.ControladorDinam;
import ensamblador.controlador.ControladorMarcador;
import ensamblador.controlador.ControladorTomasulo;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Tipos;
import ensamblador.estado.EstadoInstrucciones;
import ensamblador.estado.EstadoRegistros;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Nop;
import ensamblador.planificacionDinamica.marcador.Marcador;
import ensamblador.planificacionDinamica.tomasulo.Tomasulo;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Sp;
//import ensamblador.util.Messages;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.util.excepcion.SyscallException;
import ensamblador.datos.Lenguaje;

public class VistaAlgoritmos extends javax.swing.JFrame implements Observer, Tipos {
	private Lenguaje lenguaje;
	private PanelAlgoritmo panelAlgoritmo;
	private int numeroCiclos=0;

    private Palabra breakpoint;
    private Ensamblador editor;

    Instruccion ins;
    int i=0;

    int contador;

    Registro registro;
    int num_ciclos;
    private String la_memoria;
    private String la_pila;
    private Vector los_datos;
    LimitedStyledDocument lpd;
    static int MAX_CHARACTERS;
    Hashtable actions;
    String newline;
    
    
    ObservaRegistros ObReg=new ObservaRegistros();
    JPanel panelTablas;
    
    private ControladorDinam controlador;
    
    public VistaAlgoritmos(Ensamblador edit, int tipoAlgoritmo, Vector<Vector> fus) {
        editor=edit;
        lenguaje = Lenguaje.getInstancia();
        
        
        if(Tipos.MARCADOR==tipoAlgoritmo){
        	panelAlgoritmo=new PanelMarcador(getNumFUs(fus, 0));
        	setTitle(lenguaje.getString("MARCADOR")+"  "+editor.getNombreF());
            controlador=new ControladorMarcador(fus);
        }else{
        	panelAlgoritmo=new PanelTomasulo(getNumFUs(fus, 1));
        	setTitle(lenguaje.getString("TOMASULO")+"  "+editor.getNombreF());
            controlador=new ControladorTomasulo(fus);
        }   

        panelTablas=panelAlgoritmo;

            initComponents();     
            inicializando();

        this.setSize(1064, 720);  
        

        controlador.anhadirObservador(this);
        setMem(obtDatos(controlador.visualizarMemoria()));
        setPil(obtDatos(controlador.visualizarPila()));
        visualizarDatos();
        
        MAX_CHARACTERS=controlador.tamanhoCodigo()*100;        

    
        $sp.setText("$sp="+Sp.getRegistro().getPalabra().getHex().substring(2,10));
        

        breakpoint=new Palabra(INICIO_PC.getDec()+controlador.getPc().length*4);
        
        los_datos=new Vector();
    }
 
    
    private int getNumFUs(Vector<Vector>fus, int algoritmo){
    	Vector fu;
    	int contador=0;
    	for(int i=0;i<fus.size();i++){
    		fu=fus.elementAt(i);
    		contador+=((Integer)fu.elementAt(algoritmo)).intValue();
    	}
    	return contador;
    }
    
    /**
     *Metodo encargado de inicializar los componentes
     *@param sin paramentros
     *@return void
     **/
    private void initComponents() {
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
        botonEstadisticasRiesgos = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        checkBreakpoint = new javax.swing.JCheckBox();
        comboBreakpoint = new JComboBox(controlador.getPc());
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

        jPanel16.setMaximumSize(new java.awt.Dimension(420, 33187));
        jPanel16.setMinimumSize(new java.awt.Dimension(420, 427));
        jPanel16.setPreferredSize(new java.awt.Dimension(420, 425));
        panelReg.setLayout(new java.awt.GridLayout(1, 0, -200, 0));

        panelReg.setBorder(javax.swing.BorderFactory.createCompoundBorder());
        panelReg.setMaximumSize(new java.awt.Dimension(420, 420));
        panelReg.setMinimumSize(new java.awt.Dimension(420, 420));
        panelReg.setPreferredSize(new java.awt.Dimension(420, 420));
        jPanel4.setLayout(new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.Y_AXIS));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), lenguaje.getString("registros2"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
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
        STATUS.setText("Status=00000000");
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

        $s7.setFont(new java.awt.Font("Dialog", 0, 10));
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

        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), 
        		lenguaje.getString("segmentoDeDatos"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel25.setMaximumSize(new java.awt.Dimension(420, 32767));
        jPanel25.setMinimumSize(new java.awt.Dimension(420, 47));
        jPanel25.setPreferredSize(new java.awt.Dimension(420, 44));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        segDatos.setBackground(new java.awt.Color(238, 238, 238));
        segDatos.setEditable(false);
        segDatos.setFont(new java.awt.Font("Dialog", 0, 10));
        jScrollPane1.setViewportView(segDatos);

        org.jdesktop.layout.GroupLayout jPanel25Layout = new org.jdesktop.layout.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 368, Short.MAX_VALUE)
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 106, Short.MAX_VALUE)
        );

        org.jdesktop.layout.GroupLayout jPanel16Layout = new org.jdesktop.layout.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel16Layout.createSequentialGroup()
                .add(jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jPanel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(panelReg, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel16Layout.createSequentialGroup()
                .add(panelReg, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 132, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        Panel.add(jPanel16);

        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        jPanel17.add(jPanel15);

        Panel.add(jPanel17);

        
        

        
        

        jPanel26.setLayout(new javax.swing.BoxLayout(jPanel26, javax.swing.BoxLayout.X_AXIS));

        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createEtchedBorder(), 
        		lenguaje.getString("segmentoDeTexto"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel26.setMaximumSize(new java.awt.Dimension(1180, 32767));
        jPanel26.setMinimumSize(new java.awt.Dimension(31, 47));
        jPanel26.setPreferredSize(new java.awt.Dimension(640, 40));
        jScrollPane2.setBorder(null);
        jScrollPane2.setAutoscrolls(true);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(1180, 32767));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1180, 22));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(90, 15));
        segTexto.setBackground(new java.awt.Color(238, 238, 238));
        segTexto.setEditable(false);
        segTexto.setMinimumSize(new java.awt.Dimension(650, 15));
        segTexto.setPreferredSize(new java.awt.Dimension(650, 15));
        jScrollPane2.setViewportView(segTexto);

        jPanel26.add(jScrollPane2);

        org.jdesktop.layout.GroupLayout jPanel24Layout = new org.jdesktop.layout.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel24Layout.createSequentialGroup()
                .add(jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING, false)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel26, 0, 0, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.LEADING, jPanel24Layout.createSequentialGroup()
                        .add(1, 1, 1)
                        .add(panelAlgoritmo, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel24Layout.createSequentialGroup()
                .add(panelAlgoritmo, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jPanel26, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 134, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        );
        Panel.add(jPanel24);

        getContentPane().add(Panel, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

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

        
        botonEstadisticasRiesgos.setFont(new java.awt.Font("Dialog", 1, 11));
        
        botonEstadisticasRiesgos.setText(lenguaje.getString("informe"));
        botonEstadisticasRiesgos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        botonEstadisticasRiesgos.setMargin(new java.awt.Insets(0, 0, 0, 0));
        botonEstadisticasRiesgos.setMaximumSize(new java.awt.Dimension(20, 15));
        botonEstadisticasRiesgos.setMinimumSize(new java.awt.Dimension(10, 15));
        botonEstadisticasRiesgos.setPreferredSize(new java.awt.Dimension(15, 15));
        botonEstadisticasRiesgos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        botonEstadisticasRiesgos.setEnabled(false);
        botonEstadisticasRiesgos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEstadisticasRiesgosActionPerformed(evt);
            }

			
        });

        jPanel1.add(botonEstadisticasRiesgos);

        
        
        
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

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel27, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel28, java.awt.BorderLayout.WEST);

        pack();
    }

    /**
     *Metodo encargado de controlar los cambios en el comboBox
     *@param ItemEvent evt
     *@return void
     **/
    private void comboBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBreakpointItemStateChanged
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

        subrayar();
    }

    private void comboBreakpointActionPerformed(java.awt.event.ActionEvent evt) {
        
    }

    /**
     *Metodo que contola los cambios en el checkBox
     *@param ItemEvent evt
     *@return void
     **/
    private void checkBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBreakpointItemStateChanged
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
        
         

        subrayar();
    }

    
    /**
     *Metodo que inicializa el valor de todos los componentes
     *@param sin parametros
     *@return void
     **/
    public void inicializando(){
        setMem("");
        setPil("");
        controlador.inicializar();
     }
    
    private void botonEstadisticasRiesgosActionPerformed(ActionEvent evt) {
        new DialogoInforme(this, true);
	}
    
    /**
     *Metodo que hace retroceder la ejecucion hasta el ciclo anterior
     *@param ItemEvent evt
     *@return void
     **/
    private void botonCicloAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCicloAntActionPerformed
        botonCicloSig.setEnabled(true);
        botonEjecutar.setEnabled(true);
        panelAlgoritmo.inicializarTablas();
        try{
            if(controlador.ejecutarCicloAnterior(breakpoint)){
            	contador++;
            	   num_ciclos=contador;
            	   this.setNumeroCiclos(this.getNumeroCiclos()-1);
                   ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
                   ciclos.setText(new Integer(controlador.getCiclos()).toString());
                   if(controlador.getCiclos()==0){
                	   botonCicloAnt.setEnabled(false);
                   }

                   subrayar();
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
    private void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
    	
    	try{
    		subrayar();
    		num_ciclos=controlador.ejecutar(breakpoint);
    		this.setNumeroCiclos(controlador.getCiclos());
    		contador=num_ciclos;

    	 }catch(EjecucionException e){
    		 this.setNumeroCiclos(controlador.getCiclos());
    		 analizaExcep(e.getTipo(), e);

         }
        
    }

    /**
     *Metodo que vuelve a la ventana del editor
     *@param ActionEvent evt
     *@return void
     **/
    private void botonVolverEditActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        editor.setVisible(true);        
     
    }

    /**
     *Metodo que avanza la ejecuci�n hacia el ciclo siguiente
     *@param ActionEvent evt
     *@return void
     **/
    private void botonCicloSigActionPerformed(java.awt.event.ActionEvent evt) {
       
        botonCicloAnt.setEnabled(true);
        
        try{
        if(controlador.ejecutarCiclo(breakpoint)){
        	contador++;
        	   num_ciclos=contador;
        	   this.setNumeroCiclos(this.getNumeroCiclos()+1);
               ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
               ciclos.setText(new Integer(controlador.getCiclos()).toString());
            
               subrayar();
        }
        }catch(EjecucionException e){
        	this.setNumeroCiclos(controlador.getCiclos());
        	
        	this.analizaExcep(e.getTipo(), e);
        }
       
    }
    
    
    
    /**
     *Metodo que destaca la instruccion que esta siendo ejecutada
     *@param String valorPC
     *@return void
     **/   
    public void subrayar()
    {           
        int longTexto=controlador.visualizarCodigo().length();
        segTexto.setText(controlador.visualizarCodigo());
       
        lpd = new LimitedStyledDocument(MAX_CHARACTERS);
    
        segTexto.setDocument(lpd);
        segTexto.setCaretPosition(0);
        segTexto.setMargin(new Insets(5,5,5,5));             
	initDocument(segTexto, longTexto);         
    }
    
  
     /**
     *Metodo que inicializa el documento del segmento de texto
     *@param int inicio, int fin
     *return void
     **/
    protected void initDocument(JTextPane segTexto, int longTexto) 
    {
    	int sel=0;
        String initString =controlador.visualizarCodigo();	
	String[] styleNames = inicEstilos();
        String texto=controlador.visualizarCodigo();
        String pcs[]=controlador.getPc();
        
        int inicio;
        int fin;
        int bpoint;
        int pcInst;

        try
        {
            for (int i=0; i<pcs.length;i++)
            {
                inicio=texto.indexOf(pcs[i]);
                fin=texto.indexOf("\n",inicio);
                pcInst=(int)(new Palabra(pcs[i],true)).getDec();
                
                	if(controlador.estaEjecutando(pcs[i])){

                    lpd.insertString(lpd.getLength(), texto.substring(inicio-4,fin+1),segTexto.getStyle(styleNames[1]));                
                        if(!(controlador.getInstruccion(pcInst) instanceof Nop))
                            sel=inicio+200;                        
                        if (sel>=longTexto)
                            sel=segTexto.getText().length()-1;
                   
                }
                else    
                {
                    bpoint=(int)(breakpoint.getDec());                                
                    if(pcInst<bpoint)                
                        lpd.insertString(lpd.getLength(), texto.substring(inicio-4, fin+1),segTexto.getStyle(styleNames[0]));                
                    else                
                        lpd.insertString(lpd.getLength(),  texto.substring(inicio-4, fin+1),segTexto.getStyle(styleNames[2]));                
                }

            }
            segTexto.select(sel, sel);
        } catch (BadLocationException ble) {System.err.println("Error al insertar el texto");}
     }
    /**
     *Metodo que inicializa los estilos asociados al documento de segmento de texto
     *@param sin parametros
     *@return String[] estilos
     **/
      protected String[] inicEstilos() {	
	String NombreEstilos[] = { "default", "selec", "break" };

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
	return NombreEstilos;
    }
    

  
      /**
       *Metodo que se encarga de dar formato a la informaci�n del segmento de datos
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
    	

    	if(p instanceof EstadoRegistros){
    		Vector<JTable> tablas=controlador.updateEstadoRegistros((Hashtable)arg, 
    				panelAlgoritmo.getTableRegistros());
    		
    		panelAlgoritmo.setTableRegistros(tablas.elementAt(0), tablas.elementAt(1));

    		panelTablas=panelAlgoritmo;
    		panelTablas.setVisible(true);    		
    	}else if((p instanceof Marcador) ||(p instanceof Tomasulo)){
    	
    		controlador.updateFUS((Vector)arg, 
    				panelAlgoritmo.getTableFUs());
    		
 
    		panelTablas=panelAlgoritmo;
    		panelTablas.setVisible(true);
    	}else if(p instanceof EstadoInstrucciones){

    		controlador.updateInstrucciones((Vector)arg, 
			panelAlgoritmo.getTableInstrucciones());
    		panelTablas=panelAlgoritmo;
    		panelTablas.setVisible(true);
    	}else if(!(arg instanceof Vector))
    	{
    		Registro r=(Registro) arg;
    		if(r.letraReg()=='F')
    			visualizaRegF(r.valorHex(),r.numReg());
    		else
    			visualizaReg(r.valorHex(),r.numReg());
    	}else{
    		String datos;
    		if(p instanceof Pila)
    		{
    			datos=obtPila((Vector)arg);
    			setPil(datos);
    		}
    		else
    		{
    			datos=obtDatos((Vector)arg);
    			setMem(datos);
    		}
    		visualizarDatos();
    	}
    }
    
    public void actualizaVista(Vector registros){
    	
    }
    
    /**
     *Metodo que informa de que se ha producido una excepcion
     *@param int opcion
     *@return int error
     **/
    public int analizaExcep(int opcion, EjecucionException e)
    {
        int tipoError=javax.swing.JOptionPane.WARNING_MESSAGE;
        ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
        ciclos.setText(new Integer(this.getNumeroCiclos()).toString());
        if(opcion!=-1)
        {            
        	subrayar();
            String error=new String();
            switch(opcion)
            {
                case 0:
                    error=lenguaje.getString("error0");
                    tipoError=javax.swing.JOptionPane.WARNING_MESSAGE;
                    JOptionPane.showMessageDialog(this, error, lenguaje.getString("excepcion"), tipoError);
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
                    String re=JOptionPane.showInputDialog(this,lenguaje.getString("question5"),lenguaje.getString("entrada"),tipoError);
                     if(re!=null){
                    	 controlador.readDato(re, Tipos.INTEGER);
                    }
                    break;
                    
                case 6:
                	int errLec;
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String res=JOptionPane.showInputDialog(this,lenguaje.getString("question6"),lenguaje.getString("entrada"),tipoError);
                    if(res!=null){
                    	errLec =controlador.readDato(res, Tipos.STRING);
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
                    botonEstadisticasRiesgos.setEnabled(true);
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
                    String resu=JOptionPane.showInputDialog(this,lenguaje.getString("question12"),lenguaje.getString("entrada"),tipoError);
                     if(resu!=null){
                    	 controlador.readDato(resu, Tipos.FLOAT);
                    }
                    break;
                   
                case 13:
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String resul=JOptionPane.showInputDialog(this,lenguaje.getString("question13"),lenguaje.getString("entrada"),tipoError);
                     if(resul!=null){
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
                    ciclos.setText(new Integer(this.getNumeroCiclos()).toString());
                	deshabilitaBotones();
                	botonEstadisticasRiesgos.setEnabled(true);
                	this.botonCicloAnt.setEnabled(true);
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
        return tipoError;
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
    }
      
    /**
     *Cierra la herramienta
     *@param WindowEvent evt
     *@return void
     **/
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }
    
    

    private javax.swing.JLabel $a0;
    private javax.swing.JLabel $a1;
    private javax.swing.JLabel $a2;
    private javax.swing.JLabel $a3;
    private javax.swing.JLabel $at;
    private javax.swing.JLabel $f0;
    private javax.swing.JLabel $f1;
    private javax.swing.JLabel $f10;
    private javax.swing.JLabel $f11;
    private javax.swing.JLabel $f12;
    private javax.swing.JLabel $f13;
    private javax.swing.JLabel $f14;
    private javax.swing.JLabel $f15;
    private javax.swing.JLabel $f16;
    private javax.swing.JLabel $f17;
    private javax.swing.JLabel $f18;
    private javax.swing.JLabel $f19;
    private javax.swing.JLabel $f2;
    private javax.swing.JLabel $f20;
    private javax.swing.JLabel $f21;
    private javax.swing.JLabel $f22;
    private javax.swing.JLabel $f23;
    private javax.swing.JLabel $f24;
    private javax.swing.JLabel $f25;
    private javax.swing.JLabel $f26;
    private javax.swing.JLabel $f27;
    private javax.swing.JLabel $f28;
    private javax.swing.JLabel $f29;
    private javax.swing.JLabel $f3;
    private javax.swing.JLabel $f30;
    private javax.swing.JLabel $f31;
    private javax.swing.JLabel $f4;
    private javax.swing.JLabel $f5;
    private javax.swing.JLabel $f6;
    private javax.swing.JLabel $f7;
    private javax.swing.JLabel $f8;
    private javax.swing.JLabel $f9;
    private javax.swing.JLabel $gp;
    private javax.swing.JLabel $k0;
    private javax.swing.JLabel $k1;
    private javax.swing.JLabel $pc;
    private javax.swing.JLabel $ra;
    private javax.swing.JLabel $s0;
    private javax.swing.JLabel $s1;
    private javax.swing.JLabel $s2;
    private javax.swing.JLabel $s3;
    private javax.swing.JLabel $s4;
    private javax.swing.JLabel $s5;
    private javax.swing.JLabel $s6;
    private javax.swing.JLabel $s7;
    private javax.swing.JLabel $fp;
    private javax.swing.JLabel $t9;
    private javax.swing.JLabel $sp;
    private javax.swing.JLabel $t0;
    private javax.swing.JLabel $t1;
    private javax.swing.JLabel $t2;
    private javax.swing.JLabel $t3;
    private javax.swing.JLabel $t4;
    private javax.swing.JLabel $t5;
    private javax.swing.JLabel $t6;
    private javax.swing.JLabel $t7;
    private javax.swing.JLabel $t8;
    private javax.swing.JLabel $v0;
    javax.swing.JLabel $v1;
    private javax.swing.JLabel $zero;
    private javax.swing.JLabel BadVaddr;
    private javax.swing.JLabel CAUSE;
    private javax.swing.JLabel EPC;
    private javax.swing.JLabel HI;
    private javax.swing.JLabel LO;
    private javax.swing.JPanel Panel;
    private javax.swing.JLabel STATUS;
    private javax.swing.JButton botonCicloAnt;
    private javax.swing.JButton botonCicloSig;
    private javax.swing.JButton botonEjecutar;
    private javax.swing.JButton botonPasoAnt;
    private javax.swing.JButton botonPasoSig;
    private javax.swing.JButton botonVolverEdit;
    private javax.swing.JButton botonEstadisticasRiesgos;
    private javax.swing.JCheckBox checkBreakpoint;
    private javax.swing.JLabel ciclos;
    private javax.swing.JComboBox comboBreakpoint;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel4;

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;

    private javax.swing.JPanel panelReg;
    private javax.swing.JEditorPane segDatos;
    private javax.swing.JTextPane segTexto;


	public int getNumeroCiclos() {
		return numeroCiclos;
	}


	public void setNumeroCiclos(int numeroCiclos) {
		this.numeroCiclos = numeroCiclos;
	}

    
}
