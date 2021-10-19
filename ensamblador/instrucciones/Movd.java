package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.datos.Palabra;
import ensamblador.datos.PalabraCompuesta;
import ensamblador.datos.Tipos;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import java.awt.Color;
public class Movd extends TipoR implements PlanifDinamica{
     Registro r[]; 
     public Movd(String param[]) 
    {
this.imagSegmentado[0]="sumaPF_IF.gif";
    	this.imagSegmentado[1]="sumaPF_ID.gif";
    	 resultTomasulo=new Hashtable<String, Palabra>();
     	instruccionFP=true;
     	instruccionDouble=true;
     	tipoFU=Tipos.FP_ADD_FU;
     	tipoER=Tipos.FP_ADD_ER;
     	setOperacion("mov.d");
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("mov.d ");
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
       String imagenes="mov.gif";
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
        return 41;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        r[0].setPalabra(r[1].getPalabra());
        relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());
        boolean_pc=true;
        boolean_datos=true;
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
        imagen_etapa.append(direccion_multi).append("etapa3_mov");
        r[0].setPalabra(r[1].getPalabra());
        relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());
        boolean_datos=true;
        boolean_pc=true;
        this.cambiosVisibles();
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
        codigo=setOp(17)+setR(1)+setR(0)+setR(r[1].numReg())+setR(r[0].numReg())+setFunc(6);       
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
    	
    	Palabra palSig=relacionaRegF(r[1].getSiguiente()).getPalabra();
    	PalabraCompuesta resComp=new PalabraCompuesta(rs.getDec());
        resComp.agregarPalabras(rt,palSig);
        resultTomasulo.put(estacion, resComp); 
    	
    	if(r[0].getQi().equals(estacion)){
    	r[0].setPalabra(rt);
        relacionaRegF(r[0].getSiguiente()).setPalabra(relacionaRegF(r[1].getSiguiente()).getPalabra());        
    	}
        return -1;
    }
    public int ejecutarMarcador(){
    	r[0].setPalabra(r[1].getPalabra());
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
