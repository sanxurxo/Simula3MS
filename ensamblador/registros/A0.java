
package ensamblador.registros;

import ensamblador.datos.*;
import ensamblador.instrucciones.*;
public class A0 extends Registro {
    
    public final static A0 a0 =new A0();
    private A0() 
    {
        inicializar();
    }  
  
    public static A0 getRegistro()
    {
        return a0;
    }
    public int numReg()
    {
        return 4;
    }
    
    public char letraReg()
    {
        return 'A';
    }
    public String toString(){
    	return new String("A0");
    }
    
}
