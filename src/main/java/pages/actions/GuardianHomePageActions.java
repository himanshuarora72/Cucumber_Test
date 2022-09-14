package pages.actions;

import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;

import pages.locators.GuardianHomePageLocators;
import stepDefinitions.Hooks;
import utilities.SeleniumDriver;

public class GuardianHomePageActions extends CommonPage {

	GuardianHomePageLocators GuardianHomePageLocators = null;

	public GuardianHomePageActions() {
		this.GuardianHomePageLocators = new GuardianHomePageLocators();
		PageFactory.initElements(SeleniumDriver.getDriver(), GuardianHomePageLocators);
	}

	/**
	 * Get the nth article from guardian based on the index passed
	 * 
	 * @param number
	 */

	public void getArticleHeadline(int number) {
		System.out.println("Number"+ number);

		try {
			new WebDriverWait(SeleniumDriver.getDriver(), 60)
					.until(ExpectedConditions.visibilityOfAllElements(GuardianHomePageLocators.liveArticlesHeadline));
			String articleHeadlineText = GuardianHomePageLocators.liveArticlesHeadline.get(number).getText()
					.replace("live", "").replace("latest updates", "").replace("–", "").trim();

			SeleniumDriver.log.debug("Article Number: " + number + " Headline:" + articleHeadlineText);

			articleRetrieved = articleHeadlineText;
		} catch (IndexOutOfBoundsException e) {

			Hooks.scenarioGlobal.log("Currently No Sufficent Live Guardian News Artcile available");
			
			throw new SkipException("tst");

		}
	}

	/**
	 * Accept cookies from guardian
	 */

	public void acceptCookiesFromGuardian() {

		SeleniumDriver.getDriver().switchTo().frame("sp_message_iframe_658013");
		GuardianHomePageLocators.acceptCookiesYes.click();
		SeleniumDriver.getDriver().switchTo().defaultContent();
	}

}
