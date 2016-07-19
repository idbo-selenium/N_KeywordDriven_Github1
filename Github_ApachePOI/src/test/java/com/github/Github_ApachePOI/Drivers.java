package com.github.Github_ApachePOI;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Drivers {
	
	public static WebDriver getDriver(String browser){
		WebDriver driver = null;
		
		if(browser.equals("firefox")){
			driver = new FirefoxDriver();
		}
		else if(browser.equals("chrome")){
			System.setProperty("webdriver.chrome.driver", "C:\\Users\\sravan\\Downloads\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		}
		else{
			System.out.println("HTML unit browser");
			driver = new HtmlUnitDriver();
		}
		return driver;
	}
	
	public void takeScreenShot(WebDriver driver,String filePath,String fileName) throws Exception{
		File screenShot =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenShot,new File(filePath+fileName+ ".png"));
	}
}