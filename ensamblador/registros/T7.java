
package ensamblador.registros;


public class T7 extends Registro {
    
     private T7() 
    {
        inicializar();
    }
    public final static T7 t7 =new T7();
   
    public static T7 getRegistro()
    {
        return t7;
    }
    
    public int numReg()
    {
        return 15;
    }
    
    public char letraReg() 
    {
        return 'T';
    }
    public String toString(){
    	return new String("T7");
    }
}
