import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FileUploadTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("http://the-internet.herokuapp.com/upload");

        final String myAbsolute = "C:\\Users\\MixeilSoziashvili\\Desktop\\selenium-homework\\";
        final String testFileLocation = "src\\test\\test";
        final String finalPath = myAbsolute + testFileLocation;
        final WebElement uploadButton = driver.findElement(By.id("file-submit"));
        driver.findElement(By.id("file-upload")).sendKeys(finalPath);
        uploadButton.click();

        try {
            uploadButton.click();
        } catch (StaleElementReferenceException e) {
            System.out.println("gamoviwvie");
        }
    }

    @AfterClass
    public void afterTests() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}