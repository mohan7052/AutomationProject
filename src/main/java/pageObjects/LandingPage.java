package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LandingPage {
	public WebDriver driver;
	
	By nothanksbutton = By.xpath("//button[text()='NO THANKS']");
	By signIn = By.xpath("//span[text()='Login']");
	
	public LandingPage(WebDriver driver)
	{
		this.driver = driver;
	}
	public WebElement getNoThanks()
	{
		return driver.findElement(nothanksbutton);
	}
	public WebElement getLogin()
	{
		return driver.findElement(signIn);
	}
	
	

}
