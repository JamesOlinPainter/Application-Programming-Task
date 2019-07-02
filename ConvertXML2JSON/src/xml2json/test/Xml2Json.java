package xml2json.test;

import java.io.*;
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

public class Xml2Json {
	
	public static void main(String[] args) throws FileNotFoundException, ParseException{
		
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
		
		URL url;
	    InputStream inStream = null;
	    BufferedReader br;
	    String line, page = "";
	    
	    //	for loop 1
	    for(String key : CompanyAndRssFeedURL.keySet()) {
	    	System.out.println("Company : " + key);
	    	String[] values = CompanyAndRssFeedURL.get(key);
	    	int count = 1;
	    	//	for loop 2
	    	for (String value : values) {
				System.out.println("	RSS feed URL #" + count + " : " + value);
				count++;
				try {
					url = new URL(value);
					inStream = url.openStream();  // throws an IOException
					br = new BufferedReader(new InputStreamReader(inStream));

					while ((line = br.readLine()) != null) {
						page += line;
					}
					String mostRecentUpdate, lbd, sub, pd = null;
					lbd = StringUtils.substringBetween(page, "<lastBuildDate>", "</lastBuildDate>");
					sub = StringUtils.substringBetween(page, "<syn:updateBase>", "</syn:updateBase>");
					pd = StringUtils.substringBetween(page, "<pubDate>", "</pubDate>");
					if (lbd == null) {
						if (sub == null) {
							if (pd == null) {
								mostRecentUpdate = "No Update information";
							} else {
								mostRecentUpdate = pd;
							}
						} else {
							mostRecentUpdate = sub;
						}
					} else {
						mostRecentUpdate = lbd;
					}
					System.out.println("Most recent update to RSS feed : " + mostRecentUpdate);
					Date result;
					if (mostRecentUpdate.charAt(0) == 'N') {
						System.out.println("No Update information");
						Calendar cal = Calendar.getInstance();
						result = cal.getTime();
					} else if (Character.isDigit(mostRecentUpdate.charAt(0))) {
						DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
						result = df.parse(mostRecentUpdate);
					    System.out.println("date:" + result.toString());
					} else {
						DateFormat df = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z");
						result = df.parse(mostRecentUpdate);
						System.out.println("date:" + result.toString());
					}
					
					Calendar now = Calendar.getInstance();
					Calendar start = Calendar.getInstance();
					start.setTime (result);

					long milliseconds1 = start.getTimeInMillis();
					long milliseconds2 = now.getTimeInMillis();
					long diff = milliseconds2 - milliseconds1;
					long diffMinutes = diff / (60 * 1000);
					long diffHours = diff / (60 * 60 * 1000);
					long diffDays = diff / (24 * 60 * 60 * 1000);
					System.out.println("\nThe time since the last RSS feed update:");
					System.out.println("Time in minutes: " + diffMinutes + " minutes.");
					System.out.println("Time in hours: " + diffHours + " hours.");
					System.out.println("Time in days: " + diffDays + " days.");
					
				} catch (MalformedURLException mue) {
					mue.printStackTrace();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
					try {
						if (inStream != null) inStream.close();
					} catch (IOException ioe) {
						// nothing to see here
					}	//	end catch
				}	//	end finally
				page = "";
	    	}	//	end for loop 2
	    }	//	end for loop 1
	    
	    
	    
	    
	}	//	end main
}	//	end class
