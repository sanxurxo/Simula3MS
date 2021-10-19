
package ensamblador.instrucciones;

import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
public class Nop extends Instruccion {
    
    public Nop() 
    {
        inicia();
        string.append("nop");
    }
    
    public static String esValida(String param[])
    {
        if(param.length!=0)
            return "Instruccion no valida";
        return null;
    }
    
   public void inicia()
   {
       String imagenes="monociclo.gif";
       super.inicIma(imagenes);
   }
    
    public int numero()
    {
        return 24;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        return -1;
    }
    
    public String imagenSegmentado(int etapa){
        return "nop.gif";
    }
    
    public java.awt.Color colorSegm()
    {
        return new java.awt.Color(140,140,140);
    }
    
    public int estilo()
    {
        return 8;
    }
    
    public int numEtapas() 
    {
        return 1;
    }
   
    public int etapa1()
    {
        imagen_etapa=new StringBuffer();
        boolean_pc=true;
        Pc.getRegistro().incrementar(4);        
        imagen_etapa.append(direccion_multi).append("caminodatos");
        return -1;
    }
     
    public int etapa3() 
    {
        return -1;        
    }
    
    public int etapa4() 
    {
        return -1;
    }
    
    public int etapa5() 
    {
        return -1;
    }
    
    public String getCodificacion()
    {
        return "0x00000000";
    }
    
    public Registro regModificable()
    {
        return null;
    }
    
    public Registro getRegRS()
    {	
        return null;
    }
    
    public Registro getRegRT()
    {	
        return null;
    }
    
    public boolean hayRiesgo()
    {
        return false;
    }
    
    public int ejecutarIF()
    {
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
        
    public int ejecutarID()
    {
        return -1;
    }
    
    public int ejecutarEX()
    {
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()    
    {
        return -1;
    }
    
}
