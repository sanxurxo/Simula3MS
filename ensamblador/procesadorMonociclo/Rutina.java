package ensamblador.procesadorMonociclo;

import java.util.Hashtable;
import java.util.Vector;

import ensamblador.datos.Etiquetas;
import ensamblador.datos.Memoria;
import ensamblador.datos.Palabra;
import ensamblador.datos.Tipos;
import ensamblador.instrucciones.Addi;
import ensamblador.instrucciones.Andi;
import ensamblador.instrucciones.Beq;
import ensamblador.instrucciones.Bne;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.J;
import ensamblador.instrucciones.Jal;
import ensamblador.instrucciones.Jr;
import ensamblador.instrucciones.Lui;
import ensamblador.instrucciones.Lw;
import ensamblador.instrucciones.Mfc0;
import ensamblador.instrucciones.Ori;
import ensamblador.instrucciones.Rfe;
import ensamblador.instrucciones.Sra;
import ensamblador.instrucciones.Sw;
import ensamblador.util.excepcion.DuplicatedLabelException;
import ensamblador.util.excepcion.ModelException;

public abstract class Rutina implements Tipos {
	protected Palabra base;
	protected Hashtable<String, Instruccion> instrucciones=null;
	protected String param[];
	protected Vector<String> pcs;
	protected void crearRutina(){
		/*Es necesario este vector de pcs para
		 * mantener el orden de las instrucciones
		 * y asi poder visualizar el codigo ordenado
		 * con toString()*/		
		pcs=new Vector<String>();
		base=new Palabra("0x80000080",true);
		/*En primer lugar registramos las etiquetas*/
		
		 try{
        	 Etiquetas.getEtiquetas().anhadirEtiquetaRutina("net", "0x800000c0");
         }catch(DuplicatedLabelException e){
        	 e.printStackTrace();            	
         }
		
         try{
        	 Etiquetas.getEtiquetas().anhadirEtiquetaRutina("echo", "0x80001080");
         }catch(DuplicatedLabelException e){
        	 e.printStackTrace();
         }
      
         try{
        	 Etiquetas.getEtiquetas().anhadirEtiquetaRutina("exit", "0x800000c0");
         }catch(DuplicatedLabelException e){
        	 e.printStackTrace();
         }

         try{
        	 Etiquetas.getEtiquetas().anhadirEtiquetaRutina("xready", "0x80001084");
         }catch(DuplicatedLabelException e){
        	 e.printStackTrace();
         }
         
         
		param=new String[2];
		
		
		/*almacenar $v0 y $a0 en memoria
		 * la $k1, s1--->lui $k1, direcc[0,6]
		 * 			 --->ori $k1, $k1, direcc[6,10]*/
		 		
			
           
	
			/*Almacenamos los registros que vamos a utilizar $v0 y $a0 en la pila*/
			/*Para ello desplazamos el puntero de pila e insertamos el valor en esa posicion*/
		
		
		  	/*addi $sp, $sp, -16*/
	        param=new String[3];
	        param[0]="$sp";
	        param[1]="$sp";
	        param[2]="-16";	       
	        if(Addi.esValida(param, Addi.numParam())!=null){
	       	 System.out.println("error interno creando la rutina 1");
	        }
	        pcs.add(base.getHex());
	        instrucciones.put(base.getHex(), new Addi(param));             
			
             /*sw $v0, 0($sp)*/
             param=new String[2];
             param[0]="$v0";
             param[1]="0($sp)";
             base=base.sumar(4);
             if(Sw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 11");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Sw(param));
            
//             /*addi $sp, $sp, -4*/
// 	        param=new String[3];
// 	        param[0]="$sp";
// 	        param[1]="$sp";
// 	        param[2]="-4";
// 	        base=base.sumar(4);
// 	        if(Addi.esValida(param, Addi.numParam())!=null){
// 	       	 System.out.println("error interno creando la rutina 1");
// 	        }
// 	        pcs.add(base.getHex());
// 	        instrucciones.put(base.getHex(), new Addi(param));             
// 			
             
             /*sw $a0, 0($sp)*/
             param=new String[2];
             param[0]="$a0";
             param[1]="4($sp)";
             base=base.sumar(4);
             if(Sw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 12");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Sw(param));
             
//             /*addi $sp, $sp, -4*/
//  	        param=new String[3];
//  	        param[0]="$sp";
//  	        param[1]="$sp";
//  	        param[2]="-4";
//  	        base=base.sumar(4);
//  	        if(Addi.esValida(param, Addi.numParam())!=null){
//  	       	 System.out.println("error interno creando la rutina 1");
//  	        }
//  	        pcs.add(base.getHex());
//  	        instrucciones.put(base.getHex(), new Addi(param));             
  			
             
             /*sw $a1, 8($sp)*/
             param=new String[2];
             param[0]="$a1";
             param[1]="8($sp)";
             base=base.sumar(4);
             if(Sw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 12");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Sw(param));
            
              
              /*sw $ra, 0($sp)*/
              param=new String[2];
              param[0]="$ra";
              param[1]="12($sp)";
              base=base.sumar(4);
              if(Sw.esValida(param)!=null){
             	 System.out.println("error interno creando la rutina 12");
              }
              pcs.add(base.getHex());
              instrucciones.put(base.getHex(), new Sw(param));
             
             /*mfc0 $k0, $13*/
             param=new String[2];
             param[0]="$k0";
             param[1]="$13";
             base=base.sumar(4);
             if(Mfc0.esValida(param, Mfc0.numParam())!=null){
            	 System.out.println("error interno creando la rutina 13");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Mfc0(param));
             
             /*$sra, $k0,$k0,2*/
             param=new String[3];
             param[0]="$k0";
             param[1]="$k0";
             param[2]="2";
             base=base.sumar(4);
             if(Sra.esValida(param, Sra.numParam())!=null){
            	 System.out.println("error interno creando la rutina 14");
           }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Sra(param));
             
             /*andi $v0, $k0, 0x000F*/             
             param=new String[3];
             param[0]="$v0";
             param[1]="$k0";
             param[2]="0x000F";
             base=base.sumar(4);
             if(Andi.esValida(param, Andi.numParam())!=null){
            	 System.out.println("error interno creando la rutina 15");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Andi(param));     
             /*slti $v0, $k0, 0x44*/
//             param=new String[3];
//             param[0]="$v0";
//             param[1]="$k0";
//             param[2]="0x44";
//             base=base.sumar(4);
//             if(Slti.esValida(param, Slti.numParam())!=null){
//            	 System.out.println("error interno creando la rutina 11");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Slti(param));
             
            
             /*bne $v0, $0, net*/
             param=new String[3];
             param[0]="$v0";
             param[1]="$0";
             param[2]="net";             
             base=base.sumar(4);
             if(Beq.esValida(param, Etiquetas.getEtiquetas())!=null){
            	 System.out.println("error interno creando la rutina 16");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Bne(param, instrucciones.size()));
             
             
             
             
             /* li $a0, 0xffff0000--->lui $a0, direcc[0,6]
     		 * 			 --->ori $a0, $a0, direcc[6,10]*/
     		 	
                  param=new String[2];            
             	 Palabra etiqueta=new Palabra("0xffff0000", true);
             	 
             	 String upper=etiqueta.getHex().substring(0, 6);
             	 String lower="0x"+etiqueta.getHex().substring(6, 10);
             	 param[0]="$a0";
             	 param[1]="0xffff";
             	  base=base.sumar(4);
             	 if(Lui.esValida(param, Lui.numParam())!=null){
             		 System.out.println("error interno creando la rutina 1");
             	 }
             	 pcs.add(base.getHex());
                  instrucciones.put(base.getHex(), new Lui(param));

                  
                  String parametros[]=new String[3];
                  parametros[0]=param[0];
                  parametros[1]=param[0];
                  parametros[2]=lower;
                  base=base.sumar(4);
                  if(Ori.esValida(parametros, Ori.numParam())!=null){
                 	 System.out.println("error interno creando la rutina 2");
                  }
                  pcs.add(base.getHex());
                  instrucciones.put(base.getHex(), new Ori(parametros));    

                  /*lw $v0, 0($a0)*/
                  param=new String[2];
                  param[0]="$v0";
                  param[1]="0($a0)";
                  base=base.sumar(4);
                  if(Lw.esValida(param)!=null){
                 	 System.out.println("error interno creando la rutina 3");
                  }
                  pcs.add(base.getHex());
                  instrucciones.put(base.getHex(), new Lw(param));

                  
                  /*andi $v0, $v0, 1*/             
                  param=new String[3];
                  param[0]="$v0";
                  param[1]="$v0";
                  param[2]="1";
                  base=base.sumar(4);
                  if(Andi.esValida(param, Andi.numParam())!=null){
                 	 System.out.println("error interno creando la rutina 4");
                  }
                  pcs.add(base.getHex());
                  instrucciones.put(base.getHex(), new Andi(param));     
                  
                  /*beq $v0, $0, exit*/
                  param=new String[3];
                  param[0]="$v0";
                  param[1]="$0";
                  param[2]="exit";             
                  base=base.sumar(4);
                  if(Beq.esValida(param, Etiquetas.getEtiquetas())!=null){
                 	 System.out.println("error interno creando la rutina 5");
                  }
                  pcs.add(base.getHex());
                  instrucciones.put(base.getHex(), new Beq(param, instrucciones.size()));
                  
                  
             
             
             
             
             
             
             /*jal echo*/
             param=new String[1];
             param[0]="echo";
             base=base.sumar(4);
             if(Jal.esValida(param, Etiquetas.getEtiquetas())!=null){
            	 System.out.println("error interno creando la rutina 17");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Jal(param));
             
             
             /* la $k1, s1--->lui $k1, direcc[0,6]
              * 			 --->ori $k1, $k1, direcc[6,10]*/

//             param=new String[2];
//             etiqueta=new Palabra(Memoria.getMemoria().getDireccion("s1"));
//             upper=etiqueta.getHex().substring(0,6);
//             lower="0x"+etiqueta.getHex().substring(6,10);                            
//             param[0]="$k1";
//             param[1]=upper;
//             base=base.sumar(4);
//             if(Lui.esValida(param, Lui.numParam())!=null){
//            	 System.out.println("error interno creando la rutina 14");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Lui(param));
//
//             parametros=new String[3];
//             parametros[0]=param[0];
//             parametros[1]=param[0];
//             parametros[2]=lower;  
//             base=base.sumar(4);
//             if(Ori.esValida(parametros, Ori.numParam())!=null){
//            	 
//            	 System.out.println("error interno creando la rutina 15");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Ori(parametros));	


             
             /*Restauramos los valores de los registros a partir de lo almacenado en la pila*/

          
             
             /*lw $ra, 0($sp)*/
             param=new String[2];
             param[0]="$ra";
             param[1]="12($sp)";
             base=base.sumar(4);
             if(Lw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 18");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Lw(param));
             
             /*lw $a1, 0($sp)*/
             param=new String[2];
             param[0]="$a1";
             param[1]="8($sp)";
             base=base.sumar(4);
             if(Lw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 18");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Lw(param));
             
//             /*addi $sp, $sp, 4*/
//  	        param=new String[3];
//  	        param[0]="$sp";
//  	        param[1]="$sp";
//  	        param[2]="4";
//  	        base=base.sumar(4);
//  	        if(Addi.esValida(param, Addi.numParam())!=null){
//  	       	 System.out.println("error interno creando la rutina 1");
//  	        }
//  	        pcs.add(base.getHex());
//  	        instrucciones.put(base.getHex(), new Addi(param));   
             /*lw $a0, 0($sp)*/
             param=new String[2];
             param[0]="$a0";
             param[1]="4($sp)";
             base=base.sumar(4);
             if(Lw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 18");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Lw(param));
             
//             /*addi $sp, $sp, 4*/
//  	        param=new String[3];
//  	        param[0]="$sp";
//  	        param[1]="$sp";
//  	        param[2]="4";
//  	        base=base.sumar(4);
//  	        if(Addi.esValida(param, Addi.numParam())!=null){
//  	       	 System.out.println("error interno creando la rutina 1");
//  	        }
//  	        pcs.add(base.getHex());
//  	        instrucciones.put(base.getHex(), new Addi(param));             
             
             /*lw $v0, 0($sp)*/
             param=new String[2];
             param[0]="$v0";
             param[1]="0($sp)";
             base=base.sumar(4);
             if(Lw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 19");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Lw(param));
    
             /*addi $sp, $sp, 16*/
	   	        param=new String[3];
	   	        param[0]="$sp";
	   	        param[1]="$sp";
	   	        param[2]="16";
	   	        base=base.sumar(4);
	   	        if(Addi.esValida(param, Addi.numParam())!=null){
	   	       	 System.out.println("error interno creando la rutina 1");
	   	        }
	   	        pcs.add(base.getHex());
	   	        instrucciones.put(base.getHex(), new Addi(param));             
	              
             
             /*mfc0 $k0, $14*/
             param=new String[2];
             param[0]="$k0";
             param[1]="$14";
             base=base.sumar(4);
             if(Mfc0.esValida(param, Mfc0.numParam())!=null){
            	 System.out.println("error interno creando la rutina 20");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Mfc0(param));
    
             /*addi $k0, $k0, 4*/
             param=new String[3];
             param[0]="$k0";
             param[1]="$k0";
             param[2]="4";
             base=base.sumar(4);
             if(Addi.esValida(param, Addi.numParam())!=null){
            	 System.out.println("error interno creando la rutina 21");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Addi(param));             
             
             /*rfe*/
             param=new String[0];
             base=base.sumar(4);
             if(Rfe.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 22");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Rfe());        
             
             /*jr $k0*/
             param=new String[1];             
             param[0]="$k0";
             base=base.sumar(4);             
             if(Jr.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 23");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Jr(param));
            
	}
	
	public Instruccion getInstruccion(String pc) throws ModelException{
		
		Instruccion instruccion=instrucciones.get(pc);		
		if(instruccion==null){
			throw new ModelException();
		}
		return instruccion;
	}
	
	public boolean estaEnRango(String pc){
		Instruccion instruccion=instrucciones.get(pc);
		if(instruccion==null){
			return false;
		}
		return true;
	}
	
	public String toString(){
		
		String resultado=new String();
		Instruccion instruccion=null;
		String clave=new String();
		for(int i=0;i<pcs.size();i++){
			clave=pcs.elementAt(i);
			instruccion=instrucciones.get(clave);		
			resultado+="["+clave+"]\t"+instruccion.getCodificacion()+"   \t"+instruccion.toString()+"\n";
		}
		
		
		return resultado;
		
	}
	
	public int getTamanho(){
		return instrucciones.size();
	}
}
