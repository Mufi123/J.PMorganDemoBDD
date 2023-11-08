package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class Hooks {

	public static WebDriver driver;


	@Before
	public void browserSetup(Scenario scenario) {
		System.out.println("  I am inside browserSetup");
		boolean headlessMode = scenario.getSourceTagNames().contains("@HeadlessMode");

		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-gpu"); // Disable GPU acceleration
		if (headlessMode) {
			options.addArguments("--headless");
		}
		driver = new ChromeDriver(options);

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}


	@After
	public void teardown(Scenario scenario) {
		try{
			String screenshotName = scenario.getName().replace(" ","_");
			if(scenario.isFailed()){
				scenario.log("Failed step");
				TakesScreenshot ts = (TakesScreenshot) driver;
				byte[] screenshot = ts.getScreenshotAs(OutputType.BYTES);
				scenario.attach(screenshot, "image/png", screenshotName);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		driver.quit();
	}

}
