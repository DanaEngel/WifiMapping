package projectMap;

public class TestWifi {
	public static void main(String[] args) {
		String readFromFilePath = "C:\\Users\\dana\\Desktop\\files";
		String writeToFilePath = "C:\\Users\\dana\\Desktop\\result.csv";
		WifiMapping wifiMapping = new WifiMapping();
		wifiMapping.readFromFolderAndBuildCsv(readFromFilePath, writeToFilePath);
		System.out.println("Csv file created at path: " + writeToFilePath);
	}
}
