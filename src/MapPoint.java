
 /**
  * This class is temp one. We didn't have enough time to do all the changes for this project
  * It represents simple map point of wifi in time
  * We'll remove it in one day 
 * @author Olga Reznyk & Dan Michaeli
 *
 */
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
  

	
}