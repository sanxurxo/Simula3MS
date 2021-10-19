
package ensamblador.datos;

import java.util.*;
public class Space extends Datos {
    
     public Space() {}
    
    public static Vector esVal(String datos)
    {
        Vector variables=new Vector();          
        int tam;
                try
                {
                    tam=Integer.parseInt(datos.trim());
                } catch(NumberFormatException e)
                {
                    variables=new Vector();
                    return variables;
                }
            variables.addElement(new Integer(tam));                    
        return variables;
    }            
}
