package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GoogleSearchPageLocators {

	@FindBy(how = How.CSS, using = "input[type='text']")
	public WebElement googleSearchField;

	@FindBy(how = How.CSS, using = "form[role='search'] .FPdoLc [aria-label='Google Search']")
	public WebElement googleSearchButton;

	@FindBy(how = How.XPATH, using = "//a[text()='News']")
	public WebElement newsTab;

	@FindBy(how = How.XPATH, using = "//div[text()='Accept all']")
	public WebElement acceptAllButton;

	@FindBy(how = How.CSS, using = ".CEMjEf span")
	public List<WebElement> newsSource;

	@FindBy(how = How.CLASS_NAME, using = "mCBkyc")
	public List<WebElement> newsSourceArticle;
}
