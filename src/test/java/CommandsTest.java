import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CommandsTest {
    private WebDriver driver;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();

        driver = new ChromeDriver();
    }

    @Test
    public void firstTest() {
        driver.get("http://the-internet.herokuapp.com/context_menu");
        driver.manage().window().maximize();
        WebElement clickEl = driver.findElement(By.id("hot-spot"));
        Actions action = new Actions(driver);
        action.contextClick(clickEl).perform();
        driver.switchTo().alert().accept();
        action.click().perform();
    }

    @Test
    public void secondTest() throws InterruptedException {
        driver.navigate().to("http://the-internet.herokuapp.com/dynamic_controls");
        driver.findElement(By.xpath("/html/body/div[2]/div/div[1]/form[2]/button")).click();
        Thread.sleep(5000);
        System.out.println(driver.findElement(By.id("message")).getText());
    }

    @Test
    public void thirdTest() {
        driver.navigate().to("http://uitestpractice.com/Students/Actions");
        WebElement first = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/ol/li[1]"));
        WebElement twelfth = driver.findElement(By.xpath("/html/body/div[2]/div[1]/div[3]/ol/li[12]"));
        Actions action = new Actions(driver);
        action.moveToElement(first).clickAndHold().moveToElement(twelfth).release().build().perform();
        if (!first.getCssValue("background-color").equals("bisque")) {
            System.out.println("Background Changed");
        } else System.out.println("Not Changed");
    }

    @Test
    public void fourthTest() {
        driver.navigate().to("http://the-internet.herokuapp.com/drag_and_drop");
        WebElement aEl = driver.findElement(By.id("column-a"));
        WebElement bEl = driver.findElement(By.id("column-b"));
        final int aLocation = aEl.getLocation().x;
        final int bLocation = bEl.getLocation().x;
        if (aLocation < bLocation) {
            System.out.println("B div is located to the right of the A div");
        } else {
            System.out.println("B div is located to the left of the A div");
        }
    }
    @AfterClass
    public void afterTest() {
        driver.manage().deleteAllCookies();
        driver.quit();
    }
}
