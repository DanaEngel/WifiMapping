package projectMap;
import java.util.List;
import java.io.File;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.KmlFactory;
import de.micromata.opengis.kml.v_2_2_0.TimeStamp;

/**
 *  This class is responsible for writing the filtered kml file. It takes the
 * filtered arraylist we created in the ReadToKml class and writes it as the
 * user wants.
 * 
 * @author Dana and Enna
 */

public class WriteKml {
	private String pathToWrite;

	/**
	 * This is the constructor of the class
	 * 
	 * @param pathToWrite
	 *            - where to write the file
	 */
	public WriteKml(String pathToWrite) {
		this.pathToWrite = pathToWrite;
	}

	/**
	 * This function is responsible for writing the kml itself, it uses external JAR
	 * libraries and creates new placemakes.
	 * 
	 * @param wifiPointsList
	 *            - the filtered list.
	 */
	public void writeToKml(List<WifiPoint> wifiPointsList) {
		WifiPoint wifiPoint;
		double lat, lon, alt;

		Kml kml = KmlFactory.createKml();
		Document doc = kml.createAndSetDocument().withName("kml_document");

		for (int index = 0; index < wifiPointsList.size(); index++) {
			wifiPoint = wifiPointsList.get(index);
			try {
				lat = Double.parseDouble(wifiPoint.getLat());
				lon = Double.parseDouble(wifiPoint.getLon());
				alt = Integer.parseInt(wifiPoint.getAlt());
			} catch (Exception ex) {
				continue;
			}

			TimeStamp ts = new TimeStamp();
			ts.withWhen(wifiPoint.getTime());
			doc.createAndAddPlacemark().withName(wifiPoint.getSsid()).withVisibility(true).withOpen(true)
					.withDescription("SSID: " + wifiPoint.getSsid() + "<br>" + "Time: " + wifiPoint.getTime() + "<br>"
							+ "Mac: " + wifiPoint.getMac() + "<br>" + "Altitude: " + wifiPoint.getAlt() + "<br>"
							+ "Frequency: " + wifiPoint.getFrequency() + "<br>" + "Signal: " + wifiPoint.getSignal())
					.withStyleUrl("#red").withTimePrimitive(ts).createAndSetPoint().addToCoordinates(lon, lat, alt);
		}
		try {
			if (wifiPointsList.size() > 0) {
				kml.marshal(new File(pathToWrite));
				System.out.println("Kml file created at path " + pathToWrite);
			}
		} catch (Exception e) {
			System.out.println("Error during creating kml file");
		}
	}
}
