
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Sb extends Almacenamiento {
    Registro r[];
    private int desplazamiento;
    
    public Sb(String param[])
    {
        int posi=-1;
        inicia();
        int numReg=2;
        r=new Registro[numReg];
        string.append("sb ").append(param[0]).append(", ").append(param[1]);
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
        desplazamiento=getDesplazamiento();
        
    }

    public int numero()
    {
        return 16;
    }
    

     public int ejecutar()
    {
          Pc.getRegistro().incrementar(4);
          boolean_pc=true;                      
          
          long direccion=r[1].getPalabra().getDireccion();
          int desp=desplazamiento/4;
          int des=desplazamiento%4;
          Palabra p=r[1].getPalabra();
          p=p.sumar(desp*4);
          p=p.sumar(des);
          
          long direcFinal=(direccion+(desp*4)+des);
          
          if(((direccion+(desp*4)+des)<(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=(Sp.getRegistro().getPalabra().getDec())))            
          {
               Pila.getPila().setByte(direccion+(desp*4),des,r[0].getPalabra());
               boolean_pila=true;
          }
          else															//tiene que ser < estricto pq si no funciona como reserva dinamica y no estatica
              if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<(Memoria.getMemoria().obtPosMem())+4))
              {
                  Memoria.getMemoria().setByte(direccion,desplazamiento, r[0].getPalabra());                      
                  boolean_mem=true;
              }
     //         else if(((direcFinal)>=INICIO_ES.getDec()) &&((direcFinal)<=FIN_ES.getDec())){
              else if((p.getHex().compareTo(INICIO_ES.getHex())>=0) && (p.getHex().compareTo(FIN_ES.getHex())<=0)){ 
            	
            	  EntradaSalida.getEntradaSalida().setByte(direccion, desplazamiento, r[0].getPalabra());            	  
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
           int direccion=(int)r[1].getPalabra().getDec();           
          int desp=desplazamiento/4;
          int des=desplazamiento%4;
         
          if(((direccion+(desp*4)+des)<(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=(Sp.getRegistro().getPalabra().getDec())))            
          {
               Pila.getPila().setByte(direccion+(desp*4),des,r[0].getPalabra());
               boolean_pila=true;
          }
          else															//tiene que ser < estricto pq si no funciona como reserva dinamica y no estatica
              if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<(Memoria.getMemoria().obtPosMem())+4))
              {
                  Memoria.getMemoria().setByte(direccion,desplazamiento, r[0].getPalabra());                      
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
        codigo=setOp(40)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(desplazamiento);       
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
          int direccion=(int)r[1].getPalabra().getDec();           
          int desp=desplazamiento/4;
          int des=desplazamiento%4;
          if(((direccion+(desp*4)+des)<(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=(Sp.getRegistro().getPalabra().getDec())))            
          {
               Pila.getPila().setByte(direccion+(desp*4),des,r[0].getPalabra());
               boolean_pila=true;
          }
          else															//tiene que ser < estricto pq si no funciona como reserva dinamica y no estatica
              if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<(Memoria.getMemoria().obtPosMem())+4))
              {
                  Memoria.getMemoria().setByte(direccion,desplazamiento, r[0].getPalabra());                      
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
