package AdminTestClasses;


import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.awt.*;
import java.awt.event.KeyEvent;

public class AdminAddingPublicationTest {
    WebDriver driver;
    private AdditionalMethods methods;

    // Main tab
    private By NewPublicationButton = By.cssSelector(".i-layout__new-publication-button");
    private By PublicationTitle = By.cssSelector(".b-main-tab__title-textarea > div > textarea");
    private By PublicationSubtitle = By.cssSelector(".b-main-tab__subtitle-textarea > div > textarea");
    private By PublicationAuthorInput = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By PublicationAuthor = By.cssSelector("body > div.i-layout__content > div > div > div.b-publication__tab > div > div:nth-child(2) > div > div.b-main-tab__labeled-control.b-main-tab__labeled-control_author > div.b-main-tab__author-select > div > div.b-pb-suggest__output-agent > div > div:nth-child(1) > span");
    private By PublicationAnnouncement = By.cssSelector(".b-main-tab__announcement-textarea > div > textarea");
    private By PublicationTagOnTheCover = By.cssSelector(".b-main-tab__main-tag-select > div > div.b-pb-suggest__emitter > div > input");
    private By PublicationAddingTag = By.cssSelector(".b-main-tab__tag-select > div > div.b-pb-suggest__emitter > div > input");
    private By PublicationTextBlock = By.cssSelector("#cke_1_contents > iframe");
    // Tab covers
    private By PublicationCovers = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By PublicationCoverInArticle = By.cssSelector(".b-cover-uploader__content");
    private By PublicationCoverOnTheMainPage = By.cssSelector(".b-tile__click-receiver");
    private By PublicationCoverAdditionalFormats = By.cssSelector(".b-cover-manager__mobile-format > div.b-cover-manager__format-preview > div > div.b-cover-uploader__content");
    private By PublicationShowPreviewButton = By.cssSelector(".b-control-panel-draft__middle-column > div > div");
    // Preview page
    private By PublicationPreviewTitle = By.cssSelector(".b-pb-cover__content > div > h1");
    private By PublicationPreviewSubtitle = By.cssSelector(".b-pb-cover__subtitle");
    private By PublicationPreviewText = By.cssSelector(".b-pb-publication-body_pablo > p");
    private By PublicationPreviewAddingTag = By.cssSelector(".b-tag-list__tag-list > a > div > span");
    // Publication buttons
    private By PublicationButtion = By.cssSelector(".b-control-panel-draft__publish-button > div");
    private By PublicationConfirmButton = By.cssSelector(".b-confirm-modal__confirm-button > div");
    // Draft page
    private By DraftButton = By.cssSelector(".i-layout__menu > div > div:nth-child(2)");
    private By DraftPublication = By.cssSelector(".i-control_pablo_mel_admin");
    // Tab Settings
    private By PublicationSettings = By.cssSelector(".g-tab__tabs > div:nth-child(3)");
    private By PublicationUrl = By.cssSelector(".g-input__prefix");


    public AdminAddingPublicationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void FillingFields(String title, String subtitle, String author, String announcement, String covertag, String addingtag, String textblock) {
        methods = new AdditionalMethods(driver);
        driver.findElement(NewPublicationButton).click();
        driver.findElement(PublicationTitle).sendKeys(title);
        driver.findElement(PublicationSubtitle).sendKeys(subtitle);
        driver.findElement(PublicationAuthorInput).sendKeys(author);
        driver.findElement(PublicationAuthor).click();
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

    public void AddingCovers() {
        methods = new AdditionalMethods(driver);
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,-550)", "");
        driver.findElement(PublicationCovers).click();
        methods.Wait();
        driver.findElement(PublicationCoverInArticle).click();
        methods.Wait();
        methods.ImgageDownload();
        methods.Wait();
        driver.findElement(PublicationCoverOnTheMainPage).click();
        methods.Wait();
        methods.ImgageDownload();
        methods.Wait();
        jse1.executeScript("scroll(0,550)", "");
        driver.findElement(PublicationCoverAdditionalFormats).click();
        methods.Wait();
        methods.ImgageDownload();
        methods.Wait();
    }

    public void ClickInPublicationSettings() {
        driver.findElement(PublicationSettings).click();
    }

    public StringBuffer getPublicationUrl() {
        StringBuffer str = new StringBuffer(driver.findElement(PublicationUrl).getText());
        str.delete(0,6);
        return str;
    }

    public void ShowPreviewPublication() {
        driver.findElement(PublicationShowPreviewButton).click();
        methods.Wait();
    }

    // Compare selectors on the preview page
    public String getPublicationPreviewTitle() {
        String str = driver.findElement(PublicationPreviewTitle).getText();
        return str;
    }

    public String getPublicationPreviewSubtitle() {
        String str = driver.findElement(PublicationPreviewSubtitle).getText();
        return str;
    }

    public String getPublicationPreviewText() {
        String str = driver.findElement(PublicationPreviewText).getText();
        return str;
    }

    public String getPublicationPreviewAddingTag() {
        String str = driver.findElement(PublicationPreviewAddingTag).getText();
        return str;
    }

    public void ClickInDraftButton() {
        driver.findElement(DraftButton).click();
    }

    public String getDraftTitle() {
        String str = driver.findElement(PublicationTitle).getText();
        return str;
    }

    public String getDraftSubtitle() {
        String str = driver.findElement(PublicationSubtitle).getText();
        return str;
    }

    public String getDraftText() {
        String str = driver.findElement(PublicationTextBlock).getText();
        return str;
    }

    public String getDraftAddingTag() {
        String str = driver.findElement(PublicationAddingTag).getText();
        return str;
    }

    public void ClickInPublicButtons() {
        driver.findElement(PublicationButtion).click();
        methods.Wait();
        driver.findElement(PublicationConfirmButton).click();
    }
}