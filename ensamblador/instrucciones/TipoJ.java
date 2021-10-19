
package ensamblador.instrucciones;
import ensamblador.datos.*;
import ensamblador.registros.*;

public abstract class TipoJ extends Instruccion {

    public static int numParam()
    {
        return 3;
    }
    
     public int numEtapas()
    {
      return 3;
    }
  
    public String setDi(String codDi)
    {
        codDi=codDi.substring(codDi.length()-28,codDi.length()-2);
        return codDi;       
    }

    public abstract int ejecutar();
   
    public int numInst()
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
   
   public abstract int etapa3();
   
    public java.awt.Color colorSegm()
    {
        return new java.awt.Color(4,16,252);
    }
   
    public int estilo()
    {
        return 7;
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
