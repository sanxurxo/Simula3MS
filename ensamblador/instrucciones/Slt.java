
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Slt extends TipoR {
    Registro r[];
    
    public Slt(String param[]) 
    {
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("slt ");
        for(int i=0; i<numParam();i++)
        {     
            string.append(param[i]);
            if(i!=numParam()-1)
                string.append(", ");
          for(int j=0;j<registros.length;j++)
            {
                try
                {
                        posi=java.lang.Integer.parseInt(param[i].substring(1,param[i].length()));
                }
                catch(NumberFormatException e)
                {
                        r[i]=relacionaReg(param[i]);                       
                        break;
                }
                r[i]=relacionaReg(posi);                
            }     
        }                   
    }
    
    public int numero()
    {
        return 4;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        if(r[1].getPalabra().getDec()< r[2].getPalabra().getDec())
            r[0].setPalabra(new Palabra(1));
        else
            r[0].setPalabra(new Palabra(0));                        
         boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    public int etapa4()
    {
       imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("R_etapa4");
        if(r[1].getPalabra().getDec()< r[2].getPalabra().getDec())
            r[0].setPalabra(new Palabra(1));
        else
            r[0].setPalabra(new Palabra(0));                 
        boolean_dato=true;
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1; 
    }
    
     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(r[1].numReg())+setR(r[2].numReg())+setR(r[0].numReg())+"00000"+setFunc(42);       
        return bin_a_hex(codigo);
    }

     public Registro regModificable()
    {
        return r[0];
    }
     
    public Registro getRegRS()
    {	
        return r[1];
    }
    
    public Registro getRegRT()
    {	
        return r[2];
    }
       
     public boolean hayRiesgo()
     {
        if (r[1].estaLibre() && r[2].estaLibre())
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
        r[0].ocupar();
        
        if(r[1].getPalabra().getDec()< r[2].getPalabra().getDec())
            r[0].setPalabra(new Palabra(1));
        else
            r[0].setPalabra(new Palabra(0));
        
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {    
        r[0].liberar();        
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }        
}
