package AdminTestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by Maria on 19.05.2017.
 */
public class AdminRubricatorTest {
    private WebDriver driver;
    private AdditionalMethods methods;
    private By RubricatorTab = By.cssSelector(".i-layout__menu > div > div:nth-child(3)");
    //add rubric
    private By AddRubricButton = By.cssSelector(".b-tree-list__additional-creator-name");
    private By TitleRubricInput = By.cssSelector(".b-item-editor__name-editor > div > input");
    private By TitleSeoInput = By.cssSelector(".b-item-editor__title-seo-editor > div > input");
    private By DescriptionSeoInput = By.cssSelector(".b-item-editor__description-seo-editor > div > input");
    private By SaveButtonInAddRubricPopup = By.cssSelector(".b-item-editor__save-button > div");
    //check new rubric on website
    private By NewRubricOnWebsite = By.cssSelector(".l-frontpage__rubrics > nav > div > a:nth-child(6) > span");
    private By MetaNameSeoTitle = By.cssSelector("head > meta:nth-child(22)");
    private By MetaNameDescriptionTitle = By.cssSelector("head > meta:nth-child(23)");
    //check edit rubric and close popup
    private By EditNewRubricButton = By.cssSelector(".b-tree-list__items > div:nth-child(6) > div > .b-tree-list__root > .b-tree-list__name");
    private By CloseButtonInAddRubricPopup = By.cssSelector(".g-modal__close-icon > div");
    //check
    private By NewPublicationButton = By.cssSelector(".i-layout__new-publication-button");
    private By RubricDropdownOpenerButton = By.cssSelector(".g-dropdown__opener");
    private By NewRubricInList = By.cssSelector(".g-dropdown__content_scrollable > div > div:nth-child(6)");
    private By RubricInput = By.cssSelector(".g-dropdown__opener-text");
    //check
    private By DragUpRubricButton = By.cssSelector(".b-tree-list__items > div:nth-child(6) > div > .b-tree-list__root > .b-tree-list__dragger > .b-tree-list__up-dragger ");
    private By DragUpRubruc = By.cssSelector(".b-tree-list__items > div:nth-child(5) > div > .b-tree-list__root > .b-tree-list__name");

    private By DragDownRubricButton = By.cssSelector(".b-tree-list__items > div:nth-child(5) > div > .b-tree-list__root > .b-tree-list__dragger >.b-tree-list__down-dragger");
    //check delete rubric
    private By DeleteRubricButton = By.cssSelector(".b-item-editor__remover");
    public By RubricHidden = By.cssSelector("body > div:nth-child(4)");




    public AdminRubricatorTest(WebDriver driver) {
        this.driver = driver;
    }

    public  String getNameNewRubric() {
        String str = driver.findElement(EditNewRubricButton).getText();
        return str;
    }

    public  String getNameNewRubricOnWebsite() {
        String str = driver.findElement(NewRubricOnWebsite).getText();
        return str;
    }

    public  String MetaNameSeoTitleRubric() {
        String str = driver.findElement(MetaNameSeoTitle).getAttribute("content");
        return str;
    }

    public  String MetaNameSeoDescriptionRubric() {
        String str = driver.findElement(MetaNameDescriptionTitle).getAttribute("content");
        return str;
    }

    public  String getRubricInInput() {
        String str = driver.findElement(RubricInput).getText();
        return str;
    }
    public  String getNameDragUpRubric() {
        String str = driver.findElement(DragUpRubruc).getText();
        return str;
    }

    public void ClickOnRubricatorTab(){
        driver.findElement(RubricatorTab).click();
    }

    public void AddNewRubric(String TitleRubric){
        driver.findElement(AddRubricButton).click();
        driver.findElement(TitleRubricInput).sendKeys(TitleRubric);
        driver.findElement(SaveButtonInAddRubricPopup).click();

    }

    public void CheckNewRubricOnWebsite(){
        driver.findElement(NewRubricOnWebsite).click();
    }

    public void EditRubric(String TitleRubric, String TitleSeo, String DescriptionSeo){
        driver.findElement(EditNewRubricButton).click();
        driver.findElement(TitleRubricInput).sendKeys(TitleRubric);
        driver.findElement(TitleSeoInput).sendKeys(TitleSeo);
        driver.findElement(DescriptionSeoInput).sendKeys(DescriptionSeo);
        driver.findElement(SaveButtonInAddRubricPopup).click();
    }
    public void CheckNewRubricOnAddPublicationPage(){
        driver.findElement(NewPublicationButton).click();
        driver.findElement(RubricDropdownOpenerButton).click();
        driver.findElement(NewRubricInList).click();
    }

    public void CheckCloseEditRubricPopup(){
        methods = new AdditionalMethods(driver);
        driver.findElement(EditNewRubricButton).click();
        methods.Wait();
        driver.findElement(CloseButtonInAddRubricPopup).click();
    }

    public void DragUpRubric(){
        driver.findElement(DragUpRubricButton).click();
    }
    public void DragDownRubric(){
        driver.findElement(DragDownRubricButton).click();
    }

    public void DeleteRubric(){
        methods = new AdditionalMethods(driver);
        driver.findElement(EditNewRubricButton).click();
        methods.Wait();
        driver.findElement(DeleteRubricButton).click();

    }
}
