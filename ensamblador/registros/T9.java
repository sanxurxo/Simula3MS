
package ensamblador.registros;


public class T9 extends Registro {
    
     private T9()
    {
        inicializar();
    }
    public final static T9 t9 =new T9();
 
    public static T9 getRegistro()
    {
       
        return t9;
    }
    
    public int numReg()
    {
        return 25;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("T9");
    }
}
