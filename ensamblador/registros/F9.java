package ensamblador.registros;


public class F9 extends Registro{
    
    public final static F9 f9 = new F9();
     private F9() 
    {
        inicializar();
    }  
  
    public static F9 getRegistro()
    {
        return f9;
    }
    
    public int numReg()
    {
        return 9;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F9");
    }
}
