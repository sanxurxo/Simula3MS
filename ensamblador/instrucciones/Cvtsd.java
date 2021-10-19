package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.registros.*;
import ensamblador.datos.*;
import java.awt.Color;
public class Cvtsd extends TipoR implements PlanifDinamica{
     Registro r[];
     String s;
     double d;
     Palabra p=new Palabra("p");
     public Cvtsd(String param[]) 
    {
this.imagSegmentado[0]="sumaPF_IF.gif";
    	this.imagSegmentado[1]="sumaPF_ID.gif";
    	setOperacion("cvt.s.d");
    	 resultTomasulo=new Hashtable<String, Palabra>();
    	 instruccionDouble=true;
    	 instruccionFP=true;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("cvt.s.d ");
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
    
    public static String esValida(String[] param, int numParam)
    {
        String error=null;  
        
        if((param.length==numParam)&&(numParam==2))
        {
                if(!esRegistroF(param[0]))
                    error=param[0]+"parametros no validos. No es Reg";
                if(!esRegistroFPar(param[1]))
                    error=param[1]+"parametros no validos. No es Reg";
        }
        else
             error="parametros no validos";
             
        return error;
    }
    
    
    public void inicia()
    {
       String imagenes="neg.gif";
       super.inicIma(imagenes);
    }
    
    public static int numParam()
    {
        return 2;
    }
 
    public int numero()
    {
        return 50;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        s=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d=p.float_a_dec(s);
        r[0].setPalabra(new Palabra(d));
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_neg");
        s=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d=p.float_a_dec(s);
        r[0].setPalabra(new Palabra(d));
        boolean_dato=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_neg");
        s=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d=p.float_a_dec(s);
        return -1;
    }
     
    public int etapa2()
    {    
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa2_mov");
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
        codigo=setOp(17)+setR(1)+setR(0)+setR(r[1].numReg())+setR(r[0].numReg())+setFunc(32);       
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
    	s=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        d=p.float_a_dec(s);
        r[0].setPalabra(new Palabra(d));
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
    
    	
        resultTomasulo.put(estacion, new Palabra(d)); 
    	if(r[0].getQi().equals(estacion)){
    	 s=rs.getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
         d=p.float_a_dec(s);         
    	}
         return -1;
    }
    public int ejecutarMarcador(){
    	 s=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
         d=p.float_a_dec(s);
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
