package ensamblador.estado;

import java.util.Observable;
import ensamblador.datos.Lenguaje;

public class Riesgos extends Observable {
	
	private Lenguaje lenguaje;
	private int riesgosControl=0;
	private int riesgosEstructurales=0;
	private int riesgosRAW=0;
	private int riesgoCarga=0;	
	private int riesgosWAW=0;
	private int riesgosWAR=0;
	private int riesgosRAWMem=0;
	private int riesgosWAWMem=0;
	private int riesgosWARMem=0;
	private int numNOPs=0;
	private String configuracion=new String();
	private int numInstrucciones=0;
	private int integer=0;
	private int add=0;
	private int mult=0;
	private int div=0;
	private int carga=0;
	private int almacenamiento=0;
	private int latInteger=0;
	private int latAdd=0;
	private int latMult=0;
	private int latDiv=0;
	private int ciclos=0;
	private static Riesgos riesgos=null;
	
	

	private Riesgos() {
		inicializar();
		lenguaje = Lenguaje.getInstancia();
	}

	public void inicializar(){
		riesgosControl=0;
		riesgosEstructurales=0;
		riesgosRAW=0;
		riesgosWAW=0;
		riesgosWAR=0;
		riesgoCarga=0;	
		numNOPs=0;
		configuracion=new String();
		numInstrucciones=0;
		integer=0;
		add=0;
		mult=0;
		div=0;
		carga=0;
		almacenamiento=0;
		latInteger=0;
		latAdd=0;
		latMult=0;
		latDiv=0;
		ciclos=0;
		
	}
	
	public static Riesgos getRiesgos(){
		if(riesgos==null){
			riesgos=new Riesgos();			
		}
		return riesgos;
	}

	public int getRiesgosControl() {
		return riesgosControl;
	}

	public void setRiesgosControl(int riesgosControl) {		
		this.riesgosControl = riesgosControl;
	}

	public int getRiesgosEstructurales() {
		return riesgosEstructurales;
	}

	public void setRiesgosEstructurales(int riesgosEstructurales) {		
		this.riesgosEstructurales = riesgosEstructurales;
	}

	public int getRiesgosRAW() {
		return riesgosRAW;
	}

	public void setRiesgosRAW(int riesgosRAW) {		
		this.riesgosRAW = riesgosRAW;
	}

	public int getRiesgosWAR() {
		return riesgosWAR;
	}

	public void setRiesgosWAR(int riesgosWAR) {		
		this.riesgosWAR = riesgosWAR;
	}

	public int getRiesgosWAW() {
		return riesgosWAW;
	}

	public void setRiesgosWAW(int riesgosWAW) {
		this.riesgosWAW = riesgosWAW;
	}

	public String toString(){
		return new String("Hay "+this.riesgosControl +" riegos de control, " +
				this.riesgosEstructurales+" riesgos estructurales, "+this.riesgosRAW+" " +
						"riesgos RAW, "+ this.riesgosWAR+ " riesgos WAR y "+ this.riesgosWAW+"" +
								"riesgos WAW");
	}

	public String getConfiguracion() {
		return configuracion;
	}

	public void setConfiguracion(String configuracion) {
		this.configuracion = configuracion;
	}

	public int getNumInstrucciones() {
		return numInstrucciones;
	}

	public void setNumInstrucciones(int numInstrucciones) {
		this.numInstrucciones = numInstrucciones;
	}

	public int getAdd() {
		return add;
	}

	public void setAdd(int add) {
		this.add = add;
	}

	public int getAlmacenamiento() {
		return almacenamiento;
	}

	public void setAlmacenamiento(int almacenamiento) {
		this.almacenamiento = almacenamiento;
	}

	public int getCarga() {
		return carga;
	}

	public void setCarga(int carga) {
		this.carga = carga;
	}

	public int getDiv() {
		return div;
	}

	public void setDiv(int div) {
		this.div = div;
	}

	public int getInteger() {
		return integer;
	}

	public void setInteger(int integer) {
		this.integer = integer;
	}

	public int getLatAdd() {
		return latAdd;
	}

	public void setLatAdd(int latAdd) {
		this.latAdd = latAdd;
	}

	public int getLatDiv() {
		return latDiv;
	}

	public void setLatDiv(int latDiv) {
		this.latDiv = latDiv;
	}

	public int getLatInteger() {
		return latInteger;
	}

	public void setLatInteger(int latInteger) {
		this.latInteger = latInteger;
	}

	public int getLatMult() {
		return latMult;
	}

	public void setLatMult(int latMult) {
		this.latMult = latMult;
	}

	public int getMult() {
		return mult;
	}

	public void setMult(int mult) {
		this.mult = mult;
	}
	
	public int getCiclos() {
		return ciclos;
	}

	public void setCiclos(int ciclos) {
		this.ciclos = ciclos;
	}
	
	
	public String getBuffer(int num){
		if(num==1){
			return lenguaje.getString("1bufferde");
		}
		return (num+" "+lenguaje.getString("buffersde"));
		
	}
	
	public String toStringConfiguracion(){
		String config=new String();
		
		config="\t"+lenguaje.getString(this.getConfiguracion()).toUpperCase()+"\n";
		if(this.getConfiguracion().equals(new String("Marcador"))){
			config+=this.getInteger()+" "+lenguaje.getString("FUenteradelatencia")+" "+this.getLatInteger()+"\n";
			config+=this.getAdd()+" "+lenguaje.getString("FUdesumadelatencia")+" "+this.getLatAdd()+"\n";
			
			config+=this.getMult()+" "+lenguaje.getString("FUdemultiplicaciondelatencia")+" "+this.getLatMult()+"\n";
		
			config+=this.getDiv()+" "+lenguaje.getString("FUdedivisiondelatencia")+" "+this.getLatDiv();
		}else{
			
			config+=this.getAdd()+" "+lenguaje.getString("ERsumadelatencia")+" "+this.getLatAdd()+"\n";
			
			config+=this.getMult()+" "+lenguaje.getString("ERmultiplicaciondelatencia")+" "+this.getLatMult()+"\n";
		
			config+=this.getDiv()+" "+lenguaje.getString("ERdivisiondelatencia")+" "+this.getLatDiv()+"\n";
			config+=getBuffer(this.getCarga())+" "+lenguaje.getString("decarga")+"\n";		
			config+=getBuffer(this.getAlmacenamiento())+ " "+lenguaje.getString("dealmacenamiento");
		
		}
		return config;
	}


	public String toStringCPI(){
		String cpi=new String();		
		cpi+="\n\n    "+lenguaje.getString("Numerodeinstruccionesejecutadas")+" "+this.getNumInstrucciones();
		cpi+="\n    "+lenguaje.getString("Numerodeciclos")+" "+this.getCiclos();
		cpi+="\n    CPI= "+new Float(new Float(this.getCiclos())/new Float(this.getNumInstrucciones())).toString();
		
		return cpi;		
	}

	public String toStringResumen(){
		String resumen=new String();
		
		resumen="\n    - "+lenguaje.getString("riesgosDeControl")+": "+this.getRiesgosControl();
		resumen+="\n    - "+lenguaje.getString("riesgosEstructurales")+": "+this.getRiesgosEstructurales();
		resumen+="\n    - "+lenguaje.getString("riesgosRAW")+": "+(this.getRiesgosRAW()+this.getRiesgoCarga());
		resumen+="\n            · "+lenguaje.getString("riesgosDeCarga")+": "+this.getRiesgoCarga();
		if(!this.getConfiguracion().equals("Tomasulo")){
			resumen+="\n    - "+lenguaje.getString("riesgosWAR")+": "+this.getRiesgosWAR();
			resumen+="\n    - "+lenguaje.getString("riesgosWAW")+": "+this.getRiesgosWAW();
		}else{
			resumen+="\n    - "+lenguaje.getString("memoria2")+": ";
			resumen+="\n            · "+lenguaje.getString("riesgosRAW")+": "+this.getRiesgosRAWMem();
			resumen+="\n            · "+lenguaje.getString("riesgosWAW")+": "+this.getRiesgosWAWMem();
			resumen+="\n            · "+lenguaje.getString("riesgosWAR")+": "+this.getRiesgosWARMem();
		}
		resumen+="\n    - "+lenguaje.getString("NumerodeNops")+": "+this.getNumNOPs();		
		
		return resumen;
	}

	public int getRiesgoCarga() {
		return riesgoCarga;
	}

	public void setRiesgoCarga(int riesgoCarga) {
		this.riesgoCarga = riesgoCarga;
	}

	public int getNumNOPs() {
		return numNOPs;
	}

	public void setNumNOPs(int numNOPs) {
		this.numNOPs = numNOPs;
	}

	public int getRiesgosRAWMem() {
		return riesgosRAWMem;
	}

	public void setRiesgosRAWMem(int riesgosRAWMem) {
		this.riesgosRAWMem = riesgosRAWMem;
	}

	public int getRiesgosWARMem() {
		return riesgosWARMem;
	}

	public void setRiesgosWARMem(int riesgosWARMem) {
		this.riesgosWARMem = riesgosWARMem;
	}

	public int getRiesgosWAWMem() {
		return riesgosWAWMem;
	}

	public void setRiesgosWAWMem(int riesgosWAWMem) {
		this.riesgosWAWMem = riesgosWAWMem;
	}
	

}


