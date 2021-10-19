
package ensamblador.datos;


import java.util.*;

import ensamblador.registros.*;
import ensamblador.instrucciones.*;

/**SegDatos.java
 *Clase abstracta que recoge las caraterísticas comunes de la memoria y la pila
 **/
public abstract class SegDatos extends Observable implements ensamblador.datos.Tipos{
    
/**Metodo abstracto que devuelve una palabra con el byte solicitado
 *@param long direccion, int desplazamiento
 *@return Palabra con el byte solicitado
 **/    
    public abstract Palabra getByte(long direccion, int desp);
    
/**Metodo abstracto que coloca un byte en la posicion indicada
 *@param long direccion, int desplazamiento, Palabra palabra
 *@return void
 **/    
    public abstract void setByte(long direccion, int desp, Palabra palabra);
    
/**Metodo abstracto que proporciona informacion para visualizar los datos
 *@param Sin parametros 
 *@return Vector datos
 **/    
    public abstract Vector visualizar();
    
/**Metodo abstracto para inicializar la clase
 *@param Sin parametros 
 *@return void
 **/    
    public abstract void inicializar();     
    
/**Metodo abstracto que coloca una palabra en la posicion indicada
 *@param long direccion, int desplazamiento, Palabra palabra
 *@return void
 **/    
    public abstract void setPalabra(long direccion, int desp, Palabra palabra);
    
}
