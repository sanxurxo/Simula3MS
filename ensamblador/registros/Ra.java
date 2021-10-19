
package ensamblador.registros;



public class Ra extends Registro {
    
    private final static Ra ra =new Ra();
    private Ra() 
    {
        inicializar();
    }
    
    public int numReg() 
    {
        return 31;
    }
    
    public static Ra getRegistro()
    {
        return ra;
    }
    
    public char letraReg() 
    {
        return 'R';
    }    
    
    public String toString(){
    	return new String("RA");
    }
}
