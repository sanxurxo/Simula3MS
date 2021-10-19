
package ensamblador.datos;

import java.util.Vector;

/**Memoria.java
 *Subclase de SegDatos que implementa el funcionamiento de la memoria
 **/
public class Memoria extends SegDatos {
    
    private static Memoria mem=new Memoria();
    private int posMem=(int)INICIO_MEMORIA.getDec();
    private Vector v_variables=new Vector();
    private Vector v_direcciones=new Vector();
    private Vector v_datos=new Vector();
    private Vector v_vari_base=new Vector();
    private Vector v_direc_base=new Vector();
    private Vector v_datos_base=new Vector();
    private boolean es_ascii=false;
    
/**Constructor de Memoria
 *@param Sin parametros
 **/
    private Memoria() {}
    
/**Metodo estatico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Memoria memoria
 **/  
    public static Memoria getMemoria()
    {    	
        return mem;
    }
    
/**Metodo que indica la ultima posicion ocupada de la memoria
 *@param Sin parametros
 *@return int ultima posicion ocupada
 **/  
    public int obtPosMem()
    {
        return posMem;
    }
    
/**Metodo que anhade posiciones de memoria ocupadas
 *@param int desplazamiento
 *@return void
 **/  
    public void movPosMem(int desp)
    {
        posMem=obtPosMem()+desp;
   }
     
/**Metodo que anhade datos a la memoria
 *@param Vector datos
 *@return void
 **/  
    public void push(Vector param)
    {        
    
        Palabra p=new Palabra("p");
        int punt_mem=obtPosMem();     
        int posicion=0;
    
        v_variables.addElement(new String((String)param.elementAt(0)).trim());
    
        for (posicion=0;posicion<datos.length;posicion++){
            if(param.elementAt(1).equals(datos[posicion]))
                break;
        }
    
        String dato,s,s1,s2;
        int direcc;
        int desplaza;
        switch(posicion)
        {
           case ASCIIZ:
           case ASCII:      
               es_ascii=true;
               v_direcciones.addElement(new Palabra(obtPosMem()));
                              
               StringBuffer bt;
               for(int j=2;j<param.size();j++)
                {   
                    direcc=(obtPosMem()/4)*4;   /*el anterior multiplo de 4*/
                    desplaza=obtPosMem()%4;                    
 
                    if(desplaza!=0)                    
                    {
                        bt=new StringBuffer((String)param.elementAt(j));       
                         setByte(obtPosMem()-desplaza, desplaza,new Palabra(bt.toString()));
                        movPosMem(1);                     
                    }
                    else
                    {
                        bt=new StringBuffer();                        
                        int i=0;
                        while(((i+j)<param.size()) && (bt.length()<4))
                        {
                            bt.append((String)param.elementAt((i+j)));
                            i++;
                        }    
                        j=j+i-1;
                        setPalabra(obtPosMem(), 0, new Palabra(bt.toString()));
                        movPosMem(bt.length());
                    }                                       
                }
               
                break;
       
            case WORD:
            	
                es_ascii=false;
                desplaza=obtPosMem()%4;
              
                if (desplaza!=0)
                    movPosMem(4-desplaza);
                direcc=obtPosMem();
              
    
                v_direcciones.addElement(new Palabra(direcc));
                for(int j=2;j<param.size();j++)
                {              
    
                    if(((String)param.elementAt(j)).startsWith("0x"))
                    {
    
                        dato=((String)param.elementAt(j)).substring(2,((String)param.elementAt(j)).length());                  
                        setPalabra(obtPosMem(), 0, new Palabra(dato,false)); //el false --> extension de signo                     
                        movPosMem(4);
                    }
                    else
                    {
    
                        dato=(String)param.elementAt(j);
                        long datos=Long.parseLong(dato);                      
                        setPalabra(obtPosMem(), 0, new Palabra(datos));                     
                        movPosMem(4);                    
                    }                                                                                   
                }
              
                break;
                
            case FLOTANTE:
                es_ascii=false;
                desplaza=obtPosMem()%4; 
                if (desplaza!=0)
                    movPosMem(4-desplaza);
                direcc=obtPosMem();
                v_direcciones.addElement(new Palabra(direcc));
                for(int j=2;j<param.size();j++)
                {              
                        dato=(String)param.elementAt(j);
                        Double de=Double.valueOf(dato);
                        double datos=de.doubleValue();
                        setPalabra(obtPosMem(), 0, new Palabra(datos));                     
                        movPosMem(4);                   
                }
                break;
                
            case DOBLE:
                es_ascii=false;
                desplaza=obtPosMem()%4; 
                if (desplaza!=0)
                    movPosMem(4-desplaza);
                direcc=obtPosMem();
                v_direcciones.addElement(new Palabra(direcc));
                for(int j=2;j<param.size();j++)
                {              
                        dato=(String)param.elementAt(j);
                        Double de=Double.valueOf(dato);
                        s=p.dec_a_float(de,"DP");
                        s1=s.substring(0,32);
                        double dato1=p.float_a_dec(s1);
                        setPalabra(obtPosMem(), 0, new Palabra(dato1));                     
                        movPosMem(4); 
                        
                        s2=s.substring(32,s.length());
                        double dato2=p.float_a_dec(s2);
                        setPalabra(obtPosMem(), 0, new Palabra(dato2));                     
                        movPosMem(4); 
                }
                break;
                 
            case SPACE:
                es_ascii=true;
                int reserva=((Integer)param.elementAt(2)).intValue();                           
                int mem_antes=obtPosMem();
                Palabra cero=new Palabra(0);            
                v_direcciones.addElement(new Palabra(obtPosMem()));                              
                int desp=0;
                while((obtPosMem()-mem_antes)<reserva)
                {   
                    direcc=(obtPosMem()/4)*4;   /*el anterior multiplo de 4*/
                    desplaza=obtPosMem()%4;                    
                    desp=reserva-(obtPosMem()-mem_antes);
                    if(desplaza!=0)                    
                    {
                        setByte(obtPosMem()-desplaza, desplaza, cero);
                        movPosMem(1);                     
                    }
                    else
                    {
                         setPalabra(obtPosMem(), 0, cero);
                        if(desp<4)
                            movPosMem(desp);
                        else
                            movPosMem(4);
                        
                    }                                                                       
                }                
                break;
        }
        setChanged();
        notifyObservers(visualizar());       
    }
    
/**Metodo que proporciona informacion para visualizar la memoria
 *@param Sin parametros 
 *@return Vector informacion
 **/    
    public Vector visualizar()
    {
        Vector la_mem=new Vector();
        Vector aux;
    
        for (int i=0;i<v_datos.size();i=i+4)
        {
            aux=new Vector();
            aux.addElement((new Palabra(INICIO_MEMORIA.getDec()+i*4)).getHex());
            for (int j=0; ((j<4) && ((i+j)<v_datos.size())); j++)            
                aux.addElement(((Palabra)v_datos.elementAt(i+j)).getHex());
            la_mem.addElement(aux);
        }
    
        return la_mem;
    }
        
/**Metodo para inicializar la clase
 *@param Sin parametros 
 *@return void
 **/   
    public void inicializar()
    {
        v_variables=new Vector();
        v_direcciones=new Vector();
        v_datos=new Vector();
        posMem=(int)INICIO_MEMORIA.getDec();
        setChanged();
        notifyObservers(visualizar());
    }
        
/**Metodo para inicializar la clase con el contenido despues de almacenar solo las variables definidas en el codigo
 *@param Sin parametros 
 *@return void
 **/   
    public void inicializando()
    {
       v_variables=(Vector)v_vari_base.clone(); 
       v_direcciones=(Vector)v_direc_base.clone();
       v_datos=(Vector)v_datos_base.clone();       
       posMem=(int)(new Palabra((int)(INICIO_MEMORIA.sumar((v_datos.size()-1)*4).getDec()))).getDec();
       setChanged();
       notifyObservers(visualizar());
    }
        
/**Metodo para copiar el estado de la memoria al almacenar solo las variables definidas en el codigo
 *@param Sin parametros 
 *@return void
 **/   
    public void memBase()
    {
        v_vari_base=(Vector)v_variables.clone();
        v_direc_base=(Vector)v_direcciones.clone();
        v_datos_base=(Vector)v_datos.clone();
    }
    
/**Metodo que indica el tamanho de la memoria 
 *@param Sin parametros 
 *@return int tamanho
 **/   
    public int tamanho()
    {
        return v_variables.size();
    }
    
    
/**Metodo que indica si la variable esta almacenada en la memoria 
 *@param String variable
 *@return boolean
 **/   
    public boolean estaVariable(String var)
    {
        if(v_variables.size()==0)
            return false;
        for (int i=0;i<v_variables.size();i++)
        {
            if(var.equals((String)v_variables.elementAt(i)))
                return true;
        }
        return false;
    }
    
/**Metodo que induca el numero de variables almacenadas en la memoria
 *@param Sin parametros 
 *@return int numero de variables
 **/   
    public int num_variables()
    {
        return v_datos.size();
    }
 
        
/**Metodo que devuelve la palabra solicitada
 *@param long direccion, int desplazamiento
 *@return Palabra palabra
 **/ 
    public Palabra getVariable(long direccion,int desp)
    {
        int indice=(int)(direccion-INICIO_MEMORIA.getDec()+desp)/4;
        return (Palabra)v_datos.elementAt(indice);
    }
        
/**Metodo que devuelve la palabra solicitada
 *@param String variable
 *@return Palabra palabra
 **/ 
    public Palabra getVariable(String variable)
    {
        return (getVariable(getDireccion(variable),0));
    }
        
/**Metodo que devuelve la direccion de una variable
 *@param String variable
 *@return long direccion
 **/ 
    public long getDireccion(String variable)
    {
        int i;
        for(i=0;i<v_variables.size();i++)
        {
            if(((String)v_variables.elementAt(i)).equals(variable))
                break;
        }
        return ((Palabra)v_direcciones.elementAt(i)).getDec();
    }
    
/**Metodo que devuelve una palabra que contiene el byte solicitado en el byte menos significativo
 *@param long direccion, int desplazamiento
 *@return Palabra solicitada
 **/  
    public Palabra getByte(long direccion, int desp)
    {        
        Palabra pala=getVariable(direccion, desp);
       String palabra=pala.getHex();
       desp=((int)(direccion+desp))%4;
	           palabra=palabra.substring(2,palabra.length());
        palabra=palabra.substring(palabra.length()-2-2*(desp%4), palabra.length()-2*(desp%4));    
        Palabra pal=new Palabra(palabra,true); 
         return pal;
    }
    
/**Metodo que devuelve una palabra que contiene el byte solicitado en el byte menos significativo
 *@param String variable
 *@return Palabra solicitada
 **/  
     public Palabra getByte(String variable)
    {
        String palabra=(getVariable(getDireccion(variable),0)).getHex();
        palabra=palabra.substring(2,palabra.length());
        Palabra pal=new Palabra(palabra.substring(palabra.length()-2,palabra.length()),true);
        return pal;
    }
       
/**Metodo que coloca una palabra en la posicion indicada
 *@param long direccion, int desplazamiento, Palabra palabra
 *@return void
 **/  
    public void setPalabra(long direc, int desp, Palabra palabra)
    {

    
        int indice=(int)(direc-INICIO_MEMORIA.getDec()+desp)/4;        
        if (indice<v_datos.size())  
        {
            v_datos.remove(indice);
            v_datos.add(indice, palabra);    
        }
        else
            v_datos.addElement(palabra);        
            
        setChanged();
/**********************************************
*********notifyObservers(visualizar());********        
***********************************************/
    }
       
/**Metodo que anhade un texto a la memoria
 *@param String texto
 *@return String 
 **/  
    public String setTexto(String texto)
    {

        if((obtPosMem()%4)==0)
        {
            if(es_ascii)
                movPosMem(1);
            else
                movPosMem(4);
        }
        v_direcciones.addElement(new Palabra(obtPosMem()));          
           String re=new Palabra(obtPosMem()).getHex();
           
               StringBuffer bt;
               for(int j=0;j<texto.length();j++)
                {   
                    int direcc=(obtPosMem()/4)*4;   /*el anterior multiplo de 4*/
                    int desplaza=obtPosMem()%4;                    
 
                    if(desplaza!=0)                    
                    {
                        bt=new StringBuffer(texto.substring(j,j+1));       
                        setByte(obtPosMem()-desplaza, desplaza,new Palabra(bt.toString()));
                        movPosMem(1);                     
                    }
                    else
                    {
                        bt=new StringBuffer();                        
                        int i=0;
                        while(((i+j)<texto.length()) && (bt.length()<4))
                        {
                            bt.append(texto.substring(i+j,i+j+1));
                            i++;
                        }    
                        j=j+i-1;
                        setPalabra(obtPosMem(), 0, new Palabra(bt.toString()));
                        movPosMem(bt.length());
                    }                                       
                  }
                setChanged();
/**********************************************
*********notifyObservers(visualizar());********        
***********************************************/
               return re;
        
    }
    
    
    

    public boolean setString(long direc, Vector<String> string){
    	/*No es el desplazamiento del alineamiento, eso lo puede calcular setByte
    	 * es el desplazamiento que hay que anhadir al deplazar cada byte*/
    	int desplaz=0;
    	int indice=(int)(direc-INICIO_MEMORIA.getDec())/4;
    	int desp= (int)(direc-INICIO_MEMORIA.getDec())%4;
    	int posMem=(int)(direc-INICIO_MEMORIA.getDec());
    	int resMem=v_datos.size()*4;//v_datos indica la cantidad de memoria reservada
    	if(!this.posValida(direc, 0))
    		if(!((indice==v_datos.size()) && (desp==0)))
    		return false;
    	for(int i=0;i<string.size();i++){
    		if(posMem>=resMem){
    			break;
    		}
    		setByte(direc, desplaz, new Palabra(string.elementAt(i)));
    		desplaz++;
    		posMem++;
    	}
    	notifyObservers(visualizar());
    	return true;
    }
    
         
/**Metodo que coloca un byte en la posicion indicada
 *@param long direccion, int desplazamiento, Palabra palabra
 *@return void
 **/    
    public void setByte(long direc, int desp, Palabra palabra)
    {
        int indice=(int)(direc-INICIO_MEMORIA.getDec()+desp)/4;
        desp=(desp+(int)direc)%4;        
        String valor_final;
        if(indice==v_datos.size()){
        	v_datos.addElement(new Palabra("0x00000000", true));
        }
        Palabra valor_mem=(Palabra)v_datos.elementAt(indice);                
        String valor_fin=valor_mem.getHex().substring(2,valor_mem.getHex().length());
        String pal=palabra.getHex().substring(2,valor_mem.getHex().length());
        valor_final=valor_fin.substring(0,valor_fin.length()-2-2*desp);
        valor_final+=pal.substring(pal.length()-2, pal.length());
        valor_final+=valor_fin.substring(valor_fin.length()-2*desp,valor_fin.length());
        v_datos.remove(indice);
        v_datos.add(indice,new Palabra(valor_final, true));
        setChanged();
/**********************************************
*********notifyObservers(visualizar());********        
***********************************************/
    } 
       
/**Metodo que indica si una posicion es valida
 *@param long direccion, int desplazamiento
 *@return boolean
 **/  
    public boolean posValida(long direc, int desp)
    {
        int indice=(int)(direc-INICIO_MEMORIA.getDec()+desp)/4;
        if (indice<0){
        	return false;
        }
        if(indice<v_datos.size())
            return true;
        return false;        
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
