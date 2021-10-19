
package ensamblador.registros;


public class F18 extends Registro{
    
    public final static F18 f18 = new F18();
     private F18() 
    {
        inicializar();
    }  
  
    public static F18 getRegistro()
    {
        return f18;
    }
    
    public int numReg()
    {
        return 18;
    }
   
    public char letraReg() 
    {
        return 'F';
    }

    public String toString(){
    	return new String("F18");
    }
}