
package ensamblador.registros;


public class F0 extends Registro{
    
    public final static F0 f0 = new F0();
     private F0() 
    {
        inicializar();
    }  
  
    public static F0 getRegistro()
    {
        return f0;
    }
    
    public int numReg()
    {
        return 0;
    }
   
    public char letraReg()
    {
        return 'F';
    }
    
    public String toString(){
    	return new String("F0");
    }
}
