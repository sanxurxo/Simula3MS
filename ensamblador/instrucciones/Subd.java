package ensamblador.instrucciones;

import java.util.Hashtable;

import ensamblador.datos.Palabra;
import ensamblador.datos.PalabraCompuesta;
import ensamblador.datos.Tipos;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import java.awt.Color;
public class Subd extends TipoR implements PlanifDinamica {
     Registro r[]; 
     Palabra nada=new Palabra("nada");
     public Subd(String param[]) 
    {
this.imagSegmentado[0]="sumaPF_IF.gif";
    	this.imagSegmentado[1]="sumaPF_ID.gif";
    	 instruccionFP=true;
    	 instruccionDouble=true;
    	 resultTomasulo=new Hashtable<String, Palabra>();
    	 setOperacion(lenguaje.getString("resta"));
    	tipoFU=Tipos.FP_ADD_FU;
    	tipoER=Tipos.FP_ADD_ER;
        int posi=-1;
        r= new Registro[numParam()];
        inicia();
        string.append("sub.d ");
        for(int i=0; i<numParam();i++)
        {                       
            string.append(param[i]);
            if(i!=numParam()-1)
                string.append(", ");
            for(int j=0;j<registros.length;j++)
            {
                try
                {
                    posi=java.lang.Integer.parseInt(param[i].substring(1,param[i].length()));
                }
                catch(NumberFormatException e)
                {
                    r[i]=relacionaRegF(param[i]);                       
                    break;
                }
                r[i]=relacionaRegF(posi);
            }
        }         
    }
     
    public void inicia()
    {
       String imagenes="R_flot.gif";
       super.inicIma(imagenes);
    }
    
    public int numero()
    {
        return 31;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        String r1=r[1].getPalabra().getBin().concat(relacionaRegF(r[1].numReg()+1).getPalabra().getBin());
        String r2=r[2].getPalabra().getBin()+relacionaRegF(r[2].numReg()+1).getPalabra().getBin();
        double rf1=nada.float_a_dec(r1+r2);
        double rf2=nada.float_a_dec(r2);
        double resultado=nada.float_a_dec(r1)-nada.float_a_dec(r2);
        Double resul=new Double(resultado);
        String palabra=nada.dec_a_float(resul,"DP");
        r1=palabra.substring(0,32);
        Palabra p=new Palabra(0);
        String hx1=p.bin2hex(r1);
        r2=palabra.substring(32,palabra.length());
      //  r[0].setPalabra(new Palabra(nada.float_a_dec(r1))); 
        r[0].setPalabra(new Palabra(hx1,true));
        relacionaRegF(r[0].getSiguiente()).setPalabra(new Palabra(nada.float_a_dec(r2)));
        boolean_pc=true;
        boolean_datos=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public int etapa4()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("RF_etapa4");
        String r1=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        String r2=r[2].getPalabra().getBin()+relacionaRegF(r[2].numReg()+1).getPalabra().getBin();
        double resultado=nada.float_a_dec(r1)-nada.float_a_dec(r2);
         Double resul=new Double(resultado);
        String palabra=nada.dec_a_float(resul,"DP");
        r1=palabra.substring(0,32);
        r2=palabra.substring(32,palabra.length());
        Palabra p=new Palabra(0);
        String hx1=p.bin2hex(r1);
        r[0].setPalabra(new Palabra(hx1,true));
        //r[0].setPalabra(new Palabra(nada.float_a_dec(r1)));
        
        relacionaRegF(r[0].getSiguiente()).setPalabra(new Palabra(nada.float_a_dec(r2)));    
        boolean_datos=true;
        boolean_pc=true;
        this.cambiosVisibles();
        return -1;
    }
    
     public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("RF_etapa3");
        String r1=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
        String r2=r[2].getPalabra().getBin()+relacionaRegF(r[2].numReg()+1).getPalabra().getBin();
        double resultado=nada.float_a_dec(r1)-nada.float_a_dec(r2);
        return -1;
    }
    
    public int etapa2()
    {    
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("etapa2F");
        return -1;
    }
    
    public int etapa1()
    {
        imagen_etapa=new StringBuffer();
        boolean_pc=true;
        Pc.getRegistro().incrementar(4);
        imagen_etapa.append(direccion_multi).append("etapa1F");
        return -1;
    }
    
    public String getCodificacion()
    {
        String codigo;
        codigo=setOp(17)+setR(1)+setR(r[2].numReg())+setR(r[1].numReg())+setR(r[0].numReg())+setFunc(1);       
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
        return r[2];
    }    
    public boolean hayRiesgo()
    {
        return false;
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
    	   String r1=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
           String r2=r[2].getPalabra().getBin()+relacionaRegF(r[2].numReg()+1).getPalabra().getBin();
           double resultado=nada.float_a_dec(r1)-nada.float_a_dec(r2);
            Double resul=new Double(resultado);
           String palabra=nada.dec_a_float(resul,"DP");
           r1=palabra.substring(0,32);
           r2=palabra.substring(32,palabra.length());
           Palabra p=new Palabra(0);
           String hx1=p.bin2hex(r1);
           r[0].setPalabra(new Palabra(hx1,true));
//           r[0].setPalabra(new Palabra(nada.float_a_dec(r1))); 
           relacionaRegF(r[0].getSiguiente()).setPalabra(new Palabra(nada.float_a_dec(r2)));    
           return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()
    {
    	r[0].liberar();
        boolean_datos=true;
        this.cambiosVisibles();
        return -1;
    }
    
    public Color colorSegm() {
    	return Color.ORANGE;
    }
    
    public int estilo()
    {
        return 10;
    }
    
    public int ejecutarTomasulo(Palabra rs, Palabra rt,String estacion){
    
    	Palabra rsSig = rs.obtenerPalabra(1);
    	Palabra rtSig= rt.obtenerPalabra(1);
    	
    	String r1;
    	String r2;
    	if(rsSig!=null){
    		r1=rs.getBin()+rsSig.getBin();
    		
    	}else{
    		
    		r1=rs.getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
    	}
    
    	if(rtSig!=null){
    		
    		r2=rt.getBin()+rtSig.getBin();
    	}else{    	 
    		r2=rt.getBin()+relacionaRegF(r[2].numReg()+1).getPalabra().getBin();
    	}
         double resultado=nada.float_a_dec(r1)-nada.float_a_dec(r2);
          Double resul=new Double(resultado);
         String palabra=nada.dec_a_float(resul,"DP");
         r1=palabra.substring(0,32);
         r2=palabra.substring(32,palabra.length());

         Palabra p=new Palabra(0);
         String hx1=p.bin2hex(r1);

         PalabraCompuesta resComp=new PalabraCompuesta(hx1,true);
         resComp.agregarPalabras(new Palabra(hx1,true),new Palabra(nada.float_a_dec(r2)));
         resultTomasulo.put(estacion, resComp); 
        if(r[0].getQi().equals(estacion)){
        	r[0].setPalabra(new Palabra(hx1,true));
         //r[0].setPalabra(new Palabra(nada.float_a_dec(r1))); 
         relacionaRegF(r[0].getSiguiente()).setPalabra(new Palabra(nada.float_a_dec(r2)));
    
    	}
         return -1;
    }
    public int ejecutarMarcador(){
    	 String r1=r[1].getPalabra().getBin()+relacionaRegF(r[1].numReg()+1).getPalabra().getBin();
         String r2=r[2].getPalabra().getBin()+relacionaRegF(r[2].numReg()+1).getPalabra().getBin();
         double resultado=nada.float_a_dec(r1)-nada.float_a_dec(r2);
          Double resul=new Double(resultado);
         String palabra=nada.dec_a_float(resul,"DP");
         r1=palabra.substring(0,32);
         r2=palabra.substring(32,palabra.length());
         Palabra p=new Palabra(0);
         String hx1=p.bin2hex(r1);
         r[0].setPalabra(new Palabra(hx1,true));
         //r[0].setPalabra(new Palabra(nada.float_a_dec(r1))); 
         relacionaRegF(r[0].getSiguiente()).setPalabra(new Palabra(nada.float_a_dec(r2)));    
         return -1;
    }
    
    public Palabra WBTomasulo(String estacion){
	    ejecutarWB();	
		 Palabra p=null;
	    	if((p=resultTomasulo.get(estacion))!=null){
	    		resultTomasulo.remove(estacion);		    		
	    		return p;
	    	}
	    	return null;
	    }
    
    
}
