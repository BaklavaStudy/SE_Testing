//import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
//import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SeleniumTest {

    //Globals for b4/after all
    private static ChromeDriver driver;
    private WebDriverWait wait;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver", "C:\\Code\\SE_Testing\\BrowserDriver\\chromedriver-win64\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        //idk what these do but intellij autocomplete told me to put them.
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));


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
    @DisplayName("Get Store")
    public void getStore() throws InterruptedException {
    // Test for later
    }
}

//        driver.get("https://www.google.com");
//        driver.manage().window().maximize();
//WebElement element = driver.findElement(By.name("q"));
//        element.sendKeys("Fortnite");
//        element.submit();
//        Thread.sleep(3000);
//        driver.close();
