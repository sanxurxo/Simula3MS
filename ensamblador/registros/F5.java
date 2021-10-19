package ensamblador.registros;


public class F5 extends Registro{
    
    public final static F5 f5 = new F5();
     private F5() 
    {
        inicializar();
    }  
  
    public static F5 getRegistro()
    {
        return f5;
    }
    
    public int numReg()
    {
        return 5;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F5");
    }
}

