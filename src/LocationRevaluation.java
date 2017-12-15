import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * This class represents functions to finding truly location
 * @author Olga & Dan
 *
 */
public class LocationRevaluation {
	/**
	 * Function to found location of router by Mac
	 * @param csvFile address of merged csv with collected data
	 * @param MAC mac filter
	 * @param numOfPointsToUseForChekup number of most powerful wifi points you want me to use in calculation
	 * @return RowOfWifiPoint with coordinates of router
	 * @throws Exception
	 */
	public static ArrayList<RowOfWifiPoints> centerOfRouter(String csvFileWithFilters,String csvFileToRun, int numOfPointsToUseForChekup ) throws Exception{
		ArrayList<String>mac=ReaderWriter.createListOfMacsFromCSVFile(csvFileWithFilters);
		ArrayList<RowOfWifiPoints>listToPrint=new ArrayList<RowOfWifiPoints>();
		for (int i = 0; i < mac.size(); i++) {
			listToPrint.add(centerOfRouter1(csvFileToRun,mac.get(i), numOfPointsToUseForChekup));
		}

		ReaderWriter.WriterToCsv(listToPrint, csvFileWithFilters+"_location.csv");
		return	listToPrint;
	}
	
	
	

	/**
	 * Function to found location of device by Mac and signal
	 * @param csvFileToTakeFilterFrom
	 * @param csvFileToSearchIn
	 * @param numOfPointsToUseForChekup number of most powerful wifi points you want me to use in calculation
	 * @return list of RowOfWifiPoint with coordinates of router
	 * @throws Exception
	 */

	public static ArrayList<RowOfWifiPoints> yourLocation(String csvFileToTakeFilterFrom,String csvFileToSearchIn ,int numOfPointsToUseForChekup) throws Exception{
		String[][]matrixOfFilters=ReaderWriter.readerFromMergedCSVtoMatrix(csvFileToTakeFilterFrom);
		String[][]matrixToFilter=ReaderWriter.readerFromMergedCSVtoMatrix(csvFileToSearchIn);
		String[][]ans=matrixOfFilters;//Filters.filteringByOneLocalVariable(matrixOfFilters, "?", 3);
		ArrayList<RowOfWifiPoints>listToPrint=new ArrayList<RowOfWifiPoints>();
		String id="";
		Signal s;
		ArrayList<String>mac= new ArrayList<String>();
		ArrayList<Signal>signal=new ArrayList<Signal>();
		int i=0;
		while(i<ans.length && ans[i][0]!=null){
			int j=7;
			id=ans[i][1];
			while(ans[i][j]!=null && j+4<ans[i].length){
				mac.add(ans[i][j]);
				s=new Signal(ans[i][j+2]);
				signal.add(s);
				j+=4;
				
			}
			String[][]temp=new String[1][1];
			temp=Filters.filteringByOneLocalVariable(matrixToFilter, id, 1);
			System.out.println(i);
			listToPrint.add(yourLocation(temp,mac,signal,numOfPointsToUseForChekup));
			System.out.println();
			mac.removeAll(mac);
			signal.removeAll(signal);
			i++;
		}
		ReaderWriter.WriterToCsv(listToPrint, csvFileToTakeFilterFrom+"_location.csv");
		return listToPrint;
	}
	
	
	
	/********************private**********************/
	
//	/**
//	 * Function to found location of router by Mac
//	 * @param csvFile address of merged csv with collected data
//	 * @param MAC mac filter
//	 * @param numOfPointsToUseForChekup number of most powerful wifi points you want me to use in calculation
//	 * @return RowOfWifiPoint with coordinates of router
//	 * @throws Exception
//	 */
	private static RowOfWifiPoints centerOfRouter1(String csvFile,String mac, int numOfPointsToUseForChekup ) throws Exception{
		ArrayList<RowOfWifiPoints>listToPrint=new ArrayList<RowOfWifiPoints>();
		String[][]ans=ReaderWriter.readerFromMergedCSVtoMatrix(csvFile);
		return 	centerPoint(Filters.filterByMostPowerfulWifiSignals(Filters.filteringByMAC(ans,listToPrint, mac,1), numOfPointsToUseForChekup));
		
	}
	
	
	
	/**
	 * function that runs all help functions to get calculated coordinates
	 * @param ans matrix to search in
	 * @param mac list of macs to filter with
	 * @param signal list of signals to calculate with
	 * @param numOfPointsToUseForChekup number of most powerful wifi points you want me to use in calculation
	 * @return RowOfWifiPoint with coordinates of device
	 * @throws Exception
	 */
	
	private static RowOfWifiPoints yourLocation(String[][]ans,ArrayList<String>mac,ArrayList<Signal>signal,int numOfPointsToUseForChekup ) throws Exception{
		ArrayList<RowOfWifiPoints>listToPrint=new ArrayList<RowOfWifiPoints>();
		int count=0;
		
		/*********filtering by all macs from list****************/
		while(count<mac.size() && mac.get(count)!=null){
			Filters.filteringByMAC(ans, listToPrint, mac.get(count),count+1);
			count++;
		}
		/***************calculating match of row************************/
		listToPrint=matchBySignal(listToPrint,mac,signal);
		/***************filtering "numOfPointsToUseForChekup" most matched rows************************/
		listToPrint=Filters.filterByMostPowerfulWifiSignals(listToPrint, numOfPointsToUseForChekup);
		/***************calculating coordinates of device for this row************************/
		return centerPoint(listToPrint);
	}
	
	/**
	 * Calculating coordinates of device/router
	 * @param list list of RowOfWifiPoints
	 * @return RowOfWifiPoints with coordinates of device/router
	 */

	private static RowOfWifiPoints centerPoint(ArrayList<RowOfWifiPoints>list){
		if(list.isEmpty()==false){
			Date date=list.get(0).date;
			String id="Approx. w-center";
			int numOfWifiNetworks=1;
			double latitude=0, longitude=0, altitude=0,sumOfsignalsWeight=0;
			int i=0;
			Wifi wifi=new Wifi(list.get(0).wifiList.get(0).getMac(), list.get(0).wifiList.get(0).getSsid(),
					"calculated from "+list.size()+" samples","0");
			while(i<list.size()){
				int j=0;
				while(j<list.get(i).wifiList.size() && list.get(i).wifiList.get(j).mac!=null){
				sumOfsignalsWeight+=list.get(i).wifiList.get(j).signal.weightOfSignal();
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
			RowOfWifiPoints WIFIpoint=new RowOfWifiPoints(date, id, coord, numOfWifiNetworks);
			WIFIpoint.addWifiToList(wifi);
			//System.out.println(WIFIpoint.toString());
			return WIFIpoint;
		}
		else return null;
	}
	
	/**
	 * Calculating match of row
	 * @param list list of RowOfWifiPoints
	 * @param mac list of macs
	 * @param signal list of signals
	 * @return list of RowOfWifiPoints with added calculated match as signal in each row 
	 */
	private static ArrayList<RowOfWifiPoints> matchBySignal(ArrayList<RowOfWifiPoints>list,ArrayList<String>mac, ArrayList<Signal>signal){
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
