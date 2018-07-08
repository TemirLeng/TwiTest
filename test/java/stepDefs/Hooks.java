package stepDefs;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import pages.LoginPage;
import utils.BrowserUtils;
import utils.Driver;

public class Hooks {
	
	LoginPage login = new LoginPage();

	/**
	 * WebDriver setup
	 * 
	 * @param scenario
	 */
	@Before
	public void setUp(Scenario scenario) {
		WebDriver driver = Driver.getDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	/**
	 * Take a screenshot in case of failure
	 * Logout and close the browser 
	 *
	 * @param scenario
	 * @throws InterruptedException
	 */
	@After
	public void tearDown(Scenario scenario) throws InterruptedException {
		if (scenario.isFailed()) {
			// taking a screenshot
			final byte[] screenshot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);

			// adding the screenshot to the report
			scenario.embed(screenshot, "image/png");
		}
		
		Thread.sleep(2000);
		login.logoutFunc();
		Driver.closeDriver();
	}

}
