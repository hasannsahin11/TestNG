package day03;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.security.Key;
import java.time.Duration;
import java.util.List;

public class _02_AddressFunctionality extends DriverClass {
    /***
     * Go to "https://opencart.abstracta.us/index.php?route=account/login"
     * Login
     * Click on Address Book
     * Add a new address
     * Edit the address
     * Delete the ADDRESS
     */

    @Test
    void addAddress() {
        //<a href="https://opencart.abstracta.us:443/index.php?route=account/address" class="list-group-item">Address Book</a>
        WebElement addressBook = driver.findElement(By.linkText("Address Book"));
        addressBook.click();

        //<a href="https://opencart.abstracta.us:443/index.php?route=account/address/add" class="btn btn-primary">New Address</a>
        WebElement addAddress = driver.findElement(By.linkText("New Address"));
        addAddress.click();

        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.clear();
        firstName.sendKeys("Boni" + Keys.TAB + Keys.DELETE + "Garcia" + Keys.TAB + Keys.DELETE + "Selenium" + Keys.TAB + Keys.DELETE + "222 Java Ave" + Keys.TAB + Keys.TAB + Keys.DELETE + "Seleniumsville" + Keys.TAB + Keys.DELETE + "12321");

        WebElement country = driver.findElement(By.id("input-country"));
        Select select = new Select(country);
        int randomIndex = (int) (Math.random() * 253) + 1;
        select.selectByIndex(randomIndex);


        WebElement selectRegion = driver.findElement(By.id("input-zone"));
        Select regionSelect = new Select(selectRegion);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.not(ExpectedConditions.numberOfElementsToBe(By.xpath("//select[@id='input-zone']//option"), driver.findElements(By.xpath("//select[@id='input-zone']//option")).size())));

        List<WebElement> listOfRegions = driver.findElements(By.xpath("//select[@id='input-zone']//option"));
        int randomRegionIndex = ((int) (Math.random() * listOfRegions.size() - 1)) + 1;
        regionSelect.selectByIndex(randomRegionIndex);

        //<input type="submit" value="Continue" class="btn btn-primary">
        WebElement continueButton = driver.findElement(By.cssSelector("input[type='submit']"));
        continueButton.click();

        //<div class="alert alert-success alert-dismissible"><i class="fa fa-check-circle"></i> Your address has been successfully updated</div>
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"))));
        WebElement successMessage = driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"));
        Assert.assertTrue(successMessage.getText().contains("successfully"));
    }

    @Test(dependsOnMethods = "addAddress")
    void editAddress() {
        WebElement editButton = driver.findElement(By.xpath("(//a[text()='Edit'])[2]"));
        editButton.click();

        WebElement address1 = driver.findElement(By.id("input-address-1"));
        address1.clear();
        address1.sendKeys("5884 Snow St.");

        WebElement city = driver.findElement(By.id("input-city"));
        city.clear();
        city.sendKeys("New York");

        WebElement continueButton = driver.findElement(By.cssSelector("input[type='submit']"));
        continueButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"))));
        WebElement successMessage = driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"));

        Assert.assertTrue(successMessage.getText().contains("successfully"));
    }

    @Test(dependsOnMethods = "addAddress")
    void deleteAddress() {
        WebElement deleteButton = driver.findElement(By.xpath("(//a[text()='Delete'])[2]"));
        deleteButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"))));
        WebElement successMessage = driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"));

        Assert.assertTrue(successMessage.getText().contains("successfully"));
    }
}
