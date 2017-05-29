package TestClasses;
import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

public class SearchTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    private By searchButton = By.cssSelector(".g-button_hoverable");
    private By searchInput = By.cssSelector(".b-search-bar__input > div > input");

    private By firstArticleInListSearchResult = By.cssSelector(".l-search__search-results-container > div:nth-child(1)");
    private By showMoreArticles = By.cssSelector(".l-search__next-button-container > div");
    private By elevenArticleInListSearchResult = By.cssSelector(".l-search__search-results-container > div:nth-child(11)");

    private By emptySearchTitle = By.cssSelector(".l-search__empty-search-title");
    private By closeSearchButton = By.cssSelector(".b-search-bar__close-button > div > div > div");
    public By searchBarHidden = By.cssSelector(".i-layout__search-bar-container_transition.i-utils__hidden");


    public SearchTest(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmptySearchTitle() {
        String str = driver.findElement(emptySearchTitle).getText();
        return str;
    }

    public String getTextInSearchResultInput() {
        String str = driver.findElement(searchInput).getAttribute("value");
        return str;
    }

    public String getClassSearchBarHidden() {
        String str = driver.findElement(searchBarHidden).getAttribute("class");
        return str;
    }

    public void sendValidSearchQuery(String textInSearch){
        driver.findElement(searchButton).click();
        driver.findElement(searchInput).sendKeys(textInSearch);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public void checkArticlesInListSearchResult(){
        driver.findElement(firstArticleInListSearchResult).click();
        driver.navigate().back();
        driver.findElement(showMoreArticles).click();
        driver.findElement(elevenArticleInListSearchResult).click();
    }

    public void sendInvalidSearchQuery(String textInSearch){
        driver.findElement(searchButton).click();
        driver.findElement(searchInput).sendKeys(textInSearch);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public void checkDeleteInSearchResultInput(String resultTextInSearch){
        driver.findElement(closeSearchButton).click();
        driver.findElement(searchInput).sendKeys(resultTextInSearch);
        driver.findElement(searchInput).sendKeys(Keys.ENTER);
    }

    public void checkCloseSearch() {
        methods = new AdditionalMethods(driver);
        driver.findElement(searchButton).click();
        methods.Wait(1000);
        driver.findElement(closeSearchButton).click();
    }
}
