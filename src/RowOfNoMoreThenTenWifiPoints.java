import java.util.LinkedList;

public class RowOfNoMoreThenTenWifiPoints {
	protected Coordinates_3D coordinates;
	protected String id;
	protected int numOfWifiNetworks;
	protected Date date;
	protected LinkedList<Wifi>wifiList;

	RowOfNoMoreThenTenWifiPoints(Date date,String id,Coordinates_3D coordinates,int numOfWifiNetworks) {
		this.coordinates=coordinates;
		this.date=date;
		this.id=id;
		this.numOfWifiNetworks=numOfWifiNetworks;
		wifiList=new LinkedList<Wifi>();
	}


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

	public void setNumOfWifiNetworks(int numOfWifiNetworks) {
		this.numOfWifiNetworks = numOfWifiNetworks;
	} 
	

	  public double weightOfLat(Wifi wifi){
		  return this.coordinates.latitude*wifi.signal.weight();
	  }
	  public double weightOfLon(Wifi wifi){
		  return this.coordinates.longitude*wifi.signal.weight();
	  }
	  public double weightOfAlt(Wifi wifi){
		  return this.coordinates.altitude*wifi.signal.weight();
	  }

	  public String rowToString(){
		String wifiRow="";
		int index=0;
		while(index<wifiList.size()) {
			wifiRow+= wifiList.get(index).wifiToString()+",";
			index++;
			}
		return date.getDateAtTimeAsString()+","+this.id+","+coordinates.coordinatesToString()+","+this.numOfWifiNetworks+","+wifiRow;
	}
}
