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
    private By FbSharing = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_fb");
    private By VkSharing = By.cssSelector(".b-pb-article__sharing-panel > div > div > a.g-share__share.g-share__share_vk");
    private By TwitterSharing = By.cssSelector(".g-share__share.g-share__share_tw > div > div");
    //private By OkSharing = By.xpath("/html/body/div[2]/div[3]/div/div[3]/div/div[2]/div[1]/div[3]/div[5]/div/div/a[4]");
    //Fb
    private By FbEmailInput = By.cssSelector("#email");
    private By FbPasswordInput = By.cssSelector("#pass");
    private By FbLoginButton = By.cssSelector("#u_0_2");
    private By FbPublicButton = By.cssSelector("#u_0_1v");

    //VK
    private By VkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    private By VkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    private By VkLoginButton = By.cssSelector("#install_allow");
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

    private By TextFb = By.cssSelector("._5s6c");
    private By TextVk = By.cssSelector("#share_title");

    public SharingArticleTest(WebDriver driver) {
        this.driver = driver;
    }

    public void FbSharing(String strFbEmailInput, String strFbPasswordInput) {
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(FbSharing).click();

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
        driver.findElement(FbEmailInput).sendKeys(strFbEmailInput);
        driver.findElement(FbPasswordInput).sendKeys(strFbPasswordInput);
        driver.findElement(FbLoginButton).click();
        Assert.assertEquals(getTextFb(), "9 фраз, которые мы напрасно не говорим детям");
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//        robot.keyPress(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);

//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);

       // driver.findElement(FbPublicButton).click();
       driver.switchTo().window(parentWindowId);
    }

    public void VkSharing(String strVkEmailInput, String strVkPasswordInput) {
//        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        driver.findElement(VkSharing).click();

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
        driver.findElement(VkEmailInput).sendKeys(strVkEmailInput);
        driver.findElement(VkPasswordInput).sendKeys(strVkPasswordInput);
        driver.findElement(VkLoginButton).click();
        Assert.assertEquals(getTextVk(), "9 фраз, которые мы напрасно не говорим детям");
        driver.quit();
        //driver.findElement(VkPublicButton).click();
       // driver.switchTo().window(parentWindowId);
    }

//    public void TwitterSharing(String strTwitterEmailInput, String strTwitterPasswordInput) {
//        String parentWindowId = driver.getWindowHandle();
//        final Set<String> oldWindowsSet = driver.getWindowHandles();
//
//        driver.findElement(TwitterSharing).click();
//
//        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
//        {
//            @Override
//            public String apply(WebDriver webDriver) {
//                Set<String> newWindosSet = webDriver.getWindowHandles();
//                newWindosSet.removeAll(oldWindowsSet);
//                return newWindosSet.size() > 0 ?
//                        newWindosSet.iterator().next() : null;
//            }
//        });
//        driver.switchTo().window(newWindos);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(TwitterEmailInput).click();
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(TwitterEmailInput).sendKeys(Keys.SHIFT);
//        driver.findElement(TwitterEmailInput).sendKeys(strTwitterEmailInput);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(TwitterPasswordInput).sendKeys(strTwitterPasswordInput);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(TwitterEnterButton).click();
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.switchTo().window(parentWindowId);
//    }
//
//    public void OkSharing(String strOkEmailInput, String strOkPasswordInput) {
//        String parentWindowId = driver.getWindowHandle();
//        final Set<String> oldWindowsSet = driver.getWindowHandles();
//
//        driver.findElement(OkSharing).click();
//
//        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
//        {
//            @Override
//            public String apply(WebDriver webDriver) {
//                Set<String> newWindosSet = webDriver.getWindowHandles();
//                newWindosSet.removeAll(oldWindowsSet);
//                return newWindosSet.size() > 0 ?
//                        newWindosSet.iterator().next() : null;
//            }
//        });
//
//        driver.switchTo().window(newWindos);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(OkEmailInput).click();
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(OkEmailInput).sendKeys(strOkEmailInput);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(OkPasswordInput).click();
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(OkPasswordInput).sendKeys(strOkPasswordInput);
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(OkEnterButton).click();
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.findElement(OkPublicButton).click();
//        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
//        driver.switchTo().window(parentWindowId);
//    }

    public String getTextFb() {
        String str = driver.findElement(TextFb).getText();
        return str;
    }

    public String getTextVk() {
        String str = driver.findElement(TextVk).getText();
        return str;
    }
}
