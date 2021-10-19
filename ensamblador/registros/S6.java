
package ensamblador.registros;


public class S6 extends Registro {
    
      private S6() 
    {
        inicializar();
    }
    public final static S6 s6 =new S6();
 
    public static S6 getRegistro()
    {
       
        return s6;
    }
    
    public int numReg()
    {
        return 22;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    
    
    public String toString(){
    	return new String("S6");
    }
    
}
