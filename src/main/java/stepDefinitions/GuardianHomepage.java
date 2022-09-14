package stepDefinitions;

import pages.actions.*;
import utilities.SeleniumDriver;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class GuardianHomepage {

	GuardianHomePageActions guardianHomePage = new GuardianHomePageActions();

	@Given("^User is on the Guardian Homepage$")
	public void user_is_on_the_Guardian_Site() throws Throwable {
		SeleniumDriver.openPage("guardianURL");
	}

	@When("^Fetch the \"([^\\\"]*)\" from homepage$")
	public void fetchFirstArticle(String articleNumber) throws Throwable {
		guardianHomePage.getArticleHeadline(Integer.parseInt(articleNumber) - 1);
	}

	@And("^Accept Cookies From Guardian$")
	public void acceptCookiesFromGuardina() throws Throwable {
		guardianHomePage.acceptCookiesFromGuardian();
	}

}
