package com.hudl.framework;


import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.Status;

public class CommonFunctions {

	private WebDriver driver;
	private Reporter reporter;
	
	public CommonFunctions(WebDriver _driver, Reporter _reporter) {
		driver = _driver;
		reporter = _reporter;
	}
	
	public void fCommonLaunchUrl(String URL) {
		try {
		driver.get(URL);
		reporter.getTest().log(Status.PASS, "fCommonLaunchUrl----Successfully launched URL:"+URL);
		}
		catch (Exception e) {
			reporter.getTest().log(Status.FAIL, "fCommonLaunchUrl----Error in launching URL:"+URL);
		}
	}
	
	public WebElement fCommonGetWebElement(String Locator) {
				
		try {
			String[] Identifier = Locator.split(":");
			String LocatorType = Identifier[0];
			String LocatorPath = Identifier[1];
			WebElement element = null;
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
					
			if(LocatorType.equalsIgnoreCase("xpath")) {
				 element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LocatorPath)));
		    }
			     
			if(LocatorType.equalsIgnoreCase("id")) {
				 element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(LocatorPath)));
			}  
			
			if(LocatorType.equalsIgnoreCase("name")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(LocatorPath)));
			}
			
			if(LocatorType.equalsIgnoreCase("linkText")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText(LocatorPath)));
			}
			
			if(LocatorType.equalsIgnoreCase("cssSelector")) {
				element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(LocatorPath)));
			}
			return element;
		}
		
		catch(NoSuchElementException e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----No such element found in DOM----"+e);
		    return null;
		}
		catch(StaleElementReferenceException  e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----Stale element reference----"+e);
			return null;
		}
		catch(ElementNotInteractableException e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----Element is present but not visible----"+e);
		    return null;
		}
		catch(Exception e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----"+e);
			return null;
		}
		
	}
	
public List<WebElement> fCommonGetWebElements(String Locator) {
				
		try {
			String[] Identifier = Locator.split(":");
			String LocatorType = Identifier[0];
			String LocatorPath = Identifier[1];
			List<WebElement> elements = null;
			
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.pollingEvery(Duration.ofMillis(500));
			
			if(LocatorType.equalsIgnoreCase("xpath")) {
				elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(LocatorPath)));
		    }
			     
			if(LocatorType.equalsIgnoreCase("id")) {
				elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id(LocatorPath)));
			}  
			
			if(LocatorType.equalsIgnoreCase("name")) {
				elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.name(LocatorPath)));
			}
			
			if(LocatorType.equalsIgnoreCase("linkText")) {
				elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.linkText(LocatorPath)));
			}
			
			if(LocatorType.equalsIgnoreCase("cssSelector")) {
				elements = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(LocatorPath)));
			}
			return elements;
		}
		
		catch(NoSuchElementException e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----No such element found in DOM----"+e);
		    return null;
		}
		catch(StaleElementReferenceException  e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----Stale element reference----"+e);
			return null;
		}
		catch(ElementNotInteractableException e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----Element is present but not visible----"+e);
		    return null;
		}
		catch(Exception e) {
			reporter.getTest().log(Status.INFO, "fCommonGetWebEelement----Exception in fetching web element----"+e);
			return null;
		}
		
	}
	
	public boolean fCommonClick(String Locator) {
		
		WebElement element = fCommonGetWebElement(Locator);
		
		if(element!=null) {
			element.click();
			return true;
		}
		else {
			return false;
		}
	}
	
   public boolean fCommonEnterText(String Locator, String Text) {
		
		WebElement element = fCommonGetWebElement(Locator);
		
		if(element!=null) {
			element.sendKeys(Text);
			return true;
		}
		else {
			return false;
		}
	}
   
   public String fCommonCaptureScreenshot(String TestMethodName) {
	   
	   try {
		   LocalDateTime now = LocalDateTime.now();
		   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH-mm-ss");
		   String currentTimestamp = now.format(formatter);
		   
		   File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		   
		   String destinationpath = System.getProperty("user.dir") + File.separator + "Screenshots" + File.separator + TestMethodName + currentTimestamp +".png";
		   
		   Files.copy(srcFile.toPath(), Paths.get(destinationpath));
		   
		   return destinationpath;
	   }
	   catch(IOException e){
		   reporter.getTest().log(Status.INFO, "fCommonCaptureScreenshot----Exception in capuring screenshot and copying to destination path----"+e.getMessage());
		   return null;
	   }
   }
		
}
