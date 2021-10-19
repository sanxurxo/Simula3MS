
package ensamblador.registros;


public class Lo extends Registro {
    
    private final static Lo lo =new Lo();
    private Lo() 
    {
        inicializar();
    }
    
    public static Lo getRegistro()
    {
        return lo;
    }
    
    public int numReg() 
    {
        return 34;
    }
    
    public char letraReg() 
    {
        return ' ';
    }
    
    public String toString(){
    	return new String("LO");
    }
}
