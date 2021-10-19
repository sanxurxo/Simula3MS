
package ensamblador.instrucciones;

import ensamblador.datos.EntradaSalida;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Sp;
public class Lw extends Carga {
    int numReg;
    private int desplazamiento;
    String variable;
    Registro r[];
      
    public Lw(String param[])
    {    
        int posi=-1;
        inicia();
        if(esVariable(param[1]))
        {           
            variable=param[1];
            numReg=1;
            r=new Registro[numReg];            
        }
        else
        {
            numReg=2;
            r=new Registro[numReg];
            desplazamiento=getDesplazamiento();    
        }

        string.append("lw ").append(param[0]).append(", ").append(param[1]);
        int ind1;
        if((ind1=param[1].indexOf("$"))!=0)
        {
            int ind2=param[1].indexOf(")");
            param[1]=param[1].substring(ind1,ind2);
        }   
        for(int i=0; i<numReg;i++)
        {         
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
    
    public static String esValida(String[] param)
    {
    	 String error=null;
    	error=Carga.esValida(param);
    	if(error==null){
    	    if (!(esDireccion(param[1]))){
                error="parametros no validos, direccion"+ esDireccion(param[1])+ " variables"+ esVariable(param[1]);
    	    }
    	} 
         return error;
    }
    
    
    
    public int numero()
    {
        return 13;
    }
    
     public int ejecutar()
    {     
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        boolean_dato=true;
        
        
        if(numReg==2)
        {
        	
        	long direccion=r[1].getPalabra().getDireccion()+desplazamiento;
            
            if((direccion%4)!=0)
                return 3;
            if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec())){
            
                r[0].setPalabra(Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento));
            }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))                
                    r[0].setPalabra(Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento)); 
                else if((direccion>=INICIO_ES.getDec()) &&(direccion<=FIN_ES.getDec())){
            
					r[0].setPalabra(new Palabra(EntradaSalida.getEntradaSalida().getValor(direccion).getHex(), true));
				}else{
                    this.cambiosVisibles();
                    return 3;
                }
            this.cambiosVisibles();            
            return -1;                        
        }
        else
             r[0].setPalabra(Memoria.getMemoria().getVariable(variable));
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
      
    }
         
      public int etapa4()
     {
        /*En esta etapa se comprueban las direcc de mem*/
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_load");
        if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))            
                return -1;
                
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))                                
                    return -1;
                
                else
                    return 3;                                    
         }
         else         
             return -1;             
     
     }
      
      
       public int etapa5()
     {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa5");
        if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))
            {
                r[0].setPalabra(Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento));
                boolean_dato=true;
            }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))                
                {
                    r[0].setPalabra(Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento)); 
                    boolean_dato=true;
                }
                else
                {
                    this.cambiosVisibles();
                    return 3;
                }
            this.cambiosVisibles();
            return -1;                        
         }
         else
         {
             r[0].setPalabra(Memoria.getMemoria().getVariable(variable));
             boolean_dato=true;
             this.cambiosVisibles();
         }
         return -1;
      
     }
     
     
     
      public String getCodificacion()
    {
        String codigo;
        codigo=setOp(35)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(desplazamiento);       
        return bin_a_hex(codigo);
    }

     public Registro regModificable()
    {
        return r[0];
    }
     
    public Registro getRegRS()
    {
        if (numReg==2)
            return r[1];
        return null;
    }
    
    public Registro getRegRT()
    {	
        return null;
    }
     
    public boolean hayRiesgo()
    {
        if(r[1].estaLibre())
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
        return -1;
    }
    
    public int ejecutarMEM()
    {
        if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))
            {
                r[0].setPalabra(Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento));
                return -1;
            }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))                
                {
                    r[0].setPalabra(Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento)); 
                    return -1;                
                }
                else
                    return 3;                
         }
         else
         {
             r[0].setPalabra(Memoria.getMemoria().getVariable(variable));
             return -1;  
         }
    }
    
    public int ejecutarWB()
    {       
         r[0].liberar();
         boolean_dato=true;
         this.cambiosVisibles();
         return -1;                                      
    }  
    
    
}

