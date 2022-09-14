package pages.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.locators.GoogleSearchPageLocators;
import stepDefinitions.Hooks;
import utilities.SeleniumDriver;

public class GoogleSearchPageActions extends CommonPage {

	HashMap<String, String> matchedArticlesNews = new HashMap<String, String>();

	GoogleSearchPageLocators GoogleSearchPageLocators = null;

	public GoogleSearchPageActions() {
		this.GoogleSearchPageLocators = new GoogleSearchPageLocators();
		PageFactory.initElements(SeleniumDriver.getDriver(), GoogleSearchPageLocators);
	}

	/**
	 * Search for the article retrieved from Guardian in google search
	 * 
	 */

	public void searchArticleFromGuardian() {

		GoogleSearchPageLocators.googleSearchField.sendKeys(articleRetrieved);
		GoogleSearchPageLocators.googleSearchField.sendKeys(Keys.RETURN);

	}

	/**
	 * Click Accept Cookies from Google Search Page
	 */
	public void acceptCookies() {

		GoogleSearchPageLocators.acceptAllButton.click();

	}

	/**
	 * 
	 * Click Google News Tab after searching for a news article
	 * 
	 * @throws InterruptedException
	 */

	public void clickNewsTab() throws InterruptedException {

		GoogleSearchPageLocators.newsTab.click();

	}

	/**
	 * Verify news article retrieved from guardian is accurate by comparing it with
	 * all the news articles on google news tab from different sources
	 * 
	 * If any article on google news tab is from a trusted source and it has a
	 * semantic similarity of greater than or equal to 0.6, we can deduce its from a
	 * trusted source
	 */

	public void verifyNewsArticleIsAccurate() {

		// Getting news articles from google search results from google news tab

		new WebDriverWait(SeleniumDriver.getDriver(), 30)
				.until(ExpectedConditions.visibilityOfAllElements(GoogleSearchPageLocators.newsSourceArticle));

		List<WebElement> newsSources = GoogleSearchPageLocators.newsSource;

		List<WebElement> newsSourceHeadlines = GoogleSearchPageLocators.newsSourceArticle;

		int countOfNews = newsSources.size();

		for (int i = 0; i < countOfNews; i++) {

			if (newsSources.get(i).getText().equalsIgnoreCase("The Guardian")) {
				continue;
			}

			// Comparing the article retrieved from google with guardian article to find the
			// similarity
			double similarityValue = findSimilarity(articleRetrieved, newsSourceHeadlines.get(i).getText().trim());

			String newsSource = newsSources.get(i).getText().trim();

			String newsSourceHeadline = newsSourceHeadlines.get(i).getText().trim();

			// Logging the similarity value and article retrieved from google in
			// application.log file (location:./application.log)
			SeleniumDriver.log.debug("Similarity Value: " + similarityValue);
			SeleniumDriver.log.debug("News Source: " + newsSource);
			SeleniumDriver.log.debug("News Headline: " + newsSourceHeadline);

			// if similarity is greater then or equal to 0.6 and its from a trusted source
			// then add the values in matchedArticlesNewsArray
			if (similarityValue >= 0.6 && trustedNewsSources.contains(newsSource)) {

				matchedArticlesNews.put(newsSource, newsSourceHeadline);

			}

		}

		Hooks.scenarioGlobal.log("Verifying Guardian News Article: " + articleRetrieved);

		// If Matched Article array has atleast one value then the news is verified as
		// accurate
		if (matchedArticlesNews.size() != 0) {

			Hooks.scenarioGlobal.log("Guardian News Article is Accurate");

			Hooks.scenarioGlobal.log("Sources Verified from are as follows:");

			for (Map.Entry<String, String> set : matchedArticlesNews.entrySet()) {

				Hooks.scenarioGlobal
						.log("Source Verified from : " + set.getKey() + " with Article Headline : " + set.getValue());

			}

		} else {

			Hooks.scenarioGlobal.log("Guardian News Article is not Accurate");

		}

	}

}
