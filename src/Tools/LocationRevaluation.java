package Tools;

import java.util.ArrayList;

import Filters.Filters;
import Filters.Id_Filter;
import Filters.filter;
import WifiData.Coordinates_3D;
import WifiData.Date;
import WifiData.ListOfWifiRows;
import WifiData.RowOfWifiPoints;
import WifiData.Signal;
import WifiData.Wifi;

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
	 * @param numOfPointsToUseForCheckup number of most powerful wifi points you want me to use in calculation
	 * @return RowOfWifiPoint with coordinates of router
	 * @throws Exception
	 */
	public static ListOfWifiRows centerOfRouter(String csvFileWithFilters,String csvFileToRun, int numOfPointsToUseForCheckup ) throws Exception{
		ArrayList<String>mac=ReaderFromCsv.createListOfMacsFromCSVFile(csvFileWithFilters);
		ListOfWifiRows listToPrint=new ListOfWifiRows();
		ListOfWifiRows listToFilter=ReaderFromCsv.readerFromMergedCSVtoList(csvFileToRun);
		for (int i = 0; i < mac.size(); i++) {
			RowOfWifiPoints r=centerOfRouter1(listToFilter,mac.get(i), numOfPointsToUseForCheckup);
			if(r!=null) listToPrint.add(r);
		}

		WriteTOcsv.writer(listToPrint, csvFileWithFilters.replace(".csv", "_Our_Algo1.csv"));
		return	listToPrint;
	}
	
	
	

	/**
	 * Function to found location of device by Mac and signal
	 * @param csvFileToTakeFilterFrom
	 * @param csvFileToSearchIn
	 * @param numOfPointsToUseForCheckup number of most powerful wifi points you want me to use in calculation
	 * @return list of RowOfWifiPoint with coordinates of router
	 * @throws Exception
	 */

	public static ListOfWifiRows yourLocation(String csvFileToTakeFilterFrom,String csvFileToSearchIn ,int numOfPointsToUseForCheckup) throws Exception{
		ListOfWifiRows listToTakeFilterFrom=ReaderFromCsv.readerFromMergedCSVtoList(csvFileToTakeFilterFrom);
		if(listToTakeFilterFrom.isEmpty())return null;
		ListOfWifiRows listToFilter=ReaderFromCsv.readerFromMergedCSVtoList(csvFileToSearchIn);
		if(listToFilter.isEmpty())return null;
		ListOfWifiRows listToPrint=new ListOfWifiRows();
		filter id=new Id_Filter(listToTakeFilterFrom.get(0).getId());
		ListOfWifiRows temp=listToFilter.copy();
		temp.filter(id);
		

		for (int i = 0; i < listToTakeFilterFrom.size(); i++) {
			ArrayList<String>mac= new ArrayList<String>();
			ArrayList<Signal>signal=new ArrayList<Signal>();
			if(i>0 && listToTakeFilterFrom.get(i).getId().equals(listToTakeFilterFrom.get(i-1).getId())==false){
				filter id2=new Id_Filter(listToTakeFilterFrom.get(0).getId());
				temp=listToFilter.copy();
				temp.filter(id2);
			}
			
			for (int j = 0; j < listToTakeFilterFrom.get(i).wifiList.size(); j++) {
				mac.add(listToTakeFilterFrom.get(i).wifiList.get(j).getMac());
				signal.add(listToTakeFilterFrom.get(i).wifiList.get(j).signal);
			}
			System.out.println(i);
			RowOfWifiPoints r=yourLocation(temp,mac,signal,numOfPointsToUseForCheckup);
			if(r!=null){
				listToPrint.add(listToTakeFilterFrom.get(i));
				listToPrint.get(listToPrint.size()-1).getCoordinates().setLatitude(r.getCoordinates().latitude);
				listToPrint.get(listToPrint.size()-1).getCoordinates().setLongitude(r.getCoordinates().longitude);
				listToPrint.get(listToPrint.size()-1).getCoordinates().setAltitude(r.getCoordinates().altitude);
				}
		}

		WriteTOcsv.writer(listToPrint, csvFileToTakeFilterFrom.replace(".csv", "_Our_Algo2.csv"));
		return listToPrint;
	}
	
	
	
	/********************private**********************/
	
	/**
	 * Function to found location of router by Mac
	 * @param listToFilter of merged csv with collected data
	 * @param MAC mac filter
	 * @param numOfPointsToUseForChekup number of most powerful wifi points you want me to use in calculation
	 * @return RowOfWifiPoint with coordinates of router
	 * @throws Exception
	 */
	public static RowOfWifiPoints centerOfRouter1(ListOfWifiRows listToFilter,String mac, int numOfPointsToUseForChekup ) throws Exception{
		ListOfWifiRows listToPrint=new ListOfWifiRows();
		
		return 	centerPoint(Filters.filterByMostPowerfulWifiSignals(Filters.filteringArrayByMAC(listToFilter, listToPrint, mac, 0), numOfPointsToUseForChekup));
		
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
	
	public static RowOfWifiPoints yourLocation(ListOfWifiRows listToFilter,ArrayList<String>mac,ArrayList<Signal>signal,int numOfPointsToUseForChekup ) throws Exception{
		ListOfWifiRows listToPrint=new ListOfWifiRows();
		int count=0;
		
		/*********filtering by all macs from list****************/
		while(count<mac.size() && mac.get(count)!=null){
			listToPrint=Filters.filteringArrayByMAC(listToFilter, listToPrint, mac.get(count), count);
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

	private static RowOfWifiPoints centerPoint(ListOfWifiRows list){
		if(list.isEmpty()==false){
			Date date=list.get(0).date;
			String id="Approx. w-center";
			int numOfWifiNetworks=1;
			double latitude=0, longitude=0, altitude=0,sumOfsignalsWeight=0;
			int i=0;
			Wifi wifi=new Wifi(list.get(0).wifiList.get(0).getMac(), list.get(0).wifiList.get(0).getSsid(),
					"calculated from "+list.size()+" samples","0");
			while(i<list.size()){

					if(list.get(i).wifiList.getLast().getMac().length()>0){ //for algo 1
				sumOfsignalsWeight+=list.get(i).wifiList.getLast().signal.weightOfSignal();
				latitude+=list.get(i).weightOfLat(list.get(i).wifiList.getLast());
				longitude+=list.get(i).weightOfLon(list.get(i).wifiList.getLast());
				altitude+=list.get(i).weightOfAlt(list.get(i).wifiList.getLast());
				}
					else{ //for algo 2
						sumOfsignalsWeight+=list.get(i).wifiList.getLast().getSignal();
						latitude+=list.get(i).getCoordinates().getLatitude()*list.get(i).wifiList.getLast().getSignal();
						longitude+=list.get(i).getCoordinates().getLongitude()*list.get(i).wifiList.getLast().getSignal();
						altitude+=list.get(i).getCoordinates().getAltitude()*list.get(i).wifiList.getLast().getSignal();
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
	private static ListOfWifiRows matchBySignal(ListOfWifiRows list,ArrayList<String>mac, ArrayList<Signal>signal){
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
