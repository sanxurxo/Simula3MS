
package ensamblador.instrucciones;

import ensamblador.datos.Palabra;
import ensamblador.registros.Hi;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
public class Mfhi extends TipoR {
    
    Registro r;
    public Mfhi(String param[]) 
    {
        int posi=-1;
        inicia();
        string.append("mfhi ");              
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
        return 22;
    }
    
    public int ejecutar() 
    {
        Pc.getRegistro().incrementar(4);
       
        r.setPalabra(Hi.getRegistro().getPalabra());
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        //Pc.getRegistro().incrementar(4);
        imagen_etapa.append(direccion_multi).append("R_etapa4");
        r.setPalabra(Hi.getRegistro().getPalabra());
        boolean_dato=true;
        return -1;
        
    }

    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(0)+setR(0)+setR(r.numReg())+"00000"+setFunc(16);       
        return bin_a_hex(codigo);
    }
    
    public Registro regModificable()
    {
        return r;
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
        if (Hi.getRegistro().estaLibre())
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
        return -1;
    }
    
    public int ejecutarEX()
    {
        r.ocupar();
        r.setPalabra(Hi.getRegistro().getPalabra());
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {   
        r.liberar();
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }

   
    
}
