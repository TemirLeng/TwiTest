package stepDefs;

import static pages.LoginPage.*;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigurationReader;
import utils.Driver;

public class Twitter_logIn_stepDefs {

	LoginPage loginPage = new LoginPage();
	HomePage homePage = new HomePage();

	@Given("^User navigates to Twitter home page$")
	public void user_navigates_to_Twitter_home_page() {
		Driver.getDriver().get(ConfigurationReader.getProperty("url"));
		homePage.homeLogInBtn.click();
		isAt();
		urlIsAt();
	}

	@When("^User logs in with \"([^\"]*)\" and \"([^\"]*)\"$")
	public void user_logs_in_with_and(String username, String password) {
		loginPage.loginFunc(ConfigurationReader.getProperty(username), ConfigurationReader.getProperty(password));
	}

	@When("^User posts a new \"([^\"]*)\"$")
	public void user_posts_a_new(String tweet) {
		loginPage.newTweet(ConfigurationReader.getProperty(tweet));
	}

	@Then("^a new message should be posted$")
	public void a_new_message_should_be_posted() {
		tweetIsAt();

	}

	@Then("^after all the post should be deleted$")
	public void after_all_the_post_should_be_deleted() {

		try {
			Thread.sleep(3000);
		} catch (Exception e) {
			e.getMessage();
		}
		loginPage.deleteTweet();
	}
}