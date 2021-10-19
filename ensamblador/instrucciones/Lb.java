
package ensamblador.instrucciones;

import ensamblador.datos.EntradaSalida;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.registros.Pc;
import ensamblador.registros.Registro;
import ensamblador.registros.Sp;
public class Lb extends Carga {
	int numReg;
	String variable;
	Registro r[];
	private int desplazamiento;
	
	
	public Lb(String param[])
	{
		
		int posi=-1;
		inicia();
		if(esVariable(param[1]))
		{
			
			variable=param[1];
			numReg=1;
			r=new Registro[numReg];
			desplazamiento=0;
		}
		else
		{
			numReg=2;
			r=new Registro[numReg];       
			desplazamiento=getDesplazamiento();          
		}
		string.append("lb ").append(param[0]).append(", ").append(param[1]);
		int ind1;
		if((ind1=param[1].indexOf("$"))!=0)
		{
			int ind2=param[1].indexOf(")");
			param[1]=param[1].substring(ind1,ind2);
		}   
		for(int i=0; i<numReg;i++)
		{         
			
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
	}  
	
	
	public int numero()
	{
		return 15;
	}
	
	
	public int ejecutar(){      
		Pc.getRegistro().incrementar(4);
		boolean_pc=true;
		boolean_dato=true;
		
		if(numReg==2){
			int desp=desplazamiento/4;
			int des=desplazamiento%4;
			long direccion=r[1].getPalabra().getDireccion();
			Palabra p=r[1].getPalabra();
	        p=p.sumar(desp*4);
	        p=p.sumar(des);
			
			if(((direccion+(desp*4)+des)<=(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=Sp.getRegistro().getPalabra().getDec())){           
				r[0].setPalabra(Pila.getPila().getByte(direccion+(desp*4),des));}
			else
																		//tiene que ser < estricto pq si no funciona como reserva dinamica y no estatica									
				if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<(Memoria.getMemoria().obtPosMem())+4)){            
					r[0].setPalabra(Memoria.getMemoria().getByte(direccion,desplazamiento));}
				//else if(((direccion+(desp*4)+des)>=INICIO_ES.getDec()) &&((direccion+(desp*4)+des)<=FIN_ES.getDec())){
				 else if((p.getHex().compareTo(INICIO_ES.getHex())>=0) && (p.getHex().compareTo(FIN_ES.getHex())<=0)){ 
					r[0].setPalabra(EntradaSalida.getEntradaSalida().getByte(direccion, desplazamiento));
					}
					else
					{
						this.cambiosVisibles();
						return 3;
					}
			this.cambiosVisibles();
			return -1;         
		}          
		else
			r[0].setPalabra(Memoria.getMemoria().getByte(variable));                      
		this.cambiosVisibles();
		return -1;
	}
	
	public int etapa5()
	{
		imagen_etapa=new StringBuffer();
		imagen_etapa.append(direccion_multi).append("etapa5");
		
		if(numReg==2)
		{
			int desp=desplazamiento/4;
			int des=desplazamiento%4;
			int direccion=(int)r[1].getPalabra().getDec();
			if(((direccion+(desp*4)+des)<=(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=Sp.getRegistro().getPalabra().getDec()))            
			{
				r[0].setPalabra(Pila.getPila().getByte(direccion+(desp*4),des));
				boolean_dato=true;
			}
			else															//tiene que ser < estricto pq si no funciona como reserva dinamica y no estatica
				if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<(Memoria.getMemoria().obtPosMem())+4))
				{
					r[0].setPalabra(Memoria.getMemoria().getByte(direccion,desplazamiento)); 
					boolean_dato=true;
				}
				else
				{
					this.cambiosVisibles();
					return 3;
				}
			this.cambiosVisibles();
			return -1;            
		}          
		else
		{
			r[0].setPalabra(Memoria.getMemoria().getByte(variable));                      
			boolean_dato=true;
		}
		this.cambiosVisibles();
		return -1;       
	}
	
	
	public int etapa4()
	{
		imagen_etapa=new StringBuffer();
		imagen_etapa.append(direccion_multi).append("etapa4_load");
		
		if(numReg==2)
		{
			int desp=desplazamiento/4;
			int des=desplazamiento%4;
			int direccion=(int)r[1].getPalabra().getDec();
			if(((direccion+(desp*4)+des)<=(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=Sp.getRegistro().getPalabra().getDec()))                        
				return -1;
			
			else														//tiene que ser < estricto pq si no funciona como reserva dinamica y no estatica
				if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<(Memoria.getMemoria().obtPosMem())+4))
					return -1;
				else
				{                   
					return 3;
				}                          
		}          
		else
		{
			return -1;             
		}               
	}
	
	
	
	
	public String getCodificacion()
	{
		String codigo;
		codigo=setOp(32)+setR(r[1].numReg())+setR(r[0].numReg())+setIn(desplazamiento);       
		return bin_a_hex(codigo);
	}
	
	public Registro regModificable()
	{
		return r[0];
	}
	
	public Registro getRegRS()
	{
		if (numReg==2)
			return r[1];
		return null;
	}
	
	public Registro getRegRT()
	{	
		return null;
	}
	
	
	public boolean hayRiesgo()
	{
		if(r[1].estaLibre())
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
		return -1;
	}
	
	public int ejecutarMEM()
	{
		if(numReg==2)
		{
			int desp=desplazamiento/4;
			int des=desplazamiento%4;
			int direccion=(int)r[1].getPalabra().getDec();
			if(((direccion+(desp*4)+des)<=(INICIO_PILA.getDec()+4)) && ((direccion+(desp*4)+des)>=Sp.getRegistro().getPalabra().getDec()))            
			{
				r[0].setPalabra(Pila.getPila().getByte(direccion+(desp*4),des));
				return -1;
			}
			else
				if(((direccion+(desp*4)+des)>=INICIO_MEMORIA.getDec()) && ((direccion+(desp*4)+des)<=(Memoria.getMemoria().obtPosMem())+4))
				{
					r[0].setPalabra(Memoria.getMemoria().getByte(direccion,desplazamiento)); 
					return -1;
				}
				else
					return 3; 
		}          
		else
		{
			r[0].setPalabra(Memoria.getMemoria().getByte(variable));                      
			return -1;                                                
		}
	}
	
	public int ejecutarWB()
	{    
		r[0].liberar();
		boolean_dato=true;
		this.cambiosVisibles();
		return -1;                                                     
	}
	
	

}
