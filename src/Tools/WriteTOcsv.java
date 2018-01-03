package Tools;

import java.io.FileWriter;
import java.util.ArrayList;

import WifiPoint.RowOfWifiPoints;

public class WriteTOcsv {
	
	/**
	 * Taking table of strings  and saving it's data in .csv file
	 * @param ans string array table
	 * @param whereToSave url to save .csv file in
	 * @throws Exception
	 */
	public static void writer(ArrayList<RowOfWifiPoints>listToPrint,String whereToSave) throws Exception {

		FileWriter writer = new FileWriter(whereToSave);
		int indexOfRow=0;
		while(indexOfRow<listToPrint.size()){
			writer.write(listToPrint.get(indexOfRow).toString());
			writer.write('\n');
			indexOfRow++;
		}
		writer.flush();
		writer.close();
		
		System.out.println("CSV file is saved"); //for check up
	}
}
