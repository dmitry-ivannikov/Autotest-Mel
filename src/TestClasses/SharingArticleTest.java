package TestClasses;

import Helper.AdditionalMethods;
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
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SharingArticleTest
{
    WebDriver driver;
    AdditionalMethods methods;
    // Sharing buttons
    private By fbSharingButton = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_fb");
    private By vkSharingButton = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_vk");
    private By twitterSharingButton = By.cssSelector(".g-share__share.g-share__share_tw > div > div");
    //private By OkSharing = By.xpath("/html/body/div[2]/div[3]/div/div[3]/div/div[2]/div[1]/div[3]/div[5]/div/div/a[4]");
    //Fb
    private By fbEmailInput = By.cssSelector("#email");
    private By fbPasswordInput = By.cssSelector("#pass");
    private By fbLoginButton = By.cssSelector("#u_0_2");
    private By FbPublicButton = By.cssSelector("#u_0_1v");

    //VK
    private By vkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    private By vkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    private By vkLoginButton = By.cssSelector("#install_allow");
    private By VkPublicButton = By.cssSelector("#post_button");

    //Twitter
//    private By TwitterEmailInput = By.className("#update-form > div.ft > fieldset.sign-in > div.row.user");
//    private By TwitterPasswordInput = By.cssSelector("#password");
//    private By TwitterEnterButton = By.cssSelector("#update-form > div.ft > fieldset.submit > input.button.selected.submit");
//
//    //Ok
//    private By OkEmailInput = By.name("fr.email");
//    private By OkPasswordInput = By.name("fr.password");
//    private By OkEnterButton = By.cssSelector("#mainW > div.sharing_c > div > div > form > div.form-actions > input");
//    private By OkPublicButton =By.cssSelector("#preview_form > div.form-actions > div.jcol-r > button");

    private By textFb = By.cssSelector("._5s6c");
    private By textVk = By.cssSelector("#share_title");

    public SharingArticleTest(WebDriver driver) {
        this.driver = driver;
    }

    public void fbSharing(String textInEmail, String textInPassword, String article) {
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(fbSharingButton).click();

        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindosSet = webDriver.getWindowHandles();
                newWindosSet.removeAll(oldWindowsSet);
                return newWindosSet.size() > 0 ?
                        newWindosSet.iterator().next() : null;
            }
        });

        driver.switchTo().window(newWindos);
        driver.findElement(fbEmailInput).sendKeys(textInEmail);
        driver.findElement(fbPasswordInput).sendKeys(textInPassword);
        driver.findElement(fbLoginButton).click();
        Assert.assertEquals(getTextFb(), article);
       driver.switchTo().window(parentWindowId);
    }

    public void vkSharing(String textInEmail, String textInPassword, String article) {
//        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(vkSharingButton).click();

        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindosSet = webDriver.getWindowHandles();
                newWindosSet.removeAll(oldWindowsSet);
                return newWindosSet.size() > 0 ?
                        newWindosSet.iterator().next() : null;
            }
        });

        driver.switchTo().window(newWindos);
        driver.findElement(vkEmailInput).sendKeys(textInEmail);
        driver.findElement(vkPasswordInput).sendKeys(textInPassword);
        driver.findElement(vkLoginButton).click();
        Assert.assertEquals(getTextVk(), article);
        driver.quit();
        //driver.findElement(VkPublicButton).click();
       // driver.switchTo().window(parentWindowId);
    }

/*
    public void TwitterSharing(String strTwitterEmailInput, String strTwitterPasswordInput) {
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(TwitterSharing).click();

        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindosSet = webDriver.getWindowHandles();
                newWindosSet.removeAll(oldWindowsSet);
                return newWindosSet.size() > 0 ?
                        newWindosSet.iterator().next() : null;
            }
        });
        driver.switchTo().window(newWindos);
        driver.findElement(TwitterEmailInput).click();

        driver.findElement(TwitterEmailInput).sendKeys(Keys.SHIFT);
        driver.findElement(TwitterEmailInput).sendKeys(strTwitterEmailInput);
        driver.findElement(TwitterPasswordInput).sendKeys(strTwitterPasswordInput);
        driver.findElement(TwitterEnterButton).click();
        driver.switchTo().window(parentWindowId);
    }

    public void OkSharing(String strOkEmailInput, String strOkPasswordInput) {
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(OkSharing).click();

        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindosSet = webDriver.getWindowHandles();
                newWindosSet.removeAll(oldWindowsSet);
                return newWindosSet.size() > 0 ?
                        newWindosSet.iterator().next() : null;
            }
        });

        driver.switchTo().window(newWindos);
        driver.findElement(OkEmailInput).click();
        driver.findElement(OkEmailInput).sendKeys(strOkEmailInput);
        driver.findElement(OkPasswordInput).click();
        driver.findElement(OkPasswordInput).sendKeys(strOkPasswordInput);
        driver.findElement(OkEnterButton).click();
        driver.findElement(OkPublicButton).click();
        driver.switchTo().window(parentWindowId);
    }
*/

    public String getTextFb() {
        String str = driver.findElement(textFb).getText();
        return str;
    }

    public String getTextVk() {
        String str = driver.findElement(textVk).getText();
        return str;
    }
}
