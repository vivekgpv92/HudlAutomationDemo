package com.hudl.fan;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.hudl.framework.CommonFunctions;
import com.hudl.framework.Reporter;

public class FanHome {

	private WebDriver driver;
	private Reporter reporter;
	private CommonFunctions commonFunctions;
	
	public FanHome(WebDriver _driver, Reporter _reporter, CommonFunctions _commonFunctions) {
		driver = _driver;
		reporter = _reporter;
		commonFunctions = _commonFunctions;
		
	}
	
	//Locators
		String LoginInitials="xpath://div[contains(@class,'globalUserItemAvatar')]";
		
		//Methods
		public boolean VerifyHudlFanPageIsDisplayed() {
			if(commonFunctions.fCommonGetWebElement(LoginInitials)==null) 
			{
				reporter.getTest().log(Status.FAIL, "FanHome----VerifyHudlFanPageIsDisplayed----Hudl Fan page is not displayed----Login failed");
				return false; 
			}
			reporter.getTest().log(Status.PASS, "FanHome----VerifyHudlFanPageIsDisplayed----Hudl Fan page is successfully displayed----Login is successful");
			return true;
		}
}
