
package ensamblador.instrucciones;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.Vector;

import ensamblador.datos.Etiquetas;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Tipos;
import ensamblador.datos.Lenguaje;
import ensamblador.registros.*;

/**Instruccion.java
 *Clase abstracta que recoge las caratersticas comunes de todas las instrucciones
 **/
public abstract class Instruccion implements ensamblador.datos.Tipos{
    
	protected Lenguaje lenguaje = Lenguaje.getInstancia();
    protected StringBuffer direccion=new StringBuffer(lenguaje.getString("rutaMonociclo"));
    protected StringBuffer direccion_multi=new StringBuffer(lenguaje.getString("rutaMulticiclo"));
    protected StringBuffer string=new StringBuffer();
    protected String imagenes;
    protected String ceros="00000000000000000000000000000000";
    protected boolean boolean_pc;
    protected boolean boolean_dato;
    protected boolean boolean_datos;
    protected boolean boolean_mem;
    protected boolean boolean_pila;
    protected boolean boolean_hi_lo;
    protected boolean boolean_status;
    protected Palabra cambio_pc=new Palabra(0);
    protected Palabra cambio_dato=new Palabra(0);
    protected StringBuffer imagen_etapa=new StringBuffer();
    private int num_etapa=1;
    protected int tipoFU=Tipos.INTEGER_FU;
    protected int tipoER=Tipos.FP_ADD_ER;
    protected String operacion="Operacion";
    protected Registro r[]=new Registro[0];
    protected boolean esSalto=false;
    protected boolean saltoTomado=false;
    protected boolean instruccionFP=false;
    protected boolean instruccionDouble=false;
    protected Palabra rs=null;
    protected Palabra rt=null;
    protected Hashtable<String, Palabra> resultTomasulo=new Hashtable<String, Palabra>();
/**Metodo que informa si un numero es un numero de registro valido
 *@param String numero
 *@return boolean  
 **/
    public static boolean esNumReg(String numero)
    {        
        int posicion=-1;
        try
        {        
            posicion=java.lang.Integer.parseInt(numero);           
        }catch(NumberFormatException e){return false;}
        
        if((posicion>=0) && (posicion<=31))
            return true;
        return false;
    }
 
/**Metodo que informa si un numero es un numero de registro valido
 *@param String numero
 *@return boolean  
 **/
    public static boolean esNumRegPar(String numero)
    {        
        int posicion=-1;
        try
        {        
            posicion=java.lang.Integer.parseInt(numero);           
        }catch(NumberFormatException e){return false;}
        
        if((posicion>=0) && (posicion<=31))
           if(posicion%2==0)
               return true;
           else 
               return false;
        return false;
    }
    
/**Metodo que informa si un String es un registro valido
 *@param String registro
 *@return boolean  
 **/    
    public static boolean esRegistro(String regis)
    {
        String rgs=regis;
        boolean esRegistro=false;
        StringTokenizer st=new StringTokenizer(rgs);
        String reg=st.nextToken();        
        int posicion=-1;
        
        if(reg.startsWith("$") && (reg.length()==3 || reg.length()==5 || reg.length()==2))
        {
            if(reg.length()==2)
                try
                {                    
                    Integer.parseInt(reg.substring(1,2));
                }catch(NumberFormatException e){return false;}
                
                
            if(esNumReg(reg.substring(1,reg.length())))                          
                return true;
            else{
                String rg=new String();
                rg=rg.valueOf(reg.charAt(2));
                if(reg.length()==3)
                {
                    try
                    {
                        posicion=java.lang.Integer.parseInt(rg);
                    }catch(NumberFormatException e){posicion=-1;}
                }
            
                for(int i=0; i<registros.length;i++)
                {
                    if(reg.charAt(1)==registros[i])
                    {
                    
                        switch(i)
                        {
                            case REG_Z:
                                if(reg.equals("$zero"))
                                    esRegistro=true;                            
                                break;
                            
                            
                            case REG_A:
                                if(reg.length()==3) 
                                {                                                                                        
                                    if(reg.endsWith("t") || ((posicion>=0) &&  (posicion<4)))
                                        esRegistro=true;
                                }
                                break;
                                
                            case REG_V:
                                if(reg.length()==3) 
                                {                                                          
                                 if((posicion==0) ||  (posicion==1))
                                        esRegistro=true;
                                 }
                                 break;
                            case REG_T:
                                if(reg.length()==3) 
                                {                                                          
                                     if((posicion>=0) &&  (posicion<10))
                                        esRegistro=true;
                                }
                                break;
                            case REG_S:
                                if(reg.length()==3) 
                                {                                                          
                                   if(reg.endsWith("p") || ((posicion>=0) &&  (posicion<8)))
                                        esRegistro=true;
                                }
                                break;                       
                            case REG_R:
                                if(reg.length()==3)
                                {
                                    if(reg.equals("$ra"))
                                        esRegistro=true;
                                }
                                break;

                            case REG_F:
                                if (reg.equals("$fp"))
                                    esRegistro=true;
                                break;

                            case REG_K:
                            	 if(reg.length()==3) 
                                 {                                                          
                                      if((posicion==0) ||  (posicion==1))
                                         esRegistro=true;
                                 }
                                 break;
                        }
                    }
                }
            }
        }       
        return esRegistro;
    }
     
/**Metodo que informa si un String es un registro de punto flotante valido
 *@param String registro
 *@return boolean  
 **/    
    public static boolean esRegistroF(String regis)
    {
        String rgs=regis;
        boolean esRegistro=false;
        StringTokenizer st=new StringTokenizer(rgs);
        String reg=st.nextToken();        
        int posicion=-1;
        
        if(reg.startsWith("$") && (reg.length()==3 || reg.length()==2 || reg.length()==4))
        {
            if(reg.length()==2)
                try
                {                    
                    Integer.parseInt(reg.substring(1,2));
                }catch(NumberFormatException e){

return false;}
                
                
            if(esNumReg(reg.substring(1,reg.length())))                          
                return true;
            else
            {
                String rg=new String();
                String rg2=new String();
                rg=rg.valueOf(reg.charAt(2));
                if(reg.length()==3)
                {
                    try
                    {
                        posicion=java.lang.Integer.parseInt(rg);
                    }catch(NumberFormatException e){posicion=-1;}
                }
                if(reg.length()==4)
                {
                    rg2=rg+rg.valueOf(reg.charAt(3));
                    try
                    {
                        posicion=java.lang.Integer.parseInt(rg2);
                    }catch(NumberFormatException e){posicion=-1;}
                }
               
                    if(reg.charAt(1)==registros[6])
                    {
                        if(reg.length()==3||reg.length()==4)
                        {
                            if((posicion>=0) &&  (posicion<32))
                                esRegistro=true;
                        }
                    }
            }
        }       
        return esRegistro;
    }
     
 /**Metodo que informa si un String es un registro de punto flotante valido
 *@param String registro
 *@return boolean  
 **/    
    public static boolean esRegistroFPar(String regis)
    {
        String rgs=regis;
        boolean esRegistro=false;
        StringTokenizer st=new StringTokenizer(rgs);
        String reg=st.nextToken();        
        int posicion=-1;
        int i;
        
        if(reg.startsWith("$") && (reg.length()==3 || reg.length()==2 || reg.length()==4))
        {
            if(reg.length()==2)
                try
                {                    
                    Integer.parseInt(reg.substring(1,2));
                   
                }catch(NumberFormatException e){

return false;}
                
                
            if(esNumRegPar(reg.substring(1,reg.length())))                          
                return true;
            else
            {
                String rg=new String();
                String rg2=new String();
                rg=rg.valueOf(reg.charAt(2));
                if(reg.length()==3)
                {
                    try
                    {
                        posicion=java.lang.Integer.parseInt(rg);
                    }catch(NumberFormatException e){posicion=-1;}
                }
                if(reg.length()==4)
                {
                    rg2=rg+rg.valueOf(reg.charAt(3));
                    try
                    {
                        posicion=java.lang.Integer.parseInt(rg2);
                    }catch(NumberFormatException e){posicion=-1;}
                }
               
                if(reg.charAt(1)==registros[6])
                {
                    if(reg.length()==3||reg.length()==4)
                    {
                        if((posicion>=0) &&  (posicion<32))
                            if(posicion%2==0)
                                esRegistro=true;
                    }
                }
            }
        }       
        return esRegistro;
    }
    
/**Metodo que informa si un String es una variable
 *@param String variable
 *@return boolean  
 **/
    public static boolean esVariable(String variable)
    {
        if(Memoria.getMemoria().tamanho()==0)
            return false;
        return Memoria.getMemoria().estaVariable(variable);       
    }

 /**Metodo que relaciona un registro a una instruccion
 *@param String registro
 *@return Registro que se relaciona  
 **/
    public Registro relacionaReg(String reg)
    {
         Registro r;
         int i;
         for(i=0;i<registro.length;i++)
            if(reg.equals(registro[i]))
                break;       
         return relacionaReg(i);
     }                  

 /**Metodo que relaciona un registro a una instruccion
 *@param String registro
 *@return Registro que se relaciona  
 **/
    public Registro relacionaRegF(String reg)
    {
         Registro r;
         int i;
         for(i=0;i<regFlotante.length;i++)
            if(reg.equals(regFlotante[i]))
                break; 
         return relacionaRegF(i);
     }                  
    
/**Metodo que relaciona un registro a una instruccion
 *@param int registro
 *@return Registro que se relaciona  
 **/    
    public Registro relacionaReg(int posicion)
    {
        Registro r;
        switch(posicion)
        {
            case REG_ZERO:
                r=Zero.getRegistro();
                break;
            case REG_AT:
                r=At.getRegistro();
                break;
            case REG_V0:
                r=V0.getRegistro();
                break;
            case REG_V1:
                r=V1.getRegistro();
                break;
            case REG_A0:
                r=A0.getRegistro();
                break;
            case REG_A1:
                r=A1.getRegistro();
                break;
            case REG_A2:
                r=A2.getRegistro();
                break;
            case REG_A3:
                r=A3.getRegistro();
                break;
            case REG_T0:
                r=T0.getRegistro();
                break;
            case REG_T1:
                r=T1.getRegistro();
                break;
            case REG_T2:
                r=T2.getRegistro();
                break;
            case REG_T3:
                r=T3.getRegistro();
                break;
            case REG_T4:
                r=T4.getRegistro();
                break;
            case REG_T5:
                r=T5.getRegistro();
                break;
            case REG_T6:
                r=T6.getRegistro();
                break;
            case REG_T7:
                r=T7.getRegistro();
                break;
            case REG_S0:
                r=S0.getRegistro();
                break;
            case REG_S1:
                r=S1.getRegistro();
                break;
            case REG_S2:
                r=S2.getRegistro();
                break;
            case REG_S3:
                r=S3.getRegistro();
                break;
            case REG_S4:
                r=S4.getRegistro();
                break;
            case REG_S5:
                r=S5.getRegistro();
                break;
            case REG_S6:
                r=S6.getRegistro();
                break;
            case REG_S7:
                r=S7.getRegistro();
                break;
            case REG_T8:
                r=T8.getRegistro();
                break;
            case REG_T9:
                r=T9.getRegistro();
                break;
            case REG_K0:
            	r=K0.getRegistro();
            	break;
            case REG_K1:
            	r=K1.getRegistro();
            	break;
            case REG_SP:
                r=Sp.getRegistro();
                break;
            case REG_FP:
                r=FP.getRegistro();
                break;
           default:
            case REG_RA:
                r=Ra.getRegistro();
                break;               
        }       
        return r;
    }
    
 /**Metodo que relaciona un registro a una instruccion
 *@param int registro
 *@return Registro que se relaciona  
 **/    
    public Registro relacionaRegF(int posicion)
    {
        Registro r;
        switch(posicion)
        {
            case REG_F0:
                r=F0.getRegistro();
                break;
            case REG_F1:
                r=F1.getRegistro();
                break;
            case REG_F2:
                r=F2.getRegistro();
                break;
           case REG_F3:
                r=F3.getRegistro();
                break;     
            case REG_F4:
                r=F4.getRegistro();
                break;
            case REG_F5:
                r=F5.getRegistro();
                break;    
            case REG_F6:
                r=F6.getRegistro();
                break;
            case REG_F7:
                r=F7.getRegistro();
                break;    
            case REG_F8:
                r=F8.getRegistro();
                break;
            case REG_F9:
                r=F9.getRegistro();
                break;    
            case REG_F10:
                r=F10.getRegistro();
                break;
            case REG_F11:
                r=F11.getRegistro();
                break;    
            case REG_F12:
                r=F12.getRegistro();
                break;
            case REG_F13:
                r=F13.getRegistro();
                break;    
            case REG_F14:
                r=F14.getRegistro();
                break;
            case REG_F15:
                r=F15.getRegistro();
                break;    
            case REG_F16:
                r=F16.getRegistro();
                break;
            case REG_F17:
                r=F17.getRegistro();
                break;    
            case REG_F18:
                r=F18.getRegistro();
                break;
            case REG_F19:
                r=F19.getRegistro();
                break;    
            case REG_F20:
                r=F20.getRegistro();
                break;
            case REG_F21:
                r=F21.getRegistro();
                break;    
            case REG_F22:
                r=F22.getRegistro();
                break;
            case REG_F23:
                r=F23.getRegistro();
                break;    
            case REG_F24:
                r=F24.getRegistro();
                break;
            case REG_F25:
                r=F25.getRegistro();
                break;    
            case REG_F26:
                r=F26.getRegistro();
                break;
            case REG_F27:
                r=F27.getRegistro();
                break;    
            case REG_F28:
                r=F28.getRegistro();
                break;
             case REG_F29:
                r=F29.getRegistro();
                break;
             case REG_F30:
                r=F30.getRegistro();
                break;   
            default:
            case REG_F31:
                r=F31.getRegistro();
                break;
        }       
        return r;
    }
    
/**Metodo que comprueba si un String es una etiqueta definida
 *@param String etiqueta Etiquetas etiquetas
 *@return boolean
 **/    
    public static boolean esEtiqueta(String etiqueta, Etiquetas etiquetas)
    {
        int i=0;
        Vector nombres=etiquetas.getNombres();
        while((i<nombres.size()) && !((etiqueta.equals((String)nombres.elementAt(i))) || (etiqueta.equals(((String)nombres.elementAt(i))+":"))))
                    i++;
        
        if (i==nombres.size())
            return false;
        return true;
    }
    
/**Metodo que devuelve el String asociado a la instruccion
 *@param Sin parametros
 *@return String asociado a la instruccion  
 **/
    public String toString()
    {
        return string.toString();
    }
    
/**Metodo que devuelve el String correspondiente a la imagen de la instruccion
 *@param int posicion de la instruccion en el codigo
 *@return String asociado a la imagen de la instruccion instruccion  
 **/
     public String visualizar(int pos)
     {
         StringBuffer direc=new StringBuffer();
         return direc.append(direccion).append(imagenes).toString();
     }
     
/**Metodo que asocia una imagen a la instruccion
 *@param String imagen
 *@return void 
 **/
     public void inicIma(String img)
     {
         imagenes=img;
     }
     
/**Metodo que devuelve el numero de registro
 *@param char pos
 *@return int numero de registro 
 **/
     public int obtNumReg(char pos)
     {
       int posicion=0;
       if(Character.isDigit(pos))
            posicion=java.lang.Character.getNumericValue(pos);
       else
       {
           if(pos=='p')               
                posicion=10;
       }
       return posicion;
     }
  
/**Metodo que devuelve el campo Op de la instruccion
 *@param int campo Op
 *@return String asociado al campo Op  
 **/
     public String setOp(int cdOp)
    {
        String cod=Integer.toBinaryString(cdOp);
        while(cod.length()<6)
            cod="0"+cod;
        return cod;
    }
    
/**Metodo que devuelve el campo de cualquiera de los registros de la instruccion
 *@param int campo Registro
 *@return String asociado al campo Registro  
 **/
    public String setR(int cdR)
    {
        String cod=Integer.toBinaryString(cdR);
        while(cod.length()<5)
            cod="0"+cod;
        return cod;
    }
   
/**Metodo que pasa de binario a hexadecimal
 *@param String binario
 *@return String hexadecimal  
 **/
    public String bin_a_hex(String bin)
    {
        long codigo=Long.parseLong(bin, 2);        
        return (new Palabra(codigo)).getHex();
    }
    
    
/**Metodo abstracto que realiza la ejecucion completa de la instruccion 
 *@param Sin parametros
 *@return int error 
 **/
    public abstract int ejecutar();
    
/**Metodo abstracto que devuelve la codificacion de la instruccion  
 *@param Sin parametros
 *@return String codificacion 
 **/
    public abstract String getCodificacion();
    
/**Metodo abstracto que indica el numero de etapas en un camino de datos multiciclo de la instruccion  
 *@param Sin parametros
 *@return int numero de etapas 
**/
    public abstract int numEtapas(); 
    
 
/**Metodo abstracto que indica el numero asignado a esa instruccion  
 *@param Sin parametros
 *@return int numero de la instruccion 
**/
    public abstract int numero(); 
    
    
/**Metodo que realiza la ejecucion de la primera etapa de la instruccion en un camino de datos multiciclo  
 *@param Sin parametros
 *@return int numError
 **/
    public int etapa1()
    {
        imagen_etapa=new StringBuffer();
        boolean_pc=true;
        Pc.getRegistro().incrementar(4);
        //cambio_pc=Pc.getRegistro().getPalabra().sumar(4);        
        imagen_etapa.append(direccion_multi).append("etapa1");
        return -1;
    }
    
    
/**Metodo que indica que deben actualizarse los cambios  
 *@param Sin parametros
 *@return void
 **/
    public void cambiosVisibles()
    {
        
        if(boolean_pc)
        {
            boolean_pc=false;
            Pc.getRegistro().visualizaCambios();
        }                    
        
        if(this.boolean_dato==true)
        {
            boolean_dato=false;
            Registro r=this.regModificable();
            r.visualizaCambios();            
        }
        
        if(this.boolean_datos==true)
        {
            boolean_datos=false;
            Registro r=this.regModificable();
            Registro r2=relacionaRegF(r.numReg()+1);
            r.visualizaCambios();  
            r2.visualizaCambios();
        }
        
        if(boolean_mem)
        {
            boolean_mem=false;
            Memoria.getMemoria().visualizaCambios();
        }
        if(boolean_pila)
        {
            boolean_pila=false;
            Pila.getPila().visualizaCambios();
        }
        
        if(boolean_hi_lo)
        {
            boolean_hi_lo=false;
            Hi.getRegistro().visualizaCambios();
            Lo.getRegistro().visualizaCambios();
        }
        
        if(boolean_status)
        {
            boolean_status=false;
            Status.getRegistro().visualizaCambios();
        }
    }    
    
    
/**Metodo que establece los cambios  
 *@param boolean cambio
 *@return void
 **/
    public void setCambios(boolean cam)
    {
        boolean_pc=cam;
        boolean_dato=cam;
        boolean_datos=cam;
    }
    
    
/**Metodo que realiza la ejecucion de la primera etapa de la instruccion en un camino de datos multiciclo  
 *@param Sin parametros
 *@return int numError
 **/
    public int etapa2()
    {    
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa2");
        return -1;
    }
    
/**Metodo abstracto que realiza la ejecucion de la tercera etapa de la instruccion en un camino de datos multiciclo  
 **/
    public abstract int etapa3();
    
/**Metodo abstracto que realiza la ejecucion de la cuarta etapa de la instruccion en un camino de datos multiciclo  
 **/
    public abstract int etapa4();
    
/**Metodo abstracto que realiza la ejecucion de la quinta etapa de la instruccion en un camino de datos multiciclo  
 **/
    public abstract int etapa5();    
    
/**Metodo para visualizar una etapa en un camino de datos multiciclo  
 *@param Sin parametros
 *@return String imagen
 **/
    public String visualizarEtapa()
    {
        return imagen_etapa.toString();
    }
    
/**Metodo para establecer el numero de etapas de la instruccion en un camino de datos multiciclo  
 *@param int numero de etapas
 *@return void
 **/
    public void setEtapa(int et)
    {
        num_etapa=(et%(this.numEtapas()+1));
    }    
    
/**Metodo para conseguir el numero de etapas de la instruccion en un camino de datos multiciclo  
 *@param Sin parametros
 *@return int numero de etapas
 **/
    public int getEtapa()
    {
        return num_etapa;
    }
    
/**Metodo abstracto que devuelve la imagen asociada a cada etapa del camino de datos segmentado
 *@param int numero de etapa
 *@return String imagen  
 **/
    public abstract String imagenSegmentado(int etapa); 
    
/**Metodo abstracto que devuelve el color asociada a cada instruccion
 *@param Sin parametros
 *@return Color color  
 **/
    public abstract java.awt.Color colorSegm();
    
    
/**Metodo abstracto que devuelve el estilo asociado a cada instruccion
 *@param Sin parametros
 *@return int numero de estilo  
 **/
    public abstract int estilo();
    
/**Metodo abstracto que indica si hay riesgo para la ejecucion de una etapa del camino de datos segmentado
 *@param Sin parametros 
 *@return boolean 
 **/
    public boolean hayRiesgo(){
    	return false;
    }
    
/**Metodo abstracto que para ejecutar la etapaIF del camino de datos segmentado
 *@param Sin parametros
 *@return int error  
 **/
    public abstract int ejecutarIF();
    
/**Metodo abstracto que para ejecutar la etapa ID del camino de datos segmentado
 *@param Sin parametros
 *@return int error  
 **/    
    public abstract int ejecutarID();
    
/**Metodo abstracto que para ejecutar la etapa EX del camino de datos segmentado
 *@param Sin parametros
 *@return int error  
 **/    
    public abstract int ejecutarEX();
    
/**Metodo abstracto que para ejecutar la etapa MEM del camino de datos segmentado
 *@param Sin parametros
 *@return int error  
 **/    
    public abstract int ejecutarMEM();
    
/**Metodo abstracto que para ejecutar la etapa WB del camino de datos segmentado
 *@param Sin parametros
 *@return int error  
 **/
    public abstract int ejecutarWB();    
    

    public abstract Registro regModificable();

    public abstract Registro getRegRS();
    
    public abstract Registro getRegRT();
    
	public int getTipoFU() {
		return tipoFU;
	}

	
	public int getTipoER() {
		return tipoER;
	}
	
	public String getOperacion() {
		return operacion;
	}

	public void setOperacion(String operacion) {
		this.operacion = operacion;		
	}

	public Registro[] getRegistrosLect(){
		return r;
	}

	public boolean isSalto(){
		return esSalto;
	}
	
	public boolean esSaltoTomado(){
        return saltoTomado;
    }
	public void setSaltoTomado(boolean bol){
		saltoTomado=bol;
	}

	public boolean isInstruccionFP() {
		return instruccionFP;
	}


public void setInstruccionFP(boolean instruccionFP) {
		this.instruccionFP = instruccionFP;
	}
	public boolean isInstruccionDouble() {
		return instruccionDouble;
	}
    

}
