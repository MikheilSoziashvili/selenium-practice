import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class WebFormsTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void test() {
        driver.get("http://webdriveruniversity.com/Dropdown-Checkboxes-RadioButtons/index.html");
        final WebElement selectableOption = driver.findElement(By.xpath("//option[@value='c#']"));
        selectableOption.click();

        Select select = new Select(driver.findElement(By.xpath("//select[@id='dropdowm-menu-1']")));
        WebElement option = select.getFirstSelectedOption();

        System.out.println(option.getText());

        List<WebElement> checkBoxList = driver.findElements(By.xpath("//input[@type='checkbox']"));
        for(WebElement checkBox:checkBoxList) {
            if (!checkBox.isSelected()) {
                checkBox.click();
            }
        }

        driver.findElement(By.xpath("//input[@type='radio' and @value='yellow']")).click();

        final WebElement orangeOption = driver.findElement(By.xpath("//option[@value='orange']"));
        if (!orangeOption.isEnabled()) {
            System.out.println("Not Enabled");
        } else {
            System.out.println("Enabled");
        }
    }

    @AfterClass
    public void afterTests() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
