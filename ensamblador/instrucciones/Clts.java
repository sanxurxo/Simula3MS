package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.registros.*;
import ensamblador.datos.*;
import java.awt.Color;
public class Clts extends TipoR implements PlanifDinamica{
    Registro r[];
    long d;
    public Clts(String param[]) 
    {
	this.imagSegmentado[0]="sumaPF_IF.gif";
   	 	this.imagSegmentado[1]="sumaPF_ID.gif";
    	resultTomasulo=new Hashtable<String, Palabra>();
    	instruccionFP=true;
    	setOperacion("c.lt.s");
    	tipoFU=Tipos.FP_ADD_FU;
    	tipoER=Tipos.FP_ADD_ER;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("c.lt.s ");
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
                    r[i]=relacionaRegF(param[i]);                       
                    break;
                }
                r[i]=relacionaRegF(posi);
            }
        }                
    }     
    
    
    public void inicia()
    {
       String imagenes="ceq.gif";
       super.inicIma(imagenes);
    }
    
    public static int numParam()
    {
        return 2;
    }
    
    public int numero()
    {
        return 46;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        if(r[0].getPalabra().getFrac()<r[1].getPalabra().getFrac())
            d=1;
        else
            d=0;
        Status.getRegistro().setPalabra(new Palabra(d));
        boolean_pc=true;
        boolean_status=true;
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
        imagen_etapa.append(direccion_multi).append("etapa3_c");
        if(r[0].getPalabra().getFrac()<r[1].getPalabra().getFrac())
            d=1;
        else
            d=0;
         Status.getRegistro().setPalabra(new Palabra(d));
        boolean_status=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
     
    public int etapa2()
    {    
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa2F");
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
    
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(17)+setR(0)+setR(r[1].numReg())+setR(r[0].numReg())+setR(0)+setFunc(60);
        return bin_a_hex(codigo);
    }
     
    public Registro regModificable()
    {
        return Status.getRegistro();
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
    	if(r[0].getPalabra().getFrac()<r[1].getPalabra().getFrac())
            d=1;
        else
            d=0;
         Status.getRegistro().setPalabra(new Palabra(d));
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {
    	r[0].liberar();
    	boolean_status=true;
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
    	
    	if(rs.getFrac()<rt.getFrac())
            d=1;
        else
            d=0;
    	
        resultTomasulo.put(estacion, new Palabra(d)); 
    	if(Status.getRegistro().getQi().equals(estacion)){
         Status.getRegistro().setPalabra(new Palabra(d));
        
    	}
        return -1;
    }
    public int ejecutarMarcador(){
    	if(r[0].getPalabra().getFrac()<r[1].getPalabra().getFrac())
            d=1;
        else
            d=0;
         Status.getRegistro().setPalabra(new Palabra(d));
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
