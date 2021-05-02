package challenge7;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

import java.util.List;


public class challenge7 {
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
    public void fullChallenge7() {
        String[][] makeAndLinkArray = new String[46][2];
        String[][] modelAndLinkArray = new String[41][2];
        goToCopart();
        createMakeList(makeAndLinkArray);
        testElementList(makeAndLinkArray);
        goToCopart();
        createModelElementList(modelAndLinkArray);
        testElementList(modelAndLinkArray);
    }

    // Navigate to Copart.com
    public void goToCopart() {
        driver.get("https://www.copart.com");
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href=\"#tabMakes\"]")));
    }

    // Create a 2-dimensional array that contains all the values displayed within the (Make) section
    // This must contain the URL for the links as well
    public void createMakeList(String[][] makeAndLinkArray) {
        int i = 0;
        List<WebElement> carMakeList = driver.findElements(By.xpath("//*[@id=\"tabMakes\"]/div/div/div/span/span/a"));
        for (WebElement carMake : carMakeList) {
            makeAndLinkArray[i][0] = carMake.getText();
            makeAndLinkArray[i][1] = carMake.getAttribute("href");
            i = i + 1;
        }
    }

    // Create a 2-dimensional array that contains all the values displayed within the (Model) section
    // This must contain the URL for the links as well
    public void createModelElementList(String[][] modelAndLinkArray) {
        int i = 0;
        driver.findElement(By.xpath("//*[@href=\"#tabModels\"]")).click();
        List<WebElement> carModelList = driver.findElements(By.xpath("//*[@id=\"tabModels\"]/div/div/div/span/span/a"));
        for (WebElement carModel : carModelList) {
            modelAndLinkArray[i][0] = carModel.getText();
            modelAndLinkArray[i][1] = carModel.getAttribute("href");
            i = i + 1;
        }
    }

  // Verify that all the elements in the array navigate to the proper page
    public void testElementList(String[][] ElementList) {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        //Create For Loop which prints each link text, and then navigates to the expected webpage
        for (String[] strings : ElementList) {
            System.out.println("Navigating to this link: ");
            System.out.println(strings[0] + " " + strings[1]);
            driver.get(strings[1]);
        }
    }
}

