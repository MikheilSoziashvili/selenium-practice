import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

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
        File file = new File("src\\test\\test");
        String finalFile1 = file.getAbsolutePath();
        final WebElement uploadButton = driver.findElement(By.id("file-submit"));
        driver.findElement(By.id("file-upload")).sendKeys(finalFile1);
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