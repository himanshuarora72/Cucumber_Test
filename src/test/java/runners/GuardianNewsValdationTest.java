package runners;

import org.testng.annotations.AfterClass;

import io.cucumber.testng.CucumberOptions;
import pages.actions.BaseClassCucumber;

@CucumberOptions(features = "src/test/resources/featureFiles/guardianNewsValidation.feature", glue = "stepDefinitions", tags = "@TestGuardianFromGoogle or @TestGuardianFromBing" , plugin = {
		"json:target/reports/GuardianNewsValdationTest.json",

		"pretty" }, monochrome = true)
public class GuardianNewsValdationTest extends BaseClassCucumber {
	@AfterClass
	public static void teardown() {

	}
}