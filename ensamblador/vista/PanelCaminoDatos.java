
package ensamblador.vista;

import java.awt.*;
import java.io.*;
import javax.imageio.*;
import javax.swing.*;

import ensamblador.datos.Tipos;
import ensamblador.datos.Lenguaje;
import ensamblador.etapas.*;

/**PanelImagen.java
 *Clase que se encarga de la visualizacion de la representacion monociclo del camino de datos segmentado 
 **/
public class PanelCaminoDatos extends JPanel implements Tipos
{
    private Image imSegmentado,imagenIF,imagenID,imagenEX,imagenMEM,imagenWB,
                  imagenRiesgo,imagenAntMEM_RS,imagenAntMEM_RT,imagenAntWB_RS,imagenAntWB_RT,imagenFlush;
    
    PipelineFlotante pipeline;
    private UnidadAnticipacion unidadAnticipacion;
    private UnidadDeteccionRiesgos unidadDeteccionRiesgos;
    
/**Constructor de PanelImagen
 *@param Sin parametros
 **/
    public PanelCaminoDatos()
    {
        try
        {
            imSegmentado=(Image)ImageIO.read(getClass().getResource(Lenguaje.getInstancia().getString("rutaSegmentado")+"segmentado.gif"));
            pipeline = PipelineFlotante.getInstancia();
            unidadAnticipacion = UnidadAnticipacion.getInstancia();
            unidadDeteccionRiesgos = UnidadDeteccionRiesgos.getInstancia();
        }
        catch(IOException e) {}
    }
    
    
    public void actualizarImag()
    {
        try
        {                    	
            imagenIF=(Image)ImageIO.read(getClass().getResource(
            		"/ensamblador/segmentado/"+IF.getInstancia().getEstadoInstruccion().getInstruccion().imagenSegmentado(0).toLowerCase()));
            imagenID=(Image)ImageIO.read(getClass().getResource(
            		"/ensamblador/segmentado/"+ID.getInstancia().getEstadoInstruccion().getInstruccion().imagenSegmentado(1).toLowerCase()));
            imagenEX=(Image)ImageIO.read(getClass().getResource(
            		"/ensamblador/segmentado/"+EX.getInstancia().getEstadoInstEntera().getInstruccion().imagenSegmentado(2).toLowerCase()));
            imagenMEM=(Image)ImageIO.read(getClass().getResource(
            		"/ensamblador/segmentado/"+MEM.getInstancia().getEstadoInstrucciones()[0].getInstruccion().imagenSegmentado(3).toLowerCase()));
            imagenWB=(Image)ImageIO.read(getClass().getResource(
            		"/ensamblador/segmentado/"+WB.getInstancia().getEstadoInstrucciones()[0].getInstruccion().imagenSegmentado(4).toLowerCase()));
            

            if (unidadDeteccionRiesgos.detener())
                imagenRiesgo=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/riesgo.gif"));
            else
            	if(ID.getInstancia().getEstadoInstruccion().getInstruccion().toString().equals("nop"))
            		imagenRiesgo=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/nop.gif"));
            	else
            		imagenRiesgo=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/no_riesgo.gif"));
            
            boolean ant[] = unidadAnticipacion.anticipar();
            if (ant[ANT_RS_MEM]) 
                imagenAntMEM_RS=(Image)ImageIO.read(getClass().getResource(("/ensamblador/segmentado/antMEM_RS.gif").toLowerCase()));          
            else
                imagenAntMEM_RS=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/nop.gif"));
            if (ant[ANT_RT_MEM])            
                imagenAntMEM_RT=(Image)ImageIO.read(getClass().getResource(("/ensamblador/segmentado/antMEM_RT.gif").toLowerCase()));                           
            else
                imagenAntMEM_RT=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/nop.gif"));
            if (ant[ANT_RS_WB])             
                imagenAntWB_RS=(Image)ImageIO.read(getClass().getResource(("/ensamblador/segmentado/antWB_RS.gif").toLowerCase()));                                                
            else
                imagenAntWB_RS=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/nop.gif"));
            if (ant[ANT_RT_WB]) 
                imagenAntWB_RT=(Image)ImageIO.read(getClass().getResource(("/ensamblador/segmentado/antWB_RT.gif").toLowerCase()));              
            else
                imagenAntWB_RT=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/nop.gif"));
            
            if(pipeline.activarIFFlush())
                imagenFlush=(Image)ImageIO.read(getClass().getResource(("/ensamblador/segmentado/IF_Flush.gif").toLowerCase()));
            else
                imagenFlush=(Image)ImageIO.read(getClass().getResource("/ensamblador/segmentado/nop.gif"));
        }
        catch(IOException e) {
        	System.out.println("Error al crear las imagenes");
        }
      }
    
    
/**Metodo para pintar y dibujar en este panel
 *@param Graphics grafico
 *@return void
 **/
    public void paintComponents(Graphics g)
    {
        super.paintComponent(g);
        if (imSegmentado==null)
            return;
        actualizarImag();
        g.drawImage(imSegmentado,0,0,null);
        g.drawImage(imagenIF,0,0,null);
        g.drawImage(imagenID,0,0,null);
        g.drawImage(imagenEX,0,0,null);
        g.drawImage(imagenMEM,0,0,null);
        g.drawImage(imagenWB,0,0,null);
        g.drawImage(imagenRiesgo,0,0,null);
        g.drawImage(imagenAntMEM_RS,0,0, null);
        g.drawImage(imagenAntMEM_RT,0,0, null);
        g.drawImage(imagenAntWB_RS,0,0, null);
        g.drawImage(imagenAntWB_RT,0,0, null);
        g.drawImage(imagenFlush,0,0, null);
      }   
}
