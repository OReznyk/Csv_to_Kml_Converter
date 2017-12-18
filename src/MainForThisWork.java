

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
  
public class MainForThisWork {


	
	 /*****************************This is Main for all parts**************************/
	
	
	
	public static void main(String[] args) throws Exception  {
		
		Scanner scanner=new Scanner(System.in);
				
		/***********************main to build merged .csv file****************************/
		 
//		String addressOfFile=mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles(scanner);
		
		/***********************main to build .kml from merged csv file*******************/
		
		/**Please run this with "main to build merged csv file" or you can put address of merged .csv instead of "addressOfFile" at this format: "C:\\data\\o.csv" **/
//		String addressOfFile="C:\\Users\\Olga\\Desktop\\data[1091]\\test.csv";
//		mainPartForBuildingKmlFileFromFilteredCsvFile(addressOfFile,scanner);
		
		/**********************main for 2.a  location Of router**********************************/
		/********addressOfFile_To_Filter_With_MAC =address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
//		String addressOfFile_Where_I_need_To_Get_MAC="C:\\Users\\Olga\\Desktop\\testing\\";
//		String addressOfFile_To_Filter_With_MAC="C:\\Users\\Olga\\Desktop\\testing\\_comb_all_BM2_.csv";
//		int howMuch_Powerful_Wifis_To_Use_For_Calculation=4;
//		LocationRevaluation.centerOfRouter(addressOfFile_Where_I_need_To_Get_MAC,addressOfFile_To_Filter_With_MAC, howMuch_Powerful_Wifis_To_Use_For_Calculation);
		
	
		
		
		/**********************main for 2.b location Of device*****************************/
		
		/********aaddressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL=address with all mac & signals for check up****************/
		/********addressOfFile_To_Filter_With_MAC_and_Signal_I_get=address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
		String addressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL="C:\\Users\\Olga\\Desktop\\testing\\_comb_no_gps_ts1.csv";
		String addressOfFile_To_Filter_With_MAC_and_Signal_I_get="C:\\Users\\Olga\\Desktop\\testing\\_comb_all_BM3_.csv";
		int howMuch_Powerful_Wifis_To_Use_For_Calculation=4;
		LocationRevaluation.yourLocation(addressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL, addressOfFile_To_Filter_With_MAC_and_Signal_I_get, howMuch_Powerful_Wifis_To_Use_For_Calculation);
	
		scanner.close();		
	}


	
	
	
	
	
	/***********************************private *********************************/
	
	/**
	 * This function running project 0.1 from Wigle.csv to merged .csv with 10 most powerful wifi points in each row
	 * @param scanner
	 * @return address where user want to save sorted data
	 * @throws Exception
	 */
	private static String mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles(Scanner scanner) throws Exception{
		System.out.println("Please put here address of folder: " );
		String folderName=scanner.next();
		System.out.println("Please put here address of folder you'd like me to save file in with \\file's name in the end " );
		String whereToSave=scanner.next();
		
		ArrayList<String>listOfFiles=ReaderWriter.getAllcsvFilesFromFolder(folderName);
		ArrayList<RowOfWifiPoints>listToPrint=ReaderWriter.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
		ReaderWriter.WriterToCsv(listToPrint, whereToSave);
		//ReaderWriter.readerFromFolderToCsvFile(folderName, whereToSave);
		
		return whereToSave;
	}
	
	/**
	 * This function running project 0.2 from merged.csv to merged .kml 
	 * @param scanner
	 * @return 0 if data saved
	 * @throws Exception
	 */

	private static int mainPartForBuildingKmlFileFromFilteredCsvFile(String addressOfFile,Scanner scanner) throws Exception{
		int	choice=0;
		ArrayList<RowOfWifiPoints>filteredList=new ArrayList<>();
		
		/******************creating list of data from merged csv file*******************/
		ArrayList<RowOfWifiPoints>list=ReaderWriter.readerFromMergedCSVtoList(addressOfFile);
		
		System.out.println("Please choose how you'd like to filter this file?");
		System.out.println("I'll give you few options and you need to choose one of them with writing me back its number");
		while(choice<1||choice>3){
			System.out.println("coordinates=1, time=2, id=3");
			choice=scanner.nextInt();
			if(choice==1){
				System.out.println("Please put here a Latitude: ");
				String lat=scanner.next();
				System.out.println("And now a Longitude: ");
				String lon=scanner.next();		
				filteredList=Filters.filteringByCoordinates(list, lat, lon);
			}
			else if(choice==2){
				System.out.println("Please put here a time (it has to be date: yyyy-mm-dd and hour hh:mm:ss ): ");
				System.out.println();
				System.out.println("Please put here a time you'd like to start from: ");
				String startDate=scanner.next()+" "+scanner.next();
				System.out.println("Please put here a time you'd like to stop at: ");
				String stopDate=scanner.next()+" "+scanner.next();
				filteredList=Filters.filteringByTime(list, startDate, stopDate);
			}
			else if(choice==3){
				System.out.println("Please put here an id: ");
				String id=scanner.next();
				filteredList=Filters.filteringByID(list, id);
			}
			else System.out.println("Number is inccorect. Please try again");

		}

		if(filteredList.isEmpty()){
			System.out.println("Not in list");
			return -1;
		}
		filteredList=Filters.mostPowerfulWifiWithSameMac(filteredList);
		Kml.Kml();
		File file=new File(addressOfFile.replace(".csv", ".kml"));
		Kml.addMarksFromList(filteredList);
		Kml.writeFile(file);

		return 0;
	}

}
