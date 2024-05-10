import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import java.time.Duration;

public class CheckoutPage {
    @Test(priority = 1)
    void titleVerificationTest(){

        String actualTitle = EbayPurchaseTest.driver.getTitle();
        String expectedTitle = "Checkout | eBay";
        Assert.assertEquals(actualTitle,expectedTitle,"Title is not matched");
        EbayPurchaseTest.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(4));
    }

    @AfterTest
    void Close_the_Browser(){
        EbayPurchaseTest.driver.quit();
    }
}