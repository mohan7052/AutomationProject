package commonFunctions;
import java.io.File;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonFunctions{

	/************************************************************************************************************
	 * Function Name : Fn_enterTextUsingObject Description : This Function is useful
	 * to enter text to object by taking different locator objects as input Author :
	 * Mohana Rao Gedela Date of Creation: 26-06-2019
	 * 
	 *************************************************************************************************************/
	
	public void Fn_enterTextUsingObject(WebDriver driver, String loc, String exp, String text) {
		try {
			if (loc.equalsIgnoreCase("xpath")) {
				driver.findElement(By.xpath(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("id")) {
				driver.findElement(By.id(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("cssSelector")) {
				driver.findElement(By.cssSelector(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("name")) {
				driver.findElement(By.name(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("className")) {
				driver.findElement(By.className(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("linkText")) {
				driver.findElement(By.linkText(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("partialLinkText")) {
				driver.findElement(By.partialLinkText(exp)).sendKeys(text);
			} else if (loc.equalsIgnoreCase("tagName")) {
				driver.findElement(By.tagName(exp)).sendKeys(text);
			} else {
				System.out.println("User didn't provide any locator1");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/************************************************************************************************************
	 * Function Name : Fn_Wait Description : This Function is useful to wait until a
	 * object a load Author : Mohana Rao Gedela Date of Creation: 26-06-2019
	 * 
	 * @return
	 * 
	 *************************************************************************************************************/

	public static void Fn_Wait(long l) {
		try {
			Thread.sleep(l);

		} catch (InterruptedException e) {

		}
	}

	/************************************************************************************************************
	 * Function Name : Description : Author : Mohana Rao Gedela Date of Creation:
	 * 29-06-2019
	 * 
	 * @return
	 * 
	 *************************************************************************************************************/
	public static By Fn_locatorValue(WebDriver driver, String locatorType, String Value) throws NoSuchElementException {

		By by;
		switch (locatorType) {
		case "id":
			by = By.id(Value);
			break;
		case "name":
			by = By.name(Value);
			break;
		case "xpath":
			by = By.xpath(Value);
			break;
		case "cssSelector":
		case "css":
			by = By.cssSelector(Value);
			break;
		case "linkText":
			by = By.linkText(Value);
			break;
		case "partialLinkText":
			by = By.partialLinkText(Value);
			break;
		case "tagName":
			by = By.tagName(Value);
			break;
		case "className":
			by = By.className(Value);
		default:
			by = null;
			break;
		}
		try {
			WebElement element = driver.findElement(by);
		} catch (NoSuchElementException e) {
			return null;
		} catch (NullPointerException e1) {

			System.out.println("Element Not Exist Error " + e1);
			return null;
		}
		return by;

	}

	/************************************************************************************************************
	 * Function Name : Fn_PageSync Description : This function used to wait until a
	 * page loads to avoid timeout exception. Author : Mohana Rao Gedela Date of
	 * Creation: 29-06-2019
	 * 
	 * @return
	 * @throws InterruptedException
	 * 
	 *************************************************************************************************************/
	public static void Fn_PageSync(WebDriver driver) throws InterruptedException {
		System.out.println("Page Sync Starts...........");
		CommonFunctions.Fn_Wait(3000);
		JavascriptExecutor execute = (JavascriptExecutor) driver;
		String strBrowserReadyState = (String) execute.executeScript("return document.readyState");
		System.out.println("Browser ready state : "+strBrowserReadyState);
		Integer intCounterWait = 0;
		boolean exitFlag = false;

		while (!strBrowserReadyState.toLowerCase().contentEquals("complete") || exitFlag == true) //loop if browser state is incomplete
		{

			exitFlag = false;
			if (intCounterWait == 100)   //if counter reached 100 the exit loop
			{
				exitFlag = true;
				break;

			}
			if (strBrowserReadyState == "complete") //check browser state and exit 
			{
				exitFlag = true;
				break;
			}
			CommonFunctions.Fn_Wait(2000);
			intCounterWait += 1;
			strBrowserReadyState = (String) execute.executeScript("return document.readystate");
			System.out.println("Browser state after a run : "+strBrowserReadyState);//get browser state
		}
		CommonFunctions.Fn_Wait(3000);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		System.out.println("Page Sync Done..........");

	}

	/************************************************************************************************************
	 * Function Name : Fn_FrameIdentification Description : This function is usefulto identify frame and switch to frame
	 *  Author : Mohana Rao Gedela
	 *   Date of
	 * Creation: 19-07-2019
	 *************************************************************************************************************/
	public static boolean Fn_FrameIdentification(WebDriver driver, WebElement element) {

		final int MAXIMUM_WAIT_TIME = 10;
		boolean blnRetnFlag = false;
		WebDriverWait wait = new WebDriverWait(driver, MAXIMUM_WAIT_TIME);
		int attempts = 0;
		while (attempts < 2) {
			try {
				wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
				blnRetnFlag = true;
				break;
			} catch (Exception ex) {
				System.out.println("Exception while identifying frame is " + ex);
			}

			attempts++;
		}
		return blnRetnFlag;
	}

	/************************************************************************************************************
	 * Function Name : Fn_ClickonObject Description : Author : Mohana Rao Gedela
	 * Date of Creation: 23-07-2019
	 *************************************************************************************************************/
	public static void Fn_ClickOnObject(WebDriver driver, WebElement objElement, String objName, String objType,
			String Action) {
		boolean resultFlag = false; // check element is clicked successfully or not
		try {
			WebElement element = objElement;
			element.isDisplayed();

			if (objType.toLowerCase().contains("radio")) {

				if (element.getAttribute("checked") != null) // will check radio button is clicked or not
				{
					if (element.getAttribute("checked").equals("false"))
					{
						resultFlag = true;
						element.click();
					}
				} 
					else
						{
						resultFlag = true;
						resultFlag = Fn_DependableAction(driver, objElement, "Click"); // if unable to get checked property click directly
					}
			} 
			else if (Action.toLowerCase().contains("doubleClick")) // to double click
			{
				Actions action = new Actions(driver);
				action.moveToElement(element).doubleClick().perform();
				resultFlag = true;

			} 
			else 
			{ // other than radio
				if (objType.toLowerCase().contains("button"))
				{
					resultFlag = Fn_DependableAction(driver, objElement, "Click");

				}
			}
			if (resultFlag)
			{
				System.out.println("Operation is performed successfully");
			} 
			else
			{
				System.out.println("Something went wrong while clicking");
			}
		} 
		catch (Exception ex) {
			System.out.println("Exception occured in Fn_ClickonObject " + ex);
			throw ex;
			}
	}

/************************************************************************************************************
	 * Function Name : Fn_DependableAction Description : Author : Mohana Rao Gedela
	 * Date of Creation: 23-07-2019
*************************************************************************************************************/

	public static boolean Fn_DependableAction(WebDriver driver, WebElement objElement, String Action) {

		boolean resultFlag = false;
		final int MAXIMUM_WAIT_TIME = 10;
		WebDriverWait wait = new WebDriverWait(driver, MAXIMUM_WAIT_TIME);
		int attempts = 0;
		while (attempts < 2) {
			try {
				if (Action.equalsIgnoreCase("Click")) {
					wait.until(ExpectedConditions.elementToBeClickable(objElement)).click();
				}
				resultFlag = true;
				break;
			} catch (StaleElementReferenceException e) {

			}
			attempts++;
		}

		return resultFlag;

	}
/************************************************************************************************************
	 * Function Name : Fn_DependableAction Description : Author : Mohana Rao Gedela
	 * Date of Creation: 23-07-2019
*************************************************************************************************************/
	
	public static void Fn_LoadSync(WebDriver driver) {
		boolean blnExitFlag = false;
		Integer intCounterWait = 0;
		JavascriptExecutor execute =(JavascriptExecutor)driver;
		String strBrowserReadyState = (String) execute.executeScript("return document.complete");
		System.out.println(strBrowserReadyState);
		while(!strBrowserReadyState.toLowerCase().equals("complete") || blnExitFlag == true) 
		{
			blnExitFlag = false;
			if(intCounterWait == 100)
			{
				blnExitFlag = true;
				break;
			}
			if(strBrowserReadyState == "complete")
			{
				blnExitFlag = true;
				break;
			}
			CommonFunctions.Fn_Wait(1500);
			intCounterWait+=1;
			strBrowserReadyState = (String) execute.executeScript("return document.complete");
			System.out.println("Browser Ready State " +strBrowserReadyState+" counter "+intCounterWait+" times" );
			if(strBrowserReadyState.toLowerCase().equals("interactive") && intCounterWait == 10 ) 
			{
				blnExitFlag = true;
				break;
			}
			
		}
	}
	
	/****************************************************************************************************
	 * FunctionName :Fn_FolderCreation
	 * Date of Creation : 01/12/2019
	 * @throws IOException 
	 * 
	 * 
	 ****************************************************************************************************/
	public static void Fn_FolderCreation(String path) throws IOException
	{
		File file = new File(path);
		if(!file.exists())
		{
			if(file.mkdir())
			{
				System.out.println("Folder CReation is completed");
			}
		}else {
			System.out.println("Folder already present in the mentioned path");
		}
		
	}
/************************************************************************************************************
	 * Function Name : Fn_JqueyCalenderSelection Description : Author : Mohana Rao Gedela
	 * Date of Creation: 25-07-2019
*************************************************************************************************************/
	public static void Fn_JqueyCalenderSelection(WebDriver driver, String date, WebElement objCalIcon, WebElement yearObj, WebElement monthObj ,String beforeXpath, String afterXpath) {
		boolean flag = false;
		final int totalWeekdays = 7;
		String[] dateArr = date.split("_");
		String day= dateArr[0];
		String month = dateArr[1];
		String year = dateArr[2];
		CommonFunctions.Fn_DependableAction(driver, objCalIcon, "Click"); //this will click on calender icon
		Select yearSelection = new Select(yearObj);
		yearSelection.selectByVisibleText(year);     //to select year from drop down
		CommonFunctions.Fn_Wait(3000);
		Select monthSelection = new Select(monthObj);
		monthSelection.selectByVisibleText(month);   //to select month from drop down
		CommonFunctions.Fn_Wait(3000);
		//String beforeXpath = "//*[@id=\"ui-datepicker-div\"]/table/tbody/tr[";
		//String afterXpath = "]/td[";
		for(int rowNum=1;rowNum<=6;rowNum++) {
			
			for(int colNum=1;colNum<=totalWeekdays;colNum++) {
				String dates = driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).getText();
				System.out.println(dates);
				if(dates.equals(day))
				{
					driver.findElement(By.xpath(beforeXpath + rowNum + afterXpath + colNum + "]")).click();
					flag = true;
					break;
				}
			}
			if(flag)
				break;
		}
		
	}
	
	public static void Fn_StaticDropDownSelection(WebDriver driver,WebElement objElement,String objName,String SelectMethod) 
	{
		WebElement element = objElement;
		Select dropdownList = new Select(element);
		String getOuterHTML = element.getAttribute("outerHTML");
		System.out.println(getOuterHTML);
		String getDisabled = element.getAttribute("disabled");
		System.out.println(getDisabled);
		String getCurrentValue = element.getText();
		System.out.println(getCurrentValue);
		
	}	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}

