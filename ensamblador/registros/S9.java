
package ensamblador.registros;


public class S9 extends Registro {
    
     private S9() 
    {
        inicializar();
    }
    public final static S9 s9 =new S9();
 
    public static S9 getRegistro()
    {
       
        return s9;
    }
    
    public int numReg()
    {
        return 25;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("S9");
    }
}
