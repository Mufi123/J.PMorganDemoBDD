Feature: J.P. Morgan Logo Verification

  @HeadlessMode
  @Chrome
  Scenario: Basic Functionality Test
    Given the browser window is open
    And the user is on the Google search page
    When the user enters "J. P. Morgan" in the search box
    And the user hits Enter
    Then the user is navigated to the search results
    And the user clicks on the first result
    And the user verifies that the J.P. Morgan logo is displayed

  @HeadlessMode
  @Chrome
  Scenario: Navigation and URL Verification
    Given the browser window is open
    And the user is on the Google search page
    When the user enters "J. P. Morgan" in the search box
    And the user hits Enter
    Then the user is navigated to the search results
    And the user clicks on the first result
    Then the user verifies that the current URL matches the given URL

  @Chrome
  Scenario: Logo Placement Verification
    Given the browser window is open
    And the user is on the Google search page
    When the user enters "J. P. Morgan" in the search box
    And the user hits Enter
    Then the user is navigated to the search results
    And the user clicks on the first result
    And the user verifies that the J.P. Morgan logo is displayed in the top-left corner

  @HeadlessMode
  @Chrome
  Scenario: Negative Testing - Invalid Search
    Given the browser window is open
    And the user is on the Google search page
    When the user enters irrelevant search text in the search box
    And the user hits Enter
    And the user clicks on the first result
    And the user verifies that the J.P. Morgan logo is not displayed



