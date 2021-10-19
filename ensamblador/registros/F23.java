package ensamblador.registros;


public class F23 extends Registro{
    
    public final static F23 f23 = new F23();
     private F23() 
    {
        inicializar();
    }  
  
    public static F23 getRegistro()
    {
        return f23;
    }
    
    public int numReg()
    {
        return 23;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F23");
    }
}
