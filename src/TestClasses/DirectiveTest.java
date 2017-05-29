package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DirectiveTest {
    WebDriver driver;

    public By siteMapText = By.cssSelector(".header > span");
    public By robotTxtText = By.cssSelector("pre");
    public By rssText = By.cssSelector("pre");

    public DirectiveTest(WebDriver driver) {
        this.driver = driver;
    }

    public String getSiteMapText() {
        String str = driver.findElement(siteMapText).getText();
        return str;
    }

    public String getRobotTxtText() {
        String str = driver.findElement(robotTxtText).getText();
        return str;
    }
}
