package ensamblador.registros;


public class F30 extends Registro{
    
    public final static F30 f30 = new F30();
     private F30() 
    {
        inicializar();
    }  
  
    public static F30 getRegistro()
    {
        return f30;
    }
    
    public int numReg()
    {
        return 30;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    
    
    public String toString(){
    	return new String("F30");
    }
}
