
package ensamblador.instrucciones;
import ensamblador.datos.*;
import ensamblador.registros.*;

public abstract class TipoR extends Instruccion{
protected String imagSegmentado[]={"R_IF.gif","R_ID.gif","R_EX.gif","R_MEM.gif","R_WB.gif"};
            
   public void inicia()
   {
       String imagenes="R.gif";
       super.inicIma(imagenes);
   }
    
    public static int numParam()
    {
        return 3;
    }

    public int numEtapas()
    {
        return 4;
    }
     
    public static String esValida(String[] param, int numParam)
    {
        String error=null;  
        
        if(param.length==numParam)
        {
            for(int i=0; i<numParam;i++)
            {
                if(esRegistro(param[i]))
                {
                        if(param[i].equals("$zero")  || param[i].equals("$0"))
                        {
                            if(i==0)
                                error+=param[i]+"parametros no validos. Reg esp"; 
                        }
                }
                else
                    error=param[i]+"parametros no validos. No es Reg";
            }                
        }
        else
             error="parametros no validos";
             
        return error;
    }
    
    public static String esValidaF(String[] param, int numParam)
    {
        String error=null;  
        
        if(param.length==numParam)
        {
            for(int i=0; i<numParam;i++)
            {
                if(!esRegistroF(param[i]))
                    error=param[i]+"parametros no validos. No es Reg";
            }                
        }
        else
             error="parametros no validos";
             
        return error;
    }
        
    public static String esValidaFD(String[] param, int numParam)
    {
        String error=null;  
        
        if(param.length==numParam)
        {
            for(int i=0; i<numParam;i++)
            {
                if(!esRegistroFPar(param[i]))
                    error=param[i]+"parametros no validos. No es Reg";
            }                
        }
        else
             error="parametros no validos";
             
        return error;
    }
    
    public static String esValidaFR(String[] param, int numParam)
    {
        String error=null;  
        
        if((param.length==numParam)&&(numParam==2))
        {
                if(!esRegistroF(param[0]))
                    error=param[0]+"parametros no validos. No es Reg";
                if(!esRegistro(param[1]))
                    error=param[1]+"parametros no validos. No es Reg";
        }
        else
             error="parametros no validos";
             
        return error;
    }
    
    
    
    public static String esValidaRF(String[] param, int numParam)
    {
        String error=null;  
        
        if((param.length==numParam)&&(numParam==2))
        {
                if(!esRegistro(param[0]))
                    error=param[0]+"parametros no validos. No es Reg";
                if(!esRegistroF(param[1]))
                    error=param[1]+"parametros no validos. No es Reg";
        }
        else
             error="parametros no validos";
             
        return error;
    }
        
    public String setFunc(int cdFu)
    {       
        String cod=Integer.toBinaryString(cdFu);
        while(cod.length()<6)
            cod="0"+cod;       
        return cod;
    }
  
      public abstract int ejecutar();
      
      public int numInst() 
      {
          return 4;
      }
    
      public int etapa3()
      {
          imagen_etapa=new StringBuffer();
          imagen_etapa.append(direccion_multi).append("R_etapa3");
          return -1;
      }
      
      public abstract int etapa4();      
      public abstract Registro regModificable();      
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
        return new java.awt.Color(243,157,151);
    }
    
    public int estilo()
    {
        return 2;
    }
    
    public String setShamt(int cdIn)
    {
        String cod=Integer.toBinaryString(cdIn);
        if(cod.length()>5)
        {
            cod=cod.substring(cod.length()-5,cod.length());
        }
        else
        {
            while(cod.length()<5)
                cod="0"+cod;       
        }
        return cod;
    }
    public abstract int numero();
    
    public abstract boolean hayRiesgo();
    
    public abstract int ejecutarIF();
        
    public abstract int ejecutarID();
        
    public abstract int ejecutarEX();
        
    public abstract int ejecutarMEM();
    
    public abstract int ejecutarWB();    
  
    public abstract Registro getRegRS();
    
    public abstract Registro getRegRT();
}
