
package ensamblador.registros;

public class A2 extends Registro {
    
    public final static A2 a2 =new A2();
    private A2() 
    {
        inicializar();
    }  
   
    public static A2 getRegistro()
    {
        return a2;
    }
    public int numReg()
    {
        return 6;
    }
    
    public char letraReg()
    {
        return 'A';
    }

    public String toString(){
    	return new String("A2");
    }
}
