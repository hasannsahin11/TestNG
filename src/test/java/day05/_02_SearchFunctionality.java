package day05;

import Utilities.DriverClass;
import Utilities.ParameterDriverClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class _02_SearchFunctionality extends DriverClass {
    /**
     * Go to https://opencart.abstracta.us/index.php?route=account/login
     * Login
     * Search for "mac"
     * Check if the all results contains "mac" keyword
     */

    @Test
    @Parameters(value = "searchKeyWord")
    void searchFunctionalityTest(String str){
        WebElement searchBox= driver.findElement(By.cssSelector("input[type='text']"));
        searchBox.sendKeys(str);

        WebElement searchButton = driver.findElement(By.cssSelector("button[class='btn btn-default btn-lg']"));
        searchButton.click();

        List<WebElement> resultList = driver.findElements(By.cssSelector("h4>a"));

        for (WebElement element: resultList){
            Assert.assertTrue(element.getText().toLowerCase().contains(str));
        }
    }

}
