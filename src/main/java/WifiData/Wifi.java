package main.java.WifiData;

/**
 * This class represents the simplest wifi single AP received beacon in time.
 * 
 * @author Olga & Dan
 *
 * 
 */


public class Wifi {

	public Signal signal;
	public String frequncy;
	public String mac;
	public String ssid;


	public Wifi( String ssid, String mac,String frequncy, String signal) { 
	     	this.frequncy=frequncy;
	     	this.signal=new Signal(signal);
			this.mac=mac;
			this.ssid = ssid;
		}
	
	
	@Override
	public String toString(){
		return ssid+","+mac+","+frequncy+","+signal.toString();
	}
	
	/*************getters&setters***************/
	
	public String getFrequncy() {return frequncy;}
	public double getSignal() {return this.signal.getSignal();}
	public void setSignal(String signal) {this.signal.setSignal(Double.parseDouble(signal));}
	public String getMac() {return mac;}
	public String getSsid() {return ssid;}	



	
	/*********************** MAC manipulations*************************/
	
	/**
	 * Function to check if two Wifi is equals by mac
	 * @param a Second Wifi for check up
	 * @return true if they equals
	 */
	public boolean equalMAC(Wifi a){
		if(this.mac.equals(a.getMac()))return true;
		return false;
	}



}
