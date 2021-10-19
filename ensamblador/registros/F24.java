package ensamblador.registros;


public class F24 extends Registro{
    
    public final static F24 f24 = new F24();
     private F24() 
    {
        inicializar();
    }  
  
    public static F24 getRegistro()
    {
        return f24;
    }
    
    public int numReg()
    {
        return 24;
    }
   
    public char letraReg()
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F24");
    }
}
