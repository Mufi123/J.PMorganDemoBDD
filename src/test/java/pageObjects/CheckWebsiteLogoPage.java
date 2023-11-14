package pageObjects;

import hooks.Hooks;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

// Class responsible for checking various aspects of a website's logo
public class CheckWebsiteLogoPage {
    private final WebDriver driver;
    public Logger logger = LogManager.getLogger(CheckWebsiteLogoPage.class);
    public static class Elements {
        public static final By ACCEPT_COOKIE_BUTTON = By.xpath("//button[@id='W0wltc']");
        public static final By SEARCH_INPUT = By.name("q");
        public static final By SEARCH_RESULTS = By.cssSelector(".g h3");
        public static final By JP_MORGAN_LOGO = By.cssSelector(".primary-navigation-logo-image");
        public static final String URL = "https://google.com";
    }

    // Constructor to initialize the WebDriver
    public CheckWebsiteLogoPage(WebDriver driver) {
        this.driver = driver;
    }

    // Navigates to the Google website
    public void navigateGoogle(){
        driver.navigate().to(Elements.URL);
    }

    // Handles the cookie popup by clicking the 'Accept' button
    public void acceptCookiePopup(){
        try {
            WebElement acceptButton = driver.findElement(Elements.ACCEPT_COOKIE_BUTTON);
            acceptButton.click();
            logger.info("'Accept cookie' popup is handled succesfully ");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("'Cookie Accept' button is not displayed.");
        }
    }

    // Enters the specified text into the Google search bar
    public void enterTextInGoogle(String searchText) throws InterruptedException {
        Thread.sleep(2000);
        Hooks.driver.findElement(Elements.SEARCH_INPUT).sendKeys(searchText);
        Thread.sleep(2000);
    }

    // Simulates pressing the Enter key in the Google search bar
    public void clickEnterInGoogle() throws InterruptedException {
        try {
            Hooks.driver.findElement(Elements.SEARCH_INPUT).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Thread.sleep(2000);
    }

    // Verifies that the search results contain the expected content
    public void verifySearchResult() {
        String pageSource;
        boolean expectedContentFound = false;
        try {
            pageSource = Hooks.driver.getPageSource();
            expectedContentFound = pageSource.contains("J.P. Morgan | Official Website");

        } catch (Exception e) {
            Assert.fail("Expected content 'J.P. Morgan | Official Website' not found in search results.");
        }

        if (expectedContentFound) {
            logger.info("Expected content 'J.P. Morgan | Official Website' found in search results.");
        } else {
            Assert.fail("Expected content 'J.P. Morgan | Official Website' not found in search results.");
        }
    }

    // Verifies that clicking the first search result navigates to J.P. Morgan's official website
    public void verifyNavigateToJpMorganPage() {
        try {
            List<WebElement> elements = Hooks.driver.findElements(Elements.SEARCH_RESULTS);
            if (!elements.isEmpty()) {
                WebElement firstElement = elements.get(0);
                firstElement.click();
                logger.info("'First result' is clicked  successfully ");
            } else {
                Assert.fail("No elements found with the CSS class \".g h3\"");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            Assert.fail("Error while entered into  J.P. Morgan | Official Website");
        }
    }

    // Verifies that the current URL matches the expected URL
    public void verifyTheCurrentUrl() {
        try{
            Hooks.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String currentURL = Hooks.driver.getCurrentUrl();
            Assert.assertEquals("https://www.jpmorgan.com/global", currentURL);
            logger.info("J.P. Morgan | Official Website page is loaded successfully");
        }catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("J.P. Morgan | Official Website page is not loaded successfully");
        }
    }

    // Verifies that the J.P. Morgan logo is displayed on the webpage
    public void verifyTheJpMorganLogo() {
        WebElement logoImage = null;
        try {
            logoImage = Hooks.driver.findElement(Elements.JP_MORGAN_LOGO);
            Hooks.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            if (logoImage.isDisplayed()) {
                System.out.println("Logo image is present on the webpage.");
            } else {
                Assert.fail("Logo image is not present on the webpage.");
            }
        } catch (Exception e) {
            Assert.fail("Logo image is not present on the webpage.");
        }
    }


    // Verifies that the J.P. Morgan logo is not displayed on the webpage
    public void jpMorganLogoIsNotDisplayed() {
        WebElement logoImage = null;
        try {
            logoImage = Hooks.driver.findElement(Elements.JP_MORGAN_LOGO);
            if (logoImage.isDisplayed()) {
                Assert.fail("Logo image is present on the webpage.");
            } else {
                System.out.println("Logo image is not present on the webpage.");
            }
        } catch (Exception e) {
            System.out.println("Logo image is not present on the webpage.");
        }
    }

    // Checks the dimensions and placement of the J.P. Morgan logo on the webpage
    public void checkForLogoDimension() {
        WebElement logoImage = null;

        //  expected placement (X and Y coordinates)
        int expectedX = 295;
        int expectedY = 33;

        //  allowed tolerance for placement (adjust as needed)
        int tolerance = 5;

        // Verify that the logo is within the expected placement
        try {
            logoImage = Hooks.driver.findElement(Elements.JP_MORGAN_LOGO);

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
                Assert.fail("Logo is not placed within the expected coordinates.");
            }
        } catch (Exception e) {
            Assert.fail("Logo is not placed within the expected coordinates.");
        }
    }
}
