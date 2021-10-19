
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Sw extends Almacenamiento {
    
    Registro r[];
    private int desplazamiento;
    public Sw(String param[])
    {
        int posi=-1;
        inicia();
        int numReg=2;
        this.desplazamiento=desp;
        r=new Registro[numReg];        
        string.append("sw ").append(param[0]).append(", ").append(param[1]);
        int ind1;
            if((ind1=param[1].indexOf("$"))!=0)
            {
                int ind2=param[1].indexOf(")");
                param[1]=param[1].substring(ind1,ind2);
            }  
        for(int i=0; i<numReg;i++)
        {         
            String registro=obtReg(param[i]);         
            String r1 =new String(registro);
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
        desplazamiento=super.getDesplazamiento();        
    }       
    
    public int numero()
    {
        return 14;
    }
    
     public int ejecutar()
    {
          Pc.getRegistro().incrementar(4);
          boolean_pc=true;        
          
         
          long direccion=r[1].getPalabra().getDireccion()+desplazamiento;
          if((direccion%4)!=0)
            return 3;
          Palabra pES=new Palabra(direccion);
          if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))            
          {
               Pila.getPila().setPalabra(direccion-desplazamiento,desplazamiento,r[0].getPalabra());
               boolean_pila=true;
          }
            else
              if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))
                {
                	
                    Memoria.getMemoria().setPalabra(direccion-desplazamiento,desplazamiento, r[0].getPalabra());          
                    boolean_mem=true;
                }else if((pES.getHex().compareTo(INICIO_ES.getHex())>=0) && (pES.getHex().compareTo(FIN_ES.getHex())<=0)){ 
                //}else if((direccion>=INICIO_ES.getDec()) && (direccion<=FIN_ES.getDec())){
            	  EntradaSalida.getEntradaSalida().setValor(direccion, r[0].getPalabra());            	  
				}else{            
					
                    this.cambiosVisibles();
                    return 3;
                }
          this.cambiosVisibles();
          return -1;
     }

     public int etapa4()
     {         
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_store");

          int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
           if((direccion%4)!=0)
                return 3;
          if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))
          {
               Pila.getPila().setPalabra(direccion-desplazamiento,desplazamiento,r[0].getPalabra());
               boolean_pila=true;
          }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))
                {
                    Memoria.getMemoria().setPalabra(direccion-desplazamiento,desplazamiento, r[0].getPalabra());          
                    boolean_mem=true;
                }
                else
                {
                    this.cambiosVisibles();
                    return 3;
                }
          this.cambiosVisibles();
          return -1;
     }
     
     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(43)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(desplazamiento);       
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
        return -1;
    }
    
    public int ejecutarEX()
    {
        return -1;
    }
    
    public int ejecutarMEM()
    {
        int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;       
        if((direccion%4)!=0)
            return 3;
        if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))
        {
            Pila.getPila().setPalabra(direccion-desplazamiento,desplazamiento,r[0].getPalabra());            
            boolean_pila=true;
        }
        else        
            if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))            
            {
                Memoria.getMemoria().setPalabra(direccion-desplazamiento,desplazamiento, r[0].getPalabra());                          
                boolean_mem=true;
            }
            else            
            {
                this.cambiosVisibles();
                return 3;
            }
        this.cambiosVisibles();
        return -1;
    }
    
    public int ejecutarWB()
    {            
         return -1;                                      
    }
}
