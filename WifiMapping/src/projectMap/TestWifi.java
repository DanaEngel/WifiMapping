package projectMap;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Kml;

/**@author Dana and Enna
 * This class is the main class in which the program enters to.
 */

public class TestWifi {
	public static void main(String[] args) {
		String readFromFilePath = "C:\\Users\\ennagrigor\\Desktop\\files";
		String writeToFilePath = "C:\\Users\\ennagrigor\\Desktop\\result.csv";
		WifiMapping wifiMapping = new WifiMapping();
		wifiMapping.readFromFolderAndBuildCsv(readFromFilePath, writeToFilePath);
		System.out.println("Csv file created at path: " + writeToFilePath);
		
		Document cod;
		Kml kml;
	}
}
