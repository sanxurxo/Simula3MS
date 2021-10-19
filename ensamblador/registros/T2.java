
package ensamblador.registros;


public class T2 extends Registro {
    
    private T2() 
    {
        inicializar();
    }
    public final static T2 t2 =new T2();
   
    public static T2 getRegistro()
    {
        return t2;
    }
    
    public int numReg()
    {
        return 10;
    }
    
    public char letraReg() 
    {
        return 'T';
    }
    public String toString(){
    	return new String("T2");
    }
}
