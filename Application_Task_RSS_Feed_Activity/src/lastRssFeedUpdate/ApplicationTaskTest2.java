package lastRssFeedUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

class ApplicationTaskTest2 {

	@Test
	void testGetRssUpdateInfo() throws ParseException {
		String output = ApplicationTask.GetRssUpdateInfo("https://www.espn.com/espn/rss/action/news");
		assertEquals("java.io.IOException - No Time Stamp Data Retrieved!", output);
	}

}
