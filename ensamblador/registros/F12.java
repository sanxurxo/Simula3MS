package ensamblador.registros;


public class F12 extends Registro{
    
    public final static F12 f12 = new F12();
     private F12() 
    {
        inicializar();
    }  
  
    public static F12 getRegistro()
    {
        return f12;
    }
    
    public int numReg()
    {
        return 12;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F12");
    }
}
