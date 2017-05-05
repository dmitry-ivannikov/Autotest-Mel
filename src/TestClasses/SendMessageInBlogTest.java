package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class SendMessageInBlogTest {
    private WebDriver driver;
    private LogoutTest logout;
    private AdditionalMethods methods;

    private By WriteInBlogButton = By.cssSelector(".b-header__write-post-button_in-panel");
    private By TitleInPageWriteInBlog = By.cssSelector(".b-post-editor__title-textarea > div > textarea");
    private By SubtitleInPageWriteInBlog = By.cssSelector(".b-post-editor__subtitle-textarea > div > textarea");
    private By AnonsInPageWriteInBlog = By.cssSelector(".b-post-editor__announcement-textarea > div > textarea");
    //  By CoverButton = By.cssSelector(".b-post-editor__cover-placeholder");
    private By TextInPageWriteInBlog = By.cssSelector("#cke_1_contents > iframe");
    private By TagInPageWriteInBlog = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By PublicButton = By.cssSelector(".b-post-editor__control-panel > div > div > div");
    private By TitleTextInBlogPage = By.cssSelector(".b-pb-article__header > h1");
    private By SubtitleTextInBlogPage = By.cssSelector(".b-pb-article__header > div.b-pb-article__subtitle");
    private By TextInBlogPage = By.cssSelector("p");
    private By Image = By.cssSelector(".b-post-editor__cover-placeholder");
    private By ImageClass = By.cssSelector(".b-article-preview__clickable-area > img");
    private By MyBlogButton = By.cssSelector(".g-list_dropdown-menu-theme > a:nth-child(3) > span");

    public SendMessageInBlogTest(WebDriver driver) {
        this.driver = driver;
    }

    public void PressInBlogButton() {
        driver.findElement(WriteInBlogButton).click();
    }

    public void InsertTextInTitle(String Title) {
        driver.findElement(TitleInPageWriteInBlog).sendKeys(Title);
    }

    public void InsertTextInSubTitle(String Subtitle) {
        driver.findElement(SubtitleInPageWriteInBlog).sendKeys(Subtitle);
    }

    public void InsertTextInAnnouncement(String Announcement) {
        driver.findElement(AnonsInPageWriteInBlog).sendKeys(Announcement);
    }

    public void TextEnter(String Text) {
        driver.findElement(TextInPageWriteInBlog).click();
        driver.findElement(TextInPageWriteInBlog).sendKeys(Text);
    }

    public void InsertTextInTagField(String Tag) {
        driver.findElement(TagInPageWriteInBlog).sendKeys(Tag);
    }

    public void PressInPublicButton() {
        driver.findElement(PublicButton).click();
    }

    public void EnterBlogText(String Title, String SubTitle, String Announcement, String Text, String Tag) {
        methods = new AdditionalMethods(driver);
        PressInBlogButton();
        InsertTextInTitle(Title);
        InsertTextInSubTitle(SubTitle);
        InsertTextInAnnouncement(Announcement);
        driver.findElement(Image).click();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        methods.ImgageDownload();

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        TextEnter(Text);
        InsertTextInTagField(Tag);
        PressInPublicButton();
    }

    public String GetTitleText() {
        String str = driver.findElement(TitleTextInBlogPage).getText();
        return str;
    }

    public String GetSubtitleText() {
        String str = driver.findElement(SubtitleTextInBlogPage).getText();
        return str;
    }

    public String GetText() {
        String str = driver.findElement(TextInBlogPage).getText();
        return str;
    }

    public String GetImageClass() {
        String str = driver.findElement(ImageClass).getTagName();
        return str;
    }

    public void CheckImage() {
        logout = new LogoutTest(driver);
        driver.findElement(logout.HeaderDropdown).click();
        driver.findElement(MyBlogButton).click();
    }
}
