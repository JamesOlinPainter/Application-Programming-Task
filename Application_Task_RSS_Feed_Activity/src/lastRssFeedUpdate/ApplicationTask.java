package lastRssFeedUpdate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

/**
 * <h1>ApplicationTask</h1>
 * The ApplicationTask class implements an application that given a Dictionary keyed by Company and valued by RSS feed URL, 
 * uses a function "GetRssUpdateInfo()" to determine which companies had no activity for a given number of days.
 *
 * @author	James Olin Painter <JamesOlinPainter@gmail.com>
 * @version	1.0
 * @since	07/07/2019
 * 
 */
public class ApplicationTask {
public static void main(String[] args) throws FileNotFoundException, ParseException {
		
		Map<String, String[]> CompanyAndRssFeedURL = new HashMap<String, String[]>();
		CompanyAndRssFeedURL.put("BBC", new String[] {"http://feeds.bbci.co.uk/news/rss.xml",
													  "http://feeds.bbci.co.uk/news/world/rss.xml",
													  "http://feeds.bbci.co.uk/news/uk/rss.xml",
													  "http://feeds.bbci.co.uk/news/business/rss.xml",
													  "http://feeds.bbci.co.uk/news/politics/rss.xml",
													  "http://feeds.bbci.co.uk/news/health/rss.xml",
													  "http://feeds.bbci.co.uk/news/education/rss.xml",
													  "http://feeds.bbci.co.uk/news/science_and_environment/rss.xml",
													  "http://feeds.bbci.co.uk/news/technology/rss.xml",
													  "http://feeds.bbci.co.uk/news/entertainment_and_arts/rss.xml"});
		CompanyAndRssFeedURL.put("Real Time with Bill Maher", new String[] {"http://billmaher.hbo.libsynpro.com/rss"});
		CompanyAndRssFeedURL.put("Bill Simmons Podcast", new String[] {"https://rss.art19.com/the-bill-simmons-podcast"});
		CompanyAndRssFeedURL.put("Craigslist", new String[] {"https://www.craigslist.org/about/best/all/index.rss",
				  									  "https://sfbay.craigslist.org/apa/index.rss",
				  									  "https://newyork.craigslist.org/res/index.rss",
				  									  "https://losangeles.craigslist.org/tfr/index.rss",
				  									  "https://washingtondc.craigslist.org/gfs/index.rss"});
		CompanyAndRssFeedURL.put("Diane Rehm Show", new String[] {"https://dianerehm.org/rss/npr/dr_podcast.xml"});
		CompanyAndRssFeedURL.put("ESPN", new String[] {"https://www.espn.com/espn/rss/news",
				  									   "https://www.espn.com/espn/rss/nfl/news",
				  									   "https://www.espn.com/espn/rss/nba/news",
				  									   "https://www.espn.com/espn/rss/mlb/news",
				  									   "https://www.espn.com/espn/rss/nhl/news",
				  									   "https://www.espn.com/espn/rss/rpm/news",
				  									   "https://www.espn.com/espn/rss/soccer/news",
				  									   "https://www.espn.com/espn/rss/espnu/news",
				  									   "https://www.espn.com/espn/rss/ncb/news",
				  									   "https://www.espn.com/espn/rss/ncf/news",
				  									   "https://www.espn.com/espn/rss/action/news",
				  									   "https://www.espn.com/espn/rss/poker/master",
				  									   "http://grantland.com/features/feed/",
				  									   "https://www.espn.com/espn/rss/recruiting/osu/news",
				  									   "https://www.espn.com/espn/rss/recruiting/georgia/news",
				  									   "https://www.espn.com/espn/rss/recruiting/oregon/news",
				  									   "https://www.espn.com/espn/rss/recruiting/florida/news",
				  									   "https://www.espn.com/espn/rss/recruiting/tamu/news",
				  									   "https://www.espn.com/espn/rss/recruiting/lsu/news",
				  									   "https://www.espn.com/espn/rss/recruiting/texas/news",
				  									   "https://www.espn.com/espn/rss/recruiting/washington/news",
				  									   "https://www.espn.com/espn/rss/recruiting/psu/news",
				  									   "https://www.espn.com/espn/rss/recruiting/fsu/news",
				  									   "https://www.espn.com/espn/rss/recruiting/oklahoma/news",
				  									   "https://www.espn.com/espn/rss/recruiting/alabama/news",
				  									   "https://www.espn.com/espn/rss/recruiting/usc/news",
				  									   "https://www.espn.com/espn/rss/recruiting/michigan/news",
				  									   "https://www.espn.com/espn/rss/index"});
    
	    for(String company : CompanyAndRssFeedURL.keySet()) {
	    	System.out.println("Company : " + company);
	    	String[] values = CompanyAndRssFeedURL.get(company);
	    	int count = 1;
	    	//	for loop 2
	    	for (String rssFeedUrl : values) {
				System.out.println("	RSS feed URL #" + count + " : " + rssFeedUrl);
				count++;
				String lastUpdateInfo = GetRssUpdateInfo(rssFeedUrl);
				System.out.println("	Time since last update: " + lastUpdateInfo + ".");
	    	}
	    }
	    
	}	//	end main

	/**
	 * <h2>GetRssUpdateInfo</h2>
	 * The function takes a String variable of the URL of an RSS Feed and returns info on the length of time since the last update.
	 * <p>
	 * Using the RSS Feed URL this function scans the page data and tries to find the most recent RSS Feed update information.
	 * Upon finding a time stamp of the most recent update the function calculates how much time has passed since the last update to the current time.
	 * Time passed is shown as "# days, # hours, # minutes". 
	 * If no time stamp update information can be found the function returns "No Update information!"
	 * If an error occurs due to the URL being bad the function returns "MalformedURLException - No Time Stamp Data Retrieved!"
	 * If an I/O error occurs the function returns ""java.io.IOException - No Time Stamp Data Retrieved!""
	 * </p>
	 * @param rssFeedUrl - The RSS Feed URL - String
	 * @return timeSinceLastRssUpdate - The amount of time passed as "# days, # hours, # minutes", or the reason of no data e.g. "No Update information!"
	 * @throws ParseException
	 */
	public static String GetRssUpdateInfo(String rssFeedUrl) throws ParseException {
		String timeSinceLastRssUpdate = "", line = "", page = "";
		URL url;
	    InputStream inStream = null;
	    BufferedReader buffReader;
	    
		try {								//	connect to url source
			url = new URL(rssFeedUrl);
			inStream = url.openStream();	//	throws an IOException
			buffReader = new BufferedReader(new InputStreamReader(inStream));

			while ((line = buffReader.readLine()) != null) {
				page += line;
			}
			String mostRecentUpdate = null, lbd = null, sub = null, pd = null;
			lbd = StringUtils.substringBetween(page, "<lastBuildDate>", "</lastBuildDate>");		//	lbd - <lastBuildDate> tag
			sub = StringUtils.substringBetween(page, "<syn:updateBase>", "</syn:updateBase>");		//	sub - <syn:updateBase> tag
			pd = StringUtils.substringBetween(page, "<pubDate>", "</pubDate>");						//	pd  - <pubDate> tag
			
			//	check to see which variables are not null and prioritize variable preference.
			if (lbd == null) {
				if (sub == null) {
					if (pd == null) {
						mostRecentUpdate = "No Update information!";
					} else {
						mostRecentUpdate = pd;
					}
				} else {
					mostRecentUpdate = sub;
				}
			} else {
				mostRecentUpdate = lbd;
			}
			Date pageTextResult;
			if (mostRecentUpdate.charAt(0) == 'N') {
				timeSinceLastRssUpdate = mostRecentUpdate;
				Calendar cal = Calendar.getInstance();
				pageTextResult = cal.getTime();
			} else if (Character.isDigit(mostRecentUpdate.charAt(0))) {
				DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
				pageTextResult = df.parse(mostRecentUpdate);
			} else {
				DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
				pageTextResult = df.parse(mostRecentUpdate);
			}
			Calendar now = Calendar.getInstance();
			Calendar start = Calendar.getInstance();
			start.setTime (pageTextResult);
			long milliseconds1 = start.getTimeInMillis();
			long milliseconds2 = now.getTimeInMillis();
			long diff = milliseconds2 - milliseconds1;
			long diffMinutes = diff / (60 * 1000);
			long diffHours = diff / (60 * 60 * 1000);
			long diffDays = diff / (24 * 60 * 60 * 1000);
			int hours = 0;
			int minutes = 0;
			if (diffDays > 0) {
				hours = (int) (diffHours - (diffDays * 24)); 
			}
			if (diffMinutes > 0) {
				minutes = (int) (diffMinutes -(diffHours * 60));
			}
			if (timeSinceLastRssUpdate == "") {
				timeSinceLastRssUpdate = diffDays + " days, " + hours + " hours, " + minutes + " minutes";
			}
		} catch (MalformedURLException mue) {
			timeSinceLastRssUpdate = "MalformedURLException - No Time Stamp Data Retrieved!";
		} catch (IOException ioe) {
			timeSinceLastRssUpdate = "java.io.IOException - No Time Stamp Data Retrieved!";
		} finally {
			try {
				if (inStream != null) inStream.close();
			} catch (IOException ioe) {}
		}
		return timeSinceLastRssUpdate;
	}	//	end RssUpdateInfo

}	//	end class - ApplicationTask
