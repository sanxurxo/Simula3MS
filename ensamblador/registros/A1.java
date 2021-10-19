
package ensamblador.registros;

public class A1 extends Registro {
   
    private final static A1 a1 =new A1();
    private A1() 
    {
        inicializar();
    }  
   
    public static A1 getRegistro()
    {
        return a1;
    }
    public int numReg()
    {
        return 5;
    }
    
    public char letraReg()
    {
        return 'A';
    }
    public String toString(){
    	return new String("A1");
    }
}
