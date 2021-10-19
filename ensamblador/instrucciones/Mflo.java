
package ensamblador.instrucciones;
import ensamblador.registros.Lo;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;

public class Mflo extends TipoR {
    
    Registro r;
    public Mflo(String param[]) 
    { 
        int posi=-1;
        inicia();
        string.append("mflo ");              
            string.append(param[0]).append(" ");
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
        return 23;
    }
    
    public int ejecutar() 
    {
        Pc.getRegistro().incrementar(4);
      
        r.setPalabra(Lo.getRegistro().getPalabra());
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("R_etapa4");
        r.setPalabra(Lo.getRegistro().getPalabra());
        boolean_dato=true;
        return -1;
    }
    
   public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(0)+setR(0)+setR(r.numReg())+"00000"+setFunc(18);       
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
        if (Lo.getRegistro().estaLibre())
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
        r.setPalabra(Lo.getRegistro().getPalabra());
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
