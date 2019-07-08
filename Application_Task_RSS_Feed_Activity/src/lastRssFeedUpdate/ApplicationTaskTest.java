package lastRssFeedUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

class ApplicationTaskTest {

	@Test
	void testGetRssUpdateInfo() throws ParseException {
		String output = ApplicationTask.GetRssUpdateInfo("");
		assertEquals("MalformedURLException - No Time Stamp Data Retrieved!", output);
	}

}
