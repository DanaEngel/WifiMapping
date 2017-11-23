package projectMap;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class ReadToKml {
	public List<WifiPoint> fileWifiPoints;
	private Map<String, List<WifiPoint>> groupOfMac;
	private File csvFile;

	public ReadToKml(File csvFile) {
		this.csvFile = csvFile;
		fileWifiPoints = new ArrayList<WifiPoint>();
		filterByParameter();
	}

	public List<WifiPoint> getFilteredList() {
		return fileWifiPoints;
	}

	private void filterByParameter() {
		if (!csvFile.exists()) {
			return;
		}

		String str;
		String[] network;
		int state;
		Scanner scanner = new Scanner(System.in).useDelimiter("\n");

		System.out.println("Please select what field to filter by: ");
		System.out.println("To filter by time press: 0");
		System.out.println("To filter by id press: 1");
		System.out.println("To filter by place press: 2");

		try {
			 state = Integer.parseInt(scanner.nextLine());
		} catch (Exception e) {
			System.out.println("Not valid input");
			return;
		}
		String[] values = new String[2];

		if (state == 0) {
			System.out.println("Please enter in following format: yyyy-mm-ss hh:mm:ss");
			values[0] = scanner.nextLine();
		} else if (state == 1) {
			System.out.println("Please enter in the Id");
			values[0] = scanner.nextLine();
		} else if (state == 2) {
			System.out.println("Please enter Lat:");
			values[0] = scanner.nextLine();
			System.out.println("Please enter Lon:");
			values[1] = scanner.nextLine();
		} else {
			System.out.println("Not correct usage");
			state = 3;
		}

		scanner.close();

		try {
			FileReader fr = new FileReader(csvFile);
			BufferedReader br = new BufferedReader(fr);

			try {
				br.readLine();
				while ((str = br.readLine()) != null) {
					network = str.split(",");

					switch (state) {
					case 0:
						filterByTime(network, values[0]);
						break;
					case 1:
						filterByID(network, values[0]);
						break;
					case 2:
						filterByPlace(network, values[0], values[1]);
						break;
					default:
						System.out.println("Not a correct usage");
						break;
					}
				}
			} finally {
				br.close();
			}
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	private void filterByTime(String[] network, String value) {
		int numberOfNetworks = Integer.parseInt(network[5]);
		int index = 6;
		WifiPoint wifiPoint;
		List<WifiPoint> points = new ArrayList<WifiPoint>();

		if (!network[0].equals(value)) {
			return;
		}

		for (int i = 0; i < numberOfNetworks; i++) {
			wifiPoint = new WifiPoint(network[index], network[index + 1], network[index + 2], network[index + 3]);
			wifiPoint.setSharedInfo(network[2], network[3], network[4], network[0], network[1]);
			points.add(wifiPoint);
			index = index + 4;
		}

		groupOfMac = points.stream().collect(Collectors.groupingBy(WifiPoint::getMac));

		for (Map.Entry<String, List<WifiPoint>> entryByMac : groupOfMac.entrySet()) {
			List<WifiPoint> wifiList = entryByMac.getValue();

			if (wifiList.size() > 1) {
				Collections.sort(wifiList);
			}
			fileWifiPoints.add(wifiList.get(0));
		}
	}

	private void filterByID(String[] network, String value) {
		int numberOfNetworks = Integer.parseInt(network[5]);
		int index = 6;
		WifiPoint wifiPoint;
		List<WifiPoint> points = new ArrayList<WifiPoint>();

		if (!network[1].equals(value)) {
			return;
		}

		for (int i = 0; i < numberOfNetworks; i++) {
			wifiPoint = new WifiPoint(network[index], network[index + 1], network[index + 2], network[index + 3]);
			wifiPoint.setSharedInfo(network[2], network[3], network[4], network[0], network[1]);
			points.add(wifiPoint);
			index = index + 4;
		}

		groupOfMac = points.stream().collect(Collectors.groupingBy(WifiPoint::getMac));

		for (Map.Entry<String, List<WifiPoint>> entryByMac : groupOfMac.entrySet()) {
			List<WifiPoint> wifiList = entryByMac.getValue();

			if (wifiList.size() > 1) {
				Collections.sort(wifiList);
			}
			fileWifiPoints.add(wifiList.get(0));
		}
	}

	private void filterByPlace(String[] network, String lat, String lon) {
		int numberOfNetworks = Integer.parseInt(network[5]);
		int index = 6;
		WifiPoint wifiPoint;
		List<WifiPoint> points = new ArrayList<WifiPoint>();

		if (!(network[2].equals(lat) && network[3].equals(lon))) {
			return;
		}

		for (int i = 0; i < numberOfNetworks; i++) {
			wifiPoint = new WifiPoint(network[index], network[index + 1], network[index + 2], network[index + 3]);
			wifiPoint.setSharedInfo(network[2], network[3], network[4], network[0], network[1]);
			points.add(wifiPoint);
			index = index + 4;
		}

		groupOfMac = points.stream().collect(Collectors.groupingBy(WifiPoint::getMac));

		for (Map.Entry<String, List<WifiPoint>> entryByMac : groupOfMac.entrySet()) {
			List<WifiPoint> wifiList = entryByMac.getValue();

			if (wifiList.size() > 1) {
				Collections.sort(wifiList);
			}
			fileWifiPoints.add(wifiList.get(0));
		}
	}
}
