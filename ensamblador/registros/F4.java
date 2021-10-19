package ensamblador.registros;


public class F4 extends Registro{
    
    public final static F4 f4 = new F4();
     private F4() 
    {
        inicializar();
    }  
  
    public static F4 getRegistro()
    {
        return f4;
    }
    
    public int numReg()
    {
        return 4;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F4");
    }
    
}
