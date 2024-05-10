import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public class EbayPurchaseTest {
    public static WebDriver driver;
    public static ChromeOptions chrome_options;
    public static FirefoxOptions firefox_options;
    public static EdgeOptions edge_options;
    @BeforeTest
    @Parameters({"browser"})
    void choose_browser(String browser){
        if(browser.equalsIgnoreCase("Chrome")){
            chrome_options = new ChromeOptions();
            chrome_options.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\resources\\chromedriver.exe");
            driver = new ChromeDriver(chrome_options);
        }
        if(browser.equalsIgnoreCase("Firefox")){
            firefox_options = new FirefoxOptions();
            firefox_options.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\resources\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        if(browser.equalsIgnoreCase("Edge")){
            edge_options = new EdgeOptions();
            edge_options.addArguments("--remote-allow-origins=*");
            System.setProperty("webdriver.Edge.driver", System.getProperty("user.dir") + "\\resources\\msedgedriver.exe");
            driver = new EdgeDriver(edge_options);
        }

    }
}
