package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCommentTest {
    WebDriver driver;
    AdditionalMethods methods;

    // Main page
    private By headerMainButton = By.cssSelector(".g-list__value");
    // Article
    private By article = By.cssSelector(".b-pb-cover__title");
    // Comment field
    private By fieldForComment = By.cssSelector(".g-textarea__textarea");
    // Public Button
    private By commentPublicButton = By.cssSelector(".b-reply-form__send-button");
    // Answer Button
    private By answerButton = By.cssSelector(".b-simple-comment__replier");
    // Button delete comment
    private By deleteComment = By.cssSelector(".b-simple-comment__remover");

    public AddCommentTest(WebDriver driver) {
        this.driver = driver;
    }

    private void insertComment(String comment) {
        driver.findElement(fieldForComment).click();
        methods.Wait(500);
        driver.findElement(fieldForComment).sendKeys(comment);
    }

    public void insertAndAddComment(String comment) {
        methods = new AdditionalMethods(driver);
        driver.findElement(headerMainButton).click();
        driver.findElement(article).click();
        methods.Wait(4000);
        insertComment(comment);
        driver.findElement(commentPublicButton).click();
        driver.findElement(answerButton).click();
    }

    public void deleteComment() {
        driver.findElement(deleteComment).click();
    }
}
