package projectMap;
import java.io.File;

/**
 * @author Dana and Enna
 * This class is the main class in which the program enters to.
 * It creates a CSV file and uses it to create a new KML file by filtered fields.
 */

public class Main {
	public static void main(String[] args) {
		String readFromFilePath = "C:\\Users\\ennagrigor\\Desktop\\files";
		String writeToFilePath = "C:\\Users\\ennagrigor\\Desktop\\result.csv";
		WifiMapping wifiMapping = new WifiMapping();
		System.out.println("Csv file created at path: " + writeToFilePath);
		wifiMapping.readFromFolderAndBuildCsv(readFromFilePath, writeToFilePath);
		System.out.println("*******************************************");
		ReadToKml readKml = new ReadToKml(new File("C:\\Users\\ennagrigor\\Desktop\\result.csv"));
		WriteKml writeKml = new WriteKml("C:\\Users\\ennagrigor\\Desktop\\result.kml");
		writeKml.writeToKml(readKml.getFilteredList());
	}
	
}
