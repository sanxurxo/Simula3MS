
package ensamblador.registros;


public class FP extends Registro {
    
     private FP()
    {
        inicializar();
    }
    public final static FP fp =new FP();
 
    public static FP getRegistro()
    {
       
        return fp;
    }
    
    public int numReg()
    {
        return 30;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("FP");
    }
}
