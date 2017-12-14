package estimatingLocation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import projectMap.Wifi;

public class EstimatedMac {
	private File dataFile;
	private List<Wifi> macData;
	private String Mac;

	public EstimatedMac(File dataFile) {
		this.dataFile = dataFile;
		this.macData = new ArrayList<Wifi>();
	}

	public void routerLocationByMac(String p_Mac) {
		Mac = p_Mac;
		SearchByMac();
	}

	private void SearchByMac() {
		String str;
		String[] network;
		Wifi wifiPoint;
		int counter = 0;
		double[] weight = new double[4];
		double[] wLat = new double[4];
		double[] wLon = new double[4];
		double[] wAlt = new double[4];
		double[] sum = new double[4];
		
		try {
			FileReader fr = new FileReader(dataFile.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);

			try {
				while ((str = br.readLine()) != null) {
					if (!str.isEmpty()) {
						network = str.split(",");
						int numOfWifi = Integer.parseInt(network[5]);
						for (int i = 7; i < 5 + numOfWifi * 4; i = i + 4) {
							if (network[i].equals(Mac)) {
								wifiPoint = new Wifi(network[0], network[1], network[2], network[3], network[4],
										network[5], network[6], network[7], network[9]);
								macData.add(wifiPoint);
							}
						}
					}
				}
				if (macData.size() == 0) {
					System.out.println("MAC not found!");
					return;
				}
				if (macData.size() > 4) {
					Collections.sort(macData);
				}
				
				if (macData.size() <= 4) {
					for (int index = 0; index < macData.size(); index++) {
						Wifi tempPoint = macData.get(index);
						if (tempPoint != null) {
							weight[index] = 1 / Math.pow(Double.parseDouble(tempPoint.getSignal()), 2);
							counter++;
						}
					}
				} else {
					for (int index = 0; index < 4; index++) {
						Wifi tempPoint = macData.get(index);
						if (tempPoint != null) {
							weight[index] = 1 / Math.pow(Double.parseDouble(tempPoint.getSignal()), 2);
							counter++;
						}
					}
				}

				for (int index = 0; index < counter; index++) {
					Wifi tempPoint = macData.get(index);
					wLat[index] = Double.parseDouble(tempPoint.getLat()) * weight[index];
					wLon[index] = Double.parseDouble(tempPoint.getLon()) * weight[index];
					wAlt[index] = Double.parseDouble(tempPoint.getAlt()) * weight[index];
				}

				int k = 0;
				for (int i = 0; i < 4; i++) {
					for (int j = 0; j < counter; j++) {
						if (i == 0) {
							sum[k] += wLat[j];
						} else if (i == 1) {
							sum[k] += wLon[j];
						} else if (i == 2) {
							sum[k] += wAlt[j];
						} else {
							sum[k] += weight[j];
						}
					}
					k++;
				}
				double wSumLat = sum[0] / sum[3];
				double wSumLon = sum[1] / sum[3];
				double wSumAlt = sum[2] / sum[3];

				System.out.println("The estimated location of MAC \"" + Mac + "\" --> " + "Lat:" + wSumLat + " " + "Lon:" + wSumLon + " "
						+ "Alt:" + wSumAlt);

			} finally {
				br.close();
			}

		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}
}
