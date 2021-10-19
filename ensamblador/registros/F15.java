package ensamblador.registros;


public class F15 extends Registro{
    
    public final static F15 f15 = new F15();
     private F15() 
    {
        inicializar();
    }  
  
    public static F15 getRegistro()
    {
        return f15;
    }
    
    public int numReg()
    {
        return 15;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F15");
    }
}
