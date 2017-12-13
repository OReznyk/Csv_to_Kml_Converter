
import java.io.File;
import java.util.ArrayList;
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
		
		//mainPartForBuildingKmlFileFromFilteredCsvFile(addressOfFile,scanner);
		
		/**********************main for 2.a  location Of router**********************************/
		/********addressOfFile_To_Filter_With_MAC =address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
		//System.out.println(LocationRevaluation.centerOfRouter(addressOfFile_To_Filter_With_MAC_and_Signal_I_get, "3c:1e:04:03:7f:17", howMuch_Powerful_Wifis_To_Use_For_Calculation).rowToString());
		
		
		
		/**********************main for 2.b location Of device*****************************/
		
		/********aaddressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL=address with all mac & signals for check up****************/
		/********addressOfFile_To_Filter_With_MAC_and_Signal_I_get=address of merged csv****************/
		/********howMuch_Powerful_Wifis_To_Use_For_Calculation (int number)*************************************/
		//LocationRevaluation.yourLocation(addressOfFile_Where_I_need_To_Get_MAC_and_SIGNAL, addressOfFile_To_Filter_With_MAC_and_Signal_I_get, howMuch_Powerful_Wifis_To_Use_For_Calculation);
	
		scanner.close();		
	}


	
	
	
	
	
	/***********************************private *********************************/
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

	private static int mainPartForBuildingKmlFileFromFilteredCsvFile(String addressOfFile,Scanner scanner) throws Exception{
		String[][]ans=new String[1][1];
		int	choice=0;
		System.out.println("Please choose how you'd like to filter this file?");
		System.out.println("I'll give you few options and you need to choose one of them with writing me back its number");
		while(choice<1||choice>4){
			System.out.println("place=1, time=2, id=3 , mac=4");
			choice=scanner.nextInt();
			if(choice==1){
				System.out.println("Please put here a Latitude: ");
				String lat=scanner.next();
				System.out.println("And now a Longitude: ");
				String lon=scanner.next();		
			ans=	ReaderWriter.filteringByTwoVariables(addressOfFile, lat, lon, 2, 3);
			}
			else if(choice==2){
				System.out.println("Please put here a time (it has to be date: yyyy-mm-dd and hour hh:mm:ss ): ");
				System.out.println();
				System.out.println("Please put here a time you'd like to start from: ");
				String startTime=scanner.next()+" "+scanner.next();
				System.out.println("Please put here a time you'd like to stop at: ");
				String stopTime=scanner.next()+" "+scanner.next();
			ans=	ReaderWriter.filteringByTime(addressOfFile, startTime,stopTime, 0);
			}
			else if(choice==3){
				System.out.println("Please put here an id: ");
				String filter=scanner.next();
				ans=ReaderWriter.readerFromMergedCSVtoMatrix(addressOfFile);
			    ans=	ReaderWriter.filteringByOneVariable(ans, filter,1);
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

