package AdminTestClasses;


import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AdminAddingPublicationTest {
    WebDriver driver;
    private AdditionalMethods methods;

    private By NewPublicationButton = By.cssSelector(".i-layout__new-publication-button");
    private By PublicationTitle = By.cssSelector(".b-main-tab__title-textarea > div > textarea");
    private By PublicationSubtitle = By.cssSelector(".b-main-tab__subtitle-textarea > div > textarea");
    private By PublicationAuthor = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By PublicationAnnouncement = By.cssSelector(".b-main-tab__announcement-textarea > div > textarea");
    private By PublicationTagOnTheCover = By.cssSelector(".b-main-tab__main-tag-select > div > div.b-pb-suggest__emitter > div > input");
    private By PublicationAddingTag = By.cssSelector    (".b-main-tab__tag-select > div > div.b-pb-suggest__emitter > div > input");
    private By PublicationTextBlock = By.cssSelector("#cke_1_contents > iframe");
    private By PublicationCovers = By.name("Обложки");
    private By PublicationCoverInArticle = By.cssSelector(".b-cover-uploader__content");
    private By PublicationCoverOnTheMainPage = By.cssSelector(".b-tile__click-receiver");
    private By PublicationCoverAdditionalFormats  = By.cssSelector(".b-cover-uploader__placeholder-icon");
    private By PublicationShowPreviewButton = By.cssSelector(".b-control-panel-draft__middle-column > div > div");

    public AdminAddingPublicationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void FillingFields(String title, String subtitle, String author, String announcement, String covertag, String addingtag, String textblock){
        methods = new AdditionalMethods(driver);
        driver.findElement(NewPublicationButton).click();
        driver.findElement(PublicationTitle).sendKeys(title);
        driver.findElement(PublicationSubtitle).sendKeys(subtitle);
        driver.findElement(PublicationAuthor).sendKeys(author);
        driver.findElement(PublicationAnnouncement).sendKeys(announcement);
        driver.findElement(PublicationTagOnTheCover).sendKeys(covertag);
        driver.findElement(PublicationAddingTag).sendKeys(addingtag);
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        methods.Wait();
        driver.findElement(PublicationTextBlock).click();
        driver.findElement(PublicationTextBlock).sendKeys(textblock);
    }

    public void AddingCovers(){
        methods = new AdditionalMethods(driver);
        driver.findElement(PublicationCovers).click();
        driver.findElement(PublicationCoverInArticle).click();
        methods.ImgageDownload();
        driver.findElement(PublicationCoverOnTheMainPage).click();
        methods.ImgageDownload();
        driver.findElement(PublicationCoverAdditionalFormats).click();
        methods.ImgageDownload();
    }

    public void ShowPreviewPublication(){
        driver.findElement(PublicationShowPreviewButton).click();
    }
}
