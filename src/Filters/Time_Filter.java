package Filters;
import WifiData.*;

/**
	 * This class represents a simple time filter based on a 1D time window range
	 * @author Boaz
	 *
	 */
public class Time_Filter implements filter{

	public Time_Filter(String start, String end) {
		_start = new Date(start);
		_end = new Date(end);
}

public boolean test(RowOfWifiPoints f) {
	boolean ans = false;
	if(f!=null) {
		ans=f.date.betweenDates(_start, _end);
	}
	return ans;
}

public String toString() {
	return ""+this.getClass().getName()+" ["+this._start+","+_end+"]";
}
	
/********** Private data *************/
private Date _start, _end;

}