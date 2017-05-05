package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

/**
 * Created by user on 05.04.2017.
 */
public class SocialNetworksAuthorisationTest {
    WebDriver driver;
    // Button Enter on main
    private By HeaderLoginButton = By.cssSelector(".g-button_border_large");
    // Button Facebook
    private By FacebookLoginButton = By.cssSelector(".g-button_fb");
    // Button Vk
    private By VkLoginButton = By.cssSelector(".g-button_vk");
    // Button Google+
   // private By GoogleLoginButton = By.cssSelector(".g-button_gp");

    // Fields for FB:
    private By FacebookEmailInput = By.cssSelector("#email");
    private By FacebookPasswordInput = By.cssSelector("#pass");
    private By FacebookEnterLoginButton = By.cssSelector("#u_0_2");

    // Fields for VK:
    private By VkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    private By VkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    private By VkEnterLoginButton = By.cssSelector("#install_allow");

    // Fields for Google+:
//    private By GoogleEmailInput = By.cssSelector("#Email");
//    private By GoogleNextButton = By.cssSelector("#next");
//    private By GooglePasswordInput = By.cssSelector("#Passwd");
//    private By GoogleEnterLoginButton = By.cssSelector("#signIn");

    private By homePageName = By.cssSelector(".b-header__user-name");


    public SocialNetworksAuthorisationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void PressInLoginButton() {
        driver.findElement(HeaderLoginButton).click();
    }

    public void PressInFbLoginButton() {
        driver.findElement(FacebookLoginButton).click();
    }

    public void PressInVkLoginButton() {
        driver.findElement(VkLoginButton).click();
    }

   // public void PressInGoogleLoginButton() {
  //      driver.findElement(GoogleLoginButton).click();
  //  }

    // FB:
    public void InsertEmailLoginInFbInput(String FacebookEmailText) {
        driver.findElement(FacebookEmailInput).sendKeys(FacebookEmailText);
    }

    public void InsertPasswordInFbInput(String FacebookPasswordText) {
        driver.findElement(FacebookPasswordInput).sendKeys(FacebookPasswordText);
    }

    public void PressInFbButton() {
        driver.findElement(FacebookEnterLoginButton).click();
    }

    // Vk:
    public void InsertEmailLoginInVkInput(String VkEmailText) {
        driver.findElement(VkEmailInput).sendKeys(VkEmailText);
    }

    public void InsertPasswordInVkInput(String VkPasswordText) {
        driver.findElement(VkPasswordInput).sendKeys(VkPasswordText);
    }

    public void PressInVkButton() {
        driver.findElement(VkEnterLoginButton).click();
    }

    // Google+
//    public void InsertInGEmailInput(String GoogleEmailText) {
//        driver.findElement(GoogleEmailInput).sendKeys(GoogleEmailText);
//    }

//    public void PressInGoogleNextButton() {
//        driver.findElement(GoogleNextButton).click();
//    }
//
//    public void InsertInGPasswordInput(String GooglePasswordText) {
//        driver.findElement(GooglePasswordInput).sendKeys(GooglePasswordText);
//    }
//
//    public void PressInGoogleButton() {
//        driver.findElement(GoogleEnterLoginButton).click();
//    }


    public void FacebookAuthorisation(String FacebookEmailText, String FacebookPasswordText) {
        PressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        PressInFbLoginButton();

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

        InsertEmailLoginInFbInput(FacebookEmailText);
        InsertPasswordInFbInput(FacebookPasswordText);
        PressInFbButton();
        driver.switchTo().window(parentWindowId);
    }

    public void VkAuthorisation(String strVkEmailInput, String strVkPasswordInput) {
        PressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set <String> oldWindowsSet = driver.getWindowHandles();

        PressInVkLoginButton();

        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>() {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindosSet = webDriver.getWindowHandles();
                newWindosSet.removeAll(oldWindowsSet);
                return newWindosSet.size() > 0 ?
                        newWindosSet.iterator().next() : null;
            }
        });

        driver.switchTo().window(newWindos);

        InsertEmailLoginInVkInput(strVkEmailInput);
        InsertPasswordInVkInput(strVkPasswordInput);
        PressInVkButton();
        driver.switchTo().window(parentWindowId);
    }


//    public void GoogleAuthorisation(String strGEmailInput, String strGPasswordInput) {
//        PressInLoginButton();
//
//        String parentWindowId = driver.getWindowHandle();
//        final Set <String> oldWindowsSet = driver.getWindowHandles();
//
//        PressInGoogleLoginButton();
//
//        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>() {
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
//
//        InsertInGEmailInput(strGEmailInput);
//        PressInGoogleNextButton();
//        InsertInGPasswordInput(strGPasswordInput);
//        PressInGoogleButton();
//        driver.switchTo().window(parentWindowId);
//    }

    public String getHomePageDashboardName()
    {
        String str = driver.findElement(homePageName).getText();
        return str;
    }
}
