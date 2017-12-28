package estimatingLocation;

import projectMap.Wifi;

public class Point implements Comparable<Point>{
	private String time;
	private double diff;
	private double weight;
	private String lat;
	private String lon;
	private String alt;
	private String mac;

public Point(String time, double diff, double weight, String lat, String lon, String alt, String mac) {
	this.time = time;
	this.diff = diff;
	this.weight= weight;
	this.lat = lat;
	this.lon = lon;
	this.alt = alt;
	this.mac = mac;
}

public String getTime() {
	return time;
}

public String getMac() {
	return mac;
}

public String getLat() {
	return lat;
}

public String getLon() {
	return lon;
}

public String getAlt() {
	return alt;
}
@Override
public int compareTo(Point p) {
	double currentDiff = this.diff;
	double otherDiff = p.diff;

	if (currentDiff < otherDiff) {
		return -1;
	} else if (currentDiff == otherDiff) {
		return 0;
	} else {
		return 1;
	}
}

}