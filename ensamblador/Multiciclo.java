 /**
 *Multiciclo.java
 *Clase encargada de visualizar el procesador multiciclo
 **/

package ensamblador;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import javax.swing.text.*;
import ensamblador.registros.*;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.datos.*;
import ensamblador.instrucciones.*;


public class Multiciclo extends javax.swing.JFrame implements Observer, Tipos {
	Lenguaje lenguaje;
    private Palabra breakpoint;
    private Ensamblador editor;
    Codigo instrucc=Codigo.getInstacia();
    Pc regPC=Pc.getRegistro();
    Instruccion ins;
    int numero;
    int i=0;
    int etapa=0;
    String codigo;
    int contador;
    int num1,num2,num3;
    Memoria memoria=Memoria.getMemoria();
    Pila pila=Pila.getPila();
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
    
     /**
     *Constructor de la clase multiciclo
     **/
    public Multiciclo(Ensamblador edit,int entero1,int entero2,int entero3) {
    	lenguaje=Lenguaje.getInstancia();
    	initComponents();     
        inicializando();
        editor=edit;
        
        num1=entero1;
        num2=entero2;
        num3=entero3;
        setTitle(lenguaje.getString("MULTICICLO")+"  "+editor.getNombreF());        
        codigo=editor.Editor.getText();
        this.setSize(1124, 740);  
        memoria.addObserver(this);
        pila.addObserver(this);
        String m=obtDatos(memoria.visualizar());
        setMem(m);
        String p=obtDatos(pila.visualizar());     
        setPil(p);
        visualizarDatos();
        MAX_CHARACTERS=instrucc.tamanho()*100;        
        this.subrayar("0");
        segTexto.setText(instrucc.visualizar());
        segTexto.requestFocus();
        segTexto.select(0,0);
        ObReg.addObserver(this);
             $sp.setText("$sp="+Sp.getRegistro().getPalabra().getHex().substring(2,10));   
             breakpoint=new Palabra(INICIO_PC.getDec()+instrucc.getPc().length*4);
        los_datos=new Vector();
    }
 
    
    /**
     *Metodo encargado de inicializar los componentes
     *@param sin paramentros
     *@return void
     **/
    private void initComponents() {//GEN-BEGIN:initComponents
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
        comboBreakpoint = new JComboBox(instrucc.getPc());
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
        panelReg.setLayout(new java.awt.GridLayout(1, 0, -200, 0));

        panelReg.setBorder(new javax.swing.border.CompoundBorder());
        panelReg.setMaximumSize(new java.awt.Dimension(420, 420));
        panelReg.setMinimumSize(new java.awt.Dimension(420, 420));
        panelReg.setPreferredSize(new java.awt.Dimension(420, 420));
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

        jPanel16.add(panelReg);

        jPanel25.setLayout(new java.awt.GridLayout(1, 0));

        jPanel25.setBorder(new javax.swing.border.TitledBorder(null, lenguaje.getString("segmentoDeDatos"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel25.setMaximumSize(new java.awt.Dimension(420, 32767));
        jPanel25.setMinimumSize(new java.awt.Dimension(420, 47));
        jPanel25.setPreferredSize(new java.awt.Dimension(420, 44));
        jScrollPane1.setBorder(null);
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        segDatos.setBackground(new java.awt.Color(238, 238, 238));
        segDatos.setEditable(false);
        segDatos.setFont(new java.awt.Font("Dialog", 0, 10));
        jScrollPane1.setViewportView(segDatos);

        jPanel25.add(jScrollPane1);

        jPanel16.add(jPanel25);

        Panel.add(jPanel16);

        jPanel17.setLayout(new java.awt.GridLayout(1, 0));

        jPanel17.add(jPanel15);

        Panel.add(jPanel17);

        jPanel24.setLayout(new javax.swing.BoxLayout(jPanel24, javax.swing.BoxLayout.Y_AXIS));

        jPanel5.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 20));

        jPanel5.setAlignmentX(0.9F);
        jPanel5.setMaximumSize(new java.awt.Dimension(650, 420));
        jPanel5.setMinimumSize(new java.awt.Dimension(650, 420));
        jPanel5.setPreferredSize(new java.awt.Dimension(650, 420));
        caminoDatos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(lenguaje.getString("rutaMulticiclo")+"caminodatos.gif")));
        jPanel5.add(caminoDatos);

        jLabel28.setText("\n\n");
        jPanel5.add(jLabel28);

        jPanel24.add(jPanel5);

        jPanel26.setLayout(new javax.swing.BoxLayout(jPanel26, javax.swing.BoxLayout.X_AXIS));

        jPanel26.setBorder(new javax.swing.border.TitledBorder(null, lenguaje.getString("segmentoDeTexto"), javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));
        jPanel26.setMaximumSize(new java.awt.Dimension(1180, 32767));
        jPanel26.setMinimumSize(new java.awt.Dimension(31, 47));
        jPanel26.setPreferredSize(new java.awt.Dimension(650, 40));
        jScrollPane2.setBorder(null);
        jScrollPane2.setMaximumSize(new java.awt.Dimension(1180, 32767));
        jScrollPane2.setMinimumSize(new java.awt.Dimension(1180, 22));
        jScrollPane2.setPreferredSize(new java.awt.Dimension(90, 15));
        jScrollPane2.setAutoscrolls(true);
        segTexto.setBackground(new java.awt.Color(238, 238, 238));
        segTexto.setEditable(false);
        segTexto.setMaximumSize(new java.awt.Dimension(650, 2147483647));
        segTexto.setMinimumSize(new java.awt.Dimension(650, 15));
        segTexto.setPreferredSize(new java.awt.Dimension(650, 15));
        jScrollPane2.setViewportView(segTexto);

        jPanel26.add(jScrollPane2);

        jPanel24.add(jPanel26);

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
        botonPasoAnt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPasoAntActionPerformed(evt);
            }
        });

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
        botonPasoSig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPasoSigActionPerformed(evt);
            }
        });

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

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel27, java.awt.BorderLayout.EAST);

        getContentPane().add(jPanel28, java.awt.BorderLayout.WEST);

        pack();
    }//GEN-END:initComponents

     /**
     *Metodo que hace retroceder la ejecucion hasta el paso anterior
     *@param ItemEvent evt
     *@return void
     **/    
    private void botonPasoAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPasoAntActionPerformed
        // Add your handling code here:
        
        int excep=-1;
        int nuevo_cont;
        inicializando();     
        if(contador>0)
        {
            inicializando();
            numero=analizaInstruc(ins.numero());
            if(ins.getEtapa()==0)
                contador=contador-ins.numEtapas()-numero+1;
            else if(ins.getEtapa()==4)
                contador=contador-ins.getEtapa()-numero+1;
            else
                contador-=ins.getEtapa();
            nuevo_cont=obtienePaso();           
            contador=nuevo_cont; 
          
            ins=instrucc.obtener((int)regPC.getPalabra().getDec());
            numero=analizaInstruc(ins.numero());
            subrayar(regPC.getPalabra().getHex());        
            excep=ejecutaEtapa(1,ins);
            ins.setEtapa(2);
     
            contador++;            
            
            String imagen=ins.visualizarEtapa();
            imagen=imagen+".gif";
            caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagen.toLowerCase())));     
        
            num_ciclos=contador;
            ciclos.setText(new Integer(num_ciclos).toString());
            botonesSig(true);        
        }
        if(contador==0)
            botonesAnt(false);
        if(contador<ins.numEtapas())
            botonPasoAnt.setEnabled(false);      
    }//GEN-LAST:event_botonPasoAntActionPerformed

     /**
     *Metodo que hace avanzar la ejecucion hasta el paso siguiente
     *@param ItemEvent evt
     *@return void
     **/
    
    private void botonPasoSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPasoSigActionPerformed
        // Add your handling code here:                                
         
        botonesAnt(true);
        long posPc=regPC.getPalabra().getDec();
        int excep=-1;
        int error=0;
        if(seguir(posPc, ins))
        {
           
            if(ins==null || (etapa=ins.getEtapa())==0)
            {
                if(ins!=null) 
                {                    
                    ins.cambiosVisibles();                      
                    ins.setEtapa(1);
                }
                 ins=instrucc.obtener((int)regPC.getPalabra().getDec());
                 subrayar(regPC.getPalabra().getHex());
                 ins.setEtapa(1);
            }            
            int etapa_act=ins.getEtapa();           
            if(etapa_act>=0/*1*/)
            {
                numero=analizaInstruc(ins.numero());
                if(etapa_act!=4)
                    contador=contador+numero-1;
                for(int i=etapa_act;i<=ins.numEtapas();i++) 
                {
                    excep=ejecutaEtapa(i,ins);       /*La excepcion si ocurre es en la ultima etapa*/                     
                    ins.setEtapa(i+1);
                    contador++;
                }
            }
            error=analizaExcep(excep);
            if(error==javax.swing.JOptionPane.ERROR_MESSAGE)
                botonesSig(false);                        
            
        
        
        String imagen=ins.visualizarEtapa();
     
        imagen=imagen+".gif";
        caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagen.toLowerCase())));
        num_ciclos=contador;
        ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
        ciclos.setText(new Integer(num_ciclos).toString());
        }
        if((((regPC.getPalabra().getDec()-regPC.valorBase())/4)>=instrucc.tamanho()) || (regPC.getPalabra().getDec()>=breakpoint.getDec()))
        {
            botonesSig(false);
           
        }
        segTexto.requestFocus();
                                                        
    }//GEN-LAST:event_botonPasoSigActionPerformed

    /**
     *Metodo que se encarga de habilitar o deshabilitar todos los botones de avance
     *@param boolean activo
     *@return void
     **/
    
    public void botonesSig(boolean activo)
    {
         botonCicloSig.setEnabled(activo);
         botonPasoSig.setEnabled(activo);
         botonEjecutar.setEnabled(activo);   
         checkBreakpoint.setEnabled(activo);
         segTexto.requestFocus();
    }
    
    /**
     *Metodo que se encarga de habilitar o deshabilitar todos los botones de retroceso
     *@param boolean activo
     *@return void
     **/
    public void botonesAnt(boolean activo)
    {
         botonCicloAnt.setEnabled(activo);
         botonPasoAnt.setEnabled(activo);         
         segTexto.requestFocus();
    }
    
        /**
     *Metodo encargado de controlar los cambios en el comboBox
     *@param ItemEvent evt
     *@return void
     **/

    private void comboBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBreakpointItemStateChanged
        // Add your handling code here:
        String[] pcs=instrucc.getPc();
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
        if(regPC.getPalabra().getDec()<breakpoint.getDec())
        {                                    
             botonCicloSig.setEnabled(true);
             botonEjecutar.setEnabled(true);             
           //  if(INICIO_PC.getDec()<breakpoint.getDec())
             //   botonCicloAnt.setEnabled(true);
        }
        else
        {
            botonCicloSig.setEnabled(false);
            botonEjecutar.setEnabled(false);            
            botonCicloAnt.setEnabled(false);
            if(regPC.getPalabra().getDec()==breakpoint.getDec())
              botonCicloAnt.setEnabled(true);   
        }
            subrayar(new Palabra(Pc.getRegistro().getPalabra().getDec() -4).getHex());
    }//GEN-LAST:event_comboBreakpointItemStateChanged

    private void comboBreakpointActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBreakpointActionPerformed
    
    }//GEN-LAST:event_comboBreakpointActionPerformed

        /**
     *Metodo que contola los cambios en el checkBox
     *@param ItemEvent evt
     *@return void
     **/
    private void checkBreakpointItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBreakpointItemStateChanged
      
        String[] pcs=instrucc.getPc();        
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
                       
             breakpoint= new Palabra(INICIO_PC.getDec()+(instrucc.tamanho()-1)*4);        
       
           if(regPC.getPalabra().getDec()>=breakpoint.getDec())
            {
                botonCicloSig.setEnabled(false);
                botonEjecutar.setEnabled(false);
            }
            if(regPC.getPalabra().getDec()>breakpoint.getDec())
                botonCicloAnt.setEnabled(false);            
             
        }
        else
        {
            comboBreakpoint.setEnabled(false);
            int tamanho=instrucc.getPc().length;
            breakpoint=new Palabra(INICIO_PC.getDec()+tamanho*4);
            if(Pc.getRegistro().getPalabra().getDec()>INICIO_PC.getDec())
                botonesAnt(true);
            botonesSig(true);            
            
        }
      
            subrayar(new Palabra(Pc.getRegistro().getPalabra().getDec() -4).getHex());
    }//GEN-LAST:event_checkBreakpointItemStateChanged

    /**
     *Metodo que inicializa el valor de todos los componentes
     *@param sin parametros
     *@return void
     **/
    public void inicializando()
    {
        setMem("");
        setPil("");
        memoria.inicializando();
        pila.inicializar();
        Zero.getRegistro().inicializar();
        V0.getRegistro().inicializar();
        V1.getRegistro().inicializar();
        A0.getRegistro().inicializar();
        A1.getRegistro().inicializar();
        A2.getRegistro().inicializar();
        A3.getRegistro().inicializar();
        T0.getRegistro().inicializar();
        T1.getRegistro().inicializar();
        T2.getRegistro().inicializar();
        T3.getRegistro().inicializar();
        T4.getRegistro().inicializar();
        T5.getRegistro().inicializar();
        T6.getRegistro().inicializar();
        T7.getRegistro().inicializar();
        T8.getRegistro().inicializar();
        S0.getRegistro().inicializar();
        S1.getRegistro().inicializar();
        S2.getRegistro().inicializar();
        S3.getRegistro().inicializar();
        S4.getRegistro().inicializar();
        S5.getRegistro().inicializar();
        S6.getRegistro().inicializar();
        S7.getRegistro().inicializar();
        FP.getRegistro().inicializar();
        T9.getRegistro().inicializar();
        Ra.getRegistro().inicializar();
        Pc.getRegistro().inicializar();
        Hi.getRegistro().inicializar();
        Lo.getRegistro().inicializar();
        Status.getRegistro().inicializar();

        F0.getRegistro().inicializar();
        F1.getRegistro().inicializar();
        F2.getRegistro().inicializar();
        F3.getRegistro().inicializar();
        F4.getRegistro().inicializar();
        F5.getRegistro().inicializar();
        F6.getRegistro().inicializar();
        F7.getRegistro().inicializar();
        F8.getRegistro().inicializar();
        F9.getRegistro().inicializar();
        F10.getRegistro().inicializar();
        F11.getRegistro().inicializar();
        F12.getRegistro().inicializar();
        F13.getRegistro().inicializar();
        F14.getRegistro().inicializar();
        F15.getRegistro().inicializar();
        F16.getRegistro().inicializar();
        F17.getRegistro().inicializar();
        F18.getRegistro().inicializar();
        F19.getRegistro().inicializar();
        F20.getRegistro().inicializar();
        F21.getRegistro().inicializar();
        F22.getRegistro().inicializar();
        F23.getRegistro().inicializar();
        F24.getRegistro().inicializar();
        F25.getRegistro().inicializar();
        F26.getRegistro().inicializar();
        F27.getRegistro().inicializar();
        F28.getRegistro().inicializar();
        F29.getRegistro().inicializar();
        F30.getRegistro().inicializar();
        F31.getRegistro().inicializar();
        
    }
    
    /**
     *Metodo que ejecuta un paso
     *@param Instruccion ins
     *@return int contador
     **/
    public int ejecutaPaso(Instruccion ins)
    {
        int exc=0;
        int j=0;
        for(int i=1;i<=ins.numEtapas();i++)            
       {
                exc=ejecutaEtapa(i,ins);
                          
            switch(exc)
            {
                case 5:/*Introduce entero*/
                   
                    Syscall.readInt((String)los_datos.elementAt(j));                      
                    j++;
                    break;
                case 6:/*Introduce string*/
                    Syscall.readString((String)los_datos.elementAt(j));
                    j++;
                    break;
            } 
        }
     /*      while(j<los_datos.size())          
          {
            los_datos.remove(j);    
            j++;
          }*/
            return j;
    }
    
    
    /**
     *Obtiene el paso a ejecutar cuando se retrocede en la ejecucion cuando se trata de una 
     *instruccion de suma,resta,multiplicacion o division
     *@param sin parametros
     *@return int nuevo_contador
     **/
    
    public int obtienePaso()
    {
        int j=0;
        int netapas=0;
        int numero;
        if(ins!=null)
            ins.cambiosVisibles();
        ins=instrucc.obtener((int)regPC.getPalabra().getDec());
        numero=analizaInstruc(ins.numero());
        int nuevo_cont=0;
        while((nuevo_cont+ins.numEtapas()+numero-1)<contador)
        {
            j=ejecutaPaso(ins);
                
            if(!instrucc.esUltima((int)regPC.getPalabra().getDec()-4))
            {   
                nuevo_cont+=ins.numEtapas()+numero-1;
                ins.setEtapa(1);
                ins=instrucc.obtener((int)regPC.getPalabra().getDec());
                numero=analizaInstruc(ins.numero());
                ins.setEtapa(1);
            }
            else
            {
                ins.setEtapa(1);
                return nuevo_cont;
            }            
        }
      ins.setEtapa(1);
        
        return nuevo_cont;
        
    }  
    
    /**
     *Metodo que hace retroceder la ejecucion hasta el ciclo anterior
     *@param ItemEvent evt
     *@return void
     **/
    
    private void botonCicloAntActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCicloAntActionPerformed
      
        botonCicloSig.setEnabled(true);
        int excep=-1;
        int nuevo_cont;
        Palabra contpr=Pc.getRegistro().getPalabra();
        if(contador>0)
        {
            inicializando();
            numero=analizaInstruc(ins.numero());
            contador--;
            if(ins.getEtapa()==4)
                contador=contador-numero+1;
            nuevo_cont=obtienePaso();
            etapa=1;
            while(nuevo_cont<contador)
            {
                numero=analizaInstruc(ins.numero());
                if(etapa==3)
                    nuevo_cont=nuevo_cont+numero-1;
                excep=ejecutaEtapa(etapa, ins);
                etapa++;
                ins.setEtapa(etapa);
                nuevo_cont++;
            }
            ins.cambiosVisibles();
            String imagen=ins.visualizarEtapa();
            Palabra p=Pc.getRegistro().getPalabra();
            //Aqui se hace diferente cuando se trata de un salto condicional
            subrayar(p.sumar(-4).getHex());   
            imagen=imagen+".gif";
            caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagen.toLowerCase())));     
            num_ciclos=nuevo_cont;
            ciclos.setText(new Integer(num_ciclos).toString());
            botonesSig(true);       
        }
        if(contador==0)
        {
            botonCicloAnt.setEnabled(false);
            botonPasoAnt.setEnabled(false);
        }
    
            
    }//GEN-LAST:event_botonCicloAntActionPerformed

        /**
     *Metodo que completa la ejecucion del codigo
     *@param ActionEvent evt
     *@return void
     **/

    private void botonEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEjecutarActionPerformed
        
        botonCicloAnt.setEnabled(true);
        long posPc=regPC.getPalabra().getDec();
        int excep=-1;
        
        while(seguir(regPC.getPalabra().getDec(), ins))
        {
        	if(ins!=null){
        		numero=analizaInstruc(ins.numero());
        		if(ins.getEtapa()==3) //estamos en la etapa de EX --> EX=3
        			contador=contador+numero;
        		else        
        			contador++;
        	}else{
        		contador++;
        	}
            
            if(ins==null || (etapa=ins.getEtapa())==0)
            {                
                if(ins!=null)
                {
                    ins.cambiosVisibles();                    
                }
                ins=instrucc.obtener((int)regPC.getPalabra().getDec());
                subrayar(regPC.getPalabra().getHex());
                ins.setEtapa(etapa++);
                if(contador!=0)
                    botonesAnt(true);
            }           
            if(etapa<=ins.numEtapas())
            {
                excep=ejecutaEtapa(etapa, ins);                                                                          
                etapa++;
                ins.setEtapa(etapa);
            }
           int error=analizaExcep(excep);
            if(error==javax.swing.JOptionPane.ERROR_MESSAGE)
                break;
        }
        String imagen=ins.visualizarEtapa();
        imagen=imagen+".gif";
        caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagen.toLowerCase())));        
        num_ciclos=contador;
        ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
        ciclos.setText(new Integer(num_ciclos).toString());
        
        if((((regPC.getPalabra().getDec()-regPC.valorBase())/4)>=instrucc.tamanho()) || (regPC.getPalabra().getDec()>=breakpoint.getDec()))
        {
            botonesSig(false);
            segTexto.requestFocus();
        }
        if(((regPC.getPalabra().getDec()-regPC.valorBase())/4)<instrucc.tamanho())
            checkBreakpoint.setEnabled(true);
        segTexto.requestFocus();
                           
    }//GEN-LAST:event_botonEjecutarActionPerformed

      /**
     *Metodo que vuelve a la ventana del editor
     *@param ActionEvent evt
     *@return void
     **/
    private void botonVolverEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverEditActionPerformed
 
        this.dispose();
        editor.setVisible(true);        
     
    }//GEN-LAST:event_botonVolverEditActionPerformed


    /**
     *Metodo que ejecuta una etapa
     *@param int etapa, Instruccion ins
     *@return int etapa_siguiente
     **/
    
    public int ejecutaEtapa(int etapa, Instruccion ins)
    {
        int excep=0;    
         switch(etapa)
         {
                    case 1:                        
                        excep=ins.etapa1();
                       break;
                    case 2:
                        ins.cambiosVisibles();
                        excep=ins.etapa2();
                        break;
                    case 3:
                        ins.cambiosVisibles();
                        excep=ins.etapa3();
                         break;
                    case 4:
                        ins.cambiosVisibles();
                        excep=ins.etapa4();
                         break;
                    case 5:
                        ins.cambiosVisibles();
                        excep=ins.etapa5();
                        break;                                               
        }         
         return excep;
    }
    
    public boolean seguir(long posPc, Instruccion ins)
    {
         if((((posPc-regPC.valorBase())/4)<=instrucc.tamanho()) &&(posPc<breakpoint.getDec()))
        {
            return true;
        }
        else
        {
            if((ins!=null && (etapa=ins.getEtapa())==0))
            {
                return false;
            }
            return true;
        }       
    }
    
     /**
     *Metodo que hace avanzar la ejecucion hasta el ciclo siguiente
     *@param ItemEvent evt
     *@return void
     **/
    private void botonCicloSigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCicloSigActionPerformed
        
        botonCicloAnt.setEnabled(true);
        long posPc=regPC.getPalabra().getDec();
        int excep=-1;
        if(seguir(posPc, ins))
        {
            contador++;
            if((ins==null || (etapa=ins.getEtapa())==0))
            {                
                if(ins!=null)
                {
                    ins.cambiosVisibles();
                    
                }
                 ins=instrucc.obtener((int)regPC.getPalabra().getDec());
                 subrayar(regPC.getPalabra().getHex());
                 ins.setEtapa(etapa++);
                 if(contador!=0)
                     botonesAnt(true);
            }   
            if(etapa<=ins.numEtapas())
            {
                excep=ejecutaEtapa(etapa, ins);                                                                          
                etapa++;
                ins.setEtapa(etapa);
            }
           
        String imagen=ins.visualizarEtapa();
       
        analizaExcep(excep);
        
        imagen=imagen+".gif";
        
        caminoDatos.setIcon(new javax.swing.ImageIcon(getClass().getResource(imagen.toLowerCase())));  
        numero=analizaInstruc(ins.numero());
        if(ins.getEtapa()==4)
            contador=contador+numero-1;
        num_ciclos=contador;
        ciclos.setFont(new java.awt.Font("Dialog", 1, 36));
        if(ins.getEtapa()==4)
            ciclos.setText(new Integer(num_ciclos).toString());
        else
            ciclos.setText(new Integer(num_ciclos).toString());
        }
        else            
        {
            botonesSig(true);                     
            segTexto.requestFocus();
        }      
        if((((regPC.getPalabra().getDec()-regPC.valorBase())/4)>=instrucc.tamanho()) || (regPC.getPalabra().getDec()>=breakpoint.getDec()))
        {
            if(ins.getEtapa()==0)
                botonesSig(false);
            else
                botonesSig(true);
            segTexto.requestFocus();
        }
        if(((regPC.getPalabra().getDec()-regPC.valorBase())/4)<instrucc.tamanho())
            checkBreakpoint.setEnabled(true);
       
        segTexto.requestFocus();
    }//GEN-LAST:event_botonCicloSigActionPerformed
    
    /**
     *Metodo que destaca la instruccion que esta siendo ejecutada
     *@param String valorPC
     *@return void
     **/   
      public void subrayar(String valorPC)
    {           
        int inicio;
        int fin;
        segTexto.setText(instrucc.visualizar());
        if(contador>0)
        {
            inicio=segTexto.getText().indexOf(valorPC);
            fin=segTexto.getText().indexOf("\n",inicio);                   
        }
        else
        {
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
    protected void initDocument(int inicio, int fin) 
    {
        String initString =instrucc.visualizar();
		
	String[] styleNames = inicEstilos();
        String texto=instrucc.visualizar();
        int breakP_ini;
        Palabra ultimoBreak= new Palabra(INICIO_PC.getDec()+(instrucc.tamanho()-1)*4);        
            
        if(checkBreakpoint.isSelected() && (breakpoint.getDec()<=ultimoBreak.getDec()))       
            breakP_ini=texto.indexOf(breakpoint.getHex());
        
        else
        {
            breakpoint=new Palabra(INICIO_PC.getDec()+instrucc.tamanho()*4);        
            breakP_ini=texto.length();
        }
         try {
            if(contador>1)
                lpd.insertString(lpd.getLength(), texto.substring(0, inicio-1),segTexto.getStyle(styleNames[0]));
            if(contador>0)
            {
                if(contador==1)
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
	//Initialize some styles
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
            la_pila+="[0x70000000]";
          
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
        if(!(arg instanceof Vector))
        {
            Registro r=(Registro) arg;
            if(r.letraReg()=='F')
                visualizaRegF(r.valorHex(),r.numReg());
            else
                visualizaReg(r.valorHex(),r.numReg());
        }
        else
        {
            String datos=new String();
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
    
    /**
     *Metodo que informa de que se ha producido una excepcion
     *@param int opcion
     *@return int error
     **/
    public int analizaExcep(int opcion)
    {
        int tipoError=javax.swing.JOptionPane.WARNING_MESSAGE;
        if(opcion!=-1)
        {            
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
                    JOptionPane.showMessageDialog(this, Syscall.print(4), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 5:                    
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String re=JOptionPane.showInputDialog(this,lenguaje.getString("question5"),lenguaje.getString("entrada"),tipoError);                      
                    if(re!=null)
                    {
                        los_datos.addElement(re);
                        Syscall.readInt(re);
                    }
                    break; 
                    
                case 6:
                	int errLec;
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String res=JOptionPane.showInputDialog(this,lenguaje.getString("question6"),lenguaje.getString("entrada"),tipoError);                    
                    if(res!=null)
                    {
                        los_datos.addElement(res);
                        errLec =Syscall.readString(res);
                        this.analizaExcep(errLec);
                    }
                    break;
                    
                case 7:
                    tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog(this, Syscall.print(7), lenguaje.getString("resultado"), tipoError);
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
                    JOptionPane.showMessageDialog(this, Syscall.print(10), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 11:
                    tipoError=javax.swing.JOptionPane.INFORMATION_MESSAGE;
                    JOptionPane.showMessageDialog(this, Syscall.print(11), lenguaje.getString("resultado"), tipoError);
                    break;
                    
                case 12:
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String resu=JOptionPane.showInputDialog(this,lenguaje.getString("question12"),lenguaje.getString("entrada"),tipoError);
                     if(resu!=null)
                    {
                        los_datos.addElement(resu);
                        Syscall.readFloat(resu);
                    }
                    break;
                   
                case 13:
                    tipoError=javax.swing.JOptionPane.QUESTION_MESSAGE;
                    String resul=JOptionPane.showInputDialog(this,lenguaje.getString("question13"),lenguaje.getString("entrada"),tipoError);
                     if(resul!=null)
                    {
                        los_datos.addElement(resul);
                        Syscall.readDouble(resul);
                    }
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
    public void deshabilitaBotones()
    {
        botonCicloSig.setEnabled(false);
        botonCicloAnt.setEnabled(false);
        botonPasoSig.setEnabled(false);
        botonPasoAnt.setEnabled(false);
        botonEjecutar.setEnabled(false);
    }
     
    public int analizaInstruc(int i)
    {     
        int ciclos;
        switch(i)
        {
            case ADDS:
                ciclos=num1;
                break;
                        
            case ADDD:
                ciclos=num1;
                break;
                     
            case SUBS:         
                ciclos=num1;
                break;
                        
            case SUBD:         
                ciclos=num1;
                break;
                        
            case MULS:
                ciclos=num2;                         
                break;
                       
            case MULD:
                ciclos=num2;                        
                break;  
                        
            case DIVS:
                ciclos=num3;                       
                break;  
                            
            case DIVD:
                ciclos=num3;
                break;
                    
            default:
                ciclos=1;
                break;
        }
        return ciclos;
    }
     /**
     *Cierra la herramienta
     *@param WindowEvent evt
     *@return void
     **/

    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        System.exit(0);
    }//GEN-LAST:event_exitForm
    
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
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
    private javax.swing.JLabel caminoDatos;
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
    private javax.swing.JLabel jLabel28;
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
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel panelReg;
    private javax.swing.JEditorPane segDatos;
    private javax.swing.JTextPane segTexto;
    // End of variables declaration//GEN-END:variables
    
}
