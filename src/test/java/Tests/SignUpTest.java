package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import Pages.SignUpPage;
import Utilities.Utility;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignUpTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(SignUpTest.class);

    @Test(priority = 1)
    public void testSuccessfulSignUp() {
        driver.get("https://www.amazon.eg");
        SignUpPage signUpPage = new SignUpPage(driver);
        HomePage homePage = new HomePage(driver);

        log.info("Step 1: hovering to account tab");
        homePage.hoverOnAccountMenu();

        log.info("Step 2: Entering new email.");
        signUpPage.enterEmailAndContinue("Moataz" + System.currentTimeMillis() + "@gmail.com");

        log.info("Step 3: Clicking to create a new account.");
        signUpPage.clickContinueAsNewUser();

        log.info("Step 4: asserting the registration form.");
        signUpPage.assertSignUpPageTitles();

        log.info("Step 5: Filling in the registration form.");
        signUpPage.fillRegistrationForm("Moataz Mustafa", "Test.165550");

        log.info("Step 6: Submitting registration.");
        signUpPage.submitRegistration();

        log.info("Step 6: asserting captcha is displayed");
        signUpPage.assertCaptchaIsDisplayed();

        log.info("Positive Test completed successfully ✅");
        // simulate OTP input here if allowed or continue manual flow
    }

    @Test(priority = 2)
    public void testSignUpWithInvalidPhoneNumber() {
        driver.get("https://www.amazon.eg");
        SignUpPage signUpPage = new SignUpPage(driver);
        HomePage homePage = new HomePage(driver);

        log.info("Step 1: Hovering on account menu.");
        homePage.hoverOnAccountMenu();

        log.info("Step 2: Entering a new email.");
        signUpPage.enterEmailAndContinue("testInvalid" + System.currentTimeMillis() + "@gmail.com");

        log.info("Step 3: Clicking to create a new account");
        signUpPage.clickContinueAsNewUser();

        log.info("Step 4: Submitting with invalid mobile number.");
        signUpPage.fillRegistrationFormWithCustomPhone("abc123", "Moataz", "Test.123456");

        signUpPage.submitRegistration();

        log.info("Step 5: Asserting invalid phone message.");
        signUpPage.asserterrorMessage("رقم الهاتف غير صحيح");

        log.info("Negative Invalid Mobile Number Test completed successfully ✅");
    }
    @Test(priority = 3)
    public void testSignUpWithShortPasswordAndMismatch() {
        driver.get("https://www.amazon.eg");
        SignUpPage signUpPage = new SignUpPage(driver);
        HomePage homePage = new HomePage(driver);

        log.info("Step 1: Hovering on account menu.");
        homePage.hoverOnAccountMenu();

        log.info("Step 2: Entering a new email.");
        signUpPage.enterEmailAndContinue("testShortPwd" + System.currentTimeMillis() + "@gmail.com");

        log.info("Step 3: Clicking to create a new account.");
        signUpPage.clickContinueAsNewUser();

        log.info("Step 4: Submitting with short & mismatched password.");
        signUpPage.fillRegistrationForm("01155555555", "000");

        signUpPage.submitRegistration();

        log.info("Step 5: Asserting short password and mismatch messages.");
        signUpPage.assertErrorMessage("يجب أن يكون طول كلمة المرور 6 أحرف على الأقل.");

        log.info("Negative Invalid Password Test completed successfully ✅");
    }
}
