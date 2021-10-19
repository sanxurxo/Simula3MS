
package ensamblador.registros;


public class F1 extends Registro{
    
    public final static F1 f1 = new F1();
     private F1() 
    {
        inicializar();
    }  
  
    public static F1 getRegistro()
    {
        return f1;
    }
    
    public int numReg()
    {
        return 1;
    }
    
    public char letraReg()
    {
        return 'F';
    }
 
    public String toString(){
    	return new String("F1");
    }
}