
package ensamblador.instrucciones;

import ensamblador.registros.Registro;
public abstract class Carga extends TipoI {
private String imagSegmentado[]={"cargaIF.gif","cargaID.gif","cargaEX.gif","cargaMEM.gif","cargaWB.gif"};
       
    public void inicia()
    {
        String imagenes="lw.gif";
        super.inicIma(imagenes);
    }
    
    public static String esValida(String[] param)
    {
        desp=0; 
        boolean esValido=false;
        String error=null;
        if (param.length==numParam())
        {
            if(esRegistro(param[0]) || param[0].equals("$0"))
            {            
                esValido=true;                 
                if(param[0].equals("$zero"))                                                   
                    error="parametros no validos"; 
                if ((!(esDireccion(param[1])) && (!(esVariable(param[1])))))
                    error="parametros no validos, direccion"+ esDireccion(param[1])+ " variables"+ esVariable(param[1]);
            }
            else
                error="parametros no validos";            
        }
        else
            error="numero de parametros no valido";       
         return error;
    }
    
    public static String esValidaF(String[] param)
    {
        desp=0; 
        boolean esValido=false;
        String error=null;
        if (param.length==numParam())
        {
            if(esRegistroF(param[0]))
            {            
                esValido=true;                 
                if ((!(esDireccion(param[1])) && (!(esVariable(param[1])))))
                    error="parametros no validos, direccion"+ esDireccion(param[1])+ " variables"+ esVariable(param[1]);
            }
            else
                error="parametros no validos";            
        }
        else
            error="numero de parametros no valido";       
         return error;
    }
    public abstract String getCodificacion();      
    public abstract int ejecutar();
      
    public int numEtapas() 
    {
        return 5;
    }

    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_load");
        return -1;
    }
      
    public abstract int etapa5();
       
    public String imagenSegmentado(int etapa)
    {
        return imagSegmentado[etapa];
    }
    
    public java.awt.Color colorSegm()
    {
        return new java.awt.Color(50,166,94);
    }
    
    public int estilo()
    {
        return 4;
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
