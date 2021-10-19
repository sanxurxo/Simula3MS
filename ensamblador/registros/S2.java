
package ensamblador.registros;


public class S2 extends Registro {
    
     private S2() 
    {
        inicializar();
    }
    public final static S2 s2 =new S2();
 
    public static S2 getRegistro()
    {
       
        return s2;
    }
    
    public int numReg()
    {
        return 18;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    
    
    public String toString(){
    	return new String("S2");
    }
    
}
