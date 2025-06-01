package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage extends BasePage {
    public SignInPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By emailInput = By.id("ap_email_login");
    private final By continueBtn = By.cssSelector("input.a-button-input[type='submit'][aria-labelledby='continue-announce']");
    private final By passwordInput = By.id("ap_password");
    private final By loginBtn = By.id("signInSubmit");

    private final By loginPageTitle = By.tagName("h1"); // تسجيل الدخول
    private final By passwordLabel = By.cssSelector("label[for='ap_password']"); // كلمة المرور
    private final By forgotPassLink = By.id("auth-fpp-link-bottom");
    private final By mobileErrorMessage = By.xpath("//div[@class='a-alert-content' and contains(text(),'رقم الهاتف غير صحيح')]");
    private final By successfulLogin = By.xpath("//input[@type='submit' and @value='verifyCaptcha']");

    // Actions
    public void enterEmailAndContinue(String email) {
        Utility.sendData(driver, emailInput, email, true);
        Utility.clickingOnElement(driver, continueBtn);
    }

    public void enterPasswordAndLogin(String password) {
        Utility.sendData(driver, passwordInput, password, true);
        Utility.clickingOnElement(driver, loginBtn);
    }

    public void assertLoginPageTitles() {
        Utility.findWebElement(driver, passwordLabel);
        Utility.assertThat(driver, passwordLabel, "كلمة المرور");

        Utility.findWebElement(driver, forgotPassLink);
        Utility.assertThat(driver, forgotPassLink, "هل نسيت كلمة المرور؟");
    }
    public void assertLoginPageTitle() {
        Utility.findWebElement(driver, loginPageTitle);
        Utility.assertThat(driver, loginPageTitle, "تسجيل الدخول أو إنشاء حساب");
    }

        public void assertInvalidLogInMessage(String expectedText) {
        Utility.findWebElement(driver, mobileErrorMessage);
        Utility.assertThat(driver, mobileErrorMessage, expectedText);
    }
    public void assertvalidLogInMessage() {
        Utility.findWebElement(driver, successfulLogin);
    }
    public void clickOnForgetPassword() {
        Utility.assertThat(driver, forgotPassLink, "هل نسيت كلمة المرور؟");
        Utility.clickingOnElement(driver, forgotPassLink);

    }
}
