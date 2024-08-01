package com.hudl.framework;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reporter {

	private static Reporter reporter;
    private ExtentReports extentReports;
    private ExtentHtmlReporter htmlReporter;
    private ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();
    
    private Reporter() {
        // Initialize the ExtentHtmlReporter    	
    	String report_timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	String reportPath = System.getProperty("user.dir") + File.separator + "Reports" + File.separator + "ExtentReport_" + report_timestamp + ".html";
    	
        htmlReporter = new ExtentHtmlReporter(reportPath);
        htmlReporter.config().setTheme(Theme.STANDARD);
        htmlReporter.config().setDocumentTitle("Hudl Automation Test Report");
        htmlReporter.config().setReportName("Login Smoke Tests");

        // Initialize the ExtentReports
        extentReports = new ExtentReports();
        extentReports.attachReporter(htmlReporter);
        
        String execution_timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        extentReports.setSystemInfo("Execution Start Time", execution_timestamp);
     }
    
     public static synchronized Reporter getReporterInstance() {
            if (reporter == null) {
            	reporter = new Reporter();
            }
            return reporter;
     }
     
     public ExtentReports getExtentReports() {
         return extentReports;
     }
     
     public ExtentTest getTest() {
         return extentTest.get();
     }

     public void createTest(String testName) {
    	 
    	 ExtentTest test = extentReports.createTest(testName);
    	 extentTest.set(test);
     }
     
     public void flush() {
         extentReports.flush();
     }
     
     public void setSystemInfoInReport(String key, String val) {
    	 extentReports.setSystemInfo(key, val);
     }
       
     public void attachScreenshot(String screenShotPath) {
    	 try {
			extentTest.get().addScreenCaptureFromPath(screenShotPath);
		} catch (IOException e) {
			
			extentTest.get().log(Status.WARNING, "Failed to attach screenshot into extent report from path: "+screenShotPath+"----"+e.getMessage());
		}
     }

}

