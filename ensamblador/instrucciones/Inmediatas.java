
package ensamblador.instrucciones;
import ensamblador.registros.Registro;

public abstract class Inmediatas extends TipoI {
private String imagSegmentado[]={"inmediatasIF.gif","inmediatasID.gif","inmediatasEX.gif","inmediatasMEM.gif","inmediatasWB.gif"};
        
    
    public void inicia()
    {
        String imagenes="inmed.gif";
        super.inicIma(imagenes);
    }
    
    public static String esValida(String[] param, int numParam)
    {           
         String error=null;
        
        if(param.length==numParam+1)
        {
            for(int i=0; i<numParam;i++)
            {
                if(esRegistro(param[i]))
                {
                        if(param[i].equals("$zero") || param[i].equals("$0"))
                        {
                            if(i==0)
                                error+=param[i]+"parametros no validos. Reg esp"; 
                        }                    
                }
                else
                {
                    error=param[i]+"parametros no validos. No es Reg";
                     return error;
                }
            }                    
            try{
		if((param[numParam].startsWith("0x")) && (param[numParam].length()>2) && (((param[numParam]).trim()).length()<11))
		{  
		
		    try
		    {  
		    	Long.parseLong(param[numParam].substring(2,param[numParam].length()), 16);

		    }catch (NumberFormatException e)
		    {   
		    error=param[numParam]+"parametros no validos";
		    }
		}
		else { 

			Long.parseLong(param[numParam]);   

		}
            }catch(NumberFormatException e){
		error=param[numParam]+"parametros no validos";}
        }
        else
             error="parametros no validos. Numero de parametros incorrecto";
        
        return error;
    }

    
    public abstract int ejecutar();
    public abstract String getCodificacion();    
    
    public int numEtapas() 
    {
        return 4;
    }   
    public int etapa3()
    {        
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_inmed");
        return -1;
    }
    
    public abstract int etapa4();
    
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
        return new java.awt.Color(20,185,233);
    }
    
    public int estilo()
    {
        return 3;
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
