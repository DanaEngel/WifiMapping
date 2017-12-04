package projectMap;

import static org.junit.jupiter.api.Assertions.*;
import java.io.File;
import org.junit.jupiter.api.Test;
/**
 * This is the JUNIT test for the WifiMapping Class.
 * 
 * @author Dana and Enna
 */
class WifiMappingTest {
	String x = "csv"; 
	File a = new File("C:\\Users\\ennagrigor\\Desktop\\files\\ScanNum1.csv");

	@Test
	public void getFileExtensionTest() {
		WifiMapping test = new WifiMapping();
		
		String output = test.getFileExtension(a);
		assertEquals(x, output);
		assertNotNull(a);
	}
	
}	