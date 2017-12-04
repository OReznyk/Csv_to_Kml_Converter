

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * A class to represent, write, and read KML files.
 * 
 * @author Ethan Harstad
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
	public static  void addMark(MapPoint mark) {
		Element placemark = doc.createElement("Placemark");
		root.appendChild(placemark);
		
		Element name = doc.createElement("name");
		name.appendChild(doc.createTextNode(mark.getName()));
		placemark.appendChild(name);
		
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
		Element desc = doc.createElement("description");
		desc.appendChild(doc.createTextNode(
				"SSID: "+mark.getName()+"\n"+
				"MAC: "+mark.getMac() +"\n"+
				"Signal: "+mark.getSignal()+"\n"+
				"Time: "+mark.getTime()+"\n"+
				"Latitude: "+mark.getLatitude() + "\n" + 
				"Longitude: "+mark.getLongitude()+"\n"+
				"Altitude: " + mark.getAltitude() + " meters\n"
				)); 
		placemark.appendChild(desc);
		
		Element point = doc.createElement("Point");
		placemark.appendChild(point);
		
		if(mark.getAltitude() > 0) {
			Element altitudeMode = doc.createElement("altitudeMode");
			altitudeMode.appendChild(doc.createTextNode("absolute"));
			point.appendChild(altitudeMode);
		}
		
		Element coords = doc.createElement("coordinates");
		coords.appendChild(doc.createTextNode(mark.getLongitude() + ", " + mark.getLatitude() + ", " + mark.getAltitude()));
		point.appendChild(coords);
		
		
		
		Element TimeStamp = doc.createElement("TimeStamp");
		Element when = doc.createElement("when");
		TimeStamp.appendChild(when);
		String time=mark.getTime().replaceAll(" ", "T")+"Z";
		when.appendChild(doc.createTextNode(time));
		TimeStamp.appendChild(when);
		placemark.appendChild(TimeStamp);
	}
//
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
		return true;
	}


}