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
	String projectPath = System.getProperty("user.dir");
	@Given("browser window is open")
	public void browser_is_open() {
		googleSearchPage = new GoogleSearchPage(Hooks.driver);
		googleSearchPage.navigateGoogle();
		System.out.println("User opened the browser successfully");
		System.out.println("Project path is : "+projectPath);
	}

	@And("user is on Google search page")
	public void user_is_on_google_search_page() throws InterruptedException {

		googleSearchPage.navigateGoogle();
		Thread.sleep(2000);
		googleSearchPage.acceptCookiePopup();
	}

	@When("user enters a \"J. P. Morgan\" in search box")
	public void user_enters_a_text_in_search_box() throws InterruptedException {
		googleSearchPage.enterTextInGoogle("J. P. Morgan");
		System.out.println("User enters a text in search box successfully");
	}

	@When("user enters irrelevant search text in the search box")
	public void user_enters_irrelevant_text_in_search_box() throws InterruptedException {
		googleSearchPage.enterTextInGoogle("thisistheirrelaventtext");
	}

	@And("hits enter")
	public void hits_enter() throws InterruptedException {
		googleSearchPage.clickEnterInGoogle();
		System.out.println("User hits enter successfully");
	}

	@Then("user is navigated to the search results")
	public void user_is_navigated_to_search_results() throws PendingException {
	googleSearchPage.verifySearchResult();
	}

	@And("user clicks on the first result")
	public void user_is_navigated_to_jpmorgan_page() throws PendingException {
	googleSearchPage.verifyNavigateToJpMorganPage();
	}

	@Then("user verifies that the current URL matches to given URL")
	public void user_verifies_the_current_url(){
		googleSearchPage.verifyTheCurrentUrl();
	}
	@And("user verifies that the J.P. Morgan logo is displayed")
	public void user_is_verify_to_jpmorgan_logo() throws PendingException {
       googleSearchPage.verifyTheJpMorganLogo();
	}

	@And("user verifies that the J.P. Morgan logo is not displayed")
	public void user_is_verify_to_jpmorgan_logo_not_displayed() throws PendingException {
		googleSearchPage.jpMorganLogoIsNotDisplayed();
	}

	@And("user verifies that the J.P. Morgan logo is displayed in the top-left corner")
	public void user_is_verify_logo_dimension_and_size(){
		googleSearchPage.checkForLogoDimension();
	}

}
