package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.registros.*;
import ensamblador.datos.*;
import java.awt.Color;
public class Negs extends TipoR implements PlanifDinamica{
     Registro r[]; 
     Palabra p;
     public Negs(String param[]) 
    {
this.imagSegmentado[0]="sumaPF_IF.gif";
    	this.imagSegmentado[1]="sumaPF_ID.gif";
    	 instruccionFP=true;
    	 resultTomasulo=new Hashtable<String, Palabra>();
         tipoFU=Tipos.FP_ADD_FU;
         tipoER=Tipos.FP_ADD_ER;
         resultTomasulo=new Hashtable<String, Palabra>();
         setOperacion("neg.s");
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("neg.s ");
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
       String imagenes="neg.gif";
       super.inicIma(imagenes);
    }
    
    public static int numParam()
    {
        return 2;
    }
    
    public int numero()
    {
        return 38;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        if(r[1].getBit()==0)
            p=r[1].setBit(1);
        else
            p=r[1].setBit(0);
        r[0].setPalabra(p);
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_neg");
        if(r[1].getBit()==0)
            p=r[1].setBit(1);
        else
            p=r[1].setBit(0);
        r[0].setPalabra(p);   
        boolean_dato=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_neg");
        if(r[1].getBit()==0)
            p=r[1].setBit(1);
        else
            p=r[1].setBit(0);
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
        codigo=setOp(17)+setR(0)+setR(0)+setR(r[1].numReg())+setR(r[0].numReg())+setFunc(7);       
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
    	//r[1].ocupar();
    	if(r[1].getBit()==0)
            p=r[1].setBit(1);
        else
            p=r[1].setBit(0);
        r[0].setPalabra(p);
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
    	
    	if(rs.getBit()==0)
            p=rs.setBit(1);
        else
            p=rs.setBit(0);
    	resultTomasulo.put(estacion, p);
    	if(r[0].getQi().equals(estacion)){
        r[0].setPalabra(p);
        
    	}
        return -1;
    }
    
    public int ejecutarMarcador(){
    	if(r[1].getBit()==0)
            p=r[1].setBit(1);
        else
            p=r[1].setBit(0);
        r[0].setPalabra(p);
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
