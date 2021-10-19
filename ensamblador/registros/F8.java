package ensamblador.registros;


public class F8 extends Registro{
    
    public final static F8 f8 = new F8();
     private F8() 
    {
        inicializar();
    }  
  
    public static F8 getRegistro()
    {
        return f8;
    }
    
    public int numReg()
    {
        return 8;
    }
   
    public char letraReg()
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F8");
    }
}
