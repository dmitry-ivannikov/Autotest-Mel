package TestClasses;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.testng.Assert.*;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

public class SubscribeNewsLetterTest
{
    private WebDriver driver;
    private By InputEmail = By.cssSelector("#mce-EMAIL");
    //  By SendButton = By.xpath("#mc_embed_signup_scroll > div.b-subscribe-newsletter__mc-field-group > div.b-subscribe-newsletter__submit > div");
    private By ReturnToWebsite = By.cssSelector("#templateBody > a");
    private By InputLogin = By.name("mail");
    private By SubmitButton = By.cssSelector("#postbut");
    private By LogoButton = By.cssSelector("body > header > div > div > div > div.col-md-2_1.col-lg-2 > a");
    private By RefreshButton = By.cssSelector("#click-to-refresh");
    private By OpenLetterButton = By.cssSelector("#mails > tbody > tr > td:nth-child(2) > a");
    private By ConfirmButton = By.cssSelector("body > div.page-content > div > div > div.col-sm-8 > div > div > div.pm-text > div > div > center > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > a");
    private By ContinueButton = By.cssSelector("#templateBody > a:nth-child(4)");
    private By SubjectEmail = By.cssSelector("body > div.page-content > div > div > div.col-sm-8 > div > div > div.pm-info > h4");
    private By TitleEmail = By.cssSelector("body > div.page-content > div > div > div.col-sm-8 > div > div > div.pm-text > div > div > center > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > h1");
    private By TitlePageConfirm = By.cssSelector("#templateContainer > h1");

    public SubscribeNewsLetterTest(WebDriver driver) {
        this.driver = driver;
    }

    public void InsertEmail(String strInputEmail) {
        driver.findElement(InputEmail).sendKeys(strInputEmail);
    }

    public void PressInReturnButton() {
        driver.findElement(ReturnToWebsite).click();
    }

    public void InsertLogin(String strInputLogin) {
        driver.findElement(InputLogin).sendKeys(strInputLogin);
    }

    public void PressInContinueButton() {
        driver.findElement(ContinueButton).click();
    }

    public String getSubjectName() {
        String str = driver.findElement(SubjectEmail).getText();
        return str;
    }

    public  String getTitleName() {
        String str = driver.findElement(TitleEmail).getText();
        return str;
    }
    public  String getConfirmPageName() {
        String str = driver.findElement(TitlePageConfirm).getText();
        return str;
    }

    public void Subscribe(String strInputEmail) {
        driver.findElement(InputEmail).click();
        this.InsertEmail(strInputEmail);
        driver.findElement(InputEmail).sendKeys(Keys.ENTER);
    }

    public void ConfirmSubscribe(String strInputLogin) {
        driver.findElement(InputLogin).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        this.InsertLogin(strInputLogin);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.findElement(SubmitButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.findElement(LogoButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.findElement(RefreshButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(OpenLetterButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    public void Confirm() {
        driver.findElement(ConfirmButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

}
