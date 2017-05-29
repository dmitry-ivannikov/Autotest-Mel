package AdminTestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLoginTest {
    WebDriver driver;

    private By emailInput = By.cssSelector(".g-input__input");
    private By passwordInput = By.cssSelector(".i-layout__password-input > div > input");
    private By enterButton = By.cssSelector(".i-layout__login-button");
    private By forgotPasswordButton = By.cssSelector(".i-layout__content");

    public AdminLoginTest(WebDriver driver) {
        this.driver = driver;
    }

    public void adminAuthorisation(String email, String password) {
        driver.findElement(emailInput).sendKeys(email);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(enterButton).click();
    }
}
