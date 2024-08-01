package SmokeTest;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.hudl.home.HudlGenericWebsite;
import com.hudl.home.HudlHome;
import com.hudl.home.Identity;

public class LoginTests extends TestNgBaseClass 
{

	@Test
	(priority = 1,
	description = "This test case verifies the login functionality with valid credentials.",
	dataProvider = "LoginWithValidUserCredentials", 
	dataProviderClass = TestData.LoginTestsData.class)	
	public void LoginHudlValidUserCredentials(String username, String password) {
		
	HudlGenericWebsite hudleGenericWebPage = new HudlGenericWebsite(Driver,reporter,commonFunctions);	
	hudleGenericWebPage.LaunchHudlHomePage(Hudl_HomePage_Url);
	Assert.assertTrue(hudleGenericWebPage.VerifyHudlHomePageIsDisplayed());
	Assert.assertTrue(hudleGenericWebPage.ClickLoginBtn());
	
	Identity IdentityPage = new Identity(Driver,reporter,commonFunctions);
	Assert.assertTrue(IdentityPage.VerifyHudlIdentityPageIsDisplayed());
	Assert.assertTrue(IdentityPage.SubmitEmailAndPassword(username, password));
	
	HudlHome HudlHomePage = new HudlHome(Driver,reporter,commonFunctions);
	HudlHomePage.VerifyHudlHomePageIsDisplayed();
	}
	
	@Test
	(priority = 2,
	description = "This test case verifies the login functionality with Invalid credentials.",
	dataProvider = "LoginWithInValidUserCredentials", 
	dataProviderClass = TestData.LoginTestsData.class)	
	public void LoginHudlInValidUserCredentials(String username, String password) {
		
	HudlGenericWebsite hudleGenericWebPage = new HudlGenericWebsite(Driver,reporter,commonFunctions);	
	hudleGenericWebPage.LaunchHudlHomePage(Hudl_HomePage_Url);
	Assert.assertTrue(hudleGenericWebPage.VerifyHudlHomePageIsDisplayed());
	Assert.assertTrue(hudleGenericWebPage.ClickLoginBtn());
	
	Identity IdentityPage = new Identity(Driver,reporter,commonFunctions);
	Assert.assertTrue(IdentityPage.VerifyHudlIdentityPageIsDisplayed());
	Assert.assertTrue(IdentityPage.SubmitEmailAndPassword(username, password));
	Assert.assertTrue(IdentityPage.VerifyErrorMessageIsDisplayed());    
	}
	
	@Test
	(priority = 3,
	description = "This test case verifies the login functionality with no user credentials is provided"
	 )
	public void LoginHudlWithoutUserCredentials() {
		
	HudlGenericWebsite hudleGenericWebPage = new HudlGenericWebsite(Driver,reporter,commonFunctions);	
	hudleGenericWebPage.LaunchHudlHomePage(Hudl_HomePage_Url);
	Assert.assertTrue(hudleGenericWebPage.VerifyHudlHomePageIsDisplayed());
	Assert.assertTrue(hudleGenericWebPage.ClickLoginBtn());
	
	Identity IdentityPage = new Identity(Driver,reporter,commonFunctions);
	Assert.assertTrue(IdentityPage.VerifyHudlIdentityPageIsDisplayed());
	Assert.assertTrue(IdentityPage.ClickContinueBtn());
	Assert.assertTrue(IdentityPage.VerifyRequiredFieldsMessageIsDisplayed());
	}	
}
