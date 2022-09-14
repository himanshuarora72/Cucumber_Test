package pages.locators;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class BingSearchPageLocators {
	@FindBy(how = How.ID, using = "sb_form_q")
	public WebElement bingSearchField;

	@FindBy(how = How.ID, using = "search_icon")
	public WebElement bingSearchButton;

	@FindBy(how = How.XPATH, using = "//a[text()='News']")
	public WebElement newsTab;

//	@FindAll({
//        @FindBy(how = How.CSS, using = ".source img.pubimg"),
//        @FindBy(id = ".source img.pubimg")
//})
	@FindBy(how = How.CSS, using = ".source a")
	public List<WebElement> newsSource;

	@FindBy(how = How.CSS, using = ".caption .title")
	public List<WebElement> newsSourceArticle;

}
