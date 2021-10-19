

package ensamblador.instrucciones;
import ensamblador.registros.*;
import ensamblador.datos.*;
import java.util.*;

public class Li extends Inmediatas {
    
    private int inmediato;
    Registro r;
     public Li(String param[])
    {
        int posi=-1;
        inicia();
        //variable=param[1];     
        String r1 =new String(param[0]);
        string.append("li ").append(r1).append(", ");
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
       if(param[numParam()].startsWith("0x"))
           inmediato=Integer.parseInt(param[numParam()].substring(2,param[numParam()].length()),16);
       else
           inmediato=Integer.parseInt(param[numParam()]);
       string.append(param[numParam()]);
    }
    
     public static int numParam()
     {
         return 1;
     }
     
    
     
    public int numero()
    {
        return 58;
    }
    
    public int ejecutar()
    {
     return -1;
       
    }
    
    public int etapa5()
    {
        imagen_etapa=new StringBuffer();
         imagen_etapa.append(direccion_multi).append("etapa5");       
//        r.modificar(Memoria.getMemoria().getDireccion(variable));
//        boolean_dato=true;
         return -1; 
    }
    
     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(0)+setR(0)+setIn(0);       
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
      
    public String imagenSegmentado(int etapa)
    {
        return "nop.gif";
    }
     
    public boolean hayRiesgo()
    {
        return false;
    }
    
    public int ejecutarIF()
    {
        return -1;
    }
        
    public int ejecutarID()
    {
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
	
	public int etapa4() {
		
		return -1;
	}
   
}
