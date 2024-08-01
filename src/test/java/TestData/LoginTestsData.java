package TestData;
import org.testng.annotations.DataProvider;

public class LoginTestsData {

	@DataProvider(name = "LoginWithValidUserCredentials")
	public Object[][] LoginWithValidUserCredentials() {
        return new Object[][] {
            { "vivekgpv92@gmail.com", "Preeti@2024" }  // Valid user credentials          
        };
	}
	
	@DataProvider(name = "LoginWithInValidUserCredentials")
	public Object[][] LoginWithInValidUserCredentials() {
        return new Object[][] {
            { "vivekgpv92@gmail.com", "test" }, //valid email but invalid password
            { "vivekgpv@gmail.com", "Preeti@2024" }, // invalid email but valid password
            { "vivekgpv92@gmail.com", "***********" }, // password as ****
            { "Preeti@2024", "vivekgpv92@gmail.com" } // username is entered as password and password as username
        };
	}
}
