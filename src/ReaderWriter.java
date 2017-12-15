
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Class of functions that reads & write from/to csv files
 * @author Olga & Dan
 *
 */
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

		File dir=new File(folderName);       
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
			/*********************creating list of data from csv files that been in folder**************/
			ArrayList<RowOfWifiPoints>listToPrint=notSortedFileToArrayListOfTenMostPowerfulWifiPoints(files);
			
			/*********************writing data to csv file*****************/
			WriterToCsv(listToPrint,whereToSave);
		

		}catch (NullPointerException e) {
			System.out.println();
			System.out.println("folder not found");
			System.out.println();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}		
	
	public static ArrayList<String> createListOfMacsFromCSVFile(String csvFilePath) throws FileNotFoundException
	{
		ArrayList<String> macList = new ArrayList<String>();
		FileReader fr = new FileReader(csvFilePath);
		BufferedReader br = new BufferedReader(fr);
		String cvsSplitBy = ",";
		
		try {
			String line = br.readLine();
			while(line!=null)
			{
				line.replaceAll(",,,", "");
				String[] split = line.split(cvsSplitBy);
				macList.add(split[1]);
				
				line=br.readLine();
			}
		}
		catch (FileNotFoundException e) {
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
		return macList;


	}
	
	
	/**
	 * Read from merged csv to matrix
	 * @param csvFile
	 * @return
	 * @throws Exception
	 */

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
					ans=Functions.putAllDataFromStringArrToRowInMatrix(ans,row, count);
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


	
	/**
	 * Taking table of strings  and saving it's data in .csv file
	 * @param ans string array table
	 * @param whereToSave url to save .csv file in
	 * @throws Exception
	 */
	public static void WriterToCsv(ArrayList<RowOfWifiPoints>listToPrint,String whereToSave) throws Exception {

		FileWriter writer = new FileWriter(whereToSave);
		int indexOfRow=0;
		while(indexOfRow<listToPrint.size()){
			writer.write(listToPrint.get(indexOfRow).toString());
			writer.write('\n');
			indexOfRow++;
		}
		writer.flush();
		writer.close();
		
		System.out.println("CSV file is saved"); //for checkup
	}

	
	



	
	




	/**************************private methods*****************************************/
	
	
	/**
	 * Taking .csv or .txt file and sorting it's data in table
	 * @param csvFile url of file to open
	 * @throws Exception
	 * return ArrayList of rows with ten most powerful wifiPoints in each
	 */
	private static ArrayList<RowOfWifiPoints> notSortedFileToArrayListOfTenMostPowerfulWifiPoints(ArrayList<String>files) throws Exception {
		ArrayList<RowOfWifiPoints>listToPrint=new ArrayList<RowOfWifiPoints>();
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
							RowOfWifiPoints rowOfTen=new RowOfWifiPoints(date,id,coord,numOfWifi);
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
								RowOfWifiPoints rowOfTen=new RowOfWifiPoints(date,id,coord,numOfWifi);
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

	
	
	/************************not finished yet********************/

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

}


