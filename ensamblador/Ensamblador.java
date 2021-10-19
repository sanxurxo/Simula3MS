package ensamblador;

import java.awt.FileDialog;
import java.awt.Frame;
import java.awt.Insets;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.ServiceUI;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.Document;
import javax.swing.undo.UndoManager;

import ensamblador.datos.Lenguaje;
import ensamblador.datos.Tipos;
import ensamblador.vista.DialogoMarcador;
import ensamblador.vista.DialogoTomasulo;
import ensamblador.vista.GuiaRapida;
import ensamblador.vista.VistaAlgoritmos;
import ensamblador.vista.VistaMonoESInterrup;
import ensamblador.vista.VistaMonoESProg;
import ensamblador.vista.VistaMonociclo;
import ensamblador.vista.VistaSegmentado;


/**Ensamblador.java
 *Clase del editor y ensamblador de la herramienta
 **/
public class Ensamblador extends javax.swing.JFrame implements Observer{
    String NombreFich=new String("");
    String nombreF=new String("");
    boolean modificar=false;
    String listaErrores=new String();
    StringTokenizer st;
    StringTokenizer st2;
    public UndoManager undoManager;
    public UndoLastAction undoAction;
    public RedoAction redoAction;
    int entero_uno=1, entero_dos=1, entero_tres=1;
    private Vector<Vector> fus;
    private Vector<Integer> tiposFus;
    private Vector<Vector> ers;
    protected   Ensamblar ensambla;
    
    private Lenguaje lenguaje;
    
/**Constructor de Ensamblador
 *@param Sin parametros
 **/
    public Ensamblador() { 
    	lenguaje = Lenguaje.getInstancia();
        initComponents();
        fus=new Vector<Vector>();
        tiposFus = new Vector<Integer>();
        ers=new Vector<Vector>();
        Editor.requestFocus();
        setSize(550, 640);
        this.setLocation(200, 50);
        Document documento=Editor.getDocument();
        undoManager=new UndoManager();
        undoAction=new UndoLastAction();
        redoAction=new RedoAction();
        documento.addUndoableEditListener(new UndoableEditListener(){
          public void undoableEditHappened(UndoableEditEvent e){
              undoManager.addEdit(e.getEdit());
              undoAction.update();
              redoAction.update();
          }
        });
        
    }
    
   
    private void initComponents() {//GEN-BEGIN:initComponents
        jPanel4 = new javax.swing.JPanel();
        grupoBotones = new javax.swing.ButtonGroup();
        grupoBotonesSalto = new javax.swing.ButtonGroup();
        grupoBotonesES = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Editor = new javax.swing.JTextArea();
        jPanel7 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        Linea = new javax.swing.JTextField();
        jPanel16 = new javax.swing.JPanel();
        jPanel17 = new javax.swing.JPanel();
        jPanel18 = new javax.swing.JPanel();
        Ensamblar = new javax.swing.JButton();
        jPanel19 = new javax.swing.JPanel();
        jPanel21 = new javax.swing.JPanel();
        Ejecutar = new javax.swing.JButton();
        jPanel22 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jToolBar1 = new javax.swing.JToolBar();
        BotonNuevo = new javax.swing.JButton();
        BotonAbrir = new javax.swing.JButton();
        BotonGuardar = new javax.swing.JButton();
        BotonGuardarComo = new javax.swing.JButton();
        BotonImprimir = new javax.swing.JButton();
        jToolBar2 = new javax.swing.JToolBar();
        BotonCopiar = new javax.swing.JButton();
        BotonCortar = new javax.swing.JButton();
        BotonPegar = new javax.swing.JButton();
        jToolBar3 = new javax.swing.JToolBar();
        BotonDeshacer = new javax.swing.JButton();
        BotonRehacer = new javax.swing.JButton();
        BotonBuscar = new javax.swing.JButton();
        jToolBar4 = new javax.swing.JToolBar();
        botonError = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Errores = new javax.swing.JEditorPane();
        jPanel27 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel13 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        BarraMenuComp = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        ItemNuevo = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JSeparator();
        ItemAbrir = new javax.swing.JMenuItem();
        ItemGuardar = new javax.swing.JMenuItem();
        ItemGuardarComo = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JSeparator();
        ItemImprimir = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JSeparator();
        ItemSalir = new javax.swing.JMenuItem();
        MenuEdicion = new javax.swing.JMenu();
        ItemDeshacer = new javax.swing.JMenuItem();
        ItemRehacer = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JSeparator();
        ItemSeleccionarTodo = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JSeparator();
        ItemCopiar = new javax.swing.JMenuItem();
        ItemCortar = new javax.swing.JMenuItem();
        ItemPegar = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JSeparator();
        MenuBuscar = new javax.swing.JMenu();
        ItemLinea = new javax.swing.JMenuItem();
        ItemTexto = new javax.swing.JMenuItem();
        ItemReemplazar = new javax.swing.JMenuItem();
        MenuConfiguracion = new javax.swing.JMenu();
        
        menuEntradaSalida = new javax.swing.JMenu();
        itemMapeada = new javax.swing.JRadioButtonMenuItem();
        itemInterrupciones = new javax.swing.JRadioButtonMenuItem();
        itemDesactivada = new javax.swing.JRadioButtonMenuItem();
        menuCaminoDatos = new javax.swing.JMenu();
        itemMonociclo = new javax.swing.JRadioButtonMenuItem();
        itemMulticiclo = new javax.swing.JRadioButtonMenuItem();
        menuSegmentado = new javax.swing.JMenu();
        itemSegmentado = new javax.swing.JRadioButtonMenuItem();
        itemMarcador = new javax.swing.JRadioButtonMenuItem();
        itemTomasulo = new javax.swing.JRadioButtonMenuItem();
        
        menuSalto = new javax.swing.JMenu();
        itemSaltoRetardadoFlotante = new javax.swing.JRadioButtonMenuItem();
        itemSaltoFijoFlotante = new javax.swing.JRadioButtonMenuItem();
        

        
        MenuAyuda = new javax.swing.JMenu();
        ItemAcercaDe = new javax.swing.JMenuItem();
        ItemAyuda = new javax.swing.JMenuItem();
        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);        
        setTitle("Simula3MS");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                exitForm(evt);
            }           
        });

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel6.setLayout(new java.awt.GridLayout(1, 0));

        Editor.setTabSize(10);
        Editor.setWrapStyleWord(true);
        Editor.setFocusCycleRoot(true);
        Editor.setFocusTraversalPolicy(getFocusTraversalPolicy());
        Editor.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                HaCambiado(evt);
            }
        });
        Editor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nLinea(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Cambios(evt);
            }
        });

        jScrollPane2.setViewportView(Editor);

        jPanel6.add(jScrollPane2);

        jPanel1.add(jPanel6, java.awt.BorderLayout.CENTER);

        jPanel1.add(jPanel7, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.GridLayout(0, 1));

        jPanel20.setLayout(new java.awt.GridLayout(1, 0));

   
        Linea.setEditable(false);
        Linea.setText("\n\n\n\n");
        Linea.setBorder(null);
        jPanel20.add(Linea);

        jPanel8.add(jPanel20);

        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.X_AXIS));

        jPanel16.add(jPanel17);

        jPanel18.setLayout(new java.awt.GridLayout(1, 0));

        Ensamblar.setFont(new java.awt.Font("Dialog", 1, 14));
        Ensamblar.setText(lenguaje.getString("ensamblar"));
        Ensamblar.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
        Ensamblar.setEnabled(false);
        Ensamblar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EnsamblarActionPerformed(evt);
            }
        });

        jPanel18.add(Ensamblar);

        jPanel16.add(jPanel18);

        jPanel16.add(jPanel19);

        jPanel21.setLayout(new java.awt.GridLayout(1, 0));

        Ejecutar.setFont(new java.awt.Font("Dialog", 1, 14));
        Ejecutar.setText(lenguaje.getString("ejecutar"));
        Ejecutar.setEnabled(false);
        Ejecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EjecutarActionPerformed(evt);
            }
        });

        jPanel21.add(Ejecutar);

        jPanel16.add(jPanel21);

        jPanel16.add(jPanel22);

        jPanel8.add(jPanel16);

        jPanel1.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel1.add(jPanel9, java.awt.BorderLayout.EAST);

        jPanel1.add(jPanel10, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        jPanel2.setLayout(new javax.swing.BoxLayout(jPanel2, javax.swing.BoxLayout.X_AXIS));

        jToolBar1.setRollover(true);
        BotonNuevo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/New2.gif")));
        BotonNuevo.setToolTipText(lenguaje.getString("nuevo"));
        BotonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemNuevoActionPerformed(evt);
            }
        });

        jToolBar1.add(BotonNuevo);

        BotonAbrir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Open.gif")));
        BotonAbrir.setToolTipText(lenguaje.getString("abrir"));
        BotonAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemAbrirActionPerformed(evt);
            }
        });

        jToolBar1.add(BotonAbrir);

        BotonGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Save.gif")));
        BotonGuardar.setToolTipText(lenguaje.getString("guardar"));
        BotonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGuardarActionPerformed(evt);
            }
        });

        jToolBar1.add(BotonGuardar);

        BotonGuardarComo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/SaveAs.gif")));
        BotonGuardarComo.setToolTipText(lenguaje.getString("guardarComo"));
        BotonGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGuardarComoActionPerformed(evt);
            }
        });

        jToolBar1.add(BotonGuardarComo);

        BotonImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Print3.gif")));
        BotonImprimir.setToolTipText(lenguaje.getString("imprimir"));
        BotonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonImprimirActionPerformed(evt);
            }
        });

        jToolBar1.add(BotonImprimir);

        jPanel2.add(jToolBar1);

        jToolBar2.setRollover(true);
        BotonCopiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Copy.gif")));
        BotonCopiar.setToolTipText(lenguaje.getString("copiar"));
        BotonCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCopiarActionPerformed(evt);
            }
        });

        jToolBar2.add(BotonCopiar);

        BotonCortar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Cut.gif")));
        BotonCortar.setToolTipText(lenguaje.getString("cortar"));
        BotonCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCortarActionPerformed(evt);
            }
        });

        jToolBar2.add(BotonCortar);

        BotonPegar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Paste.gif")));
        BotonPegar.setToolTipText(lenguaje.getString("pegar"));
        BotonPegar.setEnabled(false);
        BotonPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemPegarActionPerformed(evt);
            }
        });

        jToolBar2.add(BotonPegar);

        jPanel2.add(jToolBar2);

        jToolBar3.setRollover(true);
        BotonDeshacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Undo.gif")));
        BotonDeshacer.setToolTipText(lenguaje.getString("deshacer"));
        BotonDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemDeshacerActionPerformed(evt);
            }
        });

        jToolBar3.add(BotonDeshacer);

        BotonRehacer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/Redo.gif")));
        BotonRehacer.setToolTipText(lenguaje.getString("rehacer"));
        BotonRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemRehacerActionPerformed(evt);
            }
        });

        jToolBar3.add(BotonRehacer);

        BotonBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/search2.gif")));
        BotonBuscar.setToolTipText(lenguaje.getString("buscar"));
        BotonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemTextoActionPerformed(evt);
            }
        });

        jToolBar3.add(BotonBuscar);

        jPanel2.add(jToolBar3);

        jToolBar4.setRollover(true);
        botonError.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ensamblador/iconos/error.gif")));
        botonError.setToolTipText(lenguaje.getString("errorSiguiente"));
        botonError.setEnabled(false);
        botonError.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonErrorActionPerformed(evt);
            }
        });

        jToolBar4.add(botonError);

        jPanel2.add(jToolBar4);

        getContentPane().add(jPanel2, java.awt.BorderLayout.NORTH);

        jPanel3.setLayout(new java.awt.BorderLayout());

        jPanel11.setLayout(new java.awt.BorderLayout());

        jPanel23.setLayout(new java.awt.GridLayout(1, 0));

        Errores.setEditable(false);
        Errores.setPreferredSize(new java.awt.Dimension(146, 61));
        Errores.setAutoscrolls(false);
        jScrollPane1.setViewportView(Errores);

        jPanel23.add(jScrollPane1);

        jPanel11.add(jPanel23, java.awt.BorderLayout.CENTER);

        jPanel11.add(jPanel27, java.awt.BorderLayout.WEST);

        jPanel11.add(jPanel24, java.awt.BorderLayout.NORTH);

        jPanel3.add(jPanel11, java.awt.BorderLayout.CENTER);

        jPanel12.setLayout(new java.awt.GridLayout(1, 0));

        jPanel3.add(jPanel12, java.awt.BorderLayout.NORTH);

        jPanel3.add(jPanel13, java.awt.BorderLayout.SOUTH);

        jPanel3.add(jPanel14, java.awt.BorderLayout.EAST);

        jPanel3.add(jPanel15, java.awt.BorderLayout.WEST);

        getContentPane().add(jPanel3, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel5, java.awt.BorderLayout.WEST);

        MenuArchivo.setMnemonic('f');
        MenuArchivo.setText(lenguaje.getString("archivo"));
        MenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuArchivoActionPerformed(evt);
            }
        });

        ItemNuevo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        //ItemNuevo.setMnemonic('n');
        ItemNuevo.setText(lenguaje.getString("nuevo"));
        ItemNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemNuevoActionPerformed(evt);
            }
        });

        MenuArchivo.add(ItemNuevo);
        ItemNuevo.getAccessibleContext().setAccessibleName("ItemNuevo");
        ItemNuevo.getAccessibleContext().setAccessibleDescription("ItemNuevo");

        MenuArchivo.add(jSeparator1);

        ItemAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_MASK));
        //ItemAbrir.setMnemonic('o');
        ItemAbrir.setText(lenguaje.getString("abrir"));
        ItemAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemAbrirActionPerformed(evt);
            }
        });

        MenuArchivo.add(ItemAbrir);
        ItemAbrir.getAccessibleContext().setAccessibleDescription("ItemAbrir");

        ItemGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        //ItemGuardar.setMnemonic('s');
        ItemGuardar.setText(lenguaje.getString("guardar"));
        ItemGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGuardarActionPerformed(evt);
            }
        });

        MenuArchivo.add(ItemGuardar);
        ItemGuardar.getAccessibleContext().setAccessibleDescription("ItemGuardar");

        ItemGuardarComo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        //ItemGuardarComo.setMnemonic('s');
        ItemGuardarComo.setText(lenguaje.getString("guardarComo"));
        ItemGuardarComo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemGuardarComoActionPerformed(evt);
            }
        });

        MenuArchivo.add(ItemGuardarComo);
        ItemGuardarComo.getAccessibleContext().setAccessibleDescription("ItemGuardarComo");

        MenuArchivo.add(jSeparator2);

        ItemImprimir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_P, java.awt.event.InputEvent.CTRL_MASK));
        //ItemImprimir.setMnemonic('p');
        ItemImprimir.setText(lenguaje.getString("imprimir"));
        ItemImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonImprimirActionPerformed(evt);
            }
        });

        MenuArchivo.add(ItemImprimir);
        ItemImprimir.getAccessibleContext().setAccessibleDescription("ItemImprimir");

        MenuArchivo.add(jSeparator3);

        ItemSalir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Q, java.awt.event.InputEvent.CTRL_MASK));
        //ItemSalir.setMnemonic('i');
        ItemSalir.setText(lenguaje.getString("salir"));
        ItemSalir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemSalirActionPerformed(evt);
            }
        });

        MenuArchivo.add(ItemSalir);
        ItemSalir.getAccessibleContext().setAccessibleDescription("ItemSalir");

        BarraMenuComp.add(MenuArchivo);
        MenuArchivo.getAccessibleContext().setAccessibleDescription("MenuArchivo");

        MenuEdicion.setMnemonic('e');
        MenuEdicion.setText(lenguaje.getString("edicion"));
        MenuEdicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuEdicionActionPerformed(evt);
            }
        });

        ItemDeshacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Z, java.awt.event.InputEvent.CTRL_MASK));
        ItemDeshacer.setText(lenguaje.getString("deshacer"));
        ItemDeshacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemDeshacerActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemDeshacer);

        ItemRehacer.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_Y, java.awt.event.InputEvent.CTRL_MASK));
        ItemRehacer.setText(lenguaje.getString("rehacer"));
        ItemRehacer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemRehacerActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemRehacer);

        MenuEdicion.add(jSeparator6);

        ItemSeleccionarTodo.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        //ItemSeleccionarTodo.setMnemonic('a');
        ItemSeleccionarTodo.setText(lenguaje.getString("seleccionarTodo"));
        ItemSeleccionarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemSeleccionarTodoActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemSeleccionarTodo);
        ItemSeleccionarTodo.getAccessibleContext().setAccessibleName("SeleccionarTodo");
        ItemSeleccionarTodo.getAccessibleContext().setAccessibleDescription("ItemSeleccionarTodo");

        MenuEdicion.add(jSeparator4);

        ItemCopiar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        //ItemCopiar.setMnemonic('c');
        ItemCopiar.setText(lenguaje.getString("copiar"));
        ItemCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCopiarActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemCopiar);
        ItemCopiar.getAccessibleContext().setAccessibleDescription("ItemCopiar");

        ItemCortar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_X, java.awt.event.InputEvent.CTRL_MASK));
        //ItemCortar.setMnemonic('x');
        ItemCortar.setText(lenguaje.getString("cortar"));
        ItemCortar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemCortarActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemCortar);
        ItemCortar.getAccessibleContext().setAccessibleDescription("ItemCortar");

        ItemPegar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_V, java.awt.event.InputEvent.CTRL_MASK));
        //ItemPegar.setMnemonic('v');
        ItemPegar.setText(lenguaje.getString("pegar"));
        ItemPegar.setEnabled(false);
        ItemPegar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemPegarActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemPegar);
        ItemPegar.getAccessibleContext().setAccessibleDescription("ItemPegar");

        MenuEdicion.add(jSeparator5);

        //MenuBuscar.setMnemonic('f');
        MenuBuscar.setText(lenguaje.getString("buscar"));
        MenuBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBuscarActionPerformed(evt);
            }
        });

        ItemLinea.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_MASK));
        //ItemLinea.setMnemonic('l');
        ItemLinea.setText(lenguaje.getString("linea"));
        ItemLinea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemLineaActionPerformed(evt);
            }
        });

        MenuBuscar.add(ItemLinea);
        ItemLinea.getAccessibleContext().setAccessibleDescription("ItemLinea");

        ItemTexto.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_T, java.awt.event.InputEvent.CTRL_MASK));
        //ItemTexto.setMnemonic('t');
        ItemTexto.setText(lenguaje.getString("texto"));
        ItemTexto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemTextoActionPerformed(evt);
            }
        });

        MenuBuscar.add(ItemTexto);
        ItemTexto.getAccessibleContext().setAccessibleDescription("ItemTexto");

        MenuEdicion.add(MenuBuscar);
        MenuBuscar.getAccessibleContext().setAccessibleName("Buscar");
        MenuBuscar.getAccessibleContext().setAccessibleDescription("MenuBuscar");

        ItemReemplazar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_R, java.awt.event.InputEvent.CTRL_MASK));
        //ItemReemplazar.setMnemonic('r');
        ItemReemplazar.setText(lenguaje.getString("reemplazar"));
        ItemReemplazar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ItemReemplazarActionPerformed(evt);
            }
        });

        MenuEdicion.add(ItemReemplazar);
        ItemReemplazar.getAccessibleContext().setAccessibleDescription("ItemReemplazar");

        BarraMenuComp.add(MenuEdicion);
        MenuEdicion.getAccessibleContext().setAccessibleDescription("MenuEdicion");

        MenuConfiguracion.setMnemonic('c');
        MenuConfiguracion.setText(lenguaje.getString("configuracion"));
        MenuConfiguracion.setMinimumSize(new java.awt.Dimension(6, 21));
        MenuConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuConfiguracionActionPerformed(evt);
            }
        });
        
        menuEntradaSalida.setText(lenguaje.getString("entradaSalida"));

        itemMapeada.setText(lenguaje.getString("encuesta"));
        itemInterrupciones.setText(lenguaje.getString("conInterrupciones"));
        itemDesactivada.setText(lenguaje.getString("desactivada"));
        menuEntradaSalida.add(itemMapeada);
        menuEntradaSalida.add(itemInterrupciones);
        menuEntradaSalida.add(itemDesactivada);
        itemDesactivada.setSelected(true);
        
        MenuConfiguracion.add(menuEntradaSalida);
        itemMapeada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemESActionPerformed(evt);
            }
        });

        itemInterrupciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemESActionPerformed(evt);
            }
        });

        grupoBotonesES.add(itemMapeada);
        grupoBotonesES.add(itemInterrupciones);
        grupoBotonesES.add(itemDesactivada);
        
        menuCaminoDatos.setText(lenguaje.getString("caminoDeDatos"));
        
        itemMonociclo.setSelected(true);
        itemMonociclo.setText(lenguaje.getString("monociclo"));
        grupoBotones.add(itemMonociclo);
        
        menuCaminoDatos.add(itemMonociclo);

        itemMulticiclo.setText(lenguaje.getString("multiciclo"));
        grupoBotones.add(itemMulticiclo);
        itemMulticiclo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemMulticicloActionPerformed(evt);
            }
        });

        menuCaminoDatos.add(itemMulticiclo);

        
        itemSegmentado.setText(lenguaje.getString("basico"));
        grupoBotones.add(itemSegmentado);
        itemSegmentado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemSegmentadoActionPerformed(evt);
            }
        });

        menuSegmentado.add(itemSegmentado);

        
        itemMarcador.setText(lenguaje.getString("marcador"));

        grupoBotones.add(itemMarcador);
        itemMarcador.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              itemMarcadorActionPerformed(evt);
          }
      });
      menuSegmentado.add(itemMarcador);
      
      itemTomasulo.setText(lenguaje.getString("tomasulo"));
      grupoBotones.add(itemTomasulo);
      itemTomasulo.addActionListener(new java.awt.event.ActionListener() {
          public void actionPerformed(java.awt.event.ActionEvent evt) {
              itemTomasuloActionPerformed(evt);
          }
      });
      menuSegmentado.add(itemTomasulo);
   
      menuSegmentado.setText(lenguaje.getString("segmentado"));
      menuCaminoDatos.add(menuSegmentado);
      MenuConfiguracion.add(menuCaminoDatos);
        
      menuSalto.setText(lenguaje.getString("salto"));
      itemSaltoRetardadoFlotante.setSelected(true);
    itemSaltoRetardadoFlotante.setText(lenguaje.getString("saltoRetardado"));
    itemSaltoRetardadoFlotante.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemSaltoRetardadoFlotanteActionPerformed(evt);
        }
    });
    grupoBotonesSalto.add(itemSaltoRetardadoFlotante);        
    menuSalto.add(itemSaltoRetardadoFlotante);

    itemSaltoFijoFlotante.setText(lenguaje.getString("saltoFijo"));
    itemSaltoFijoFlotante.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            itemSaltoFijoFlotanteActionPerformed(evt);
        }
    });
    grupoBotonesSalto.add(itemSaltoFijoFlotante);
    menuSalto.add(itemSaltoFijoFlotante);
    MenuConfiguracion.add(menuSalto);
      
      
      
//
//        
//        menuSegmentadoFlotante.setText("Segmentado básico");
//        grupoBotones.add(menuSegmentadoFlotante);
//
//        itemSaltoRetardadoFlotante.setText("Salto retardado");
//        itemSaltoRetardadoFlotante.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                itemSaltoRetardadoFlotanteActionPerformed(evt);
//            }
//        });
//        grupoBotones.add(itemSaltoRetardadoFlotante);        
//        menuSegmentadoFlotante.add(itemSaltoRetardadoFlotante);
//
//        itemSaltoFijoFlotante.setText("Salto fijo");
//        itemSaltoFijoFlotante.addActionListener(new java.awt.event.ActionListener() {
//            public void actionPerformed(java.awt.event.ActionEvent evt) {
//                itemSaltoFijoFlotanteActionPerformed(evt);
//            }
//        });
//        grupoBotones.add(itemSaltoFijoFlotante);
//        menuSegmentadoFlotante.add(itemSaltoFijoFlotante);
//
//        menuSegmentado.add(menuSegmentadoFlotante);
//
//        
//        
//       
        
        BarraMenuComp.add(MenuConfiguracion);

        MenuAyuda.setMnemonic('h');
        MenuAyuda.setText(lenguaje.getString("ayuda"));
        MenuAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuAyudaActionPerformed(evt);
            }
        });

        ItemAcercaDe.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_H, java.awt.event.InputEvent.CTRL_MASK));
        //itemAcercaDe.setMnemonic('a');
        ItemAcercaDe.setText(lenguaje.getString("acercaDe"));
        ItemAcercaDe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAcercaDeActionPerformed(evt);
            }
        });
        

        MenuAyuda.add(ItemAcercaDe);
        ItemAcercaDe.getAccessibleContext().setAccessibleDescription("itemAcercaDe");

        ItemAyuda.setText(lenguaje.getString("ayuda"));
        ItemAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAyudaActionPerformed(evt);
            }
        });
        

        MenuAyuda.add(ItemAyuda);
        ItemAyuda.getAccessibleContext().setAccessibleDescription("itemAyuda");

        BarraMenuComp.add(MenuAyuda);
        MenuAyuda.getAccessibleContext().setAccessibleDescription("MenuAyuda");

        setJMenuBar(BarraMenuComp);
        BarraMenuComp.getAccessibleContext().setAccessibleName("BarraMenuComp");
        BarraMenuComp.getAccessibleContext().setAccessibleDescription("BarraMenuComp");

        pack();
    }//GEN-END:initComponents

    
    private void itemESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMulticicloActionPerformed
        Editor.requestFocus();
        itemMonociclo.setSelected(true);
        
    }//GEN-LAST:event_itemMulticicloActionPerformed

    
    private void itemMulticicloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMulticicloActionPerformed
        Editor.requestFocus();
        new VentanaMulticiclo(this, Editor, false).show();
        
    }//GEN-LAST:event_itemMulticicloActionPerformed

    private void itemSegmentadoActionPerformed(java.awt.event.ActionEvent evt) {
       	new DialogoSegmentado(this, true);
       	this.itemSegmentado.setSelected(true);
    
        }
    
    private void itemSaltoRetardadoFlotanteActionPerformed(java.awt.event.ActionEvent evt) {
       	new DialogoSegmentado(this, true);
       	this.itemSegmentado.setSelected(true);
    
        }
    
    private void itemSaltoFijoFlotanteActionPerformed(java.awt.event.ActionEvent evt) {
       	new DialogoSegmentado(this, true);
       	this.itemSegmentado.setSelected(true);
    
        }
    
    
    private void itemMarcadorActionPerformed(java.awt.event.ActionEvent evt) {
       	new DialogoMarcador(this, true);
    
        }
    
    private void itemTomasuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemMulticicloActionPerformed
    	new DialogoTomasulo(this, true);
    
    }
    
    private void MenuAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuAyudaActionPerformed
         
    }//GEN-LAST:event_MenuAyudaActionPerformed

    
/**Metodo que indica que se ha producido un cambio en el area de texto  
 *@param event evento
 *@return void
 **/    
    private void HaCambiado(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_HaCambiado
        Ensamblar.setEnabled(true);   
        Ejecutar.setEnabled(false);
    }//GEN-LAST:event_HaCambiado
    

    private void MenuConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuConfiguracionActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_MenuConfiguracionActionPerformed

/**Metodo que cierra la aplicacion  
 *@param event evento
 *@return void
 **/    
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        // Add your handling code here:
        ItemSalir.doClick();
    }//GEN-LAST:event_exitForm

    
    
/**Metodo para el escuchador del boton ejecutar  
 *@param event evento
 *@return void
 **/    
    private void EjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EjecutarActionPerformed
    	// Add your handling code here:
    	this.setVisible(false);
    	if (itemMapeada.isSelected()&& (itemMonociclo.isSelected())){
    		new VistaMonoESProg(this).setVisible(true);
    	} 
    	if(itemInterrupciones.isSelected() && (itemMonociclo.isSelected())){
    		ensambla.analizaDato("s1: .word 0");
    		ensambla.analizaDato("s2: .word 0");
    		new VistaMonoESInterrup(this).setVisible(true);
    	}
    	if (itemMonociclo.isSelected() && (itemDesactivada.isSelected())){
    		new VistaMonociclo(this).setVisible(true);
    	}
    	if (itemMulticiclo.isSelected())
    		new Multiciclo(this,entero_uno,entero_dos,entero_tres).setVisible(true);
    	
    	if(itemSegmentado.isSelected()){
    		if (itemSaltoRetardadoFlotante.isSelected())
    			new VistaSegmentado(this, Tipos.SALTO_RETARDAD0, this.getFus(), this.getTiposFus()).setVisible(true);
    		if (this.itemSaltoFijoFlotante.isSelected())
    			new VistaSegmentado(this, Tipos.SALTO_FIJO, this.getFus(), this.getTiposFus()).setVisible(true);
    	}
    	if (itemMarcador.isSelected()){
    		new VistaAlgoritmos(this, Tipos.MARCADOR,this.getFus()).setVisible(true);
    	}
    	if (itemTomasulo.isSelected()){
    		
    		new VistaAlgoritmos(this, Tipos.TOMASULO,fus).setVisible(true);
    	}
    	
    }//GEN-LAST:event_EjecutarActionPerformed
    
    
    public void ejecutarAlgoritmo(){
    	
    	new VistaAlgoritmos(this, Tipos.MARCADOR,this.getFus()).setVisible(true);
    
    }
    
    public Vector<Vector> getErs() {
    	return ers;
    }


public void setErs(Vector<Vector> ers) {
	this.ers = ers;
}


public Vector<Vector> getFus() {
	return fus;
}

public void setFus(Vector<Vector> fus) {
	this.fus = fus;
}

public void setTiposFus(Vector<Integer> tiposFus) {
	this.tiposFus = tiposFus;
}

public Vector<Integer> getTiposFus() {
	return tiposFus;
}

    
/**Metodo para el escuchador del boton error siguiente  
 *@param event evento
 *@return void
 **/    
    private void botonErrorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonErrorActionPerformed
       
        String token;
        if(st.countTokens()>0)
        {
              if((token=st.nextToken()).indexOf("\t")>-1)
              {  
                st2=new StringTokenizer(token, "\t");
                token=st2.nextToken();
              }
             BuscarError(token); 
            if(st.countTokens()==0)
                botonError.setEnabled(false);
        }                            
    }//GEN-LAST:event_botonErrorActionPerformed

    
/**Metodo para el escuchador del boton ensamblar  
 *@param event evento
 *@return void
 **/    
    private void EnsamblarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EnsamblarActionPerformed
        // Add your handling code here:
        
        ensambla=new Ensamblar(Editor.getText());
        Errores.setText("");
        listaErrores=ensambla.analiza();
        if(listaErrores!=null)
        {
            Ejecutar.setEnabled(false);
            Errores.setText(listaErrores);
            Errores.setCaretPosition(0);
            Errores.setMargin(new Insets(5,5,5,5));  
            botonError.setEnabled(true); 
            st=new StringTokenizer(listaErrores, "\n");
            botonError.doClick();                      
        }
        else{        	
            Ejecutar.setEnabled(true);
        }
        
    }//GEN-LAST:event_EnsamblarActionPerformed

/**Metodo para establecer cambios en el area de texto  
 *@param event evento
 *@return void
 **/    
    private void Cambios(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_Cambios
         modificar=true;
        Ensamblar.setEnabled(true);   
        Ejecutar.setEnabled(false);
    }//GEN-LAST:event_Cambios
        
/**Metodo para el escuchador del item rehacer  
 *@param event evento
 *@return void
 **/    
    private void ItemRehacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemRehacerActionPerformed
         redoAction.actionPerformed(evt);
        Editor.requestFocus();
    }//GEN-LAST:event_ItemRehacerActionPerformed
    
    private void MenuEdicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuEdicionActionPerformed
        // Add your handling code here:
    }//GEN-LAST:event_MenuEdicionActionPerformed
    
/**Metodo para el escuchador del item deshacer  
 *@param event evento
 *@return void
 **/    
    private void ItemDeshacerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemDeshacerActionPerformed
         undoAction.actionPerformed(evt);
        Editor.requestFocus();
    }//GEN-LAST:event_ItemDeshacerActionPerformed
    
/**Metodo para el escuchador del boton imprimir  
 *@param event evento
 *@return void
 **/    
    private void BotonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonImprimirActionPerformed
        // Add your handling code here:        			
	
	String impresion = String.valueOf(Editor.getText());
	if (!impresion.equals(""))
	{
//		PrinterJob job=PrinterJob.getPrinterJob();
//		job.printDialog();
//		
//		//Cogemos el servicio de impresión por defecto (impresora por defecto)
//		PrintService service =job.getPrintService();//PrintServiceLookup.lookupPrintServices(null, null); //lookupDefaultPrintService();
//		
////		Le decimos el tipo de datos que vamos a enviar a la impresora
////		Tipo: bytes Subtipo: autodetectado
//		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
//
////		Creamos un trabajo de impresión
//		DocPrintJob pj = service.createPrintJob();
////		Nuestro trabajo de impresión envía una cadena de texto
//		 final  String lineBreak
//         = System.getProperty(
//                     "line.separator");
//
//		
//		impresion=impresion.replaceAll("\n", lineBreak);
//		byte[] bytes;
////		Transformamos el texto a bytes que es lo que soporta la impresora
//		bytes=impresion.getBytes();
////		Creamos un documento (Como si fuese una hoja de Word para imprimir)
//		if (service!=null)
//		{
//			DocFlavor[] flavs=service.getSupportedDocFlavors();
//			if (flavs.length==0)
//				System.out.println("No Flavors supported!!!");
//			for (int i=0; i<flavs.length; i++)
//			{
//			System.out.println(flavs[i].getMimeType() + "-->"
//			+ flavs[i].getRepresentationClassName());
//			}
//		}
//		Doc doc=new SimpleDoc(bytes,flavor, null);
//		PrintRequestAttributeSet attrs = new HashPrintRequestAttributeSet(); 
//
////		Obligado coger la excepción PrintException
//		try {
//		  //Mandamos a impremir el documento
//		  pj.print(doc, null);
//		}
//		catch (PrintException e) {
//		  System.out.println("Error al imprimir: "+e.getMessage());
//		}
		
		 final  String lineBreak = System.getProperty("line.separator");		
		impresion=impresion.replaceAll("\n", lineBreak);
		byte[] bytes;
//		Transformamos el texto a bytes que es lo que soporta la impresora
		bytes=impresion.getBytes();

		
		
		PrintRequestAttributeSet pras=new HashPrintRequestAttributeSet();
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintService printService[]=PrintServiceLookup.lookupPrintServices(flavor, pras);
		PrintService defaultService=PrintServiceLookup.lookupDefaultPrintService();
		PrintService service=ServiceUI.printDialog(null, 200, 200, printService, defaultService, flavor, pras);
		if(service!=null){
			DocPrintJob job=service.createPrintJob();
			DocAttributeSet das=new HashDocAttributeSet();
			Doc doc=new SimpleDoc(bytes,flavor, das);
			try {
				job.print(doc, pras);
			} catch (PrintException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	Editor.requestFocus();
	Editor.select(0, 0);
			
    }//GEN-LAST:event_BotonImprimirActionPerformed
    
    private void MenuBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBuscarActionPerformed
        
    }//GEN-LAST:event_MenuBuscarActionPerformed
    
    private void nLinea(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nLinea
        
    }//GEN-LAST:event_nLinea
   
/**Metodo para el escuchador del item acercaDe
 *@param event evento
 *@return void
 **/    
    private void itemAcercaDeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcercaDeActionPerformed
        new PanelAyuda().setVisible(true);
    }//GEN-LAST:event_itemAcercaDeActionPerformed

    
    /**Metodo para el escuchador del item ayuda  
     *@param event evento
     *@return void
     **/    
        private void itemAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAcercaDeActionPerformed
            new GuiaRapida().setVisible(true);
        }

    
/**Metodo para el escuchador del item reemplazar  
 *@param event evento
 *@return void
 **/    
    private void ItemReemplazarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemReemplazarActionPerformed
        Editor.requestFocus();
        int reemp=3;
        new Busqueda(this, Editor, false, reemp).setVisible(true);     
    }//GEN-LAST:event_ItemReemplazarActionPerformed
    
/**Metodo para el escuchador del item buscar texto  
 *@param event evento
 *@return void
 **/    
    private void ItemTextoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemTextoActionPerformed
        Editor.requestFocus();
        int texto=2;
        new Busqueda(this, Editor, false,texto).setVisible(true);
    }//GEN-LAST:event_ItemTextoActionPerformed
    
/**Metodo para el escuchador del item busscar linea  
 *@param event evento
 *@return void
 **/    
    private void ItemLineaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemLineaActionPerformed
        this.setFocusableWindowState(false); 
        Editor.requestFocus();
        int linea=1;
        new Busqueda(this, Editor, false,linea).setVisible(true);       
    }//GEN-LAST:event_ItemLineaActionPerformed
    
/**Metodo para el escuchador del boton pegar  
 *@param event evento
 *@return void
 **/    
    private void ItemPegarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemPegarActionPerformed
        Editor.paste();
        Editor.requestFocus();
    }//GEN-LAST:event_ItemPegarActionPerformed
    
/**Metodo para el escuchador del boton cortar
 *@param event evento
 *@return void
 **/    
    private void ItemCortarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemCortarActionPerformed
        activaPegar();
        Editor.cut();
        Editor.requestFocus();
    }//GEN-LAST:event_ItemCortarActionPerformed
    
/**Metodo para el escuchador del boton copiar
 *@param event evento
 *@return void
 **/    
    private void ItemCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemCopiarActionPerformed
        activaPegar();
        Editor.copy();
        Editor.requestFocus();
    }//GEN-LAST:event_ItemCopiarActionPerformed
    
/**Metodo para el escuchador del item seleccionar todo 
 *@param event evento
 *@return void
 **/    
    private void ItemSeleccionarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemSeleccionarTodoActionPerformed
        Editor.selectAll();
    }//GEN-LAST:event_ItemSeleccionarTodoActionPerformed
    
/**Metodo para el escuchador del etem salir
 *@param event evento
 *@return void
 **/    
    private void ItemSalirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemSalirActionPerformed
        int r=0;
        //if (modificar)
        //{
            r=GuardarCambios();
            if(r!=0)    //r==0 --> cancelar
            	System.exit(0);
        //}
    }//GEN-LAST:event_ItemSalirActionPerformed
 
/**Metodo para el escuchador del item abrir
 *@param event evento
 *@return void
 **/           
    private void ItemAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemAbrirActionPerformed
         int r=0;
        if (modificar)
            r=GuardarCambios();
        if ((r==1) || (!modificar)) {
            FileDialog Dialogo = new FileDialog(this, "Abrir...", FileDialog.LOAD);            
            Dialogo.setVisible(true);
            if (Dialogo.getFile()==null)
                return;
            nombreF=Dialogo.getFile();
            NombreFich = Dialogo.getDirectory() + nombreF;            
            java.awt.Frame ventana = (Frame)Dialogo.getParent();            
            ventana.setTitle("Simula3MS "+nombreF);
            FileInputStream fis=null;
            String str=null;
            try{
                fis=new FileInputStream(NombreFich);
                int tam=fis.available();
                byte[] bytes=new byte[tam];
                fis.read(bytes);
                str=new String(bytes);
            } catch (IOException e) {
            } finally{
                try{
                    fis.close();
                }catch (IOException e2){
                }
            }
            
           if (str!=null)
           {
                Editor.setText(str);
                Ensamblar.setEnabled(true);
                Errores.setText("");
           }
        }
        Editor.requestFocus();
       
    }//GEN-LAST:event_ItemAbrirActionPerformed
    
/**Metodo para el escuchador del item nuevo
 *@param event evento
 *@return void
 **/    
    private void ItemNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemNuevoActionPerformed
        int r=0;
        if (modificar)
            r=GuardarCambios();
        
        if ((r==1) || (!modificar)) {
            NombreFich = "";
            nombreF="";            
            javax.swing.JFrame ventana = (javax.swing.JFrame)Editor.getParent().getParent().getParent().getParent().getParent().getParent().getParent().getParent();            
            ventana.setTitle("Simula3MS "+nombreF);
            Editor.setText("");
            Errores.setText("");
             Ensamblar.setEnabled(false);
        }
        Editor.requestFocus();
       
    }//GEN-LAST:event_ItemNuevoActionPerformed
    
/**Metodo para el escuchador del item guardar como
 *@param event evento
 *@return void
 **/    
    private void ItemGuardarComoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemGuardarComoActionPerformed
        GuardarComo();
        Editor.requestFocus();
    }//GEN-LAST:event_ItemGuardarComoActionPerformed
    
/**Metodo para el escuchador del item guardar
 *@param event evento
 *@return void
 **/    
    private void ItemGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ItemGuardarActionPerformed
        if ("".equals(NombreFich))
            GuardarComo();
        else
            Guardar(NombreFich);
        Editor.requestFocus();
    }//GEN-LAST:event_ItemGuardarActionPerformed
    
    private void MenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuArchivoActionPerformed
       
    }//GEN-LAST:event_MenuArchivoActionPerformed
    
/**Metodo que guarda cambios en un fichero  
 *@param String nombre del fichero
 *@return void
 **/    
    private void Guardar(String NombreFich) {
        FileOutputStream fos=null;
        String str=Editor.getText();
        try{        	
            fos=new FileOutputStream(NombreFich);
            fos.write(str.getBytes());
        } catch (IOException e) {
        }
        finally {
            try{
                fos.close();
            } catch (IOException e2){
            }
        }
        modificar=false;
    }
    
/**Metodo que guarda cambios en un fichero  
 *@param Sin parametros
 *@return void
 **/    
    private void GuardarComo() {
        FileDialog Dialogo=new FileDialog(this, "Guardar como...", FileDialog.SAVE);
        Dialogo.setVisible(true);
        if (Dialogo.getFile()==null)
            return;
        
        nombreF=Dialogo.getFile();
        NombreFich = Dialogo.getDirectory() + nombreF;            
        java.awt.Frame ventana = (Frame)Dialogo.getParent();            
        ventana.setTitle("Simula3MS "+nombreF);
        Guardar(NombreFich);
    }
    
    
/**Metodo que muestra el dialogo de guardar cambios  
 *@param Sin parametros
 *@return int respuesta
 **/    
    private int GuardarCambios() {
        JOptionPane panel=new JOptionPane();
        Object[] opciones={"SI", "NO", "CANCELAR"};
        int resp=panel.showOptionDialog(this, "Desea guardar cambios?", "Guardar cambios", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (resp==JOptionPane.YES_OPTION)
            if ("".equals(NombreFich))
                GuardarComo();
            else
                Guardar(NombreFich);
        if (resp==JOptionPane.CANCEL_OPTION)
        {
        	return 0;
        }        
        return 1;
    }
    
    
/**Metodo que activa el boton pegar  
 *@param Sin parametros
 *@return void
 **/    
    public void activaPegar(){
        ItemPegar.setEnabled(true);
        BotonPegar.setEnabled(true);
    }
     
    
/**Metodo que busca el error siguiente  
 *@param Strin error
 *@return int posicion del error
 **/    
     public int BuscarError(String textoBuscado)
    {
        String txt, str;      
        txt=Editor.getText();
        str=textoBuscado;
        
        Editor.requestFocus();
        int sel;
        if (Editor.getSelectionStart() != Editor.getSelectionEnd())
        {            
            sel =Editor.getSelectionEnd();           
        }
        else
            sel=0;
        int pos=txt.indexOf(str, sel);
        if (pos>0)
        {            
                Editor.select(pos, pos + str.length());                                
        }
        return pos;
    }                          
    
/**Metodo que actualiza cambios  
 *@param Observable o, Object error
 *@return void
 **/    
    public void update(Observable o, Object error)
    {
        String errores=(String)error;
        Errores.setText(errores);
    }
    
    /**Metodo para obtener el nombre del fichero  
     *@param
     *@return String nombreFichero
     **/      
    public String getNombreF()
    {
    	return this.nombreF;
    }
    
/**Metodo de inicio de la aplicacion  
 *@param Sin parametros
 *@return void
 **/    
    public static void main(String args[]) {
    
    	//Se elige el idioma de la aplicacion
    	String leng;
    	
    	if (args.length ==1) {
    		leng = new String(args[0]);
		}
    	else {
    		leng = "es";
    	}
    	
    	Lenguaje.inicializar(leng);    	
    	
		//Se visualiza el panel de inicio
        PanelInicio p=new PanelInicio();                     
        p.setVisible(true);
        try{
            java.lang.Thread.sleep(3000);
        } catch(InterruptedException e){}
        p.setVisible(false);
        new Ensamblador().setVisible(true);
    }
        
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuBar BarraMenuComp;
    private javax.swing.JButton BotonAbrir;
    private javax.swing.JButton BotonBuscar;
    private javax.swing.JButton BotonCopiar;
    private javax.swing.JButton BotonCortar;
    protected javax.swing.JButton BotonDeshacer;
    private javax.swing.JButton BotonGuardar;
    private javax.swing.JButton BotonGuardarComo;
    private javax.swing.JButton BotonImprimir;
    private javax.swing.JButton BotonNuevo;
    private javax.swing.JButton BotonPegar;
    protected javax.swing.JButton BotonRehacer;
    protected javax.swing.JTextArea Editor;
    private javax.swing.JButton Ejecutar;
    private javax.swing.JButton Ensamblar;
    private javax.swing.JEditorPane Errores;
    private javax.swing.JMenuItem ItemAbrir;
    private javax.swing.JMenuItem ItemAyuda;
    private javax.swing.JMenuItem ItemAcercaDe;
    private javax.swing.JMenuItem ItemCopiar;
    private javax.swing.JMenuItem ItemCortar;
    protected javax.swing.JMenuItem ItemDeshacer;
    private javax.swing.JMenuItem ItemGuardar;
    private javax.swing.JMenuItem ItemGuardarComo;
    private javax.swing.JMenuItem ItemImprimir;
    private javax.swing.JMenuItem ItemLinea;
    private javax.swing.JMenuItem ItemNuevo;
    private javax.swing.JMenuItem ItemPegar;
    private javax.swing.JMenuItem ItemReemplazar;
    protected javax.swing.JMenuItem ItemRehacer;
    private javax.swing.JMenuItem ItemSalir;
    private javax.swing.JMenuItem ItemSeleccionarTodo;
    private javax.swing.JMenuItem ItemTexto;
    private javax.swing.JTextField Linea;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuAyuda;
    private javax.swing.JMenu MenuBuscar;
    private javax.swing.JMenu MenuConfiguracion;
    private javax.swing.JMenu MenuEdicion;
    private javax.swing.JMenu menuSegmentado;
    private javax.swing.JButton botonError;
    private javax.swing.ButtonGroup grupoBotones;
    private javax.swing.ButtonGroup grupoBotonesSalto;
    private javax.swing.ButtonGroup grupoBotonesES;
    private javax.swing.JRadioButtonMenuItem itemMonociclo;
    private javax.swing.JRadioButtonMenuItem itemMulticiclo;
    private javax.swing.JRadioButtonMenuItem itemMarcador;
    private javax.swing.JRadioButtonMenuItem itemTomasulo;
    private javax.swing.JRadioButtonMenuItem itemSegmentado;
    private javax.swing.JRadioButtonMenuItem itemMapeada;
    private javax.swing.JRadioButtonMenuItem itemInterrupciones;
    private javax.swing.JRadioButtonMenuItem itemDesactivada;
//    private javax.swing.JMenu menuSegmentadoBasico;
//    private javax.swing.JRadioButtonMenuItem itemSaltoRetardado;
//    private javax.swing.JRadioButtonMenuItem itemSaltoFijo;
    private javax.swing.JMenu menuSegmentadoFlotante;
    private javax.swing.JMenu menuCaminoDatos;
    private javax.swing.JMenu menuEntradaSalida;
    private javax.swing.JMenu menuSalto;    
    private javax.swing.JRadioButtonMenuItem itemSaltoRetardadoFlotante;
    private javax.swing.JRadioButtonMenuItem itemSaltoFijoFlotante;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JToolBar jToolBar2;
    private javax.swing.JToolBar jToolBar3;
    private javax.swing.JToolBar jToolBar4;
    // End of variables declaration//GEN-END:variables
    
    public class RedoAction extends AbstractAction {
        
        public RedoAction(){
            super("Redo");
            update();
        }
        
        public void actionPerformed(java.awt.event.ActionEvent e) {
            undoManager.redo();
            undoAction.update();
            update();
        }
        
        public void update() {            
            boolean canRedo=undoManager.canRedo();    
            if(canRedo) {
                ItemRehacer.setEnabled(true);
                BotonRehacer.setEnabled(true);
                putValue(Action.NAME, undoManager.getRedoPresentationName());
            }
            else {
                ItemRehacer.setEnabled(false);
                BotonRehacer.setEnabled(false);
                putValue(Action.NAME,"Redo");
            }
        }
    }
    
    
    
    public class UndoLastAction extends AbstractAction {
        
        public UndoLastAction() {
            super("Undo");
            update();
        }
        
        public void actionPerformed(java.awt.event.ActionEvent e) {
            undoManager.undo();
            redoAction.update();
            update();
        }
        public void update() {
            boolean canUndo=undoManager.canUndo();
            
            if(canUndo) {
                BotonDeshacer.setEnabled(true);
                ItemDeshacer.setEnabled(true);
                putValue(Action.NAME, undoManager.getUndoPresentationName());
            }
            else {
                BotonDeshacer.setEnabled(false);
                ItemDeshacer.setEnabled(false);
                putValue(Action.NAME, "Undo");
            }
        }
    }
    
    
  
    
    
}


