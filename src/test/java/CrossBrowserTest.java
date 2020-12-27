import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;

public class CrossBrowserTest {
    private WebDriver driver;

    @Parameters("browser")
    @BeforeClass
    public void setup(String browser) {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
    }

    @Test
    public void firstTest() {
        driver.get("http://webdriveruniversity.com/Autocomplete-TextField/autocomplete-textfield.html");
        driver.findElement(By.id("myInput")).sendKeys("a");

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebDriverWait wait = new WebDriverWait(driver, 2);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("myInputautocomplete-list"))));

        try {
            List<WebElement> options = driver.findElements(By.xpath("//div[@id='myInputautocomplete-list']/child::div/child::input"));
            for (WebElement option : options) {
                if (option.getAttribute("value").equals("Avacado")) {
                    js.executeScript("arguments[0].click()", option);
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("no such element");
        }
        //ეს ხაზი მაღლა ციკლშიც შეიძლება ეწეროს სადაც Avocado-ს ვაჭერ
        js.executeScript("arguments[0].click()", driver.findElement(By.id("submit-button")));
    }

    @Test
    public void secondTest() {
        driver.navigate().to("http://webdriveruniversity.com/To-Do-List/index.html");

        Actions act = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 2);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        final WebElement item = driver.findElement(By.xpath("//li[contains(text(),'Practice magic')]"));
        act.moveToElement(item).perform();

        wait.until(ExpectedConditions.visibilityOf(item));
        try {
            js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//li[contains(text(), 'Practice magic')]//i")));
        } catch (NoSuchElementException e) {
            System.out.println("no such element");
        }
    }

    @Test
    public void thirdTest() {
        driver.navigate().to("http://webdriveruniversity.com/Scrolling/index.html");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Actions act = new Actions(driver);

        final WebElement leftBar = driver.findElement(By.id("zone2-entries"));

        js.executeScript("arguments[0].scrollIntoView()", leftBar);
        act.moveToElement(leftBar).perform();

        System.out.println(js.executeScript("return document.querySelector('#zone2-entries').innerText;"));
    }

    @AfterClass
    public void afterTests() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
