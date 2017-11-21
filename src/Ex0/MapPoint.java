package Ex0;

/**
 * A point represented by latitude, longitude, and altitude.
 * 
 * @author Ethan Harstad with my little changes
 *
 * https://www.javatips.net/api/HABtk-master/src/com/aerodynelabs/map/MapSettingsPanel.java
 */
public class MapPoint {

	protected double lat;
	protected double lon;
	protected double alt;
	protected String mac;
	protected String signal;
	protected String time;
	protected String name = null;



	/**
	 * Create a point with an associated name and time.
	 * @param latitude
	 * @param longitude
	 * @param altitude
	 * @param time
	 * @param name
	 * @param mac
	 * @param signal
	 */
	public  MapPoint(double latitude, double longitude, double altitude, String time, String name, String mac, String signal) {
		lat = latitude;
		lon = longitude;
		alt = altitude;
		this.time = time;
		this.name = name;
		this.signal=signal;
		this.mac=mac;
	}
	/**
	 * Get the mac address of this point
	 * @return string mac address
	 */
	public String getMac() {
		return mac;
	}

	/**
	 * Get the signal of this point
	 * @return string signal
	 */
	public String getSignal() {
		return signal;
	}


	/**
	 * Get the latitude of this point.
	 * @return double latitude
	 */
	public  double getLatitude() {
		return lat;
	}

	/**
	 * Get the longitude of this point.
	 * @return double longitude
	 */
	public  double getLongitude() {
		return lon;
	}

	/**
	 * Get the altitude of this point.
	 * @return double altitude
	 */
	public  double getAltitude() {
		return alt;
	}

	/**
	 * Get the time associated with this point.
	 * @return string time
	 */
	public  String getTime() {
		return time;
	}

	/**
	 * Get the name associated with this point.
	 * @return string name
	 */
	public  String getName() {
		return name;
	}


	/**
	 * Get a human readable representation of this point;
	 */
	
	
	public  String toString() {
		return name + ": " + lat + ", " + lon + ", " + alt + ", " + time;
	}

}