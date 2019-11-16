package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	public WebDriver driver;
	
	By emailTextBox = By.xpath("//input[@id='user_email']");
	By passwordTextBox = By.xpath("//input[@id='user_password']");
	By signInButton = By.xpath("//input[@value='Log In']");
	
	public LoginPage(WebDriver driver)
	{
		this.driver = driver;
	}
	
	public WebElement getEmail()
	{
		return driver.findElement(emailTextBox);
	}
	public WebElement getPswd()
	{
		return driver.findElement(passwordTextBox);
	}
	public WebElement getSignin()
	{
		return driver.findElement(signInButton);
	}
	
}
