import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CookieTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("http://demo.guru99.com/test/cookie/selenium_aut.php");
        WebElement userName = driver.findElement(By.xpath("//input[@name='username']"));
        WebElement password = driver.findElement(By.xpath("//input[@name='password']"));
        userName.sendKeys("123");
        password.sendKeys("456");
        driver.findElement(By.xpath("//button[@name='submit']")).click();
        Cookie ck = new Cookie("Selenium","123");
        driver.manage().addCookie(ck);
//        მუშაობს თუ არა Cookie გადამოწმება
//        driver.navigate().to("http://demo.guru99.com/test/cookie/selenium_aut.php");
//        driver.manage().getCookieNamed("Selenium");
//        driver.navigate().refresh();
//        *************
        driver.manage().deleteCookieNamed("Selenium");
    }

    @AfterClass
    public void afterTests() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
