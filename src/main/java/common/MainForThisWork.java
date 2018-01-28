package main.java.common;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.text.Position;

import main.java.Filters.*;
import main.java.Tools.*;
import main.java.WifiData.Coordinates_3D;
import main.java.WifiData.ListOfWifiRows;
import main.java.WifiData.RowOfWifiPoints;
  
public class MainForThisWork {


	
	 /*****************************This is Main for all parts**************************/
	
	
	
	public static void main(String[] args) throws Exception  {
		
		Scanner scanner=new Scanner(System.in);
				
		/***********************main to build merged .csv file****************************/
		 
//	String addressOfFile=mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles(scanner);
		
		/***********************main to build .kml from merged csv file*******************/
		
		/**Please run this with "main to build merged csv file" or you can put address of merged .csv instead of "addressOfFile" at this format: "C:\\data\\o.csv" **/
//		String addressOfFile="C:\\Users\\Olga\\Desktop\\CsvFilesExamples\\test.csv";
//		mainPartForBuildingKmlFileFromFilteredCsvFile(addressOfFile,scanner);
		
		/**********************main for 2.a  location Of router**********************************/
		/********addressOfFile_To_Filter_With_MAC =address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
//		String addressOfFile_Where_I_need_To_Get_MAC="C:\\Users\\Olga\\Desktop\\testing\\output\\Algo1\\Algo1_BM2_4.csv";
//		String addressOfFile_To_Filter_With_MAC="C:\\Users\\Olga\\Desktop\\testing\\_comb_all_BM2_.csv";
//		int howMuch_Powerful_Wifis_To_Use_For_Calculation=4;
//		LocationRevaluation.centerOfRouter(addressOfFile_Where_I_need_To_Get_MAC,addressOfFile_To_Filter_With_MAC, howMuch_Powerful_Wifis_To_Use_For_Calculation);
		
	
		
		
		/**********************main for 2.b location Of device*****************************/
		
		/********aaddressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL=address with all mac & signals for check up****************/
		/********addressOfFile_To_Filter_With_MAC_and_Signal_I_get=address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
//		String addressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL="C:\\Users\\Olga\\Desktop\\doc_ex2\\_comb_no_gps_ts2_.csv";
//		String addressOfFile_To_Filter_With_MAC_and_Signal_I_get="C:\\Users\\Olga\\Desktop\\doc_ex2\\_comb_all_BM3_.csv";
//		int howMuch_Powerful_Wifis_To_Use_For_Calculation=4;
//		LocationRevaluation.yourLocation(addressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL, addressOfFile_To_Filter_With_MAC_and_Signal_I_get, howMuch_Powerful_Wifis_To_Use_For_Calculation);
	
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
		System.out.println("Enter folder path of WigleWifi scans: " );
		String folderName=scanner.next();
		System.out.println("Enter path to write the merged csv file into (with 'filename.csv'):" );
		String whereToSave=scanner.next();
		
		ArrayList<String>listOfFiles=ReaderFromCsv.getAllcsvFilesFromFolder(folderName);
		ListOfWifiRows listToPrint=ReaderFromCsv.notSortedFileToArrayListOfTenMostPowerfulWifiPoints(listOfFiles);
		WriteTOcsv.writer(listToPrint, whereToSave);
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
		
		/******************creating list of data from merged csv file*******************/
		ListOfWifiRows list=ReaderFromCsv.readerFromMergedCSVtoList(addressOfFile);
		
		System.out.println("Enter a number to choose a filter:");
		while(choice<1||choice>3){
			System.out.println("coordinates=1, time=2, id=3");
			choice=scanner.nextInt();
			if(choice==1){
				System.out.println();
				System.out.println("Latitude: ");
				String lat=scanner.next();
				System.out.println("Longitude: ");
				String lon=scanner.next();	
				System.out.println("Distance: ");
				double dis=Double.parseDouble(scanner.next());
				Coordinates_3D a=new Coordinates_3D(lat, lon, "0");
				filter coor=new Position_Filter_Circle(a,dis);
				list.filter(coor);
			}
			else if(choice==2){
				System.out.println();
				System.out.println("Please enter time (it has to be in format: yyyy-mm-dd hh:mm:ss) ");
				System.out.println("Please enter the time you'd like to start from: ");
				String startDate=scanner.next()+" "+scanner.next();
				System.out.println("Please enter the time you'd like to stop at: ");
				String stopDate=scanner.next()+" "+scanner.next();
				filter time=new Time_Filter(startDate,stopDate);
				list.filter(time);
			}
			else if(choice==3){
				System.out.println();
				System.out.println("Please enter an id: ");
				filter id=new Id_Filter(scanner.next());
				list.filter(id);
			}
			else System.out.println("Number is inccorect. Please try again");

		}

		if(list.isEmpty()){
			System.out.println("Not in list");
			return -1;
		}
		Kml.Kml();
		File file=new File(addressOfFile.replace(".csv", ".kml"));
		Kml.addMarksFromList(Filters.mostPowerfulWifiWithSameMac(list));
		Kml.writeFile(file);

		return 0;
	}

}

