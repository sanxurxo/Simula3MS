
package ensamblador.registros;


public class T4 extends Registro {
    
    private T4() 
    {
        inicializar();
    }
    public final static T4 t4 =new T4();
   
    public static T4 getRegistro()
    {
        return t4;
    }
    
    public int numReg()
    {
        return 12;
    }
    
    public char letraReg() 
    {
        return 'T';
    }    
    public String toString(){
    	return new String("T4");
    }
}
