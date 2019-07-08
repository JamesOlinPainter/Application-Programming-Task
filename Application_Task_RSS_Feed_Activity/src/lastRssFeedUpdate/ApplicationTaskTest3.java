package lastRssFeedUpdate;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;

import org.junit.jupiter.api.Test;

class ApplicationTaskTest3 {

	@Test
	void testGetRssUpdateInfo() throws ParseException {
		String output = ApplicationTask.GetRssUpdateInfo("http://billmaher.hbo.libsynpro.com/rss");
		assertEquals(true, Character.isDigit(output.charAt(0)));
	}

}
