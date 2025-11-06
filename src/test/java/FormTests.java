import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class FormTests {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeClass
    public void setup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @Test
    public void testFormSubmission() {
        driver.get("https://demoqa.com/automation-practice-form");

        driver.findElement(By.id("firstName")).sendKeys("Mukhammed");
        driver.findElement(By.id("lastName")).sendKeys("Kaipov");
        driver.findElement(By.id("userEmail")).sendKeys("kpv@gmail.com");
        driver.findElement(By.xpath("//label[text()='Male']")).click();
        driver.findElement(By.id("userNumber")).sendKeys("0123456789");

        WebElement submit = driver.findElement(By.id("submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", submit);
        submit.click();

        WebElement modal = wait.until(
                ExpectedConditions.visibilityOfElementLocated(By.className("modal-content"))
        );

        String modalText = modal.getText();
        Assert.assertTrue(modalText.contains("Mukhammed"));
        Assert.assertTrue(modalText.contains("Kaipov"));
        Assert.assertTrue(modalText.contains("kpv@gmail.com"));
        Assert.assertTrue(modalText.contains("0123456789"));

        driver.findElement(By.id("closeLargeModal")).click();
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}
