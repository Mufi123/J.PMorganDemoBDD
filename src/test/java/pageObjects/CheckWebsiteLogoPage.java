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

import static org.junit.Assert.fail;

public class CheckWebsiteLogoPage {
    private final WebDriver driver;
    public Logger logger = LogManager.getLogger(CheckWebsiteLogoPage.class);
    private static final By ACCEPT_COOKIE_BUTTON = By.xpath("//button[@id='W0wltc']");
    private static final By SEARCH_INPUT = By.name("q");
    private static final By SEARCH_RESULTS = By.cssSelector(".g h3");
    private static final By JP_MORGAN_LOGO = By.cssSelector(".primary-navigation-logo-image");

    public CheckWebsiteLogoPage(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateGoogle(){
        driver.navigate().to("https://google.com");
    }

    public void acceptCookiePopup(){
        try {
            WebElement acceptButton = driver.findElement(ACCEPT_COOKIE_BUTTON);
            acceptButton.click();
            logger.info("'Accept cookie' popup is handled succesfully ");
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("'Cookie Accept' button is not displayed.");
        }
    }

    public void enterTextInGoogle(String searchText) throws InterruptedException {
        Thread.sleep(2000);
        Hooks.driver.findElement(SEARCH_INPUT).sendKeys(searchText);
        Thread.sleep(2000);
    }

    public void clickEnterInGoogle() throws InterruptedException {
        try {
            Hooks.driver.findElement(SEARCH_INPUT).sendKeys(Keys.ENTER);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        Thread.sleep(2000);
    }

    public void verifySearchResult() {
        String pageSource;
        boolean expectedContentFound = false;
        try {
            pageSource = Hooks.driver.getPageSource();
            expectedContentFound = pageSource.contains("J.P. Morgan | Official Website");

        } catch (Exception e) {
            fail("Expected content 'J.P. Morgan | Official Website' not found in search results.");
        }

        if (expectedContentFound) {
            logger.info("Expected content 'J.P. Morgan | Official Website' found in search results.");
        } else {
            fail("Expected content 'J.P. Morgan | Official Website' not found in search results.");
        }
    }

    public void verifyNavigateToJpMorganPage() {
        try {
            List<WebElement> elements = Hooks.driver.findElements(SEARCH_RESULTS);
            if (!elements.isEmpty()) {
                WebElement firstElement = elements.get(0);
                firstElement.click();
                logger.info("'First result' is clicked  successfully ");
            } else {
                logger.error("No elements found with the CSS class \".g h3\"");
            }
        } catch (org.openqa.selenium.NoSuchElementException e) {
            logger.error("Error while entered into  J.P. Morgan | Official Website");
        }
    }

    public void verifyTheCurrentUrl() {
        try{
            Hooks.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String currentURL = Hooks.driver.getCurrentUrl();
            Assert.assertEquals("https://www.jpmorgan.com/global", currentURL);
            System.out.println("J.P. Morgan | Official Website page is loaded successfully");
        }catch (org.openqa.selenium.NoSuchElementException e) {
            System.out.println("J.P. Morgan | Official Website page is not loaded successfully");
        }
    }

    public void verifyTheJpMorganLogo() {
        WebElement logoImage = null;
        try {
            logoImage = Hooks.driver.findElement(JP_MORGAN_LOGO);
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

    public void jpMorganLogoIsNotDisplayed() {
        WebElement logoImage = null;
        try {
            logoImage = Hooks.driver.findElement(JP_MORGAN_LOGO);
            if (logoImage.isDisplayed()) {
                fail("Logo image is present on the webpage.");
            } else {
                System.out.println("Logo image is not present on the webpage.");
            }
        } catch (Exception e) {
            System.out.println("Logo image is not present on the webpage.");
        }
    }

    public void checkForLogoDimension() {
        WebElement logoImage = null;

        //  expected placement (X and Y coordinates)
        int expectedX = 295;
        int expectedY = 33;

        //  allowed tolerance for placement (adjust as needed)
        int tolerance = 5;

        // Verify that the logo is within the expected placement
        try {
            logoImage = Hooks.driver.findElement(JP_MORGAN_LOGO);

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
