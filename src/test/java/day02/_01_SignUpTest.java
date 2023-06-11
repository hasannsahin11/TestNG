package day02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 1-Go to https://opencart.abstracta.us/index.php?route=account/login
 * 2-Click on My Account
 * 3-Click on Register
 * 4-Fill out the form with valid information
 * 5-Accept the Agreement
 * 6-Click on continue
 * 7-Verify that you're signed up
 */
public class _01_SignUpTest {
    @Test
    void signUp() throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.get("https://opencart.abstracta.us/index.php?route=account/login");

        WebElement myAccount = driver.findElement(By.xpath("//a[@title='My Account']"));
        myAccount.click();

        WebElement registerButton = driver.findElement(By.linkText("Register"));
        registerButton.click();

        WebElement firstName = driver.findElement(By.id("input-firstname"));
        firstName.sendKeys("Aston");

        WebElement lastName = driver.findElement(By.id("input-lastname"));
        lastName.sendKeys("Martin");

        WebElement email = driver.findElement(By.id("input-email"));
        email.sendKeys("aston2@martin.com");

        WebElement telephone = driver.findElement(By.id("input-telephone"));
        telephone.sendKeys("3338080");

        WebElement password = driver.findElement(By.id("input-password"));
        password.sendKeys("Password");

        WebElement passwordConfirm = driver.findElement(By.id("input-confirm"));
        passwordConfirm.sendKeys("Password");

        WebElement agreement = driver.findElement(By.xpath("//input[@type='checkbox']"));
        agreement.click();
        WebElement continueButton = driver.findElement(By.xpath("//input[@type='submit']"));
        continueButton.click();

        WebElement successMessage = driver.findElement(By.xpath("//div[@id='content']//p"));
        String expectedMessage = "Congratulations! Your new account has been successfully created!";
        Assert.assertEquals(successMessage.getText(), expectedMessage, "Your test is failed");
        System.out.println("Your test has passed");
        Thread.sleep(3000);
        driver.quit();


    }
}
