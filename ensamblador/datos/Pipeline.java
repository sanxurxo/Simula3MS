
package ensamblador.datos;

import ensamblador.registros.Pc;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.Syscall;
import ensamblador.instrucciones.Nop;
import ensamblador.datos.UnidadAnticipacion;
import ensamblador.datos.UnidadDeteccionRiesgos;
import ensamblador.datos.Tipos;

import java.util.*;

/**Pipeline.java
 *Clase que almacena las instrucciones que se estan ejecutando en cada momento en un procesador segmentado
 **/
public class Pipeline implements Tipos{
    
    private Instruccion instrucciones[] = new Instruccion[5];
    private static Pipeline pipeline = new Pipeline();
    private Codigo codigo = Codigo.getInstacia();
    private UnidadAnticipacion unidadAnticipacion = UnidadAnticipacion.getInstancia();
    private UnidadDeteccionRiesgos unidadDeteccionRiesgos = UnidadDeteccionRiesgos.getInstancia();
    private int salto;
    private boolean saltoTomado;
    private int avance = 0;
    private Vector diagramaMult[];
    private Vector instMult;
    private Vector etapa;
        
    
/**Constructor de Pipeline
 *@param Sin parametros
 **/
    private Pipeline() {};
      
/**Metodo statico que devuelve la unica instancia de esta clase
 *@param Sin parametros
 *@return Pipeline pipeline
 **/ 
    public static Pipeline getInstacia()
    {
        return pipeline;
    }
    
/**Metodo que inicializa la unica instancia del pipeline
 *@param Sin parametros
 *@return void
 **/
    public void inicializar(int salto)     //salto_retardado-->0, salto_fijo-->1
    {
        this.salto = salto;
        instrucciones=new Instruccion[5];
        for(int i=0; i<5; i++)
            instrucciones[i] = new Nop();
        
        diagramaMult=new Vector[5];
        for(int i=0;i<5;i++)
            diagramaMult[i]=new Vector();
        
        instMult=new Vector();
        etapa=new Vector();
    }
    
    public Instruccion[] getInstrucciones()
    {
        return instrucciones;
    }
    
/**Metodo que devuelve la instruccion de una posicion del pipeline
 *@param int posicion
 *@return Instruccion instruccion
 **/
    public Instruccion getInstruccion(int pos)
    {
        return instrucciones[pos];
    }
    
/**Metodo que indica la posicion de una instruccion en el pipeline dada su direccion 
 *@param String direccion
 *@return int posicion
 **/
    public int posPipeline(String dir)
    {
        int dirIns=(int)(new Palabra(dir,true)).getDec();
        for (int i=0; i<instrucciones.length;i++)
        {
            if ((codigo.getPcInst(instrucciones[i]))==dirIns)           
                return i;                        
        }
        return -1;
    }
    
    //con salto fijo, si se toma el salto, se activa IF_Flush
    public boolean activarIFFlush(){
        if((salto==SALTO_FIJO) && (saltoTomado))
            return true;
        return false;
    }
    
/**Metodo para avanzar el pipeline
 *@param Instruccion instruccion que se anhade al pipeline
 *@return int tipo de avance
 **/
    public int avanzarPipeline(Instruccion inst, boolean saltoTomado)
    {
        //int numEtapa;
        avance = 0;
        this.saltoTomado=saltoTomado;

        //con salto fijo, si se toma el salto, se elimina la inst del hueco de retardo
        if(activarIFFlush())
            instrucciones[0]=new Nop();
        
        if (!(inst.toString().equals("syscall")))
        {
            if (!unidadDeteccionRiesgos.detener()) {
                for (int i=4; i>0; i--) {
                    instrucciones[i] = instrucciones[i-1];
                }
                instrucciones[0] = inst;
                avance = 0;       //avance=0-->avance de todo el pipeline
            }
            else {
                for (int i=4; i>2; i--) {
                    instrucciones[i] = instrucciones[i-1];
                }
                instrucciones[2] = new Nop();
                avance = 1;         //avance=1-->se inserta burbuja en etapa EX
            }
        }
        else
        {
            if (!estaParado()) {
                
                if (!unidadDeteccionRiesgos.detener()) {
                    for (int i=4; i>0; i--) {
                        instrucciones[i] = instrucciones[i-1];
                    }
                    instrucciones[0] = new Nop();
                    Pc.getRegistro().incrementar(-4);       //para detener el pipeline antes de ejecutar SYSCALL
                    Pc.getRegistro().visualizaCambios();
                    avance = 2;      //avance=2-->se inserta burbuja en etapa IF
                }
                else {
                    for (int i=4; i>2 ;i--) {
                        instrucciones[i] = instrucciones[i-1];
                    }
                    instrucciones[2] = new Nop();      
                    avance = 1;         //avance=1-->se inserta burbuja en etapa EX
                }
            }
            else {
                for(int i=4; i>0 ;i--) {
                    instrucciones[i]=instrucciones[i-1];
                }
                instrucciones[0]=new Nop();
                avance = 3;         //avance=3-->ejcucion de SYSCALL
            }
             
        }
        construirDiagMult(inst);
        return avance;
    }
    
           
/**Metodo que indica si el pipeline esta parado
 *@param Sin parametros
 *@return boolean
 **/
    public boolean estaParado()
    {
        int i;
        for(i=0;i<4;i++)
            if (!(instrucciones[i].toString().equals("nop")))
                break;
        if (i!=4)
            return false;
        return true;
    }
       
/**Metodo que añade una etapa para facilitar la representacion del diagrama multiciclo
 *@param int indice
 *@return String etapa
 **/    
    public String anhadirEtapa(int i)
    {
        switch(i)
        {
            case 0:
                return "burb";
            case 1:
                return "  IF";
            case 2:
                return "  ID";
            case 3:
                return " EX";
            case 4:
                return "MEM";
            case 5:
                return " WB";
            default:
                return "";
            /*case 6:
                return "";
            default:
                return "ERROR"; */   
        }
    }
    
    public void construirDiagMult(Instruccion inst)
    {
        int numEtapa=0;
        
        if(activarIFFlush())
        {
            instMult.set((instMult.size()-1), new Nop());
        }
        
        switch(avance)
        {
            case 0: //avance=0-->avance de todo el pipeline
                if(instMult.size()==5){
                    instMult.remove(0);
                    etapa.remove(0);
                    for(int i=0; i<instMult.size(); i++)                                                                  
                        diagramaMult[i]=diagramaMult[i+1];
                    diagramaMult[instMult.size()]=new Vector();
                }
                
                for(int i=0; i<instMult.size(); i++)               
                {
                    if(!((Instruccion)instMult.elementAt(i) instanceof Syscall))
                    {
                    //System.out.println("entramos en el bucle : "+i);
                    //diagramaMult[i]=diagramaMult[i+1];                    
                    numEtapa=((Integer)etapa.elementAt(i)).intValue()+1;
                    if(numEtapa<6){
                    etapa.set(i, new Integer(numEtapa));   
                    diagramaMult[i].addElement(anhadirEtapa(numEtapa));                    
                    }
                    }
                }
                               
                instMult.add(inst);                
                etapa.add(new Integer(1));                                
                diagramaMult[instMult.size()-1].addElement(anhadirEtapa(1));
                
                /*for(int i=0; i<instMult.size(); i++)
                {
                    System.out.println(((Instruccion)instMult.elementAt(i)).toString());
                    for(int j=0; j<diagramaMult[i].size();j++)
                        System.out.println((String)diagramaMult[i].elementAt(j));
                }*/
                
                break;
                
            case 1: //avance=1-->se inserta burbuja en etapa EX 
                for(int i=instMult.size()-1; i>(instMult.size()-3); i--)               
                {
                    if(!((Instruccion)instMult.elementAt(i) instanceof Syscall))
                    {
                    //numEtapa=((Integer)etapa.elementAt(i)).intValue()+1;                    
                    //etapa.set(i, new Integer(numEtapa));   
                    diagramaMult[i].addElement(anhadirEtapa(0));                    
                    }
                }

                for(int i=instMult.size()-3; i>=0; i--)               
                {
                    if(!((Instruccion)instMult.elementAt(i) instanceof Syscall))
                    {
                    numEtapa=((Integer)etapa.elementAt(i)).intValue()+1;                    
                    if(numEtapa<6){
                    etapa.set(i, new Integer(numEtapa));   
                    diagramaMult[i].addElement(anhadirEtapa(numEtapa));                    
                    }
                    }
                }
                break;
                
            case 2: //avance=2-->se inserta burbuja en etapa IF
                for(int i=0; i<instMult.size(); i++)               
                {
                    if(!((Instruccion)instMult.elementAt(i) instanceof Syscall))
                    {
                    //System.out.println("entramos en el bucle : "+i);
                    //diagramaMult[i]=diagramaMult[i+1];                    
                    numEtapa=((Integer)etapa.elementAt(i)).intValue()+1;                    
                    if(numEtapa<6){
                    etapa.set(i, new Integer(numEtapa));   
                    //System.out.println("y anhadimos etapa "+anhadirEtapa(numEtapa));
                    diagramaMult[i].addElement(anhadirEtapa(numEtapa));                    
                    }
                    }
                }                
                break;
                
            case 3: //avance=3-->ejcucion de SYSCALL
                if(instMult.size()==5){
                    instMult.remove(0);
                    etapa.remove(0);
                    for(int i=0; i<instMult.size(); i++)                                                                  
                        diagramaMult[i]=diagramaMult[i+1];
                    diagramaMult[instMult.size()]=new Vector();
                }
                
                instMult.add(inst);                
                etapa.add(new Integer(1));                                
                
                for(int i=0; i<instMult.size(); i++)               
                {
                    if(!((Instruccion)instMult.elementAt(i) instanceof Syscall))
                    {
                    //System.out.println("entramos en el bucle : "+i);
                    //diagramaMult[i]=diagramaMult[i+1];                    
                    numEtapa=((Integer)etapa.elementAt(i)).intValue()+1;
                    if(numEtapa<6){
                    etapa.set(i, new Integer(numEtapa));   
                    diagramaMult[i].addElement(anhadirEtapa(numEtapa));                    
                    }
                    }
                    diagramaMult[instMult.size()-1].addElement(anhadirEtapa(6));
                }           
                //diagramaMult[instMult.size()-1].addElement(anhadirEtapa(6));
                break;
                
            default:
                System.out.println("Error al construir el diagrama multiciclo");
                
        }
        
    }
    
/**Metodo que devuelve informacion para dibujar el diagrama multiciclo
 *@param Sin parametros
 *@return Vector informacion
 **/
    public Vector[] getDiagramaMult()
    {
        return diagramaMult;
    }
       
/**Metodo que devuelve informacion para dibujar el diagrama monociclo
 *@param Sin parametros
 *@return Vector informacion
 **/
    public Vector getInstMult()
    {
        return instMult;
    }
    
}
