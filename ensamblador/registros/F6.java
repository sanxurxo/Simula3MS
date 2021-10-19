package ensamblador.registros;


public class F6 extends Registro{
    
    public final static F6 f6 = new F6();
     private F6() 
    {
        inicializar();
    }  
  
    public static F6 getRegistro()
    {
        return f6;
    }
    
    public int numReg()
    {
        return 6;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F6");
    }
}
