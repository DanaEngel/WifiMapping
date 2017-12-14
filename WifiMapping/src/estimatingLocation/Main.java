package estimatingLocation;

import java.io.File;

public class Main {

	public static void main(String[] args) {
		String Path = "C:\\Users\\home\\Desktop\\Ex2\\data\\BM1\\comb\\_comb4_.csv";
		EstimatedMac estMac = new EstimatedMac(new File(Path));
		estMac.routerLocationByMac("40:65:a3:35:4c:c4");

	}

}
