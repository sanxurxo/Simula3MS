
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Jr extends TipoR {
    private String imagSegmentado[]={"R_IF.gif","jrID.gif","jrEX.gif","jrMEM.gif","jrWB.gif"};
    String imagenes="jr.gif";
    Registro r; 
    
    public Jr(String param[]) 
    {
    	esSalto=true;
        saltoTomado=true;
        int posi=-1;
        inicia();
        string.append("jr ");
            string.append(param[0]);
            
           for(int j=0;j<registros.length;j++)
            {
                try
                {
                        posi=java.lang.Integer.parseInt(param[0].substring(1,param[0].length()));
                }
                catch(NumberFormatException e)
                {
                         r=relacionaReg(param[0]);                       
                        break;
                }
                r=relacionaReg(posi);
                
            }             
    }
    public static int numParam()
    {
        return 1;
    }
    
    public int numero()
    {
        return 19;
    }
    
    public int ejecutar() 
    {
    	
        Palabra salto=r.getPalabra();
        if((salto.getDec()>=INICIO_PC.getDec())){  // && (salto.getDec()<Codigo.getInstacia().tamanho()*4+INICIO_PC.getDec()))                        
            Pc.getRegistro().setPalabra(r.getPalabra());
        }
        else
        {
            boolean_pc=true;
            this.cambiosVisibles();        
            return 2;
        }
        boolean_pc=true;        
        this.cambiosVisibles();
        return -1;
    }
    
    public int numEtapas()
    {
        return 3;
    }
    
    public int etapa3()
    {        
        imagen_etapa=new StringBuffer();
        Palabra salto=r.getPalabra();
        imagen_etapa.append(direccion_multi).append("jr");
        if((salto.getDec()>=INICIO_PC.getDec()) && (salto.getDec()<Codigo.getInstacia().tamanho()*4+INICIO_PC.getDec()))                        
        {
            Pc.getRegistro().setPalabra(r.getPalabra());
            boolean_pc=true;
        }
        else
            return 2;
        return -1;
    }

    public int etapa4()
    {
        return -1;
    }
    public static String esValida(String[] param)
    {
       // boolean esValido=false;
        String error=null;  
        
         if(param.length==numParam())
        {
                if(esRegistro(param[0]))
                {
                           if(param[0].equals("$zero"))
                        {
                                error+=param[0]+"parametros no validos. Reg esp"; 
                        }            
                }
                else
                    error=param[0]+"parametros no validos. No es Reg";      
        }
        else
             error="parametros no validos";
             
        return error;
    }
    

     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(r.numReg())+setR(0)+setR(0)+"00000"+setFunc(8);       
        return bin_a_hex(codigo);
    }
         
    public Registro regModificable()
    {
        return null;
    }
    
    public Registro getRegRS()
    {	
        return r;
    }
    
    public Registro getRegRT()
    {	
        return null;
    }
        
    public boolean hayRiesgo()
    {
        if (r.estaLibre())
            return false;
        return true;
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
        boolean_pc=true;
        Palabra salto=r.getPalabra();
        if((salto.getDec()>=INICIO_PC.getDec()) && (salto.getDec()<Codigo.getInstacia().tamanho()*4+INICIO_PC.getDec()))                        
            Pc.getRegistro().setPalabra(r.getPalabra());
        else
        {
            this.cambiosVisibles();
            return 2;
        }
        this.cambiosVisibles();
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
   
    
    public String imagenSegmentado(int etapa)
    {
        return imagSegmentado[etapa];
    }
    
}
