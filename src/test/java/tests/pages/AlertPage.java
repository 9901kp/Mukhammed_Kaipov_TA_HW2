package tests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AlertPage {

    WebDriver driver;
    WebDriverWait wait;

    public AlertPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[@href='#Textbox']")
    private WebElement textboxTab;

    @FindBy(xpath = "//button[@onclick='promptbox()']")
    private WebElement promptButton;

    @FindBy(id = "demo1")
    private WebElement resultText;

    @Step("Open alerts page")
    public void open() {
        driver.get("https://demo.automationtesting.in/Alerts.html");
    }

    @Step("Go to textbox alert tab")
    public void goToTextboxAlert() {
        textboxTab.click();
    }

    @Step("Click prompt alert button and input name: {name}")
    public void sendNameToAlert(String name) {
        promptButton.click();
        wait.until(ExpectedConditions.alertIsPresent());
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(name);
        alert.accept();
    }

    @Step("Get result text")
    public String getResultText() {
        return wait.until(ExpectedConditions.visibilityOf(resultText)).getText();
    }
}
