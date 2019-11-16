package utilities;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class Log {
	public static Logger getLogData(String className)
	{
		DOMConfigurator.configure("log4j.properties");
		return Logger.getLogger(className);
		
	}

}
