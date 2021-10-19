package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.datos.Palabra;
import ensamblador.datos.PalabraCompuesta;
import ensamblador.datos.Tipos;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import java.awt.Color;
public class Mtc1 extends TipoR implements PlanifDinamica {     
     Registro r[]; 
     public Mtc1(String param[]) 
    {
    	this.imagSegmentado[0]="sumaPF_IF.gif";
    	this.imagSegmentado[1]="sumaPF_ID.gif";

    	instruccionFP=true;
    	resultTomasulo=new Hashtable<String, Palabra>();
       	tipoFU=Tipos.FP_ADD_FU;
       	tipoER=Tipos.FP_ADD_ER;
       	setOperacion("mtc1");
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("mtc1 ");
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
       String imagenes="mono.gif";
       super.inicIma(imagenes);
    }
    
    public static int numParam()
    {
        return 2;
    }
    
    public int numEtapas() 
    {
        return 3;
    }
    
    
    public int numero()
    {
        return 54;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        r[0].setPalabra(r[1].getPalabra()); 
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        return -1;
    }
    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("multiciclo");
        r[0].setPalabra(r[1].getPalabra());  
        boolean_dato=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
     
    public int etapa2()
    {    
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("multiciclo");
        return -1;
    }
    
    public int etapa1()
    {
        imagen_etapa=new StringBuffer();
        boolean_pc=true;
        Pc.getRegistro().incrementar(4);
        imagen_etapa.append(direccion_multi).append("multiciclo");
        return -1;
    }
    
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(17)+setR(4)+setR(r[1].numReg())+setR(r[0].numReg())+"00000000000";       
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
    	r[0].setPalabra(r[1].getPalabra());
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
      
    public Color colorSegm() {
    	return Color.ORANGE;
    }
    
    public int estilo()
    {
        return 10;
    }

    public int ejecutarTomasulo(Palabra rs, Palabra rt, String estacion){
    	
    	
        resultTomasulo.put(estacion, rs); 
    	if(r[0].getQi().equals(estacion)){
    	r[0].setPalabra(rs);    	
    	}
        return -1;
    }
    public int ejecutarMarcador(){
    	r[0].setPalabra(r[1].getPalabra());
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
