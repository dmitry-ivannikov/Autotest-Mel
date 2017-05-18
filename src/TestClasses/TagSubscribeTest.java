
package TestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by kelta on 10.05.2017.
 */

public class TagSubscribeTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    //Кнопка подписки на тег на странице тега
    private By tagSubscribeButtonOff = By.cssSelector(".l-tag__tag-subscriber");
    //Кнопка перехода на страницу новоти с главной страницы
    private By newsButton = By.cssSelector(".b-pb-article-fresh__article-all-news > a");
    //Кнопка закрытия окна авторизации
    private By socialModalCloseButton = By.cssSelector(".g-modal__close-icon");
    //Кнопка отписки от тега на странице тега
    private By tagSubscribeButtonOn = By.cssSelector(".l-tag__tag-subscriber");
    //Кнопка перехода на страницу подписок пользователя
    private By mySubscribers = By.cssSelector(".b-header__main-menu > div > div > a:nth-child(2)");
    //Текст с названием тега на странице подписок пользователя
    private By mySubscribersTagName = By.cssSelector(".b-article-preview__source-title > a");
    //Заголовок на странице подписок
    private By mySubscribersTitle = By.cssSelector(".b-welcome__welcome > div");
    //Кнопка обновления списка тегов на странице подписок
    private By moreTags = By.cssSelector(".b-suggestion-feed__tags-refresh-button");
    //Первая кнопка в списке тегов на странице подписок
    private By firstTagButton = By.cssSelector(".b-suggestion-feed__tag-manager-frame > div > div > div:nth-child(1)");

    public TagSubscribeTest(WebDriver driver) {
        this.driver = driver;
    }

    public void clickOnSubscribeButtonOff() {
        driver.findElement(tagSubscribeButtonOff).click();
    }

    /*
       public void ThreadSleep()
        {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    */
    public String getTagNameFromMySubscribers() {
        String str = driver.findElement(mySubscribersTagName).getText();
        return str;
    }

    public String getTagNameButton() {
        String str = driver.findElement(tagSubscribeButtonOn).getText();
        return str;
    }

    public String getTextSubscribePage() {
        String str = driver.findElement(mySubscribersTitle).getText();
        return str;
    }

    public String getTextFirstButton() {
        String str = driver.findElement(firstTagButton).getText();
        return str;
    }

    public String getTextSecondButton() {
        String str = driver.findElement(firstTagButton).getText();
        return str;
    }

    //function to compare text between two tag buttons
    public void isStringEquals(String str1, String str2) {
        if (str1 == str2) {
            driver.quit();
        }
    }

    public void subscribeOnTagGuest() {
        methods = new AdditionalMethods(driver);
        driver.findElement(newsButton).click();
        clickOnSubscribeButtonOff();
        methods.Wait();
        driver.findElement(socialModalCloseButton).click();
    }

    public void subscribeOnTagUser() {
        clickOnSubscribeButtonOff();
        driver.findElement(mySubscribers).click();
    }

    public void unsubscribeFromTagUser() {
        driver.findElement(mySubscribersTagName).click();
        driver.findElement(tagSubscribeButtonOn).click();
    }

    public void checkUnsubscribe() {
        driver.findElement(mySubscribers).click();
    }

    public void checkTagUpdate() {
        methods = new AdditionalMethods(driver);
        getTextFirstButton();
        methods.Wait();
        driver.findElement(moreTags).click();
        getTextSecondButton();

    }
}