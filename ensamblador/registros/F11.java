package ensamblador.registros;


public class F11 extends Registro{
    
    public final static F11 f11 = new F11();
     private F11() 
    {
        inicializar();
    }  
  
    public static F11 getRegistro()
    {
        return f11;
    }
    
    public int numReg()
    {
        return 11;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    public String toString(){
    	return new String("F11");
    }
}
