package Filters;
import WifiData.*;

/**
 * KALLLL
 * @author Boaz
 *
 */
public class Not_Filter implements filter{

	
	public Not_Filter(filter f) {
		_filter = f;
	}
	public boolean test(RowOfWifiPoints rec) {
		return !(_filter.test(rec));
	}

	public String toString() {
		return "not("+_filter+")";
	}

	/********** Private data *************/
	private filter _filter;
}