package ensamblador.registros;


public class F26 extends Registro{
    
    public final static F26 f26 = new F26();
     private F26() 
    {
        inicializar();
    }  
  
    public static F26 getRegistro()
    {
        return f26;
    }
    
    public int numReg()
    {
        return 26;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("26");
    }
}
