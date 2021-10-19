
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Sub extends TipoR {
    Registro r[];
    
    public Sub(String param[]) 
    {   
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("sub ");
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
        return 2;
    }
     
    public int ejecutar()
    {
        int excep=-1;
        Pc.getRegistro().incrementar(4);
        long resultado=(r[1].getPalabra().getDec()-r[2].getPalabra().getDec());
        if(resultado<(TOPE_NEG.getDec()))
        {
            resultado=resultado%(TOPE_NEG.getDec());
            excep=0;
        }  
        if(resultado>(TOPE_POS.getDec()))
        {
            resultado=resultado%(TOPE_POS.getDec()+1);
            excep=0;
        }   
        r[0].setPalabra(new Palabra(resultado));        
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return excep;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();        
        int excep=-1;
        imagen_etapa.append(direccion_multi).append("R_etapa4");
        long resultado=(r[1].getPalabra().getDec()-r[2].getPalabra().getDec());
        if(resultado<(TOPE_NEG.getDec()))
        {
            resultado=resultado%(TOPE_NEG.getDec());
        }         
        if(resultado>(TOPE_POS.getDec()))
        {
            resultado=resultado%(TOPE_POS.getDec()+1);
        }   
        r[0].setPalabra(new Palabra(resultado));        
        boolean_dato=true;
        return excep;
    }
    
    public int etapa3()
    {
        imagen_etapa=new StringBuffer();        
        int excep=-1;
        imagen_etapa.append(direccion_multi).append("R_etapa3");
        long resultado=(r[1].getPalabra().getDec()-r[2].getPalabra().getDec());
        if((resultado<(TOPE_NEG.getDec()))||(resultado>TOPE_POS.getDec()))
            return 0;
        return -1;
    }
    
     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(r[1].numReg())+setR(r[2].numReg())+setR(r[0].numReg())+"00000"+setFunc(34);       
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
    	int excep=-1;
        r[0].ocupar();
        /*long resultado=r[1].getPalabra().getDec()-r[2].getPalabra().getDec();
        if(resultado<(0-TOPE.getDec()+1))        
            return 0;*/
        
        long resultado=r[1].getPalabra().getDec()-r[2].getPalabra().getDec();
        if(resultado<(TOPE_NEG.getDec()))
        {
            resultado=resultado%(TOPE_NEG.getDec());
            excep=0;
        }
        if(resultado>(TOPE_POS.getDec()))
        {
            resultado=resultado%(TOPE_POS.getDec()+1);
            excep=0;
        }   
        r[0].setPalabra(new Palabra(resultado));
        
        return excep;
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