package Ex0;

import java.io.File;
import java.util.List;
import java.util.Scanner;


public class MainOfFirstAndSecondParts {

	public static void main(String[] args) throws Exception  {
		//mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles();

		mainPartForBuildingKmlFileFromFilteredCsvFile();

	}


	public static void mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles() throws Exception{
		Scanner scanner=new Scanner(System.in);
		String folderName="";
		String whereToSave="";

		System.out.println("Please put here address of folder: " );
		folderName=scanner.next();
		System.out.println("Please put here address of folder you'd like me to save file in with \\file's name in the end " );
		whereToSave=scanner.next();
		ReaderWriter.readerFromFolder(folderName, whereToSave);
		scanner.close();
	}

	@SuppressWarnings("resource")
	public static int mainPartForBuildingKmlFileFromFilteredCsvFile() throws Exception{
		Scanner scanner=new Scanner(System.in);
		String addressOfFile="";
		int	choice=0;
		//		System.out.println("Please put here address of file we've created in first part: ");
		//		addressOfFile=scanner.next();
		addressOfFile="C:\\Users\\Olga\\Desktop\\bones\\test.csv";

		System.out.println("Please choose how you'd like to filter this file?");
		System.out.println("I'll give you few options and you need to choose one of them with writing me back its number");
		while(choice<1||choice>3){
			System.out.println("place=1, time=2, id=3 ");
			choice=scanner.nextInt();
			if(choice==1){
				System.out.println("Please put here a Latitude: ");
				String lat=scanner.next();
				System.out.println("And now a Longitude: ");
				String lon=scanner.next();		
				ReaderWriter.filteringByTwoVariables(addressOfFile, lat, lon, 2, 3);
			}
			else if(choice==2){
				System.out.println("Please put here a time (it has to be date: yyyy-mm-dd and hour hh:mm:ss ): ");
				System.out.println();
				System.out.println("Please put here a time you'd like to start from: ");
				String startTime=scanner.next()+" "+scanner.next();
				System.out.println("Please put here a time you'd like to stop at: ");
				String stopTime=scanner.next()+" "+scanner.next();
				ReaderWriter.filteringByTime(addressOfFile, startTime,stopTime, 0);
			}
			else if(choice==3){
				System.out.println("Please put here an id: ");
				String id=scanner.next();
				ReaderWriter.filteringByOneVariable(addressOfFile, id, 1);
			}
			else System.out.println("Number is inccorect. Please try again");

		}

		if(ReaderWriter.getAns()[0][0].equals("Does not exist")){
			System.out.println("Does not exist");
			return -1;
		}

		List<MapPoint> path=MatrixFunctions.separator(ReaderWriter.getAns());

		Kml.Kml();
		path.toArray();
		System.out.println(path.size());
		File file=new File("test.kml");
		int i=0;
		while(path.isEmpty()==false && i<path.size()){
			Kml.addMark(path.get(i));
			i++;
		}
		Kml.writeFile(file);

		scanner.close();
		return 0;
	}

}

