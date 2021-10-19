
package ensamblador.instrucciones;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Ori extends Inmediatas{
    
    Registro r[];
    int inmediato;
		//long maximum=2147483647;
		long resultado;

    
    public Ori(String param[]) 
    {
        int posi=-1;		
        r= new Registro[numParam()];
        inicia();
        string.append("ori ");
        for(int i=0; i<numParam();i++)
        {           
            string.append(param[i]);
            if(i!=numParam())
                string.append(", ");
          for(int j=0;j<registros.length;j++)
            {
                try
                {
                        posi=java.lang.Integer.parseInt(param[i].substring(1,param[i].length()));
                }
                catch(NumberFormatException e)
                {
                        r[i]=relacionaReg(param[i]);                       
                        break;
                }
                r[i]=relacionaReg(posi);
                
            }    
        } 
        if(param[numParam()].startsWith("0x")){
            inmediato=Integer.parseInt(param[numParam()].substring(2,param[numParam()].length()),16);
        }else
           inmediato=Integer.parseInt(param[numParam()]);
       string.append(param[numParam()]);
    }      
    
    public int numero()
    {
        return 9;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
	/*if (inmediato>32767){
		resultado=(r[1].getPalabra().getDec()) | (inmediato+Long.parseLong("FFFF0000",16));		} 
	else{*/
	    resultado=(r[1].getPalabra().getDec()) | (inmediato);    
		//} 		
	
	
	
		/*String s=new String("4294967295");
		Long l=Long.parseLong(s);
		if(resultado>l.longValue()) 
			resultado=resultado-maximum-maximum-2;*/       
				
	    r[0].setPalabra(new Palabra(resultado)); 
		
		boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
    }
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_inmed");
	/*if (inmediato>32767){
		resultado=(r[1].getPalabra().getDec()) | (inmediato+Long.parseLong("FFFF0000",16));		} 
	else{*/
	    resultado=(r[1].getPalabra().getDec()) | (inmediato);    
		//} 		
		//if(resultado>2147483647) resultado=resultado-maximum-maximum-2;       
	    r[0].setPalabra(new Palabra(resultado)); 
/*
	if (inmediato>32767)
	    r[0].setPalabra(new Palabra((r[1].getPalabra().getDec()) | (inmediato+Long.parseLong("FFFF0000",16))));  
	else
	    r[0].setPalabra(new Palabra((r[1].getPalabra().getDec()) | (inmediato)));  */
		boolean_dato=true;
		 this.cambiosVisibles();
        return -1;
    }
    
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(13)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(inmediato);       
        return bin_a_hex(codigo);
    }
    
    public Registro regModificable()
    {
        return r[0];
    }
    
    public Registro getRegRS()
    {	
        return r[1];
    }
    
    public Registro getRegRT()
    {	
        return null;
    }

    public boolean hayRiesgo()
    {
        if (r[1].estaLibre())
            return false;
        return true;
    }
     
    public int ejecutarIF()
    {        
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int ejecutarID()
    {
        return -1;
    }
    
    public int ejecutarEX()
    {
        r[0].ocupar();
        /*if (inmediato>32767)
        {
            resultado=(r[1].getPalabra().getDec()) | (inmediato+Long.parseLong("FFFF0000",16));		} 
            else{*/
	    resultado=(r[1].getPalabra().getDec()) | (inmediato);    
	//} 		
	//if(resultado>2147483647) resultado=resultado-maximum-maximum-2;       
	    r[0].setPalabra(new Palabra(resultado)); 

        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {             
	r[0].liberar();
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;        
    }
     
}
