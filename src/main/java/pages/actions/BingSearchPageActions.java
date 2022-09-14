package pages.actions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.locators.BingSearchPageLocators;
import stepDefinitions.Hooks;
import utilities.SeleniumDriver;

public class BingSearchPageActions extends CommonPage {
	
	HashMap<String, String> matchedArticlesNews = new HashMap<String, String>();

	
	BingSearchPageLocators BingSearchPageLocators = null;

	public BingSearchPageActions() {
		this.BingSearchPageLocators = new BingSearchPageLocators();
		PageFactory.initElements(SeleniumDriver.getDriver(), BingSearchPageLocators);
	}

	/**
	 * Search for the article retrieved from Guardian in Bing search
	 * 
	 */

	public void searchArticleFromGuardian() {

		BingSearchPageLocators.bingSearchField.sendKeys(articleRetrieved);
		BingSearchPageLocators.bingSearchButton.click();

	}


	/**
	 * 
	 * Click Bing News Tab after searching for a news article
	 * 
	 * @throws InterruptedException
	 */

	public void clickNewsTab() throws InterruptedException {
		
		new WebDriverWait(SeleniumDriver.getDriver(), 30).until(ExpectedConditions.elementToBeClickable(BingSearchPageLocators.newsTab));
		BingSearchPageLocators.newsTab.click();

	}

	/**
	 * Verify news article retrieved from guardian is accurate by comparing it with
	 * all the news articles on Bing news tab from different sources
	 * 
	 * If any article on Bing news tab is from a trusted source and it has a
	 * semantic similarity of greater than or equal to 0.6, we can deduce its from a
	 * trusted source
	 */

	public void verifyNewsArticleIsAccurate() {

		try {
		// Getting news articles from Bing search results from MSN news tab
		
		new WebDriverWait(SeleniumDriver.getDriver(), 30).until(ExpectedConditions.visibilityOfAllElements(BingSearchPageLocators.newsSourceArticle));

		List<WebElement> newsSources = BingSearchPageLocators.newsSource;

		List<WebElement> newsSourceHeadlines = BingSearchPageLocators.newsSourceArticle;
		


		for (int i = 0; i < 5; i++) {

			if (newsSources.get(i).getText().equalsIgnoreCase("The Guardian")) {
				continue;
			}

			// Comparing the article retrieved from Bing with guardian article to find the
			// similarity
			double similarityValue = findSimilarity(articleRetrieved, newsSourceHeadlines.get(i).getText().trim());

			
			// Logging the similarity value and article retrieved from Bing in
			// application.log file (location:./application.log)
			String newsSource=newsSources.get(i).getText().isEmpty()?newsSources.get(i).findElement(By.cssSelector("img.pubimg")).getAttribute("title"):
				newsSources.get(i).getText();
			
			String newsSourceHeadline=newsSourceHeadlines.get(i).getText().trim();
			
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

		}}
		
		catch(IndexOutOfBoundsException e) {
			
			Hooks.scenarioGlobal.log("No Sufficent News Article from Bing search to verify if the news is accurate");
			
		}

	}

}
