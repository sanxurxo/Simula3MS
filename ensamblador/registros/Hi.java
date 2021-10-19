
package ensamblador.registros;


public class Hi extends Registro {
    
    private final static Hi hi =new Hi();
    private Hi() 
    {
        inicializar();
    }
    public static Hi getRegistro()
    {
        return hi;
    }
        
    public int numReg() 
    {
        return 33;
    }
 
     public char letraReg() 
    {
        return ' ';
    }    

     public String toString(){
     	return new String("HI");
     }
}
