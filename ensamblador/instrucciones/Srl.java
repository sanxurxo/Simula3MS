package ensamblador.instrucciones;

import ensamblador.datos.Palabra;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;

public class Srl extends TipoR {

	 private long shamt;
	 public Srl(String param[]) 
	    {
	    	setOperacion(lenguaje.getString("desplazamiento"));
	          int posi=-1;
	        r = new Registro[numParam()-1];
	        inicia();
	        string.append("srl ");        
	        
	        for(int i=0; i<numParam()-1;i++)
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
	        if(param[numParam()-1].startsWith("0x")){
	            shamt=Long.parseLong(param[numParam()-1].substring(2,param[numParam()-1].length()),16);
	        }
	        else
	            shamt=Integer.parseInt(param[numParam()-1]);
	        string.append(param[numParam()-1]);
	        }    
	 
	 
	 

	 //Es necesario redefinir esValida pq no encaja en el formato estandar de una tipoR
	 // sll reg, reg, shamt en lugar de inst reg, reg, reg
	 public static String esValida(String[] param, int numParam)
	    {           
	         String error=null;
	        
	        if(param.length==numParam())
	        {
	            for(int i=0; i<numParam()-1;i++)
	            {
	                if(esRegistro(param[i]))
	                {
	                        if(param[i].equals("$zero") || param[i].equals("$0"))
	                        {
	                            if(i==0)
	                                error+=param[i]+"parametros no validos. Reg esp"; 
	                        }                    
	                }
	                else
	                {
	                    error=param[i]+"parametros no validos. No es Reg";
	                     return error;
	                }
	            }                    
	            try{
			if((param[numParam()-1].startsWith("0x")) && (param[numParam].length()>2) && (((param[numParam()-1]).trim()).length()<11))
			{  
			
			    try
			    {  
			    	Long.parseLong(param[numParam()-1].substring(2,param[numParam()-1].length()), 16);

			    }catch (NumberFormatException e)
			    {   
			    error=param[numParam()-1]+"parametros no validos";
			    }
			}
			else { 

				Long.parseLong(param[numParam()-1]);   

			}
	            }catch(NumberFormatException e){
			error=param[numParam()-1]+"parametros no validos";}
	        }
	        else
	             error="parametros no validos. Numero de parametros incorrecto";
	        
	        return error;
	    }

	 
	 
	@Override
	public int ejecutar() {
		Pc.getRegistro().incrementar(4);
		Palabra p=r[1].getPalabra();
		Palabra nueva=new Palabra(0);
		int tam=p.getBin().length();
		if(shamt>r[0].getPalabra().getBin().length())        
            return 0;         
		for(int i=0;(i<(tam-shamt));i++){
			if((i+shamt)>=0){
				nueva.setBit(i, p.getBit((int)(i+shamt)));
			}
		}
		r[0].setPalabra(nueva);
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
	}

	@Override
	public int ejecutarEX() {
		r[0].ocupar();
		Palabra p=r[1].getPalabra();
		Palabra nueva=new Palabra(0);
		int tam=p.getBin().length();
		for(int i=0;(i<(tam-shamt));i++){
			if((i+shamt)>=0){
				nueva.setBit(i, p.getBit((int)(i+shamt)));
			}
		}
		r[0].setPalabra(nueva);
		return -1;
	}

	@Override
	public int ejecutarID() {
		return -1;
	}

	@Override
	public int ejecutarIF() {
		 Pc.getRegistro().incrementar(4);
	     boolean_pc=true;
	     this.cambiosVisibles();
	     return -1;
	}

	@Override
	public int ejecutarMEM() {
	    return -1;
	}

	@Override
	public int ejecutarWB() {
		r[0].liberar();
        boolean_dato=true;
        this.cambiosVisibles();
        return -1;
	}

	public int etapa3()
    {
        imagen_etapa=new StringBuffer();
        int except=-1;
        imagen_etapa.append(direccion_multi).append("R_etapa3");     
        if(shamt>r[0].getPalabra().getBin().length())        
            return 0;         
        return -1;
    }
	@Override
	public int etapa4() {
		imagen_etapa=new StringBuffer();
        int except=-1;
        imagen_etapa.append(direccion_multi).append("R_etapa4");        
        Palabra p=r[1].getPalabra();
		Palabra nueva=new Palabra(0);
		int tam=p.getBin().length();
		for(int i=0;(i<(tam-shamt));i++){
			if((i+shamt)>=0){
				nueva.setBit(i, p.getBit((int)(i+shamt)));
			}
		}
		r[0].setPalabra(nueva);
        boolean_dato=true;
        boolean_pc=true;
        boolean_dato=true;
        this.cambiosVisibles();
        return except;
	}

	@Override
	public Registro getRegRS() {
		return r[1];
	}

	@Override
	public Registro getRegRT() {
		
		return null;
	}

	@Override
	public boolean hayRiesgo() {
		 if (r[1].estaLibre() )
	            return false;
	        return true;
	}

	@Override
	public int numero() {
		// TODO Auto-generated method stub
		return 62;
	}

	@Override
	public Registro regModificable() {
		return r[0];
	}

	@Override
	public String getCodificacion() {
		 String codigo;
	     codigo=setOp(0)+setR(r[1].numReg())+"00000"+setR(r[0].numReg())+this.setShamt((int)shamt)+setFunc(2);       
	     return bin_a_hex(codigo);
	}

}
