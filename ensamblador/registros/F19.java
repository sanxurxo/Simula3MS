
package ensamblador.registros;


public class F19 extends Registro{
    
    public final static F19 f19 = new F19();
     private F19() 
    {
        inicializar();
    }  
  
    public static F19 getRegistro()
    {
        return f19;
    }
    
    public int numReg()
    {
        return 19;
    }
   
    public char letraReg()
    {
        return 'F';
    }
 
    public String toString(){
    	return new String("F19");
    }
}