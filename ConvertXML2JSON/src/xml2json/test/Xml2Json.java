package xml2json.test;

import java.io.*;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Xml2Json {
	
	public static void main(String[] args) throws FileNotFoundException{
		  
		String xmlString = "";
		xmlString += loadFile("newTest.xml");
		Pattern p_1 = Pattern.compile("<lastBuildDate>(.*?)</lastBuildDate>", Pattern.DOTALL);
		Pattern p_2 = Pattern.compile("<syn:updateBase>(.*?)</syn:updateBase>", Pattern.DOTALL);
		Pattern p_3 = Pattern.compile("<pubDate>(.*?)</pubDate>", Pattern.DOTALL);
		Matcher matcher_1 = p_1.matcher(xmlString);
		Matcher matcher_2 = p_2.matcher(xmlString);
		Matcher matcher_3 = p_3.matcher(xmlString);
		if (matcher_1.find()) {
		    System.out.println(matcher_1.group(1));
		}
		else if(matcher_2.find()) {
		    System.out.println(matcher_2.group(1));
		}
		else if (matcher_3.find()) {
		    System.out.println(matcher_3.group(1));
		}
		//System.out.println(lbd); 
		
	}
	
	public static String loadFile(String filename) throws FileNotFoundException {
		
		File file = new File(filename);
	    Scanner inputFile = new Scanner(file);
	    
		String fileContents = "";

		while (inputFile.hasNext()) {
			fileContents += inputFile.nextLine();
		}
		inputFile.close();
		return fileContents;
	}
}
