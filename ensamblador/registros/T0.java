
package ensamblador.registros;


public class T0 extends Registro {
    
    private T0() 
    {
        inicializar();
    }
    public final static T0 t0 =new T0();
   
    public static T0 getRegistro()
    {
        return t0;
    }
    
    public int numReg()
    {
        return 8;
    }
    
    public char letraReg() 
    {
        return 'T';
    }
    public String toString(){
    	return new String("T0");
    }
}
