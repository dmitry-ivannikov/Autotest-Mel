package AdminTestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AdminSearchTest {
    WebDriver driver;

    private By searchString = By.cssSelector(".b-search-bar__input > div > input");
    private By publicationInSearch = By.cssSelector(".i-layout__search-article-list > div > div > div > a.b-article-list-item__link > div.b-article-list-item__title");

    public AdminSearchTest(WebDriver driver) {
        this.driver = driver;
    }

    public void insertText(String searchLine){
        driver.findElement(searchString).sendKeys(searchLine);
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public void clickInPublication(){
        driver.findElement(publicationInSearch).click();
    }
}
