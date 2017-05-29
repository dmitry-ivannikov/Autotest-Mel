package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class SubscribeNewsLetterTest {
    private WebDriver driver;

    private By inputEmail = By.cssSelector("#mce-EMAIL");
    private By returnToWebsite = By.cssSelector("#templateBody > a");
    private By inputLogin = By.name("mail");
    private By submitButton = By.cssSelector("#postbut");
    private By logoButton = By.cssSelector("body > header > div > div > div > div.col-md-2_1.col-lg-2 > a");
    private By refreshButton = By.cssSelector("#click-to-refresh");
    private By openLetterButton = By.cssSelector("#mails > tbody > tr > td:nth-child(2) > a");
    private By confirmButton = By.cssSelector("body > div.page-content > div > div > div.col-sm-8 > div > div > div.pm-text > div > div > center > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > table > tbody > tr > td > a");
    private By continueButton = By.cssSelector("#templateBody > a:nth-child(4)");
    private By subjectEmail = By.cssSelector("body > div.page-content > div > div > div.col-sm-8 > div > div > div.pm-info > h4");
    private By titleEmail = By.cssSelector("body > div.page-content > div > div > div.col-sm-8 > div > div > div.pm-text > div > div > center > table > tbody > tr > td > table > tbody > tr:nth-child(1) > td > table > tbody > tr > td > h1");
    private By titlePageConfirm = By.cssSelector("#templateContainer > h1");

    public SubscribeNewsLetterTest(WebDriver driver) {
        this.driver = driver;
    }

    public void insertEmail(String textInEmail) {
        driver.findElement(inputEmail).sendKeys(textInEmail);
    }

    public void pressInReturnButton() {
        driver.findElement(returnToWebsite).click();
    }

    public void insertLogin(String textInLogin) {
        driver.findElement(inputLogin).sendKeys(textInLogin);
    }

    public void pressInContinueButton() {
        driver.findElement(continueButton).click();
    }

    public String getSubjectName() {
        String str = driver.findElement(subjectEmail).getText();
        return str;
    }

    public  String getTitleName() {
        String str = driver.findElement(titleEmail).getText();
        return str;
    }
    public  String getConfirmPageName() {
        String str = driver.findElement(titlePageConfirm).getText();
        return str;
    }

    public void subscribe(String textInEmail) {
        driver.findElement(inputEmail).click();
        insertEmail(textInEmail);
        driver.findElement(inputEmail).sendKeys(Keys.ENTER);
    }

    public void confirmSubscribe(String textInLogin) {
        driver.findElement(inputLogin).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        insertLogin(textInLogin);
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.findElement(submitButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.findElement(logoButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        driver.findElement(refreshButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(openLetterButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

    public void confirm() {
        driver.findElement(confirmButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
    }

}
