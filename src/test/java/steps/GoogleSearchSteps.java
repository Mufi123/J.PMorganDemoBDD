package steps;

import hooks.Hooks;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.GoogleSearchPage;

public class GoogleSearchSteps {
	private GoogleSearchPage googleSearchPage;

	@Given("the browser window is open")
	public void browser_is_open() {
		googleSearchPage = new GoogleSearchPage(Hooks.driver);
		googleSearchPage.navigateGoogle();
		System.out.println("The user opened the browser successfully");
	}

	@And("the user is on the Google search page")
	public void user_is_on_google_search_page() throws InterruptedException {
		googleSearchPage.navigateGoogle();
		Thread.sleep(2000);
		googleSearchPage.acceptCookiePopup();
	}

	@When("the user enters \"J. P. Morgan\" in the search box")
	public void user_enters_text_in_search_box() throws InterruptedException {
		googleSearchPage.enterTextInGoogle("J. P. Morgan");
		System.out.println("The user enters text in the search box successfully");
	}

	@When("the user enters irrelevant search text in the search box")
	public void user_enters_irrelevant_text_in_search_box() throws InterruptedException {
		googleSearchPage.enterTextInGoogle("thisistheirrelevanttext");
	}

	@And("the user hits Enter")
	public void hits_enter() throws InterruptedException {
		googleSearchPage.clickEnterInGoogle();
		System.out.println("The user hits Enter successfully");
	}

	@Then("the user is navigated to the search results")
	public void user_is_navigated_to_search_results() throws PendingException {
		googleSearchPage.verifySearchResult();
	}

	@And("the user clicks on the first result")
	public void user_is_navigated_to_jpmorgan_page() throws PendingException {
		googleSearchPage.verifyNavigateToJpMorganPage();
	}

	@Then("the user verifies that the current URL matches the given URL")
	public void user_verifies_the_current_url() {
		googleSearchPage.verifyTheCurrentUrl();
	}

	@And("the user verifies that the J.P. Morgan logo is displayed")
	public void user_is_verify_to_jpmorgan_logo() throws PendingException {
		googleSearchPage.verifyTheJpMorganLogo();
	}

	@And("the user verifies that the J.P. Morgan logo is not displayed")
	public void user_is_verify_to_jpmorgan_logo_not_displayed() throws PendingException {
		googleSearchPage.jpMorganLogoIsNotDisplayed();
	}

	@And("the user verifies that the J.P. Morgan logo is displayed in the top-left corner")
	public void user_is_verify_logo_dimension_and_size() {
		googleSearchPage.checkForLogoDimension();
	}
}
