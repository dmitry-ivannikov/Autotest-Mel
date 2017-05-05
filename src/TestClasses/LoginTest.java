package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class LoginTest {

    private WebDriver driver;
    private By HeaderLoginButton = By.cssSelector(".b-header__login-button");
    private By EmailLoginButton = By.cssSelector(".g-auth-social__email-button");
    private By LoginEmainInput = By.cssSelector(".b-auth-email__login-email-input > div > input");
    private By LoginPasswordInput = By.cssSelector(".b-auth-email__login-password-input > div > input");
    private By LoginEnterButton = By.cssSelector(".b-auth-email__login-button");
    private By HeaderUserName = By.cssSelector(".b-header__user-name");

    public LoginTest(WebDriver driver) {
        this.driver = driver;
    }

    private void InsertEmailInLoginWindow(String DataInEmailInputString) {
        driver.findElement(LoginEmainInput).sendKeys(DataInEmailInputString);
    }

    private void InsertPasswordInLoginWindow(String DataInPasswordInputString) {
        driver.findElement(LoginPasswordInput).sendKeys(DataInPasswordInputString);
    }

    private void PressInLoginButton() {
        driver.findElement(LoginEnterButton).click();
    }

    public void Authorisation(String DataInEmailInputString, String DataInPasswordInputString) {
        driver.findElement(HeaderLoginButton).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(EmailLoginButton).click();
        // Insert text in email input
        InsertEmailInLoginWindow(DataInEmailInputString);
        // Insert text in password input
        InsertPasswordInLoginWindow(DataInPasswordInputString);
        // Tap in LoginEnter button
        PressInLoginButton();
    }

    public String getHomePageDashboardName() {
        String str = driver.findElement(HeaderUserName).getText();
        return str;
    }
}