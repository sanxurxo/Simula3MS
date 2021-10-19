

package ensamblador.datos;

import java.util.Vector;

import ensamblador.instrucciones.Instruccion;

/**Codigo.java
 *Clase que almacena las instrucciones de un codigo
 **/
public class Codigo implements Tipos{
    Vector instrucciones=new Vector();
    private static Codigo codigo=new Codigo();
    private Palabra direc_base=new Palabra("00400000",true);
    
/**Constructor de Codigo
 *@param Sin parametros
 **/
    private Codigo() {};
   
 /**Metodo estatico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Codigo codigo
 **/   
    public static Codigo getInstacia()
    {
        return codigo;
    }
    
 /**Metodo que anhade una instruccion al Codigo 
 *@param Instruccion instruccion
 *@return void
 **/   
    public void anhadir(Instruccion inst)
    {
        instrucciones.addElement(inst);
    }
 
 /**Metodo que devuelve el Pc asociado a una instruccion 
 *@param Instruccion instruccion
 *@return int valor del Pc
 **/   
    public int getPcInst(Instruccion inst)
    {
         for(int i=0; i<instrucciones.size();i++)  
             if(((Instruccion)instrucciones.elementAt(i)).equals(inst))
                return((int)INICIO_PC.getDec()+i*4);
         return -1;        
    }
    
 /**Metodo que devuelve la instruccion asociada a un Pc 
 *@param int valor del Pc
 *@return Instruccion instruccion
 **/
    public Instruccion obtener(int pc)
    {
        pc=(int)(pc-direc_base.getDec())/4;
        return (Instruccion)instrucciones.elementAt(pc);
    }
    
 /**Metodo que devuelve una instruccion a partir de su posicion en el codigo 
 *@param int posicion
 *@return Instruccion instruccion
 **/
    public Instruccion obt_pos(int pos)
    {
       return (Instruccion)instrucciones.elementAt(pos);
    }
      
 /**Metodo que indica cuantas instruciones hay
 *@param Sin parametros
 *@return int numero de instrucciones
 **/
    public int tamanho()
    {
        return instrucciones.size();
    }
    
 /**Metodo que devuelve el Pc asociado a una instruccion indicando su posicion en el codigo 
 *@param int indice
 *@return String valor del PC
 **/
    public String obtenerDirec(int indice)
    {
        String palHex=Integer.toHexString((int)(direc_base.getDec()+indice*4));
        String zero="00000000";
        if(palHex.length()!=8)
            palHex=zero.substring(0,8-palHex.length())+palHex;    
        return palHex;
    }
    
 /**Metodo que inicializa la unica instancia de codigo 
 *@param Sin parametros
 *@return void
 **/
    public void inicializar()
    {
        instrucciones.clear();
    }
   
/**Metodo que proporciona informacion para visualizar el Codigo
 *@param Sin parametros 
 *@return String codigo
 **/    
    public String visualizar()
    {
        StringBuffer texto=new StringBuffer();
        for(int i=0; i<instrucciones.size();i++)        
            texto.append("   [").append("0x").append(obtenerDirec(i)).append("]").append("\t    ").append(obt_pos(i).getCodificacion()).append("   \t").append(obt_pos(i).toString()).append("\t\n");                  
        return texto.toString();
    }
    
 /**Metodo que devuelve la lista de los Pc en que hay instrucciones
 *@param Sin parametros
 *@return String[] PCs
 **/    
    public String[] getPc()
    {
        String pcs[]=new String[instrucciones.size()];
        for(int i=0; i<instrucciones.size();i++)
            pcs[i]=("0x").concat(obtenerDirec(i));
        return pcs;        
    }

 /**Metodo que indica si un numero de Pc es el ultimo valido
 *@param int numero de Pc
 *@return boolean
 **/    
    public boolean esUltima(int pc)
    {
        pc=(int)(pc-direc_base.getDec())/4;
        if(pc>=this.tamanho())
            return true;
        return false;
    }
    
 /**Metodo que devuelve la posicion de una instruccion
 *@param Instruccion instruccion
 *@return int posicion
 **/    
      public int posInst(Instruccion inst)
    {
        for(int i=0; i<instrucciones.size();i++)  
             if(((Instruccion)instrucciones.elementAt(i)).equals(inst))
                return i;
        return -1;        
    }

      
      /**Metodo que indica si un PC esta fuera
       * del rango valido
       *@param int numero de Pc
       *@return boolean
       **/
      public boolean estaFueraRango(int pcActual){    	  
          int pc=(int)(pcActual-direc_base.getDec())/4;
          if((pc>=this.tamanho()) || (pcActual<direc_base.getDec())){
              return true;
          }
          return false;
      }
}
