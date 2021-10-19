package ensamblador.instrucciones;

import java.util.Hashtable;
import java.awt.Color;

import ensamblador.registros.*;
import ensamblador.datos.*;

public class Cled extends TipoR implements PlanifDinamica{
    Registro r[]; 
    long d;
    String s1,s2;
    double d1,d2;
    Palabra p=new Palabra("p");
    public Cled(String param[]) 
    {
this.imagSegmentado[0]="sumaPF_IF.gif";
   	 	this.imagSegmentado[1]="sumaPF_ID.gif";
    	resultTomasulo=new Hashtable<String, Palabra>();
    	instruccionFP=true;
    	instruccionDouble=true;
    	setOperacion("c.le.d");
    	tipoFU=Tipos.FP_ADD_FU;
    	tipoER=Tipos.FP_ADD_ER;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("c.le.d ");
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
    
    public int numEtapas() 
    {
          return 3;
    }
    
    public int numero()
    {
        return 45;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        s1=r[0].getPalabra().getBin()+relacionaRegF(r[0].numReg()+1).getPalabra().getBin();
        d1=p.float_a_dec(s1);
        s2=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d2=p.float_a_dec(s2);
        if(d1<=d2)
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
        s1=r[0].getPalabra().getBin()+relacionaRegF(r[0].numReg()+1).getPalabra().getBin();
        d1=p.float_a_dec(s1);
        s2=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d2=p.float_a_dec(s2);
        if(d1<=d2)
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
        codigo=setOp(17)+setR(1)+setR(r[1].numReg())+setR(r[0].numReg())+setR(0)+setFunc(62); 
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
    	Status.getRegistro().ocupar();
    	s1=r[0].getPalabra().getBin()+relacionaRegF(r[0].numReg()+1).getPalabra().getBin();
        d1=p.float_a_dec(s1);
        s2=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d2=p.float_a_dec(s2);
        if(d1<=d2)
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
    	Status.getRegistro().liberar();
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
    	
    	Status.getRegistro().ocupar();
    	s1=rs.getBin()+relacionaRegF(r[0].numReg()+1).getPalabra().getBin();
        d1=p.float_a_dec(s1);
        s2=rt.getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d2=p.float_a_dec(s2);
        if(d1<=d2)
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
    	Status.getRegistro().ocupar();
    	s1=r[0].getPalabra().getBin()+relacionaRegF(r[0].numReg()+1).getPalabra().getBin();
        d1=p.float_a_dec(s1);
        s2=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d2=p.float_a_dec(s2);
        if(d1<=d2)
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
