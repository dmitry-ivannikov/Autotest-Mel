package Main;

import AdminTestClasses.AdminAddingPublicationTest;
import AdminTestClasses.AdminAddingUserTest;
import AdminTestClasses.AdminLoginTest;
import AdminTestClasses.AdminRubricatorTest;
import Helper.AdditionalMethods;
import Helper.GetUrl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AdminTestCases {

    private WebDriver driver;
    private AdditionalMethods methods;
    private AdminLoginTest adminLogin;
    private AdminAddingUserTest addingUser;
    private AdminAddingPublicationTest addingPublication;
    private GetUrl getUrl;
    private AdminRubricatorTest rubricator;

    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver" , "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void BeforeTests() throws IOException {
        setup();
    }

    @After
    public void AfterTests(){
        driver.quit();
    }

    @Test
    public void AddingAdminUser(){
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        addingUser = new AdminAddingUserTest(driver);
        adminLogin = new AdminLoginTest(driver);

        String UserEmail = methods.GenerateStr();
        getUrl.driverGetAdminUrl();
        adminLogin.AdminAuthorisation("test@example.com", "123qwe");
        addingUser.AddingNewUser("Name", "SurName",UserEmail);
        driver.get("https://mail.ru/");
        addingUser.EmailAuthorisation("test153153153@mail.ru", "Qa123_000");

        final Set<String> oldWindowsSet = driver.getWindowHandles();
        addingUser.RegistrateUser();
        methods.MoveFocucToTheNewWindow(oldWindowsSet);
        methods.Wait();

        Assert.assertEquals(addingUser.getRegistrationName(), "Name");
        Assert.assertEquals(addingUser.getRegistrationSurname(), "SurName");
        Assert.assertEquals(addingUser.getRegistrationEmail(), UserEmail);
        Assert.assertEquals(driver.getTitle(), "Регистрация пользователя");

        addingUser.EnterPasswordAndConfirm("12345678","12345678");
        methods.Wait();
        Assert.assertEquals(driver.getTitle(), "Публикации");
    }


    @Test
    public void AddingPublication(){
        addingPublication = new AdminAddingPublicationTest(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);

        getUrl.driverGetAdminUrl();
        adminLogin.AdminAuthorisation("test@example.com", "123qwe");
        addingPublication.FillingFields("Title", "Subtitle", "The Question", "Annoucement", "Covertag","Addingtag","TText in block");
        Assert.assertEquals(driver.getTitle(), "Новая публикация");
        String url = driver.getCurrentUrl();
        addingPublication.AddingCovers();

        // Draft page
        driver.get(url);
        Assert.assertEquals(addingPublication.getDraftTitle(), "Title");
        Assert.assertEquals(addingPublication.getDraftSubtitle(), "Subtitle");

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        addingPublication.ShowPreviewPublication();
        methods.MoveFocucToTheNewWindow(oldWindowsSet);

        // Preview page
        Assert.assertEquals(addingPublication.getPublicationPreviewTitle(), "Title");
        Assert.assertEquals(addingPublication.getPublicationPreviewSubtitle(), "Subtitle");
        Assert.assertEquals(addingPublication.getPublicationPreviewText(), "Text in block");
        Assert.assertEquals(addingPublication.getPublicationPreviewAddingTag(), "Addingtag");

        // Publication of the article
        driver.switchTo().window(parentWindowId);
        addingPublication.ClickInPublicButtons();

        String publicationUrl = getUrl.driverGetStr()+addingPublication.getPublicationUrl()+"title";
        System.out.println(publicationUrl);
        driver.get(publicationUrl);
    }
    @Test
    public void Rubricator() throws IOException {
        methods = new AdditionalMethods(driver);
        rubricator = new AdminRubricatorTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGetAdminUrl();
        adminLogin = new AdminLoginTest(driver);
        adminLogin.AdminAuthorisation("test@example.com", "123qwe");
        rubricator.ClickOnRubricatorTab();
        //add new rubric
        rubricator.AddNewRubric("Test");
        methods.Wait();
        // check new rubric
        Assert.assertEquals(rubricator.getNameNewRubric(),"Test");
        getUrl.driverGet();
        Assert.assertEquals(rubricator.getNameNewRubricOnWebsite(),"TEST");
        rubricator.CheckNewRubricOnWebsite();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/test");
        Assert.assertEquals(driver.getTitle(),"Test | Мел");
        Assert.assertEquals(rubricator.MetaNameSeoTitleRubric(),"Test | Мел");
        getUrl.driverGetAdminUrl();
        rubricator.CheckNewRubricOnAddPublicationPage();
        Assert.assertEquals(rubricator.getRubricInInput(),"Test");
        methods.Wait();
        //edit rubric
        rubricator.ClickOnRubricatorTab();
        rubricator.EditRubric("2","TestTitleSeo","TestDescriptionSeo");
        methods.Wait();
        // check edit rubric
        Assert.assertEquals(rubricator.getNameNewRubric(),"Test2");
        getUrl.driverGet();
        Assert.assertEquals(rubricator.getNameNewRubricOnWebsite(),"TEST2");
        rubricator.CheckNewRubricOnWebsite();
        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/test");
        Assert.assertEquals(driver.getTitle(),"TestTitleSeo");
        Assert.assertEquals(rubricator.MetaNameSeoTitleRubric(),"TestTitleSeo");
        Assert.assertEquals(rubricator.MetaNameSeoDescriptionRubric(),"TestDescriptionSeo");
        //check close popup
        getUrl.driverGetAdminUrl();
        rubricator.ClickOnRubricatorTab();
        rubricator.CheckCloseEditRubricPopup();
        methods.Wait();
        //check dragging rubric
        rubricator.DragUpRubric();
        Assert.assertEquals(rubricator.getNameDragUpRubric(),"Test2");
        rubricator.DragDownRubric();
        Assert.assertEquals(rubricator.getNameNewRubric(),"Test2");
        //check delete rubric
        rubricator.DeleteRubric();
        Assert.assertTrue(driver.findElement(rubricator.RubricHidden).isDisplayed());
        methods.Wait();
        getUrl.driverGetCurrentUrl("rubric/test");
        Assert.assertEquals(driver.getTitle(),"Страница не найдена");
        getUrl.driverGet();
        Assert.assertEquals(rubricator.getNameNewRubricOnWebsite(),"БЛОГИ");
        driver.quit();




    }
}
