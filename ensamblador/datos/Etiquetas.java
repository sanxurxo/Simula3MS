
package ensamblador.datos;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import ensamblador.util.excepcion.DuplicatedLabelException;

/**Etiquetas.java
 *Clase que mantiene las etiquetas de un codigo
 **/
public class Etiquetas implements Tipos{
    
        private Vector nombres=new Vector();
        private Vector direcciones=new Vector();
        private static Etiquetas etiquetas=new Etiquetas();
        private static StringBuffer error=new StringBuffer();
        private Hashtable<String, String> etiquetasRutina=new Hashtable<String, String>();
        
 /**Constructor de Etiquetas
 *@param Sin parametros
 **/
        private Etiquetas(){};
       
 /**Metodo statico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Etiquetas instancia
 **/    
        public static Etiquetas getEtiquetas()
        {
            return etiquetas;
        }
        
/**Metodo que anhade una etiqueta a las Etiquetas 
 *@param String nombre, int pos
 *@return void
 **/    
        public void anhadirEtiqueta(String nom,int pos)
        {
            int posicion;
            for(int i=0; i<nombres.size();i++){
                if(nombres.elementAt(i).equals(nom))
                    this.setError(nom);
           }
           nombres.addElement(nom);
           posicion=(int)INICIO_PC.getDec()+pos*4;            
           direcciones.addElement(new Palabra(posicion));
        }
        
        public void anhadirEtiquetaRutina(String nom, String direcc)throws DuplicatedLabelException{
            int posicion;            
            if(etiquetasRutina.get(nom)==null){
            	etiquetasRutina.put(nom, direcc);            	
            	
            }else{
            	throw new DuplicatedLabelException();
            }
            
            
        }
        
/**Metodo que devuelve la direccion de una etiqueta 
 *@param String etiqueta
 *@return int
 **/        
        public long getDireccion(String etiq)
        {        	
            int i=0;
            while ((i<nombres.size())&&(!etiq.equals((String)nombres.elementAt(i))))            
                i++;
            
         
            if(i>=nombres.size()){
         
            	String valor;
           
            	if((valor=etiquetasRutina.get(etiq))!=null){         
            		return new Palabra(valor, true).getDec();
            	}
            }
             return (int)((Palabra)direcciones.elementAt(i)).getDec();
        }
        
        public String getDireccionEtiquetasRutina(String etiqueta){
        	return etiquetasRutina.get(etiqueta);
        }
        
/**Metodo que devuelve los identificadores de las etiquetas 
 *@param Sin parametros
 *@return Vector identificadores
 **/    
        public Vector getNombres()
        {
        	Vector v=new Vector();
        	
        	for(int i=0;i<nombres.size();i++){
        		v.add(nombres.elementAt(i));
        	
        	}
        	Enumeration en=etiquetasRutina.keys();
        	String s= new String();
        	while(en.hasMoreElements()){
        		s=(String)en.nextElement();        	
        		if(!v.contains(s)){        	
        			v.add(s);
        		}
        		
        	}
        	
            return v;
        }                
        
/**Metodo que inicializa la unica instancia de Etiquetas 
 *@param Sin parametros
 *@return void
 **/    
        public void inicializar()
        {
            direcciones=new Vector();
            nombres=new Vector();
            error=new StringBuffer();
            this.etiquetasRutina=new Hashtable<String, String>();
        }
        
/**Metodo que establece un errror si el identificador de la etiqueta no es valido 
 *@param String identificador
 *@return void
 **/    
        public void setError(String eti)
        {
            error.append(eti+"\t"+"Etiqueta ya definida"+"\n");            
        }
        
/**Metodo statico para comprabar si hubo algun error al crear las Etiquetas 
 *@param Sin parametros
 *@return String error
 **/    
        public static String getError()
        {
            if(error!=null)
                return error.toString();
            else
                return null;
        }
        
        public String toString(){
        	String s=new String();
        	for(int i=0; i<nombres.size();i++){
        		s+=nombres.elementAt(i)+"-->"+((Palabra)direcciones.elementAt(i)).getHex()+"\n";
        	}
        	Enumeration en=etiquetasRutina.keys();
        	
        	String aux=new String();
        	while(en.hasMoreElements()){
        		aux=(String)en.nextElement();
        		s+=aux+"-->  "+etiquetasRutina.get(aux)+"\n";
        	}
        	
        	System.out.println(s);
        	return s;
        	
        }
}

