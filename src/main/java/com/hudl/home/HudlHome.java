package com.hudl.home;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.hudl.framework.CommonFunctions;
import com.hudl.framework.Reporter;

public class HudlHome {

	private WebDriver driver;
	private Reporter reporter;
	private CommonFunctions commonFunctions;
	
	public HudlHome(WebDriver _driver, Reporter _reporter, CommonFunctions _commonFunctions) {
		driver = _driver;
		reporter = _reporter;
		commonFunctions = _commonFunctions;
		
	}
	
	//Locators
	String LoginInitials="xpath://div[@class='hui-globalusermenu']/..//h5[text()='VV']";
	
	//Methods
	public boolean VerifyHudlHomePageIsDisplayed() {
		if(commonFunctions.fCommonGetWebElement(LoginInitials)==null) 
		{
			reporter.getTest().log(Status.FAIL, "HudlHome----VerifyHudlHomePageIsDisplayed----Home page is not displayed----Login failed");
			return false; 
		}
		reporter.getTest().log(Status.PASS, "HudlHome----VerifyHudlHomePageIsDisplayed----Home page is successfully displayed----Login is successful");
		return true;
	}
}
