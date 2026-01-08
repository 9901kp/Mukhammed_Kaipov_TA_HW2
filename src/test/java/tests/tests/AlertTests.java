package tests.tests;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import tests.base.BaseTest;
import tests.pages.AlertPage;

@Epic("Alerts")
@Feature("Prompt Alert")
public class AlertTests extends BaseTest {

    @Test
    @Description("Verify alert textbox input is shown in result text")
    public void testAlertWithTextbox() {

        AlertPage alertPage = new AlertPage(driver);

        alertPage.open();
        alertPage.goToTextboxAlert();
        String name = "Mukhammed Kaipov";
        alertPage.sendNameToAlert(name);

        String resultText = alertPage.getResultText();
        takeScreenshot("Alert result with input name");

        Assert.assertTrue(resultText.contains(name));
    }
}
