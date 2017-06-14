package TestClasses;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;

public class FooterTest {

    WebDriver driver;
    private AdditionalMethods methods;
    private GetUrl getUrl;

    private By rubricHighschool = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(2)");
    private By rubricSchool = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(1)");
    // private By RubricInternationall = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(3)");
    private By rubricFun = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(3)");
    private By rubricFamily = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(4)");
    private By rubricGames = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(5)");
    private By rubricBlogs = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(6)");
    private By rubricAfisha = By.cssSelector(".i-layout > div.i-layout__footer > div > div:nth-child(1) > div > a:nth-child(7)");
    private By linkContacts = By.cssSelector(".b-footer__services > a:nth-child(2)");
    private By titleContacts = By.cssSelector(".l-contacts__content > h1");
    private By linkAdvertising = By.cssSelector(".b-footer__services > a:nth-child(3)");
    private By titleAdvertising = By.cssSelector(".l-advertising-proposal__content > h1");
    private By mediakitButton = By.cssSelector(".l-advertising-proposal__download-mediakit-button");
    private By pricelistButton = By.cssSelector(".l-advertising-proposal__download-pricelist-button");
    private By linkTermsOfUse = By.cssSelector(".b-footer__services > a:nth-child(4)");
    private By titleTermsOfUse = By.cssSelector(".b-terms-of-use__title");
    private By fbButton = By.cssSelector(".b-footer__socials > div:nth-child(1)");
    private By vkButton = By.cssSelector(".b-footer__socials > div:nth-child(2)");
    private By twitterButton = By.cssSelector(".b-footer__socials > div:nth-child(3)");
    private By okButton = By.cssSelector(".b-footer__socials > div:nth-child(4)");


    public FooterTest(WebDriver driver) {
        this.driver = driver;
    }

    private String getTitleContacts(){
        String str = driver.findElement(titleContacts).getText();
        return str;
    }

    private String getTitleTermsOfUse(){
        String str = driver.findElement(titleTermsOfUse).getText();
        return str;
    }

    private String getTitleAdvertising(){
        String str = driver.findElement(titleAdvertising).getText();
        return str;
    }

    public void checkFooterRubric(String school, String hightschool, String fun, String family, String games, String blogs, String afisha) {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        //check rubric
        driver.findElement(rubricSchool).click();
        Assert.assertEquals(driver.getCurrentUrl(), school);

        driver.findElement(rubricHighschool).click();
        Assert.assertEquals(driver.getCurrentUrl(), hightschool);

        driver.findElement(rubricFun).click();
        Assert.assertEquals(driver.getCurrentUrl(), fun);

        driver.findElement(rubricFamily).click();
        Assert.assertEquals(driver.getCurrentUrl(), family);

        driver.findElement(rubricGames).click();
        Assert.assertEquals(driver.getCurrentUrl(), games);

        driver.findElement(rubricBlogs).click();
        Assert.assertEquals(driver.getCurrentUrl(), blogs);

        driver.findElement(rubricAfisha).click();
        Assert.assertEquals(driver.getCurrentUrl(), afisha);
    }

    public void checkFooterContacts(String urlContacts, String contacts, String urlAdvertising, String advertising) {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        //check another links and social-buttons in footer
        driver.findElement(linkContacts).click();
        Assert.assertEquals(driver.getCurrentUrl(), urlContacts);
        Assert.assertEquals(getTitleContacts(), contacts);

        driver.findElement(linkAdvertising).click();
        Assert.assertEquals(driver.getCurrentUrl(), urlAdvertising);
        Assert.assertEquals(getTitleAdvertising(), advertising);
    }

    public void checkFooterMediakit(String firstMediakid, String lastMediakid) {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        driver.findElement(mediakitButton).click();
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        String parentHandle = driver.getWindowHandle();
        for (String childHandle : driver.getWindowHandles()) {
            if (!childHandle.equals(parentHandle)) {
                driver.switchTo().window(childHandle);
            }
        }

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), firstMediakid);

        driver.close();
        driver.switchTo().window(parentHandle);

        driver.findElement(pricelistButton).click();
        methods.Wait(4000);
        String parentHandle2 = driver.getWindowHandle();
        for (String childHandle : driver.getWindowHandles()) {
            if (!childHandle.equals(parentHandle2)) {
                driver.switchTo().window(childHandle);
            }
        }
        methods.Wait(4000);
        Assert.assertEquals(driver.getCurrentUrl(), lastMediakid);

        driver.close();
        driver.switchTo().window(parentHandle);
        methods.Wait(4000);
    }

    public void checkFooterLinks(String termsOfUse, String userAgreement, String facebook, String vk, String twitter, String ok){
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        driver.findElement(linkTermsOfUse).click();
        Assert.assertEquals(driver.getCurrentUrl(), termsOfUse);
        Assert.assertEquals(getTitleTermsOfUse(),userAgreement);

        driver.findElement(fbButton).click();
        methods.Wait(4000);
        Assert.assertEquals(driver.getCurrentUrl(), facebook);

        getUrl.driverGet();
        driver.findElement(vkButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), vk);

        getUrl.driverGet();
        driver.findElement(twitterButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), twitter);

        getUrl.driverGet();
        driver.findElement(okButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), ok);
    }
}

