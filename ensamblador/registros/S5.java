
package ensamblador.registros;


public class S5 extends Registro {
    
     private S5() 
    {
        inicializar();
    }
    public final static S5 s5 =new S5();
 
    public static S5 getRegistro()
    {
       
        return s5;
    }
    
    public int numReg()
    {
        return 21;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    
    
    public String toString(){
    	return new String("S5");
    }
    
}
