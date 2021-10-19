
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Bne extends SaltoCondicional {

    Registro r[];
    String etiqueta;
    private int posIns;
    
     public Bne(String param[],int p)
    {
    	 esSalto=true;
          posIns=(int)INICIO_PC.getDec()+(p)*4+4;
        int posi=-1;
        inicia();
        r = new Registro[numParam()];
        string.append("bne ");
        for(int i=0; i<numParam()-1;i++)
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
        etiqueta=param[2];
        string.append(etiqueta);
    }    
     
    public int numero()
    {
        return 11;
    }
    

    public int ejecutar()
    {    	
        if(!r[0].getPalabra().getHex().equals(r[1].getPalabra().getHex()))
        {
            int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
            Pc.getRegistro().modificar(i);
            boolean_pc=true;
        }
        else
            Pc.getRegistro().incrementar(4);
        boolean_pc=true;        
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        if(!r[0].getPalabra().getHex().equals(r[1].getPalabra().getHex()))
        {
            int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
            Pc.getRegistro().modificar(i);
            boolean_pc=true;
             imagen_etapa.append(direccion_multi).append("bne");
        }
        else
             imagen_etapa.append(direccion_multi).append("bne_no");
      
        return -1;
    }
 
    public String getCodificacion()
    {
       int posSalto=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
       int salto=(posSalto-posIns)/4;
        int offset=(salto);              
        String codigo;
        codigo=setOp(5)+setR(r[0].numReg())+setR(r[1].numReg())+setIn(offset);       
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
        if(r[0].estaLibre() && r[1].estaLibre())
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
 
        saltoTomado=false;
//        if(hayRiesgo())
//            return -1;
        if(!r[0].getPalabra().getHex().equals(r[1].getPalabra().getHex()))
        {
        	
            int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
            Pc.getRegistro().modificar(i);
            boolean_pc=true;
            saltoTomado=true;
        }
        else
        {
        	
            Pc.getRegistro().incrementar(4);
            boolean_pc=true;
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
      
}
