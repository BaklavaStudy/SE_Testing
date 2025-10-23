import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;

@SpiraTestConfiguration(
    url = "https://rmit.spiraservice.net/",
    login = "s4005276",
    rssToken = "{70C6A183-7D62-4DD3-9BD3-07FFB013954C}",
    projectId = 706,
    releaseId = 2520,
    testSetId = 5622
)

public class Category {

    private static ChromeDriver driver;
    private static WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\SE_Testing\\BrowserDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        // Hard cap so tests dont wait forever.
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
    @SpiraTestCase(testCaseId = 44476)
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

    // This test only passes if i add a thread.sleep. Otherwise doesn't correctly load the expectedPhones selection
    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 44477)
    @DisplayName("2. Laptop Category testing")
    public void laptopCategoriesOG() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Laptops"))).click();

        Thread.sleep(2000); // Legit doesn't work with an explicit wait. Every time I tried it would just go overtime, ;/

        String[] laptops = {"Sony vaio i5", "Sony vaio i7", "MacBook air",
                "Dell i7 8gb", "2017 Dell 15.6 Inch", "MacBook Pro"};


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

    @Test
    @Order(3)
    @SpiraTestCase(testCaseId = 44478)
    @DisplayName("3. Phone Category testing")
    public void phoneCategory() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Phones"))).click();

        Thread.sleep(2000); // Legit doesn't work with an explicit wait. Every time I tried it would just go overtime, ;/

        String[] expectedPhones = {
                "Samsung galaxy s6", "Nokia lumia 1520", "Nexus 6",
                "Samsung galaxy s7", "Iphone 6 32gb", "Sony xperia z5", "HTC One M9"
        };


        boolean match = true; // Should remain true as we want all laptop and gallery items to match.
        List<WebElement> items = driver.findElements(By.cssSelector(".card-title a")); // Get all listing titles

        for (int i=0; i < expectedPhones.length; i++) {
            System.out.println("Laptop at " + i + ": " + expectedPhones[i]);
            System.out.println("Catalogue item at " + i + ": " + items.get(i).getText());
            if (!expectedPhones[i].equals(items.get(i).getText())) {
                match = false;
                break;
            }
        }
        Assertions.assertTrue(match);
    }


    @Test
    @Order(4)
    @SpiraTestCase(testCaseId = 44479)
    @DisplayName("4. Monitor Category testing")
    public void monitorCategory() throws InterruptedException {

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Monitors"))).click();

        Thread.sleep(2000); // Legit doesn't work with an explicit wait. Every time I tried it would just go overtime, ;/

        String[] monitors = {"Apple monitor 24", "ASUS Full HD"};

        boolean match = true; // Should remain true as we want all laptop and gallery items to match.
        List<WebElement> items = driver.findElements(By.cssSelector(".card-title a")); // Get all listing titles

        for (int i=0; i < monitors.length; i++) {
            System.out.println("Monitor at " + i + ": " + monitors[i]);
            System.out.println("Catalogue item at " + i + ": " + items.get(i).getText());
            if (!monitors[i].equals(items.get(i).getText())) {
                match = false;
                break;
            }
        }
        Assertions.assertTrue(match);
    }
}
