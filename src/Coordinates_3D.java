
/**
 * This class represents the simplest 3D coordinates
 * @author Olga Reznyk & Dan Michaeli
 *
 */
public class Coordinates_3D {
	protected double latitude;
	protected double longitude;
	protected double altitude;
	
Coordinates_3D(String latitude, String longitude, String altitude) {	
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		this.altitude = Double.parseDouble(altitude);	
	}

/*************getters&setters***************/
public double getLatitude() {return latitude;}
public double getLongitude() {return longitude;}
public double getAltitude() {return altitude;}

public String coordinatesToString(){
	return this.latitude+","+this.longitude+","+this.altitude;
}
}
