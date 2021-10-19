package ensamblador.registros;

import java.util.Vector;

import ensamblador.datos.Palabra;


public class Status extends Registro32 {
    
    private static Status status= new Status();
    private Status() 
    {
    	valor=new Palabra("0000ff03", true); //los bits del 15 al 8 se inicializan a 1 por la mascara de interrupciones
        inicializar();
    }
    public void inicializar()
    {
    	this.cicloModificado=0;
    	this.qi=new Vector<String>();
        this.valor=new Palabra("0000ff03", true); 
        this.valoresSegm=new Vector();
        this.valoresSegm.add(new Palabra("0000ff03", true));
        this.numReferencias=0;
        setChanged();
        notifyObservers(this);        
    }

    public static Status getRegistro()
    {        
        return status;
    }
    
    public int numReg()
    {
        return 35;
    }

    public char letraReg() 
    {
        return 'C';
    }
    public String toString(){
    	return new String("Status");
    }
}
