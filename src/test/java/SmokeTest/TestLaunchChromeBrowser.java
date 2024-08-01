package SmokeTest;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestLaunchChromeBrowser {

	public static void main(String[] args) {
		
		//String chromeDriverPath = System.getProperty("user.dir") + File.separator + "Drivers" + File.separator + "chromedriver.exe";
		//System.setProperty("webdriver.chrome.driver", chromeDriverPath);
				
		WebDriverManager.chromedriver().setup();		
		
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.hudl.com/");
		
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.quit();
		
	}
	
}
