
public class Wifi {

		protected Signal signal;
		protected Frequncy frequncy;
		protected String mac;
		protected String ssid;


	Wifi( String ssid, String mac,String frequncy, String signal) { 
			this.frequncy=new Frequncy(frequncy);
			this.signal=new Signal(signal);
			this.mac=mac;
			this.ssid = ssid;
		}

	public String getMac() {
		return mac;
	}

	public String getSsid() {
		return ssid;
	}
	
	public boolean sameMAC(Wifi a){
		if(this.mac.equals(a.getMac()))return true;
		return false;
	}

	public String wifiToString(){
		return ssid+","+mac+","+frequncy.toString()+","+signal.toString();
	}

}
