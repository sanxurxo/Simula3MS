package ensamblador.componentes.bus;

import ensamblador.estado.DesambiguarMem;

public class DataTrunks {

	private static DataTrunks dataTrunks=null;
	private boolean busInt=false;
	private boolean busAdd=false;
	private boolean busMul=false;
	private boolean busDiv=false;
	
	private DataTrunks() {
		iniciarBuses();
		
	}

	public void iniciarBuses(){
		this.setBusDiv(false);
		this.setBusAdd(false);
		this.setBusInt(false);
		this.setBusMul(false);
	}
	
	
	public static DataTrunks getDataTrunks(){
		if(dataTrunks==null){
			dataTrunks=new DataTrunks();			
		}
		return dataTrunks;
	}
	

	public static void setDataTrunks(DataTrunks dataTrunks) {
		DataTrunks.dataTrunks = dataTrunks;
	}

	public boolean isBusAdd() {
		return busAdd;
	}

	public void setBusAdd(boolean busAdd) {
		this.busAdd = busAdd;
	}

	public boolean isBusDiv() {
		return busDiv;
	}

	public void setBusDiv(boolean busDiv) {
		this.busDiv = busDiv;
	}

	public boolean isBusInt() {
		return busInt;
	}

	public void setBusInt(boolean busInt) {
		this.busInt = busInt;
	}

	public boolean isBusMul() {
		return busMul;
	}

	public void setBusMul(boolean busMul) {
		this.busMul = busMul;
	}

}
