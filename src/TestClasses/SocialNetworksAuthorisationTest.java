package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Set;

public class SocialNetworksAuthorisationTest {
    WebDriver driver;

    // Button Enter on main
    private By headerLoginButton = By.cssSelector(".g-button_border_large");
    // Button Facebook
    private By facebookLoginButton = By.cssSelector(".g-button_fb");
    // Button Vk
    private By vkLoginButton = By.cssSelector(".g-button_vk");
    // Button Google+
   // private By GoogleLoginButton = By.cssSelector(".g-button_gp");

    // Fields for FB:
    private By facebookEmailInput = By.cssSelector("#email");
    private By facebookPasswordInput = By.cssSelector("#pass");
    private By facebookEnterLoginButton = By.cssSelector("#u_0_2");

    // Fields for VK:
    private By vkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    private By vkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    private By vkEnterLoginButton = By.cssSelector("#install_allow");

    // Fields for Google+:
//    private By GoogleEmailInput = By.cssSelector("#Email");
//    private By GoogleNextButton = By.cssSelector("#next");
//    private By GooglePasswordInput = By.cssSelector("#Passwd");
//    private By GoogleEnterLoginButton = By.cssSelector("#signIn");

    private By homePageName = By.cssSelector(".b-header__user-name");


    public SocialNetworksAuthorisationTest(WebDriver driver) {
        this.driver = driver;
    }

    public void pressInLoginButton() {
        driver.findElement(headerLoginButton).click();
    }

    public void pressInFbLoginButton() {
        driver.findElement(facebookLoginButton).click();
    }

    public void pressInVkLoginButton() {
        driver.findElement(vkLoginButton).click();
    }

   // public void PressInGoogleLoginButton() {
  //      driver.findElement(GoogleLoginButton).click();
  //  }

    // FB:
    public void insertEmailLoginInFbInput(String facebookEmailText) {
        driver.findElement(facebookEmailInput).sendKeys(facebookEmailText);
    }

    public void insertPasswordInFbInput(String facebookPasswordText) {
        driver.findElement(facebookPasswordInput).sendKeys(facebookPasswordText);
    }

    public void pressInFbButton() {
        driver.findElement(facebookEnterLoginButton).click();
    }

    // Vk:
    public void insertEmailLoginInVkInput(String vkEmailText) {
        driver.findElement(vkEmailInput).sendKeys(vkEmailText);
    }

    public void insertPasswordInVkInput(String vkPasswordText) {
        driver.findElement(vkPasswordInput).sendKeys(vkPasswordText);
    }

    public void PressInVkButton() {
        driver.findElement(vkEnterLoginButton).click();
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


    public void facebookAuthorisation(String textInEmail, String textInPassword) {
        pressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();

        pressInFbLoginButton();

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

        insertEmailLoginInFbInput(textInEmail);
        insertPasswordInFbInput(textInPassword);
        pressInFbButton();
        driver.switchTo().window(parentWindowId);
    }

    public void vkAuthorisation(String textInEmail, String textInPassword) {
        pressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set <String> oldWindowsSet = driver.getWindowHandles();

        pressInVkLoginButton();

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

        insertEmailLoginInVkInput(textInEmail);
        insertPasswordInVkInput(textInPassword);
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
