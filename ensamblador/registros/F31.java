package ensamblador.registros;


public class F31 extends Registro{
    
    public final static F31 f31 = new F31();
     private F31() 
    {
        inicializar();
    }  
  
    public static F31 getRegistro()
    {
        return f31;
    }
    
    public int numReg()
    {
        return 31;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F31");
    }
}
