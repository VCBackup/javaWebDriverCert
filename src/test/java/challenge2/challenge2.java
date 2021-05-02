package challenge2;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.*;
import org.testng.Assert;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class challenge2 {
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

    // Search for Exotics
    @Test()
    public void searchForExotics() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.sendKeys("Exotics");
        searchBox.sendKeys(Keys.ENTER);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Verify Porsche is on the list
    // Use a Hard Assert
    @Test()
    public void verifyPorsche() {
        List<WebElement> carMake = driver.findElements(
                By.xpath("//tbody//td[5]//span"));
        Assert.assertFalse(carMake.isEmpty());
        for (int i = carMake.size()-1; i > 0; i--){
            if (carMake.get(i).getText().contains("PORSCHE")){
                System.out.println(carMake.get(i).getText());
                System.out.println("Porsche is contained in the list");
                break;
            }
            else {
                System.out.println("Porsche is not contained within the list");
            }
        }
    }


}
