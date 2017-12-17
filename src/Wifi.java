
/**
 * This class represents the simplest wifi single AP received beacon in time.
 * 
 * @author Olga Reznyk & Dan Michaeli
 *
 * 
 */


public class Wifi {

		protected Signal signal;
		protected int frequncy;
		protected String mac;
		protected String ssid;


	Wifi( String ssid, String mac,String frequncy, String signal) { 
	     	this.frequncy=Integer.parseInt(frequncy);
	     	this.signal=new Signal(signal);
			this.mac=mac;
			this.ssid = ssid;
		}
	
	
	@Override
	public String toString(){
		return ssid+","+mac+","+frequncy+","+signal.toString();
	}
	
	/*************getters&setters***************/
	
	public int getFrequncy() {return frequncy;}
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
