package projectMap;

/** This class represents all of the relevant information we need to fetch from
 * the files.
 * 
 * @author Enna and Dana
 */
public class WifiPoint implements Comparable<WifiPoint> {
	private String ssid;
	private String mac;
	private String frequency;
	private String signal;
	private String id;
	private String lat;
	private String lon;
	private String alt;
	private String time;

	/**
	 * THis is the constructor of the class.
	 * 
	 * @param ssid
	 * @param mac
	 * @param frequency
	 * @param signal
	 */
	public WifiPoint(String ssid, String mac, String frequency, String signal) {
		this.ssid = ssid;
		this.mac = mac;
		this.frequency = frequency;
		this.signal = signal;
	}

	public String getSsid() {
		return ssid;
	}

	public void setSsid(String ssid) {
		this.ssid = ssid;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getFrequency() {
		return frequency;
	}

	public void setFrequency(String frequency) {
		this.frequency = frequency;
	}

	public String getSignal() {
		return signal;
	}

	public void setSignal(String signal) {
		this.signal = signal;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLon() {
		return lon;
	}

	public void setLon(String lon) {
		this.lon = lon;
	}

	public String getAlt() {
		return alt;
	}

	public void setAlt(String alt) {
		this.alt = alt;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public void setSharedInfo(String lat, String lon, String alt, String time, String id) {
		this.lat = lat;
		this.lon = lon;
		this.alt = alt;
		this.time = time;
		this.id = id;
	}

	/**
	 * This is the the toString that creates a new string with the relevant
	 * information.
	 */
	@Override
	public String toString() {
		String str = ssid + ',' + mac + ',' + frequency + ',' + signal;
		return str;
	}

	/**
	 * This function compares each signal (the strength of wifi) and returns -1,0,1
	 * according to whether it is stronger, the same or weaker.
	 */
	@Override
	public int compareTo(WifiPoint o) {
		int currentSignal = Integer.parseInt(this.signal);
		int otherSignal = Integer.parseInt(o.signal);

		if (currentSignal < otherSignal) {
			return -1;
		} else if (currentSignal == otherSignal) {
			return 0;
		} else {
			return 1;
		}
	}
	
}
