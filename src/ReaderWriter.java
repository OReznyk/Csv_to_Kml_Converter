
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ReaderWriter {

	static int[]place=new int[10];
	String[][]ans=new String[15][46];

	/**
	 * Checking the folder for .csv and .txt files.
	 * If they exists, opening them one by one and saving the sorted data from all of them in .csv file 
	 * @param folderName url of folder to check
	 * @param whereToSave url of place to save in
	 * @throws Exception
	 */
	public static void readerFromFolderToCsvFile(String folderName,String whereToSave) throws Exception{

		File dir=new File(folderName);       //here you put name of folder you want to read from
		ArrayList<String> files = new ArrayList<String>();
		String name="";

		try{
			for(File f:dir.listFiles()){     //opening folder and checking files in it
				if(f.getName().endsWith(".txt") || f.getName().endsWith(".csv") )
				{
					name=folderName+"\\"+f.getName();
					files.add(name);
				}
			}
			ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint=notSortedFileToArrayListOfTenMostPowerfulWifiPoints(files);

			WriterToCsv(listToPrint,whereToSave);
			System.out.println("CSV file is saved"); //for checkup

		}catch (NullPointerException e) {
			System.out.println();
			System.out.println("folder not found");
			System.out.println();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		

	public static String[][] readerFromMergedCSVtoMatrix(String csvFile) throws Exception{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[][]ans=new String[15][46];

		try {

			br = new BufferedReader(new FileReader(csvFile));

			int count=0;  

			while((line = br.readLine()) != null ){
				if(line.isEmpty())break;
				String[] row = line.split(cvsSplitBy);
					ans=Functions.buildStringTableFromStringARR(ans,row, count);
					count++;
					if(count==ans.length)ans=Functions.reBuild(ans);

			} br.close();

			if(ans[0][0]==null){
				ans[0][0]="Does not exist";}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ans;

	}


	public static String[][] filteringByOneVariable(String[][]matrix,String filter, int colmToFilter){

		String[][]ans=new String[15][46];
		int count=0,i=0;
		
		while(matrix[i][0]!=null){
			
		 if(matrix[i][colmToFilter].equals(filter)){
					ans=Functions.buildStringTableFromStringARR(ans,matrix[i], count);
					count++;
					if(count==ans.length)ans= Functions.reBuild(ans);
		 }
		 i++;
				}
	
		return ans;

	}

	/**
	 * Taking sorted .csv file
	 * Choosing data by MAC and saving 4 of Most powerful in table
	 * @param csvFile url of file to open
	 * @param filter variable to filter with
	 * @param colmToFilter number of column to search in
	 * @throws Exception
	 */
	public static ArrayList<RowOfNoMoreThenTenWifiPoints> filteringByMAC(String [][]ans,ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint, String filter, int count) throws Exception{
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
							RowOfNoMoreThenTenWifiPoints WifiPoint=new RowOfNoMoreThenTenWifiPoints(date, id, coord, 1);
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
								RowOfNoMoreThenTenWifiPoints WifiPoint=new RowOfNoMoreThenTenWifiPoints(date, id, coord, 1);
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
	 *  Time filter, filtering by start and end times
	 * @param csvFile
	 * @param startDate
	 * @param stopDate
	 * @param colmToFilter
	 * @throws Exception
	 */
	public static String[][] filteringByTime(String csvFile, String startDate, String stopDate, int colmToFilter) throws Exception {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[][]ans=new String[15][46];

		Date startFilter=new Date(startDate);
		Date stopFilter=new Date(stopDate);

		try {

			br = new BufferedReader(new FileReader(csvFile));

			int count=0;  

			while((line = br.readLine()) != null ){
				String[] row = line.split(cvsSplitBy);
				Date thisTime=new Date(row[0]);
				if(thisTime.betweenDates(startFilter, stopFilter)){
					ans=Functions.buildStringTableFromStringARR(ans,row, count);
					count++;}


			} br.close();

			if(ans[0][0]==null){
				ans[0][0]="Does not exist";}



		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ans;


	}

	//
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

	/**
	 * Taking sorted .csv file
	 * Choosing data by filter and saving it in table
	 * @param csvFile url of file to open
	 * @param lat latitude 
	 * @param lon longitude
	 * @param latColmToFilter number of latitude column to search in
	 * @param lonColmToFilter number of  longitude column to search in
	 */
	public static String[][] filteringByTwoVariables(String csvFile,String lat,String lon, int latColmToFilter,int lonColmToFilter){
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String[][]ans=new String[15][46];

		try {

			br = new BufferedReader(new FileReader(csvFile));

			int count=0;  

			while((line = br.readLine()) != null ){
				if(line.isEmpty())break;
				String[] row = line.split(cvsSplitBy);

				if(row[latColmToFilter].contains(lat) && row[lonColmToFilter].contains(lon)){
					ans=Functions.buildStringTableFromStringARR(ans, row, count++ );
					System.out.println();
				}

			} br.close();

			if(ans[0][0]==null){
				ans[0][0]="Does not exist";
			}


		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return ans;

	}





	/**************************private methods*****************************************/

	//	private static String[][] sortedFileToArrayListOfTenMostPowerfulWifiPoints(String csvFile) throws Exception {
	//		BufferedReader br = null;
	//		String line = "";
	//		String cvsSplitBy = ",";
	//		String[][]ans=new String[15][46];
	//
	//		try {
	//
	//			br = new BufferedReader(new FileReader(csvFile));
	//
	//			int count=0;  
	//
	//			while((line = br.readLine()) != null ){
	//				if(line.isEmpty())break;
	//				String[] row = line.split(cvsSplitBy);
	//					ans=MatrixFunctions.buildStringTableFromStringARR(ans, row, count++ );
	//			} br.close();
	//
	//			if(ans[0][0]==null){
	//				ans[0][0]="Does not exist";
	//			}
	//		
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
	//		return ans;
	//	}

	
	
	/**
	 * Taking .csv or .txt file and sorting it's data in table
	 * @param csvFile url of file to open
	 * @throws Exception
	 * return ArrayList of rows with ten most powerful wifiPoints in each
	 */
	private static ArrayList<RowOfNoMoreThenTenWifiPoints> notSortedFileToArrayListOfTenMostPowerfulWifiPoints(ArrayList<String>files) throws Exception {
		ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint=new ArrayList<RowOfNoMoreThenTenWifiPoints>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int indexOfFile=0;
		int numOfRow=0; 

		while(indexOfFile!=files.size()){

			String id="";
			int numOfWifi=0;
			try {
				br = new BufferedReader(new FileReader(files.get(indexOfFile)));
				boolean temp=false;
				while((line = br.readLine()) != null ){
					if(line.isEmpty())break;
					String[] row = line.split(cvsSplitBy);

					if(temp!=true)	{

						for (int i = 0; i < row.length; i++) {
							if (row[i].contains("display")) id=row[i].substring(8);

							if (row[i].equals("FirstSeen")) place[0]=i;
							if (row[i].equals("CurrentLatitude")) place[2]=i;
							if (row[i].equals("CurrentLongitude")) place[3]=i;	
							if (row[i].equals("AltitudeMeters")) place[4]=i;	

							if (row[i].equals("SSID")) place[6]=i;
							if (row[i].equals("MAC")) {place[7]=i; temp=true;}
							if (row[i].equals("Channel")) place[8]=i;
							if (row[i].equals("RSSI"))  place[9]=i; 
						}
					}
					else{
						Coordinates_3D coord=new Coordinates_3D(row[place[2]], row[place[3]], row[place[4]]);
						Date date=new Date(row[place[0]]);
						Wifi wifi=new Wifi(row[place[6]], row[place[7]], row[place[8]], row[place[9]]);
						numOfWifi++;

						if(listToPrint.isEmpty()){
							RowOfNoMoreThenTenWifiPoints rowOfTen=new RowOfNoMoreThenTenWifiPoints(date,id,coord,numOfWifi);
							rowOfTen.addWifiToList(wifi);
							listToPrint.add(rowOfTen);
						}
						else{ 
							if(listToPrint.get(numOfRow).date.sameDate(date)){//same date
								listToPrint.get(numOfRow).addWifiToList(wifi);;
							}
							else{//not same date
								listToPrint.get(numOfRow).setNumOfWifiNetworks(numOfWifi-1);
								if(listToPrint.get(numOfRow).wifiList.size()>10) System.out.println(listToPrint.get(numOfRow).wifiList.size());
								numOfWifi=1;
								RowOfNoMoreThenTenWifiPoints rowOfTen=new RowOfNoMoreThenTenWifiPoints(date,id,coord,numOfWifi);
								rowOfTen.addWifiToList(wifi);
								listToPrint.add(rowOfTen);
								numOfRow++;
							}
						}		
					}
				}indexOfFile++;
				br.close();

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (br != null) {
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return listToPrint;
	}

	/**
	 * Taking table of strings  and saving it's data in .csv file
	 * @param ans string array table
	 * @param whereToSave url to save .csv file in
	 * @throws Exception
	 */
	private static void WriterToCsv(ArrayList<RowOfNoMoreThenTenWifiPoints>listToPrint,String whereToSave) throws Exception {

		FileWriter writer = new FileWriter(whereToSave);
		int indexOfRow=0;
		while(indexOfRow<listToPrint.size()){
			writer.write(listToPrint.get(indexOfRow).rowToString());
			writer.write('\n');
			indexOfRow++;
		}
		writer.flush();
		writer.close();
	}


}


