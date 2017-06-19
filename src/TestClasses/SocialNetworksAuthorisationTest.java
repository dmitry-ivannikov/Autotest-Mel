package TestClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import Helper.AdditionalMethods;

import java.util.Set;

public class SocialNetworksAuthorisationTest {
    WebDriver driver;
    AdditionalMethods methods;

    // Button Enter on main
    private By headerLoginButton = By.cssSelector(".g-button_border_large");
    // Button Facebook
    private By facebookLoginButton = By.cssSelector(".g-button_fb");
    // Button Vk
    private By vkLoginButton = By.cssSelector(".g-button_vk");
    // Button Google+
    private By googleLoginButton = By.cssSelector(".g-button_gp");

    // Fields for FB:
    private By facebookEmailInput = By.cssSelector("#email");
    private By facebookPasswordInput = By.cssSelector("#pass");
    private By facebookEnterLoginButton = By.cssSelector("#u_0_2");

    // Fields for VK:
    private By vkEmailInput = By.cssSelector("#login_submit > div > div > input:nth-child(7)");
    private By vkPasswordInput = By.cssSelector("#login_submit > div > div > input:nth-child(9)");
    private By vkEnterLoginButton = By.cssSelector("#install_allow");

    // Fields for Google+:
    private By googleEmailInput = By.cssSelector("#identifierId");
    private By googleNextButton = By.cssSelector("#identifierNext");
    private By googlePasswordInput = By.cssSelector("#password > div.aCsJod.oJeWuf > div > div.Xb9hP > input");
    private By googleEnterLoginButton = By.cssSelector("#passwordNext");

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

    public void pressInGoogleLoginButton() {
        driver.findElement(googleLoginButton).click();
    }

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

    public void pressInVkButton() {
        driver.findElement(vkEnterLoginButton).click();
    }

    // Google+
    public void insertInGEmailInput(String email) {
        driver.findElement(googleEmailInput).sendKeys(email);
    }

    public void pressInGoogleNextButton() {
        driver.findElement(googleNextButton).click();
    }

    public void insertInGPasswordInput(String password) {
        driver.findElement(googlePasswordInput).sendKeys(password);
    }

    public void pressInGoogleButton() {
        driver.findElement(googleEnterLoginButton).click();
    }


    public void facebookAuthorisation(String textInEmail, String textInPassword) {
        methods = new AdditionalMethods(driver);
        pressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        methods.Wait(2000);
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
    public void facebookEnterLoginAndPassword(String textInEmail, String textInPassword){
        insertEmailLoginInFbInput(textInEmail);
        insertPasswordInFbInput(textInPassword);
        pressInFbButton();
    }


    public void vkAuthorisation(String textInEmail, String textInPassword) {
        methods = new AdditionalMethods(driver);
        pressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set <String> oldWindowsSet = driver.getWindowHandles();

        methods.Wait(300);
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
        pressInVkButton();
        driver.switchTo().window(parentWindowId);
    }

    public void vkEnterLoginAndPassword(String textInEmail, String textInPassword){
        insertEmailLoginInVkInput(textInEmail);
        insertPasswordInVkInput(textInPassword);
        pressInVkButton();
    }
    public void googleAuthorisation(String email, String password) {
        methods = new AdditionalMethods(driver);
        pressInLoginButton();

        String parentWindowId = driver.getWindowHandle();
        final Set <String> oldWindowsSet = driver.getWindowHandles();
        methods.Wait(2000);
        pressInGoogleLoginButton();

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

        insertInGEmailInput(email);
        pressInGoogleNextButton();
        insertInGPasswordInput(password);
        methods.Wait(1000);
        pressInGoogleButton();
        driver.switchTo().window(parentWindowId);
    }

    public String getHomePageDashboardName()
    {
        String str = driver.findElement(homePageName).getText();
        return str;
    }
}
