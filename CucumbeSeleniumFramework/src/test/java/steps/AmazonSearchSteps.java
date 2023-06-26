package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import pageObjects.AmazonHomePage;
import pageObjects.AmazonSearchResultsPage;
import io.cucumber.java.en.Then;
import io.cucumber.java.After;
import io.cucumber.java.en.And;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import io.cucumber.core.cli.Main;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class AmazonSearchSteps {
	 private WebDriver driver;
	    private AmazonHomePage homePage;
	    private AmazonSearchResultsPage resultsPage;

	    @Given("I am on the Amazon Australia website")
	    public void i_am_on_the_amazon_australia_website() {
	        System.setProperty("webdriver.chrome.driver", "C:/Users/MagDeep/eclipse-workspace/Devops_project/CucumbeSeleniumFramework/chromedriver.exe");
	        driver = new ChromeDriver();
	        homePage = new AmazonHomePage(driver);
	        resultsPage = new AmazonSearchResultsPage(driver);
	        homePage.open();
	    }

	    @When("I search for {string}")
	    public void whenISearchFor(String searchTerm) {
	        homePage.search(searchTerm);
	    }

	    @And("I apply filters like:")
	    public void andIApplyFilters(DataTable dataTable) throws InterruptedException {
	    	List<Map<String, String>> filters = dataTable.asMaps(String.class, String.class);

	        for (Map<String, String> filter : filters) {
	            String filterType = filter.get("Filter Type");
	            String option = filter.get("Option");

	            resultsPage.applyFilters(filterType, option);
	        }
	    }

	    @And("I sort the results by price")
	    public void andISortResultsByPrice() throws InterruptedException {
	        resultsPage.sortByPriceHighToLow();
	    }

	    @Then("I log the price of the lowest ticketed item")
	    public void thenILogPriceOfLowestTicketedItem() {
	        resultsPage.logLowestAndHighestPrices();
	    }

	    @And("I log the price of the highest ticketed item")
	    public void andILogPriceOfHighestTicketedItem() {
	        resultsPage.logLowestAndHighestPrices();
	    }

	    // Add @After hook or method to close the browser and cleanup resources
}