package ensamblador.datos;

import java.util.*;
public class Flotante extends Datos {
    
    public Flotante(String datos) {}
    
    public static Vector esVal(String datos)
    {
        Vector variables=new Vector();
        String token;
        StringTokenizer st=new StringTokenizer(datos,",");
        while(st.hasMoreTokens())
        {
            token=st.nextToken();
            token=token.trim();
            try
                {
                    Double.valueOf(token).doubleValue();
                }
            catch (NumberFormatException e)
                {
                    variables=new Vector();
                    return variables;
                }
                
                variables.addElement(token);
        }
        return variables;
    }
    
    
}
