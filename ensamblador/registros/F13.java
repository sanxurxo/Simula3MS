package ensamblador.registros;


public class F13 extends Registro{
    
    public final static F13 f13 = new F13();
     private F13() 
    {
        inicializar();
    }  
  
    public static F13 getRegistro()
    {
        return f13;
    }
    
    public int numReg()
    {
        return 13;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    public String toString(){
    	return new String("F13");
    }
    
}
