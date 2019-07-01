package xml2json.test;

import java.io.*;
import java.util.Scanner;
import org.json.JSONObject;
import org.json.XML;

public class Xml2Json {

	private static final int INDENT_FACTOR = 3;
	
	public static void main(String[] args) throws FileNotFoundException{
		  
		String xmlString = "";
		//String lbd = "";
		xmlString += loadFile("newTest.xml");
		JSONObject jsonObject = XML.toJSONObject(xmlString);
		//lbd = jsonObject.getJSONObject("channel").toString();
		String jsonPrettyPrintString = jsonObject.toString(INDENT_FACTOR);
		  
		System.out.println(jsonPrettyPrintString);
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
