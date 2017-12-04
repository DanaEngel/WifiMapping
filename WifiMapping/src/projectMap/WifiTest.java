package projectMap;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
/**
 * This is the JUNIT test for the Wifi Class
 * @author Enna and Dana 
 */
class WifiTest {
	 Wifi b = new Wifi("01/11/2017 16:58:24", "model=ONEPLUS A3010", "32.10466","35.20568","663","Ariel_University", "e0:10:7F:1a:b2:58","11","-85");
	 Wifi a = new Wifi("01/11/2017 16:58:24", "model=ONEPLUS A3010", "32.10466","35.20568","663","Ariel_University", "24:c9:a1:35:a6:98","6","-63");
	 
	@Test
	void compareToTest() {
		int output = a.compareTo(b);
		assertEquals(1, output);
	}


}
