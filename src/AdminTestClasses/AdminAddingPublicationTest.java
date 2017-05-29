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
    private By newPublicationButton = By.cssSelector(".i-layout__new-publication-button");
    private By publicationTitle = By.cssSelector(".b-main-tab__title-textarea > div > textarea");
    private By publicationSubtitle = By.cssSelector(".b-main-tab__subtitle-textarea > div > textarea");
    private By publicationAuthorInput = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By publicationAuthor = By.cssSelector(".b-pb-suggest__output-agent > div > div:nth-child(1) > span");
    private By publicationAnnouncement = By.cssSelector(".b-main-tab__announcement-textarea > div > textarea");
    private By publicationTagOnTheCover = By.cssSelector(".b-main-tab__main-tag-select > div > div.b-pb-suggest__emitter > div > input");
    private By publicationAddingTag = By.cssSelector(".b-main-tab__tag-select > div > div.b-pb-suggest__emitter > div > input");
    private By publicationTextBlock = By.cssSelector("#cke_1_contents > iframe");
    // Tab covers
    private By publicationCovers = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By publicationCoverInArticle = By.cssSelector(".b-cover-uploader__content");
    private By publicationCoverOnTheMainPage = By.cssSelector(".b-tile__click-receiver");
    private By publicationCoverAdditionalFormats = By.cssSelector(".b-cover-manager__mobile-format > div.b-cover-manager__format-preview > div > div.b-cover-uploader__content");
    private By publicationShowPreviewButton = By.cssSelector(".b-control-panel-draft__middle-column > div > div");
    // Preview page
    private By publicationPreviewTitle = By.cssSelector(".b-pb-cover__content > div > h1");
    private By publicationPreviewSubtitle = By.cssSelector(".b-pb-cover__subtitle");
    private By publicationPreviewText = By.cssSelector(".b-pb-publication-body_pablo > p");
    // Publication buttons
    private By publicationButtion = By.cssSelector(".b-control-panel-draft__publish-button > div");
    private By publicationConfirmButton = By.cssSelector(".b-confirm-modal__confirm-button > div");
    // Tab Settings
    private By publicationSettings = By.cssSelector(".g-tab__tabs > div:nth-child(3)");
    private By publicationUrl = By.cssSelector(".b-publication-settings__address-site-input > div > span");

    public AdminAddingPublicationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void fillingFields(String title, String subtitle, String author, String announcement, String covertag, String addingtag, String textblock) {
        methods = new AdditionalMethods(driver);

        driver.findElement(publicationTitle).sendKeys(title);
        driver.findElement(publicationSubtitle).sendKeys(subtitle);
        driver.findElement(publicationAuthorInput).sendKeys(author);
        driver.findElement(publicationAuthor).click();
        driver.findElement(publicationAnnouncement).sendKeys(announcement);
        driver.findElement(publicationTagOnTheCover).sendKeys(covertag);
        driver.findElement(publicationAddingTag).sendKeys(addingtag);
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_ENTER);
        r.keyRelease(KeyEvent.VK_ENTER);
        methods.Wait(4000);
        driver.findElement(publicationTextBlock).click();
        driver.findElement(publicationTextBlock).sendKeys(textblock);

    }
    public void clickInNewPublication(){
        driver.findElement(newPublicationButton).click();
    }
    public void addingCovers() {
        methods = new AdditionalMethods(driver);
        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
        jse1.executeScript("scroll(0,-550)", "");
        driver.findElement(publicationCovers).click();
        methods.Wait(4000);
        driver.findElement(publicationCoverInArticle).click();
        methods.Wait(4000);
        methods.imgageDownload();
        methods.Wait(4000);
        driver.findElement(publicationCoverOnTheMainPage).click();
        methods.Wait(4000);
        methods.imgageDownload();
        methods.Wait(4000);
        jse1.executeScript("scroll(0,550)", "");
        driver.findElement(publicationCoverAdditionalFormats).click();
        methods.Wait(4000);
        methods.imgageDownload();
    }

    public void showPreviewPublication() {
        driver.findElement(publicationShowPreviewButton).click();
        methods.Wait(4000);
    }

    // Compare selectors on the preview page
    public String getPublicationPreviewTitle() {
        String str = driver.findElement(publicationPreviewTitle).getText();
        return str;
    }

    public String getPublicationPreviewSubtitle() {
        String str = driver.findElement(publicationPreviewSubtitle).getText();
        return str;
    }

    public String getPublicationPreviewText() {
        String str = driver.findElement(publicationPreviewText).getText();
        return str;
    }

    public String getDraftTitle() {
        String str = driver.findElement(publicationTitle).getText();
        return str;
    }

    public String getDraftSubtitle() {
        String str = driver.findElement(publicationSubtitle).getText();
        return str;
    }

    public void clickInPublicButtons() {
        driver.findElement(publicationButtion).click();
        methods.Wait(4000);
        driver.findElement(publicationConfirmButton).click();
    }

    public void clickInPublicationSettings(){
        driver.findElement(publicationSettings).click();
    }

    public String getPublicationUrl() {
        StringBuffer str = new StringBuffer(driver.findElement(publicationUrl).getText());
        str.delete(0,6);
        return String.valueOf(str);
    }
}