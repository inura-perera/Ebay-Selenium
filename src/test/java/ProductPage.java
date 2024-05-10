import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProductPage {

    public static String Product_Page_Item_Name;
    public static String Product_Page_Item_Price;
    public static String Product_Page_Item_Quantity;

    @Test(priority = 1)
    void Retrieve_and_Display_Item_Details(){
        ArrayList<String> tabs = new ArrayList<>(EbayPurchaseTest.driver.getWindowHandles());
        EbayPurchaseTest.driver.switchTo().window(tabs.get(1));
        Product_Page_Item_Name = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[1]/h1/span")).getText();
        String text_to_be_cleaned = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[3]/div/div/div[2]/span[2]/span")).getText();
        Pattern pattern = Pattern.compile("[()US\\s]");
        Matcher matcher = pattern.matcher(text_to_be_cleaned);


        Product_Page_Item_Price = matcher.replaceAll("");
        System.out.println("Product Page Details");
        System.out.println("...............................................................");
        System.out.println("Title:- "+Product_Page_Item_Name);
        System.out.println("Price:- "+Product_Page_Item_Price);
        System.out.println("...............................................................");
    }

    @Test(priority = 2)
    void Assertion_of_Item_Title(){
        String actualTitle = Product_Page_Item_Name;
        String expectedTitle = HomePage.Selected_Item_Name;
        Assert.assertEquals(actualTitle,expectedTitle,"Item title is not matched");
    }

    @Test(priority = 3)
    void Assertion_of_Item_Price(){
        String actualPrice = Product_Page_Item_Price;
        String expectedPrice = HomePage.Selected_Item_Price;
        Assert.assertEquals(actualPrice,expectedPrice,"Item price is not matched");
    }

    @Test(priority = 4)
    @Parameters({"color"})
    void Set_Color(String color){
        WebElement colorDropdownElement = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"x-msku__select-box-1000\"]"));
        Select colorSelect = new Select(colorDropdownElement);
        colorSelect.selectByVisibleText(color);
    }

    @Test(priority = 5)
    @Parameters({"quantity"})
    void Set_Quantity(String quantity){
        WebElement quantityTextBox = EbayPurchaseTest.driver.findElement(By.id("qtyTextBox"));
        quantityTextBox.clear();
        quantityTextBox.sendKeys(quantity);
        Product_Page_Item_Quantity = quantity;
    }
    @Test(priority = 6)
    void Add_Item_to_Cart(){
        WebElement Add_Cart_Button = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/div[8]/ul/li[2]/div/a/span/span"));
        Add_Cart_Button.click();
    }
    @Test(priority = 7)
    void Click_On_Cart_Icon(){
        WebElement Cart_Icon_Button = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"gh-cart-n\"]"));
        Cart_Icon_Button.click();
    }


}
