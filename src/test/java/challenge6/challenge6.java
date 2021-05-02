package challenge6;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;


public class challenge6 {
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
        driver.manage().window().maximize();
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

    // Full test
    @Test()
    public void fullChallenge6() throws InterruptedException, IOException {
        goToCopart();
        searchForNissan();
        findSkylineModel();
        takeScreenshotOfPage("skylineSuccess");
    }

    // Navigate to Copart.com
    public void goToCopart() {
        driver.get("https://www.copart.com");
    }

    // Search for Nissan
    public void searchForNissan() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("input-search"));
        searchBox.sendKeys("Nissan");
        searchBox.sendKeys(Keys.ENTER);
        WebDriverWait webPageTimer = new WebDriverWait(driver, 10);
        webPageTimer.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//td[6]//span")));
        searchBox.sendKeys(Keys.ESCAPE);
    }

    // Select the Skyline model on the left side of the page
    public void findSkylineModel() throws IOException {
        WebDriverWait webPageTimer = new WebDriverWait(driver, 10);
        webPageTimer.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(
                "//*[@class=\"list-group-item\"][4]//h4//a")));
        WebElement filterOptionsMinus = driver.findElement(By.xpath("//*[@class=\"fa fa-minus-square\"]"));
        filterOptionsMinus.click();
        WebElement filterOptionsPlus = webPageTimer.until(d -> d.findElement(
                By.xpath("//*[@class=\"fa fa-plus-square\"]")));
        filterOptionsPlus.click();
        WebElement modelSearchBox = driver.findElement(By.xpath(
                "//*[@class=\"list-group-item\"][4]//div//form//div//input"));
        modelSearchBox.click();
        modelSearchBox.sendKeys("Skyline");
        modelSearchBox.sendKeys(Keys.ENTER);
        try{
            WebElement clickBox = driver.findElement(By.xpath(
                    "//*[@class=\"list-group-item\"][4]//div//ul//li//div//label//abbr[@value=\"Skyline\""));
            webPageTimer.until(ExpectedConditions.elementToBeClickable(clickBox)).click();
        } catch(NoSuchElementException e){
            System.out.println("The results for a Nissan Skyline search returned 0.");
            takeScreenshotOfPage("skylineFailure");
        }
        WebElement skylineCheckBox = driver.findElement(By.xpath(
                "//*[@class=\"list-group-item\"][4]//div//ul//li//div//label//input"));
        skylineCheckBox.click();
    }

    // Take a screenshot of the page
    public void takeScreenshotOfPage(String screenshotName) throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File scrFile = screenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\temp\\" +screenshotName+ ".png"));
    }
}
