package ensamblador.instrucciones;

import java.awt.Color;

import ensamblador.datos.Tipos;
import ensamblador.registros.BadVaddr;
import ensamblador.registros.Cause;
import ensamblador.registros.EPC;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Status;
public class Mfc0 extends TipoR {     
     Registro r[]; 
     public Mfc0(String param[]) {
	
     	setOperacion("mfc0");
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("mfc0 ");
                       
            string.append(param[0]);

                string.append(", ");
            for(int j=0;j<registros.length;j++){
                try{
                    posi=java.lang.Integer.parseInt(param[0].substring(1,param[0].length()));
                }
                catch(NumberFormatException e)
                {
                    r[0]=relacionaReg(param[0]);
                    break;
                }

                    r[0]=relacionaReg(posi);
            }
        
            string.append(param[1]);
            try{
                posi=java.lang.Integer.parseInt(param[1].substring(1,param[1].length()));
            }
            catch(NumberFormatException e){System.out.println("ERROR");}
            switch(posi){
            case Tipos.R_BADVADDR:
            	r[1]=BadVaddr.getRegistro();
            	break;
            case Tipos.R_STATUS:
            	r[1]=Status.getRegistro();
            	break;
            case Tipos.R_CAUSE:
            	r[1]=Cause.getRegistro();
            	break;
            case Tipos.R_EPC:
            	r[1]=EPC.getRegistro();
            	break;
            }
    }     
     
     
     public static String esValida(String[] param, int numParam){
         String error=null; 
         int posi=0;
         
         if(param.length==numParam){
        	 

                 if(esRegistro(param[0]))
                 {
                         if(param[0].equals("$zero")  || param[0].equals("$0")){
                                 error+=param[0]+"parametros no validos. Reg esp"; 
                         }
                 }
                 else
                     error=param[0]+"parametros no validos. No es Reg";
                 
                 try{
                     posi=java.lang.Integer.parseInt(param[1].substring(1,param[1].length()));
                 }
                 catch(NumberFormatException e){System.out.println("ERROR");}
                 switch(posi){
                 case Tipos.R_BADVADDR:                 
                 case Tipos.R_STATUS:
                 case Tipos.R_CAUSE:                 	
                 case Tipos.R_EPC:                 	
                 	break;
                 default: error=param[1]+" parametros no validos. No es un registro del coprocesador 0";
                 break;
                 }
                                                     
         }
         else
              error="parametros no validos";
              
         return error;
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
    
    
    public int numero(){
        return 59;
    }
    
    public int ejecutar(){
        Pc.getRegistro().incrementar(4);
        r[0].setPalabra(r[1].getPalabra());
        
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4(){
        return -1;
    }
    
     public int etapa3(){
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
    
    public String getCodificacion(){
        String codigo;
        codigo=setOp(16)+setR(0)+setR(r[1].numReg())+setR(r[0].numReg())+"00000000000";       
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

   
}
