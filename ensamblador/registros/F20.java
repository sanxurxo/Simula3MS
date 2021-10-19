package ensamblador.registros;


public class F20 extends Registro{
    
    public final static F20 f20= new F20();
     private F20() 
    {
        inicializar();
    }  
  
    public static F20 getRegistro()
    {
        return f20;
    }
    
    public int numReg()
    {
        return 20;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F20");
    }
}
