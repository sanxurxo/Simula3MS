
package ensamblador.registros;


public class V0 extends Registro {
    
    private V0() 
    {
        inicializar();
    }
    public final static V0 v0 =new V0();
 
    public static V0 getRegistro()
    {
        return v0;
    }
    
    public int numReg()
    {
        return 2;
    }
    
    public char letraReg() 
    {
        return 'V';
    }
    public String toString(){
    	return new String("v0");
    }
    
}
