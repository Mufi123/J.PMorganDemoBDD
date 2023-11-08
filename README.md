# Project Name
BDD+Cucumber+Java
selenium-cucumber : Automation Testing Using Java

selenium-cucumber is a behavior driven development (BDD) approach to write automation test script to test Web. It enables you to write and execute automated acceptance/unit tests. It is cross-platform, open source and free. Automate your test cases with minimal coding

## Table of Contents

- [Introduction](#introduction)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)
- [Project Structure](#project-structure)
- [Running Tests](#running-tests)
- [Reporting](#reporting)


## Introduction
This project is a demonstration of Behavior-Driven Development (BDD) using Cucumber, Java, and Maven. BDD is an agile software development methodology that encourages collaboration among developers, testers, and non-technical stakeholders to define and test application behavior in a common language that everyone can understand.

#### Key Components
Cucumber: Cucumber is a widely-used BDD framework that allows to write executable specifications using plain text and Gherkin syntax.

Java: Java is the programming language of choice for this project, providing the flexibility and robustness required for implementing BDD test automation.

Maven: Maven is used for project management and dependency resolution, simplifying the build and configuration process.

## Project-Structure

J.PMorganDemoBDDFrameWork
|
|_src/test/java
|	|_hooks
|	|	|_Hooks.java
|	|	|...
|	|_pageObjects
|	|	|_GoogleSearchPage
|	|	|...
|	|_runners
|	|	|_TestRunner
|	|	|...
|	|_steps
|	|	|_GoogleSearchSteps
|	|	|...
|_src/test/resources
|	|_drivers
|	|   |_chromedriver.exe
|	|	|...
|	|_Features
|	| 	|_GoogleSearch.feature
|	|	|...

**hooks/Hooks** -- In `Hooks.java`, the browser is initialized before the test using the `@Before` hook, and it is closed after the test using the `@After` hook. Hooks ensure consistent test execution by offering a standardized approach to handle setup and teardown processes.

**pageObjects/GoogleSearchPage** --  They abstract the details of a web page, including its elements (e.g., buttons, text fields, links) and actions (e.g., clicking, entering text, verifying content).
Page Objects promote modularity and reusability by separating page-specific details from the test code.

**runners/TestRunner** -- The Runner class sets up the environment and triggers the execution of Cucumber scenarios, coordinating the interaction between feature files and step definitions.

**steps/GoogleSearchSteps** -- This is the landing page when the tests are triggered 


## Description of tests (GoogleSearch.feature)
* Basic Functionality Test: This is a positive test to check if the J.P. Morgan logo is displayed after performing a Google search.
* Navigation and URL Verification: In this positive test, we verify that the URL to which we navigate is correct.
* Logo Placement Verification: This positive test ensures that the logo is correctly positioned on the page.
* Negative Testing - Invalid Search: In this negative test, we verify that invalid input should not display any results.

## Running-tests
* Run tests from the command line using Maven. Open a terminal and navigate to your project's root directory. Use the following command:
  `mvn clean install`
* Run tests from your IDE. Most IDEs have built-in support for running Cucumber tests. You can run specific feature files, scenarios, or step definitions directly from the IDE.


## Reports (.target)
* In tests  implemented multiple reports like (HTML reports,JSON reports, JUNIT Reports)

        *HTML reports we can find the screenshot when tests are failed , we find the tests results in the reports
        *JSON reports generate proper JSON reports that can be integrated into other tools.
        *JUnit reports generate xml report for the running tests



