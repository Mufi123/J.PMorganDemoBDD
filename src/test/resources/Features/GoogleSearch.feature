Feature: J.P. Morgan Logo Verification

  @HeadlessMode
  Scenario: Basic Functionality Test
    Given browser window is open
    And user is on Google search page
    When user enters a "J. P. Morgan" in search box
    And hits enter
    Then user is navigated to the search results
    And user clicks on the first result
    And user verifies that the J.P. Morgan logo is displayed

  @HeadlessMode
  Scenario: Navigation and URL Verification
    Given browser window is open
    And user is on Google search page
    When user enters a "J. P. Morgan" in search box
    And hits enter
    Then user is navigated to the search results
    And user clicks on the first result
    Then user verifies that the current URL matches to given URL

  @HeadlessMode
  Scenario: Logo Placement Verification
    Given browser window is open
    And user is on Google search page
    When user enters a "J. P. Morgan" in search box
    And hits enter
    Then user is navigated to the search results
    And user clicks on the first result
    And user verifies that the J.P. Morgan logo is displayed in the top-left corner

  @HeadlessMode
  Scenario: Negative Testing - Invalid Search
    Given browser window is open
    And user is on Google search page
    When user enters irrelevant search text in the search box
    And hits enter
    And user clicks on the first result
    And user verifies that the J.P. Morgan logo is not displayed



