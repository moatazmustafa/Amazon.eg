package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage {
    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    // Locators
    private final By emailInput = By.id("ap_email_login");
    private final By continueButton = By.cssSelector("input.a-button-input[type='submit'][aria-labelledby='continue-announce']");
    private final By newAccountBtn = By.cssSelector("input.a-button-input");
    private final By mobileInput = By.id("ap_phone_number");
    private final By nameInput = By.id("ap_customer_name");
    private final By passwordInput = By.id("ap_password");
    private final By rePasswordInput = By.id("ap_password_check");
    private final By verifyMobileBtn = By.id("continue");
    private final By registerPageTitle = By.cssSelector("h1.moa_desktop_signup"); // إنشاء حساب
    private final By phoneLabel = By.cssSelector("label[for='ap_phone_number']"); // رقم الموبايل
    private final By nameLabel = By.cssSelector("label[for='ap_customer_name']"); // الاسم
    private final By passwordLabel = By.cssSelector("label[for='ap_password']"); // كلمة المرور
    private final By rePasswordLabel = By.cssSelector("label[for='ap_password_check']"); // إعادة إدخال كلمة المرور
    private final By loginHint = By.cssSelector("span.a-text-bold"); // لديك حساب في أمازون؟
    private final By loginLink = By.cssSelector("a#ra-sign-in-link"); // سجل دخولك
    private final By captchaTitle = By.cssSelector("a.a-link-nav-icon > i.a-icon-logo");
    private final By MobErrorMessage = By.xpath("//div[@class='a-alert-content' and contains(text(),'رقم الهاتف غير صحيح')]");
    private final By PassErrorMessage = By.xpath("//div[@class='a-alert-content' and contains(text(),'يجب أن يكون طول كلمة المرور')]");

    // Actions
    public void enterEmailAndContinue(String email) {
        Utility.sendData(driver, emailInput, email, true);
        Utility.clickingOnElement(driver, continueButton);
    }

    public void clickContinueAsNewUser() {
        Utility.clickingOnElement(driver, newAccountBtn);
    }

    public void fillRegistrationForm(String name, String pass) {
        String randomPhone = Utility.generateRandomPhoneNumber("011");

        Utility.sendData(driver, mobileInput, randomPhone, true);
        Utility.sendData(driver, nameInput, name, true);
        Utility.sendData(driver, passwordInput, pass, true);
        Utility.sendData(driver, rePasswordInput, pass, true);
    }


    public void submitRegistration() {
        Utility.clickingOnElement(driver, verifyMobileBtn);
    }
    public void assertCaptchaIsDisplayed() {
        Utility.findWebElement(driver, captchaTitle);
    }
    public void assertSignUpPageTitles() {
        Utility.findWebElement(driver, registerPageTitle);
        Utility.assertThat(driver, registerPageTitle, "إنشاء حساب");

        Utility.findWebElement(driver, phoneLabel);
        Utility.assertThat(driver, phoneLabel, "رقم الموبايل");

        Utility.findWebElement(driver, nameLabel);
        Utility.assertThat(driver, nameLabel, "الاسم");

        Utility.findWebElement(driver, passwordLabel);
        Utility.assertThat(driver, passwordLabel, "كلمة المرور (6 أحرف على الأقل)");

        Utility.findWebElement(driver, rePasswordLabel);
        Utility.assertThat(driver, rePasswordLabel, "إعادة إدخال كلمة المرور");

        Utility.findWebElement(driver, loginHint);
        Utility.assertThat(driver, loginHint, "لديك حساب في أمازون؟");

        Utility.findWebElement(driver, loginLink);
        Utility.assertThat(driver, loginLink, "سجل دخولك");
    }
    public void fillRegistrationFormWithCustomPhone(String phone, String name, String pass) {
        Utility.sendData(driver, mobileInput, phone, true);
        Utility.sendData(driver, nameInput, name, true);
        Utility.sendData(driver, passwordInput, pass, true);
        Utility.sendData(driver, rePasswordInput, pass, true);
    }

    public void asserterrorMessage(String expectedText) {
        Utility.findWebElement(driver, MobErrorMessage);
        Utility.assertThat(driver, MobErrorMessage, expectedText);
    }
    public void assertErrorMessage(String expectedText) {
        Utility.findWebElement(driver, PassErrorMessage);
        Utility.assertThat(driver, PassErrorMessage, expectedText);
    }
}
