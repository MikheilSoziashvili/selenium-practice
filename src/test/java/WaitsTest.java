import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.openqa.selenium.support.ui.ExpectedConditions.textToBePresentInElement;

public class WaitsTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("https://demoqa.com/progress-bar");
        driver.manage().window().maximize();
        final WebDriverWait wait = new WebDriverWait(driver, 20);
        final WebElement startButton = driver.findElement(By.id("startStopButton"));
        final WebElement finalText = driver.findElement(By.xpath("//div[@id='progressBar']/div[@role='progressbar']"));
        startButton.click();
        wait.until(textToBePresentInElement(finalText, "100%"));
        System.out.println(finalText.getText());
    }

    @AfterClass
    public void afterTests() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
