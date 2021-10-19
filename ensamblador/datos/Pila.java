

package ensamblador.datos;


import java.util.*;
import ensamblador.registros.*;
import ensamblador.instrucciones.*;

/**Pila.java
 *Subclase de SegDatos que implementa el funcionamiento de una pila
 **/
public class Pila extends SegDatos{
    private Vector la_pila=new Vector();
    private Sp sp=Sp.getRegistro(); 
    private static Pila pila=new Pila();

/**Constructor de Pila
 *@param Sin parametros
 **/
    private Pila(){};
    
 /**Metodo estatico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Pila pila
 **/  
    public static Pila getPila()
    {
        return pila;
    }
    
/**Metodo que coloca una palabra en la posicion indicada
 *@param long direccion, int desplazamiento, Palabra palabra
 *@return void
 **/   
    public void setPalabra(long direccion, int desp, Palabra palabra)
    {
        int indice=(int)(INICIO_PILA.getDec()-direccion-desp)/4;
        if(indice<la_pila.size())
            la_pila.remove(indice);
        if(indice<la_pila.size())
            la_pila.add(indice, palabra);
        else
        {
            while(la_pila.size()<indice)            
                la_pila.add(la_pila.size(), new Palabra(0));                            
            la_pila.add(indice, palabra);
        }
        setChanged();
/**********************************************
*********notifyObservers(visualizar());********        
***********************************************/
    }
    
/**Metodo que devuelve la palabra solicitada
 *@param long direccion, int desplazamiento
 *@return Palabra palabra
 **/    
    public Palabra getPalabra(long direccion, int desp)
    {
        int resta=(int)(INICIO_PILA.getDec()-(direccion+desp));
        int indice=((resta)/4);        
        return (Palabra)la_pila.elementAt(indice);
         
    }
    
/**Metodo que coloca un byte en la posicion indicada
 *@param long direccion, int desplazamiento, Palabra palabra
 *@return void
 **/    
    public void setByte(long direccion, int desp, Palabra palabra)
    {
       
        Palabra pal=getPalabra(direccion, desp);
        int despl=desp%4;
        String spal=pal.getHex().substring(2, pal.getHex().length());
        String spalabra=palabra.getHex().substring(2,palabra.getHex().length());
        String valor_final=new String();
        valor_final=spal.substring(0,spal.length()-2-2*despl);
        valor_final+=spalabra.substring(spalabra.length()-2, spalabra.length());
        valor_final+=spal.substring(spal.length()-2*despl, spal.length());
        direccion+=(desp/4);
        setPalabra(direccion, -despl, new Palabra(valor_final, true));
    }
        
/**Metodo que devuelve una palabra que contiene el byte solicitado en el byte menos significativo
 *@param long direccion, int desplazamiento
 *@return Palabra solicitada
 **/  
    public Palabra getByte(long direccion, int desp)
    {
        Palabra pal=getPalabra(direccion, desp);
        desp=desp%4;
        String valor_final=pal.getHex().substring(2,pal.getHex().length());
        valor_final=valor_final.substring(valor_final.length()-2-2*desp, valor_final.length()-2*desp);
        return new Palabra(valor_final, true);
    }
    
/**Metodo que proporciona informacion para visualizar la pila
 *@param Sin parametros 
 *@return Vector informacion
 **/    
    public Vector visualizar()
    {
        Vector pil=new Vector();
        Vector aux;           
        int i=0;
        for (i=0; i<la_pila.size(); i=i+4)
        {
            aux=new Vector();
            for (int j=0; ((j<4) && ((i+j)<la_pila.size())); j++)
                aux.addElement(((Palabra)la_pila.elementAt(i+j)).getHex());
            pil.addElement(aux);
        }
        int modulo;
        if(la_pila.size()>0)
        {
            modulo=(((Vector)pil.elementAt((i-1)/4)).size())%4;
        
            if(modulo!=0)
            {
                for (int j=modulo; j<4;j++)
                {
                    ((Vector)pil.elementAt((i-1)/4)).addElement("          ");
                }
            }
        }
        return pil;
    }
    
 /**Metodo que anhade una palabra de ceros a la pila
 *@param Sin parametros 
 *@return void
 **/    
    public void nuevaPalabra()
    {
        la_pila.addElement(new Palabra(0));
    }
    
/**Metodo para inicializar la clase
 *@param Sin parametros 
 *@return void
 **/    
    public void inicializar()
    {
        la_pila.clear();
        sp.inicializar();
    }
    
/**Metodo para indicar que la interfaz grafica debe visualizar los cambios
 *@param Sin parametros 
 *@return void
 **/      
    public void visualizaCambios()
    {
        notifyObservers(visualizar());
    }
}