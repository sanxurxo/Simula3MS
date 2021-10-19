
package ensamblador.registros;


public class T5 extends Registro {
    
     private T5() 
    {
        inicializar();
    }
    public final static T5 t5 =new T5();
   
    public static T5 getRegistro()
    {
        return t5;
    }
    
    public int numReg()
    {
        return 13;
    }
    
    public char letraReg() 
    {
        return 'T';
    }    
    
    public String toString(){
    	return new String("T5");
    }
}
