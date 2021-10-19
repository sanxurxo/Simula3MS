
package ensamblador.registros;


public class T1 extends Registro {
    
    private T1() 
    {
        inicializar();
    }
    public final static T1 t1 =new T1();
   
    public static T1 getRegistro()
    {
        return t1;
    }
    
    public int numReg()
    {
        return 9;
    }
    
    public char letraReg() 
    {
        return 'T';
    }    
    
    public String toString(){
    	return new String("T1");
    }
}
