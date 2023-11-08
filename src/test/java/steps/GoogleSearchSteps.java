package steps;

import java.util.List;
import java.util.concurrent.TimeUnit;

import hooks.Hooks;
import io.cucumber.java.PendingException;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import io.cucumber.java.en.*;

import static org.junit.Assert.fail;

public class GoogleSearchSteps {
	String projectPath = System.getProperty("user.dir");
	@Given("browser window is open")
	public void browser_is_open() {
		System.out.println("User opened the browser successfully");
		System.out.println("Project path is : "+projectPath);
	}

	@And("user is on Google search page")
	public void user_is_on_google_search_page() throws InterruptedException {

		Hooks.driver.navigate().to("https://google.com");
		Thread.sleep(2000);
		try {
			WebElement acceptButton = Hooks.driver.findElement(By.xpath("//button[@id='W0wltc']"));
			acceptButton.click();
			System.out.println("'Accept cookie' popup is handled succesfully ");
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("'Cookie Accept' button is not displayed.");
		}
	}

	@When("user enters a \"J. P. Morgan\" in search box")
	public void user_enters_a_text_in_search_box() throws InterruptedException {
		Thread.sleep(2000);

		Hooks.driver.findElement(By.name("q")).sendKeys("J. P. Morgan");

		Thread.sleep(2000);

		System.out.println("User enters a text in search box successfully");
	}

	@When("user enters irrelevant search text in the search box")
	public void user_enters_irrelevant_text_in_search_box() throws InterruptedException {
		Thread.sleep(2000);

		Hooks.driver.findElement(By.name("q")).sendKeys("thisistheirrelaventtext");

		Thread.sleep(2000);

	}

	@And("hits enter")
	public void hits_enter() throws InterruptedException {

		try {
			Hooks.driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		Thread.sleep(2000);
		System.out.println("User hits enter successfully");
	}

	@Then("user is navigated to the search results")
	public void user_is_navigated_to_search_results() throws PendingException {

		String pageSource;
		boolean expectedContentFound = false;
		try {
			pageSource = Hooks.driver.getPageSource();
			expectedContentFound = pageSource.contains("J.P. Morgan | Official Website");

		} catch (Exception e) {
			fail("Expected content 'J.P. Morgan | Official Website' not found in search results.");
		}

		if (expectedContentFound) {
			System.out.println("Expected content 'J.P. Morgan | Official Website' found in search results.");
		} else {
			fail("Expected content 'J.P. Morgan | Official Website' not found in search results.");
		}
	}

	@And("user clicks on the first result")
	public void user_is_navigated_to_jpmorgan_page() throws PendingException {
		try {
			List<WebElement> elements = Hooks.driver.findElements(By.cssSelector(".g h3"));
			if (!elements.isEmpty()) {
				WebElement firstElement = elements.get(0);
				firstElement.click();
				System.out.println("'First result' is clicked  successfully ");
			} else {
				System.out.println("No elements found with the CSS class \".g h3\"");
			}
		} catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("Error while entered into  J.P. Morgan | Official Website");
		}
	}

	@Then("user verifies that the current URL matches to given URL")
	public void user_verifies_the_current_url(){
		try{
			Hooks.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			String currentURL = Hooks.driver.getCurrentUrl();
			Assert.assertEquals("https://www.jpmorgan.com/global", currentURL);
			System.out.println("J.P. Morgan | Official Website page is loaded successfully");
		}catch (org.openqa.selenium.NoSuchElementException e) {
			System.out.println("J.P. Morgan | Official Website page is not loaded successfully");
		}
	}
	@And("user verifies that the J.P. Morgan logo is displayed")
	public void user_is_verify_to_jpmorgan_logo() throws PendingException {
        WebElement logoImage = null;
        try {
            logoImage = Hooks.driver.findElement(By.cssSelector(".primary-navigation-logo-image"));
			Hooks.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (logoImage.isDisplayed()) {
                System.out.println("Logo image is present on the webpage.");
            } else {
                fail("Logo image is not present on the webpage.");
            }
        } catch (Exception e) {
            fail("Logo image is not present on the webpage.");
        }
	}

	@And("user verifies that the J.P. Morgan logo is not displayed")
	public void user_is_verify_to_jpmorgan_logo_not_displayed() throws PendingException {
		WebElement logoImage = null;
		try {
			logoImage = Hooks.driver.findElement(By.cssSelector(".primary-navigation-logo-image"));
			if (logoImage.isDisplayed()) {
				fail("Logo image is present on the webpage.");
			} else {
				System.out.println("Logo image is not present on the webpage.");
			}
		} catch (Exception e) {
			System.out.println("Logo image is not present on the webpage.");
		}
	}

	@And("user verifies that the J.P. Morgan logo is displayed in the top-left corner")
	public void user_is_verify_logo_dimension_and_size(){
		WebElement logoImage = null;

		//  expected placement (X and Y coordinates)
		int expectedX = 295;
		int expectedY = 33;

		//  allowed tolerance for placement (adjust as needed)
		int tolerance = 5;

		// Verify that the logo is within the expected placement
		try {
			logoImage = Hooks.driver.findElement(By.cssSelector(".primary-navigation-logo-image"));

			// Get the logo's location and size
			org.openqa.selenium.Point logoLocation = logoImage.getLocation();
			org.openqa.selenium.Dimension logoSize = logoImage.getSize();

			// Get the X and Y coordinates of the logo
			int xCoordinate = logoLocation.getX();
			int yCoordinate = logoLocation.getY();

			if ((xCoordinate >= expectedX - tolerance) && (xCoordinate <= expectedX + tolerance) &&
					(yCoordinate >= expectedY - tolerance) && (yCoordinate <= expectedY + tolerance)) {
				System.out.println("Logo is placed within the expected coordinates.");
			} else {
				fail("Logo is not placed within the expected coordinates.");
			}
		} catch (Exception e) {
			fail("Logo is not placed within the expected coordinates.");
		}
	}

}
