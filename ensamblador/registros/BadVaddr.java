package ensamblador.registros;


public class BadVaddr extends Registro {
    
    private static BadVaddr badVaddr= new BadVaddr();
    private BadVaddr(){
        inicializar();
    }

    public static BadVaddr getRegistro(){        
        return badVaddr;
    }
    
    public int numReg()
    {
        return 8; //Este numero 8 hace referencia al coprocesador 0
    }

    public char letraReg() {
        return 'C'; 
    }
    public String toString(){
    	return new String("BaddVaddr");
    }
}
