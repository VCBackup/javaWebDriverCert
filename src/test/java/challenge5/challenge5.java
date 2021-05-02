package challenge5;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

import java.util.HashMap;
import java.util.Map;
import java.util.*;

public class challenge5 {
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

        public void countFrequencies(ArrayList<String> list) {
            // hashmap to store the frequency of element
            Map<String, Integer> hm = new HashMap<>();

            for (String i : list) {
                Integer j = hm.get(i);
                hm.put(i, (j == null) ? 1 : j + 1);
            }

            // displaying the occurrence of elements in the arraylist
            for (Map.Entry<String, Integer> val : hm.entrySet()) {
                System.out.println(val.getKey() + " " + "occurs" + ": " + val.getValue() + " times");
            }
        }

        // Full test
        @Test()
        public void fullChallenge5() throws InterruptedException {
            goToCopart();
            searchForPorsche();
            showEntry100();
            countPorscheTypes();
            countDamageTypes();
        }

        // Navigate to Copart.com
        public void goToCopart() {
            driver.get("https://www.copart.com");
        }

        // Search for Porsche
        public void searchForPorsche() throws InterruptedException {
            WebElement searchBox = driver.findElement(By.id("input-search"));
            searchBox.sendKeys("Porsche");
            searchBox.sendKeys(Keys.ENTER);
            WebDriverWait webPageTimer = new WebDriverWait(driver, 10);
            webPageTimer.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//td[6]//span")));
            searchBox.sendKeys(Keys.ESCAPE);
        }

        // Change the drop-down for Show Entries from 20 to 100
        public void showEntry100() {
            WebElement showEntryDropDown = driver.findElement(By.xpath("//*[@name=\"serverSideDataTable_length\"]"));
            showEntryDropDown.click();
            WebElement dropDown100 = driver.findElement(By.xpath("//*[@value=\"100\"]"));
            dropDown100.click();
        }

        // Count how many different models of Porsche are in the results on the first page
        // Return in the terminal the types and how many there are of each
        public void countPorscheTypes(){
            ArrayList<String> carMakeStrings = new ArrayList<>();
            List<WebElement> carMakeElements = driver.findElements(
                    By.xpath("//tbody//td[6]//span"));
            Assert.assertFalse(carMakeElements.isEmpty());
            while (carMakeElements.size() < 100){
                carMakeElements.clear();
                carMakeElements = driver.findElements(
                        By.xpath("//tbody//td[6]//span"));
            }
            for (WebElement i: carMakeElements){
                carMakeStrings.add(i.getText());
            }
            System.out.println("These are the current Porsche Types: ");
            countFrequencies(carMakeStrings);
        }

        //Create a switch statement to count the types of damages
    public void countDamageTypes(){
        String column = "damage";
        String columnDetails;
        switch (column) {
            case "selection" -> columnDetails = "//tbody//td[1]//div";
            case "images" -> columnDetails = "//tbody//td[2]//div";
            case "lotNumber" -> columnDetails = "//tbody//td[3]//div";
            case "year" -> columnDetails = "//tbody//td[4]//span";
            case "make" -> columnDetails = "//tbody//td[5]//span";
            case "model" -> columnDetails = "//tbody//td[6]//span";
            case "itemNumber" -> columnDetails = "//tbody//td[7]//span";
            case "location" -> columnDetails = "//tbody//td[8]//span";
            case "saleDate" -> columnDetails = "//tbody//td[9]//span";
            case "odometer" -> columnDetails = "//tbody//td[10]//span";
            case "docType" -> columnDetails = "//tbody//td[11]//span";
            case "damage" -> columnDetails = "//tbody//td[12]//span";
            case "retailValue" -> columnDetails = "//tbody//td[13]//span";
            case "currentBid" -> columnDetails = "//tbody//td[14]//span";
            default -> throw new IllegalStateException("Unexpected value: " + column);
        }
        ArrayList<String> carDamageStrings = new ArrayList<>();
        List<WebElement> carDamageElements = driver.findElements(By.xpath(columnDetails));
        Assert.assertFalse(carDamageElements.isEmpty());
        while (carDamageElements.size() < 100){
            carDamageElements.clear();
            carDamageElements = driver.findElements(
                    By.xpath(columnDetails));
        }
        for (WebElement i: carDamageElements){
            carDamageStrings.add(i.getAttribute("innerText"));
        }
        System.out.println("These are the current Damage Types: ");
        countFrequencies(carDamageStrings);
        }
    }