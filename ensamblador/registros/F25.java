package ensamblador.registros;


public class F25 extends Registro{
    
    public final static F25 f25 = new F25();
     private F25() 
    {
        inicializar();
    }  
  
    public static F25 getRegistro()
    {
        return f25;
    }
    
    public int numReg()
    {
        return 25;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F25");
    }
}
