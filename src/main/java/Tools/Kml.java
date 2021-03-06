package main.java.Tools;


import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import main.java.WifiData.RowOfWifiPoints;

/**
 * A class to represent, write, and read KML files.
 * took from Ethan Harstad
 * @author  Olga & Dan
 *
 */

public class Kml {

	private static Document doc;
	private static Element root;

	/**
	 * Create a KML object.
	 */
	public static  void Kml() {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			doc = builder.newDocument();
			Element kml = doc.createElementNS("http://www.opengis.net/kml/2.2", "kml");
			doc.appendChild(kml);
			root = doc.createElement("Document");
			kml.appendChild(root);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	/**
	 * Add a placemark to this KML object.
	 * @param mark 
	 */
	public static  void addMarksFromList(ArrayList<RowOfWifiPoints>list) {
		if(list.isEmpty())return;

		for (int i = 0; i < list.size(); i++) {
			if(!list.get(i).wifiList.isEmpty()){
				for (int j = 0; j < list.get(i).wifiList.size(); j++) {

					Element placemark = doc.createElement("Placemark");
					root.appendChild(placemark);

					Element name = doc.createElement("name");
					name.appendChild(doc.createTextNode(list.get(i).wifiList.get(j).getSsid()));
					placemark.appendChild(name);

					//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
					Element desc = doc.createElement("description");
					desc.appendChild(
							doc.createTextNode(
									"SSID: "+list.get(i).wifiList.get(j).getSsid()+"\n"+
											"MAC: "+list.get(i).wifiList.get(j).getMac() +"\n"+
											"Signal: "+list.get(i).wifiList.get(j).getSignal()+"\n"+
											"Time: "+list.get(i).date.toString()+"\n"+
											"Latitude: "+list.get(i).getCoordinates().getLatitude()+ "\n" + 
											"Longitude: "+list.get(i).getCoordinates().getLongitude()+"\n"+
											"Altitude: " + list.get(i).getCoordinates().getAltitude())
							); 
					placemark.appendChild(desc);

					Element point = doc.createElement("Point");
					placemark.appendChild(point);

					if(list.get(i).getCoordinates().getAltitude() > 0) {
						Element altitudeMode = doc.createElement("altitudeMode");
						altitudeMode.appendChild(doc.createTextNode("absolute"));
						point.appendChild(altitudeMode);
					}

					Element coords = doc.createElement("coordinates");
					coords.appendChild(doc.createTextNode(list.get(i).getCoordinates().coordinatesToString()));
					point.appendChild(coords);



					Element TimeStamp = doc.createElement("TimeStamp");
					Element when = doc.createElement("when");
					TimeStamp.appendChild(when);
					String time=list.get(i).date.getDate()+"Z";
					when.appendChild(doc.createTextNode(time));
					TimeStamp.appendChild(when);
					placemark.appendChild(TimeStamp);
				}
			}
		}
	}

	/**
	 * Write this KML object to a file.
	 * @param file
	 * @return boolean true if file written
	 */
	public static  boolean writeFile(File file) {
		try {
			TransformerFactory factory = TransformerFactory.newInstance();
			Transformer transformer = factory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
			DOMSource src = new DOMSource(doc);
			StreamResult out = new StreamResult(file);
			transformer.transform(src, out);
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("Kml saved");
		return true;
	}

	public static String addFilteringArea(double[] rectTop, double[] rectBot, String kml){//adds the rectangle of the filtering area
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


}