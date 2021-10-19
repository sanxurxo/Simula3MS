package ensamblador.registros;


public class F14 extends Registro{
    
    public final static F14 f14 = new F14();
     private F14() 
    {
        inicializar();
    }  
  
    public static F14 getRegistro()
    {
        return f14;
    }
    
    public int numReg()
    {
        return 14;
    }
   
    public char letraReg() 
    {
        return 'F';
    }    

    public String toString(){
    	return new String("F14");
    }
}
