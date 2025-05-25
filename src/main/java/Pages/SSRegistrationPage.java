package Pages;

import Utilities.Utility;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SSRegistrationPage extends BasePage {

    private By screenTypeDropdown = By.id("screen_type");
    private By identifierField = By.id("identifier");
    private By passwordField = By.id("password");
    private By phoneField = By.id("phone");
    private By submitButton = By.xpath("/html/body/div[2]/main/div[4]/div/div[2]/form/div[5]/div/button");
    private By generateMyBarcode = By.cssSelector("#smartstore-phone > button");
    private By otpField = By.id("otp");
    private By firstNameField = By.id("firstname");
    private By lastNameField = By.id("lastname");
    private By submitRegistration = By.cssSelector("#smartstore-register > button");

    public SSRegistrationPage(WebDriver driver) {
        super(driver);
    }

    public void selectScreenType(String option) {
        selectFromDropdown(screenTypeDropdown, option);
    }

    public void enterIdentifier(String identifier) {
        Utility.sendData(driver, identifierField, identifier,true);
    }

    public void enterPassword(String password) {
        Utility.sendData(driver, passwordField, password,true);
    }

    public void enterPhone(String phone) {
        Utility.sendData(driver, phoneField, phone,true);
    }

    public void submitForm() {
        Utility.clickingOnElement(driver, submitButton);
    }

    public void generateMyBarcode() {
        Utility.clickingOnElement(driver, generateMyBarcode);
    }

    public void submitRegistration() {
        Utility.clickingOnElement(driver, submitRegistration);
    }

    public void enterOtp(String otp) {
        Utility.sendData(driver, otpField, otp,true);
    }

    public void enterFirstName(String firstName) {
        Utility.sendData(driver, firstNameField, firstName,true);
    }

    public void enterLastName(String lastName) {
        Utility.sendData(driver, lastNameField, lastName,true);
    }
}
