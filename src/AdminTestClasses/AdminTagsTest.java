package AdminTestClasses;

import Helper.AdditionalMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Created by Maria on 19.06.2017.
 */
public class AdminTagsTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    private By tagsTab = By.cssSelector(".i-layout__menu > div > div:nth-child(4)");
    private By addNewTag = By.cssSelector(".i-layout__content > div.i-layout__subheader > div");
    private By titleTagInput = By.cssSelector(".b-tag-dialog__name-input > div > input");
    private By urlInput = By.cssSelector(".b-tag-dialog__address-input > div > input");
    private By descriptionInput = By.cssSelector(".b-tag-dialog__description-input > div > textarea");
    private By seoTitleTag = By.cssSelector(".b-tag-dialog__title-seo-input > div > input");
    private By seoDescriptionTag = By.cssSelector(".b-tag-dialog__description-seo-input > div > textarea");
    private By saveButtonInPopup = By.cssSelector(".b-tag-dialog__save-button > div");
    //сортировка
    private By sortByNameButton = By.cssSelector(".b-table__sort-by-name-button > div");
    private By sortByPublicationsButton = By.cssSelector(".b-table__sort-by-publications-button > div");
    private By sortByPostsButton = By.cssSelector(".b-table__sort-by-posts-button > div");
    private By sortBySubscriptionsButton = By.cssSelector(".b-table__sort-by-subscriptions-button > div");

    private By firstTagInList = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(1)");
    private By secondTagInList = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(1)");
    private By firstTagPublicationsCount = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(2)");
    private By secondTagPublicationsCount = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(2)");
    private By firstTagPostsCount = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(3)");
    private By secondTagPostsCount = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(3)");
    private By firstTagSubscriptionsCount = By.cssSelector(".b-table__items > div:nth-child(1) > div:nth-child(4)");
    private By secondTagSubscriptionsCount = By.cssSelector(".b-table__items > div:nth-child(2) > div:nth-child(4)");

    private By openingTagOnSiteButton = By.cssSelector("#\\34 428 > div.b-table-row__controls > a > div");
    private By tagUrlInAdmin = By.cssSelector(".b-tag-dialog__address-input > div > span");
    //три точки, вызывающие дропдаун с кнопкой "Редактировать"
    private By editTagDropdownButton = By.cssSelector("#\\34 428 > .b-table-row__controls > div > div > .g-dropdown__opener > div");
    //кнопка "Редатировать" в дропдауне
    private By editTagButton = By.cssSelector("#\\34 428 > .b-table-row__controls > div > div > .g-dropdown__content > div");
    private By closePopupEditTagButton = By.cssSelector(".g-modal__close-icon > div");
    //сео на сайте
    private By seoTitleTagOnSite = By.cssSelector("head > meta:nth-child(24)");
    private By seoDescriptionTagOnSite = By.cssSelector("head > meta:nth-child(25)");
    private By descriptionTagOnSite = By.cssSelector(".i-layout__over-content-flow > div > div:nth-child(5)");
    //подписка на тег на сайте после регистрации
    private By subscribeOnTagButton = By.cssSelector(".l-tag__tag-subscriber > div > .b-checkbox__text > .b-checkbox__label");
    //добавление тега к посту
    private By titleTagInputInPost = By.cssSelector(".b-pb-suggest__emitter > div > input");
    private By savePostButton = By.cssSelector(".b-post-editor__control-panel > div > div > div");
    private By deleteTagInPostButton = By.cssSelector(".b-tag-manager__tags > div:nth-child(1) > div > .b-tag__remover > div");
    //добавление тега к публикации
    private By addingTagInputInPublication = By.cssSelector(".b-main-tab__tag-select > div > .b-pb-suggest__emitter > div > input");
    private By savePublicationButton = By.cssSelector(" .b-control-panel-published__save-button > div");
    private By savePublicationButtonInPopup = By.cssSelector(".b-confirm-modal__confirm-button > div");
    private By deleteTagInPublicationButton = By.cssSelector(".b-tag__icon > div");


    public AdminTagsTest(WebDriver driver) {
        this.driver = driver;
    }

    public String getTagTitle() {
        String str = driver.findElement(firstTagInList).getText();
        return str;
    }

    public String getSecondTagTitle() {
        String str = driver.findElement(secondTagInList).getText();
        return str;
    }
    public String getTagPublicationsCount() {
        String str = driver.findElement(firstTagPublicationsCount).getText();
        return str;
    }

    public String getSecondTagPublicationsCount() {
        String str = driver.findElement(secondTagPublicationsCount).getText();
        return str;
    }

    public String getFirstTagPostsCount() {
        String str = driver.findElement(firstTagPostsCount).getText();
        return str;
    }

    public String getSecondTagPostsCount() {
        String str = driver.findElement(secondTagPostsCount).getText();
        return str;
    }

    public String getFirstTagSubscriptionsCount() {
        String str = driver.findElement(firstTagSubscriptionsCount).getText();
        return str;
    }

    public String getSecondTagSubscriptionsCount() {
        String str = driver.findElement(secondTagSubscriptionsCount).getText();
        return str;
    }

    public String getSeoTitleOnSite() {
        String str = driver.findElement(seoTitleTagOnSite).getAttribute("content");
        return str;
    }

    public String getSeoDescriptionOnSite() {
        String str = driver.findElement(seoDescriptionTagOnSite).getAttribute("content");
        return str;
    }

    public String getDescriptionTagOnSite() {
        String str = driver.findElement(descriptionTagOnSite).getText();
        return str;
    }

    public String getUrlTagInAdmin() {
        StringBuffer str = new StringBuffer(driver.findElement(tagUrlInAdmin).getText());
        str.delete(0,14);
        return String.valueOf(str);
    }

    public void tagsCompareByName(String str1, String str2) {
        if (str2.compareTo(str1) < 0 ) {
            Assert.fail("Не работает сортировака по названию тегов");
        }
    }
    public void tagsCompare(int firstNumber, int lastNubmer ) {
        if ( firstNumber < lastNubmer ) {
            Assert.fail("Не работает сортировка тегов по количеству постов/публикаций/подписчиков");
        }
    }
    public void isStringEquals(String str1, String str2) {
        if (str1.equals(str2) ) {
            Assert.fail("Не увеличился счетчик постов/публикаций/подписчиков у тега");
        }
    }

    public void openTagsTab(){
        driver.findElement(tagsTab).click();
    }

    public void addNewTag(String titleTag, String url, String descriptionTag,String seoTitle, String seoDescription ) {
        driver.findElement(addNewTag).click();
        driver.findElement(titleTagInput).sendKeys(titleTag);
        driver.findElement(urlInput).sendKeys(url);
        driver.findElement(descriptionInput).sendKeys(descriptionTag);
        driver.findElement(seoTitleTag).sendKeys(seoTitle);
        driver.findElement(seoDescriptionTag).sendKeys(seoDescription);
        driver.findElement(saveButtonInPopup).click();
    }

    public void sortTagsByName(){
        driver.findElement(sortByNameButton).click();
    }

    public void sortTagsByPublications(){
        driver.findElement(sortByPublicationsButton).click();
    }

    public void sortTagsByPosts(){
        driver.findElement(sortByPostsButton).click();
    }

    public void sortTagsBySubscriptions() {
        driver.findElement(sortBySubscriptionsButton).click();
    }

    public void openTagOnSite(){
        driver.findElement(openingTagOnSiteButton).click();
    }

    public void openPopupEditingTag(){
        driver.findElement(editTagDropdownButton).click();
        driver.findElement(editTagButton).click();
    }

    public void closePopupEditTag(){
        driver.findElement(closePopupEditTagButton).click();
    }

    public void editTag(String tagDescription ){
        driver.findElement(descriptionInput).clear();
        driver.findElement(descriptionInput).sendKeys(tagDescription);
        driver.findElement(saveButtonInPopup).click();
    }

    public void subscribeOnTag(){
        driver.findElement(subscribeOnTagButton).click();
    }

    public void addNewTagInPost(String titleTag){
        methods = new AdditionalMethods(driver);
        driver.findElement(titleTagInputInPost).sendKeys(titleTag);
        driver.findElement(titleTagInputInPost).sendKeys(Keys.ENTER);
        methods.Wait(1000);
        driver.findElement(savePostButton).click();
    }

    public void addNewTagInPublication(String titleTagInPublication){
        driver.findElement(addingTagInputInPublication).sendKeys(titleTagInPublication);
        driver.findElement(addingTagInputInPublication).sendKeys(Keys.ENTER);
        driver.findElement(savePublicationButton).click();
        driver.findElement(savePublicationButtonInPopup).click();
    }

    public void deleteNewTagInPublication(){
        methods = new AdditionalMethods(driver);
        driver.findElement(deleteTagInPublicationButton).click();
        driver.findElement(savePublicationButton).click();
        methods.Wait(1000);
        driver.findElement(savePublicationButtonInPopup).click();
    }

    public void deleteNewTagInPost(){
        driver.findElement(deleteTagInPostButton).click();
        driver.findElement(savePostButton).click();
    }
}
