
package ensamblador.registros;


public class T8 extends Registro {
    
    private T8() 
    {
        inicializar();
    }
    public final static T8 t8 =new T8();
   
    public static T8 getRegistro()
    {
        return t8;
    }
    
    public int numReg()
    {
        return 24;
    }
    
    public char letraReg() 
    {
        return 'T';
    }    
    
    public String toString(){
    	return new String("T8");
    }
}
