package com.github.Github_ApachePOI;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GitHub_Login {

	public WebDriver driver;
	public int seconds = 4000;
	final static Logger logger = Logger.getLogger(GitHub_Login.class);
	
	public WebElement WaitForElement(By locator,int seconds){
		return (new WebDriverWait(driver, seconds)).until(ExpectedConditions.visibilityOfElementLocated(locator));		
	}
	
	public void Open_Browser(String s,String d) throws Exception{
		//driver = new FirefoxDriver();
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\sravan\\Downloads\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		logger.info("In GitHub_Login class Open_Browser");
	}
	
	public void Navigate_To(String s,String d){
		driver.get("https://github.com/login");
		logger.info("In GitHub_Login class Navigate_To");
	}
	
	public void Wait_For(String s,String d) throws InterruptedException{
		Thread.sleep(4000);
	}
	
	public void Validate_GitHub(String s,String d){
		String gitHub_title = driver.getTitle();
		if(s.equals(gitHub_title)){
			System.out.println("GitHub SignInPage Validated");
		}
		logger.info("In GitHub_Login class Validate_GitHub");
	}
	
	public void Validate_Profile(String s,String d){
		WaitForElement(By.xpath(s), seconds).isDisplayed();
		System.out.println("User Profile Page Validated");
		logger.info("In GitHub_Login class Validate_Profile");
	}
	
	public void Validate_Logout(String s,String d){
		String logoutpage_Title = driver.getTitle();
		if(s.equals(logoutpage_Title)){
			System.out.println("Logout page validation");
		}
		logger.info("In GitHub_Login class Validate_Logout");
	}
	
	public void Enter_Username(String s,String d){
		WaitForElement(By.id(s), seconds).sendKeys(d);
		logger.info("In GitHub_Login class Enter_Username");
	}
	
	public void Enter_Password(String s,String d){
		WaitForElement(By.id(s), seconds).sendKeys(d);
		logger.info("In GitHub_Login class Enter_Password");
	}
	
	public void Click_SignIn(String s,String d){
		WaitForElement(By.xpath(s), seconds).click();
		logger.info("In GitHub_Login class Click_SignIn");
	}
	
	public void Click_Profile(String s,String d){
		WaitForElement(By.xpath(s), seconds).click();
		logger.info("In GitHub_Login class Click_Profile");
	}
	
	public void Click_SignOut(String s,String d){
		WaitForElement(By.xpath(s), seconds).click();
		logger.info("In GitHub_Login class Click_SignOut");
	}
	public void Close_Browser(String s,String d){
		driver.close();
		logger.info("In GitHub_Login class Close_Browser");
	}
}