
package ensamblador.registros;


public class T6 extends Registro {
    
   private T6() 
    {
        inicializar();
    }
    public final static T6 t6 =new T6();
   
    public static T6 getRegistro()
    {
        return t6;
    }
    
    public int numReg()
    {
        return 14;
    }
    
    public char letraReg() 
    {
        return 'T';
    }
    public String toString(){
    	return new String("T6");
    }
}
