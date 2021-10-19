
package ensamblador.instrucciones;
import ensamblador.datos.*;
import ensamblador.registros.*;

import java.util.*;

public abstract class SaltoCondicional extends TipoI {
 private String imagSegmentado[]={"beqIF.gif","beqID.gif","beqEX.gif","beqMEM.gif","beqWB.gif"};
    
   public void inicia()
   {
       String imagenes="beq.gif";
       super.inicIma(imagenes);
       esSalto=true;
   }
      
   public static int numParam()
   {
       return 3;
   }
   
   
   public static String esValida(String[] param, Etiquetas etiq)
   {
       boolean esValido=false;
       String error=null;   /*0 indica que es valido*/
       Vector etiquetas=etiq.getNombres();
        if(param.length==numParam())
        {
            for(int i=0; i<numParam()-1;i++)
            {
                if(esRegistro(param[i]))                
                    esValido=true;
                               
                else
                    error=param[i]+"parametros no validos";
            }
            if(!esEtiqueta(param[2], etiq))
                error="parametros no validos";
        }
        else
            error="numero de parametros incorrecto"+ param.length; 
        return error;   
   }    
   
      public abstract int ejecutar();
      public abstract String getCodificacion();    
      
      public int numEtapas() 
      {
          return 3;
      }            
      
      public int etapa4() 
      {
          return -1;
      }
      
      public int etapa5() 
      {
          return -1;
      }
       
    public String imagenSegmentado(int etapa)
    {
        return imagSegmentado[etapa];
    }
    
    public java.awt.Color colorSegm()
    {
        return new java.awt.Color(209,22,226);
    }
    
    public int estilo()
    {
        return 6;
    }
    
    public abstract int numero();
    
    public abstract boolean hayRiesgo();
    
    public abstract int ejecutarIF();
        
    public abstract int ejecutarID();
        
    public abstract int ejecutarEX();
        
    public abstract int ejecutarMEM();
    
    public abstract int ejecutarWB();    
  
    public abstract Registro regModificable();
    
    public abstract Registro getRegRS();
    
    public abstract Registro getRegRT();
}
