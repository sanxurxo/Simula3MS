
package ensamblador.instrucciones;
import ensamblador.registros.*;
import ensamblador.datos.*;

public class J extends SaltoIncondicional {    
  private String imagSegmentado[]={"jumpIF.gif","jumpID.gif","jumpEX.gif","jumpMEM.gif","jumpWB.gif"};
    String imagenes="j.gif";  
    String etiqueta;

    public J(String param[])
    {    
    	esSalto=true;
        saltoTomado=true;
        inicia();
        etiqueta=param[0];
        string.append("j ").append(etiqueta);
    }    
    
    public void inicia()
   {
       String imagenes="jump.gif";
       super.inicIma(imagenes);
   }
    
    public static int numParam()
    {
        return 1;
    }  
    
    public int numero()
    {
        return 12;
    }
    
    public int ejecutar()    
    {
        int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
        Pc.getRegistro().modificar(i);    
        boolean_pc=true;        
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_salto_incond");
        int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
        Pc.getRegistro().modificar(i);    
        boolean_pc=true;
        return -1;
    }
    
     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(2)+setDi((new Palabra(Etiquetas.getEtiquetas().getDireccion(etiqueta))).getBin());       
        return bin_a_hex(codigo);
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
    
    public int ejecutarIF()
    {
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int ejecutarID()
    {
        int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);        
        Pc.getRegistro().modificar(i);
        boolean_pc=true;
        this.cambiosVisibles();       
        return -1;
    }
    
    public int ejecutarEX()
    {
    	int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);        
        Pc.getRegistro().modificar(i);
        boolean_pc=true;
        this.cambiosVisibles();
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
    
    public String imagenSegmentado(int etapa)
    {
        return imagSegmentado[etapa];
    }  
}
