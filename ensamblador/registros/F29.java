package ensamblador.registros;


public class F29 extends Registro{
    
    public final static F29 f29 = new F29();
     private F29() 
    {
        inicializar();
    }  
  
    public static F29 getRegistro()
    {
        return f29;
    }
    
    public int numReg()
    {
        return 29;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F29");
    }
}
