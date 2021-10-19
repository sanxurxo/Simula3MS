package ensamblador.registros;


public class F16 extends Registro{
    
    public final static F16 f16 = new F16();
     private F16() 
    {
        inicializar();
    }  
  
    public static F16 getRegistro()
    {
        return f16;
    }
    
    public int numReg()
    {
        return 16;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F16");
    }
}
