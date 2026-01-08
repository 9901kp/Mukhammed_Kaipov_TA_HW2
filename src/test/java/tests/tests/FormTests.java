package tests.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.pages.FormPage;

@Epic("Forms")
@Feature("Practice Form")
public class FormTests extends BaseTest {

    @Test
    @Description("Verify that submitted form data is shown in modal window")
    public void testFormSubmission() {

        FormPage formPage = new FormPage(driver);

        formPage.open();
        formPage.fillForm("Mukhammed", "Kaipov", "kpv@gmail.com", "0123456789");
        formPage.submitForm();

        String modalText = formPage.getModalText();
        takeScreenshot("Modal with submitted data");

        Assert.assertTrue(modalText.contains("Mukhammed"));
        Assert.assertTrue(modalText.contains("Kaipov"));
        Assert.assertTrue(modalText.contains("kpv@gmail.com"));
        Assert.assertTrue(modalText.contains("0123456789"));

        formPage.closeModal();
    }
}
