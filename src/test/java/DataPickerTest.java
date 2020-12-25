import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class DataPickerTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("https://jqueryui.com/datepicker/");

        final WebElement frame = driver.findElement(By.xpath("//iframe[@class='demo-frame']"));
        WebDriverWait wait = new WebDriverWait(driver,2);

        try {
            wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frame));
        } catch (NoSuchFrameException e) {
            System.out.println("no such frame");
        }

        final WebElement datepicker = driver.findElement(By.id("datepicker"));
        datepicker.sendKeys("");
        final WebElement actualDate = driver.findElement(By.id("ui-datepicker-div"));
        wait.until(ExpectedConditions.visibilityOf(actualDate));
        try {
            driver.findElement(By.xpath("//a[@class='ui-datepicker-prev ui-corner-all' and @title='Prev']")).click();

            List<WebElement> visibleDates = driver.findElements(By.xpath("//table[@class='ui-datepicker-calendar']//td"));

            for (WebElement element:visibleDates) {
                final String date = element.getText();

                if (date.equalsIgnoreCase("30")) {
                    element.click();
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("no such element");
        }
    }

    @AfterClass
    public void afterTests() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}