package day02;

import Utilities.DriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class _02_UpdateNameTest extends DriverClass {
    /**
     * Go to "https://opencart.abstracta.us/index.php?route=account/login"
     * Login
     * Click on Edit Account
     * Update name
     * Click on Continue
     * Verify the success message
     * Then update the name with the old name
     */

    @Test
    void updateNameTest() {
        updateName("Sr");
        updateName("Jr");
    }

    public void updateName(String name) {
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://opencart.abstracta.us/index.php?route=account/login");
//        WebElement userName = driver.findElement(By.id("input-email"));
//        userName.sendKeys("aston@martin.com" + Keys.TAB + "Password" + Keys.TAB + Keys.TAB + Keys.ENTER);


        //<a href="https://opencart.abstracta.us:443/index.php?route=account/edit">Edit Account</a>
        WebElement editAccount = driver.findElement(By.linkText("Edit Account"));
        editAccount.click();

        WebElement updateName = driver.findElement(By.id("input-firstname"));
        updateName.clear();
        updateName.sendKeys(name);

        //<input type="submit" value="Continue" class="btn btn-primary">
        WebElement saveButton = driver.findElement(By.xpath("//input[@type='submit']"));
        saveButton.click();

        //<div class="alert alert-success alert-dismissible"><i class="fa fa-check-circle">
        // </i> Success: Your account has been successfully updated.</div>

        WebElement successMessage = driver.findElement(By.cssSelector("div[class=\"alert alert-success alert-dismissible\"]"));
        String expectedMessage = "Success: Your account has been successfully updated.";
        Assert.assertEquals(successMessage.getText(), expectedMessage, "Your test has failed");
        System.out.println("Your test has passed.");
    }
}
