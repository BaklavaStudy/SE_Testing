//import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
//import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    private static ChromeDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\SE_Testing\\BrowserDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //idk what these do but intellij autocomplete told me to put them.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get("https://demoblaze.com/index.html#");
    }

    @AfterAll
    public static void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    @Order(1)
    @DisplayName("1. All Category testing")
    public void allCategories() throws InterruptedException {
        String[] categories = {"Phones", "Laptops", "Monitors"};

        for (String category : categories) {
            WebElement catLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(category)));
            catLink.click();

            // Wait for products to load
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card-title")));
        }
    }

    // This test fails because it doesn't correctly click the laptop link. I'm not sure why.
    @Test
    @Order(2)
    @DisplayName("2. Laptop Category testing")
    public void laptopCategories() throws InterruptedException {
        String[] laptops = {"Sony vaio i5", "Sony vaio i7", "MacBook air", "Dell i7 8gb", "2017 Dell 15.6 Inch", "MacBook Pro"};

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Laptops"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sony vaio i5")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card-title")));

        boolean match = true; // Should remain true as we want all laptop and gallery items to match.
        List<WebElement> items = driver.findElements(By.cssSelector(".card-title a")); // Get all listing titles

        for (int i=0; i < laptops.length; i++) {
            System.out.println("Laptop at " + i + ": " + laptops[i]);
            System.out.println("Catalogue item at " + i + ": " + items.get(i).getText());
            if (!laptops[i].equals(items.get(i).getText())) {
                match = false;
                break;
            }
        }
        Assertions.assertTrue(match);
    }

}

//        driver.get("https://www.google.com");
//        driver.manage().window().maximize();
//        WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Fortnite");
//        element.submit();
//        Thread.sleep(3000);
//        driver.close();
