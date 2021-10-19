
package ensamblador.registros;

import java.util.Vector;

import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;

public class Sp extends Registro {
    
    private static Sp sp= new Sp(); 
    private Sp()
    {        
        inicializar();
    }
    
    public static Sp getRegistro()
    {
      return sp;
    }
    
    public void inicializar()
    {
    	this.valor=new Palabra("70000000",true);
        this.valoresSegm=new Vector();
        this.valoresSegm.add(new Palabra("70000000",true));
        this.numReferencias=0;
        setChanged();
        notifyObservers(this);   
    }
    public int numReg()
    {
        return 29;
    }

    public void modificar(long entero)
    {
        modPila(entero);
        this.valor=new Palabra(entero);               
        setChanged();
    }
    
    public void modificar(String hex)
    {
        modPila((new Palabra(hex,true)).getDec());
        this.valor=new Palabra(hex, true);
        setChanged();
    }
    
    public void incrementar(long decimal)
    {
        modPila(this.getPalabra().getDec()-decimal);
        this.valor=valor.sumar(decimal);        
        setChanged();
    }
    
    public void setPalabra(Palabra palabra)
    {
        modPila(palabra.getDec());
        this.valor=palabra;     
        this.valoresSegm.add(palabra);
        setChanged();
    }
    
    public void modPila(long valor_act)
    {
        long pila_antes=this.getPalabra().getDec();
        int modif=(int)(pila_antes-valor_act)/4;
        while(modif>0)
        {    
            Pila.getPila().nuevaPalabra();            
            modif--;
        }               
    }
    
    public char letraReg() 
    {
        return ' ';
    }
    
    public String toString(){
    	return new String("SP");
    }
}
