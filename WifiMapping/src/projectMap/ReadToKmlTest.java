package projectMap;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

class ReadToKmlTest {
	ArrayList<WifiPoint> a = new ArrayList<WifiPoint>();

	@Test
	void testGetFilteredList() {
		ReadToKml test = new ReadToKml();
		List<WifiPoint> output = test.getFilteredList();
		assertEquals(a ,output);
		}


}
