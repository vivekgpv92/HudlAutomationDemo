package com.hudl.framework;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driver {

	private WebDriver driver;
	
	
	public WebDriver fCommonGetWebDriver(String browserType) {
		
		if(driver!=null) {
			driver.quit();
		}	
		
		try {
			if(browserType.equalsIgnoreCase("Chrome")) {
											
				WebDriverManager.chromedriver().setup();
				driver = new ChromeDriver();
				return driver;
			}
			else
			if(browserType.equalsIgnoreCase("Firefox")) {
				
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();
				return driver;
			}
			else
			{
				throw new RuntimeException("Invalid browser type");				
			}
		}
		catch(RuntimeException e) {
			 System.out.println("Valid browser type is Chrome/Firefox. Failed to launch WebDriver due to Invalid browser type:"+browserType);
			 return null;
		}
		catch(Exception e) {
		    System.out.println("Failed to launch WebDriver due to exception: "+e);
			return null;
		}
	}
	
}
