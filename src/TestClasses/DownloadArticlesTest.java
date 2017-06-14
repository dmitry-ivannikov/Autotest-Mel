package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DownloadArticlesTest {
    WebDriver driver;

    private By moreArticleButton = By.cssSelector(".b-pb-frontpage__more-publications-button_scope_mobile > div");
    // Random article
    public By article1 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(73)");
    public By article2 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(76)");
    public By article3 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(80)");
    public By article4 = By.cssSelector(".b-pb-frontpage__grid > div:nth-child(87)");
    public By articles = By.cssSelector(".i-layout__content");

    public DownloadArticlesTest(WebDriver driver) {
        this.driver = driver;
    }

    public void pressInArticle(By selector){
        driver.findElement(selector).click();
    }

    public void pressInArticleMore() {
        driver.findElement(moreArticleButton).click();
    }
}
