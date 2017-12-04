package projectMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * This is the JUNIT test for the WifiPoint Class
 * @author Enna and Dana 
 */
class WifiPointTest {
	 WifiPoint a = new WifiPoint("Ariel_University", "e0:10:7F:1a:b2:58","11","-85");
	 WifiPoint b = new WifiPoint("Ariel_University", "24:c9:a1:35:a6:98","6","-63");
	 
	@Test
	void compareToTest() {
		int output = a.compareTo(b);
		assertEquals(-1, output);
	}

}
