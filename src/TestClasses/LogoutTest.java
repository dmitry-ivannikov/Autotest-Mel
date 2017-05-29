package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest
{
    WebDriver driver;

    public By headerDropdown = By.cssSelector(".g-dropdown__opener");
    private By exitButtonInDropdown = By.cssSelector(".b-header__logout-button");
    private By exitWindowToAuthorisationSocial = By.cssSelector(".b-header__user-photo");

    public LogoutTest(WebDriver driver) {
        this.driver = driver;
    }

    public void exitFromAccount() {
        driver.findElement(headerDropdown).click();
        driver.findElement(exitButtonInDropdown).click();
    }

    public void exitToAuthorisationSocial() {
        driver.findElement(headerDropdown).click();
        driver.findElement(exitButtonInDropdown).click();

    }


}