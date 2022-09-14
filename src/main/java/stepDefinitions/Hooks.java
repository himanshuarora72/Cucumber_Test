package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.SeleniumDriver;

public class Hooks {

	public static Scenario scenarioGlobal;

	@Before
	public static void start(Scenario scenario) {
		System.out.println("Inside");
		scenarioGlobal = scenario;
		SeleniumDriver.log.debug("Driver SetUp through Before tag");
		SeleniumDriver.setUpDriver();
		SeleniumDriver.log.debug("Driver Set through Before tag");

	}

	@After
	public static void end(Scenario scenario) {
		WebDriver driver = SeleniumDriver.getDriver();

		if (scenario.isFailed()) {
			scenario.log("url: " + driver.getCurrentUrl());

			if (driver instanceof TakesScreenshot) {
				final byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", "Taking Screen Shot");

			}

		}

		SeleniumDriver.log.debug("tearDown the Driver and browser completely");
		utilities.SeleniumDriver.tearDown();
		System.out.println("Please verify the Extent report in the resultsReport Folder");

	}

}
