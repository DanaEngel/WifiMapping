package estimatingLocation;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		/*String Path = "C:\\Users\\dana\\Desktop\\Ex2\\data\\BM1\\comb\\_comb4_.csv";
		EstimatedMac estMac = new EstimatedMac(new File(Path));
		estMac.routerLocationByMac("40:65:a3:35:4c:c4");
		*/
		String Path = "C:\\Users\\Dana\\Desktop\\Ex2\\data\\TS1\\_comb_no_gps_ts1.csv";
		String Path2 = "C:\\Users\\Dana\\Desktop\\Ex2\\data\\BM1\\comb\\_comb7_.csv";
		EstimatedUser user = new EstimatedUser(new File(Path) , new File(Path2));
		user.userLocationBySignal(3, "-70");
	}
}
