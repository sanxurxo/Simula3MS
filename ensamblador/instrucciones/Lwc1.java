package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Tipos;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Sp;
public class Lwc1 extends Carga implements PlanifDinamica{
    
    int numReg;
    private int desplazamiento;
    String variable;
    Registro r[];
      
    public Lwc1(String param[])
    {   
    	resultTomasulo=new Hashtable<String, Palabra>();
    	this.desplazamiento=desp;
    	instruccionFP=true;
    	tipoFU=Tipos.INTEGER_FU;
    	tipoER=Tipos.LOAD_ER;
    	setOperacion(lenguaje.getString("carga"));
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
            desplazamiento=super.getDesplazamiento();
        
        }

        string.append("lwc1 ").append(param[0]).append(", ").append(param[1]);
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
                    if(i==0)
                        r[i]=relacionaRegF(param[i]);
                    else
                        r[i]=relacionaReg(param[i]);                       
                    break;
                }
                if(i==0)
                    r[i]=relacionaRegF(posi);
                else
                    r[i]=relacionaReg(posi);
            }    
        }      
    }
    
    public void inicia()
    {
       String imagenes="lwc1.gif";
       super.inicIma(imagenes);
    }
    public int getDesplazamiento()
    {
        return desplazamiento;
    }
    
    
    public int numero()
    {
        return 56;
    }
    
     public int ejecutar()
    {     
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        boolean_dato=true;
        if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<INICIO_PILA.getDec()) && (direccion>Sp.getRegistro().getPalabra().getDec()))            
                r[0].setPalabra(Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento));
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))                
                    r[0].setPalabra(Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento)); 
                else
                {
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
    
     public int etapa1()
     {
        imagen_etapa=new StringBuffer();
        boolean_pc=true;
        Pc.getRegistro().incrementar(4);
        imagen_etapa.append(direccion_multi).append("etapa1F");
        return -1;
     }
     
    public int etapa2()
    {    
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa2_accmem");
        return -1;
    }
    
     public int etapa3()
     {        
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_accmem");
        return -1;
     }
    
      public int etapa4()
     {
        /*En esta etapa se comprueban las direcc de mem*/
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_lwc1");
        if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<INICIO_PILA.getDec()) && (direccion>Sp.getRegistro().getPalabra().getDec()))            
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
        imagen_etapa.append(direccion_multi).append("etapa5_lwc1");
        if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<INICIO_PILA.getDec()) && (direccion>Sp.getRegistro().getPalabra().getDec()))
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
                else{
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
         }
        this.cambiosVisibles();
        return -1;
     }
      
     public String getCodificacion()
     {
        String codigo;
        codigo=setOp(49)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(desplazamiento);       
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
         return null;
     }
     
    public boolean hayRiesgo()
    {
        return false;
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
            if((direccion<INICIO_PILA.getDec()) && (direccion>Sp.getRegistro().getPalabra().getDec()))
            {
                r[0].setPalabra(Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento));
            }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem()))){
                    r[0].setPalabra(Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento)); 
                }
                else{

                    return 3;
                }

            return -1;                        
         }
         else
         {
             r[0].setPalabra(Memoria.getMemoria().getVariable(variable));

         }

        return -1;  
    }
    
    public int ejecutarWB()
    {   
    	r[0].liberar();
    	boolean_dato=true;
    	this.cambiosVisibles();
        return -1;                                      
    }  
    
    public int ejecutarTomasulo(Palabra rs, Palabra rt, String estacion){
    	
    
    	Palabra p=null;
    	if(numReg==2)
        {
            int direccion=(int)rs.getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<INICIO_PILA.getDec()) && (direccion>Sp.getRegistro().getPalabra().getDec()))
            {
            	p=Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento);
            	if(r[0].getQi().equals(estacion)){
                r[0].setPalabra(p);
            	}
     
            }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem()))){
                	p=Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento);
                	if(r[0].getQi().equals(estacion)){
                    r[0].setPalabra(p);
                	}
     
                }
                else{
                    return 3;
                }
     
       
            resultTomasulo.put(estacion,p);
            return -1;                        
         }
         else
         {
        	 p=Memoria.getMemoria().getVariable(variable);
        	 if(r[0].getQi().equals(estacion)){
             r[0].setPalabra(p);
        	 }
     
         }
       
    	resultTomasulo.put(estacion,p);
        return -1;
    	
    }
    public int ejecutarMarcador(){
    	if(numReg==2)
        {
            int direccion=(int)r[1].getPalabra().getDec()+desplazamiento;
            if((direccion%4)!=0)
                return 3;
            if((direccion<INICIO_PILA.getDec()) && (direccion>Sp.getRegistro().getPalabra().getDec()))
            {
                r[0].setPalabra(Pila.getPila().getPalabra(direccion-desplazamiento,desplazamiento));
     
            }
            else
                if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem()))){
                    r[0].setPalabra(Memoria.getMemoria().getVariable(direccion-desplazamiento,desplazamiento)); 
     
                }
                else{
     
     
                    return 3;
                }
     
     
            return -1;                        
         }
         else
         {
             r[0].setPalabra(Memoria.getMemoria().getVariable(variable));

         }

    	
        return -1;
    }
    
    public Palabra WBTomasulo(String estacion){
	    ejecutarWB();	

		 Palabra p=null;
	    	if((p=resultTomasulo.get(estacion))!=null){
	
	    		resultTomasulo.remove(estacion);		    		
	    		return p;
	    	}
	    	return null;
	    }
}

