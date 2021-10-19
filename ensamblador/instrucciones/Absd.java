package ensamblador.instrucciones;

import java.awt.Color;
import java.util.Hashtable;

import ensamblador.datos.Palabra;
import ensamblador.datos.PalabraCompuesta;
import ensamblador.datos.Tipos;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;

public class Absd extends TipoR  implements PlanifDinamica{
     Registro r[]; 
     Palabra p;
    public Absd(String param[]) {
	this.imagSegmentado[0]="sumaPF_IF.gif";
    	this.imagSegmentado[1]="sumaPF_ID.gif";
    	
    	resultTomasulo=new Hashtable<String, Palabra>();
    	instruccionFP=true;
    	instruccionDouble=true;
    	setOperacion(lenguaje.getString("abs"));
    	tipoFU=Tipos.FP_ADD_FU;
    	tipoER=Tipos.FP_ADD_ER;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("abs.d ");
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
        return 37;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        p=r[1].setBit(0);
        r[0].setPalabra(p);
        relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());
        boolean_pc=true;
        boolean_datos=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_neg");
        p=r[1].setBit(0);
        r[0].setPalabra(p);
        relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());                       
        boolean_datos=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_neg");
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
        codigo=setOp(17)+setR(1)+setR(0)+setR(r[1].numReg())+setR(r[0].numReg())+setFunc(5);       
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
        p=r[1].setBit(0);
        r[0].setPalabra(p);
        relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {
    	r[0].liberar();
        boolean_datos=true;
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
   
    	
    	 p=rs.setBit(0);
    	 Palabra pSig=relacionaRegF(r[1].getSiguiente()).getPalabra();
    	 PalabraCompuesta resComp=new PalabraCompuesta(p.getDec());
         resComp.agregarPalabras(p,pSig);
         resultTomasulo.put(estacion, resComp); 
    	 if(this.regModificable().getQi().equals(estacion)){
         r[0].setPalabra(p);
         relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());
         
    	}
    	
         return -1;
    }
    public int ejecutarMarcador(){
    	 p=r[1].setBit(0);
         r[0].setPalabra(p);
         relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());
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
