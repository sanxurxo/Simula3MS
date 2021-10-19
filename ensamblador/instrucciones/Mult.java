
package ensamblador.instrucciones;
import ensamblador.datos.Palabra;
import ensamblador.registros.Hi;
import ensamblador.registros.Lo;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
public class Mult extends TipoR {
    
    Registro r[];
    long hi;
    long lo;
    public Mult(String param[]) 
    {    
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("mult ");
        for(int i=0; i<numParam();i++)
        {           
            string.append(param[i]);
            if(i!=numParam()-1)
                string.append(",");
       for(int j=0;j<registros.length;j++)
            {
                try
                {
                        posi=java.lang.Integer.parseInt(param[i].substring(1,param[i].length()));
                }
                catch(NumberFormatException e)
                {
                        r[i]=relacionaReg(param[i]);                       
                        break;
                }
                r[i]=relacionaReg(posi);           
            }      
        }            
    }
    
    public static int numParam()
    {
        return 2;
    }
    
    public int numero()
    {
        return 20;
    }
    
    public int ejecutar() 
    {
	long palDec0, palDec1,p,max=Integer.MAX_VALUE;
        Pc.getRegistro().incrementar(4);
        if((palDec0=r[0].getPalabra().getDec())>Integer.MAX_VALUE) palDec0=palDec0+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        if((palDec1=r[1].getPalabra().getDec())>Integer.MAX_VALUE) palDec1=palDec1+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        long result=palDec0*palDec1;
        
        String palHex=new String();
        
        String palBin=new String(); 

        palHex=Long.toHexString(result);
        while(palHex.length()<16){
        	if(result<0){
        		palHex="f"+palHex;
        	}else{
        		palHex="0"+palHex;
        	}
        }
        
        
       

        Hi.getRegistro().setPalabra(new Palabra(palHex.substring(0, 8), false));
        Lo.getRegistro().setPalabra(new Palabra(palHex.substring(8, 16), false));
   
        boolean_pc=true;
        boolean_hi_lo=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
	
		long palDec0, palDec1,p,max=Integer.MAX_VALUE;
		if((palDec0=r[0].getPalabra().getDec())>Integer.MAX_VALUE) palDec0=palDec0+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        if((palDec1=r[1].getPalabra().getDec())>Integer.MAX_VALUE) palDec1=palDec1+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        long result=palDec0*palDec1;
        
        String palHex=new String();
        
        String palBin=new String(); 

        palHex=Long.toHexString(result);
        while(palHex.length()<16){
        	if(result<0){
        		palHex="f"+palHex;
        	}else{
        		palHex="0"+palHex;
        	}
        }
        
        
       

        Hi.getRegistro().setPalabra(new Palabra(palHex.substring(0, 8), false));
        Lo.getRegistro().setPalabra(new Palabra(palHex.substring(8, 16), false));

	
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("R_etapa4");
		
		
        
        boolean_hi_lo=true;
        return -1;
        
    }

     public String getCodificacion()
    {
        String codigo;
        codigo=setOp(0)+setR(r[0].numReg())+setR(r[1].numReg())+setR(0)+"00000"+setFunc(24);       
        return bin_a_hex(codigo);
    }

    public Registro regModificable()
    {
        return null;
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
        if (r[0].estaLibre() && r[1].estaLibre())
            return false;
        return true;
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
        Hi.getRegistro().ocupar();
        Lo.getRegistro().ocupar();
        
	long palDec0, palDec1,p,max=Integer.MAX_VALUE;

        if((palDec0=r[0].getPalabra().getDec())>Integer.MAX_VALUE) palDec0=palDec0+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        if((palDec1=r[1].getPalabra().getDec())>Integer.MAX_VALUE) palDec1=palDec1+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        long result=palDec0*palDec1;
        
        String palHex=new String();
        
        String palBin=new String(); 

        palHex=Long.toHexString(result);
        while(palHex.length()<16){
        	if(result<0){
        		palHex="f"+palHex;
        	}else{
        		palHex="0"+palHex;
        	}
        }
        
        
       

        Hi.getRegistro().setPalabra(new Palabra(palHex.substring(0, 8), false));
        Lo.getRegistro().setPalabra(new Palabra(palHex.substring(8, 16), false));
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {     
        Hi.getRegistro().liberar();
        Lo.getRegistro().liberar();
        this.boolean_hi_lo=true;
        this.cambiosVisibles();
        return -1;
    }

    public int ejecutarTomasulo(Palabra rs, Palabra rt){
    	long palDec0, palDec1,p,max=Integer.MAX_VALUE;

        if((palDec0=rs.getDec())>Integer.MAX_VALUE) palDec0=palDec0+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        if((palDec1=rt.getDec())>Integer.MAX_VALUE) palDec1=palDec1+Integer.MIN_VALUE+Integer.MIN_VALUE; 
long result=palDec0*palDec1;
        
        String palHex=new String();
        
        String palBin=new String(); 

        palHex=Long.toHexString(result);
        while(palHex.length()<16){
        	if(result<0){
        		palHex="f"+palHex;
        	}else{
        		palHex="0"+palHex;
        	}
        }
        
        
       

        Hi.getRegistro().setPalabra(new Palabra(palHex.substring(0, 8), false));
        Lo.getRegistro().setPalabra(new Palabra(palHex.substring(8, 16), false));
        return -1;
    }
    public int ejecutarMarcador(){
    	long palDec0, palDec1,p,max=Integer.MAX_VALUE;

        if((palDec0=r[0].getPalabra().getDec())>Integer.MAX_VALUE) palDec0=palDec0+Integer.MIN_VALUE+Integer.MIN_VALUE; 
        if((palDec1=r[1].getPalabra().getDec())>Integer.MAX_VALUE) palDec1=palDec1+Integer.MIN_VALUE+Integer.MIN_VALUE; 
long result=palDec0*palDec1;
        
        String palHex=new String();
        
        String palBin=new String(); 

        palHex=Long.toHexString(result);
        while(palHex.length()<16){
        	if(result<0){
        		palHex="f"+palHex;
        	}else{
        		palHex="0"+palHex;
        	}
        }
        
        
       

        Hi.getRegistro().setPalabra(new Palabra(palHex.substring(0, 8), false));
        Lo.getRegistro().setPalabra(new Palabra(palHex.substring(8, 16), false));
        return -1;
    }
    
}
