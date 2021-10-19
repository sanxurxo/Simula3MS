
package ensamblador.instrucciones;

import java.util.Vector;

import ensamblador.registros.*;
import ensamblador.datos.*;
public class Syscall extends Instruccion {
    
    private String codSyscall="00000000000000000000000000001100";
    public Syscall() {
        inicia();
        string.append("syscall");
    }
    
   public void inicia()
   {
       String imagenes="monociclo.gif";
       super.inicIma(imagenes);
   }
   
   public int numero()
    {
        return 25;
    }
    
    public int ejecutar()
    {
        Pc.getRegistro().incrementar(4);
        boolean_pc=true;
        this.cambiosVisibles();
        int opcion=(int)V0.getRegistro().getPalabra().getDec();
        switch(opcion)
        {
            case 1:              
                return 4;
            case 2:
                return 10;
            case 3:
                return 11;
            case 4:
                return 7;
            case 5:               
                return 5;
            case 6:
                return 12;
            case 7:
                return 13;
            case 8:
                return 6;
            case 9:
                return -1;
            case 10:
                return 9;
            default:
                return 8;
        }
    }
    
    public static int readInt(String lect)    
    {
        int entero;
        if(!lect.equals(null))
        {            
             try{
                entero=Integer.parseInt(lect);               
            }catch(NumberFormatException e){
            	//System.out.println("Error Syscall readInt");
            	return -1;
            	}
                   V0.getRegistro().setPalabra(new Palabra(entero));
             V0.getRegistro().notifyObservers(V0.getRegistro());
             return 0;
        }
        return -1;
    }
    
    public static int readFloat(String lect)    
    {
        Float flot;
        float flotante;
        if(!lect.equals(null))
        {            
             try{
                flot=Float.valueOf(lect);
                flotante=flot.floatValue();
            }catch(NumberFormatException e){return -1;}
                   F0.getRegistro().setPalabra(new Palabra(flotante));
             F0.getRegistro().notifyObservers(F0.getRegistro());
             return 0;
        }
        return -1;
    }
    
     public static int readDouble(String lect)    
    {
        Palabra nada=new Palabra("nada");
        Double doble;
        String r1,r2;
        if(!lect.equals(null))
        {            
             try{
                doble=Double.valueOf(lect);
            }catch(NumberFormatException e){return -1;}
            String palabra=nada.dec_a_float(doble,"DP");
            r1=palabra.substring(0,32);
            r2=palabra.substring(32,palabra.length());
       	    F0.getRegistro().setPalabra(new Palabra(nada.float_a_dec(r1))); 
            F1.getRegistro().setPalabra(new Palabra(nada.float_a_dec(r2)));
            
            F0.getRegistro().notifyObservers(F0.getRegistro());
            F1.getRegistro().notifyObservers(F1.getRegistro());
            return 0;
        }
        return -1;
    }
    
    public static int readString(String lect)
    {
    	Vector<String> strings=new Vector<String>();
    	long direc=0;
        if(!lect.equals(null)){
        	
        	for(int i=0;i<lect.length();i++){
        		strings.addElement(lect.substring(i, i+1));
        	}
        	direc=A0.getRegistro().getPalabra().getDec();

        	if(Memoria.getMemoria().posValida(direc, 0)){
        	
        		Memoria.getMemoria().setString(direc, strings);
        		
        	}else{
        		//se ha intentado escribir una posicion invalida de memoria
        		return 3;
        	}
        	
//        	direc=A0.getRegistro().getPalabra().getDec()
//            A0.getRegistro().setPalabra(new Palabra(Memoria.getMemoria().setTexto(lect),true));
            
        	Memoria.getMemoria().visualizaCambios();
            //A0.getRegistro().notifyObservers(A0.getRegistro());
            A1.getRegistro().setPalabra(new Palabra(lect.length()));
            A1.getRegistro().notifyObservers(A1.getRegistro());
      }
		return -1;
    }
    
    public static String print(int opcion)
    {
        Palabra pal=A0.getRegistro().getPalabra();
        String a;
        double c;
        Double d;
        if(opcion==4){
        	
        	return String.valueOf(A0.getRegistro().getPalabra().getDec());
        }            
        else if(opcion==10)
            return String.valueOf(F12.getRegistro().getPalabra().getFrac());
        else if(opcion==11)
        {
            a=F12.getRegistro().getPalabra().getBin()+F13.getRegistro().getPalabra().getBin();
            c=pal.float_a_dec(a);
            d=new Double(c);
            return d.toString();
        }
        else
        {
            int i=0;

            StringBuffer sal=new StringBuffer();
            String resul=new String();
            
            int desp=(int)(pal.getDec()%4);
            int pos=0;
             while ((Memoria.getMemoria().posValida(pal.getDec(),i)))
            {
                resul=Memoria.getMemoria().getVariable(pal.getDec(),i).getAscii();
                if(i==0) 
                {
                    if((pos=resul.indexOf("\0",desp))!=-1)    
                    {
                        sal.append(resul.substring(desp,pos));
                        break;
                    }
                    else
                        sal.append(resul.substring(desp,resul.length()));
                    i=i+4;
                }
                else
                {
                    if((pos=resul.indexOf("\0"))!=-1)
                    {
                        sal.append(resul.substring(0,pos));                        
                        break;
                    }
                    else
                    {
                        sal.append(resul);
                        i=i+4;
                    }
                }               
            }        
            return sal.toString();
        }
    }
    
    public String imagenSegmentado(int etapa)
    {
        return "nop.gif";
    }
    
    public java.awt.Color colorSegm()
    {
        return new java.awt.Color(0,0,0);
    }
    
    public int estilo()
    {
        return 9;
    }
    
    public String getCodificacion()
    {        
        return bin_a_hex(codSyscall);
    }
    
    public int numInst() 
    {
        return 1;
    }
    
    public int numEtapas()
    {
        return 1;
    }
    
    public int etapa1()
    {
        imagen_etapa=new StringBuffer();
        imagen_etapa.append(direccion_multi).append("caminodatos");
        return this.ejecutar();
    }
    
    public int etapa4() 
    {
        return -1;
    }
    
    public int etapa5() 
    {
        return -1;
    }
    
    public int etapa3() 
    {
        return -1;        
    }
    
    public Registro regModificable()
    {
        return null;
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
        return false;
    }
    
    public int ejecutarIF()
    {
    	
        return -1;
    }
    public int ejecutarID()
    {
        return -1;
    }
    
    public int ejecutarEX()
    {
        return -1;
    }
    
    public int ejecutarMEM()
    {
        return -1;
    }
    
    public int ejecutarWB()    
    {
        return -1;
    }
    
}
