package testCases;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import commonFunctions.Base;
import commonFunctions.CommonFunctions;
import pageObjects.LandingPage;
import pageObjects.LoginPage;
import utilities.Log;


public class HomePage extends Base{
	
	@Test
	public static void TestCas() throws InterruptedException, IOException
	{
		
			
		
		driver.get("https://www.irctc.com");
		logger.info("URL is opened");
		
		
	
	}

}