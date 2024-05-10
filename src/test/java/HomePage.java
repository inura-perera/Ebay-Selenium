import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class HomePage {

    public static String Selected_Item_Name;

    public static String Selected_Item_Price;
    @Test(priority = 1)
    @Parameters({"product", "category"})
    void select_category(String product ,String category){
        EbayPurchaseTest.driver.findElement(By.id("gh-ac")).sendKeys(product);
        WebElement categoryDropdownElement = EbayPurchaseTest.driver.findElement(By.xpath("//*[@id=\"gh-cat\"]"));
        Select categorySelect = new Select(categoryDropdownElement);
        categorySelect.selectByVisibleText(category);
        EbayPurchaseTest.driver.findElement(By.id("gh-btn")).click();
    }
    @Test(priority = 2)
    void getting_no_of_results(){
        String result = EbayPurchaseTest.driver.findElement(By.xpath("//h1[contains(text(),'results for')]")).getText();
        System.out.println(result);
    }
    @Test(priority = 3)
    @Parameters({"no_of_checking_results"})
    void assertion_of_word_mobile_phone(int no_of_checking_results){
        int filtered_count = 0;
        boolean first_filterd_item_selected = false;
        for(int i = 0;i< no_of_checking_results; i++){
            String xpathExpression = String.format("//div[@id='srp-river-results']/ul/li[%d]",(i+1));
            String ithProduct = EbayPurchaseTest.driver.findElement(By.xpath(xpathExpression)).getText();
            if (ithProduct.contains("Mobile Phone")) {
                filtered_count++;
                String[] lines = ithProduct.split("\\r?\\n");
                String title = lines[0];
                String price = lines[3];
                System.out.println("...............................................................");
                System.out.println(title);
                System.out.println(price);

                if(first_filterd_item_selected==false){
                    String itemID = EbayPurchaseTest.driver.findElement(By.xpath(xpathExpression)).getAttribute("id");
                    String xpathExpression_2 = String.format("//*[@id=\"%s\"]/div/div[1]/div/a/div/img",itemID);
                    WebElement first_filtered_item_image = EbayPurchaseTest.driver.findElement(By.xpath(xpathExpression_2));
                    first_filtered_item_image.click();

                    Selected_Item_Name = title;
                    Selected_Item_Price = price;
                    first_filterd_item_selected = true;
                }

            }
        }
        System.out.println("...............................................................");
        Assert.assertTrue(filtered_count > 0, "At least one of the first five search results does not contain 'Mobile Phone'");
        System.out.println(filtered_count+" of first "+no_of_checking_results+" results contain 'Mobile Phone'");
    }

}