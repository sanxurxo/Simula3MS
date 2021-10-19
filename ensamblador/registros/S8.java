
package ensamblador.registros;


public class S8 extends Registro {
    
     private S8() 
    {
        inicializar();
    }
    public final static S8 s8 =new S8();
 
    public static S8 getRegistro()
    {
       
        return s8;
    }
    
    public int numReg()
    {
        return 30;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("S8");
    }
}
