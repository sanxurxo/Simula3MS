
package ensamblador.instrucciones;
import ensamblador.datos.Palabra;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;

public class Addi extends Inmediatas 
{        
    
    private long inmediato;
	//long maximum=2147483647;
    
    public Addi(String param[]) 
    {
    	setOperacion(lenguaje.getString("suma"));
          int posi=-1;
        r = new Registro[numParam()];
        inicia();
        string.append("addi ");        
        
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
            inmediato=Long.parseLong(param[numParam()].substring(2,param[numParam()].length()),16);
        }
        else
            inmediato=Integer.parseInt(param[numParam()]);
        string.append(param[numParam()]);
        }    
    
    public int numero()
    {
        return 1;
    }
      
    public int ejecutar()
    {
         int except=-1;
        Pc.getRegistro().incrementar(4);
        long resultado=r[1].getPalabra().getDec()+inmediato;
	//if (inmediato>32767)
	  //  resultado=r[1].getPalabra().getDec()+inmediato+Long.parseLong("FFFF0000",16);
        
		//if(resultado>2147483647) resultado=resultado-maximum-maximum-2;    
        
        if((resultado>TOPE_POS.getDec()))
        {
            resultado=resultado%(TOPE_POS.getDec()+1);
            except=0;
        }   
        if((resultado<TOPE_NEG.getDec()))
        {
            resultado=resultado%(TOPE_NEG.getDec());
            except=0;
        }   
      
        r[0].setPalabra(new Palabra(resultado));        
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return except;                           
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa4_inmed");
        int except=-1;        
        long resultado=r[1].getPalabra().getDec()+inmediato;
	if (inmediato>32767)
	    resultado=r[1].getPalabra().getDec()+inmediato+Long.parseLong("FFFF0000",16);

		//if(resultado>2147483647) resultado=resultado-maximum-maximum-2;  
		
		  
        if(resultado>TOPE_POS.getDec())       
            resultado=resultado%(TOPE_POS.getDec()+1);
        if(resultado<TOPE_NEG.getDec())       
            resultado=resultado%(TOPE_NEG.getDec());
                    
  
        r[0].setPalabra(new Palabra(resultado));        
         boolean_dato=true;
         this.cambiosVisibles();
        return except;    
    }

     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        int except=-1;
        imagen_etapa.append(direccion_multi).append("etapa3_inmed");
        long resultado=r[1].getPalabra().getDec()+inmediato;
	if (inmediato>32767)
	    resultado=r[1].getPalabra().getDec()+inmediato+Long.parseLong("FFFF0000",16);

		//if(resultado>2147483647) resultado=resultado-maximum-maximum-2;  
		

        if((resultado>TOPE_POS.getDec()) || (resultado<TOPE_NEG.getDec()))        
            return 0;                          
        return -1;
    }
   
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(8)+setR(r[1].numReg())+setR(r[0].numReg())+setIn((int)inmediato);       
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
        /*long resultado=r[1].getPalabra().getDec()+inmediato;
	if (inmediato>32767)
	    resultado=r[1].getPalabra().getDec()+inmediato+Long.parseLong("FFFF0000",16);

		if(resultado>2147483647) resultado=resultado-maximum-maximum-2;  
		

        if(resultado>TOPE.getDec())        
            return 0;*/  
      
        long resultado=r[1].getPalabra().getDec()+inmediato;
	if (inmediato>32767)
	    resultado=r[1].getPalabra().getDec()+inmediato+Long.parseLong("FFFF0000",16);

		//if(resultado>2147483647) resultado=resultado-maximum-maximum-2;  
		

        if(resultado>TOPE_POS.getDec())        
            resultado=resultado%(TOPE_POS.getDec()+1);           
        if(resultado<TOPE_NEG.getDec())        
            resultado=resultado%(TOPE_NEG.getDec());           
        
        
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
 