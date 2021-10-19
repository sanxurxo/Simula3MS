
package ensamblador.instrucciones;
import java.util.StringTokenizer;

import ensamblador.registros.Registro;

public abstract class TipoI extends Instruccion {
    
    protected static int desp;
    
    
    public static int numParam()
    {
        return 2;
    }
           
    public static boolean esDireccion(String direccion)
    {
        String aux;
     
	Character temp=new Character(direccion.charAt(0));
	Character neg=new Character('-');

        if(Character.isDigit(direccion.charAt(0)) || true)
        {            
            if(direccion.endsWith(")"))
            {
                StringTokenizer st=new StringTokenizer(direccion,"(");
                aux=st.nextToken();
                if(st.hasMoreTokens())
                {
                 try{
                     desp=Integer.parseInt(aux);
                 }catch(NumberFormatException e){return false;}
                aux=st.nextToken();
                }                                      
                st=new StringTokenizer(aux, ")");
                aux=st.nextToken();
            }
            else
                return false;
            
        }
        else
        {
            if((direccion.startsWith("(")) && (direccion.endsWith(")")))            
                aux=direccion.substring(1,direccion.length()-1);                
            else            
                return false;            
        }
        
        if (!esRegistro(aux))
            return false;
        
        return true;
    }
    
    public int getDesplazamiento()
    {
        return desp;
    }

    public String obtReg(String param)
    {
        if(!param.startsWith("$"))
        {
            int c1=param.indexOf("(");
            int c2=param.indexOf(")");
            param=param.substring(c1+1,c2);
        }
        return param;
    }    
    
    public String setIn(int cdIn)
    {
        String cod=Integer.toBinaryString(cdIn);
        if(cod.length()>16)
        {
            cod=cod.substring(cod.length()-16,cod.length());
        }
        else
        {
            while(cod.length()<16)
                cod="0"+cod;       
        }
        return cod;
    }
    public abstract int ejecutar();
    
    public abstract int numEtapas();
    
    public int etapa3()
    {        
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_acc_mem");
        return -1;
    }
    
    public abstract int numero();
    
    public abstract int etapa4();    

    public abstract int etapa5();
    
    public abstract String imagenSegmentado(int etapa); 
    
    public abstract int estilo();
    
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
