package FrameworkDemo.FrameworkDemo;

import java.io.IOException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.Test;

import pageObjects.LandingPage;
import pageObjects.LoginPage;
import utilities.Log;


public class HomePage extends Base{

	@Test
	public static void basePageNavigation() throws InterruptedException, IOException
	{
		Logger logger = Logger.getLogger("HomePage");
		PropertyConfigurator.configure("log4j.properties");
		driver= setUpBrowser(driver);
		LandingPage landingPage = new LandingPage(driver);
		landingPage.getNoThanks().click();
		landingPage.getLogin().click();
		
		LoginPage loginPage = new LoginPage(driver);
		CommonFunctions.Fn_Wait(2000);
		loginPage.getEmail().sendKeys("mohanarao7052@gmail.com");
		CommonFunctions.Fn_Wait(2000);
		loginPage.getPswd().sendKeys("Chippi@10");
		CommonFunctions.Fn_Wait(2000);
		loginPage.getSignin().click();
		
		
		
		
		
	}
	
}
