//package steps;
//
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//public class Hooks {
//    protected static WebDriverWait wait;
//    protected static ChromeDriver driver;
//
//    public static void setup() {
//        if (driver == null) {
//            driver = new ChromeDriver();
//        }
//    }
//
//    public static void teardown() {
//        if (driver != null) {
//            driver.quit();
//        }
//    }
//}
