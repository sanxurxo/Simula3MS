
package ensamblador.registros;


public class S3 extends Registro {
    
    private S3() 
    {
        inicializar();
    }
    public final static S3 s3 =new S3();
 
    public static S3 getRegistro()
    {
       
        return s3;
    }
    
    public int numReg()
    {
        return 19;
    }
    
    public char letraReg() 
    {
        return 'S';
    }    

    public String toString(){
    	return new String("S3");
    }
}
