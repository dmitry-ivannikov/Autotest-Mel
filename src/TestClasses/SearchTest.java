package TestClasses;
import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

/**
 * Created by Maria on 12.05.2017.
 */
public class SearchTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    private By SearchButton = By.cssSelector(".g-button_hoverable");
    private By SearchInput = By.cssSelector(".b-search-bar__input > div > input");

    private By FirstArticleInListSearchResult = By.cssSelector(".l-search__search-results-container > div:nth-child(1)");
    private By ShowMoreArticles = By.cssSelector(".l-search__next-button-container > div");
    private By ElevenArticleInListSearchResult = By.cssSelector(".l-search__search-results-container > div:nth-child(11)");

    private By EmptySearchTitle = By.cssSelector(".l-search__empty-search-title");
    private By CloseSearchButton = By.cssSelector(".b-search-bar__close-button > div > div > div");
    public By SearchBarHidden = By.cssSelector(".i-layout__search-bar-container_transition.i-utils__hidden");


    public SearchTest(WebDriver driver) {
        this.driver = driver;
    }

    public String getEmptySearchTitle() {
        String str = driver.findElement(EmptySearchTitle).getText();
        return str;
    }

    public  String GetTextInSearchResultInput() {
        String str = driver.findElement(SearchInput).getAttribute("value");
        return str;
    }

    public  String GetClassSearchBarHidden() {
        String str = driver.findElement(SearchBarHidden).getAttribute("class");
        return str;
    }


    public void SendValidSearchQuery(String strSearchInput){
        driver.findElement(SearchButton).click();
        driver.findElement(SearchInput).sendKeys(strSearchInput);
        driver.findElement(SearchInput).sendKeys(Keys.ENTER);

    }

    public void CheckArticlesInListSearchResult(){
        driver.findElement(FirstArticleInListSearchResult).click();
        driver.navigate().back();
        driver.findElement(ShowMoreArticles).click();
        driver.findElement(ElevenArticleInListSearchResult).click();

    }

    public void SendInvalidSearchQuery(String strSearchInput){
        driver.findElement(SearchButton).click();
        driver.findElement(SearchInput).sendKeys(strSearchInput);
        driver.findElement(SearchInput).sendKeys(Keys.ENTER);

    }

    public void CheckDeleteInSearchResultInput(String strSearchResultInput){
        driver.findElement(CloseSearchButton).click();
        driver.findElement(SearchInput).sendKeys(strSearchResultInput);
        driver.findElement(SearchInput).sendKeys(Keys.ENTER);
    }

    public void CheckCloseSearch() {
        methods = new AdditionalMethods(driver);
        driver.findElement(SearchButton).click();
        methods.Wait();
        driver.findElement(CloseSearchButton).click();

    }
}
