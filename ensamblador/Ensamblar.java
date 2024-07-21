/**
 *Ensambla.java
 *Clase encargada de controlar las caracteristicas sintacticas de las instrucciones
 **/
package ensamblador;

import java.util.Observable;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import ensamblador.datos.Ascii;
import ensamblador.datos.Asciiz;
import ensamblador.datos.Codigo;
import ensamblador.datos.Etiquetas;
import ensamblador.datos.Flotante;
import ensamblador.datos.Lenguaje;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Pila;
import ensamblador.datos.Space;
import ensamblador.datos.Tipos;
import ensamblador.datos.Word;
import ensamblador.instrucciones.Absd;
import ensamblador.instrucciones.Abss;
import ensamblador.instrucciones.Add;
import ensamblador.instrucciones.Addd;
import ensamblador.instrucciones.Addi;
import ensamblador.instrucciones.Adds;
import ensamblador.instrucciones.And;
import ensamblador.instrucciones.Andi;
import ensamblador.instrucciones.Bc1f;
import ensamblador.instrucciones.Bc1t;
import ensamblador.instrucciones.Beq;
import ensamblador.instrucciones.Bne;
import ensamblador.instrucciones.Ceqd;
import ensamblador.instrucciones.Ceqs;
import ensamblador.instrucciones.Cled;
import ensamblador.instrucciones.Cles;
import ensamblador.instrucciones.Cltd;
import ensamblador.instrucciones.Clts;
import ensamblador.instrucciones.Cvtds;
import ensamblador.instrucciones.Cvtdw;
import ensamblador.instrucciones.Cvtsd;
import ensamblador.instrucciones.Cvtsw;
import ensamblador.instrucciones.Cvtwd;
import ensamblador.instrucciones.Cvtws;
import ensamblador.instrucciones.Div;
import ensamblador.instrucciones.Divd;
import ensamblador.instrucciones.Divs;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.J;
import ensamblador.instrucciones.Jal;
import ensamblador.instrucciones.Jr;
import ensamblador.instrucciones.La;
import ensamblador.instrucciones.Lb;
import ensamblador.instrucciones.Li;
import ensamblador.instrucciones.Lui;
import ensamblador.instrucciones.Lw;
import ensamblador.instrucciones.Lwc1;
import ensamblador.instrucciones.Mfc0;
import ensamblador.instrucciones.Mfc1;
import ensamblador.instrucciones.Mfhi;
import ensamblador.instrucciones.Mflo;
import ensamblador.instrucciones.Movd;
import ensamblador.instrucciones.Movs;
import ensamblador.instrucciones.Mtc1;
import ensamblador.instrucciones.Muld;
import ensamblador.instrucciones.Muls;
import ensamblador.instrucciones.Mult;
import ensamblador.instrucciones.Negd;
import ensamblador.instrucciones.Negs;
import ensamblador.instrucciones.Nop;
import ensamblador.instrucciones.Or;
import ensamblador.instrucciones.Ori;
import ensamblador.instrucciones.Rfe;
import ensamblador.instrucciones.Sb;
import ensamblador.instrucciones.Sll;
import ensamblador.instrucciones.Slt;
import ensamblador.instrucciones.Slti;
import ensamblador.instrucciones.Sra;
import ensamblador.instrucciones.Srl;
import ensamblador.instrucciones.Sub;
import ensamblador.instrucciones.Subd;
import ensamblador.instrucciones.Subs;
import ensamblador.instrucciones.Sw;
import ensamblador.instrucciones.Swc1;
import ensamblador.instrucciones.Syscall;
import ensamblador.procesadorMonociclo.RutinaTecladoUsuario;
import ensamblador.util.excepcion.DuplicatedLabelException;
import ensamblador.util.excepcion.EnsamblarException;
public class Ensamblar extends Observable implements Tipos{

	private Memoria memoria=Memoria.getMemoria();
	private Vector variables;
	private String texto;
	Codigo codigo=Codigo.getInstacia();
	Lenguaje lenguaje;

	/**
	 *Constructor de la clase ensamblar
	 **/
	public Ensamblar(String txt) {        
		texto=txt;
		memoria.inicializar();
		Pila.getPila().inicializar();        
		lenguaje = Lenguaje.getInstancia();
	}

	/**
	 *Metodo que separa los comentarios del cdigo
	 *@param sin parametros
	 *@return String sinComentarios
	 **/
	public String quitaComent()
	{
		String token=new String();
		StringTokenizer st=new StringTokenizer(texto,"\n");
		StringBuffer sinComent=new StringBuffer();

		while (st.hasMoreTokens())
		{
			token=new String(st.nextToken("\n"));
			while((token.length()>0) && ((token.substring(0,1).equals("\n") || token.substring(0,1).equals("\t") || token.substring(0,1).equals(" "))))     
				token=token.substring(1,token.length());            
			int n=token.length();
			int p=token.indexOf("#");
			if (p==-1)
			{
				while((token.length()>0) && ((token.substring(token.length()-1,token.length()).equals("\n") || token.substring(token.length()-1,token.length()).equals("\t") || token.substring(token.length()-1,token.length()).equals(" "))))
					token=token.substring(0,token.length()-1);
				if (!token.equals("\n"))
				{
					sinComent.append(token);
					sinComent.append("\n");
				}
			}
			else
				if (p!=0)
				{
					token=token.substring(0,p-1);
					while((token.length()>0) && ((token.substring(0,1).equals("\n") || token.substring(0,1).equals("\t") || token.substring(0,1).equals(" "))))          
						token=token.substring(0,token.length()-1);
					sinComent.append(token);
					sinComent.append("\n");
				}

		}
		return new String(sinComent);
	}

	/**
	 *Metodo que identifica las etiquetas
	 *@param String texto
	 *@return Etiquetas etiquetas
	 **/
	public Etiquetas setEtiquetas(String texto)
	{
		Etiquetas etiquetas=Etiquetas.getEtiquetas();
		etiquetas.inicializar();
		StringTokenizer st=new StringTokenizer(texto,".globl main");
		StringTokenizer st2;        
		String aux=new String();
		String sinEspacio=new String();
		int i=0;
		while (!((aux=st.nextToken("\n")).equals(".data")) && (st.hasMoreTokens()))
		{                                   
			if(aux.indexOf(":")!=-1)
			{                
				st2=new StringTokenizer(aux,":");       
				String token=st2.nextToken();             
				etiquetas.anhadirEtiqueta(token,i-1);                
				
			}
			else
			{
				/*Las pseudoinstrucciones la y li dan lugar a dos instrucciones: lui y ori*/
				
				Pattern patronLa=Pattern.compile("(la)\\s*\\$");
				Matcher encajaLa=patronLa.matcher(aux);
				
				Pattern patronLi=Pattern.compile("(li)\\s*\\$");
				Matcher encajaLi=patronLi.matcher(aux);
				
								
				if((encajaLa.find())|| (encajaLi.find())){				
					i=i+2;
				}
				else
					i++;
			}
		}
		return etiquetas;
	}

	/**
	 *Metodo que devuelve las variables
	 *@param sin paramentros
	 *@return Vector variables
	 **/   
	public Vector getVariables()
	{
		return variables;
	}


	public String separaInstrucciones(StringTokenizer st, boolean isRutina){
		String token;
		String err=null;
		String ERROR=null;
		Etiquetas etiquetas=Etiquetas.getEtiquetas();
		while (st.hasMoreTokens())
		{            
			token=st.nextToken("\n");
			err=null;
			if(!Instruccion.esEtiqueta(token, etiquetas))
			{                                
				err=analizaInstruc(new String(token),etiquetas, getVariables(), isRutina);
				if(err!=null)
				{
					if(ERROR==null)
						ERROR=(token.trim());
					else
						ERROR+=(token.trim());

					ERROR+='\t';
					ERROR+=err;
					ERROR+='\n';                                       
				}                                    
			}
		}
		return ERROR;
	}

	/**
	 *Metodo que analiza el .text
	 *@param String texto
	 *@return String error
	 **/
	public String analizaTexto(String tx)
	{		
		String ERROR=null;
		String err=null;
		Vector variables=getVariables();
		StringTokenizer st=new StringTokenizer(tx);
		String token=st.nextToken();  

		if ((token.toString()).equals(".globl"))
		{
			token=st.nextToken();
			if ((token.toString()).equals("main"))
			{                    
				token = st.nextToken("\n");

				if (token.trim().isBlank()) {

					token = st.nextToken();
				}

				if (token.contains("main:"))
				{
					setEtiquetas(tx);

					StringTokenizer stToCheckNoInstructionsAfterMainLabel = new StringTokenizer(token);
					if (!stToCheckNoInstructionsAfterMainLabel.nextToken().equals("main:")
							|| stToCheckNoInstructionsAfterMainLabel.hasMoreTokens())
					{
						ERROR = token + lenguaje.getString("instruccionNoValida");
					}
					else ERROR = this.separaInstrucciones(st, false);
				}
				else                    
					ERROR=lenguaje.getString("faltaMain");                               
			}
			else               
				ERROR=lenguaje.getString("falta.globlMain");                
		}
		else                
			ERROR=lenguaje.getString("falta.globlMain");            
		return ERROR;
	}

	/**
	 *Metodo que analiza el .data
	 *@param String texto
	 *@return String error
	 **/

	public String analizaDatos(String tx){
	
		String ERROR=null;
		String err=null;
		String var=tx;            
		if(tx.length()>1){
			StringTokenizer st=new StringTokenizer(tx, "\n");
			String token;            
			while(st.hasMoreTokens())
			{  
				token=st.nextToken(); 
				err=analizaDato(token);
				if(err!=null)
				{
					if(ERROR==null)
						ERROR=(token);                    
					else                                                   
						ERROR+=(token);                                                                 
					ERROR+='\t';                                         
					ERROR+=err;
					ERROR+='\n';                                 
				}
			}
		}        
//		else
//			ERROR=lenguaje.getString("datosSinInicializar")+"\n";
		if(ERROR==null)
			memoria.memBase();
		return ERROR;
	}



	/**
	 *Metodo que analiza el codigo para dividir entre .text .data y .text 0x80000080
	 *@param String texto
	 *@return Vector<String> partes
	 **/
	public Vector<String> divideCodigo(String codigo) throws EnsamblarException{
		String data=null;
		String texto;
		String text=null;
		String text0x=null;
		Vector<String> partes=new Vector<String>();
		int dataPos=-1;
		int textPos=-1;
		int text0xPos=-1;
		textPos=codigo.indexOf(".text");
		if(textPos!=-1){
			texto=codigo.substring(textPos, codigo.indexOf("\n", textPos));    	
			//texto=texto.trim();


			if(texto.indexOf(" 0x")!=-1){

				text0xPos=textPos;
				textPos=codigo.indexOf(".text", codigo.indexOf("\n", text0xPos));
			}else{

				text0xPos=codigo.indexOf(".text", codigo.indexOf("\n", textPos));

				if(text0xPos!=-1){
					texto=codigo.substring(text0xPos, codigo.indexOf("\n", text0xPos));

					if(texto.indexOf(" 0x")==-1){
						text0xPos=-1;

					}
				}
			}
		}

		if(textPos==-1){
			throw new EnsamblarException("falta .text");
		}

		dataPos=codigo.indexOf(".data");

		int topeData;
		int topeText;
		int topeText0x;
		int iniData=codigo.indexOf("\n", dataPos);
		if(dataPos>textPos){
			if(dataPos>text0xPos){
				topeData=codigo.length();
				if(text0xPos>textPos){		//.text---.text0x---.data

					topeText=text0xPos;
					topeText0x=dataPos;
				}else{

					topeText0x=textPos;//.text0x---.text---.data
					topeText=dataPos;
				}
			}else{					//.text---.data ---.text0x

				topeText=dataPos;
				topeData=text0xPos;
				topeText0x=codigo.length();
			}    			    			
		}else{
			if(dataPos>text0xPos){	//.text0x----.data----.text

				topeText0x=dataPos;
				topeData=textPos;
				topeText=codigo.length();    				
			}else{
				topeData=text0xPos;
				if(textPos>text0xPos){ //.data ----.text0xPos---.textPos

					topeText0x=textPos;
					topeText=codigo.length();
				}else{ 					//.data -----.textPos----.text0xPos

					topeText0x=codigo.length();
					topeText=text0xPos;
				}
			}
		}


		text=new String(codigo.substring(codigo.indexOf("\n",textPos), topeText));

		partes.add(Tipos.TEXT, text);
		if(dataPos>=0){    		    		
			data=new String(codigo.substring(codigo.indexOf("\n",dataPos), topeData));    		
			partes.add(Tipos.DATA, data);

		}else{
			partes.add(Tipos.DATA, new String());
		}
		if(text0xPos>=0){

			text0x=new String(codigo.substring(text0xPos, topeText0x)); //En este caso es necesario que incluya la primera linea (.text 0x80000080) para comprobar que la
			//direccion es correcta
			partes.add(Tipos.TEXT0X, text0x);

		}else{
			partes.add(Tipos.TEXT0X, new String());
		}

		return partes;

	}
	
	
	/**
	 *Metodo que anhade las etiquetas de la rutina cuando hay .text 0x80000080
	 *@param String texto
	 *@return String error
	 **/
	public String buscaEtiquetasRutina(String texto){
		String error=null;
		if(texto.length()>0){
			StringTokenizer st=new StringTokenizer(texto, "\n");
			String token=st.nextToken();

			int numInstrucciones=0;
			while(st.hasMoreTokens()){			
				token=st.nextToken();

				if(token.indexOf(":")!=-1){
					try{
						((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setEtiqueta(token, numInstrucciones);
					}catch(DuplicatedLabelException e){
						error=e.toString();
					}		
				}else{
					if((token.indexOf("la $")!=-1) ||(token.indexOf("li $")!=-1)){	
						numInstrucciones+=2;					
					}else{
						numInstrucciones++;
					}
				}
			}
		}
		return error;
	}
	
	/**
	 *Metodo que analiza el .text 0x80000080
	 *@param String texto
	 *@return String error
	 **/
	public String analizaTexto0x(String texto){
		String ERROR=null;
		if(texto.length()>0){
			
			StringTokenizer st=new StringTokenizer(texto, "\n");
			String token=st.nextToken();

			String direccion=token.substring(token.indexOf("0x"), token.length()).trim();
			if(direccion.equals("0x80000080")){

				if(!esEtiqueta(st.toString())){

					ERROR=this.separaInstrucciones(st, true);
				}
			}else{
				ERROR="direccion incorrecta";
			}
		}
		
		return ERROR;
	}

	public boolean esEtiqueta(String tx){
		if(tx.contains(":")){
			return true;
		}
		return false;
	}
	

	/**
	 *Metodo que divide el codigo en .text y .data
	 *@param sin parametros
	 *@return String error
	 **/

	public String analiza()
	{
		String ERROR=null;
		String errdatos=null;
		String errtexto=null;
		String errtexto0x=null;
		String errEti=null;
		codigo.inicializar();
		String tx=quitaComent();
		String var=tx;
		StringTokenizer stk;
		Vector<String> partes;
		try{
			partes=this.divideCodigo(tx);
			RutinaTecladoUsuario.inicializar();
			errdatos=analizaDatos(partes.elementAt(Tipos.DATA));            
			errtexto=analizaTexto(partes.elementAt(Tipos.TEXT));
			errEti=buscaEtiquetasRutina(new String(partes.elementAt(Tipos.TEXT0X)));
			errtexto0x=analizaTexto0x(partes.elementAt(Tipos.TEXT0X));
			
			if(errdatos!=null){
				ERROR=errdatos;
				if(errtexto!=null)                    
					ERROR+=errtexto;
				if(errtexto0x!=null)                    
					ERROR+=errtexto0x;
				if(errEti!=null)                    
					ERROR+=errEti;
			}
			else{
				if(errtexto!=null)
					ERROR=errtexto;
				if(errtexto0x!=null)                    
					ERROR+=errtexto0x;
				if(errEti!=null)                    
					ERROR+=errEti;
			}			
		}catch(EnsamblarException e){
			ERROR=lenguaje.getString(e.getMensaje());
		}
//		if(tx.indexOf(".text")!=-1)
//		{
//		if(tx.indexOf(".data")!=-1)
//		{
//		if(tx.indexOf(".data") > tx.indexOf(".text"))
//		{
//		errdatos=analizaDatos(tx.substring(tx.indexOf(".data")+(".data").length()+1, tx.length()));
//		errtexto=analizaTexto(tx.substring(tx.indexOf(".text")+(".text").length()+1,tx.indexOf(".data")));                              
//		}
//		else
//		{
//		errdatos=analizaDatos(tx.substring(tx.indexOf(".data")+(".data").length()+1,tx.indexOf(".text"))); 
//		errtexto=analizaTexto(tx.substring(tx.indexOf(".text")+(".text").length()+1, tx.length()));
//		}            
//		if(errdatos!=null)                
//		{
//		ERROR=errdatos;
//		if(errtexto!=null)                    
//		ERROR+=errtexto;                    
//		}
//		else
//		if(errtexto!=null)
//		ERROR=errtexto;
//		}
//		else
//		errtexto=analizaTexto(tx.substring(tx.indexOf(".text")+(".text").length()+1,tx.length()));                              
//		if (errtexto!=null)
//		ERROR=errtexto;

//		}
//		else                    
//		ERROR=lenguaje.getString("falta.text");

		if(ERROR==null)
		{
			String error2;
			if((error2=Etiquetas.getError()).length()==0)
				return null;
			else
				return error2;
		}
		return ERROR;  
	}

	/**
	 *Metodo que analiza cada instruccion por separado.
	 *@param String instrucciones, Etiquetas etiquetas, Vector variables
	 *@return String error
	 **/
	public String analizaInstruc(String instruc, Etiquetas etiquetas, Vector variables, boolean isRutina)
	{        
		StringTokenizer st2=new StringTokenizer(instruc);
		boolean esValida=false;
		String token=null;
		Instruccion inst=null;
		String err=null;
		String error=null;    
		StringTokenizer st;
		RutinaTecladoUsuario rutina=null;
		int nTokens=0;
		if(st2.hasMoreTokens())        
			token=st2.nextToken();        

		if(st2.hasMoreTokens())
		{
			st=new StringTokenizer(st2.nextToken("\n"),",");
			nTokens=st.countTokens();
		}       
		else
			st=st2;
		String param[]=new String[nTokens]; 


		int i=0;
		for(i=0; i<nTokens;i++)  
		{            
			param[i]=st.nextToken(",").trim();          
		}

		for (i=0;i<instrucciones.length;i++)   //instrucciones=array del interface
		{
			esValida=false;        


			if (instrucciones[i].equals(token))
			{

				switch(i)
				{

				case ADD:         
					esValida=true;
					err=Add.esValida(param,Add.numParam());
					if(err==null)
					{
						inst=new Add(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                                                  
						error=err;

					break;

				case ADDI:
					esValida=true;
					err=Addi.esValida(param,Addi.numParam());
					if(err==null)
					{
						inst=new Addi(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}       
					}
					else        
						error=err;

					break;

				case ADDS:         
					esValida=true;
					err=Adds.esValidaF(param,Adds.numParam());
					if(err==null)
					{
						inst=new Adds(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                       
					}
					else                                                  
						error=err;

					break;

				case ADDD:         
					esValida=true;
					err=Addd.esValidaFD(param,Addd.numParam());
					if(err==null)
					{
						inst=new Addd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                       
					}
					else                                                  
						error=err;

					break;

				case SUB:
					esValida=true;
					err=Sub.esValida(param,Sub.numParam());
					if(err==null)
					{
						inst=new Sub(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case SUBS:
					esValida=true;
					err=Subs.esValidaF(param,Subs.numParam());
					if(err==null)
					{
						inst=new Subs(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;  

				case SUBD:
					esValida=true;
					err=Subd.esValidaFD(param,Subd.numParam());
					if(err==null)
					{
						inst=new Subd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;  

				case LUI:
					esValida=true;

					err=Lui.esValida(param,Lui.numParam());
					if(err==null)
					{
						inst=new Lui(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                   
					}
					else                         
						error=err;

					break;

				case SLT:
					esValida=true;
					err=Slt.esValida(param,Slt.numParam());
					if(err==null)
					{
						inst=new Slt(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                   
					}
					else                         
						error=err;

					break;

				case SLTI:
					esValida=true;
					err=Slti.esValida(param,Slti.numParam());
					if(err==null)
					{
						inst=new Slti(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case AND:
					esValida=true;
					err=And.esValida(param,And.numParam());
					if(err==null)
					{
						inst=new And(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                      
					}
					else                         
						error=err;

					break;

				case ANDI:
					esValida=true;
					err=Andi.esValida(param,Andi.numParam());
					if(err==null)
					{
						inst=new Andi(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                         
					}
					else        
						error=err;                   
					break;

				case OR:
					esValida=true;
					err=Or.esValida(param,Or.numParam());
					if(err==null)
					{
						inst=new Or(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                     
					}
					else                         
						error=err;                
					break;

				case ORI:
					esValida=true;
					err=Ori.esValida(param,Ori.numParam());
					if(err==null)
					{                        
						inst=new Ori(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case ABSS:
					esValida=true;
					err=Abss.esValidaF(param,Abss.numParam());
					if(err==null)
					{
						inst=new Abss(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case ABSD:
					esValida=true;
					err=Absd.esValidaFD(param,Absd.numParam());
					if(err==null)
					{
						inst=new Absd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case NEGS:
					esValida=true;
					err=Negs.esValidaF(param,Negs.numParam());
					if(err==null)
					{
						inst=new Negs(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case NEGD:
					esValida=true;
					err=Negd.esValidaFD(param,Negd.numParam());
					if(err==null)
					{
						inst=new Negd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case CEQS:
					esValida=true;
					err=Ceqs.esValidaF(param,Ceqs.numParam());
					if(err==null)
					{
						inst=new Ceqs(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case CEQD:
					esValida=true;
					err=Ceqd.esValidaFD(param,Ceqd.numParam());
					if(err==null)
					{
						inst=new Ceqd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case CLES:
					esValida=true;
					err=Cles.esValidaF(param,Cles.numParam());
					if(err==null)
					{
						inst=new Cles(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case CLED:
					esValida=true;
					err=Cled.esValidaFD(param,Cled.numParam());
					if(err==null)
					{
						inst=new Cled(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case CLTS:
					esValida=true;
					err=Clts.esValidaF(param,Clts.numParam());
					if(err==null)
					{
						inst=new Clts(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case CLTD:
					esValida=true;
					err=Cltd.esValidaFD(param,Cltd.numParam());
					if(err==null)
					{
						inst=new Cltd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
						error=err;

					break;

				case BEQ:
					esValida=true;
					err=Beq.esValida(param,etiquetas);
					if(err==null)
					{
						int pos=codigo.tamanho();
						inst=new Beq(param,pos);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                          
					}
					else                         
						error=err;

					break;

				case BNE:
					esValida=true;
					err=Bne.esValida(param,etiquetas);
					if(err==null)
					{
						int pos=codigo.tamanho();
						inst=new Bne(param,pos);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                          
					}
					else                         
						error=err;

					break;

				case BC1T:
					esValida=true;
					err=Bc1t.esValida(param,etiquetas);
					if(err==null)
					{
						int pos=codigo.tamanho();
						inst=new Bc1t(param,pos);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                          
					}
					else                         
						error=err;

					break;

				case BC1F:
					esValida=true;
					err=Bc1f.esValida(param,etiquetas);
					if(err==null)
					{
						int pos=codigo.tamanho();
						inst=new Bc1f(param,pos);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                          
					}
					else                         
						error=err;

					break;

				case INS_J:
					esValida=true;
					err=J.esValida(param,etiquetas);
					if(err==null)
					{
						inst=new J(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                          
					}
					else                         
						error=err;

					break;

				case LW:
					esValida=true;
					err=Lw.esValida(param);
					if(err==null)
					{
						inst=new Lw(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                       
					}
					else                         
						error=err;

					break;

				case LWC1:
					esValida=true;
					err=Lwc1.esValidaF(param);
					if(err==null)
					{
						inst=new Lwc1(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                       
					}
					else                         
						error=err;

					break;

				case SW:
					esValida=true;
					err=Sw.esValida(param);
					if(err==null)
					{
						inst=new Sw(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                         
					}
					else                         
						error=err;

					break;

				case SWC1:
					esValida=true;
					err=Swc1.esValidaF(param);
					if(err==null)
					{
						inst=new Swc1(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                         
					}
					else                         
						error=err;

					break;

				case LB:
					esValida=true;
					err=Lb.esValida(param);
					if(err==null)
					{
						inst=new Lb(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							if(isRutina){
								((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
							}else{
								codigo.anhadir(inst);
							}
						}                          
					}
					else                         
						error=err;

					break;

				case SB:
					esValida=true;
					err=Sb.esValida(param);
					if(err==null)
					{
						inst=new Sb(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                         
					}
					else                         
						error=err;

					break;

				case LA:
					esValida=true;
					err=La.esValida(param);
					if(err==null)
					{                       
						Palabra etiqueta=new Palabra(Memoria.getMemoria().getDireccion(param[1].trim()));
						String upper=etiqueta.getHex().substring(0,6);
						String lower="0x"+etiqueta.getHex().substring(6,10);                            
						param[1]=upper;

						inst=new Lui(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}     
						String parametros[]=new String[3];
						parametros[0]=param[0];
						parametros[1]=param[0];
						parametros[2]=lower;  
						inst=new Ori(parametros);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else                         
					{
						error=err;                       
					}

					break;   

				case JAL:
					esValida=true;
					err=Jal.esValida(param, etiquetas);
					if(err==null)
					{
						inst=new Jal(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;

				case JR:
					esValida=true;
					err=Jr.esValida(param);
					if(err==null)
					{
						inst=new Jr(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;

				case MULT:
					esValida=true;
					err=Mult.esValida(param,Mult.numParam());
					if(err==null)
					{
						inst=new Mult(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;

				case MULS:
					esValida=true;
					err=Muls.esValidaF(param,Muls.numParam());
					if(err==null)
					{
						inst=new Muls(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case MULD:
					esValida=true;
					err=Muld.esValidaFD(param,Muld.numParam());
					if(err==null)
					{
						inst=new Muld(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case DIV:
					esValida=true;
					err=Div.esValida(param,Div.numParam());
					if(err==null)
					{
						inst=new Div(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;                        

				case DIVS:
					esValida=true;
					err=Divs.esValidaF(param,Divs.numParam());
					if(err==null)
					{
						inst=new Divs(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case DIVD:
					esValida=true;
					err=Divd.esValidaFD(param,Divd.numParam());
					if(err==null)
					{
						inst=new Divd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case MFHI:
					esValida=true;
					err=Mfhi.esValida(param,Mfhi.numParam());
					if(err==null)
					{
						inst=new Mfhi(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;                             

				case MFLO:
					esValida=true;
					err=Mflo.esValida(param,Mflo.numParam());
					if(err==null)
					{
						inst=new Mflo(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break; 

				case MTC1:
					esValida=true;
					err=Mtc1.esValidaFR(param,Mtc1.numParam());
					if(err==null)
					{
						inst=new Mtc1(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;                             

				case MFC1:
					esValida=true;
					err=Mfc1.esValidaRF(param,Mfc1.numParam());
					if(err==null)
					{
						inst=new Mfc1(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;                             

				case MOVS:
					esValida=true;
					err=Movs.esValidaF(param,Movs.numParam());
					if(err==null)
					{
						inst=new Movs(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;                             

				case MOVD:
					esValida=true;
					err=Movd.esValidaFD(param,Movd.numParam());
					if(err==null)
					{
						inst=new Movd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;                             

				case CVTDS:
					esValida=true;
					err=Cvtds.esValida(param,Cvtds.numParam());
					if(err==null)
					{
						inst=new Cvtds(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case CVTDW:
					esValida=true;
					err=Cvtdw.esValida(param,Cvtdw.numParam());
					if(err==null)
					{
						inst=new Cvtdw(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case CVTSD:
					esValida=true;
					err=Cvtsd.esValida(param,Cvtsd.numParam());
					if(err==null)
					{
						inst=new Cvtsd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case CVTSW:
					esValida=true;
					err=Cvtsw.esValidaFR(param,Cvtsw.numParam());
					if(err==null)
					{
						inst=new Cvtsw(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case CVTWS:
					esValida=true;
					err=Cvtws.esValidaRF(param,Cvtws.numParam());
					if(err==null)
					{
						inst=new Cvtws(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    

				case CVTWD:
					esValida=true;
					err=Cvtwd.esValida(param,Cvtwd.numParam());
					if(err==null)
					{
						inst=new Cvtwd(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;    


				case LI:

					esValida=true;
					err=Li.esValida(param,Lui.numParam());

					if(err==null){

						Palabra etiqueta;

						if(param[1].startsWith("0x")){
							etiqueta=new Palabra(param[1].trim(), true);
						}else{
							int numEntero = Integer.parseInt(param[1].trim());
							etiqueta=new Palabra(numEntero);
						}
						String upper=etiqueta.getHex().substring(0, 6);
						String lower="0x"+etiqueta.getHex().substring(6, 10);
						param[1]=upper;

						inst=new Lui(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}

						String parametros[]=new String[3];
						parametros[0]=param[0];
						parametros[1]=param[0];
						parametros[2]=lower;
						inst=new Ori(parametros);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}

					}
					else                         
						error=err;
					break;

				case NOP:                        
					esValida=true;
					err=Nop.esValida(param);
					if(err==null)
					{
						inst=new Nop();
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else
						error=err;
					break;      

				case SYSCALL:
					esValida=true;
					if(isRutina){
						((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(new Syscall());
					}else{
						codigo.anhadir(new Syscall());
					}                            
					break;

				case MFC0:

					esValida=true;
					err=Mfc0.esValida(param,Mfc0.numParam());

					if(err==null){

						inst=new Mfc0(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}                       
					}
					else{                   
						error=err;
					}
					break;

				case RFE:
					esValida=true;
					err=Rfe.esValida(new String[0]);

					if(err==null){
						inst=new Rfe();                    	
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}else{
						error=err;
					}

					break;
				case SLL:
					esValida=true;
					err=Sll.esValida(param,Sll.numParam());
					if(err==null)
					{
						inst=new Sll(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else						
						error=err;

					break;
					
				case SRL:
					esValida=true;
					err=Srl.esValida(param,Srl.numParam());
					if(err==null)
					{
						inst=new Srl(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else						
						error=err;

					break;
				case SRA:
					esValida=true;
					err=Sra.esValida(param,Sra.numParam());
					if(err==null)
					{
						inst=new Sra(param);
						if(isRutina){
							((RutinaTecladoUsuario)RutinaTecladoUsuario.getInstancia()).setInstruccion(inst);
						}else{
							codigo.anhadir(inst);
						}
					}
					else						
						error=err;

					break;
				default:
					esValida=false;
				break;
				}
				break;                              
			}
			else
			{

				esValida=false;
				error=token+lenguaje.getString("instruccionNoValida");
			}
		}

		if (!esValida || err!=null)    
		{
			return "\t"+lenguaje.getString("instruccionNoValida");
		}
		else
			return null;

	}     

	/**
	 *Metod que analiza cada dato individualmente
	 *@param String dato
	 *@return error
	 **/
	public String analizaDato(String token)
	{
		String error=null;
		Vector variable=new Vector();
		Vector aux=new Vector();
		boolean esValida=false;


		if(token.indexOf(':')!=-1){

			StringTokenizer st=new StringTokenizer(token,":");
			String tk=st.nextToken();
			int i=0;
			if(memoria.estaVariable(tk)){
				error=lenguaje.getString("variableYaDefinida")+"\n";
			}else{
				variable.addElement(tk);

			}

			if(st.hasMoreTokens() && (error==null))
			{  

				tk=st.nextToken();                                           
				StringTokenizer st2=new StringTokenizer(tk);

				if(st2.hasMoreTokens() && (error==null))
				{

					tk=st2.nextToken();                        
					for (i=0;i<datos.length;i++)   //instrucciones=array del interface        
					{                                                                                             
						if (datos[i].equals(tk))
							break;
					}

					if(i==datos.length){
						error=lenguaje.getString("tipoDeDatosNoValido");
					}
					else{
						variable.addElement(tk);

					}

					if(!st2.hasMoreTokens())
						error=lenguaje.getString("datosSinInicializar");                        
					else                            
					{
						tk=st2.nextToken("\n"); 
						switch(i)                
						{    
						case ASCII:                                               
							aux=Ascii.esVal(tk);
							if (aux.size()>0)
							{                                     
								esValida=true;
								variable.addAll(aux);
								memoria.push(variable);                         
							}                                                  
							break;

						case ASCIIZ:                                               
							aux=Asciiz.esVal(tk);
							if (aux.size()>0)
							{                                     
								esValida=true;
								variable.addAll(aux);
								memoria.push(variable);                         
							}

							break;

						case WORD:                      

							aux=Word.esVal(tk); 
							if (aux.size()>0)
							{                                     
								esValida=true;
								variable.addAll(aux);
								memoria.push(variable);

							}
							else
								error=lenguaje.getString("parametrosIncorrectos");

							break;                                                                

						case SPACE:
							aux=Space.esVal(tk);
							if (aux.size()>0)
							{                                     
								esValida=true;
								variable.addAll(aux);
								memoria.push(variable);                         
							}
							else
							{
								error=lenguaje.getString("parametrosIncorrectos");
							}

							break;  

						case FLOTANTE:
							aux=Flotante.esVal(tk);
							if (aux.size()>0)
							{                                     
								esValida=true;
								variable.addAll(aux);
								memoria.push(variable);                         
							}
							else
							{
								error=lenguaje.getString("parametrosIncorrectos");
							}

							break;     

						case DOBLE:
							aux=Flotante.esVal(tk);
							if (aux.size()>0)
							{                                     
								esValida=true;
								variable.addAll(aux);
								memoria.push(variable);                         
							}
							else
							{
								error=lenguaje.getString("parametrosIncorrectos");
							}

							break;     
						}

						if (!esValida)
						{
							error=lenguaje.getString("tipoDeDatosNoValido");
						}
					}
				}
				else
					error=lenguaje.getString("datosIncorrectos");                    
			}
			else
				error=lenguaje.getString("definicionDeDatosIncorrecta");
		}
		else
			error=lenguaje.getString("datosIncorrectos");

		///memBase vale para establecer como memoria inicial estable
		if(error==null)
			memoria.memBase();
		return error; 
	}
}
