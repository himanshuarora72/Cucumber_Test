package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.actions.GoogleSearchPageActions;
import utilities.SeleniumDriver;

public class GoogleSearchPage {

	GoogleSearchPageActions googleSearchPage = new GoogleSearchPageActions();

	@When("^Launch google seach page$")
	public void user_is_on_the_Google_Search() throws Throwable {
		SeleniumDriver.navigateToPage("googleSearchURL");
		googleSearchPage.acceptCookies();

	}

	@And("^Enter article retrieved from guardian in search field$")
	public void searchGoogle() throws Throwable {
		googleSearchPage.searchArticleFromGuardian();
	}

	@Then("^Verify guardian news article is accurate from google search results$")
	public void verifyGoogleNewsIsAccurate() throws Throwable {
		googleSearchPage.verifyNewsArticleIsAccurate();
	}

	@And("^Click google news tab on Search Results page$")
	public void clickGoogleNewsTab() throws Throwable {
		googleSearchPage.clickNewsTab();
	}

}
