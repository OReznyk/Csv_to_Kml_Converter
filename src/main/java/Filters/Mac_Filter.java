package main.java.Filters;
import main.java.WifiData.*;

public class Mac_Filter implements filter{

	/**
	 * 
	 * @author Dan & Olga
	 *
	 */
	
	public Mac_Filter(String mac) {
		_mac = mac;
}

public boolean test(Wifi f) {
	boolean ans = false;
	if(f!=null) {
		ans=f.getMac().equals(_mac);
	}
	return ans;
}

public String toString() {
	return ""+this.getClass().getName()+" ["+this._mac+"]";
}
	
/********** Private data *************/
private String _mac;

@Override
public boolean test(RowOfWifiPoints f) {
	// TODO Auto-generated method stub
	boolean test=false;
	for (int i = 0; i < f.wifiList.size(); i++) {
		test=test(f.wifiList.get(i));
		if(!test) {
			f.wifiList.remove(i);
			i--;
		}
	}
	if(f.wifiList.isEmpty()) return false;
	else return true;
}



}
