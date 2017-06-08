package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    private By headerLoginButton = By.cssSelector(".b-header__login-button");
    private By emailLoginButton = By.cssSelector(".g-auth-social__email-button");
    private By loginEmainInput = By.cssSelector(".b-auth-email__login-email-input > div > input");
    private By loginPasswordInput = By.cssSelector(".b-auth-email__login-password-input > div > input");
    private By loginEnterButton = By.cssSelector(".b-auth-email__login-button");
    private By headerUserName = By.cssSelector(".b-header__user-name");

    public LoginTest(WebDriver driver) {
        this.driver = driver;
    }

    private void insertEmailInLoginWindow(String dataInEmailInputString) {
        driver.findElement(loginEmainInput).sendKeys(dataInEmailInputString);
    }

    private void insertPasswordInLoginWindow(String dataInPasswordInputString) {
        driver.findElement(loginPasswordInput).sendKeys(dataInPasswordInputString);
    }

    private void pressInLoginButton() {
        driver.findElement(loginEnterButton).click();
    }

    public void authorisation(String dataInEmailInputString, String dataInPasswordInputString) {
        methods = new AdditionalMethods(driver);
        driver.findElement(headerLoginButton).click();
        methods.Wait(1000);
        driver.findElement(emailLoginButton).click();
        // Insert text in email input
        insertEmailInLoginWindow(dataInEmailInputString);
        // Insert text in password input
        insertPasswordInLoginWindow(dataInPasswordInputString);
        // Tap in LoginEnter button
        pressInLoginButton();
    }

    public String getHomePageDashboardName() {
        String str = driver.findElement(headerUserName).getText();
        return str;
    }
}