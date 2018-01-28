package main.java.WifiData;

/**
 * This class represents the simplest 3D coordinates
 * @author Olga & Dan
 *
 */
public class Coordinates_3D {
	public double latitude;
	public double longitude;
	public double altitude;
	
public Coordinates_3D(String latitude, String longitude, String altitude) {	
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



public double distance2D(Coordinates_3D p2)
{
	double t = Math.pow((latitude-p2.getLatitude()), 2)+Math.pow((longitude-p2.getLongitude()),2);
	return Math.sqrt(t);
}


public double distance3D(Coordinates_3D p2)
{
	double t = Math.pow((latitude-p2.getLatitude()), 2)+Math.pow((longitude-p2.getLongitude()),2)+Math.pow( (altitude-p2.getAltitude()),2);
	return Math.sqrt(t);
}
public String coordinatesToString(){
	return this.longitude+","+this.latitude+","+this.altitude;
}
}
