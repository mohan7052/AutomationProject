package FrameworkDemo.FrameworkDemo;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import utilities.Log;




public class Base 
{
	
static String baseURL ;
static String browserType ;
static String chromePath ;
public static WebDriver driver;
//public static Logger logger;

	public static WebDriver setUpBrowser(WebDriver driver) throws InterruptedException, IOException {

		browserType = Utility.ReadConfig("browserType");
		baseURL = Utility.ReadConfig("baseURL");
		chromePath = Utility.ReadConfig("chromePath");
		Logger logger = Logger.getLogger("HomePage");
		PropertyConfigurator.configure("log4j.properties");
		if (browserType == null) {
			System.out.println("BroserType is null..." + "Please go to config.properties file and give brosertype");
		}
		else if(browserType.toLowerCase().equals("chrome")) {
			System.setProperty("webdriver.chrome.driver", chromePath);
			driver = new ChromeDriver();
			driver.get(baseURL);
			driver.manage().window().maximize();
			CommonFunctions.Fn_PageSync(driver);
			logger.info("URL Successfully Launched");
		} else {
			System.out.println("Please configure framework with other browser type");
		}
		return driver;
	}  
}
