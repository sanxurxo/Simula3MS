package ensamblador.instrucciones;

import java.util.Hashtable;
import java.awt.Color;
import ensamblador.registros.*;
import ensamblador.datos.*;

public class Adds extends TipoR implements PlanifDinamica{
    Registro r[]; 
    public Adds(String param[]) 
    {    
this.imagSegmentado[0]="sumaPF_IF.gif";
   	 	this.imagSegmentado[1]="sumaPF_ID.gif";	
    	resultTomasulo=new Hashtable<String, Palabra>();
    	instruccionFP=true;
    	setOperacion(lenguaje.getString("suma"));
    	tipoFU=Tipos.FP_ADD_FU;
    	tipoER=Tipos.FP_ADD_ER;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("add.s ");
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
       String imagenes="R_flot.gif";
       super.inicIma(imagenes);
    }
    
    public int numero()
    {
        return 26;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        Palabra p1=new Palabra(0);
        Palabra p2=new Palabra(0);
        
        double resultado=p1.float_a_dec(r[1].getPalabra().getFloat())+p2.float_a_dec(r[2].getPalabra().getFloat());
        //double resultado=r[1].getPalabra().getFrac()+r[2].getPalabra().getFrac();
        r[0].setPalabra(new Palabra(resultado)); 
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("RF_etapa4");
        double resultado=r[1].getPalabra().getFrac()+r[2].getPalabra().getFrac();
        r[0].setPalabra(new Palabra(resultado));        
        boolean_dato=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("RF_etapa3");
//        double resultado=r[1].getPalabra().getFrac()+r[2].getPalabra().getFrac();
        Palabra p1=new Palabra(0);
        Palabra p2=new Palabra(0);
        
        double resultado=p1.float_a_dec(r[1].getPalabra().getFloat())+p2.float_a_dec(r[2].getPalabra().getFloat());

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
        codigo=setOp(17)+setR(0)+setR(r[2].numReg())+setR(r[1].numReg())+setR(r[0].numReg())+setFunc(0); 
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
//        double resultado=r[1].getPalabra().getFrac()+r[2].getPalabra().getFrac();
        Palabra p1=new Palabra(0);
        Palabra p2=new Palabra(0);
        
        double resultado=p1.float_a_dec(r[1].getPalabra().getFloat())+p2.float_a_dec(r[2].getPalabra().getFloat());

        r[0].setPalabra(new Palabra(resultado));
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
    	
//        double resultado=rs.getFrac()+rt.getFrac();
  
        Palabra p1=new Palabra(0);
        Palabra p2=new Palabra(0);
        
        double resultado=p1.float_a_dec(rs.getFloat())+p2.float_a_dec(rt.getFloat());

        resultTomasulo.put(estacion, new Palabra(resultado)); 
        if(r[0].getQi().equals(estacion)){
        r[0].setPalabra(new Palabra(resultado));
        
    	}
        return -1;
    }
    public int ejecutarMarcador(){
//        double resultado=r[1].getPalabra().getFrac()+r[2].getPalabra().getFrac();
        Palabra p1=new Palabra(0);
        Palabra p2=new Palabra(0);
        
        double resultado=p1.float_a_dec(r[1].getPalabra().getFloat())+p2.float_a_dec(r[2].getPalabra().getFloat());

        r[0].setPalabra(new Palabra(resultado));
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
