package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AmazonHomePage {
	private WebDriver driver;

    // Locators
    private By searchBox = By.id("twotabsearchtextbox");

    public AmazonHomePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://www.amazon.com");
    }

    public void search(String searchTerm) {
        driver.findElement(searchBox).sendKeys(searchTerm);
        driver.findElement(searchBox).submit();
    }
	public void teardown() {
        driver.quit();
    }
}
