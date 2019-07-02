package xml2json.test;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.apache.commons.lang3.StringUtils;

public class Xml2Json {
	
	public static void main(String[] args) throws FileNotFoundException{
		
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
		  
		String xmlString = "";
		xmlString += loadFile("newTest.xml");
		System.out.println("lastBuildDate : " + StringUtils.substringBetween(xmlString, "<lastBuildDate>", "</lastBuildDate>"));
		System.out.println("syn:updateBase : " + StringUtils.substringBetween(xmlString, "<syn:updateBase>", "</syn:updateBase>"));
		System.out.println("pubDate : " + StringUtils.substringBetween(xmlString, "<pubDate>", "</pubDate>"));
		for(String key : CompanyAndRssFeedURL.keySet()) {
			System.out.println("Company : " + key);
			String[] values = CompanyAndRssFeedURL.get(key); 
			int count = 1;
			for (String value : values) {
				System.out.println("	RSS feed URL #" + count + " : " + value);
				count++;
			}
		}
	}
	
	public static String loadFile(String filename) throws FileNotFoundException {
		
		File file = new File(filename);
	    Scanner inputFile = new Scanner(file);
	    
		String fileContents = "";

		while (inputFile.hasNext()) {
			fileContents += inputFile.nextLine().trim();
		}
		inputFile.close();
		return fileContents;
	}
}