import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShoppingCart {
    public static String Shopping_Cart_Item_Name;
    public static String Shopping_Cart_Item_Price;
    public static String Shopping_Cart_Item_Quantity;

    @Test(priority = 1)
    void Getting_Shopping_Cart_Item_Details(){
        Shopping_Cart_Item_Name = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[2]/div[1]/h3/a")).getText();
        String text_to_be_cleaned= EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[1]/div[1]/div/ul/li/div/div/div/div[1]/div/div[3]/div/div[2]/div/div/div[2]/div/span/span/span")).getText();

        Pattern pattern = Pattern.compile("[()US\\s]");
        Matcher matcher = pattern.matcher(text_to_be_cleaned);

        Shopping_Cart_Item_Price = matcher.replaceAll("");

        //it didn't get the quantity, so I added a thread sleep
        try {

            Thread.sleep(10000);
        } catch (InterruptedException e) {

            Thread.currentThread().interrupt();
            System.err.println("Thread was interrupted, failed to complete operation");
        }


        Shopping_Cart_Item_Quantity = EbayPurchaseTest.driver.findElement(By.xpath("//*[@data-test-id=\"qty-dropdown\"]")).getText();
    }
    @Test(priority = 2)
    void Assertion_of_Item_Title(){
        String actualTitle = Shopping_Cart_Item_Name;
        String expectedTitle = ProductPage.Product_Page_Item_Name;
        Assert.assertEquals(actualTitle,expectedTitle,"Item title is not matched");
    }

    @Test(priority = 3)
    void Assertion_of_Item_Price(){
        String actualPrice = Shopping_Cart_Item_Price;
        String expectedPrice = ProductPage.Product_Page_Item_Price;
        Assert.assertEquals(actualPrice,expectedPrice,"Item price is not matched");
    }

    @Test(priority = 4)
    void Assertion_of_Item_Quantity(){
        String actualQuantity = Shopping_Cart_Item_Quantity;
        String expectedQuantity = ProductPage.Product_Page_Item_Quantity;
        Assert.assertEquals(actualQuantity,"Qty "+expectedQuantity,"Item quantity is not matched");
        System.out.println("Shopping Cart Details");
        System.out.println("...............................................................");
        System.out.println("Title:- "+Shopping_Cart_Item_Name);
        System.out.println("Price:- "+Shopping_Cart_Item_Price);
        System.out.println("Quantity:- "+Shopping_Cart_Item_Quantity);
        System.out.println("...............................................................");
    }
    @Test(priority = 5)
    void Go_to_Checkout(){

        WebElement Go_to_Checkout_Button = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div[2]/div/div/div[2]/button"));
        Go_to_Checkout_Button.click();
    }

    @Test(priority = 6)
    void Continue_as_guest(){
        Duration duration = Duration.ofSeconds(10);
        WebDriverWait wait = new WebDriverWait(EbayPurchaseTest.driver,duration);
        WebElement Continue_as_guest_Button = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"gxo-btn\"]")));
        Continue_as_guest_Button.click();
    }
}
