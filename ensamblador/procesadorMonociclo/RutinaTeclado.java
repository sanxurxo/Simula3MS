package ensamblador.procesadorMonociclo;

import java.util.Hashtable;
import java.util.Vector;

import ensamblador.datos.Etiquetas;
import ensamblador.datos.Palabra;
import ensamblador.instrucciones.Addi;
import ensamblador.instrucciones.Andi;
import ensamblador.instrucciones.Beq;
import ensamblador.instrucciones.Instruccion;
import ensamblador.instrucciones.J;
import ensamblador.instrucciones.Jr;
import ensamblador.instrucciones.Lui;
import ensamblador.instrucciones.Lw;
import ensamblador.instrucciones.Ori;
import ensamblador.instrucciones.Sw;

public class RutinaTeclado extends Rutina {


	protected static RutinaTeclado rutinaTeclado=null;
	
	protected RutinaTeclado(boolean b){
		//necesito este constructor pq si no al crear la rutina del usuario llama al otro
		//q llama constructor al de la superclase y entonces se produce un error
		//pr no se han incluido en la mem las posiciones s1 y s2 
		instrucciones=new Hashtable<String, Instruccion>();
		pcs=new Vector<String>();
		base=new Palabra("0x80000080",true);
		
	}
	private RutinaTeclado(){
		instrucciones=new Hashtable<String, Instruccion>();
		this.crearRutina();
	}
	
	public static RutinaTeclado getInstancia(){
		if(rutinaTeclado==null){
			rutinaTeclado=new RutinaTeclado();
	
		}
		return rutinaTeclado;
	}
	
	protected void  crearRutina(){
		
		super.crearRutina();
		base=new Palabra("0x80001080",true);	
//		/* li $a0, 0xffff0000--->lui $a0, direcc[0,6]
//		 * 			 --->ori $a0, $a0, direcc[6,10]*/
//		 	
//             param=new String[2];            
//        	 Palabra etiqueta=new Palabra("0xffff0000", true);
//        	 
//        	 String upper=etiqueta.getHex().substring(0, 6);
//        	 String lower="0x"+etiqueta.getHex().substring(6, 10);
//        	 param[0]="$a0";
//        	 param[1]="0xffff";
//        	 if(Lui.esValida(param, Lui.numParam())!=null){
//        		 System.out.println("error interno creando la rutina 1");
//        	 }
//        	 pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Lui(param));
//
//             
//             String parametros[]=new String[3];
//             parametros[0]=param[0];
//             parametros[1]=param[0];
//             parametros[2]=lower;
//             base=base.sumar(4);
//             if(Ori.esValida(parametros, Ori.numParam())!=null){
//            	 System.out.println("error interno creando la rutina 2");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Ori(parametros));    
//
//             /*lw $v0, 0($a0)*/
//             param=new String[2];
//             param[0]="$v0";
//             param[1]="0($a0)";
//             base=base.sumar(4);
//             if(Lw.esValida(param)!=null){
//            	 System.out.println("error interno creando la rutina 3");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Lw(param));
//
//             
//             /*andi $v0, $v0, 1*/             
//             param=new String[3];
//             param[0]="$v0";
//             param[1]="$v0";
//             param[2]="1";
//             base=base.sumar(4);
//             if(Andi.esValida(param, Andi.numParam())!=null){
//            	 System.out.println("error interno creando la rutina 4");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Andi(param));     
//             
//             /*beq $v0, $0, exit*/
//             param=new String[3];
//             param[0]="$v0";
//             param[1]="$0";
//             param[2]="exit";             
//             base=base.sumar(4);
//             if(Beq.esValida(param, Etiquetas.getEtiquetas())!=null){
//            	 System.out.println("error interno creando la rutina 5");
//             }
//             pcs.add(base.getHex());
//             instrucciones.put(base.getHex(), new Beq(param, instrucciones.size()));
//             
//             
             
             /*lw $v0, 4($a0)*/
             param=new String[2];
             param[0]="$v0";
             param[1]="4($a0)";
       //      base=base.sumar(4);
             if(Lw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 6");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Lw(param));

             
             
             /*lw $a1, 8($a0)*/
             param=new String[2];
             param[0]="$a1";
             param[1]="8($a0)";
            base=base.sumar(4);
             if(Lw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 6");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Lw(param));

             
             /*andi $a1, $a1, 1*/
             param=new String[3];
             param[0]="$a1";
             param[1]="$a1";
             param[2]="1";
             base=base.sumar(4);
             if(Andi.esValida(param, Andi.numParam())!=null){
            	 System.out.println("error interno creando la rutina 21");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Andi(param));             
   
             
             
             /*beq $a1, $0, xreadyRut*/
             param=new String[3];
             param[0]="$a1";
             param[1]="$0";
             param[2]="xready";             
             base=base.sumar(4);
             if(Beq.esValida(param, Etiquetas.getEtiquetas())!=null){
            	 System.out.println("error interno creando la rutina 5");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Beq(param, instrucciones.size()));
             
             
             /*sw $v0, 0($k1)*/
             param=new String[2];
             param[0]="$v0";
             param[1]="12($a0)";
             base=base.sumar(4);
             if(Sw.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 7");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Sw(param));

             /*j net*/
             param=new String[1];
             param[0]="$ra";
             base=base.sumar(4);
             if(Jr.esValida(param)!=null){
            	 System.out.println("error interno creando la rutina 23");
             }
             pcs.add(base.getHex());
             instrucciones.put(base.getHex(), new Jr(param));

             this.toString();

	}
	
	
}
