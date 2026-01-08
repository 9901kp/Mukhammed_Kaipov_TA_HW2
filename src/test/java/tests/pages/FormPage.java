package tests.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class FormPage {

    WebDriver driver;
    WebDriverWait wait;

    public FormPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(xpath = "//label[text()='Male']")
    private WebElement genderMale;

    @FindBy(id = "userNumber")
    private WebElement phone;

    @FindBy(id = "submit")
    private WebElement submitButton;

    @FindBy(className = "modal-content")
    private WebElement modal;

    @FindBy(id = "closeLargeModal")
    private WebElement closeModal;

    @Step("Open practice form page")
    public void open() {
        driver.get("https://demoqa.com/automation-practice-form");
    }

    @Step("Fill form with user data: {fName} {lName} {mail} {number}")
    public void fillForm(String fName, String lName, String mail, String number) {
        firstName.sendKeys(fName);
        lastName.sendKeys(lName);
        email.sendKeys(mail);
        selectGenderMale();
        phone.sendKeys(number);
    }

    @Step("Select Male gender")
    private void selectGenderMale() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", genderMale);
    }

    @Step("Submit form")
    public void submitForm() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", submitButton);
        submitButton.click();
    }

    @Step("Get modal text")
    public String getModalText() {
        WebElement visibleModal = wait.until(ExpectedConditions.visibilityOf(modal));
        return visibleModal.getText();
    }

    @Step("Close modal")
    public void closeModal() {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true); arguments[0].click();", closeModal);
    }

}
