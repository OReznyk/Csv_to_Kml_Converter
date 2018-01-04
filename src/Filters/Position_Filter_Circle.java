package Filters;
import WifiData.*;

/**
 * This class represents a simple position filter based on a 2D distance from a center Point
 * Note: this is a simple L2 distance - when using GEO coordinates - one can expect a sphere (and not a circle).
 * @author Boaz
 *
 */
public class Position_Filter_Circle implements filter{
	
	public Position_Filter_Circle(Coordinates_3D p, double dist) {
		_center = p;
		_dist = dist;
	}
	public boolean test(RowOfWifiPoints f) {
		boolean ans = false;
		if(f!=null) {
			Coordinates_3D pos = f.getCoordinates();
			double dist2D = pos.distance2D(_center);
			if(dist2D < _dist) {ans = true;}
		}
		return ans;
	}
	public String toString() {
		return ""+this.getClass().getName()+" cen:"+this._center+", radius:"+_dist;
	}
	/********** Private data can be located anywhere *************/
	private final Coordinates_3D _center;
	private double _dist;

}