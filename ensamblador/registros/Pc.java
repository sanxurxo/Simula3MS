
package ensamblador.registros;

import java.util.Vector;

import ensamblador.datos.Palabra;
public class Pc extends Registro {
    
    private static Pc pc= new Pc();
    private Pc() 
    {
        inicializar();
    }

    public static Pc getRegistro()
    {        
        return pc;
    }
    
    public void inicializar()
    {
        this.valor=new Palabra("00400000", true); 
        this.valoresSegm=new Vector();
        this.valoresSegm.add(new Palabra("00400000",true));
        this.numReferencias=0;
        setChanged();
        notifyObservers(this);        
    }

    public int numReg()
    {
        return 32;
    }

    public int valorBase()
    {
        Palabra palBase=new Palabra("00400000",true);
        return (int)palBase.getDec();
    }
    
    public char letraReg() 
    {
        return ' ';
    }    
    public String toString(){
    	return new String("PC");
    }
}
