package Ex0;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalTime;

public class ReaderWriter {

	static int[]place=new int[10];
	static String[][]ans=new String[15][46];

	/**
	 * Checking the folder for .csv and .txt files.
	 * If they exists, opening them one by one and saving the sorted data from all of them in .csv file 
	 * @param folderName url of folder to check
	 * @param whereToSave url of place to save in
	 * @throws Exception
	 */
	public static void readerFromFolder(String folderName,String whereToSave) throws Exception{
		File dir=new File(folderName); //here you put name of folder you want to read from
		String[]namesOfFiles=new String[2];
		String name="";
		int count=0;

		try{
			for(File f:dir.listFiles()){ //opening folder and checking files in it
				if(f.getName().endsWith(".txt") || f.getName().endsWith(".csv") )
				{
					name=f.getName();
					if(count==namesOfFiles.length) namesOfFiles=MatrixFunctions.reBuild(namesOfFiles);
					namesOfFiles[count++]=name;
				}
			}
			//Functions.toPrint(namesOfFiles); //for checkup
			int i=0;
			while(i<namesOfFiles.length){
				if((namesOfFiles[i]!=null)){
					notSortedFileToTable(namesOfFiles[i]);
					i++;
				}
				else break;
			}	
			WriterToCsv(ReaderWriter.getAns(),whereToSave);
			//System.out.println("CSV file is saved"); //for checkup

		}catch (NullPointerException e) {
			System.out.println("folder not found");
			System.out.println();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();

		}

	}		

	/**
	 * Taking .csv or .txt file and sorting it's data in table
	 * @param csvFile url of file to open
	 * @throws Exception
	 */
	
	
	public static void notSortedFileToTable(String csvFile) throws Exception {
		String[][]	arr=new String[20][place.length];
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		String Display="";
		String ID="1";


		try {

			br = new BufferedReader(new FileReader(csvFile));

			boolean temp=false;
			int count=0;  

			while((line = br.readLine()) != null ){

				String[] row = line.split(cvsSplitBy);

				if(temp!=true)	{

					for (int i = 0; i < row.length; i++) {
						if (row[i].contains("display")) Display=row[i].substring(8);

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
					if(count==arr.length){
						arr=MatrixFunctions.reBuild(arr);
					}
					else{
						arr[count][0]=row[place[0]];
						arr[count][1]=Display;

						for (int j = 2; j < 5; j++) {
							arr[count][j]=row[place[j]];
						}

						if(count>0 && arr[count][0].equals(arr[count-1][0])){
							ID=""+(Integer.parseInt(ID)+1);
						} 
						else ID="1";
						arr[count][5]=""+ID;

						for (int j = 6; j < place.length; j++) {
							arr[count][j]=row[place[j]];
						} count++;
					}
				}
			} br.close();

			ans=MatrixFunctions.toSort(arr,ans);

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

	/**
	 * Taking sorted .csv file
	 * Choosing data by filter and saving it in table
	 * @param csvFile url of file to open
	 * @param filter variable to filter with
	 * @param colmToFilter number of column to search in
	 * @throws Exception
	 */
	public static void filteringByOneVariable(String csvFile, String filter, int colmToFilter) throws Exception{
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));

			int count=0;  

			while((line = br.readLine()) != null ){
				String[] row = line.split(cvsSplitBy);
				if(row[colmToFilter].contains(filter)){
					ans=MatrixFunctions.buildStringTableFromStringARR(ans,row, count);
					count++;
				}
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
	
	}

	/**
	 *  Time filter, filtering by start and end times
	 * @param csvFile
	 * @param fromTime
	 * @param untillTime
	 * @param colmToFilter
	 * @throws Exception
	 */
	public static void filteringByTime(String csvFile, String fromTime, String untillTime, int colmToFilter) throws Exception {
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		TimeSplitterFromStringToInt startFilter=new TimeSplitterFromStringToInt(fromTime);
		TimeSplitterFromStringToInt stopFilter=new TimeSplitterFromStringToInt(untillTime);

		try {

			br = new BufferedReader(new FileReader(csvFile));

			int count=0;  

			while((line = br.readLine()) != null ){
				String[] row = line.split(cvsSplitBy);
				TimeSplitterFromStringToInt thisTime=new TimeSplitterFromStringToInt(row[0]);

				if(thisTime.getDate().equals(startFilter.getDate()) && thisTime.getDate().equals(stopFilter.getDate())){
					if(thisTime.getTime().equals(startFilter.getTime()) || thisTime.getTime().equals(stopFilter.getTime()) || thisTime.getTime().isAfter(startFilter.getTime()) && thisTime.getTime().isBefore(stopFilter.getTime())){
						ans=MatrixFunctions.buildStringTableFromStringARR(ans,row, count);
						count++;
					}		
				}


				if(thisTime.getDate().equals(startFilter.getDate()) && thisTime.getDate().equals(stopFilter.getDate())==false ){
					System.out.println(thisTime.getTime()+" and "+startFilter.getTime());
					if(thisTime.getTime().equals(startFilter.getTime()) || thisTime.getTime().isAfter(startFilter.getTime()) && thisTime.getTime().isBefore(LocalTime.parse("23:59:59"))){
						ans=MatrixFunctions.buildStringTableFromStringARR(ans,row, count );
						count++;
					}		
				}
				if(thisTime.getDate().equals(stopFilter.getDate()) && thisTime.getDate().equals(startFilter.getDate())==false){
					if( thisTime.getTime().equals(stopFilter.getTime()) || thisTime.getTime().isAfter(LocalTime.parse("00:00:00")) && thisTime.getTime().isBefore(stopFilter.getTime())){
						ans=MatrixFunctions.buildStringTableFromStringARR(ans,row, count);
						count++;
					}		
				}

				if(thisTime.getDate().isAfter(startFilter.getDate()) && thisTime.getDate().isBefore(stopFilter.getDate())){
					if(thisTime.getTime().isAfter(LocalTime.parse("00:00:00")) && thisTime.getTime().isBefore(LocalTime.parse("23:59:59"))){
						ans=MatrixFunctions.buildStringTableFromStringARR(ans,row, count);
						count++;
					}	
				}
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


	}

	/**
	 * Taking sorted .csv file
	 * Choosing data by filter and saving it in table
	 * @param csvFile url of file to open
	 * @param lat latitude 
	 * @param lon longitude
	 * @param latColmToFilter number of latitude column to search in
	 * @param lonColmToFilter number of  longitude column to search in
	 */
	public static void filteringByTwoVariables(String csvFile,String lat,String lon, int latColmToFilter,int lonColmToFilter){
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";

		try {

			br = new BufferedReader(new FileReader(csvFile));

			int count=0;  

			while((line = br.readLine()) != null ){

				String[] row = line.split(cvsSplitBy);

				if(row[latColmToFilter].contains(lat) && row[lonColmToFilter].contains(lon)){
					ans=MatrixFunctions.buildStringTableFromStringARR(ans, row, count++ );
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

	}

	/**
	 * Taking table of strings  and saving it's data in .csv file
	 * @param ans string array table
	 * @param whereToSave url to save .csv file in
	 * @throws Exception
	 */
	public static void WriterToCsv(String[][]ans,String whereToSave) throws Exception {

		String csvFile = whereToSave;
		FileWriter writer = new FileWriter(csvFile);
		for (int i = 0; i < ans.length; i++) {	
			for (int j = 0; j < ans[0].length; j++) {
				if(ans[i][j]!=null){
					writer.write(ans[i][j]);
					writer.write(",");
				}
				else break; 
			}
			writer.write('\n');
		}
		writer.flush();
		writer.close();
	}

	/**
	 * Get the array table associated with all data from last function you worked in.
	 * @return
	 */
	public static String[][] getAns() {
		return ans;
	}
}


