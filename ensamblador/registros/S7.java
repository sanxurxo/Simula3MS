
package ensamblador.registros;


public class S7 extends Registro {
    
     private S7() 
    {
        inicializar();
    }
    public final static S7 s7 =new S7();
 
    public static S7 getRegistro()
    {
       
        return s7;
    }
    
    public int numReg()
    {
        return 23;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("S7");
    }
    
}
