
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
	if(latitude.contains("?")){
		this.latitude = 0;
	}
	if(longitude.contains("?")){
		this.longitude = 0;
	}
	if(altitude.contains("?")){
		this.altitude = 0;
	}
	else{
		this.latitude = Double.parseDouble(latitude);
		this.longitude = Double.parseDouble(longitude);
		this.altitude = Double.parseDouble(altitude);	}
	}

public boolean sameCoordinates(double lat,double lon){
	if(this.latitude==lat && this.longitude==lon)return true;
	return false;
}



/*************getters&setters***************/
public double getLatitude() {return latitude;}
public double getLongitude() {return longitude;}
public double getAltitude() {return altitude;}
public void setLatitude(double latitude) {this.latitude = latitude;}
public void setLongitude(double longitude) {this.longitude = longitude;}
public void setAltitude(double altitude) {this.altitude = altitude;}

public String coordinatesToString(){
	return this.latitude+","+this.longitude+","+this.altitude;
}
}
