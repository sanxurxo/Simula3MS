
package ensamblador.datos;

import java.util.*;
public class Ascii extends Datos {
    
    public Ascii(String datos) {}
    
    public static Vector esVal(String datos)
    {
        Vector variables=new Vector();
        Vector aux=Datos.esValida(datos);
        if(aux.size()==0)
            return new Vector();
 
        for(int i=0;i<aux.size();i++)
        {
            String var=(String)aux.elementAt(i);
           
            for(int j=0;j<var.length();j++)                                     
                variables.addElement(var.substring(j,j+1));                                                         
        }
        return variables;
    }
    
}
