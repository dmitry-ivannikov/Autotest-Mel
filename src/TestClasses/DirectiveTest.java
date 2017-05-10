package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DirectiveTest {
    WebDriver driver;

    public By SiteMapText = By.cssSelector(".header > span");
    public By RobotTxtText = By.cssSelector("pre");
    public By RSSText = By.cssSelector("pre");

    public DirectiveTest(WebDriver driver) {
        this.driver = driver;
    }

    public String GetSiteMapText() {
        String str = driver.findElement(SiteMapText).getText();
        return str;
    }

    public String GetRobotTxtText() {
        String str = driver.findElement(RobotTxtText).getText();
        return str;
    }
}
