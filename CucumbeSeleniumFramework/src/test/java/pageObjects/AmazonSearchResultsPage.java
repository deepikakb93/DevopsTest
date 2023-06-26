package pageObjects;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AmazonSearchResultsPage {

	 private WebDriver driver;
	    // Locators
	    private By filterDropdowns = By.cssSelector("[data-component-type='s-search-filter'] .a-dropdown-prompt");

	    public void AmazonSearchResultsPage(WebDriver driver) {
	        this.driver = driver;
	    }
	   

	    // Locators
	    private By searchBox = By.id("twotabsearchtextbox");
	    //private By brandFilter = By.xpath("//span[contains(text(),'Sony')]");
	   // private By resolutionFilter = By.xpath("//*[contains(text(),'Resolution')]/parent::*/following::ul[1]//*[contains(text(),'4K')]");
	   // private By modelYearFilter = By.xpath("//*[contains(text(),'Model Year')]/parent::*/following::ul[1]//*[contains(text(),'2022')]");
	    private By sortbyfeatured=By.xpath("(//span[contains(text(),'Featured')])[1]");
	    private By hightolowfilter=By.xpath("//a[contains(text(),'Price: High to Low')]");
	    
	    public AmazonSearchResultsPage(WebDriver driver) {
	        this.driver = driver;
	        
	    }

	    public void open() {
	        driver.get("https://www.amazon.com.au");
	        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
	    }
	    
	    public void search(String searchTerm) {
	        driver.findElement(searchBox).sendKeys(searchTerm);
	        driver.findElement(searchBox).submit();
	    }
	    
	    public void applyFilters(String filterType, String option) throws InterruptedException {
	        // Apply brand filter
	    	
	        switch (filterType) {
            case "Brand":
            	driver.findElement(By.xpath("//span[contains(text(),'"+option+"')]")).click();
                break;
            case "Resolution":
                driver.findElement(By.xpath("//*[contains(text(),'Resolution')]/parent::*/following::ul[1]//*[contains(text(),'"+option+"')]")).click();
                break;
            case "Model Year":
                driver.findElement(By.xpath("//*[contains(text(),'Model Year')]/parent::*/following::ul[1]//*[contains(text(),'"+option+"')]")).click();
                break;
            default:
                throw new IllegalArgumentException("Invalid filter type: " + filterType);
        }
	    	
	    }

	    private void clickFilter(By filterLocator) {
	        WebElement filter = driver.findElement(filterLocator);
	        filter.click();
	    }

	    public void sortByPriceHighToLow() throws InterruptedException {
	        // Implementation to sort results by price (high to low)
	    	driver.findElement(sortbyfeatured).click();
	    	Thread.sleep(5000);
	    	driver.findElement(hightolowfilter).click();
	    	
	    }

	    public void logLowestAndHighestPrices() {
	        // Implementation to log the lowest and highest ticketed item prices
	    }
}
