
package ensamblador.registros;


public class T3 extends Registro {
    
    private T3() 
    {
        inicializar();
    }
    public final static T3 t3 =new T3();
   
    public static T3 getRegistro()
    {
        return t3;
    }
    
    public int numReg()
    {
        return 11;
    }
    
    public char letraReg() 
    {
        return 'T';
    }
    public String toString(){
    	return new String("T3");
    }
}
