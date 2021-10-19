
package ensamblador.registros;


public class At extends Registro {
    
     public final static At at =new At();
    private At() 
    {
        inicializar();
    }  
  
    public static At getRegistro()
    {
        return at;
    }
 
    public int numReg() 
    {
        return 1;
    }
    
    public char letraReg()
    {
        return 'A';
    }
    public String toString(){
    	return new String("AT");
    }
}
