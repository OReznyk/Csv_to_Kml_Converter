package Filters;
import WifiData.*;

public class Position_Filter_Rect implements filter {
	
	public Position_Filter_Rect(Coordinates_3D a, Coordinates_3D b) {
		xTopRight = Math.max(a.longitude,b.longitude);
		yTopRight = Math.max(a.latitude,b.latitude);
		xBottomLeft = Math.min(a.longitude,b.longitude);
		yBottomLeft = Math.min(a.latitude,b.latitude);
		xBottomRight = xTopRight;
		yBottomRight = yBottomLeft;
		xTopLeft = xBottomLeft;
		yTopLeft = yTopRight;
//		double lat=Math.max(a.latitude,b.latitude);
//		_tr.setLatitude(lat);
//		_tr.setLongitude(Math.max(a.longitude, b.longitude));
//		_tr.altitude=0;
//		
//		_bl.latitude=Math.min(a.latitude, b.latitude);
//		_bl.longitude=Math.min(a.longitude, b.longitude);
//		_bl.altitude=0;
//		
//		
//		_br.longitude=_tr.longitude;
//		_br.latitude=_bl.latitude;
//		_br.altitude=0;
//		
//		_tl.longitude=_bl.longitude;
//		_tl.latitude=_tr.latitude;
//		_tl.altitude=0;

	}
	
	public boolean test(RowOfWifiPoints f) {

	if(f!=null) {
		double linelon = f.coordinates.latitude;
		double linelat= f.coordinates.longitude;
		if(linelat<=yTopRight && linelat>=yBottomRight && linelon<=xTopRight && linelon>=xTopLeft)
		{
			return true;
		}
	}
	
//			if(f.coordinates.latitude<_tr.latitude && f.coordinates.latitude>=_br.latitude && 
//					f.coordinates.longitude<_tr.longitude && f.coordinates.longitude>=_tl.longitude)
//			{
//				return true;
//			}
//		}
		return false;
	}
	public String toString() {
		return "Top right coordinate: "+xTopRight+" "+yTopRight +" ;  Botton left coordinate: "+xBottomLeft+" "+yBottomLeft;
	}
	
	/********** Private data can be located anywhere *************/
	private double xTopRight, yTopRight ,xBottomLeft,yBottomLeft,xBottomRight,yBottomRight, xTopLeft ,yTopLeft;


}
