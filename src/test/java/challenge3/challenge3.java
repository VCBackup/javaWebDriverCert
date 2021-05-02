package challenge3;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;

import java.util.List;

public class challenge3 {
    public WebDriver driver;

    @BeforeSuite
    public void startSuite() {
    }
    @AfterSuite
    public void stopSuite() {
        System.out.println("All done!!!");
    }

    @BeforeClass
    public void startClass() {
        System.setProperty("webdriver.chrome.driver", ".bin//chromedriver.exe");
        driver = new ChromeDriver();
    }
    @AfterClass
    public void stopClass(){
        driver.quit();
    }

    @BeforeMethod()
    public void beforeMethod() {
    }
    @AfterMethod()
    public void afterMethod(){
    }

    // Navigate to Copart.com
    @Test()
    public void goToCopart() {
        driver.get("https://www.copart.com");
    }

    // Print a list of all the Popular Items of vehicle Make/Models on the homepage
    // and the URL/href for each type, use a loop to make sure everything is displayed
    @Test()
    public void printPopularItems() {
        List<WebElement> popularItems = driver.findElements(
                By.xpath("//*[@id=\"tabMakes\"]//span/span/a"));
        for (WebElement popularItem : popularItems) {
            System.out.println(popularItem.getText());
            System.out.print(": ");
            System.out.print(popularItem.getAttribute("href"));
            System.out.println("");
        }
    }
}
