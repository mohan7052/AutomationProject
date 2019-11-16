package FrameworkDemo.FrameworkDemo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;
import org.apache.log4j.Logger;

public class Utility {
	 static Properties prop;
	 static FileReader reader;
	 //static String dir = System.getProperty("user.dir");
	 
	public static String ReadConfig(String value) throws IOException {
		try
		{
			
			reader = new FileReader(new File("./Configuration/config.properties"));
			Properties prop = new Properties();
			prop.load(reader);
			value = prop.getProperty(value);
			
		} 
		catch (FileNotFoundException e) 
		{
			
			e.printStackTrace();
		}
		return value;
	}
	
	
	

}
