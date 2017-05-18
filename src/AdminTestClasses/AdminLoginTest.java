package AdminTestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginTest {
    WebDriver driver;

    private By EmailInput = By.cssSelector(".g-input__input");
    private By PasswordInput = By.cssSelector(".i-layout__password-input > div > input");
    private By EnterButton = By.cssSelector(".i-layout__login-button");
    private By ForgotPasswordButton = By.cssSelector(".i-layout__content");

    public AdminLoginTest(WebDriver driver) {
        this.driver = driver;
    }

    public void AdminAuthorisation(String Email, String Password) {
        driver.findElement(EmailInput).sendKeys(Email);
        driver.findElement(PasswordInput).sendKeys(Password);
        driver.findElement(EnterButton).click();
    }
}
