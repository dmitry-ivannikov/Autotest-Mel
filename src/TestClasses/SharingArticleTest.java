package TestClasses;

import Helper.AdditionalMethods;
import Main.SiteTestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SharingArticleTest
{
    WebDriver driver;
    AdditionalMethods methods;
    SiteTestCases testCases;
    private By fbSharingButton = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_fb");
    private By vkSharingButton = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_vk");
    private By twitterSharingButton = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_tw");
    private By okSharingButton = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_ok");

    //Fb
    private By fbEmailInput = By.cssSelector("#email");
    private By fbPasswordInput = By.cssSelector("#pass");
    private By fbLoginButton = By.cssSelector("#u_0_2");

    //VK
    private By vkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    private By vkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    private By vkLoginButton = By.cssSelector("#install_allow");

    //Twitter
    private By twitterEmailInput = By.className("#update-form > div.ft > fieldset.sign-in > div.row.user");
    private By twitterPasswordInput = By.cssSelector("#password");
    private By twitterEnterButton = By.cssSelector("#update-form > div.ft > fieldset.submit > input.button.selected.submit");
    public By twitterTextArticle = By.cssSelector("#status");

    // Ok
    private By okEmailInput = By.name("fr.email");
    private By okPasswordInput = By.name("fr.password");
    private By okLoginButton = By.cssSelector(".form-actions > input");

    private By textFb = By.cssSelector("._5s6c");
    private By textVk = By.cssSelector("#share_title");
    private By textOk = By.cssSelector(".media-link_h");

    public SharingArticleTest(WebDriver driver) {
        this.driver = driver;
    }

    public void sharing(String mail, String password, String article,
                        By sharingButton, By emailInput, By passwordInput, By loginButton, By textSelecor){
        methods = new AdditionalMethods(driver);
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        driver.findElement(sharingButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        driver.findElement(emailInput).sendKeys(mail);
        driver.findElement(passwordInput).sendKeys(password);
        driver.findElement(loginButton).click();
        Assert.assertEquals(getText(textSelecor), article);
        driver.close();
        driver.switchTo().window(parentWindowId);
    }

    public void sharingFb(ArrayList<String> expectedResult){
        testCases = new SiteTestCases();
        sharing(expectedResult.get(0),expectedResult.get(1), expectedResult.get(2), fbSharingButton, fbEmailInput, fbPasswordInput, fbLoginButton, textFb);
    }

    public void sharingVk(ArrayList<String> expectedResult){
        testCases = new SiteTestCases();
        sharing(expectedResult.get(3),expectedResult.get(4), expectedResult.get(2), vkSharingButton, vkEmailInput, vkPasswordInput, vkLoginButton, textVk);
    }

    public void sharingOk(ArrayList<String> expectedResult){
        testCases = new SiteTestCases();
        sharing(expectedResult.get(5),expectedResult.get(4), expectedResult.get(2), okSharingButton, okEmailInput, okPasswordInput, okLoginButton, textOk);
    }

    public void twitterSharing(ArrayList<String> expectedResult){
        methods = new AdditionalMethods(driver);
        testCases = new SiteTestCases();
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        driver.findElement(twitterSharingButton).click();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        StringBuffer str = new StringBuffer(getText(twitterTextArticle));
        str.delete(44,112);
        Assert.assertEquals(String.valueOf(str), expectedResult.get(2));
        driver.switchTo().window(parentWindowId);
    }

    public String getText(By selector){
        String str = driver.findElement(selector).getText();
        return str;
    }
}
