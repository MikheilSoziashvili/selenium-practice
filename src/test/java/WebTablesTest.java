import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebTablesTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("http://techcanvass.com/Examples/webtable.html");
        driver.manage().window().maximize();

        final String beforeXPATH = "//*[@id='t01']//tr[";
        String afterXPATH = "]/td[1]";

        final List<WebElement> trAmount = driver.findElements(By.tagName("tr"));
        List<WebElement> tdAmount = driver.findElements(By.tagName("td"));

        for (int i = 2; i <= trAmount.size(); i++) {
            String XPATH = beforeXPATH + i + afterXPATH;
            final String companyName = driver.findElement(By.xpath(XPATH)).getText();

            if (companyName.equals("Honda")) {
                String xpath2 = beforeXPATH + i + "]/td[" + (tdAmount.size() / (trAmount.size()-1)) + "]";
                System.out.println(driver.findElement(By.xpath(xpath2)).getText());
            }
        }
    }

    @AfterClass
    public void afterTest() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
