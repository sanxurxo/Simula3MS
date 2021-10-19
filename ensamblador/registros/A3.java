
package ensamblador.registros;


public class A3 extends Registro {

    public final static A3 a3 =new A3();
    
    private A3() 
    {
        inicializar();
    }  
   
    public static A3 getRegistro()
    {
        return a3;
    }
    public int numReg()
    {
        return 7;
    }
    
    public char letraReg()
    {
        return 'A';
    }

    public String toString(){
    	return new String("A3");
    }
}
