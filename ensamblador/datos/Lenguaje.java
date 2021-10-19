package ensamblador.datos;

import java.util.Locale;
import java.util.ResourceBundle;

public class Lenguaje {
	
	private static Lenguaje lenguaje = null;
	private static Locale currentLocale;
	private static ResourceBundle messages;
	
    private Lenguaje() {}
    
    public static void inicializar(String leng) {
    	currentLocale = new Locale(leng);    	
    	messages = ResourceBundle.getBundle("ensamblador/util/messages/MessagesBundle", currentLocale);
    }
    
    public static Lenguaje getInstancia() {    	     
    	if(lenguaje == null){        
    		lenguaje = new Lenguaje();        
    	}        
    	return lenguaje;      
    }
    
    public String getString(String str) {
    	return messages.getString(str);
    }
	
}
