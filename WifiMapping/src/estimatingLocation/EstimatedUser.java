package estimatingLocation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import projectMap.Wifi;

public class EstimatedUser {
	private List<Point> signalData;
	private double power = 2;
	private double norm = 10000;
	private double sigDiff = 0.4;
	private String minDiff = "3";
	private double noSignal = -120;
	private int diffNoSig = 100;
	private String signal;
	private int num;
	private File file;
	private File file2;
	private Point[] mostSimilar;

	public EstimatedUser(File file, File file2) {
		this.file = file;
		this.file2 = file2;
		this.signalData = new ArrayList<Point>();
	}

	public void userLocationBySignal(int num, String signal) {
		this.signal = signal;
		this.num = num;
		SearchBySignal();
		GetSimilarPoints();
	}

	private double Max(double x, double y) {
		if (x > y)
			return x;
		else
			return y;
	}

	private void SearchBySignal() {
		String str;
		String[] network;
		Wifi wifiPoint;
		double diff;
		double weight;
		Point p;

		try {
			FileReader fr = new FileReader(file.getAbsolutePath());
			BufferedReader br = new BufferedReader(fr);

			br.readLine();
			try {
				while ((str = br.readLine()) != null) {
					if (!str.isEmpty()) {
						network = str.split(",");
						int numOfWifi = Integer.parseInt(network[5]);
						for (int i = 9; i < 5 + numOfWifi * 4; i = i + 4) {
							if (Integer.parseInt(network[i]) == noSignal) {
								diff = diffNoSig;
							} else {
								diff = Max(Math.abs(Double.parseDouble(signal) - Double.parseDouble(network[i])),
										Double.parseDouble(minDiff));
							}
							weight = norm / (Math.pow(diff, sigDiff) * Math.pow(Double.parseDouble(signal), power));
							p = new Point(network[0], diff, weight, network[2], network[3], network[4], network[i - 2]);
							signalData.add(p);
						}
					}
				}
			}

			finally {
				br.close();
			}
		} catch (IOException ex) {
			System.out.print("Error reading file\n" + ex);
			System.exit(2);
		}
	}

	private void GetSimilarPoints() {
		mostSimilar = new Point[num];
		Collections.sort(signalData);

		for (int index = 0; index < num; index++) {
			mostSimilar[index] = signalData.get(index);
		}

		EstimatedMac algo1 = new EstimatedMac(file2);
		for (int i = 0; i < mostSimilar.length; i++) {
			algo1.routerLocationByMac(mostSimilar[i].getMac());
		}
	}

//	private void FindPointLocation(Point p) {
//		String str;
//		String network[];
//		try {
//			FileReader fr = new FileReader(file2.getAbsolutePath());
//			BufferedReader br = new BufferedReader(fr);
//
//			try {
//				while ((str = br.readLine()) != null) {
//					if (!str.isEmpty()) {
//						network = str.split(",");
//						if (network[0].equals(p.getTime())) {
//							int numOfWifi = Integer.parseInt(network[5]);
//							for (int i = 7; i < 5 + numOfWifi * 4; i = i + 4) {
//								if (network[i].equals(p.getMac())) {
//									writeToCsv(p);
//									return;
//								}
//							}
//						}
//					}
//				}
//			} finally {
//				br.close();
//			}
//		} catch (IOException ex) {
//			System.out.print("Error reading file\n" + ex);
//			System.exit(2);
//		}
//	}

//	private void writeToCsv(Point p) {
//		try {
//			FileWriter fw = new FileWriter(pathToAlgo1);
//			PrintWriter out = new PrintWriter(fw);
//			String Mac, Lat, Lon, Alt, Time;
//			int numberOfWifi = 1;
//
//			try {
//				out.print(p.getTime() + ',' + '$' + ',' + p.getLat() + ',' + p.getLon() + ',' + p.getAlt() + ','
//						+ numberOfWifi + ',' + '$' + ',' + p.getMac());
//				out.print('\n');
//			} finally {
//				out.close();
//				fw.close();
//			}
//		} catch (IOException ex) {
//			System.out.print("Error writing file\n" + ex);
//		}
//	}
}