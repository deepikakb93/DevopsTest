**Add the project Dependencies**:
Cucumber-java

Cucumber-junit

Cucumber-core

Cucumber-picocontainer

junit

httpclient

json

jackson-databind

**Api Testing**


![image](https://github.com/deepikakb93/DevopsTest/assets/70422581/5f958412-366a-4636-9bb2-6802d43969db)

Amazon Search Feature

This project demonstrates how to automate the Amazon search functionality using Behavior-Driven Development (BDD) 
with Java and Selenium.

Prerequisites

Java 8 or above

Maven

WebDriver compatible with your browser (Chrome)

Setup

Clone the repository: git clone git@github.com:deepikakb93/DevopsTest.git

Set up WebDriver:

Download the WebDriver executable for your preferred browser.

Update the WebDriver path in the Eg: Devops_project/CucumbeSeleniumFramework/chromedriver.exe.

Running the Tests

mvn test

Test Scenario

Feature: Amazon Search

  Scenario: Filter and sort Sony TV results
    Given I am on the Amazon Australia website
    When I search for "Sony TV"
    And I apply filters like:
      | Filter Type  | Option      |
      | Brand        | Sony        |
      | Resolution   | 4K          |
      | Model Year   | 2022        |
    And I sort the results by price 
    Then I log the price of the lowest ticketed item
    And I log the price of the highest ticketed item
	
Project Structure:

src/test/java

    -Features
		-AmazonLoginPage.feature
     -ApiTest.feature
        
    -pageObjects
	  -AmazonHomePage.java
      -AmazonSearchResultsPage.java
            
    -runners
        -ApiRunners.java
        -Runners.java
        
	-steps
		-AmazonSearchSteps.java
		-VerifyingApi.java

  
pages: Contains Java classes representing the page objects for the Amazon website.(Eg: AmazonHomePage, AmazonSearchResultsPage)

steps: Contains the step definitions for the BDD scenario.

features: Contains the feature file describing the BDD scenario.(Eg: AmazonLoginPage.feature, ApiTest.feature)
