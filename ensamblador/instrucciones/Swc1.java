
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Swc1 extends Almacenamiento implements PlanifDinamica {
    
    Registro r[];
    private int desplazamiento;
    public Swc1(String param[])
    {
    	this.desplazamiento=desp;
    	instruccionFP=true;
    	tipoFU=Tipos.INTEGER_FU;
    	tipoER=Tipos.STORE_ER;
    	setOperacion(lenguaje.getString("almacenamiento"));
        int posi=-1;
        inicia();
        int numReg=2;
        r=new Registro[numReg];        
        string.append("swc1 ").append(param[0]).append(", ").append(param[1]);
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
        desplazamiento=getDesplazamiento();        
    }       
    
    public void inicia()
    {
       String imagenes="swc1.gif";
       super.inicIma(imagenes);
    }
    
    public int getDesplazamiento()
    {
        return desplazamiento;
    }
    
    public int numero()
    {
        return 57;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;        
          
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
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_swc1");
          //Pc.getRegistro().incrementar(4);
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
        codigo=setOp(57)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(desplazamiento);       
        return bin_a_hex(codigo);
    }
     
    public Registro regModificable()
    {
        return null;
    }
    
    public Registro getRegRS()
    {	
        return r[1];
    }
    
    public Registro getRegRT()
    {	
    	return r[0];
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
    
    public int ejecutarTomasulo(Palabra rs, Palabra rt, String estacion){
    	
    	
    	//int direccion=(int)rt.getDec()+desplazamiento;
    	int direccion=(int)rs.getDec()+desplazamiento;
    	
        if((direccion%4)!=0)
            return 3;
        if((direccion<=INICIO_PILA.getDec()) && (direccion>=Sp.getRegistro().getPalabra().getDec()))
        {        	
            //Pila.getPila().setPalabra(direccion-desplazamiento,desplazamiento,r[0].getPalabra());
        	Pila.getPila().setPalabra(direccion-desplazamiento,desplazamiento,rs);
            boolean_pila=true;
        }
        else
            if((direccion>=INICIO_MEMORIA.getDec()) && (direccion<=(Memoria.getMemoria().obtPosMem())))
            {
                  
            	   Memoria.getMemoria().setPalabra(direccion-desplazamiento,desplazamiento, rs);
                boolean_mem=true;
            }
            else
            {

                return 3;
            }

        //resultTomasulo.put(estacion, rt);
        return -1;
   }
   public int ejecutarMarcador(){
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

                return 3;
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
