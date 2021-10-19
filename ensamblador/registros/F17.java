
package ensamblador.registros;


public class F17 extends Registro{
    
    public final static F17 f17 = new F17();
     private F17() 
    {
        inicializar();
    }  
  
    public static F17 getRegistro()
    {
        return f17;
    }
    
    public int numReg()
    {
        return 17;
    }
   
    public char letraReg()
    {
        return 'F';
    }
    
    public String toString(){
    	return new String("F17");
    }
}