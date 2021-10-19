
package ensamblador.registros;


public class S1 extends Registro {
    
     private S1() 
    {
        inicializar();
    }
    public final static S1 s1 =new S1();
 
    public static S1 getRegistro()
    {
       
        return s1;
    }
    
    public int numReg()
    {
        return 17;
    }
    
    public char letraReg() 
    {
        return 'S';
    }
    
    public String toString(){
    	return new String("S1");
    }
}
