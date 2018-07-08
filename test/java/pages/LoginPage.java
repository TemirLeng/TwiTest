package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utils.BrowserUtils;
import utils.ConfigurationReader;
import utils.Driver;

public class LoginPage {
	static private WebDriver driver;

	public LoginPage() {
		driver = Driver.getDriver();
		PageFactory.initElements(driver, this);

	}

	/**
	 * Locating elements on the webpages using Page Object Model
	 */

	@FindBy(css = "input.js-username-field")
	public WebElement username;

	@FindBy(css = "input.js-password-field")
	public WebElement password;

	@FindBy(xpath = "//button[.='Log in']")
	public WebElement logInButton;

	@FindBy(id = "tweet-box-home-timeline")
	public WebElement postMessage;

	@FindBy(xpath = "//span[.='Reply']//preceding-sibling::span")
	public WebElement tweet;

	@FindBy(css = "span.Icon.Icon--caretDownLight.Icon--small")
	public WebElement deleteButton;

	@FindBy(xpath = "//button[.='Delete Tweet']")
	public WebElement delete;

	@FindBy(xpath = "//div[@class='modal-footer']//button[.='Delete']")
	public WebElement delete2;

	@FindBy(css = "a.Icon")
	public WebElement closePopUpMessage;

	@FindBy(id = "user-dropdown-toggle")
	public WebElement logout;

	@FindBy(xpath = "//button[.='Log out']")
	public WebElement logoutButton;

	/**
	 * Logging in
	 * 
	 * @param username1
	 * @param password1
	 */
	public void loginFunc(String username1, String password1) {
		username.sendKeys(username1);
		password.sendKeys(password1);
		logInButton.click();
	}

	/**
	 * Post a new tweet. Getting a tweet message from properties file for
	 * re-usability purposes Used a Fluent wait for a webelement to be clickable
	 * inside the try-catch block
	 * 
	 * @param tweetM
	 */

	public void newTweet(String tweetM) {
		postMessage.clear();
		postMessage.sendKeys(tweetM);
		tweet.click();
		try {
			BrowserUtils.waitForClickablility(closePopUpMessage, 3);
			closePopUpMessage.click();

		} catch (Exception e) {
			System.out.println("Pop-up message is handled");
		}

	}

	/**
	 * Delete a tweet
	 * 
	 */

	public void deleteTweet() {
		deleteButton.click();
		delete.click();
		delete2.click();
	}

	/**
	 * Verification static methods Title, url, tweet
	 * 
	 * @return
	 */
	public static boolean isAt() {
		return Driver.getDriver().getTitle().equals(ConfigurationReader.getProperty("loginPageTitle"));

	}

	public static boolean urlIsAt() {
		return Driver.getDriver().getCurrentUrl().equals(ConfigurationReader.getProperty("url"));
	}

	public static boolean tweetIsAt() {
		String message = driver.findElement(By.xpath("//p[.='" + ConfigurationReader.getProperty("tweet") + "']"))
				.getText();
		return message.equals(ConfigurationReader.getProperty("tweet"));

	}

	/**
	 * Logout functionality
	 * 
	 */
	public void logoutFunc() {
		logout.click();
		logoutButton.click();
	}
}
