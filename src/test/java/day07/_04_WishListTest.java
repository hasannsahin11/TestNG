package day07;

import Utilities.DriverClass;
import Utilities.ParameterDriverClass;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class _04_WishListTest extends ParameterDriverClass {
    /**
     * Go to "https://opencart.abstracta.us/index.php?route=account/login"
     * Log in
     * search for ipod (send "ipod" from xml file)
     * add one of the products to the wish list randomly
     * Click on Wish List button
     * Check if the name of the product is the same with the name of the product you added.
     *
     * Use POM and ParameterDriver
     * Perform non-parallel cross browser test
     * */

    @Test
    @Parameters(value = "searchKeyword")
    void wishListTest(String searchKeyword) {

        _05_WishListElements wishListElements = new _05_WishListElements(driver);

        wishListElements.getSearchBox().sendKeys(searchKeyword);
        wishListElements.getSearchButton().click();

        int randomIndex = (int) (Math.random() * wishListElements.getProductsList().size());
        String productName = wishListElements.getProductNameList().get(randomIndex).getText();
        wishListElements.getProductsList().get(randomIndex).click();

        wishListElements.getWishListButton().click();

        Assert.assertEquals(wishListElements.getWishListProduct().getText(), productName);

        wishListElements.getRemoveFromWishListButton().click();

    }
}
