
package ensamblador.registros;


public class S4 extends Registro {
    
     private S4() 
    {
        inicializar();
    }
    public final static S4 s4 =new S4();
 
    public static S4 getRegistro()
    {
       
        return s4;
    }
    
    public int numReg()
    {
        return 20;
    }
    
    public char letraReg() 
    {
        return 'S';
    }
    
    public String toString(){
    	return new String("S4");
    }
}
