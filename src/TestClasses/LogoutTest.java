package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest
{
    WebDriver driver;

    public By HeaderDropdown = By.cssSelector(".g-dropdown__opener");
    private By ExitButtonInDropdown = By.cssSelector(".b-header__logout-button");
    private By ExitWindowToAuthorisationSocial = By.cssSelector(".b-header__user-photo");

    public LogoutTest(WebDriver driver) {
        this.driver = driver;
    }

    public void ExitFromAccount() {
        driver.findElement(HeaderDropdown).click();
        driver.findElement(ExitButtonInDropdown).click();
    }

    public void ExitToAuthorisationSocial() {
        driver.findElement(HeaderDropdown).click();
        driver.findElement(ExitButtonInDropdown).click();

    }
}