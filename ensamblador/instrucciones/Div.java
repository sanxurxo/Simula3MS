
package ensamblador.instrucciones;


import ensamblador.registros.*;
import ensamblador.datos.*;
public class Div extends TipoR {
    
    Registro r[];
    long hi;
    long lo;
     public Div(String param[]) 
    {
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("div ");
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
    
    public static int numParam()
    {
        return 2;
    }
    
    public int numero()
    {
        return 21;
    }
    
    public int ejecutar() 
    {
        Pc.getRegistro().incrementar(4);        
        if(r[1].getPalabra().getDec()==0)        
            return 1;            
        
        hi=r[0].getPalabra().getDec()%r[1].getPalabra().getDec();
        lo=r[0].getPalabra().getDec()/r[1].getPalabra().getDec();
        Hi.getRegistro().setPalabra(new Palabra(hi));
        Lo.getRegistro().setPalabra(new Palabra(lo));        
        boolean_pc=true;
        boolean_hi_lo=true;
        this.cambiosVisibles();
        return -1;
    }
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        int except=-1;
        imagen_etapa.append(direccion_multi).append("R_etapa4");        
        hi=r[0].getPalabra().getDec()%r[1].getPalabra().getDec();
        lo=r[0].getPalabra().getDec()/r[1].getPalabra().getDec();
        Hi.getRegistro().setPalabra(new Palabra(hi));
        Lo.getRegistro().setPalabra(new Palabra(lo));        
        boolean_pc=true;
        boolean_hi_lo=true;
        this.cambiosVisibles();
        return -1;
    }

    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        int except=-1;
        imagen_etapa.append(direccion_multi).append("R_etapa3");
         if(r[1].getPalabra().getDec()==0)        
            return 1;                 
        return -1;
    }
    
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(r[0].numReg())+setR(r[1].numReg())+setR(0)+"00000"+setFunc(26);       
        return bin_a_hex(codigo);
    }
    
    public Registro regModificable()
    {
        return null;
    }
    
    public Registro getRegRS()
    {	
        return r[0];
    }
    
    public Registro getRegRT()
    {	
        return r[1];
    }
    
    public boolean hayRiesgo()
    {
        if (r[0].estaLibre() && r[1].estaLibre())
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
        Hi.getRegistro().ocupar();
        Lo.getRegistro().ocupar();
        
        if(r[1].getPalabra().getDec()==0)        
            return 1;
        
        long hi=r[0].getPalabra().getDec()%r[1].getPalabra().getDec();
        long lo=r[0].getPalabra().getDec()/r[1].getPalabra().getDec();
        Hi.getRegistro().setPalabra(new Palabra(hi));
        Lo.getRegistro().setPalabra(new Palabra(lo));
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {     
        Hi.getRegistro().liberar();
        Lo.getRegistro().liberar();
        boolean_hi_lo=true;
        this.cambiosVisibles();
        return -1;               
    }

}
