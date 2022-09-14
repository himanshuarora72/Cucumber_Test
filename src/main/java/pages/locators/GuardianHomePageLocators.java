package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GuardianHomePageLocators {

	@FindBy(how = How.CSS, using = "button[title='Yes, I’m happy']")
	public WebElement acceptCookiesYes;

	@FindBy(how = How.CSS, using = ".fc-item--type-live .fc-item__header a[data-link-name='article']")
	public List<WebElement> liveArticlesHeadline;

}
