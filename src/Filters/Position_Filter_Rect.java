package Filters;
import WifiData.*;

public class Position_Filter_Rect implements filter {
	
	public Position_Filter_Rect(Coordinates_3D a, Coordinates_3D b) {
		
		_tr.setLatitude(Math.max(a.latitude,b.latitude));
		_tr.longitude=Math.max(a.longitude, b.longitude);
		_tr.altitude=0;
		
		_bl.latitude=Math.min(a.latitude, b.latitude);
		_bl.longitude=Math.min(a.longitude, b.longitude);
		_bl.altitude=0;
		
		
		_br.longitude=_tr.longitude;
		_br.latitude=_bl.latitude;
		_br.altitude=0;
		
		_tl.longitude=_bl.longitude;
		_tl.latitude=_tr.latitude;
		_tl.altitude=0;

	}
	
	public boolean test(RowOfWifiPoints f) {

		if(f!=null) {
			if(f.coordinates.latitude<_tr.latitude && f.coordinates.latitude>=_br.latitude && 
					f.coordinates.longitude<_tr.longitude && f.coordinates.longitude>=_tl.longitude)
			{
				return true;
			}
		}
		return false;
	}
	public String toString() {
		return "Top right coordinate: "+_tr.toString()+" ;  Botton left coordinate: "+_bl.toString();
	}
	
	/********** Private data can be located anywhere *************/
	private  Coordinates_3D _tr;
	private  Coordinates_3D _tl;
	private  Coordinates_3D _br;
	private  Coordinates_3D _bl;

}
