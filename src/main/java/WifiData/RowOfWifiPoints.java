package main.java.WifiData;
import java.util.LinkedList;

/**
 * This class represents row of no more than 10 wifi networks collected in same location at same time by same device
 * @author Olga & Dan
 *
 */
public class RowOfWifiPoints {
	public Coordinates_3D coordinates;
	private String id;
	protected int numOfWifiNetworks;
	public Date date;
	public LinkedList<Wifi>wifiList;

	public RowOfWifiPoints(Date date,String id,Coordinates_3D coordinates,int numOfWifiNetworks) {
		this.coordinates=coordinates;
		this.date=date;
		this.id=id;
		this.numOfWifiNetworks=numOfWifiNetworks;
		wifiList=new LinkedList<Wifi>();
	}


	/**
	 * Adding Wifi  to list after checking its match by signal
	 * @param wifi 
	 */
	public void addWifiToList(Wifi wifi){
		if(wifiList.size()<10){
			wifiList.add(wifi);
		}
		else{
			int indexOfWifi=0,i=1;
			while(i<10){
				if(wifiList.get(indexOfWifi).signal.morePowerful(wifiList.get(i).signal)) indexOfWifi=i;
				i++;
			}
			if(wifi.signal.morePowerful(wifiList.get(indexOfWifi).signal)){
				wifiList.remove(indexOfWifi);
				wifiList.add(wifi);
			}
		}
	}


		/*************getters&setters***************/
	  public void setNumOfWifiNetworks(int numOfWifiNetworks) {this.numOfWifiNetworks = numOfWifiNetworks;} 
	  public int getNumOfWifiNetworks() {return numOfWifiNetworks;}

	  	/************Calculators for coordinates weight********************/
	public double weightOfLat(Wifi wifi){
		  return this.getCoordinates().latitude*wifi.signal.weightOfSignal();
	  }
	  public double weightOfLon(Wifi wifi){
		  return this.getCoordinates().longitude*wifi.signal.weightOfSignal();
	  }
	  public double weightOfAlt(Wifi wifi){
		  return this.getCoordinates().altitude*wifi.signal.weightOfSignal();
	  }
	  
	  @Override
	  public String toString(){
		String wifiRow="";
		int index=0;
		while(index<wifiList.size()) {
			wifiRow+= wifiList.get(index).toString()+",";
			index++;
			}
		return date.toString()+","+this.getId()+","+getCoordinates().coordinatesToString()+","+this.numOfWifiNetworks+","+wifiRow;
	}


	public String getId() {
		return id;
	}


	public Coordinates_3D getCoordinates() {
		return coordinates;
	}


}
