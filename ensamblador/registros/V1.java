
package ensamblador.registros;


public class V1 extends Registro {
    
    private V1() 
    {
        inicializar();
    }
    public final static V1 v1 =new V1();
 
    public static V1 getRegistro()
    {
        return v1;
    }
    
    public int numReg()
    {
        return 3;
    }
    
    public char letraReg() 
    {
        return 'V';
    }    
    public String toString(){
    	return new String("V1");
    }
}
