package TestClasses;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import java.util.concurrent.TimeUnit;
/**
 * Created by Maria on 27.02.2017.
 */
public class FooterTest {

    WebDriver driver;
    private AdditionalMethods methods;
    private GetUrl getUrl;

    private By RubricHighschool = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(2)");
    private By RubricSchool = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(1)");
    // private By RubricInternationall = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(3)");
    private By RubricFun = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(3)");
    private By RubricFamily = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(4)");
    private By RubricGames = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(5)");
    private By RubricBlogs = By.cssSelector(".i-layout__footer > div > div:nth-child(1) > div > a:nth-child(6)");
    private By RubricAfisha = By.cssSelector(".i-layout > div.i-layout__footer > div > div:nth-child(1) > div > a:nth-child(7)");
    private By LinkContacts = By.cssSelector(".i-layout__footer > div >div:nth-child(2) > ul > li:nth-child(2)");
    private By TitleContacts = By.cssSelector(".l-contacts__content > h1");
    private By LinkAdvertising = By.cssSelector(".i-layout__footer > div > div:nth-child(2) > ul > li:nth-child(3)");
    private By TitleAdvertising = By.cssSelector(".l-advertising-proposal__content > h1");
    private By MediakitButton = By.cssSelector(".l-advertising-proposal__download-mediakit-button");
    private By PricelistButton = By.cssSelector(".l-advertising-proposal__download-pricelist-button");
    private By LinkTermsOfUse = By.cssSelector(".i-layout__footer > div > div:nth-child(2) > ul > li:nth-child(4)");
    private By TitleTermsOfUse = By.cssSelector(".b-terms-of-use__title");
    private By FbButton = By.cssSelector(".b-footer__content-row_buttons > ul > li:nth-child(1)");
    private By VkButton = By.cssSelector(".b-footer__content-row_buttons > ul > li:nth-child(2)");
    private By TwitterButton = By.cssSelector(".b-footer__content-row_buttons > ul > li:nth-child(3)");
    private By OkButton = By.cssSelector(".b-footer__content-row_buttons > ul > li:nth-child(4)");


    public FooterTest(WebDriver driver) {
        this.driver = driver;
    }

    private String getTitleContacts(){
        String str = driver.findElement(TitleContacts).getText();
        return str;
    }

    private String getTitleTermsOfUse(){
        String str = driver.findElement(TitleTermsOfUse).getText();
        return str;
    }

    private String getTitleAdvertising(){
        String str = driver.findElement(TitleAdvertising).getText();
        return str;
    }


    public void CheckFooter() {
        methods = new AdditionalMethods(driver);
//check rubric
        driver.findElement(RubricSchool).click();
        Assert.assertEquals(driver.getCurrentUrl(),getUrl.driverGetStr()+"rubric/school" );

        driver.findElement(RubricHighschool).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/highschool");

        driver.findElement(RubricFun).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/fun");

        driver.findElement(RubricFamily).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/family");

        driver.findElement(RubricGames).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/games");

        driver.findElement(RubricBlogs).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"blogs");

        driver.findElement(RubricAfisha).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"author/afisha-mela");
//check another links and social-buttons in footer
        driver.findElement(LinkContacts).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"page/contacts");
        Assert.assertEquals(getTitleContacts(),"Контакты");

        driver.findElement(LinkAdvertising).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"page/advertising-proposal");
        Assert.assertEquals(getTitleAdvertising(),"Реклама");

        driver.findElement(MediakitButton).click();

        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);

        String parentHandle = driver.getWindowHandle();
        for (String childHandle : driver.getWindowHandles()) {
            if (!childHandle.equals(parentHandle)) {
                driver.switchTo().window(childHandle);
            }
        }
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/s/p2rizp286zu7kcm/Mel_Mediakit.pdf?dl=0");

        driver.close();
        driver.switchTo().window(parentHandle);

        driver.findElement(PricelistButton).click();
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String parentHandle2 = driver.getWindowHandle();
        for (String childHandle : driver.getWindowHandles()) {
            if (!childHandle.equals(parentHandle2)) {
                driver.switchTo().window(childHandle);
            }
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.dropbox.com/s/zm1jzitag2umqc5/Mel_Pricelist.pdf?dl=0");

        driver.close();
        driver.switchTo().window(parentHandle);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.findElement(LinkTermsOfUse).click();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"page/terms-of-use");
        Assert.assertEquals(getTitleTermsOfUse(),"Пользовательское соглашение");

        driver.findElement(FbButton).click();
        try {
            Thread.sleep(4000);} catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.facebook.com/melfmru");

        getUrl.driverGet();
        driver.findElement(VkButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://vk.com/melfmru");

        getUrl.driverGet();
        driver.findElement(TwitterButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://twitter.com/melfmru");

        getUrl.driverGet();
        driver.findElement(OkButton).click();
        Assert.assertEquals(driver.getCurrentUrl(), "https://ok.ru/group/57557432139808");

    }
}

