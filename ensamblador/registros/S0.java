
package ensamblador.registros;


public class S0 extends Registro {
    
    private S0() 
    {
        inicializar();
    }

    public final static S0 s0 =new S0();
 
    public static S0 getRegistro()
    {
       
        return s0;
    }
    
    public int numReg()
    {
        return 16;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("S0");
    }
}
