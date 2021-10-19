
package ensamblador.registros;


public class K1 extends Registro {
    
    public final static K1 k1 =new K1();
    private K1() 
    {
        inicializar();
    }  
  
    public static K1 getRegistro()
    {
        return k1;
    }
    
    public int numReg() 
    {
        return 27;
    }   
    
    public char letraReg() 
    {
        return 'K';
    }    

    public String toString(){
    	return new String("K1");
    }
}
