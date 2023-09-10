
package ensamblador.instrucciones;
import ensamblador.registros.*;
import ensamblador.datos.*;

public class Lui extends Inmediatas {
    
    Registro r;
    private long inmediato;
    
    public Lui(String param[]) 
    {
         int posi=-1;
        inicia();
        string.append("lui ");        
        
             string.append(param[0]).append(", ");
            for(int j=0;j<registros.length;j++)
            {
                try
                {
                        posi=java.lang.Integer.parseInt(param[0].substring(1,param[0].length()));
                }
                catch(NumberFormatException e)
                {
                        r=relacionaReg(param[0]);                       
                        break;
                }
                r=relacionaReg(posi);                
            }    
          
        if(param[numParam()].startsWith("0x")){
     	
        	String h=param[numParam()];
        	/* Completamos con 0s pq el valor que se indica en una instruccion lui son los 16 bits mas significativos */
            h = "0x0000" + h.substring(2);

            Long dec=Long.decode(h);
           	long palDec=dec.parseLong(dec.toString());
            String palBin=Long.toBinaryString(palDec);
            int i;

            for(i=palBin.length(); i<32; i++){ //Completamos con 0s hasta 32 pq en la lui no hay extension de signo,
            	palBin="0"+palBin; 				//es decir, si cargas 0x8f -->0x008f0000 y no 0xff8f0000
            }
            	
            inmediato=Long.parseLong(palBin.substring(1,palBin.length()),2);
      
            String signo=palBin.substring(0,1);
            if(signo.equals(new String("1"))){            	
            	inmediato=new Long((long)(inmediato-Math.pow(2, i-1))).longValue();
            }
          
        }
        else
            inmediato=Integer.parseInt(param[numParam()]);
        string.append(param[numParam()]);
       }    
    
    public static int numParam()
    {
        return 1;
    }
    
    public int numero()
    {
        return 3;
    }
    
    public int ejecutar()
    {
        int except=-1;
        Palabra topeLui=new Palabra("0000ffff",true);
        Pc.getRegistro().incrementar(4);        
        if(inmediato>topeLui.getDec())
        {        	
        	inmediato=inmediato/((topeLui.getDec()+1));            
//            except=0;
        }                  
        Palabra p = new Palabra((topeLui.getDec() + 1) * ((int) inmediato));
       
        r.setPalabra(p);       
        boolean_pc=true;
        boolean_dato=true;
        
        this.cambiosVisibles();
        r.visualizaCambios();
        return except;                           
    }    
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
         imagen_etapa.append(direccion_multi).append("etapa4_load");
         int except=-1;
        Palabra topeLui=new Palabra("0000ffff",true);
         if(inmediato>topeLui.getDec())
        {
            inmediato=inmediato/((topeLui.getDec()+1));
         }                   
         Palabra p;
         if(inmediato>0){
         	p=new Palabra((topeLui.getDec()+1)*((int)inmediato));
         }else
         	p=new Palabra(inmediato);
        r.setPalabra(p);        
        boolean_dato=true;
        this.cambiosVisibles();
        return except;    
    }
    
    public int etapa3()
    {
        imagen_etapa=new StringBuffer();
         imagen_etapa.append(direccion_multi).append("etapa3_acc_mem");
         int except=-1;
        Palabra topeLui=new Palabra("0000ffff",true);
//        if(inmediato>topeLui.getDec())       
//            return 0;
               
        return -1;    
    }
    
    
    
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(15)+setR(0)+setR(r.numReg())+setIn((int)inmediato);       
        return bin_a_hex(codigo);
    }
       
    public Registro regModificable()
    {
        return r;
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
        r.ocupar();
        
        int except=-1;
        Palabra topeLui=new Palabra("0000ffff",true);       
        if(inmediato>topeLui.getDec())
        {
            inmediato=inmediato/((topeLui.getDec()+1));
           // except=0;
        }
        Palabra p;
        if(inmediato>0){
        	p=new Palabra((topeLui.getDec()+1)*((int)inmediato));
        }else
        	p=new Palabra(inmediato);
        
        r.setPalabra(p);
       
        return except;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {    
        r.liberar();
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;                                  
    }
            
}
