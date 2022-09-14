package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.actions.BingSearchPageActions;
import utilities.SeleniumDriver;

public class BingSearchPage {

	BingSearchPageActions bingSearchPage = new BingSearchPageActions();

	@When("^Launch bing seach page$")
	public void user_is_on_the_Google_Search() throws Throwable {
		SeleniumDriver.navigateToPage("bingSearchURL");

	}

	@And("^Enter article retrieved from guardian in bing search field$")
	public void searchBing() throws Throwable {
		bingSearchPage.searchArticleFromGuardian();
	}

	@Then("^Verify guardian news article is accurate from bing search results$")
	public void verifyBingNewsIsAccurate() throws Throwable {
		bingSearchPage.verifyNewsArticleIsAccurate();
	}

	@And("^Click bing news tab on Search Results page$")
	public void clickBingNewsTab() throws Throwable {
		bingSearchPage.clickNewsTab();
	}

}
