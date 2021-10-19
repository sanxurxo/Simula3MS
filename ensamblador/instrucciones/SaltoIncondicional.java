
package ensamblador.instrucciones;
import ensamblador.datos.*;
import ensamblador.registros.*;

import java.util.*;

public abstract class SaltoIncondicional extends TipoJ {
    
   public void inicia()
   {
      super.inicIma(imagenes);
      esSalto=true;
      saltoTomado=true;
   }
    
    
  public static int numParam()
  {
      return 1;
  }
  
 public static String esValida (String param[], Etiquetas etiq)
  {      
      String error=null;
      Vector etiquetas=etiq.getNombres();
      if(param.length==1)
      {
         if(!(esEtiqueta(param[0], etiq)))
              error="Etiqueta incorrecta";  
      }
      else    
      {
          error="Numero de parametros no valido";
      }
      
      return error;
  }
    
    public abstract int ejecutar();
    
    public abstract int numero();
    
    public abstract int etapa3();
   
    public boolean hayRiesgo()
    {
        return false;
    }
    
    public abstract int ejecutarIF();
        
    public abstract int ejecutarID();
        
    public abstract int ejecutarEX();
        
    public abstract int ejecutarMEM();
    
    public abstract int ejecutarWB();
  
    public abstract String getCodificacion();    
  
    public abstract Registro regModificable();
  
 
    public Registro getRegRS()
    {	
       return null;    
    }
    
    public Registro getRegRT()
    {	
       return null;
    }
    
}
