package utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumDriver {
	private static SeleniumDriver seleniumDriver;
	private static WebDriver driver;
	private static FileInputStream fis;

	public static int PAGE_LOAD_TIMEOUT;
	public static WebDriverWait waitDriver;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static Properties Config = new Properties();

	private SeleniumDriver() {
		try {
			fis = new FileInputStream(
					System.getProperty("user.dir") + "/src/test/resources/properties/config.properties");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Config.load(fis);
			log.debug("Config Properties file loaded");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		if (Config.getProperty("browser").equalsIgnoreCase("firefox")) {

			if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/geckodriver-mac-64bit");
			} else if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver-windows-64bit.exe");
			}
			driver = new FirefoxDriver();
			log.debug("Firefox driver called");
		} else if (Config.getProperty("browser").equalsIgnoreCase("chrome")) {
			if (System.getProperty("os.name").toLowerCase().indexOf("mac") >= 0) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "/drivers/chromedriver-mac-64bit");
			} else if (System.getProperty("os.name").toLowerCase().indexOf("win") >= 0) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver-windows-32bit.exe");
			}
			driver = new ChromeDriver();
			log.debug("Chrome driver called");
		}

		driver.manage().window().maximize();
		waitDriver = new WebDriverWait(driver, Integer.parseInt(Config.getProperty("explicit.wait")));
		driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
				TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(Integer.parseInt(Config.getProperty("PAGE_LOAD_TIMEOUT")),
				TimeUnit.SECONDS);
	}

	public static void openPage(String url) {
		log.debug("Entering String url");
		driver.get(Config.getProperty(url));
	}

	public static void navigateToPage(String url) {
		log.debug("Entering String url");
		driver.navigate().to(Config.getProperty(url));
	}

	public static WebDriver getDriver() {
		log.debug("Returning driver");
		return driver;
	}

	public static void setUpDriver() {
		log.debug("SetUp driver");
		if (seleniumDriver == null)
			seleniumDriver = new SeleniumDriver();
		log.debug("Driver is set");

	}

	public static void tearDown() {
		log.debug("TearDown driver");
		if (driver != null) {
			driver.close();
			driver.quit();
		}
		seleniumDriver = null;
		log.debug("Driver Closed");
	}

}
