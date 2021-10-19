package ensamblador.registros;


public class Cause extends Registro32 {
    
    private static Cause cause= new Cause();
    private Cause() {
        inicializar();
    }

    public static Cause getRegistro()
    {        
        return cause;
    }
    
    public int numReg(){
        return 36;
    }

    public char letraReg() 
    {
        return 'C';
    }
    public String toString(){
    	return new String("Cause");
    }
}
