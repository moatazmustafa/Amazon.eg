package Tests;

import BaseTest.BaseTest;
import Pages.HomePage;
import Pages.SignInPage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Test;

public class SignInTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(SignInTest.class);

    @Test
    public void testSuccessfulLogin() {
        driver.get("https://www.amazon.eg");
        HomePage homePage = new HomePage(driver);
        SignInPage signInPage = new SignInPage(driver);

        log.info("Step 1: hovering to account tab");
        homePage.hoverOnAccountMenu();

        log.info("Step 2: Asserting the account menu title.");
        signInPage.assertLoginPageTitle();

        log.info("Step 2: Entering mobile number or email.");
        signInPage.enterEmailAndContinue("01555888986"); // Replace with a real credential

        log.info("Step 2: Asserting the login page titles.");
        signInPage.assertLoginPageTitles();

        log.info("Step 3: Entering password.");
        signInPage.enterPasswordAndLogin("111111"); // Replace with real password

        log.info("Step 4: Asserting successful login.");
        signInPage.assertvalidLogInMessage(); // Replace with expected user name or greeting

        log.info("Positive Login Test completed successfully ✅");
    }
}
