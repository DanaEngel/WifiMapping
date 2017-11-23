package projectMap;
import java.io.File;

/**@author Dana and Enna
 * This class is the main class in which the program enters to.
 */

public class Main {
	public static void main(String[] args) {
//		String readFromFilePath = "C:\\Users\\ennagrigor\\Desktop\\files";
//		String writeToFilePath = "C:\\Users\\ennagrigor\\Desktop\\result.csv";
//		WifiMapping wifiMapping = new WifiMapping();
//		wifiMapping.readFromFolderAndBuildCsv(readFromFilePath, writeToFilePath);
//		System.out.println("Csv file created at path: " + writeToFilePath);
//		System.out.println("*******************************************");
		
		ReadToKml readKml = new ReadToKml(new File("C:\\Users\\ennagrigor\\Desktop\\result.csv"));
		WriteKml writeKml = new WriteKml("C:\\Users\\ennagrigor\\Desktop\\result.kml");
		writeKml.writeToKml(readKml.getFilteredList());
	}
}
