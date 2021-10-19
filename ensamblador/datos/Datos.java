/**
 *Datos.java
 *Clase abstracta que contiene las caracteristicas comunes a los diferentes tipos de datos
 **/
package ensamblador.datos;
import java.util.StringTokenizer;
import java.util.Vector;

public abstract class Datos {
    /**
     *Metodo estatico que indica si una declaracion de datos es valida
     *@param String datos
     *@retrun Vector errores
     **/
    public static Vector esValida(String datos)
    {
        Vector error=new Vector();
        char com[]={'"'};
        String comilla=new String(com);        
   
        String token;
        while(datos.startsWith(" "))
                  datos=datos.substring(1,datos.length());
        while(datos.endsWith(" "))
                  datos=datos.substring(0,datos.length()-1);               
        if(datos.startsWith("\"") && datos.endsWith("\""))
        {             
            StringTokenizer st=new StringTokenizer(datos,comilla);
            while(st.hasMoreTokens())
            {       
                datos=st.nextToken();                
                error.addElement(datos);
                if(st.hasMoreTokens())
                {
                    datos=st.nextToken().trim();
                    if(!datos.equals(","))
                        return new Vector();
                }
                    
            }               
        }
        else
            return new Vector();
        return error;
    }
}