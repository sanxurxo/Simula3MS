package ensamblador.controlador;

import java.util.Vector;
import java.awt.image.*;
import java.awt.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import ensamblador.componentes.fus.fusMarcador.EstadoFUMarcador;
import ensamblador.componentes.fus.fusMarcador.FUMarcador;
import ensamblador.datos.Codigo;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.etapas.EX;
import ensamblador.etapas.ID;
import ensamblador.etapas.IF;
import ensamblador.etapas.MEM;
import ensamblador.etapas.PipelineFlotante;
import ensamblador.etapas.WB;
import ensamblador.instrucciones.Instruccion;

import ensamblador.registros.A0;
import ensamblador.registros.A1;
import ensamblador.registros.A2;
import ensamblador.registros.A3;
import ensamblador.registros.F0;
import ensamblador.registros.F1;
import ensamblador.registros.F10;
import ensamblador.registros.F11;
import ensamblador.registros.F12;
import ensamblador.registros.F13;
import ensamblador.registros.F14;
import ensamblador.registros.F15;
import ensamblador.registros.F16;
import ensamblador.registros.F17;
import ensamblador.registros.F18;
import ensamblador.registros.F19;
import ensamblador.registros.F2;
import ensamblador.registros.F20;
import ensamblador.registros.F21;
import ensamblador.registros.F22;
import ensamblador.registros.F23;
import ensamblador.registros.F24;
import ensamblador.registros.F25;
import ensamblador.registros.F26;
import ensamblador.registros.F27;
import ensamblador.registros.F28;
import ensamblador.registros.F29;
import ensamblador.registros.F3;
import ensamblador.registros.F30;
import ensamblador.registros.F31;
import ensamblador.registros.F4;
import ensamblador.registros.F5;
import ensamblador.registros.F6;
import ensamblador.registros.F7;
import ensamblador.registros.F8;
import ensamblador.registros.F9;
import ensamblador.registros.Hi;
import ensamblador.registros.Lo;
import ensamblador.registros.Pc;
import ensamblador.registros.Ra;
import ensamblador.registros.S0;
import ensamblador.registros.S1;
import ensamblador.registros.S2;
import ensamblador.registros.S3;
import ensamblador.registros.S4;
import ensamblador.registros.S5;
import ensamblador.registros.S6;
import ensamblador.registros.S7;
import ensamblador.registros.FP;
import ensamblador.registros.T9;
import ensamblador.registros.Status;
import ensamblador.registros.T0;
import ensamblador.registros.T1;
import ensamblador.registros.T2;
import ensamblador.registros.T3;
import ensamblador.registros.T4;
import ensamblador.registros.T5;
import ensamblador.registros.T6;
import ensamblador.registros.T7;
import ensamblador.registros.T8;
import ensamblador.registros.V0;
import ensamblador.registros.V1;
import ensamblador.registros.Zero;
import ensamblador.unidadFuncional.UFuncional;
import ensamblador.unidadFuncional.UFuncionalEntera;
import ensamblador.unidadFuncional.UFuncionalNoSegm;
import ensamblador.unidadFuncional.UFuncionalSegm;
import ensamblador.util.excepcion.EjecucionException;
import ensamblador.vista.VistaAlgoritmos;

public class ControladorSegmentado extends Controlador {

	private PipelineFlotante pipeline;
	private int salto = 0;
	private Vector<UFuncional> unidFuncionales;
	private IF etapaIF;
    private ID etapaID;
    private EX etapaEX;
    private MEM etapaMEM;
    private WB etapaWB;
	
	public ControladorSegmentado(int salto, Vector<Vector> uf, Vector<Integer> tiposUF) {
		super();
		this.pipeline = PipelineFlotante.getInstancia();
		this.unidFuncionales = crearUF(uf, tiposUF);
		this.salto = salto;
		etapaIF = IF.getInstancia();
    	etapaID = ID.getInstancia();
    	etapaEX = EX.getInstancia();
    	etapaMEM = MEM.getInstancia();
    	etapaWB = WB.getInstancia(); 
	}
	
	public Vector<UFuncional> crearUF(Vector<Vector> uf, Vector<Integer> tiposUF) {
    	Vector<UFuncional> unidFunc = new Vector<UFuncional>();
    	Vector<Integer> aux;
    	
    	unidFunc.add(new UFuncionalEntera(Tipos.INTEGER_FU, 1));
    	for(int i=0; i<tiposUF.size(); i++) {    		
    		aux = uf.elementAt(i);
    		if (tiposUF.elementAt(i).intValue() == Tipos.UF_SEGM) {        		        		
    			for(int n=0; n<aux.elementAt(0).intValue(); n++) {        		
    				unidFunc.add(new UFuncionalSegm(i+1, aux.elementAt(1).intValue()));        			
    			}        		
    		}    		
        	else {        		
        		for(int n=0; n<aux.elementAt(0).intValue(); n++) {        		
        			unidFunc.add(new UFuncionalNoSegm(i+1, aux.elementAt(1).intValue()));        			
        		}        		
        	}    		
    	}
    	
    	return unidFunc;
    }
	
	
	/**
     *Metodo que inicializa el valor de todos los componentes
     *@param sin parametros
     *@return void
     **/
    public void inicializar()
    {        
        super.inicializar();        
        inicializarUF();
        pipeline.inicializar(salto, unidFuncionales);
     
    }
    
    public void inicializarUF() {
    	for(UFuncional uf:this.unidFuncionales) {
    		uf.inicializar();
    	}
    }

    
    public int posPipeline(String dir)
    {
    	return pipeline.posPipeline(dir);
    }
	
	public int ejecutar(int ciclo) {
		Instruccion inst;
		int excep;
		long posPc=getDecimalPC();
	        		
	    inst = instrucc.obtener((int)regPC.getPalabra().getDec());	    
	    excep = pipeline.avanzarPipeline(inst, ciclo); 
	    return excep;    			    
	}
	
	public Image crear_grafica_monociclo()
    {
		int numX, numY, numUF, latMayor;
        
        numUF = etapaEX.getNumeroUF();
        latMayor = 0;
        for(UFuncional uf:etapaEX.getUnidFuncionales()) {
        	if(uf.getLatencia()>latMayor) {
        		latMayor = uf.getLatencia();
        	}
        }
        numX = 40*(4+latMayor)+15*(4+latMayor-1)+30;
        numY = 40*numUF+30*(numUF+1)+5;
		
        
        
        int tamano_lienzo_x;
        int tamano_lienzo_y;
        if(numX>597) 
        	tamano_lienzo_x = numX;
        else
        	tamano_lienzo_x = 597;
        
        if(numY+10>419) 
        	tamano_lienzo_y = numY+10;
        else
        	tamano_lienzo_y = 419;
        
        BufferedImage bi=new BufferedImage(tamano_lienzo_x,tamano_lienzo_y,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g=(Graphics2D)bi.createGraphics();
        
        //se pinta el fondo del panel
        g.setColor(new Color(238, 238, 238));
        g.fillRect(0, 0, tamano_lienzo_x, tamano_lienzo_y);        
        
        //se dibuja el contenido del panel
        g.setColor(Color.BLACK);
        g.drawRect(5, numY/2, 40, 40);
        g.setColor(etapaIF.getEstadoInstruccion().getInstruccion().colorSegm());
        g.fillRect(6, numY/2+1, 39, 39);
        g.setColor(Color.BLACK);
        g.drawString("IF", 25, numY/2-5);
        dibujarFlecha(g, 45, numY/2+20, 60, numY/2+20);
                        
        g.drawRect(60, numY/2, 40, 40);
        g.setColor(etapaID.getEstadoInstruccion().getInstruccion().colorSegm());
        g.fillRect(61, numY/2+1, 39, 39);
        g.setColor(Color.BLACK);
        g.drawString("ID", 75, numY/2-5);
        
        g.setColor(Color.LIGHT_GRAY);
        g.drawRect(115, 30, numX-230, numY-30);
        g.setColor(Color.BLACK);
        g.drawString("EX", numX/2-10, 20);
        
        g.drawRect(numX-100, numY/2, 40, 40);
        g.setColor(etapaMEM.getEstadoInstrucciones()[0].getInstruccion().colorSegm());
        g.fillRect(numX-99, numY/2+1, 39, 19);
        g.setColor(etapaMEM.getEstadoInstrucciones()[1].getInstruccion().colorSegm());
        g.fillRect(numX-99, numY/2+21, 39, 19);
        g.setColor(Color.BLACK);
        g.drawString("MEM", numX-92, numY/2-5);
        dibujarFlecha(g, numX-60, numY/2+20, numX-45, numY/2+20);
        
        g.drawRect(numX-45, numY/2, 40, 40);
        g.setColor(etapaWB.getEstadoInstrucciones()[0].getInstruccion().colorSegm());
        g.fillRect(numX-44, numY/2+1, 39, 19);
        g.setColor(etapaWB.getEstadoInstrucciones()[1].getInstruccion().colorSegm());
        g.fillRect(numX-44, numY/2+21, 39, 19);
        g.setColor(Color.BLACK);
        g.drawString("WB", numX-30, numY/2-5);
        
        
        numX -= 250;
        numY -= 40;
        
        int x=125, y=60;
                      
        int lat = 0;
        UFuncional uf;
        boolean ufSegm = false;
   
        for(int i=0; i<numUF; i++) {        	     	
        	uf = etapaEX.getUnidFuncionales().elementAt(i);
        
        	lat = uf.getLatencia();
        	if(uf instanceof UFuncionalSegm) {
        		ufSegm = true;
        	} 
        	else {
        		ufSegm = false;
        	}
        	
        	x = 125+((latMayor-lat)/2)*55;
        	g.setColor(Color.BLACK);
        	g.drawLine(100, numY/2+40, 115, y+20);        	        	
    		g.drawString(uf.toString(), 75+numX/2, y-5);
        	for(int j=0; j<lat; j++) {          		
        		
        		g.setColor(Color.BLACK);
        		if(ufSegm) {
        			g.drawRect(x, y, 40, 40);
        		}
        		else {
        			if((j==0) || (j==lat-1)) {
        				if(j==0)
        					g.drawRect(x, y, 50, 40);
        				else
        					g.drawRect(x-10, y, 50, 40);
        			}
        			else
        				g.drawRect(x-10, y, 60, 40);
        		}
        		
        		if(j==0){
    				dibujarFlecha(g, 115, y+20, x, y+20);
    			}
        		if(ufSegm && (j!=0)) {        		
        			dibujarFlecha(g, x-15, y+20, x, y+20); 			
        		}
        		if(j==lat-1){
        			g.setColor(Color.BLACK);
					g.drawLine(x+40, y+20, numX+125, y+20);
        		}
        		g.drawLine(numX+125, y+20, numX+135, numY/2+40);
        		dibujarFlecha(g, numX+135, numY/2+40, numX+150, numY/2+40);
        		
        		g.setColor(uf.getInstruccion(j).colorSegm());
        		if(ufSegm) {
        			g.fillRect(x+1, y+1, 39, 39);
        		}
        		else {
        			if((j==0) || (j==lat-1)) {
        				if(j==0)
        					g.fillRect(x+1, y+1, 49, 39);
        				else
        					g.fillRect(x-10+1, y+1, 49, 39);
        			}
        			else
        				g.fillRect(x-10+1, y+1, 59, 39);
        		}

                x+=55; 
        	}
        	y+=70;
        } 
        
        return bi;
    }

    private void dibujarFlecha(Graphics g, int x1, int y1, int x2, int y2) {
    	g.setColor(Color.BLACK);
    	g.drawLine(x1, y1, x2, y2);
    	g.drawLine(x2-5, y2-5, x2, y2);
    	g.drawLine(x2-5, y2+5, x2, y2);
    }

	
	public Image crear_grafica_multiciclo(int numCiclo)
    {
		Vector<Vector<String>> diagramaMult;
		Vector<Instruccion> instMult;
		
		Image burbuja = new BufferedImage(30, 30, BufferedImage.TYPE_3BYTE_BGR); //inicializacion por defecto
		try {
	          burbuja=(Image)ImageIO.read(getClass().getResource(Lenguaje.getInstancia().getString("rutaSegmentado")+"burbuja.gif"));
	    }
	    catch(IOException e) {}
	    
	    
	    instMult = PipelineFlotante.getInstancia().getInstMult();
        diagramaMult = PipelineFlotante.getInstancia().getDiagMult();
        
        int posFinX=0, posX=120, posY=50;
        int i=0;
        Instruccion inst;
        
        for(Vector<String> etapas:diagramaMult) {
      	  if(posFinX<etapas.size()) {
      		  posFinX=etapas.size();
      	  }
        }
        posX=120+posFinX*33;
	    
	    
        int tamano_lienzo_x;
        int tamano_lienzo_y;
        if((posX+40)>597)         
        	tamano_lienzo_x = posX+40;        
        else
        	tamano_lienzo_x = 597;
        
        if((30+diagramaMult.size()*40)>419)
        	tamano_lienzo_y = 30+diagramaMult.size()*40;
        else
        	tamano_lienzo_y = 419;
        
        BufferedImage bi=new BufferedImage(tamano_lienzo_x,tamano_lienzo_y,BufferedImage.TYPE_3BYTE_BGR);
        Graphics2D g=(Graphics2D)bi.createGraphics();
        
        //se pinta el fondo del panel
        g.setColor(new Color(238, 238, 238));
        g.fillRect(0, 0, tamano_lienzo_x, tamano_lienzo_y);        
        
        //se dibuja el contenido del panel
        for(int j=posX; j>120; j-=33)
        {
        	g.setColor(Color.BLACK);
      	  	g.drawString(new Integer(numCiclo).toString(), j+5, 20);
      	  	numCiclo--;
        } 
        
        for(Vector<String> etapas:diagramaMult) {
      	  posX=120+posFinX*33;    	  
      	  inst = instMult.elementAt(i);
  		  g.setColor(inst.colorSegm());
            g.drawString(inst.toString(), 0, posY);
      	  for(int aux=(etapas.size()-1); aux>=0; aux--) {
      		  if(etapas.elementAt(aux).equals(" burb")) {
      			  g.drawImage(burbuja, posX, posY-20, null);
      			  //g.setColor(Color.BLACK);
      			  //g.drawString(etapas.elementAt(aux), posX, posY);
      		  }
      		  else {
      			  g.setColor(inst.colorSegm());
      			  g.fillRect(posX, posY-20, 30, 30);
      			  g.setColor(Color.BLACK);
      			  g.drawString(etapas.elementAt(aux), posX, posY);
      		  }
      		  posX-=33;
      	  }
      	  posY+=40;
      	  i++;
        }
        
        return bi;
    }

	@Override
	public boolean ejecutarCiclo(Palabra breakpoint) throws EjecucionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean ejecutarCicloAnterior(Palabra breakpoint) throws EjecucionException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int ejecutar(Palabra breakpoint) throws EjecucionException {
		// TODO Auto-generated method stub
		return 0;
	}


	
	
}
