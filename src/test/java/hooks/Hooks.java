package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

public class Hooks {
	public static WebDriver driver;


	/**
	 * This method is executed before each scenario and sets up the WebDriver instance.
	 *
	 * @param scenario The Cucumber scenario to check if it's tagged with @HeadlessMode.
	 */
	@Before("@Chrome")
	public void browserSetup(Scenario scenario) {
			System.out.println("Inside browserSetup");
			boolean headlessMode = scenario.getSourceTagNames().contains("@HeadlessMode");

			// Set the system property to the path of the ChromeDriver executable.
			System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-gpu"); // Disable GPU acceleration
			if (headlessMode) {
				options.addArguments("--headless");
			}

			// Initialize the WebDriver instance with ChromeOptions.
			driver = new ChromeDriver(options);

			// Set timeouts and maximize the browser window.
			driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
	}

	@Before("Mobile")
	public void mobileSetUp(){
			// Initialize Appium for mobile testing
//			public static AppiumDriver<?> appiumDriver;
//			appiumDriver = initializeAppiumDriver();
//			private AppiumDriver<?> initializeAppiumDriver() {
//				DesiredCapabilities capabilities = new DesiredCapabilities();
//				// Set desired capabilities for your mobile app testing
//
//				AppiumDriver<?> driver; // AppiumDriver is a generic type for AndroidDriver or IOSDriver
//
//				if (isAndroid()) {
//					driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
//				} else if (isIOS()) {
//					driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), capabilities);
//				} else {
//					throw new IllegalArgumentException("Invalid mobile platform specified");
//				}
//
//			}
	}

	@Before("@Firefox")
	public void setupFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "src/test/resources/drivers/geckodriver.exe");
		driver = new FirefoxDriver();
	}

	/**
	 * This method is executed after each scenario and handles teardown and failure screenshots.
	 *
	 * @param scenario The Cucumber scenario to capture screenshots.
	 */
	@After
	public void teardown(Scenario scenario) {
		try {
			String screenshotName = scenario.getName().replace(" ", "_");
			if (scenario.isFailed()) {
				scenario.log("Failed step");
				TakesScreenshot ts = (TakesScreenshot) driver;
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", screenshotName);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// Quit the WebDriver instance to release resources.
			driver.quit();
		}
	}
}
