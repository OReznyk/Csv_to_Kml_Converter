
import java.io.File;
import java.util.List;
import java.util.Scanner;


public class MainOfFirstAndSecondParts {

	public static void main(String[] args) throws Exception  {
		Scanner scanner=new Scanner(System.in);
		//String addressOfFile=mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles(scanner);

		mainPartForBuildingKmlFileFromFilteredCsvFile("C:\\Users\\Olga\\Desktop\\data[1091]\\data\\o.csv",scanner);
		scanner.close();
		
		
	}


	public static String mainPartForBuildingFilteredCsvFileFromFolderOfNotFilteredCsvFiles(Scanner scanner) throws Exception{
		String folderName="";
		String whereToSave="";

		System.out.println("Please put here address of folder: " );
		folderName=scanner.next();
		System.out.println("Please put here address of folder you'd like me to save file in with \\file's name in the end " );
		whereToSave=scanner.next();
		ReaderWriter.readerFromFolderToCsvFile(folderName, whereToSave);
		return whereToSave;
	}

	public static int mainPartForBuildingKmlFileFromFilteredCsvFile(String addressOfFile,Scanner scanner) throws Exception{
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
			ans=	ReaderWriter.filteringByOneVariable(addressOfFile, filter,1);
			}
			else System.out.println("Number is inccorect. Please try again");

		}

		if(ans[0][0].equals("Does not exist")){
			System.out.println("Does not exist");
			return -1;
		}

		List<MapPoint> path=MatrixFunctions.separator(ans);

		Kml.Kml();
		path.toArray();
		System.out.println(path.size());
		File file=new File("C:\\Users\\Olga\\Desktop\\data[1091]\\data\\test3.kml");
		int i=0;
		while(path.isEmpty()==false && i<path.size()){
			Kml.addMark(path.get(i));
			i++;
		}
		Kml.writeFile(file);

		return 0;
	}

}

