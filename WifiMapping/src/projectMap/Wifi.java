package projectMap;
/** This class creates each network as an object with the network's data */
public class Wifi implements Comparable<Wifi> {
	private String Time;
	private String ID;
	private String Lat;
	private String Lon;
	private String Alt;
	private String SSID;
	private String MAC;
	private String Frequency;
	private String Signal;

	public Wifi(String Time, String ID, String Lat, String Lon, String Alt,
			String SSID, String MAC, String Frequency, String Signal) {
		this.Time = Time;
		this.ID = ID;
		this.Lat = Lat;
		this.Lon = Lon;
		this.Alt = Alt;
		this.SSID = SSID;
		this.MAC = MAC;
		this.Frequency = Frequency;
		this.Signal = Signal;
	}

	public String toString() {
		String str = SSID + ',' + MAC + ',' + Frequency + ',' + Signal;
		return str;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	public String getLon() {
		return Lon;
	}

	public void setLon(String lon) {
		Lon = lon;
	}

	public String getAlt() {
		return Alt;
	}

	public void setAlt(String alt) {
		Alt = alt;
	}

	public String getSSID() {
		return SSID;
	}

	public void setSSID(String sSID) {
		SSID = sSID;
	}

	public String getMAC() {
		return MAC;
	}

	public void setMAC(String mAC) {
		MAC = mAC;
	}

	public String getFrequency() {
		return Frequency;
	}

	public void setFrequency(String Frequency) {
		this.Frequency = Frequency;
	}

	public String getSignal() {
		return Signal;
	}

	public void setSignal(String Signal) {
		this.Signal = Signal;
	}
    
	/** This function compares the strength of the networks */
	@Override
	public int compareTo(Wifi o) {
		int currentSignal = Integer.parseInt(this.Signal);
		int otherSignal = Integer.parseInt(o.Signal);

		if (currentSignal < otherSignal) {
			return -1;
		} else if (currentSignal == otherSignal) {
			return 0;
		} else {
			return 1;
		}
	}
}
