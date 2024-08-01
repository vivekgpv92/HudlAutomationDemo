package SmokeTest;

import java.lang.reflect.Method;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.*;

import com.hudl.framework.CommonFunctions;
import com.hudl.framework.Driver;
import com.hudl.framework.Reporter;

public class TestNgBaseClass {

	protected WebDriver Driver;
	protected Reporter reporter;
	protected Driver _driver;
	protected String browser;
	protected String Hudl_HomePage_Url;
	protected CommonFunctions commonFunctions;
	
	@Parameters({"browser", "url"})
	@BeforeClass
	public void beforeClass(String browser, String url) {
		this.browser = browser;
		this.Hudl_HomePage_Url = url;
		reporter = Reporter.getReporterInstance();
		reporter.setSystemInfoInReport("Browser", browser);
		_driver = new Driver();
	}
	
	@BeforeMethod
	public void beforeMethod(Method method) {		
		reporter.createTest(method.getName());
		Driver = _driver.fCommonGetWebDriver(browser);
		Driver.manage().window().maximize();
		commonFunctions = new CommonFunctions(Driver, reporter);
	}
		
	@AfterMethod()
	public void afterMethod(ITestResult result) {
		
		reporter.attachScreenshot(commonFunctions.fCommonCaptureScreenshot(result.getMethod().getMethodName()));
		
		Driver.quit();		
	}
	
	@AfterClass
	public void afterClass() {
		reporter.flush();
	}
}
