package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AddCommentTest {
    WebDriver driver;

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
    // Field Answer on comment
    private By fieldAnswerComment = By.cssSelector(".b-reply-form__input > div > textarea");
    // Button public answer on comment
    private By answerPublicButton = By.cssSelector(".b-reply-form__send-button");
    // Button delete answer
    private By deleteAnswerComment = By.cssSelector(".b-simple-comment__remover");
    // Button delete comment
    private By deleteComment = By.cssSelector(".b-simple-comment__remover");

    public AddCommentTest(WebDriver driver) {
        this.driver = driver;
    }

    private void insertComment(String comment) {
        driver.findElement(fieldForComment).click();
        driver.findElement(fieldForComment).sendKeys(comment);
    }

    private void insertAnswerComment(String answerComment) {
        driver.findElement(fieldAnswerComment).click();
        driver.findElement(fieldAnswerComment).sendKeys(answerComment);
    }

    public void Wait() {
        try {
            Thread.sleep(7000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void insertAndAddComment(String comment, String answerComment) {
        driver.findElement(headerMainButton).click();
        driver.findElement(article).click();
        insertComment(comment);
        driver.findElement(commentPublicButton).click();
        Wait();
        driver.findElement(answerButton).click();
       // Wait();
       // InsertAnswerComment(strAnswerComment);
       // Wait();
      //  driver.findElement(AnswerPublicButton).click();
      //  Wait();
    }

    public void deleteComment() {
      //  driver.findElement(DeleteAnswerComment).click();
        driver.findElement(deleteComment).click();
    }
}
