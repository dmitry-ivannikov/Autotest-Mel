package AdminTestClasses;

import Helper.AdditionalMethods;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;


public class AdminAddingAuthorTest {
    WebDriver driver;
    private AdditionalMethods methods;

    private By blogsButton = By.cssSelector(".i-layout__menu > div > div:nth-child(6)");
    private By newAuthorButton = By.cssSelector(".i-layout__subheader > div");
    private By downloadPhotoButton = By.cssSelector(".i-layout__photo-upload > div");
    private By downloadCoverButton = By.cssSelector(".i-layout__cover-upload > div");
    private By nameField = By.cssSelector(".i-layout__first-name-input > div > input");
    private By surnameField = By.cssSelector(".i-layout__last-name-input > div > input");
    private By emailField = By.cssSelector(".i-layout__email-input > div > input");
    private By aboutAuthorField = By.cssSelector(".i-layout__description-textarea > div > textarea");
    private By saveAuthorButton = By.cssSelector(".i-layout__save-button > div");
    public By secondAuthor = By.cssSelector("#\\31 84 > div.b-table-row__name");
    public By thirdAuthor = By.cssSelector("#\\32 11 > div.b-table-row__name");
    private By sortArrowButton = By.cssSelector(".b-table__sort-by-name-button > div");
    private By openInNewPageButton = By.cssSelector(".b-table-row__controls");
    private By dropdownButton = By.cssSelector(".g-dropdown__opener");
    private By editButtonInDropdown = By.cssSelector(".g-dropdown__content > div > div:nth-child(1)");
    private By deleteButtonInDropdown = By.cssSelector(".g-dropdown__content > div > div:nth-child(2)");
    private By confirmDeleteAuthorButton = By.cssSelector(".i-layout__remove-button");
    private By authorNameAndSurname = By.cssSelector(".b-table-row__name");
    private By authorNameAndSurnameInSite = By.cssSelector(".b-pb-author__name");
    private By aboutAuthorInSite = By.cssSelector(".b-pb-author__quote");
    private By sortingPublicationsButton = By.cssSelector(".b-table__sort-by-publications-button");
    private By sortingSubscribersButton = By.cssSelector(".b-table__sort-by-subscriptions-button");

    public By firstPublicationCount = By.cssSelector("#\\32 8 > div.b-table-row__publications-count");
    public By secondPublicationcount = By.cssSelector("#\\32 37 > div.b-table-row__publications-count");

    public By firstSubscribersCount = By.cssSelector("#\\31 15 > div.b-table-row__subscriptions-count");
    public By secondSubscribersCount = By.cssSelector("#\\36 7 > div.b-table-row__subscriptions-count");

    public AdminAddingAuthorTest(WebDriver driver) {
        this.driver = driver;
    }

    public void addingNewAuthor(String name, String surname, String email, String aboutAuthor){
        methods = new AdditionalMethods(driver);

        driver.findElement(blogsButton).click();
        driver.findElement(newAuthorButton).click();
        driver.findElement(downloadPhotoButton).click();
        methods.Wait(200);
        methods.imgageDownload();
        methods.Wait(200);
        driver.findElement(downloadCoverButton).click();
        methods.Wait(100);
        methods.imgageDownload();
        methods.Wait(100);
        driver.findElement(nameField).sendKeys(name);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(emailField).sendKeys(email);
        driver.findElement(aboutAuthorField).sendKeys(aboutAuthor);
        driver.findElement(saveAuthorButton).click();
    }

    public void clickInSortArrowButton(){
        driver.findElement(sortArrowButton).click();
    }

    public void clickInOpenInNewPageButton(){
        driver.findElement(openInNewPageButton).click();
    }

    public void clickIndropdownButton(){
        driver.findElement(dropdownButton).click();
    }

    public void editAuthor(String name, String surname){
        clickIndropdownButton();
        driver.findElement(editButtonInDropdown).click();
        methods.Wait(100);
        driver.findElement(nameField).clear();
        methods.Wait(100);
        driver.findElement(nameField).sendKeys(name);
        methods.Wait(100);
        driver.findElement(surnameField).clear();
        methods.Wait(100);
        driver.findElement(surnameField).sendKeys(surname);
        driver.findElement(saveAuthorButton).click();
        methods.Wait(100);
    }

    public String getAuthorNameAndSurname(){
        String str = driver.findElement(authorNameAndSurname).getText();
        return str;
    }

    public String getAuthorNameAndSurnameInSite(){
        String str = driver.findElement(authorNameAndSurnameInSite).getText();
        return str;
    }

    public String getAboutAuthorInSite(){
        String str = driver.findElement(aboutAuthorInSite).getText();
        return str;
    }

    public void clickInsortingPublicationButton(){
        driver.findElement(sortingPublicationsButton).click();
    }

    public void clickInsortingSubscribersButton(){
        driver.findElement(sortingSubscribersButton).click();
    }

    public int convertSelectorToNumber(By selector){
        String str = driver.findElement(selector).getText();
        int number = Integer.parseInt(str);
        return number;
    }

    public void compareTheNumbers(int firstNumber, int lastNumber){
        if(firstNumber < lastNumber){
            Assert.fail("Sorting not working");
        }
    }

    public void clickInDeleteButtons(){
        driver.findElement(deleteButtonInDropdown).click();
        methods.Wait(100);
        driver.findElement(confirmDeleteAuthorButton).click();
    }

    public void compareAuthorsAfterSort(String arr[]) {
        for (int j = 0; j < arr.length; j++) {
            for (int i = j + 1; i < arr.length; i++) {
                if (arr[i].compareTo(arr[j]) > 0) {
                    Assert.fail("Sorting authors not works");
                }
            }
        }
    }

    public String getTextFromTheAuthorField(By selector){
        String str = driver.findElement(selector).getText();
        return str;
    }
}
