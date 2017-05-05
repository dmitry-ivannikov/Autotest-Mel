package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadArticlesTest {
    WebDriver driver;

    private By MoreArticleButton = By.cssSelector(".b-pb-frontpage__more-publications-button_scope_mobile > div");
    // Random article
    public By Article1 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(73)");
    public By Article2 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(75)");
    public By Article3 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(97)");
    public By Article4 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(103)");
    public By Article5 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(105)");
    public By Articles = By.cssSelector(".i-layout__content");

    public DownloadArticlesTest(WebDriver driver) {
        this.driver = driver;
    }

    public void PressInArticle(By selector){
        driver.findElement(selector).click();
    }

    public void PressInArticleMore() {
        try {
            driver.findElement(MoreArticleButton).click();
        }
        catch (Exception e) {
            driver.close();
        }
    }
}
