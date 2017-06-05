package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.lang.*;

public class RegistrationTest {
    private WebDriver driver;
    AdditionalMethods methods;

    public By headerLoginButton = By.cssSelector(".b-header__login-button");
    private By emailLoginButton = By.cssSelector(".g-auth-social__email-button");
    // Registration window
    private By registrationWindow = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By registrationFirstNameInput = By.cssSelector(".b-auth-email__first-name-input > div > input");
    private By registrationLastNameInput = By.cssSelector(".b-auth-email__last-name-input > div > input");
    private By registrationEmailInput = By.cssSelector(".b-auth-email__reg-email-input > div > input");
    private By registrationPasswordInput = By.cssSelector(".b-auth-email__reg-password-input > div > input");
    private By registrationCheckBox = By.cssSelector(".b-auth-email__accept-checkbox > div > div.b-checkbox__icon");
    private By registrationEmailButton = By.cssSelector(".b-auth-email__registration-button");
    private By headerUserName = By.cssSelector(".b-header__user-name");

    public RegistrationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void pressInRegistrationButton(){
        driver.findElement(registrationWindow).click();
    }

    private void insertFirstNameRegistration(String name) {
        driver.findElement(registrationFirstNameInput).sendKeys(name);
    }

    private void insertLastNameRegistration(String surname) {
        driver.findElement(registrationLastNameInput).sendKeys(surname);
    }

    private void insertEmailRegistration(String strEmailInput) {
        driver.findElement(registrationEmailInput).sendKeys(strEmailInput);
    }

    private void insertPasswordRegistration(String password) {
        driver.findElement(registrationPasswordInput).sendKeys(password);
    }

    private void pressCheckBoxRegistration() {
        driver.findElement(registrationCheckBox).click();
    }

    private void pressEmailRegistratinButton() {
        driver.findElement(registrationEmailButton).click();
    }

    // Registration of the user with enter button
    public void firstUserRegistration(String firstName, String lastName, String email, String password) {
        methods = new AdditionalMethods(driver);

        driver.findElement(headerLoginButton).click();
        methods.Wait(100);
        driver.findElement(emailLoginButton).click();
        driver.findElement(registrationWindow).click();
        insertFirstNameRegistration(firstName);
        insertLastNameRegistration(lastName);
        insertEmailRegistration(email);
        insertPasswordRegistration(password);
        pressCheckBoxRegistration();
        pressEmailRegistratinButton();
    }

    // Method for clear fields
    private void clearRegistrationInputFields() {
        driver.findElement(registrationFirstNameInput).clear();
        driver.findElement(registrationLastNameInput).clear();
        driver.findElement(registrationEmailInput).clear();
        driver.findElement(registrationPasswordInput).clear();
    }

    // Method for registration with valid data
    public void registrationWithValidData(String firstName, String lastName, String email, String password) {
        insertFirstNameRegistration(firstName);
        insertLastNameRegistration(lastName);
        insertEmailRegistration(email);
        insertPasswordRegistration(password);
        pressCheckBoxRegistration();
        pressEmailRegistratinButton();
    }

    //contrast RegistrationWithValidData the removal of invalid data
    public void registrationWithInvalidData(String firstName, String lastName, String email, String password) {
        registrationWithValidData(firstName, lastName, email, password);
        pressInRegistrationButton();
    }

    public String getHeaderUserName() {
        String str = driver.findElement(headerUserName).getText();
        return str;
    }
}
