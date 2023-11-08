package hooks;

import java.util.concurrent.TimeUnit;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Hooks {

	public static WebDriver driver;


	@Before
	public void browserSetup() {
		System.out.println("  I am inside browserSetup");
		System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");
		driver = new ChromeDriver();

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
