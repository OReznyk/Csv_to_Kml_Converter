
 class MapPoint {
	protected Coordinates_3D coordinates;
	protected String id;
	protected Date date;
	protected Wifi wifiPoint;

/*to Do: change all strings to protected variables*/ 
  MapPoint(Date date,String id,Coordinates_3D coordinates,Wifi wifiPoint) {
	    this.id=id;
	    this.coordinates=coordinates;
	    this.date=date;
	    this.wifiPoint=wifiPoint;
	}
  
public String getId() { return id; }


  public double weightOfLat(){
	  return this.coordinates.latitude*this.wifiPoint.signal.weight();
  }
  public double weightOfLon(){
	  return this.coordinates.longitude*this.wifiPoint.signal.weight();
  }
  public double weightOfAlt(){
	  return this.coordinates.altitude*this.wifiPoint.signal.weight();
  }
  
	
}