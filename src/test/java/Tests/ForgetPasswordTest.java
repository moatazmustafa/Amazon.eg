// ForgetPasswordTest.java
package Tests;

import BaseTest.BaseTest;
import Pages.ForgetPasswordPage;
import Pages.HomePage;
import Pages.SignInPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class ForgetPasswordTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(ForgetPasswordTest.class);

    @Test
    public void testValidForgetPasswordFlow() {
        driver.get("https://www.amazon.eg");
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = new SignInPage(driver);
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);

        log.info("Step 1: Hover to account menu and click sign in.");
        homePage.hoverOnAccountMenu();

        log.info("Step 2: Enter valid mobile/email and continue.");
        signInPage.enterEmailAndContinue("01555888986");

        log.info("Step 3: Assert login page titles.");
        signInPage.assertLoginPageTitles();

        log.info("Step 4: Asserting and click on forgot password link.");
        signInPage.clickOnForgetPassword();

        log.info("Step 5: Click continue.");
        forgetPasswordPage.clickContinue();

        log.info("Step 6: Assert WhatsApp verification page is shown and click continue.");
        forgetPasswordPage.clickOnWhatsappVerificationNotice();

        log.info("✅ Forget password test completed successfully.");
    }
}
