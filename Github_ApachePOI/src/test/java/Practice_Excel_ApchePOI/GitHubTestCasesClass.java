package Practice_Excel_ApchePOI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GitHubTestCasesClass {

	public WebDriver driver;
	public void Open_Browser(String s,String d){
		System.setProperty("webdriver.chrome.driver", "F:\\Eclipse_Selenium\\Java_Selenium_Maven\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
	}
	
	public void Navigate_To(String s,String d){
		driver.navigate().to("https://github.com/login");
	}
	
	public void Wait_For(String s,String d)throws Exception{
		Thread.sleep(2000);
	}
	public void Enter_Username(String s,String d){
		driver.findElement(By.id(s)).sendKeys(d);
	}
	
	public void Enter_Password(String s,String d){
		driver.findElement(By.id(s)).sendKeys(d);
	}
	
	public void Click_SignIn(String s,String d){
		driver.findElement(By.xpath(s)).click();
	}
	
	public void Click_Profile(String s,String d){
		driver.findElement(By.xpath(s)).click();
	}
	
	public void Click_SignOut(String s,String d){
		driver.findElement(By.xpath(s)).click();
	}
	
	public void Close_Browser(String s,String d){
		driver.close();
	}
}