package ensamblador.registros;


public class F27 extends Registro{
    
    public final static F27 f27 = new F27();
     private F27() 
    {
        inicializar();
    }  
  
    public static F27 getRegistro()
    {
        return f27;
    }
    
    public int numReg()
    {
        return 27;
    }
    
    public char letraReg()
    {
        return 'F';
    }

    public String toString(){
    	return new String("F27");
    }
}
