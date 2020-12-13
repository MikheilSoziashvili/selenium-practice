import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommandTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("http://the-internet.herokuapp.com/add_remove_elements/");
        WebElement addButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/button"));

        for (int i = 0; i < 5; i++) {
            addButton.click();
        }

        final String button = driver.findElement(By.cssSelector("div #elements button.added-manually:last-child")).getText();
        System.out.println(button);
        final String button2ndWay = driver.findElement(By.cssSelector("button[class^='added']:last-child")).getText();
        System.out.println(button2ndWay);
        final String button3rdWay = driver.findElement(By.xpath("//*[contains(@class, 'manually')][contains(text(),'Delete')][last()]")).getText();
        System.out.println(button3rdWay);
    }

    @Test
    public void secondTest() {
        driver.navigate().to("http://the-internet.herokuapp.com/challenging_dom");

    }

}
