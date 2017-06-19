package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import java.util.concurrent.TimeUnit;

public class SubscribeNewsLetterTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    private By inputEmail = By.cssSelector("#mce-EMAIL");
    private By returnToWebsite = By.cssSelector("#templateBody > a");
    private By inputLogin = By.name("mail");
    private By submitButton = By.cssSelector("#postbut");
    private By logoButton = By.cssSelector("a");
    private By refreshButton = By.cssSelector("#click-to-refresh");
    private By openLetterButton = By.cssSelector("#mails > tbody > tr > td:nth-child(2) > a");
    private By confirmButton = By.cssSelector("tr:nth-child(2) > td > table > tbody > tr > td > a");
    private By continueButton = By.cssSelector("#templateBody > a:nth-child(4)");
    private By subjectEmail = By.cssSelector("h4");
    private By titleEmail = By.cssSelector("tr:nth-child(1) > td > table > tbody > tr > td > h1");
    private By titlePageConfirm = By.cssSelector("h1");

    public SubscribeNewsLetterTest(WebDriver driver) {
        this.driver = driver;
    }

    public void pressInReturnButton() {
        driver.findElement(returnToWebsite).click();
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

    public void subscribe(String email) {
        driver.findElement(inputEmail).click();
        driver.findElement(inputEmail).sendKeys(email);
        driver.findElement(inputEmail).sendKeys(Keys.ENTER);
    }

    public void confirmSubscribe(String login) {
        methods = new AdditionalMethods(driver);
        driver.findElement(inputLogin).click();
        driver.findElement(inputLogin).sendKeys(login);
        driver.findElement(submitButton).click();
        driver.findElement(logoButton).click();
        driver.findElement(refreshButton).click();
        methods.Wait(1500);
        driver.findElement(openLetterButton).click();
    }

    public void confirm() {
        driver.findElement(confirmButton).click();
    }

}
