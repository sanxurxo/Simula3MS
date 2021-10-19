package ensamblador.registros;


public class EPC extends Registro {
    
    private static EPC status= new EPC();
    private EPC() 
    {
        inicializar();
    }

    public static EPC getRegistro()
    {        
        return status;
    }
    
    public int numReg()
    {
        return 37;
    }

    public char letraReg() 
    {
        return 'C';
    }
    public String toString(){
    	return new String("Status");
    }
}
