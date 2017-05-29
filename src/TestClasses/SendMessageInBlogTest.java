package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SendMessageInBlogTest {
    private WebDriver driver;
    private LogoutTest logout;
    private AdditionalMethods methods;

    private By writeInBlogButton = By.cssSelector(".b-header__write-post-button_in-panel");
    private By titleInPageWriteInBlog = By.cssSelector(".b-post-editor__title-textarea > div > textarea");
    private By subtitleInPageWriteInBlog = By.cssSelector(".b-post-editor__subtitle-textarea > div > textarea");
    private By anonsInPageWriteInBlog = By.cssSelector(".b-post-editor__announcement-textarea > div > textarea");
    //  By CoverButton = By.cssSelector(".b-post-editor__cover-placeholder");
    private By textInPageWriteInBlog = By.cssSelector("#cke_1_contents > iframe");
    private By tagInPageWriteInBlog = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By publicButton = By.cssSelector(".b-post-editor__control-panel > div > div > div");
    private By titleTextInBlogPage = By.cssSelector(".b-pb-article__header > h1");
    private By subtitleTextInBlogPage = By.cssSelector(".b-pb-article__header > div.b-pb-article__subtitle");
    private By textInBlogPage = By.cssSelector("p");
    private By image = By.cssSelector(".b-post-editor__cover-placeholder");
    private By imageClass = By.cssSelector(".b-article-preview__clickable-area > img");
    private By myBlogButton = By.cssSelector(".g-list_dropdown-menu-theme > a:nth-child(3) > span");

    public SendMessageInBlogTest(WebDriver driver) {
        this.driver = driver;
    }

    public void pressInBlogButton() {
        driver.findElement(writeInBlogButton).click();
    }

    public void insertTextInTitle(String Title) {
        driver.findElement(titleInPageWriteInBlog).sendKeys(Title);
    }

    public void insertTextInSubTitle(String Subtitle) {
        driver.findElement(subtitleInPageWriteInBlog).sendKeys(Subtitle);
    }

    public void insertTextInAnnouncement(String Announcement) {
        driver.findElement(anonsInPageWriteInBlog).sendKeys(Announcement);
    }

    public void textEnter(String Text) {
        driver.findElement(textInPageWriteInBlog).click();
        driver.findElement(textInPageWriteInBlog).sendKeys(Text);
    }

    public void insertTextInTagField(String Tag) {
        driver.findElement(tagInPageWriteInBlog).sendKeys(Tag);
    }

    public void pressInPublicButton() {
        driver.findElement(publicButton).click();
    }

    public void enterBlogText(String Title, String SubTitle, String Announcement, String Text, String Tag) {
        methods = new AdditionalMethods(driver);
        pressInBlogButton();
        insertTextInTitle(Title);
        insertTextInSubTitle(SubTitle);
        insertTextInAnnouncement(Announcement);
        driver.findElement(image).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        methods.imgageDownload();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        textEnter(Text);
        insertTextInTagField(Tag);
        pressInPublicButton();
    }

    public String getTitleText() {
        String str = driver.findElement(titleTextInBlogPage).getText();
        return str;
    }

    public String getSubtitleText() {
        String str = driver.findElement(subtitleTextInBlogPage).getText();
        return str;
    }

    public String getText() {
        String str = driver.findElement(textInBlogPage).getText();
        return str;
    }

    public String getImageClass() {
        String str = driver.findElement(imageClass).getTagName();
        return str;
    }

    public void checkImage() {
        logout = new LogoutTest(driver);
        driver.findElement(logout.headerDropdown).click();
        driver.findElement(myBlogButton).click();
    }
}
