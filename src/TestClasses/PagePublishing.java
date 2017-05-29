package TestClasses;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PagePublishing {
    WebDriver driver;

    public PagePublishing(WebDriver driver) {
        this.driver = driver;
    }
    // publication page
    private By publicationTitle = By.cssSelector(".b-pb-cover__title");
    private By publicationSubtitle = By.cssSelector(".b-pb-cover__subtitle");
    private By publicationTagOnTheCover = By.cssSelector(".b-pb-cover__content > div > a > div");
    private By publicationText = By.cssSelector("p");
    private By publicationAuthor = By.cssSelector(".b-pb-author__name > a > span");
    public By publicationImage = By.cssSelector(".b-pb-article__cover > div > picture > img");
    // main page
    private By mainPagePublicationTitle = By.cssSelector(".b-pb-cover__title");
    private By mainPagePublicationSubtitle = By.cssSelector(".b-pb-cover__subtitle");
    private By mainPagePublicationCoverTag = By.cssSelector(".b-pb-cover__content > div > object > a > div");
    public By mainPagePublicationImage = By.cssSelector(".b-pb-cover__highlighting");

    public String getPublicationTitle(){
        String str = driver.findElement(publicationTitle).getText();
        return str;
    }

    public String getPublicationSubtitle(){
        String str = driver.findElement(publicationSubtitle).getText();
        return str;
    }

    public String getPublicationTagOnTheCover(){
        String str = driver.findElement(publicationTagOnTheCover).getText();
        return str;
    }

    public String getPublicationText(){
        String str = driver.findElement(publicationText).getText();
        return str;
    }

    public String getPublicationAuthor(){
        String str = driver.findElement(publicationAuthor).getText();
        return str;
    }

    public String getMainPagePublicationTitle(){
        String str = driver.findElement(mainPagePublicationTitle).getText();
        return str;
    }

    public String getMainPagePublicationSubtitle(){
        String str = driver.findElement(mainPagePublicationSubtitle).getText();
        return str;
    }

    public String getMainPagePublicationTagOnTheCover(){
        String str = driver.findElement(mainPagePublicationCoverTag).getText();
        return str;
    }
}
