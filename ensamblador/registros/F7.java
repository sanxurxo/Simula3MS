package ensamblador.registros;


public class F7 extends Registro{
    
    public final static F7 f7 = new F7();
     private F7() 
    {
        inicializar();
    }  
  
    public static F7 getRegistro()
    {
        return f7;
    }
    
    public int numReg()
    {
        return 7;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    
    public String toString(){
    	return new String("F7");
    }
}
