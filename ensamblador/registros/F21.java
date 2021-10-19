package ensamblador.registros;


public class F21 extends Registro{
    
    public final static F21 f21 = new F21();
     private F21() 
    {
        inicializar();
    }  
  
    public static F21 getRegistro()
    {
        return f21;
    }
    
    public int numReg()
    {
        return 21;
    }
   
    public char letraReg()
    {
        return 'F';
    }
    
    public String toString(){
    	return new String("F21");
    }
    
}
