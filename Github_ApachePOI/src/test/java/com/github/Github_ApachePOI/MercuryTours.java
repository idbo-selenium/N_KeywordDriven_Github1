package com.github.Github_ApachePOI;

import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class MercuryTours {

	WebDriver driver;
	final static Logger logger = Logger.getLogger(MercuryTours.class);
	public MercuryTours(WebDriver driver){
		this.driver = driver;
	}
	
	public void OpenMercuryTours(){
		logger.info("In MercuryTours class 'OpenMercuryTours'");		
		driver.get("http://newtours.demoaut.com/");
		//driver.manage().window().maximize();
		System.out.println(driver.getTitle());
		logger.info("Out MercuryTours class 'OpenMercuryTours'");
	}
	
	public void login(){
		logger.info("In MercuryTours class 'login'");
		driver.findElement(By.cssSelector("input[name='userName']")).sendKeys("mercury");
		driver.findElement(By.cssSelector("input[name='password']")).sendKeys("mercury");
		driver.findElement(By.cssSelector("input[name='login']")).click();
		//System.out.println(driver.getTitle());
		logger.info("Out MercuryTours class 'login'");		
	}
	
	public void flightFinder(String deptCity,String arrCity){
		logger.info("In MercuryTours class 'flightFinder'");
		driver.findElement(By.xpath("//input[@value='oneway']")).click();
		driver.findElement(By.xpath("//select[@name='passCount']/option[3]")).click();
		WebElement departure = driver.findElement(By.xpath("//select[@name='fromPort']"));
		Select departureSelect = new Select(departure);
		List<WebElement> deptOptions = departureSelect.getOptions();
		for(WebElement option : deptOptions){
			//System.out.println(option.getText());
			if(option.getText().equals(deptCity)){
				departureSelect.selectByValue(deptCity);
				break;
			}
		}
		driver.findElement(By.xpath("//select[@name='fromMonth']/option[4]")).click();
		driver.findElement(By.xpath("//select[@name='fromDay']/option[7]")).click();
		WebElement arrival = driver.findElement(By.xpath("//select[@name='toPort']"));
		Select arrivalSelect = new Select(arrival);
		List<WebElement> arrivalOptions = arrivalSelect.getOptions();
		for(WebElement option : arrivalOptions){
			System.out.println(option.getText());
			if(option.getText().equals(arrCity)){
				arrivalSelect.selectByValue(arrCity);
			}
		}
		driver.findElement(By.xpath("//input[@name='findFlights']")).click();
		logger.info("In MercuryTours class 'flightFinder'");
	}
	
	public void signOff(){
		logger.info("In MercuryTours class 'signOff'");
		driver.findElement(By.linkText("SIGN-OFF")).click();
		driver.close();
		logger.info("Out MercuryTours class 'signOff'");
	}
	
	public static void main(String [] args){
		PropertyConfigurator.configure("log4j.properties");
		logger.info("In MercuryTours class 'Main'");		
		WebDriver driver = Drivers.getDriver("chrome");
		MercuryTours mt = new MercuryTours(driver);
		mt.OpenMercuryTours();
		mt.login();
		mt.flightFinder("London","New York");
		mt.signOff();
		logger.info("Out MercuryTours class 'Main'");
	}	
}