package ensamblador.registros;


public class F2 extends Registro{
    
    public final static F2 f2 = new F2();
     private F2() 
    {
        inicializar();
    }  
  
    public static F2 getRegistro()
    {
        return f2;
    }
    
    public int numReg()
    {
        return 2;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F2");
    }
}
