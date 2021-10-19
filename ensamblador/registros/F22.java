package ensamblador.registros;


public class F22 extends Registro{
    
    public final static F22 f22 = new F22();
     private F22() 
    {
        inicializar();
    }  
  
    public static F22 getRegistro()
    {
        return f22;
    }
    
    public int numReg()
    {
        return 22;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    
    public String toString(){
    	return new String("F22");
    }
    
}
