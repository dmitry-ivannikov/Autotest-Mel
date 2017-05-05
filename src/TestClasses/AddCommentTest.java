package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCommentTest {
    WebDriver driver;
    // Main page
    private By HeaderMainButton = By.cssSelector(".g-list__value");
    // Article
    private By Article = By.cssSelector(".b-pb-cover__title");
    // Comment field
    private By FieldForComment = By.cssSelector(".g-textarea__textarea");
    // Public Button
    private By CommentPublicButton = By.cssSelector(".b-reply-form__send-button");
    // Answer Button
    private By AnswerButton = By.cssSelector(".b-simple-comment__replier");
    // Field Answer on comment
    private By FieldAnswerComment = By.cssSelector(".b-reply-form__input > div > textarea");
    // Button public answer on comment
    private By AnswerPublicButton = By.cssSelector(".b-reply-form__send-button");
    // Button delete answer
    private By DeleteAnswerComment = By.cssSelector(".b-simple-comment__remover");
    // Button delete comment
    private By DeleteComment = By.cssSelector(".b-simple-comment__remover");

    public AddCommentTest(WebDriver driver) {
        this.driver = driver;
    }

    private void InsertComment(String strComment) {
        driver.findElement(FieldForComment).click();
        driver.findElement(FieldForComment).sendKeys(strComment);
    }

    private void InsertAnswerComment(String strAnswerComment) {
        driver.findElement(FieldAnswerComment).click();
        driver.findElement(FieldAnswerComment).sendKeys(strAnswerComment);
    }

    public void Wait() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void InsertAndAddComment(String strComment, String strAnswerComment) {
        driver.findElement(HeaderMainButton).click();
        driver.findElement(Article).click();
        InsertComment(strComment);
        driver.findElement(CommentPublicButton).click();
        Wait();
        driver.findElement(AnswerButton).click();
       // Wait();
       // InsertAnswerComment(strAnswerComment);
       // Wait();
      //  driver.findElement(AnswerPublicButton).click();
      //  Wait();

    }

    public void DeleteComment() {
      //  driver.findElement(DeleteAnswerComment).click();
        driver.findElement(DeleteComment).click();
    }
}
