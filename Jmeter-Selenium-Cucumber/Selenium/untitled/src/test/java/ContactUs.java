import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;

import java.time.Duration;

@SpiraTestConfiguration(
    url = "https://rmit.spiraservice.net/",
    login = "s4005276",
    rssToken = "{70C6A183-7D62-4DD3-9BD3-07FFB013954C}",
    projectId = 706,
    releaseId = 2520,
    testSetId = 5623
)

public class ContactUs {
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
    @SpiraTestCase(testCaseId = 44473)
    @DisplayName("1. Test contact form")
    public void testContactForm() {
        WebElement contactButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Contact")));
        contactButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("exampleModal")));

        driver.findElement(By.id("recipient-email")).sendKeys("test@example.com");
        driver.findElement(By.id("recipient-name")).sendKeys("Adnan");
        driver.findElement(By.id("message-text")).sendKeys("This is a Selenium test message.");

        driver.findElement(By.xpath("//button[text()='Send message']")).click();

        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        driver.switchTo().alert().accept();

        Assertions.assertTrue(alertText.toLowerCase().contains("thanks")
                        || alertText.toLowerCase().contains("thank"),
                "Expected a thank-you or confirmation alert, got: " + alertText);

        System.out.println("Contact form successfully sent.");
    }


    // Works just need to fidget with the clicking x button.
    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 44474)
    @DisplayName("2. Test about us video")
    public void testAboutUsVideo() throws InterruptedException {
        WebElement aboutUsButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("About us")));
        aboutUsButton.click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("videoModal")));
        WebElement videoElement = driver.findElement(By.tagName("video"));

        // Play video using JS
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].play()", videoElement);
        Thread.sleep(2000); // Let it play for a bit

        boolean isPaused = (Boolean) js.executeScript("return arguments[0].paused;", videoElement);
        Assertions.assertFalse(isPaused, "Video did not play.");

        // Make fullscreen
        js.executeScript("if (arguments[0].requestFullscreen) arguments[0].requestFullscreen();", videoElement);
        Thread.sleep(2000);

        js.executeScript("if (document.exitFullscreen) document.exitFullscreen();");

        // Need to work on this. Doesn't work right now.
        driver.findElement(By.xpath("//*[@id='videoModal']/div/div/div[1]/button/span")).click();

        System.out.println("About Us video played and fullscreen worked.");
    }
}
