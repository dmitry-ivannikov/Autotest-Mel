package AdminTestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminLogoutTest {
    WebDriver driver;

    private By ExitButton = By.cssSelector(".b-header__logout-button");

    public AdminLogoutTest(WebDriver driver) {
        this.driver = driver;
    }

    public void AdminLogout() {
        driver.findElement(ExitButton).click();
    }
}
