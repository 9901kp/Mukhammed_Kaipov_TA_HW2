import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class AlertTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testAlertWithTextbox() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
        driver.findElement(By.xpath("//a[@href='#Textbox']")).click();
        driver.findElement(By.xpath("//button[@onclick='promptbox()']")).click();
        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        String name = "Mukhammed Kaipov";
        alert.sendKeys(name);
        alert.accept();

        WebElement result = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.id("demo1"))
        );
        String resultText = result.getText();

        Assert.assertTrue(resultText.contains(name),
                "Result text does not contain expected name!");

        wait.withTimeout(Duration.ofSeconds(10));
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
