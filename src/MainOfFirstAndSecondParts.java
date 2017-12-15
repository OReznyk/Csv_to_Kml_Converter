

import java.io.File;
import java.util.List;
import java.util.Scanner;
  
public class MainOfFirstAndSecondParts {


	
	 /*****************************This is Main for all parts**************************/
	
	
	
	public static void main(String[] args) throws Exception  {
		
		Scanner scanner=new Scanner(System.in);
		
		/***********************main to build merged .csv file****************************/
		 
		//String addressOfFile=mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles(scanner);
		
		/***********************main to build .kml from merged csv file*******************/
		
		/**Please run this with "main to build merged csv file" or you can put address of merged .csv instead of "addressOfFile" at this format: "C:\\data\\o.csv" **/
		//String addressOfFile="C:\\Users\\Olga\\Desktop\\data[1091]\\data\\o.csv";
		//mainPartForBuildingKmlFileFromFilteredCsvFile(addressOfFile,scanner);
		
		/**********************main for 2.a  location Of router**********************************/
		/********addressOfFile_To_Filter_With_MAC =address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
//		String addressOfFile_Where_I_need_To_Get_MAC="C:\\Users\\Olga\\Desktop\\New folder (2)\\data\\BM2\\comb\\Algo1_4_1512843349855__comb_all_.csv";
//		String addressOfFile_To_Filter_With_MAC="C:\\Users\\Olga\\Desktop\\New folder (2)\\data\\BM2\\comb\\_comb_all_.csv";
//		int howMuch_Powerful_Wifis_To_Use_For_Calculation=3;
//		LocationRevaluation.centerOfRouter(addressOfFile_Where_I_need_To_Get_MAC,addressOfFile_To_Filter_With_MAC, howMuch_Powerful_Wifis_To_Use_For_Calculation);
//		
		
		
		/**********************main for 2.b location Of device*****************************/
		
		/********aaddressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL=address with all mac & signals for check up****************/
		/********addressOfFile_To_Filter_With_MAC_and_Signal_I_get=address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
		String addressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL="C:\\Users\\Olga\\Desktop\\New folder (2)\\data\\TS2\\_comb_no-GPS_TS2_.csv";
		String addressOfFile_To_Filter_With_MAC_and_Signal_I_get="C:\\Users\\Olga\\Desktop\\New folder (2)\\data\\BM3\\comb\\_comb_all_.csv";
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
		String folderName="";
		String whereToSave="";

		System.out.println("Please put here address of folder: " );
		folderName=scanner.next();
		System.out.println("Please put here address of folder you'd like me to save file in with \\file's name in the end " );
		whereToSave=scanner.next();
		ReaderWriter.readerFromFolderToCsvFile(folderName, whereToSave);
		return whereToSave;
	}
	/**
	 * This function running project 0.2 from merged.csv to merged .kml 
	 * @param scanner
	 * @return 0 if data saved
	 * @throws Exception
	 */

	private static int mainPartForBuildingKmlFileFromFilteredCsvFile(String addressOfFile,Scanner scanner) throws Exception{
		String[][]ans=new String[1][1];
		int	choice=0;
		System.out.println("Please choose how you'd like to filter this file?");
		System.out.println("I'll give you few options and you need to choose one of them with writing me back its number");
		while(choice<1||choice>3){
			System.out.println("place=1, time=2, id=3");
			/******************creating matrix of data from merged csv file*******************/
			String[][]matrix=ReaderWriter.readerFromMergedCSVtoMatrix(addressOfFile);
			choice=scanner.nextInt();
			if(choice==1){
				System.out.println("Please put here a Latitude: ");
				String lat=scanner.next();
				System.out.println("And now a Longitude: ");
				String lon=scanner.next();		
			ans=	Filters.filteringByTwoVariables(matrix, lat, lon, 2, 3);
			}
			else if(choice==2){
				System.out.println("Please put here a time (it has to be date: yyyy-mm-dd and hour hh:mm:ss ): ");
				System.out.println();
				System.out.println("Please put here a time you'd like to start from: ");
				String startTime=scanner.next()+" "+scanner.next();
				System.out.println("Please put here a time you'd like to stop at: ");
				String stopTime=scanner.next()+" "+scanner.next();
			ans=	Filters.filteringByTime(matrix, startTime,stopTime, 0);
			}
			else if(choice==3){
				System.out.println("Please put here an id: ");
				String filter=scanner.next();
				
			    ans=Filters.filteringByOneLocalVariable(matrix, filter,1);
			}
			else System.out.println("Number is inccorect. Please try again");

		}

		if(ans[0][0].equals("Does not exist")){
			System.out.println("Does not exist");
			return -1;
		}

		List<MapPoint> path=Functions.separator(ans);

		Kml.Kml();
		path.toArray();
		System.out.println(path.size());
		File file=new File(addressOfFile.replace(".csv", ".kml"));
		int i=0;
		while(path.isEmpty()==false && i<path.size()){
			Kml.addMark(path.get(i));
			i++;
		}
		Kml.writeFile(file);

		return 0;
	}

}

