
import java.util.ArrayList;

/**
 * This class represents functions to filter matrix/lists
 * 
 * @author Olga Reznyk & Dan Michaeli
 *
 */
public class Filters {
	/**
	 * Function to filter matrix with one local variable as ID
	 * @param matrix
	 * @param filter
	 * @param colmToFilter in which column in matrix exists this variable
	 * @return filtered matrix
	 */
	public static String[][] filteringByOneLocalVariable(String[][]matrix,String filter, int colmToFilter){
		String[][]ans=new String[15][46];
		int count=0,i=0;
		while(matrix[i][0]!=null){	
		 if(matrix[i][colmToFilter].equals(filter)){
					ans=Functions.putAllDataFromStringArrToRowInMatrix(ans,matrix[i], count);
					count++;
					if(count==ans.length)ans= Functions.reBuild(ans);
		 }
		 i++;
				}
		return ans;
	}
	
	/**
	 * Choosing data by filter and saving it in table
	 * @param matrix table to filter
	 * @param lat latitude 
	 * @param lon longitude
	 * @param latColmToFilter number of latitude column to search in
	 * @param lonColmToFilter number of  longitude column to search in
	 */
	public static String[][] filteringByTwoVariables(String[][]matrix,String lat,String lon, int latColmToFilter,int lonColmToFilter){
		String[][]ans=new String[15][46];
		int count=0,i=0;  
		while(matrix[i][0]!=null){
				if(matrix[i][latColmToFilter].contains(lat) && matrix[i][lonColmToFilter].contains(lon)){
					ans=Functions.putAllDataFromStringArrToRowInMatrix(ans, matrix[i], count++ );
					count++;
					if(count==ans.length)ans=Functions.reBuild(ans);
				}
				i++;
			} 
			if(ans[0][0]==null){
				ans[0][0]="Does not exist";
			}


		return ans;

	}

	
	/**
	 * Time filter, filtering matrix by start and end times
	 * @param csvFile
	 * @param startDate
	 * @param stopDate
	 * @param colmToFilter
	 * @throws Exception
	 */
	public static String[][] filteringByTime(String[][]matrix, String startDate, String stopDate, int colmToFilter) throws Exception {
		String[][]ans=new String[10][46];
		Date startFilter=new Date(startDate);
		Date stopFilter=new Date(stopDate);
		int count=0,i=0;  
			while(matrix[i][0]!=null){
				Date thisTime=new Date(matrix[i][0]);
				if(thisTime.betweenDates(startFilter, stopFilter)){
					ans=Functions.putAllDataFromStringArrToRowInMatrix(ans,matrix[i], count);
					count++;
					if(count==ans.length)ans=Functions.reBuild(ans);
					}
				i++;
		}
			if(ans[0][0]==null){
				ans[0][0]="Does not exist";}

		return ans;
	}
	

	/**
	 * Filtering matrix by MAC and saving 4 of Most powerful in table
	 * @param ans matrix to filter
	 * @param listToPrint list to put filtered data in
	 * @param filter mac to filter with
	 * @param count if you want to filter same file with more then 1 MAC you need to put here num of each mac you filtering with
	 * @return list
	 * @throws Exception
	 */
	public static ArrayList<RowOfWifiPoints> filteringByMAC(String [][]ans,ArrayList<RowOfWifiPoints>listToPrint, String filter, int count) throws Exception{
		int colmToFilter=7;
		
			int row=0;
			while(ans[row][0]!=null){
				int index=0;
				while(colmToFilter+index<ans[row].length && ans[row][colmToFilter+index]!=null){
					if(ans[row][colmToFilter+index].equals(filter)){
						Coordinates_3D coord=new Coordinates_3D(ans[row][2], ans[row][3], ans[row][4]);
						String id=ans[row][1];
						Date date=new Date(ans[row][0]);
						Wifi wifi=new Wifi(ans[row][colmToFilter+index-1], ans[row][colmToFilter+index], ans[row][colmToFilter+index+1], ans[row][colmToFilter+index+2]);
						
						if(count==1){
							RowOfWifiPoints WifiPoint=new RowOfWifiPoints(date, id, coord, 1);
							WifiPoint.addWifiToList(wifi);
							listToPrint.add(WifiPoint);
						}
						else{
							boolean exists=false;
							for (int i = 0; i < listToPrint.size(); i++) {
								if(listToPrint.get(i).date.sameDate(date) && listToPrint.get(i).id.equals(id)){
									listToPrint.get(i).wifiList.add(wifi);
									exists=true;
									break;
								}

							} if(exists==false){
								RowOfWifiPoints WifiPoint=new RowOfWifiPoints(date, id, coord, 1);
								WifiPoint.addWifiToList(wifi);
								listToPrint.add(WifiPoint);
							}
						}
					}
					index=index+4;
				}
				row++;
			}
		return listToPrint;

	}
	/**
	 * Filtering "numOfPointsToUseForChekup" most matched(powerful) signals
	 * @param list of data to filter
	 * @param numOfPointsToUseForChekup number of most powerful wifi points you want me to use in calculation
	 * @return filtered list
	 */
	public static ArrayList<RowOfWifiPoints> filterByMostPowerfulWifiSignals(ArrayList<RowOfWifiPoints>list, int numOfPointsToUseForChekup){
		if(numOfPointsToUseForChekup==list.size())return list;
		if(numOfPointsToUseForChekup>list.size()){ 
			System.out.println("You don't have that much in list, so I'll work on "+list.size()+" I have in list");
			return list;
		}

		int row=numOfPointsToUseForChekup;
		while(row<list.size()){
			int index=-1;
			for (int i = 0; i <= numOfPointsToUseForChekup; i++) {
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

	/******************* Not finished yet *********************/
	
	// function in work
		//	/**
		//	 *  Time filter, filtering by start and end times
		//	 * @param csvFile
		//	 * @param fromTime
		//	 * @param untillTime
		//	 * @param colmToFilter
		//	 * @throws Exception
		//	 */
		//	public static ArrayList<RowOfTenWifiPoints> filteringByTime(String csvFile, String fromTime, String untillTime, int colmToFilter) throws Exception {
		//		BufferedReader br = null;
		//		String line = "";
		//		String cvsSplitBy = ",";
		//		ArrayList<RowOfTenWifiPoints>listToPrint=new ArrayList<RowOfTenWifiPoints>();
		//		int numOfWifi=0;
		//		Date startFilter=new Date(fromTime);
		//		Date stopFilter=new Date(untillTime);
		//
		//		try {
		//
		//			br = new BufferedReader(new FileReader(csvFile));
		//			int numOfRow=0;  
		//			while((line = br.readLine()) != null){
		//				if(line.isEmpty())break;
		//				String[] row = line.split(cvsSplitBy);
		//				Date date=new Date(row[0]);
		//					
		//				if(date.betweenDates(startFilter, stopFilter)){
		//					String id=row[1];
		//					numOfWifi++;
		//					Coordinates_3D coordinates=new Coordinates_3D(row[2], row[3], row[4]);
		//					Wifi wifi=new Wifi(row[6], row[7], row[8],row[9]);
		//					
		//					if(listToPrint.isEmpty()){
		//					RowOfTenWifiPoints rowOfWifiPoints=new RowOfTenWifiPoints(date, id, coordinates, numOfWifi);
		//					rowOfWifiPoints.addWifiToList(wifi);
		//					}
		//					else{
		//						if(listToPrint.get(numOfRow).date.sameDate(date)){//same date
		//							listToPrint.get(numOfRow).addWifiToList(wifi);;
		//						}
		//						else{//not same date
		//							listToPrint.get(numOfRow).setNumOfWifiNetworks(numOfWifi-1);
		//							if(listToPrint.get(numOfRow).wifiList.size()>10) System.out.println(listToPrint.get(numOfRow).wifiList.size());
		//							numOfWifi=1;
		//							RowOfTenWifiPoints rowOfTen=new RowOfTenWifiPoints(date,id,coordinates,numOfWifi);
		//							rowOfTen.addWifiToList(wifi);
		//							listToPrint.add(rowOfTen);
		//							numOfRow++;
		//						}	
		//					}
		//				}
		//
		//			} br.close();
		//
		//		} catch (FileNotFoundException e) {
		//			e.printStackTrace();
		//		} catch (IOException e) {
		//			e.printStackTrace();
		//		} finally {
		//			if (br != null) {
		//				try {
		//					br.close();
		//				} catch (IOException e) {
		//					e.printStackTrace();
		//				}
		//			}
		//		}
		//		return listToPrint;
		//	}

}
