package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ForgetPasswordPage extends BasePage {
    public ForgetPasswordPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By forgotPasswordTitle = By.cssSelector("//label[@for='ap_email' and contains(text(), 'البريد الإلكتروني أو رقم الموبايل')]"); // المساعدة الخاصة بكلمة المرور
    private final By checkWhatsappButton = By.xpath("//input[@type='submit' and @aria-labelledby='whatsapp-button-announce']");
    private final By continueButton = By.xpath("//input[@id='continue' and @type='submit']");
    private final By whatsappVerificationText = By.xpath("//span[contains(text(), 'تحقق على واتساب')]");
    // Actions
    public void assertForgetPasswordPageTitles() {
        Utility.assertThat(driver, forgotPasswordTitle, "البريد الإلكتروني أو رقم الموبايل");
    }

    public void clickContinue() {
        WebElement button = Utility.findWebElement(driver,continueButton);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

    }

    public void clickOnWhatsappVerificationNotice() {
        Utility.clickingOnElement(driver, checkWhatsappButton);
        Utility.assertThat(driver,whatsappVerificationText, "تحقق على واتساب");
    }
}
