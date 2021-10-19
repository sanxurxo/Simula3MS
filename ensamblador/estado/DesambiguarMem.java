package ensamblador.estado;

import java.util.Hashtable;
import java.util.Vector;

public class DesambiguarMem {

	private static DesambiguarMem desambiguarMem=null;
	private static Hashtable<String, Vector<Integer>>load=null;
	private static Hashtable<String, Vector<Integer>>store=null;
	private static Vector<Integer>ambLoad=null;
	private static Vector<Integer>ambStore=null;
	
	private DesambiguarMem() {
		load=new Hashtable<String, Vector<Integer>>();
		store=new Hashtable<String, Vector<Integer>>();
		ambLoad=new Vector<Integer>();
		ambStore=new Vector<Integer>();
	}

	public void inicializar(){
		load=new Hashtable<String, Vector<Integer>>();
		store=new Hashtable<String, Vector<Integer>>();
		ambLoad=new Vector<Integer>();
		ambStore=new Vector<Integer>();
	}
	
	public static DesambiguarMem getDesambiguarMem(){
		if(desambiguarMem==null){
			desambiguarMem=new DesambiguarMem();
		}
		return desambiguarMem;
	}
	
	public void registrarAmbLoad(Integer ciclo){
		ambLoad.addElement(ciclo);		
	}
	
	public void registrarAmbStore(Integer ciclo){
		ambStore.addElement(ciclo);		
	}
	public boolean esAmbLoad(Integer ciclo){
		if(ambLoad.indexOf(ciclo)==-1){
			return false;
		}
		return true;
	}
	
	public boolean esAmbStore(Integer ciclo){
		if(ambStore.indexOf(ciclo)==-1){
			return false;
		}
		return true;
	}
	
	public boolean ambLoadAnterior(Integer ciclo){
		for(int i=0;i<ambLoad.size();i++){
			if(ambLoad.get(i)<ciclo){
				return true;
			}
		}
		return false;
	}
	
	public boolean ambStoreAnterior(Integer ciclo){
		for(int i=0;i<ambStore.size();i++){
			if(ambStore.get(i)<ciclo){
				return true;
			}
		}
		return false;
	}
	
	public boolean desregistrarAmbLoad(Integer ciclo){
		return ambLoad.remove(ciclo);
	}
	
	public boolean desregistrarAmbStore(Integer ciclo){
		return ambStore.remove(ciclo);
	}
	
	public boolean esDestinoCarga(String direccion){
		this.toString();
		if(load.get(direccion)!=null){
			return true;
		}
		return false;
	}
	
	public boolean esDestinoAlmacenamiento(String direccion){
		this.toString();
		if(store.get(direccion)!=null){
			return true;
		}
		return false;
	}
	
	
	/**Metodo que registra una direccion destino de carga
	 * @param la direccion destino y la instruccion que va a leer de esa direcc
	 * @return true si fue posible escribir la direcc, y false si hubo algun problema **/
	public boolean registrarCarga(String direccion, Integer cicloEmision){
		if(load.get(direccion)==null){
			Vector<Integer> v=new Vector<Integer>();
			v.addElement(cicloEmision);
			load.put(direccion, v);
		
			
		return true;
		}else{
			Vector v=load.get(direccion);
			v.addElement(cicloEmision);
		}
		return false;
	}
	
	/**Metodo que registra una direccion destino de almacenamiento
	 * @param la direccion destino y la instruccion que va a escribir en esa direcc
	 * @return true si fue posible escribir la direcc, y false si hubo algun problema **/
	public boolean registrarAlmacenamiento(String direccion, Integer cicloEmision){
		if(store.get(direccion)==null){
			Vector<Integer> v=new Vector<Integer>();
			v.addElement(cicloEmision);
			store.put(direccion, v);
			
			return true;
		}else{
			Vector v=store.get(direccion);
			v.addElement(cicloEmision);
		}
		return false;
	}
	
	/**Metodo que borra una direccion destino decarga
	 * @param la direccion destino
	 * @return true si fue posible borrar la direcc, y false si hubo algun problema **/
	public boolean borrarCarga(String direccion, Integer cicloEmision){
		Vector<Integer> v=load.get(direccion);
		if(v!=null){
			v.remove(cicloEmision);
			if(v.size()==0){
				if(load.remove(direccion)!=null){
					this.toString();
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
	}
	
	public boolean cargaAnterior(String direccion, Integer cicloEmision){
		Vector<Integer> v;
		Integer ciclo;
		this.toString();
		v=load.get(direccion);
		if(v!=null){
			for(int i=0;i<v.size();i++){
				ciclo=v.elementAt(i);
				if(ciclo.intValue()<cicloEmision.intValue()){
					return true;
				}				
			}
		}
		return false;
	}
	
	public boolean almacenamientoAnterior(String direccion, Integer cicloEmision){
		Vector<Integer> v;
		Integer ciclo;
		this.toString();
		v=store.get(direccion);
		if(v!=null){
			for(int i=0;i<v.size();i++){
				ciclo=v.elementAt(i);
				if(ciclo.intValue()<cicloEmision.intValue()){
					return true;
				}				
			}
		}
		return false;
		
	}
	
	/**Metodo que borra una direccion destino de almacenamiento
	 * @param la direccion destino 
	 * @return true si fue posible borrar la direcc, y false si hubo algun problema **/
	public boolean borrarAlmacenamiento(String direccion, Integer cicloEmision){
		Vector<Integer> v=store.get(direccion);
		if(v!=null){
			v.remove(cicloEmision);
			if(v.size()==0){
				if(store.remove(direccion)!=null){
					this.toString();
					return true;
				}
				return false;
			}
			return true;
		}
		return false;
		
	}
	
	public String toString(){
		String string="Almacenamiento\n";
		string+=store.toString();
		string+="\n carga\n";
		string+=load.toString();
		
		return string;
	}
}
