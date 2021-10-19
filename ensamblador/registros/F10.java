package ensamblador.registros;


public class F10 extends Registro{
    
    public final static F10 f10 = new F10();
     private F10() 
    {
        inicializar();
    }  
  
    public static F10 getRegistro()
    {
        return f10;
    }
    
    public int numReg()
    {
        return 10;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F10");
    }
}
