package commonFunctions;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import commonFunctions.CommonFunctions;
import commonFunctions.Utility;
import utilities.Log;




public class Base 
{
	
static String baseURL ;
static String browserType ;
static String chromePath ;
public static WebDriver driver;
public static Logger logger;


 @Parameters("browser")
 @BeforeClass
 public void Fn_openBrowser(String browserType) throws IOException, InterruptedException
 {
	logger = logger.getLogger("Base");
	PropertyConfigurator.configure("Log4j.properties");
	chromePath = Utility.ReadConfig("chromePath"); 
	if (browserType == null) {
		//System.out.println("BroserType is null..." + "Please go to testng.xml and give browsertype");
		logger.info("BroserType is null...\" + \"Please go to testng.xml file and give browsertype");
	}
	else if(browserType.toLowerCase().equals("chrome"))
	{
		System.setProperty("webdriver.chrome.driver", chromePath);
		driver = new ChromeDriver();
				driver.manage().window().maximize();
		CommonFunctions.Fn_PageSync(driver);
		logger.info("Chrome Browser Opened ");
	}
	else
	{
		//System.out.println("Please configure framework with other browser type");
		logger.info("Please configure framework with other browser type");
	}
 }
 
 /*############################################################################################
  * MethodName	  : tearDown
  * Functionality : This Method useful to close chrome browser after every test case run
  * 
  *#############################################################################################*/
 @AfterClass
 public void tearDown()
 {
	 driver.close();
	 logger.info("Browser closed successfully");
 }


 }
 