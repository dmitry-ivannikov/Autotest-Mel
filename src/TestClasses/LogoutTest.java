package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LogoutTest
{
    WebDriver driver;

    public By headerDropdown = By.cssSelector(".g-dropdown__opener");
    private By exitButtonInDropdown = By.cssSelector(".b-header__logout-button");
    private By enterButton = By.cssSelector("body > div.i-layout > div.i-layout__header > div > div.b-header__header > div > div.b-header__action-bar > div.i-control.g-button.g-button_pablo_mel.g-button_pablo.g-button_aquamarine-dark-theme.b-header__login-button.g-button_border_large.g-button_size_medium.g-button_hoverable > span");

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

    public String checkEnterButton() {
        String str = driver.findElement(enterButton).getText();
        return str;
    }
}