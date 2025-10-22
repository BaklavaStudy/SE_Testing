import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CartTest {
    //Globals for b4/after all
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
    @DisplayName("1. Add item to cart test")
    public void addItemToCart() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Laptops"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Sony vaio i5"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Add to cart"))).click();

        // Wait for alert and accept it
        wait.until(ExpectedConditions.alertIsPresent());
        driver.switchTo().alert().accept();

        driver.navigate().back(); // back to homepage
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cart"))).click();

        boolean itemPresent = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//td[contains(text(), 'Sony vaio i5')]"))) != null;

        Assertions.assertTrue(itemPresent, "Item not found in cart");
    }

    @Test
    @Order(2)
    @DisplayName("2. Place order test")
    public void placeOrderTest() {
        wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Cart"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Place Order']"))).click();

        // Fill form
        driver.findElement(By.id("name")).sendKeys("Adnan");
        driver.findElement(By.id("country")).sendKeys("Australia");
        driver.findElement(By.id("city")).sendKeys("Melbourne");
        driver.findElement(By.id("card")).sendKeys("1234567890123456");
        driver.findElement(By.id("month")).sendKeys("10");
        driver.findElement(By.id("year")).sendKeys("2025");

        driver.findElement(By.xpath("//button[text()='Purchase']")).click();

        // Verify success message
        WebElement confirmMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sweet-alert")));
        Assertions.assertTrue(confirmMsg.getText().contains("Thank you"), "Order confirmation not found!");
    }
}
