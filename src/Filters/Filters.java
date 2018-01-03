package Filters;
import java.util.ArrayList;

import WifiPoint.Date;
import WifiPoint.RowOfWifiPoints;

/**
 * This class represents functions to filter matrix/lists
 * 
 * @author Olga & Dan
 *
 */
public class Filters {
	/**
	 * Function to filter ArrayList by ID
	 * @param list ArrayList<RowOfWifiPoints>
	 * @param id string filter
	 * @return filtered ArrayList<RowOfWifiPoints>
	 */
	public static ArrayList<RowOfWifiPoints> filteringByID(ArrayList<RowOfWifiPoints>list,String id){
		ArrayList<RowOfWifiPoints>copy=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId().equals(id)) {
			copy.add(list.get(i));
			}
		}

		return copy;
	}

	/**
	 * Function to filter ArrayList by latitude and longitude
	 * @param list ArrayList<RowOfWifiPoints>
	 * @param lat latitude filter
	 * @param lon longitude filter
	 * @return filtered ArrayList<RowOfWifiPoints>
	 */
	public static ArrayList<RowOfWifiPoints> filteringByCoordinates(ArrayList<RowOfWifiPoints>list,String lat,String lon){
		ArrayList<RowOfWifiPoints>copy=new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if((list.get(i).coordinates.getLatitude()+"").contains(lat) && (list.get(i).coordinates.longitude+"").contains(lon)) {
				copy.add(list.get(i));
			}
		}
		return copy;
	}

	/**
	 * Function to filter ArrayList by start/stop date
	 * @param list ArrayList<RowOfWifiPoints>
	 * @param startDate from date filter
	 * @param stopDate until date filter 
	 * @return filtered ArrayList<RowOfWifiPoints>
	 * @throws Exception
	 */
	public static ArrayList<RowOfWifiPoints> filteringByTime(ArrayList<RowOfWifiPoints>list, String startDate, String stopDate) throws Exception {
		ArrayList<RowOfWifiPoints>copy=new ArrayList<>();
		Date startFilter=new Date(startDate);
		Date stopFilter=new Date(stopDate);

		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).date.betweenDates(startFilter, stopFilter)) {
				copy.add(list.get(i));
			}
		}
		return copy;
	}

	/**
	 * Function to filter ArrayList by mac
	 * @param listToFilter ArrayList<RowOfWifiPoints>
	 * @param mac mac filter
	 * @return filtered list
	 * @throws Exception
	 */
	public static ArrayList<RowOfWifiPoints> filteringArrayByMAC(ArrayList<RowOfWifiPoints>listToFilter,ArrayList<RowOfWifiPoints>listToAddRows, String mac,int count) throws Exception{
		ArrayList<RowOfWifiPoints>copy=new ArrayList<>();
		
			for (int row = 0; row < listToFilter.size(); row++) { 
				for (int col = 0; col < listToFilter.get(row).wifiList.size(); col++) {
					if(listToFilter.get(row).wifiList.get(col).mac.equals(mac)){ 
						RowOfWifiPoints r=new RowOfWifiPoints(listToFilter.get(row).date, listToFilter.get(row).getId(), listToFilter.get(row).getCoordinates(), listToFilter.get(row).getNumOfWifiNetworks());
						r.wifiList.add(listToFilter.get(row).wifiList.get(col));
						copy.add(r);
					}
				}
				
			}
		if(count==0){
			listToAddRows=copy;
		}
		else{
			listToAddRows=mergeTwoListsByDateAndID(listToAddRows, copy);
		}
		return listToAddRows;
	}

	/**
	 * Function to merge two ArrayList<RowOfWifiPoints> by Date and ID
	 * @param a ArrayList<RowOfWifiPoints>
	 * @param b ArrayList<RowOfWifiPoints>
	 * @return merged list
	 */
	public static ArrayList<RowOfWifiPoints> mergeTwoListsByDateAndID(ArrayList<RowOfWifiPoints>a,ArrayList<RowOfWifiPoints>b){
		ArrayList<RowOfWifiPoints>mergedList=b;
		for (int rowA = 0; rowA < a.size(); rowA++) {
			int rowB = 0;
			for (rowB = 0; rowB < b.size(); rowB++) {
				if(a.get(rowA).date.sameDate(b.get(rowB).date) && a.get(rowA).getId().equals(b.get(rowB).getId())){
					mergedList.get(rowB).wifiList.addAll(a.get(rowA).wifiList);
					break;
				}
			}
			if(rowB==b.size()) mergedList.add(a.get(rowA));
		}

		return mergedList;
	}

	/**
	 * Filtering "numOfPointsToUseForCheckup" most matched(powerful) signals
	 * @param list of data to filter
	 * @param numOfPointsToUseForCheckup number of most powerful wifi points you want me to use in calculation
	 * @return filtered list
	 */
	public static ArrayList<RowOfWifiPoints> filterByMostPowerfulWifiSignals(ArrayList<RowOfWifiPoints>list,
			int numOfPointsToUseForCheckup){
		if(numOfPointsToUseForCheckup==list.size())return list;
		if(numOfPointsToUseForCheckup>list.size()){ 
			//System.out.println("You don't have that much in list, so I'll work on "+list.size()+" I have in list");
			return list;
		}

		int row=numOfPointsToUseForCheckup;
		while(row<list.size()){
			int index=-1;
			for (int i = 0; i <= numOfPointsToUseForCheckup; i++) {
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

	public static ArrayList<RowOfWifiPoints> mostPowerfulWifiWithSameMac(ArrayList<RowOfWifiPoints>list){
		if(list.isEmpty())return list;
		ArrayList<String>addedMac=new ArrayList<String>();
		ArrayList<Integer>formWichRowAddedMac=new ArrayList<Integer>();
		//add first line of mac addresses to "added"
		for (int i = 0; i < list.get(0).wifiList.size(); i++) {
			addedMac.add(list.get(0).wifiList.get(i).mac);
			formWichRowAddedMac.add(0);
		}
		//run at list to choose most Powerful wifi
		for (int row =1; row < list.size(); row++) { 
			for (int col = 0; col < list.get(row).wifiList.size(); col++) {
				int i=0;
				for (i = addedMac.size()-1; i >= 0; i--) {
					if(list.get(row).wifiList.get(col).getMac().equals(addedMac.get(i))){
						for (int j = 0; j < list.get(formWichRowAddedMac.get(i)).wifiList.size(); j++) {
							if(list.get(formWichRowAddedMac.get(i)).wifiList.get(j).equalMAC(list.get(row).wifiList.get(col))){
								if(list.get(formWichRowAddedMac.get(i)).wifiList.get(j).signal.morePowerful(list.get(row).wifiList.get(col).signal)){
									list.get(row).wifiList.remove(col);
								}
								else {
									list.get(formWichRowAddedMac.get(i)).wifiList.remove(j);
									addedMac.remove(i);
									formWichRowAddedMac.remove(i);
									addedMac.add(list.get(row).wifiList.get(col).getMac());
									formWichRowAddedMac.add(row);
								}break;
							}
						}break;
					}
				}
				if(i<0){
					addedMac.add(list.get(row).wifiList.get(col).getMac());
					formWichRowAddedMac.add(row);
				}
			}
		}

		return list;	
	}
}