import com.inflectra.spiratest.addons.junitextension.SpiraTestCase;
import com.inflectra.spiratest.addons.junitextension.SpiraTestConfiguration;
import org.junit.jupiter.api.*;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpiraTestConfiguration(
    url = "https://rmit.spiraservice.net/",
    login = "s4005276",
    rssToken = "{70C6A183-7D62-4DD3-9BD3-07FFB013954C}",
    projectId = 706,
    releaseId = 2520,
    testSetId = 5624
)

public class Login_Signup {

    private static ChromeDriver driver;
    private static WebDriverWait wait;

    private static String email;
    private static String password;

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
    @SpiraTestCase(testCaseId = 44471)
    @DisplayName("1. Sign up")
    public void signup() {

        /*
         * Ensures that a random number is picked
         * up until 999999 and that it is always 6 digits
         * It is exclusive of next int so it'll never be 7 digits.
         */
        int randomNum = new Random().nextInt(900000) + 100000;
        email = "email" + randomNum;
        password = "password";

        wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2"))).click();

        // Inputs to show up
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username"))).sendKeys(email);
        driver.findElement(By.id("sign-password")).sendKeys(password);

        driver.findElement(By.xpath("//*[@id='signInModal']/div/div/div[3]/button[2]")).click();


        // The alert message.
        // Wait for alert to appear and confirm
        wait.until(ExpectedConditions.alertIsPresent());
        String alertText = driver.switchTo().alert().getText();
        System.out.println("Sign-up Alert: " + alertText);
        driver.switchTo().alert().accept();

        Assertions.assertTrue(alertText.contains("Sign up successful") || alertText.contains("already"),
                "Unexpected sign-up alert text: " + alertText);
    }


    @Test
    @Order(2)
    @SpiraTestCase(testCaseId = 44472)
    @DisplayName("2. Login")
    public void login() {
        wait.until(ExpectedConditions.
                elementToBeClickable(By.id("login2"))).click();

        wait.until(ExpectedConditions.
                visibilityOfElementLocated(By.id("loginusername")))
                .sendKeys(email);

        driver.findElement(By.id("loginpassword")).sendKeys(password);

        // Going for the login
        driver.findElement(By.xpath("//button[text()='Log in']")).click();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));

        WebElement welcomeUser = driver.findElement(By.id("nameofuser"));
        System.out.println("Logged in as: " + welcomeUser.getText());

        Assertions.assertTrue(welcomeUser.getText().contains(email),
                "Login failed or username mismatch.");
    }
}
