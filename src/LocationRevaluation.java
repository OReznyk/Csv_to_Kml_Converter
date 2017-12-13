import java.util.ArrayList;

public class LocationRevaluation {

	public static RowOfNoMoreThenTenWifiPoints centerOfRouter(String csvFile,String MAC, int numOfPointsToUseForChekup ) throws Exception{
		ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint=new ArrayList<RowOfNoMoreThenTenWifiPoints>();
		ArrayList<String>mac=new ArrayList<String>();
		mac.add(MAC);
		String[][]ans=ReaderWriter.readerFromMergedCSVtoMatrix(csvFile);
		return	LocationRevaluation.centerPoint(LocationRevaluation.mostPowerfulWifiSignals(ReaderWriter.filteringByMAC(ans,listToPrint, mac.get(0),1), numOfPointsToUseForChekup));
	}
	
	

	public static ArrayList<RowOfNoMoreThenTenWifiPoints> yourLocation(String csvFileToTakeFilterFrom,String csvFileToSearchIn ,int howMuchYouWantToGetBack) throws Exception{
		String[][]matrixOfFilters=ReaderWriter.readerFromMergedCSVtoMatrix(csvFileToTakeFilterFrom);
		String[][]matrixToFilter=ReaderWriter.readerFromMergedCSVtoMatrix(csvFileToSearchIn);
		String[][]ans=ReaderWriter.filteringByOneVariable(matrixOfFilters, "?", 3);
		ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint=new ArrayList<RowOfNoMoreThenTenWifiPoints>();
		String id="";
		Signal s;
		ArrayList<String>mac= new ArrayList<String>();
		ArrayList<Signal>signal=new ArrayList<Signal>();
		int i=0;
		while(i<ans.length && ans[i][0]!=null){
			int j=7;
			id=ans[i][1];
			while(ans[i][j]!=null){
				mac.add(ans[i][j]);
				s=new Signal(ans[i][j+2]);
				signal.add(s);
				j+=4;
				
			}
			String[][]temp=new String[1][1];
			temp=ReaderWriter.filteringByOneVariable(matrixToFilter, id, 1);
			System.out.println(i);
			listToPrint.add(yourLocation(temp,mac,signal,howMuchYouWantToGetBack));
			System.out.println();
			mac.removeAll(mac);
			signal.removeAll(signal);
			i++;
		}
		return listToPrint;
	}
	
	
	
	/********************private**********************/
	
	private static RowOfNoMoreThenTenWifiPoints yourLocation(String[][]ans,ArrayList<String>mac,ArrayList<Signal>signal,int howMuchYouWantToGetBack ) throws Exception{
		ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint=new ArrayList<RowOfNoMoreThenTenWifiPoints>();
		int count=0;
		while(count<mac.size() && mac.get(count)!=null){
		ReaderWriter.filteringByMAC(ans, listToPrint, mac.get(count),count+1);
		count++;
		}
//		for (int i = 0; i < listToPrint.size(); i++) {
//			System.out.println(listToPrint.get(i).rowToString());
//		}
		listToPrint=matchBySignal(listToPrint,mac,signal);
		System.out.println();
//		for (int i = 0; i < listToPrint.size(); i++) {
//			System.out.println(listToPrint.get(i).rowToString());
//		}
		listToPrint=mostPowerfulWifiSignals(listToPrint, howMuchYouWantToGetBack);

//		for (int i = 0; i < listToPrint.size(); i++) {
//			System.out.println(listToPrint.get(i).rowToString());
//		}

		return centerPoint(listToPrint);
	}
	
	private static ArrayList<RowOfNoMoreThenTenWifiPoints> mostPowerfulWifiSignals(ArrayList<RowOfNoMoreThenTenWifiPoints>list, int howMuchYouWantToGetBack){
		if(howMuchYouWantToGetBack==list.size())return list;
		if(howMuchYouWantToGetBack>list.size()){ 
			System.out.println("You don't have that much in list, so I'll work on "+list.size()+" I have in list");
			return list;
		}

		int row=howMuchYouWantToGetBack;
		while(row<list.size()){
			int index=-1;
			for (int i = 0; i <= howMuchYouWantToGetBack; i++) {
				if(list.get(row).wifiList.getLast().signal.morePowerful(list.get(i).wifiList.getLast().signal)){
					if(index<0)index=i;
					else{
						if(list.get(index).wifiList.getLast().signal.morePowerful(list.get(i).wifiList.getLast().signal))
							index=i;
					}
				}
			} if(index<0)list.remove(row);
			else list.remove(index);
		}
		return list;	
	}

	private static RowOfNoMoreThenTenWifiPoints centerPoint(ArrayList<RowOfNoMoreThenTenWifiPoints>list){
		if(list.isEmpty()==false){
			Date date=list.get(0).date;
			String id="Olga&Dan";
			int numOfWifiNetworks=1;
			double latitude=0, longitude=0, altitude=0,sumOfsignalsWeight=0;
			int i=0;
			Wifi wifi=new Wifi("Center", "here", "0", "0");
			while(i<list.size()){
				int j=0;
				while(j<list.get(i).wifiList.size() && list.get(i).wifiList.get(j).mac!=null){
				sumOfsignalsWeight+=list.get(i).wifiList.get(j).signal.weight();
				latitude+=list.get(i).weightOfLat(list.get(i).wifiList.get(j));
				longitude+=list.get(i).weightOfLon(list.get(i).wifiList.get(j));
				altitude+=list.get(i).weightOfAlt(list.get(i).wifiList.get(j));
				j++;
				}
				i++;
			}

			latitude=latitude/sumOfsignalsWeight;
			longitude=longitude/sumOfsignalsWeight;
			altitude=altitude/sumOfsignalsWeight;

			Coordinates_3D coord=new Coordinates_3D(latitude+"", longitude+"", altitude+"");
			RowOfNoMoreThenTenWifiPoints WIFIpoint=new RowOfNoMoreThenTenWifiPoints(date, id, coord, numOfWifiNetworks);
			WIFIpoint.addWifiToList(wifi);
			System.out.println(WIFIpoint.rowToString());
			return WIFIpoint;
		}
		else return null;
	}

	private static ArrayList<RowOfNoMoreThenTenWifiPoints> matchBySignal(ArrayList<RowOfNoMoreThenTenWifiPoints>list,ArrayList<String>mac, ArrayList<Signal>signal){
		int row=0;
		double inputSignal=0,signalOfWifi=0,dif=0;
		
		while(row<list.size()){
			double wAll=1;
			int indexInMac=0;
			
			while(indexInMac<mac.size()){
				
			int col=0;
			boolean inputMacInList=false;
			double w=1;
	
			
			while(col<list.get(row).wifiList.size()){
				
				if(list.get(row).wifiList.get(col).mac.equals(mac.get(indexInMac))){
					inputSignal=Math.abs(signal.get(indexInMac).getSignal());
					signalOfWifi=Math.abs(list.get(row).wifiList.get(col).signal.getSignal());
					if(Math.abs(inputSignal-signalOfWifi)<3) dif=3;
					else dif=Math.abs(inputSignal-signalOfWifi);
					w=10000/(Math.pow(dif, 0.4)*Math.pow(inputSignal, 2));
					
					inputMacInList=true;
					break;
				}col++;
				
			}
			if(inputMacInList==false){
				w=10000/(Math.pow(100, 0.4)*Math.pow(signal.get(indexInMac).signal, 2));
			}
			
			wAll*=w;
			indexInMac++;
		}
			Wifi p=new Wifi("","","0",wAll+"");
			list.get(row).wifiList.add(p);
			row++;
		}
		return list;	
	}
}
