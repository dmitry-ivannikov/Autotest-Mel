package AdminTestClasses;


import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminFrontPageTest {
    WebDriver driver;

    private By frontPageButton = By.cssSelector(".b-header__menu > div > div:nth-child(2)");
    private By pageSwitcherIcon = By.cssSelector(".b-switcher__icon > div");
    private By titleMainPublication = By.cssSelector(".b-pb-cover__content > div > div.b-pb-cover__title");
    private By publicationToAdd = By.cssSelector(".b-switcher__title");
    private By frontPageSaveButton = By.cssSelector(".i-layout__save-button > div");

    public AdminFrontPageTest(WebDriver driver) {
        this.driver = driver;
    }

    public void clickInFrontPageButton(){
        driver.findElement(frontPageButton).click();
    }

    public void clickInPublicationSwitcher(){
        driver.findElement(pageSwitcherIcon).click();
    }

    public String getTitleMainPublication(){
        String str = driver.findElement(titleMainPublication).getText();
        return str;
    }

    public String getTitlePublicationToAdd(){
        String str = driver.findElement(publicationToAdd).getText();
        return str;
    }

    public void comprasionPublicationsInFrontPage(String firstPublication, String secondPublication){
        if(firstPublication.equals(secondPublication)){
            Assert.fail("The publication isn`t added on the front page by clicking in switcher");
        }
    }

    public void clickInFrontPageSaveButton(){
        driver.findElement(frontPageSaveButton).click();
    }
}
