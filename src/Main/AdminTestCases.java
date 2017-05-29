package Main;

import AdminTestClasses.*;
import Helper.AdditionalMethods;
import Helper.GetUrl;
import TestClasses.PagePublishing;
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
    private AdminSearchTest search;
    private PagePublishing publishing;
    private AdminAutosaveTest autosave;
    private AdminPublicationFrontPageTest frontPage;
    private AdminBlogs blogs;

    public void setup() throws IOException {
        System.setProperty("webdriver.chrome.driver" , "C:\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Before
    public void beforeTests() throws IOException {
        setup();
    }

    @After
    public void afterTests(){
        driver.quit();
    }

    @Test
    public void addingAdminUser(){
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        addingUser = new AdminAddingUserTest(driver);
        adminLogin = new AdminLoginTest(driver);

        String UserEmail = methods.generateStr();
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        addingUser.addingNewUser("Name", "SurName",UserEmail);
        driver.get("https://mail.ru/");
        addingUser.emailAuthorisation("test153153153@mail.ru", "Qa123_000");

        final Set<String> oldWindowsSet = driver.getWindowHandles();
        addingUser.registrateUser();
        methods.moveFocucToTheNewWindow(oldWindowsSet);
        methods.Wait(4000);

        Assert.assertEquals(addingUser.getRegistrationName(), "Name");
        Assert.assertEquals(addingUser.getRegistrationSurname(), "SurName");
        Assert.assertEquals(addingUser.getRegistrationEmail(), UserEmail);
        Assert.assertEquals(driver.getTitle(), "Регистрация пользователя");

        addingUser.enterPasswordAndConfirm("12345678","12345678");
        methods.Wait(4000);
        Assert.assertEquals(driver.getTitle(), "Публикации");
        //methods.getBrowserLogs();
    }

    @Test
    public void addingPublication(){
        addingPublication = new AdminAddingPublicationTest(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);
        search = new AdminSearchTest(driver);
        publishing = new PagePublishing(driver);
        autosave = new AdminAutosaveTest(driver);
        frontPage = new AdminPublicationFrontPageTest(driver);

        int a = 0; // initial range
        int b = 10000; // the final value of the range
        int randomNumber = a + (int) (Math.random() * b);
        String title = "Title"+randomNumber;

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        addingPublication.clickInNewPublication();
        autosave.clickInPublicationSaveButton();
        methods.Wait(4000);
        String firstTime = autosave.getPublicationSaveTime();
        addingPublication.fillingFields(title, "Subtitle", "The Question", "Annoucement", "Covertag","Addingtag","TText in block");
        Assert.assertEquals(driver.getTitle(), "Новая публикация");
        String draftUrl = driver.getCurrentUrl();
        addingPublication.addingCovers();

        // Draft page
        driver.get(draftUrl);
        Assert.assertEquals(addingPublication.getDraftTitle(), title);
        Assert.assertEquals(addingPublication.getDraftSubtitle(), "Subtitle");

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        addingPublication.showPreviewPublication();
        methods.moveFocucToTheNewWindow(oldWindowsSet);

        // Preview page
        Assert.assertEquals(addingPublication.getPublicationPreviewTitle(), title);
        Assert.assertEquals(addingPublication.getPublicationPreviewSubtitle(), "Subtitle");
        Assert.assertEquals(addingPublication.getPublicationPreviewText(), "Text in block");
        //Assert.assertEquals(addingPublication.getPublicationPreviewAddingTag(), "Addingtag");

        // Publication of the article
        driver.switchTo().window(parentWindowId);

        autosave.clickInPublicationSaveButton();
        methods.Wait(4000);
        String secondTime = autosave.getPublicationSaveTime();
        autosave.comparisonPublicationTime(firstTime,secondTime);

        addingPublication.clickInPublicButtons();
        search.insertText(title);
        search.clickInPublication();
        addingPublication.clickInPublicationSettings();

        String publicationUrl = getUrl.driverGetStr()+addingPublication.getPublicationUrl()+"title";
        driver.get(publicationUrl);
        // Page publishing
        Assert.assertEquals(publishing.getPublicationTitle(), title);
        Assert.assertEquals(publishing.getPublicationSubtitle(), "Subtitle");
        Assert.assertEquals(publishing.getPublicationTagOnTheCover(), "COVERTAG");
        Assert.assertEquals(publishing.getPublicationAuthor(), "The Question");
        Assert.assertEquals(publishing.getPublicationText(), "Text in block");
        Assert.assertTrue(driver.findElement(publishing.publicationImage).isDisplayed());

        // check front page
        driver.get("http://admin.pablo-mel.qa.lan/frontpage");
        String addingPublication = frontPage.getTitlePublicationToAdd();
        String mainPublication = frontPage.getTitleMainPublication();
        methods.Wait(4000);
        frontPage.comprasionPublicationsInFrontPage(addingPublication, mainPublication);

        frontPage.clickInPublicationSwitcher();
        methods.Wait(4000);
        frontPage.clickInFrontPageSaveButton();
        methods.Wait(4000);
        getUrl.driverGet();
        methods.Wait(4000);
        Assert.assertEquals(publishing.getMainPagePublicationTitle(), title);
        Assert.assertEquals(publishing.getMainPagePublicationSubtitle(), "Subtitle");
        Assert.assertEquals(publishing.getMainPagePublicationTagOnTheCover(), "COVERTAG");
        //methods.getBrowserLogs();
    }

    @Test
    public void Rubricator() throws IOException {
        methods = new AdditionalMethods(driver);
        rubricator = new AdminRubricatorTest(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        rubricator.clickOnRubricatorTab();

        //add new rubric
        rubricator.addNewRubric("Test");
        methods.Wait(4000);

        // check new rubric
        Assert.assertEquals(rubricator.getNameNewRubric(),"Test");
        getUrl.driverGet();
        Assert.assertEquals(rubricator.getNameNewRubricOnWebsite(),"TEST");
        rubricator.checkNewRubricOnWebsite();

        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/test");
        Assert.assertEquals(driver.getTitle(),"Test | Мел");
        Assert.assertEquals(rubricator.metaNameSeoTitleRubric(),"Test | Мел");

        getUrl.driverGetAdminUrl();
        rubricator.checkNewRubricOnAddPublicationPage();
        Assert.assertEquals(rubricator.getRubricInInput(),"Test");
        methods.Wait(4000);

        //edit rubric
        rubricator.clickOnRubricatorTab();
        rubricator.editRubric("2","TestTitleSeo","TestDescriptionSeo");
        methods.Wait(4000);

        // check edit rubric
        Assert.assertEquals(rubricator.getNameNewRubric(),"Test2");
        getUrl.driverGet();
        Assert.assertEquals(rubricator.getNameNewRubricOnWebsite(),"TEST2");
        rubricator.checkNewRubricOnWebsite();

        Assert.assertEquals(driver.getCurrentUrl(), getUrl.driverGetStr()+"rubric/test");
        Assert.assertEquals(driver.getTitle(),"TestTitleSeo");
        Assert.assertEquals(rubricator.metaNameSeoTitleRubric(),"TestTitleSeo");
        Assert.assertEquals(rubricator.metaNameSeoDescriptionRubric(),"TestDescriptionSeo");

        //check close popup
        getUrl.driverGetAdminUrl();
        rubricator.clickOnRubricatorTab();
        rubricator.checkCloseEditRubricPopup();
        methods.Wait(4000);

        //check dragging rubric
        rubricator.dragUpRubric();
        Assert.assertEquals(rubricator.getNameDragUpRubric(),"Test2");
        rubricator.dragDownRubric();
        Assert.assertEquals(rubricator.getNameNewRubric(),"Test2");

        //check delete rubric
        rubricator.deleteRubric();
        Assert.assertTrue(driver.findElement(rubricator.rubricHidden).isDisplayed());
        methods.Wait(4000);
        getUrl.driverGetCurrentUrl("rubric/test");
        Assert.assertEquals(driver.getTitle(),"Страница не найдена | Мел");
        getUrl.driverGet();
        Assert.assertEquals(rubricator.getNameNewRubricOnWebsite(),"БЛОГИ");
        //methods.getBrowserLogs();
    }

    @Test
    public void CheckBlogs() {
        methods = new AdditionalMethods(driver);
        blogs = new AdminBlogs(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);
        search = new AdminSearchTest(driver);

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        blogs.clickInBlogsButton();
        search.insertText("FirstMessage");
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        String blogInAdmin = blogs.getPostTitleInAdmin();
        blogs.clickInOpenAtSiteButton();
        methods.Wait(4000);
        methods.moveFocucToTheNewWindow(oldWindowsSet);
        String blogInSite = blogs.getPostTitleInSite();
        blogs.comprasionTitleBlogs(blogInAdmin,blogInSite);
        driver.switchTo().window(parentWindowId);
        blogs.clickInDropDownMenu();
        blogs.clickInPostFutureButton();
        Assert.assertTrue(driver.findElement(blogs.flagAddToFrontPage).isDisplayed());
        blogs.clickInDropDownMenu();
        blogs.clickInPostFutureButton();

        blogs.clickInDropDownMenu();
        blogs.clickInPostBlockingButton();
        Assert.assertTrue(driver.findElement(blogs.iconImgHiddenBlog).isDisplayed());
    }


}
