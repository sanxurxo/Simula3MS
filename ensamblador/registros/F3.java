package ensamblador.registros;


public class F3 extends Registro{
    
    public final static F3 f3 = new F3();
     private F3() 
    {
        inicializar();
    }  
  
    public static F3 getRegistro()
    {
        return f3;
    }
    
    public int numReg()
    {
        return 3;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F3");
    }
}
