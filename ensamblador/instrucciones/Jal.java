
package ensamblador.instrucciones;
import ensamblador.registros.*;
import ensamblador.datos.*;

public class Jal extends SaltoIncondicional {
    private String imagSegmentado[]={"jalIF.gif","jalID.gif","jalEX.gif","jalMEM.gif","jalWB.gif"};
    String imagenes="jal.gif";  
    String etiqueta;
    private Palabra valorRa;
    
    public Jal(String param[])
    {
    	esSalto=true;
        saltoTomado=true;
        inicia();
        etiqueta=param[0];
        string.append("jal ").append(etiqueta);
    }

    public void inicia()
    {
       String imagenes="jal.gif";
       super.inicIma(imagenes);
    }
     
    public static int numParam()
    {
        return 1;
    }     
    
    public int numero()
    {
        return 18;
    }
    
    public int ejecutar() 
    {
        int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
        Palabra p=new Palabra(Pc.getRegistro().getPalabra().getDec() +4);
        Ra.getRegistro().setPalabra(p);
        Pc.getRegistro().modificar(i);    
         boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa3_salto_incond");
        int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);
        Palabra p=new Palabra(Pc.getRegistro().getPalabra().getDec() );
        Ra.getRegistro().setPalabra(p);
        Pc.getRegistro().modificar(i);    
        boolean_pc=true;
        boolean_dato=true;
        return -1;
    }

    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(3)+setDi((new Palabra(Etiquetas.getEtiquetas().getDireccion(etiqueta))).getBin());       
        return bin_a_hex(codigo);
    }
    
    public Registro regModificable()
    {
        return Ra.getRegistro();
    }
    
    public Registro getRegRS()
    {	
        return null;
    }
    
    public Registro getRegRT()
    {	
        return null;
    }
     
    
    public boolean hayRiesgo()
    {
        if (Ra.getRegistro().estaLibre())
            return false;
        return true;
    }
     
    public int ejecutarIF()
    {        
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        this.cambiosVisibles();
        valorRa=Pc.getRegistro().getPalabra();
        return -1;
    }
    
    public int ejecutarID()
    {
        int i=(int)Etiquetas.getEtiquetas().getDireccion(etiqueta);              
        Pc.getRegistro().modificar(i);
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;  
    }
    
    public int ejecutarEX()
    {
        Ra.getRegistro().ocupar();
        Ra.getRegistro().setPalabra(valorRa);
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {   
        Ra.getRegistro().liberar();
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public String imagenSegmentado(int etapa)
    {
        return imagSegmentado[etapa];
    }
    
}
