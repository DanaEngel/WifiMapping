package projectMap;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Enna and Dana This class contains the read from csv (not formated)
 *         and write to a new grouped and formated csv file. it uses a Map - a
 *         data structure that help to group the relevant data together.
 */

public class WifiMapping {
	private List<Wifi> fileWifiPoints;
	private Map<String, Map<String, Map<String, Map<String, List<Wifi>>>>> wifiGroups;

	public WifiMapping() {
		fileWifiPoints = new ArrayList<Wifi>();
	}

	/**
	 * This function reads the csv file that is not formated and groups all of the
	 * information in clusters (Map data structure) in which each level is a
	 * different group. This way we have easier access to each cluster.
	 * 
	 * @param folderName
	 *            - csvs not formated
	 * @param csvFilePath
	 *            - where it creates a csv.
	 */
	public void readFromFolderAndBuildCsv(String folderName, String csvFilePath) {
		File directory = new File(folderName);
		File[] filesInDir = directory.listFiles();

		if (filesInDir.length == 0 || filesInDir == null) {

			return;
		}

		for (File file : filesInDir) {
			if (!getFileExtension(file).equals("csv")) {
				continue;
			}

			if (file.exists()) {
				readFromFileAndGroup(file.getAbsolutePath());
			}
		}
		writeToFile(csvFilePath);
	}

	/**
	 * This function gets the file extension of the file we are reading.
	 * 
	 * @param file
	 *            - the file we are reading
	 * @return the name of the file name extension
	 */
	private String getFileExtension(File file) {
		String fileName = file.getName();
		String extension = "";
		if (fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0) {
			extension = fileName.substring(fileName.lastIndexOf(".") + 1);
		}
		return extension;
	}

	/**
	 * This function is a private function that is used in the public function above
	 * in order to read and group all of the data into clusters.
	 * 
	 * @param fileName
	 */
	private void readFromFileAndGroup(String fileName) {
		String str;
		String[] network;
		Wifi wifiPoint;

		try {
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);

			try {
				str = br.readLine();
				network = str.split(",");
				String ID = network[2];
				str = br.readLine();

				while (str != null) {
					str = br.readLine();
					if (str != null) {
						network = str.split(",");
						wifiPoint = new Wifi(network[3], ID, network[6], network[7], network[8], network[1], network[0],
								network[4], network[5]);
						fileWifiPoints.add(wifiPoint);
					}
				}
				wifiGroups = fileWifiPoints.stream()
						.collect(Collectors.groupingBy(Wifi::getTime, Collectors.groupingBy(Wifi::getLat,
								Collectors.groupingBy(Wifi::getLon, Collectors.groupingBy(Wifi::getAlt)))));
			} finally {
				br.close();
			}

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	/**
	 * This private function is used in order to write the new csv file with the
	 * grouped data.
	 * 
	 * @param fileName - where to write the file.
	 */

	private void writeToFile(String fileName) {
		try {
			FileWriter fw = new FileWriter(fileName);
			PrintWriter out = new PrintWriter(fw);
			String ID, Lat, Lon, Alt, Time;
			int numberOfWifi = 0;

			try {
				out.print("Time" + ',' + "ID" + ',' + "Lat" + ',' + "Lon" + ',' + "Alt" + ',' + "#WiFi networks" + ',');
				for (int j = 1; j < 11; j++) {
					out.print("SSID" + j + ',' + "MAC" + j + ',' + "Frequncy" + j + ',' + "Signal" + j + ',');
				}
				out.print("\n");

				for (Map.Entry<String, Map<String, Map<String, Map<String, List<Wifi>>>>> entryTime : wifiGroups
						.entrySet()) {
					Time = entryTime.getKey();
					Map<String, Map<String, Map<String, List<Wifi>>>> wifiLevelTwo = entryTime.getValue();

					for (Map.Entry<String, Map<String, Map<String, List<Wifi>>>> entryLat : wifiLevelTwo.entrySet()) {
						Lat = entryLat.getKey();
						Map<String, Map<String, List<Wifi>>> wifiLevelThree = entryLat.getValue();

						for (Map.Entry<String, Map<String, List<Wifi>>> entryLon : wifiLevelThree.entrySet()) {
							Lon = entryLon.getKey();
							Map<String, List<Wifi>> wifiLevelFour = entryLon.getValue();

							for (Map.Entry<String, List<Wifi>> entryAlt : wifiLevelFour.entrySet()) {
								Alt = entryAlt.getKey();
								List<Wifi> wifiList = entryAlt.getValue();

								Collections.sort(wifiList);

								if (wifiList.size() > 10) {
									wifiList = wifiList.subList(0, 10);
									numberOfWifi = 10;
								} else {
									numberOfWifi = wifiList.size();
								}
								ID = wifiList.get(0).getID();

								out.print(
										Time + ',' + ID + ',' + Lat + ',' + Lon + ',' + Alt + ',' + numberOfWifi + ',');

								for (int index = 0; index < wifiList.size(); index++) {
									Wifi wifiNode = wifiList.get(index);
									out.print(wifiNode.getSSID() + ',' + wifiNode.getMAC() + ','
											+ wifiNode.getFrequency() + ',' + wifiNode.getSignal() + ',');
								}
								out.print("\n");
							}
						}
					}
				}
			} finally {
				out.close();
				fw.close();
			}
		} catch (IOException ex) {
			System.out.print("Error writing file\n" + ex);
		}
	}
}
