package AdminTestClasses;

import Helper.AdditionalMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminBlogs {
    WebDriver driver;
    AdditionalMethods methods;

    private By blogsButton = By.cssSelector(".i-layout__menu > div > div:nth-child(5)");
    private By openAtSiteButton = By.cssSelector(".b-post__button-open-at-site-container");
    private By blogTitleInSite = By.cssSelector(".b-pb-article__body > div.b-pb-article__header > h1");
    private By dropdownMenu = By.cssSelector(".g-dropdown__opener");

    private By postFutureButton = By.cssSelector(".g-dropdown__content > div > div:nth-child(1)");
    public By postBlockingButton = By.cssSelector(".g-dropdown__content > div > div:nth-child(2)");
    private By postFeedbackButton = By.cssSelector(".g-dropdown__content > div > div:nth-child(3)");

    public By flagAddToFrontPage = By.cssSelector(".b-post__status-icon-container");
    private By postBlockedModalConfirmButton = By.cssSelector(".b-post-blocked-modal__confirm-button");
    public By iconImgHiddenBlog = By.cssSelector(".b-post__status-icon-container");

    private By blogTitleInAdmin = By.cssSelector(".b-post__title");


    public AdminBlogs(WebDriver driver) {
        this.driver = driver;
    }

    public void clickInBlogsButton(){
        driver.findElement(blogsButton).click();
    }

    public String getPostTitleInAdmin(){
        String str = driver.findElement(blogTitleInAdmin).getText();
        return str;
    }

    public String getPostTitleInSite(){
        String str = driver.findElement(blogTitleInSite).getText();
        return str;
    }

    public void comprasionTitleBlogs(String firstBlog, String secondBlog){
        if(!(firstBlog.equals(secondBlog))){
            Assert.fail("The blog isn`t displayed on the website");
        }
    }

    public void clickInOpenAtSiteButton(){
        driver.findElement(openAtSiteButton).click();
    }

    public void clickInDropDownMenu(){
        driver.findElement(dropdownMenu).click();
    }

    public void clickInPostFutureButton(){
        driver.findElement(postFutureButton).click();
    }

    public void clickInPostBlockingButton(){
        methods = new AdditionalMethods(driver);
        driver.findElement(postBlockingButton).click();
        methods.Wait(4000);
        driver.findElement(postBlockedModalConfirmButton).click();
    }

    public void clickInPostFeedbackButton(){
        driver.findElement(postFeedbackButton).click();
    }
}
