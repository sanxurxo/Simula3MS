package ensamblador.registros;


public class F28 extends Registro{
    
    public final static F28 f28 = new F28();
     private F28() 
    {
        inicializar();
    }  
  
    public static F28 getRegistro()
    {
        return f28;
    }
    
    public int numReg()
    {
        return 28;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F28");
    }
    
}
