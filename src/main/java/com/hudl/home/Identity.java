package com.hudl.home;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.Status;
import com.hudl.framework.CommonFunctions;
import com.hudl.framework.Reporter;

public class Identity {

	private WebDriver driver;
	private Reporter reporter;
	private CommonFunctions commonFunctions;
	
	public Identity(WebDriver _driver, Reporter _reporter, CommonFunctions _commonFunctions) {
		driver = _driver;
		reporter = _reporter;
		commonFunctions = _commonFunctions;
	}
	
	//Locators
	String Login_Txt = "xpath://h2[text()='Log In']";
	String Email_TxtBox = "id:email";
	String Password_TxtBox = "id:password";
	String Continue_Btn = "xpath://button[@id='logIn']";
	String Error_Txt = "xpath://p[text()=\"We don't recognize that email and/or password\"]";
	String RequiredFields_Txt = "xpath://p[text()='Please fill in all of the required fields']";
			
	
	//Methods
	public boolean VerifyHudlIdentityPageIsDisplayed() {
		if(commonFunctions.fCommonGetWebElement(Login_Txt)==null) 
		{
			reporter.getTest().log(Status.FAIL, "IdentityPage----VerifyHudlIdentityPageIsDisplayed----Identity page is not displayed");
			return false; 
		}
		reporter.getTest().log(Status.PASS, "IdentityPage----VerifyHudlIdentityPageIsDisplayed----Identity page is successfully displayed");
		return true;
	}
	
	public boolean SubmitEmailAndPassword(String email, String password) {
		
		if(commonFunctions.fCommonEnterText(Email_TxtBox, email)==false) 
		{
			reporter.getTest().log(Status.FAIL, "IdentityPage----SubmitEmailAndPassword----Failed to enter email id:"+email);
			return false;
		}
		if(commonFunctions.fCommonEnterText(Password_TxtBox, password)==false) 
		{
			reporter.getTest().log(Status.FAIL, "IdentityPage----SubmitEmailAndPassword----Failed to enter password:"+password);
			return false;
		}
		if(commonFunctions.fCommonClick(Continue_Btn)==false) {
			reporter.getTest().log(Status.FAIL, "IdentityPage----SubmitEmailAndPassword----Failed to click on Continue Btn");
			return false;
		}
		reporter.getTest().log(Status.PASS, "IdentityPage----SubmitEmailAndPassword----Successfully submited email id: "+email+" ----and password: "+password);
		return true;
	}
	
	public boolean ClickContinueBtn() {
		if(commonFunctions.fCommonClick(Continue_Btn)==false) {
			reporter.getTest().log(Status.FAIL, "IdentityPage----ClickContinueBtn----Failed to click on Continue Btn");
			return false;
		}
		reporter.getTest().log(Status.PASS, "IdentityPage----ClickContinueBtn----Successfully clicked on Continue Btn");
		return true;
	}
	
	public boolean VerifyErrorMessageIsDisplayed() {
		WebElement errorTxt = commonFunctions.fCommonGetWebElement(Error_Txt);
		if(errorTxt==null) 
		{
			reporter.getTest().log(Status.FAIL, "IdentityPage----VerifyErrorMessageIsDisplayed----Error message is not displayed for invalid user credentials");
			return false; 
		}
		if(!errorTxt.getText().trim().equals("We don't recognize that email and/or password")) {
			reporter.getTest().log(Status.FAIL, "IdentityPage----VerifyErrorMessageIsDisplayed----Error message is not displayed for invalid user credentials");
			return false; 
		}
		reporter.getTest().log(Status.PASS, "IdentityPage----VerifyErrorMessageIsDisplayed----Error message successfully displayed for invalid user credentials as: We don't recognize that email and/or password");
		return true;
	}
	
	public boolean VerifyRequiredFieldsMessageIsDisplayed() {
		WebElement RequiredFieldTxt = commonFunctions.fCommonGetWebElement(RequiredFields_Txt);
		if(RequiredFieldTxt==null) 
		{
			reporter.getTest().log(Status.FAIL, "IdentityPage----VerifyRequiredFieldsMessageIsDisplayed----Error message to fill required fields is not displayed for blank user credentials");
			return false; 
		}
		
		reporter.getTest().log(Status.PASS, "IdentityPage----VerifyRequiredFieldsMessageIsDisplayed----Required fields message successfully displayed as: "+RequiredFieldTxt.getText().trim());
		return true;
	}
}
