package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import WifiData.Coordinates_3D;
import WifiData.Date;
import WifiData.ListOfWifiRows;
import WifiData.RowOfWifiPoints;
import WifiData.Wifi;

/**
 * Class of functions that reads & write from/to csv files
 * @author Olga & Dan
 *
 */
public class ReaderFromCsv {

	
	/**
	 * Checking the folder for .csv and .txt files
	 * @param directoryName directory to search csv files
	 * @return list with all the csv files paths
	 */
	public static ArrayList<String> getAllcsvFilesFromFolder(String directoryName){

		ArrayList<String> fileList = new ArrayList<String>();
		File directory = new File(directoryName);

		//get all the files from a directory

		if (!directory.isDirectory()) {
			return fileList;
		}
		File[] fList = directory.listFiles();

		for (File file : fList){

			if (file.isFile()){

				if(file.getAbsolutePath().endsWith(".csv")){
					fileList.add(file.getAbsolutePath());
					//System.out.println("Fetching data from: "+file.getAbsolutePath());
					//printLog(file);
				
				}

			} else if (file.isDirectory()){

				fileList.addAll(getAllcsvFilesFromFolder(file.getAbsolutePath()));
			}
		}
		return fileList;

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
				if(line.isEmpty())break;
				line.replaceAll(",,,", "");
				String[] split = line.split(cvsSplitBy);
				macList.add(split[1].replaceAll(" ", ""));
				
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
	
	
	public static ListOfWifiRows readerFromMergedCSVtoList(String csvFile) throws Exception{
		ListOfWifiRows list=new ListOfWifiRows();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		try {

			br = new BufferedReader(new FileReader(csvFile));

			while((line = br.readLine()) != null ){
				if(line.isEmpty())break;
				String[] row = line.split(cvsSplitBy);
				Date date=new Date(row[0]);
				String ID=row[1];
				Coordinates_3D coord=new Coordinates_3D(row[2], row[3], row[4]);
				int numOfNetw=Integer.parseInt(row[5]);
				RowOfWifiPoints a=new RowOfWifiPoints(date, ID, coord, numOfNetw);
				for (int i = 6; i+3 < row.length; i=i+4) {
					if(row[i+1].contains(":") && row[i+1].length()==17){
					Wifi wifi=new Wifi(row[i],row[i+1],row[i+2],row[i+3]);
					a.wifiList.add(wifi);}
					else{Wifi wifi=new Wifi(row[i+1],row[i],row[i+2],row[i+3]);
					a.wifiList.add(wifi);}
					
				}
				list.add(a);
			} br.close();
	

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
		return list;

	}


	
	
	/**
	 * Taking .csv or .txt file and sorting it's data in table
	 * @param csvFile url of file to open
	 * @throws Exception
	 * return ArrayList of rows with ten most powerful wifiPoints in each
	 */
	public static ListOfWifiRows notSortedFileToArrayListOfTenMostPowerfulWifiPoints(ArrayList<String>files) throws Exception {
		ListOfWifiRows listToPrint=new ListOfWifiRows();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int indexOfFile=0;
		int numOfRow=0; 
		int[]place=new int[10];
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
								listToPrint.get(numOfRow).addWifiToList(wifi);
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
	

	/**************************private methods*****************************************/
	private static void printLog(File file) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                	System.out.println("Fetching data from: "+file.getAbsolutePath());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

}


