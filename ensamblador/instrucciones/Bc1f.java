package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;

import java.util.*;
public class Bc1f extends SaltoCondicional implements PlanifDinamica{

    Registro r[]; 
    String etiqueta;
    private int posIns;
    public Bc1f(String param[], int p){
    	resultTomasulo=new Hashtable<String, Palabra>();
    	esSalto=true;
    	instruccionFP=true;
    	setOperacion("bc1f");
    	tipoFU=Tipos.INTEGER_FU;
    	tipoER=Tipos.FP_ADD_ER;
        posIns=(int)INICIO_PC.getDec()+p*4;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("bc1f ");
        etiqueta=param[0];
        string.append(etiqueta);
    }
    
    public static String esValida(String[] param, Etiquetas etiq)
    {
       boolean esValido=false;
       String error=null;   /*0 indica que es valido*/
       Vector etiquetas=etiq.getNombres();
        if(param.length==numParam())
        {
            if(!esEtiqueta(param[0], etiq))
                error="parametros no validos";
        }
        else
            error="numero de parametros incorrecto"+ param.length; 
        return error;   
   }    
    
    public void inicia()
   {
       String imagenes="bc1t.gif";
       super.inicIma(imagenes);
   }
    
    public static int numParam()
    {
        return 1;
    }
    
    public int numero()
    {
        return 35;
    }
    
    public int ejecutar()
    {
        if(Status.getRegistro().getPalabra().getDec()==0)
        {
            int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
            Pc.getRegistro().modificar(i);
        }
        else
            Pc.getRegistro().incrementar(4);
        boolean_pc=true;        
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
        imagen_etapa.append(direccion_multi).append("etapa2_saltocond");
        return -1;
    }
    
    public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        if(Status.getRegistro().getPalabra().getDec()==0)
        {
            int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
            Pc.getRegistro().modificar(i);
            boolean_pc=true;
            imagen_etapa.append(direccion_multi).append("bc1f");        
        }
        else
            imagen_etapa.append(direccion_multi).append("bc1f_no");        
        return -1;   
        
    }
    
     public String getCodificacion()
    {        
        int posSalto=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
        //int offset=((posSalto-posIns)/4);       
        String codigo;
        codigo=setOp(17)+setR(8)+setR(0)+setIn(posSalto);       
        return bin_a_hex(codigo);
    }
     
    public Registro regModificable()
    {
        return null;
    }
    
    public Registro getRegRS()
    {	
        return null;
    }
    
    public Registro getRegRT()
    {	
        return null;
    }
    
    public boolean hayRiesgo()
    {
        return !Status.getRegistro().estaLibre();
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
    	saltoTomado=false;
    	if(hayRiesgo()) {
    		return -1;
    	}
    	if(Status.getRegistro().getPalabra().getDec()==0){
            int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
            Pc.getRegistro().modificar(i);
            saltoTomado=true;
        }
    	else
        {
            Pc.getRegistro().incrementar(4);
        }
    	boolean_pc=true;
    	this.cambiosVisibles();
        return -1;
    }
    
    public int ejecutarEX()
    {
        return -1;
    }
    
    public int ejecutarMEM()
    {                 
        return -1;
    }
    
    public int ejecutarWB()
    {            
        return -1;                                      
    }

    public int ejecutarTomasulo(Palabra rs, Palabra rt, String estacion){
    	
    	 if(Status.getRegistro().getPalabra().getDec()==0){
             int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
             Pc.getRegistro().modificar(i);                                
         }
    	
    	
         return -1;
    	
    }
    public int ejecutarMarcador(){
    	 if(Status.getRegistro().getPalabra().getDec()==0){
             int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
             Pc.getRegistro().modificar(i);                                
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
