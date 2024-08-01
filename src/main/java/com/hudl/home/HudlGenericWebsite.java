package com.hudl.home;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.Status;
import com.hudl.framework.CommonFunctions;
import com.hudl.framework.Reporter;

public class HudlGenericWebsite {

	private WebDriver driver;
	private Reporter reporter;
	private CommonFunctions commonFunctions;
	
	public HudlGenericWebsite(WebDriver _driver, Reporter _reporter, CommonFunctions _commonFunctions) {
		driver = _driver;
		reporter = _reporter;
		commonFunctions = _commonFunctions;
	}
	
	//Locators
	String title_Hudl = "xpath://*[@title='Home']";
	String btn_Login = "xpath://a[contains(text(),'Log in')]";
	String btn_Hudl = "xpath://img[@alt='Hudl logo mark']/../span[text()='Hudl']";
	
	//methods
	
	public void LaunchHudlHomePage(String url) {
		commonFunctions.fCommonLaunchUrl(url);
	}
	
	public boolean VerifyHudlHomePageIsDisplayed() {
		if(commonFunctions.fCommonGetWebElement(title_Hudl)==null) 
		{
			reporter.getTest().log(Status.FAIL, "HudlGenericWebsite----VerifyHudlHomePageIsDisplayed----Hudl homepage is not displayed");
			return false; 
		}
		reporter.getTest().log(Status.PASS, "HudlGenericWebsite----VerifyHudlHomePageIsDisplayed----Hudl homepage is successfully displayed");
		return true;
	}
	
	public boolean ClickLoginBtn() {
		
		if(commonFunctions.fCommonClick(btn_Login)==false) {
			reporter.getTest().log(Status.FAIL, "HudlGenericWebsite----ClickLoginBtn----Failed to click on Login Btn");
			return false;
		}
		if(commonFunctions.fCommonClick(btn_Hudl)==false) {
			reporter.getTest().log(Status.FAIL, "HudlGenericWebsite----ClickLoginBtn----Failed to click on Hudl Img Btn in dropdown");
			return false;
		}
		reporter.getTest().log(Status.PASS, "HudlGenericWebsite----ClickLoginBtn----Successfully clicked on Hudl Login btn on Homepage");
		return true;
	}
}
