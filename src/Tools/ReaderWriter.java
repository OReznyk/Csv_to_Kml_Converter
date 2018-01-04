package Tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

import WifiPoint.Coordinates_3D;
import WifiPoint.Date;
import WifiPoint.Wifi;

/**
 * Class of functions that reads & write from/to csv files
 * @author Olga & Dan
 *
 */
public class ReaderWriter {

	static int[]place=new int[10];

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
					System.out.println("Fetching data from: "+file.getAbsolutePath());
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


	public static ArrayList<RowOfWifiPoints> readerFromMergedCSVtoList(String csvFile) throws Exception{
		ArrayList<RowOfWifiPoints>list=new ArrayList<RowOfWifiPoints>();
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
		System.out.println("Saved successfully");
		return list;
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

		System.out.println("CSV file saved successfully"); //for checkup
	}












	/**************************private methods*****************************************/


	/**
	 * Taking .csv or .txt file and sorting it's data in table
	 * @param csvFile url of file to open
	 * @throws Exception
	 * return ArrayList of rows with ten most powerful wifiPoints in each
	 */
	public static ArrayList<RowOfWifiPoints> notSortedFileToArrayListOfTenMostPowerfulWifiPoints(ArrayList<String>files) throws Exception {
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

	public static ArrayList<RowOfWifiPoints> notSortedFileToArrayList(String csvFile) throws Exception {
		ArrayList<RowOfWifiPoints>listToPrint=new ArrayList<RowOfWifiPoints>();
		BufferedReader br = null;
		String line = "";
		String cvsSplitBy = ",";
		int numOfRow=0; 
		String id="";
		int numOfWifi=0;
		try {
			br = new BufferedReader(new FileReader(csvFile));
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
			}
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

		return listToPrint;
	}
	
	
	private static void printToKmlByGPS(ArrayList<String[]> list, String path, double[] rectTop, double[] rectBot)
	{
		HashMap<Integer, String> mac = new HashMap<>(); //creating a list of mac
		for(int i = 0; i < list.size(); i++)
		{
			mac.put(i, list.get(i)[7]);
		}

		String temp = "";
		String[] lineMark = {"#","","","","","","-200"}; // used to mark lines that won't get added to the kml file
		for(int i = 0; i < list.size(); i++)
		{
			temp = mac.get(i);
			for(int j = i + 1; j < list.size(); j++)
			{
				if(temp.equals(mac.get(j)) && i!=j) // if the mac address of this line was found in another line
				{
					if(Double.parseDouble(list.get(j)[6])<Double.parseDouble(list.get(i)[6])) // replace the line with the lower signal with lineMark 
					{
						list.set(j, lineMark);
					}
					else list.set(i, lineMark);
				}
			}
		}
		PrintWriter pw = null;
		try {
			pw  = new PrintWriter(new File(path));
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		StringBuilder builder = new StringBuilder();
		String kmlHeader = " <kml xmlns=\"http://www.opengis.net/kml/2.2\">\n    <Document>\n       <name>Wifi Scanner.kml</name> <open>1</open>\n "
				+ "      <Style id=\"red\">\n      <IconStyle>\n        <Icon>\n"
				+ "          <href>http://maps.google.com/mapfiles/ms/icons/red-dot.png</href>\n        </Icon>\n      </IconStyle>\n    </Style>\n<Style id=\"Magnifier\">\n "
				+ "     <IconStyle>\n        <Icon>\n "
				+ "         <href>https://images.vexels.com/media/users/3/132064/isolated/preview/27a9fb54f687667ecfab8f20afa58bbb-search-businessman-circle-icon-by-vexels.png</href>\n"
				+ "        </Icon>\n      </IconStyle>\n    </Style><Style id=\"exampleStyleDocument\">           <LabelStyle>\n           <color>ff0000cc</color>\n           </LabelStyle>\n"
				+ "         </Style>\n\n       <Style id=\"transBluePoly\">\n      <LineStyle>\n        <width>1.5</width>\n      </LineStyle>\n      <PolyStyle>\n        <color>7dff0000</color>\n "
				+ "     </PolyStyle>\n    </Style> <Folder><name>Wifi Networks</name>";

		builder.append(kmlHeader);

		String polygonHeader = "<name>Untitled Polygon.kml</name>\n" + "	<Style id=\"sh_ylw-pushpin\">\n" + "		<IconStyle>\n" + "			<scale>1.1</scale>\n" + "			<Icon>\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>\n" + "			</Icon>\n" + "			<hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\n"
				+ "		</IconStyle>\n" + "		<LineStyle>\n" + 
				"			<color>ff000000</color>\n" + "			<width>3</width>\n" + "		</LineStyle>\n" + "		<PolyStyle>\n" + "			<color>66ffff55</color>\n" + 
				"		</PolyStyle>\n" + "	</Style>\n" + "	<Style id=\"sn_ylw-pushpin\">\n" + "		<IconStyle>\n" + "			<scale>1.3</scale>\n" + "			<Icon>\n" + 
				"				<href>http://maps.google.com/mapfiles/kml/pushpin/ylw-pushpin.png</href>\n" + "			</Icon>\n" + "			<hotSpot x=\"20\" y=\"2\" xunits=\"pixels\" yunits=\"pixels\"/>\n"
				+ "		</IconStyle>\n" + "		<LineStyle>\n" + 
				"			<color>ff000000</color>\n" + "			<width>3</width>\n" + "		</LineStyle>\n" + "		<PolyStyle>\n" + "			<color>66ffff55</color>\n" + "		</PolyStyle>\n" + 
				"	</Style>\n" + "	<StyleMap id=\"msn_ylw-pushpin\">\n" + "		<Pair>\n" + "			<key>normal</key>\n" + "			<styleUrl>#sn_ylw-pushpin</styleUrl>\n" + 
				"		</Pair>\n" + 				"		<Pair>\n" + "			<key>highlight</key>\n" + "			<styleUrl>#sh_ylw-pushpin</styleUrl>\n" +"		</Pair>\n" + 	"	</StyleMap>";

		// add the filtering area to the kml file
		builder.append(polygonHeader);
		String polygon = addFilteringArea(rectTop, rectBot, path); 
		builder.append(polygon);									

		// writing the kml file
		for(int i = 0; i < list.size(); i++)
		{
			if(list.get(i)!=lineMark) // if the line was not marked add its data to the kml
			{
				String desc = "Wifi SSID: "+list.get(i)[8]+"\nMac: "+list.get(i)[7]+"\nSignal strength: "+list.get(i)[6]
						+"\nTime: "+list.get(i)[0]+"\nChannel: "+list.get(i)[9]
								+"\nNumber of additional wifi connections: "+list.get(i)[5];
				builder.append(kmlPlacemarkGenerator(list.get(i)[3], list.get(i)[2], list.get(i)[8], desc));
			}
			if(i+1==list.size()){
				builder.append("</Folder>\n</Document>\n</kml>");
				pw.write(builder.toString());
				pw.close();
			}
		}
	}
	
	private static String addFilteringArea(double[] rectTop, double[] rectBot, String kml){//adds the rectangle of the filtering area
		//rectTop = {xTopLeft, yTopLeft, xTopRight, yTopRight}
		//rectBot = {xBottomLeft, yBottomLeft, xBottomRight, yBottomRight}
		//<altitudeMode>relativeToGround</altitudeMode>
		kml+="<Placemark>\n      <name>Filtered Area</name>\n      <styleUrl>#msn_ylw-pushpin</styleUrl>\n "
				+ "     <Polygon>\n        <extrude>1</extrude>\n          		<tessellate>1</tessellate>\n"
				+ "				\n 				<outerBoundaryIs>\n"
				+ "					<LinearRing>\n           "
				+ " <coordinates>\n              " 
				+rectTop[0]+","+rectTop[1]+",50\n              "
				+rectTop[2]+","+rectTop[3]+",50\n              "
				+rectBot[2]+","+rectBot[3]+",50\n              "
				+rectBot[0]+","+rectBot[1]+",50\n              "
				+rectTop[0]+","+rectTop[1]+",50\n              "
				+"</coordinates>\n       "
				+ "   </LinearRing>\n        </outerBoundaryIs>\n      </Polygon>\n    </Placemark>";
		return kml;
	}

	private static String kmlPlacemarkGenerator(String lon,String lat,String pointName, String desc){// adds a placemark (with description)
		if(pointName.indexOf('&')>=0)
		{
			pointName = pointName.replaceAll("&", "&amp;");
		}
		if(desc.indexOf('&')>=0)
		{
			desc = desc.replaceAll("&", "&amp;");
		}

		String all = "<Placemark>\n           <name>"+pointName+"</name>\n           <description>"+ desc+"</description>\n "
				+ "          <styleUrl>#red</styleUrl>\n           <Point>\n                   "
				+ "         <coordinates>";
		all+= lon+","+lat+"</coordinates>\n           </Point>\n       </Placemark>\n\n		";


		return all;

	}

}


