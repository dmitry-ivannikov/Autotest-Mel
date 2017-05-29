package AdminTestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminRubricatorTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    private By rubricatorTab = By.cssSelector(".i-layout__menu > div > div:nth-child(3)");
    //add rubric
    private By addRubricButton = By.cssSelector(".b-tree-list__additional-creator-name");
    private By titleRubricInput = By.cssSelector(".b-item-editor__name-editor > div > input");
    private By titleSeoInput = By.cssSelector(".b-item-editor__title-seo-editor > div > input");
    private By descriptionSeoInput = By.cssSelector(".b-item-editor__description-seo-editor > div > input");
    private By saveButtonInAddRubricPopup = By.cssSelector(".b-item-editor__save-button > div");
    //check new rubric on website
    private By newRubricOnWebsite = By.cssSelector(".l-frontpage__rubrics > nav > div > a:nth-child(6) > span");
    private By metaNameSeoTitle = By.cssSelector("head > meta:nth-child(22)");
    private By metaNameDescriptionTitle = By.cssSelector("head > meta:nth-child(23)");
    //check edit rubric and close popup
    private By editNewRubricButton = By.cssSelector(".b-tree-list__items > div:nth-child(6) > div > .b-tree-list__root > .b-tree-list__name");
    private By closeButtonInAddRubricPopup = By.cssSelector(".g-modal__close-icon > div");
    //check
    private By newPublicationButton = By.cssSelector(".i-layout__new-publication-button");
    private By rubricDropdownOpenerButton = By.cssSelector(".g-dropdown__opener");
    private By newRubricInList = By.cssSelector(".g-dropdown__content_scrollable > div > div:nth-child(6)");
    private By rubricInput = By.cssSelector(".g-dropdown__opener-text");
    //check
    private By dragUpRubricButton = By.cssSelector(".b-tree-list__items > div:nth-child(6) > div > .b-tree-list__root > .b-tree-list__dragger > .b-tree-list__up-dragger ");
    private By dragUpRubruc = By.cssSelector(".b-tree-list__items > div:nth-child(5) > div > .b-tree-list__root > .b-tree-list__name");
    private By dragDownRubricButton = By.cssSelector(".b-tree-list__items > div:nth-child(5) > div > .b-tree-list__root > .b-tree-list__dragger >.b-tree-list__down-dragger");
    //check delete rubric
    private By deleteRubricButton = By.cssSelector(".b-item-editor__remover");
    public By rubricHidden = By.cssSelector("body > div:nth-child(4)");

    public AdminRubricatorTest(WebDriver driver) {
        this.driver = driver;
    }

    public String getNameNewRubric() {
        String str = driver.findElement(editNewRubricButton).getText();
        return str;
    }

    public String getNameNewRubricOnWebsite() {
        String str = driver.findElement(newRubricOnWebsite).getText();
        return str;
    }

    public String metaNameSeoTitleRubric() {
        String str = driver.findElement(metaNameSeoTitle).getAttribute("content");
        return str;
    }

    public String metaNameSeoDescriptionRubric() {
        String str = driver.findElement(metaNameDescriptionTitle).getAttribute("content");
        return str;
    }

    public String getRubricInInput() {
        String str = driver.findElement(rubricInput).getText();
        return str;
    }

    public String getNameDragUpRubric() {
        String str = driver.findElement(dragUpRubruc).getText();
        return str;
    }

    public void clickOnRubricatorTab(){
        driver.findElement(rubricatorTab).click();
    }

    public void addNewRubric(String TitleRubric){
        driver.findElement(addRubricButton).click();
        driver.findElement(titleRubricInput).sendKeys(TitleRubric);
        driver.findElement(saveButtonInAddRubricPopup).click();
    }

    public void checkNewRubricOnWebsite(){
        driver.findElement(newRubricOnWebsite).click();
    }

    public void editRubric(String TitleRubric, String TitleSeo, String DescriptionSeo){
        driver.findElement(editNewRubricButton).click();
        driver.findElement(titleRubricInput).sendKeys(TitleRubric);
        driver.findElement(titleSeoInput).sendKeys(TitleSeo);
        driver.findElement(descriptionSeoInput).sendKeys(DescriptionSeo);
        driver.findElement(saveButtonInAddRubricPopup).click();
    }

    public void checkNewRubricOnAddPublicationPage(){
        driver.findElement(newPublicationButton).click();
        driver.findElement(rubricDropdownOpenerButton).click();
        driver.findElement(newRubricInList).click();
    }

    public void checkCloseEditRubricPopup(){
        methods = new AdditionalMethods(driver);

        driver.findElement(editNewRubricButton).click();
        methods.Wait(4000);
        driver.findElement(closeButtonInAddRubricPopup).click();
    }

    public void dragUpRubric(){
        driver.findElement(dragUpRubricButton).click();
    }

    public void dragDownRubric(){
        driver.findElement(dragDownRubricButton).click();
    }

    public void deleteRubric(){
        methods = new AdditionalMethods(driver);

        driver.findElement(editNewRubricButton).click();
        methods.Wait(4000);
        driver.findElement(deleteRubricButton).click();
    }
}
