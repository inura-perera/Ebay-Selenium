import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Navigation {
    @Test(priority = 1)
    @Parameters({"url"})
    void navigation(String url){
        EbayPurchaseTest.driver.get(url);
        EbayPurchaseTest.driver.manage().window().maximize();
    }
    @Test(priority = 2)
    void logoVerificationTest(){
        WebElement logo = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"gh-logo\"]"));
        Assert.assertTrue(logo.isDisplayed(),"Logo is not displayed on the page.");
    }
    @Test(priority = 3)
    void titleVerificationTest(){
        String actualTitle = EbayPurchaseTest.driver.getTitle();
        String expectedTitle = "Electronics, Cars, Fashion, Collectibles & More | eBay";
        Assert.assertEquals(actualTitle,expectedTitle,"Title is not matched");
    }
}
