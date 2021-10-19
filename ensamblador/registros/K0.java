
package ensamblador.registros;


public class K0 extends Registro {
    
     public final static K0 k0 =new K0();
    private K0() 
    {
        inicializar();
    }  
  
    public static K0 getRegistro()
    {
        return k0;
    }
    
    public int numReg() 
    {
        return 26;
    }
    
    public char letraReg() 
    {
        return 'K';
    }    
    public String toString(){
    	return new String("K0");
    }
}
