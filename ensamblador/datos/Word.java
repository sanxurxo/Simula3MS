
package ensamblador.datos;

import java.util.*;
public class Word extends Datos {
    
    public Word(String datos) {}
    
    public static Vector esVal(String datos)
    {
        Vector variables=new Vector();
        String token;
        
        StringTokenizer st=new StringTokenizer(datos,",");
        while(st.hasMoreTokens())
        {
            token=st.nextToken();
            token=token.trim();
            if((token.startsWith("0x")) && (token.length()>2) && (token.length()<11))
            {
                try
                {
                    
                    Long.parseLong(token.substring(2,token.length()), 16);
                }catch (NumberFormatException e)
                {
                    variables=new Vector();
                    return variables;
                }
                
                variables.addElement(token);
            }
            else
            {
                try
                {
                    Integer.parseInt(token);
                } catch(NumberFormatException e)
                {
                    variables=new Vector();
                    return variables;
                }
            variables.addElement(token);
            }
        }
        return variables;
    }
    
    
}
