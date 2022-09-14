package pages.actions;

import java.util.ArrayList;
import java.util.List;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import io.cucumber.testng.TestNGCucumberRunner;

public class BaseClassCucumber {

	private static TestNGCucumberRunner testNGCucumberRunner;

	public static List<String> tests = new ArrayList<String>();
	public static String currentClass;

	@Test(groups = "cucumber", description = "Runs Cucumber Scenarios", dataProvider = "scenarios")
	public void scenario(PickleWrapper pickle, FeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runScenario(pickle.getPickle());
	}

	/**
	 * @return returns two dimensional array of {@link CucumberFeatureWrapper}
	 *         objects.
	 */
	@DataProvider
	public Object[][] scenarios() {
		return testNGCucumberRunner.provideScenarios();
	}

	@BeforeClass
	public void test(ITestContext context) {
		currentClass = this.getClass().getSimpleName();

		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());

	}

	@AfterTest
	public void end2() {
		tests.add(currentClass);
		testNGCucumberRunner.finish();
	}
}
