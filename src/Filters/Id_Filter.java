package Filters;

import WifiData.*;

public class Id_Filter implements filter{

	public Id_Filter(String id) {
		_id = id;
}

public boolean test(RowOfWifiPoints f) {
	boolean ans = false;
	if(f!=null) {
		ans=f.getId().equals(_id);
	}
	return ans;
}

public String toString() {
	return ""+this.getClass().getName()+" ["+this._id+"]";
}
	
/********** Private data *************/
private String _id;

}