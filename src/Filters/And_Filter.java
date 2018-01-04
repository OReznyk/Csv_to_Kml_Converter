package Filters;

import WifiData.*;

public class And_Filter implements filter{
	private filter _f1, _f2;
	
	public And_Filter(filter f1, filter f2) {
		_f1 = f1;
		_f2 = f2;
	}

	public boolean test(RowOfWifiPoints f) {
		return _f1.test(f) && _f2.test(f);
	}
	public String toString() {
		return "("+_f1+" and "+_f2+")";
	}

}