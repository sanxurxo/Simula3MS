
package ensamblador.registros;


public class Zero extends Registro {
    
    private static Zero zero= new Zero(); 
    private Zero() 
    {
        inicializar();
    }
        
    public static Zero getRegistro()
    {
        return zero;
    }
    
    public int numReg()
    {
        return 0;
    }
    
    public char letraReg() 
    {
        return 'Z';
    }    
    public String toString(){
    	return new String("ZERO");
    }
}
