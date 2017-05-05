package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.lang.*;

public class RegistrationTest
{
    private WebDriver driver;
    public By HeaderLoginButton = By.cssSelector(".b-header__login-button");
    private By EmailLoginButton = By.cssSelector(".g-auth-social__email-button");
    // Registration window
    private By RegistrationWindow = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By RegistrationFirstNameInput = By.cssSelector(".b-auth-email__first-name-input > div > input");
    private By RegistrationLastNameInput = By.cssSelector(".b-auth-email__last-name-input > div > input");
    private By RegistrationEmailInput = By.cssSelector(".b-auth-email__reg-email-input > div > input");
    private By RegistrationPasswordInput = By.cssSelector(".b-auth-email__reg-password-input > div > input");
    private By RegistrationCheckBox = By.cssSelector(".b-auth-email__accept-checkbox > div > div.b-checkbox__icon");
    private By RegistrationEmailButton = By.cssSelector(".b-auth-email__registration-button");
    private By HeaderUserName = By.cssSelector(".b-header__user-name");


    public RegistrationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void PressInRegistrationButton(){
        driver.findElement(RegistrationWindow).click();
    }

    private void InsertFirstNameRegistration(String strNameInput) {
        driver.findElement(RegistrationFirstNameInput).sendKeys(strNameInput);
    }

    private void InsertLastNameRegistration(String strSureNameInput) {
        driver.findElement(RegistrationLastNameInput).sendKeys(strSureNameInput);
    }

    private void InsertEmailRegistration(String strEmailInput) {
        driver.findElement(RegistrationEmailInput).sendKeys(strEmailInput);
    }

    private void InsertPasswordRegistration(String strPasswordInput) {
        driver.findElement(RegistrationPasswordInput).sendKeys(strPasswordInput);
    }

    private void PressCheckBoxRegistration() {
        driver.findElement(RegistrationCheckBox).click();
    }

    private void PressEmailRegistratinButton() {
        driver.findElement(RegistrationEmailButton).click();
    }

    // Registration of the user with enter button
    public void FirstUserRegistration(String FirstResgistrationName, String LastResgistrationName, String EmailInRegistration, String PasswordInRegistration) {
        driver.findElement(HeaderLoginButton).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(EmailLoginButton).click();
        driver.findElement(RegistrationWindow).click();
        InsertFirstNameRegistration(FirstResgistrationName);
        InsertLastNameRegistration(LastResgistrationName);
        InsertEmailRegistration(EmailInRegistration);
        InsertPasswordRegistration(PasswordInRegistration);
        PressCheckBoxRegistration();
        PressEmailRegistratinButton();
    }

    // Method for clear fields
    private void ClearRegistrationInputFields() {
        driver.findElement(RegistrationFirstNameInput).clear();
        driver.findElement(RegistrationLastNameInput).clear();
        driver.findElement(RegistrationEmailInput).clear();
        driver.findElement(RegistrationPasswordInput).clear();
    }

    // Method for registration with valid data
    public void RegistrationWithValidData(String FirstResgistrationName, String LastResgistrationName, String EmailInRegistration, String PasswordInRegistration) {
        InsertFirstNameRegistration(FirstResgistrationName);
        InsertLastNameRegistration(LastResgistrationName);
        InsertEmailRegistration(EmailInRegistration);
        InsertPasswordRegistration(PasswordInRegistration);
        PressCheckBoxRegistration();
        PressEmailRegistratinButton();
    }

    //contrast RegistrationWithValidData the removal of invalid data
    public void RegistrationWithInvalidData(String FirstResgistrationName, String LastResgistrationName, String EmailInRegistration, String PasswordInRegistration) {
        RegistrationWithValidData(FirstResgistrationName, LastResgistrationName, EmailInRegistration, PasswordInRegistration);
        PressInRegistrationButton();
    }

    public String GetHeaderUserName() {
        String str = driver.findElement(HeaderUserName).getText();
        return str;
    }

}
