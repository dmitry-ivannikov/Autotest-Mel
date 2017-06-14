package Main;

import AdminTestClasses.*;
import Helper.AdditionalMethods;
import Helper.GetUrl;
import TestClasses.PagePublishing;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import java.io.IOException;
import java.util.Arrays;
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
    private AdminFrontPageTest frontPage;
    private AdminBlogs blogs;
    private AdminAddingAuthorTest author;
    private AdminLogoutTest logout;

    public AdminTestCases(){

    }
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
    public void authorisationAndLogout(){
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);
        logout = new AdminLogoutTest(driver);
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        logout.adminLogout();
        methods.Wait(100);
        Assert.assertEquals(driver.getTitle(), "Вход");
    }

    @Test
    public void addingAdminUser(){
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        addingUser = new AdminAddingUserTest(driver);
        adminLogin = new AdminLoginTest(driver);

        // получение рандомной почты для регистрируемого пользователя
        String UserEmail = methods.generateStr();
        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        // заполнение полей регистрируемого пользователя
        addingUser.addingNewUser("Name", "SurName",UserEmail);
        driver.get("https://mail.ru/");
        // авторизация на mail.ru
        addingUser.emailAuthorisation("test153153153@mail.ru", "Qa123_000");
        methods.Wait(1000);
        // сохранение значений текущего окна
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        // переход по ссылке для окончания регистрации
        addingUser.registrateUser();
        // перенос фокуса на первое окно
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(100);

        // сравнение полей, вводимых при первичной регистрации
        Assert.assertEquals(addingUser.getRegistrationName(), "Name");
        Assert.assertEquals(addingUser.getRegistrationSurname(), "SurName");
        Assert.assertEquals(addingUser.getRegistrationEmail(), UserEmail);
        Assert.assertEquals(driver.getTitle(), "Регистрация пользователя");

        // ввод пароля и нажатие на кнопку регистрации
        addingUser.enterPasswordAndConfirm("12345678","12345678");
        methods.Wait(500);
        // проверка перехода на страницу публикаций после окончания регистрации
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
        frontPage = new AdminFrontPageTest(driver);

        // получение рандомной строки для заголовка публикации
        int a = 0;
        int b = 10000;
        int randomNumber = a + (int) (Math.random() * b);
        String title = "Title"+randomNumber;

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        addingPublication.clickInNewPublication();
        autosave.clickInPublicationSaveButton();
        methods.Wait(4000);
        // запись в строку времени при нажатии на кнопку сохранения
        String firstTime = autosave.getPublicationSaveTime();
        // заполнения полей при создании публикации
        addingPublication.fillingFields(title, "Subtitle", "The Question", "Annoucement", "Covertag","Addingtag","TText in block");
        methods.Wait(4000);
        Assert.assertEquals(driver.getTitle(), "Новая публикация");
        // запись в строку текущего url на странице заполнения обложек
        String draftUrl = driver.getCurrentUrl();
        // заполнение обложек
        addingPublication.addingCovers();
        methods.Wait(4000);
        // переход по url для создаваемого черновика
        driver.get(draftUrl);
        methods.Wait(4000);
        // проверка на соответствие данных при создании публикации и перехода в черновик
        Assert.assertEquals(addingPublication.getDraftTitle(), title);
        Assert.assertEquals(addingPublication.getDraftSubtitle(), "Subtitle");

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        // нажатие на кнопку просмотра превью публикации
        addingPublication.showPreviewPublication();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        // проверка соответствия данных при создании публикации и в превью
        Assert.assertEquals(addingPublication.getPublicationPreviewTitle(), title);
        Assert.assertEquals(addingPublication.getPublicationPreviewSubtitle(), "Subtitle");
        Assert.assertEquals(addingPublication.getPublicationPreviewText(), "Text in block");
        //Assert.assertEquals(addingPublication.getPublicationPreviewAddingTag(), "Addingtag");

        // переход на окно создания публикации
        driver.switchTo().window(parentWindowId);
        // нажатие второй раз на кнопку сохранения
        autosave.clickInPublicationSaveButton();
        methods.Wait(200);
        // запись в строку второго времени при нажатии на кнопку сохранения
        String secondTime = autosave.getPublicationSaveTime();
        // проверка на то, что время отличается при первом и втором нажатии на кнопку сохранения
        autosave.comparisonPublicationTime(firstTime,secondTime);
        addingPublication.clickInPublicButton();
        addingPublication.clickInConfirmPublicButtons();
//        methods.Wait(5000);
//        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//        jse1.executeScript("scroll(0,700)", "");
//        methods.Wait(200);
//        driver.findElement(addingPublication.publicationCoverAdditionalFormats).click();
//        methods.Wait(5000);
//        methods.imgageDownload();
//
//        addingPublication.clickInPublicButton();
//        methods.Wait(5000);
//        addingPublication.clickInConfirmPublicButtons();
        methods.Wait(5000);
        // на странице публикаций поиск созданной и опубликованной статьи
        search.insertText(title);
        search.clickInPublication();
        addingPublication.clickInPublicationSettings();
        // получение url публикации для сайта
        String publicationUrl = getUrl.driverGetStr()+addingPublication.getPublicationUrl()+"title";
        driver.get(publicationUrl);
        // проверка соответствия данных созданной публикации и публикации на сайте
        Assert.assertEquals(publishing.getPublicationTitle(), title);
        Assert.assertEquals(publishing.getPublicationSubtitle(), "Subtitle");
        Assert.assertEquals(publishing.getPublicationTagOnTheCover(), "COVERTAG");
        Assert.assertEquals(publishing.getPublicationAuthor(), "The Question");
        Assert.assertEquals(publishing.getPublicationText(), "Text in block");
        Assert.assertTrue(driver.findElement(publishing.publicationImage).isDisplayed());

        // переход на страницу первой полосы
        getUrl.driverGetCurrentAdminUrl("frontpage");
        String addingPublication = frontPage.getTitlePublicationToAdd();
        String mainPublication = frontPage.getTitleMainPublication();
        // сравнение заголовка двух публикаций до нажатия на кнопку добавления на первую полосу
        frontPage.comprasionPublicationsInFrontPage(addingPublication, mainPublication);
        // нажатие на кнопку добавления на первую полосу
        frontPage.clickInPublicationSwitcher();
        // нажатие на кнопку сохранения изменений
        frontPage.clickInFrontPageSaveButton();
        methods.Wait(4000);
        getUrl.driverGet();
        methods.Wait(4000);
        // сравенение на соответствия созданной публикации и первой публикации, отображающейся на сайте
        Assert.assertEquals(publishing.getMainPagePublicationTitle(), title);
        Assert.assertEquals(publishing.getMainPagePublicationSubtitle(), "Subtitle");
        Assert.assertEquals(publishing.getMainPagePublicationTagOnTheCover(), "COVERTAG");
        //methods.getBrowserLogs();
    }

    @Test
    public void rubricator() throws IOException {
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
        Assert.assertEquals(driver.getTitle(),"Test | Образовательный портал Мел.ФМ. Статьи о школе и обучении в России и по всему миру");
        Assert.assertEquals(rubricator.metaNameSeoTitleRubric(),"Test | Образовательный портал Мел.ФМ. Статьи о школе и обучении в России и по всему миру");

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
    public void checkBlogs() {
        methods = new AdditionalMethods(driver);
        blogs = new AdminBlogs(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);
        search = new AdminSearchTest(driver);
        frontPage = new AdminFrontPageTest(driver);
        publishing = new PagePublishing(driver);

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        blogs.clickInBlogsButton();
        // поиск публикации по названию
        search.insertText("FirstMessage");
        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        // запись в строку заголовка блога в админке
        String blogInAdmin = blogs.getPostTitleInAdmin();
        // нажатие на кнопку открытия на сайте публикации
        blogs.clickInOpenAtSiteButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        // запись в строку заголовка блога на сайте
        String blogInSite = blogs.getPostTitleInSite();
        // сравнение заголовков
        blogs.comprasionTitleBlogs(blogInAdmin,blogInSite);
        // переход на окно в адмикне
        driver.switchTo().window(parentWindowId);
        methods.Wait(1000);
        // нажатие на выпадающее меню
        blogs.clickInDropDownMenu();
        methods.Wait(1000);
        // нажатие на кнопку блокировки публикации
        blogs.clickInPostBlockingButton();
        methods.Wait(1000);
        // проверка отображения значка блорировки
        Assert.assertTrue(driver.findElement(blogs.iconImgHiddenBlog).isDisplayed());
        blogs.clickInDropDownMenu();
        // разблокировка публикации
        driver.findElement(blogs.postBlockingButton).click();
        methods.Wait(500);
        blogs.clickInDropDownMenu();
        methods.Wait(500);
        // нажатие на кнокпку добавление на первую полосу
        blogs.clickInPostFutureButton();
        methods.Wait(500);
        // проверка отображения флага
        Assert.assertTrue(driver.findElement(blogs.flagAddToFrontPage).isDisplayed());

        getUrl.driverGetCurrentAdminUrl("frontpage");
        methods.Wait(5000);
        // нажатие на кнопку добавления блога на первую полосу3
        frontPage.clickInPublicationSwitcher();
        methods.Wait(5000);
        // нажатие на кнопку сохранения изменений
        frontPage.clickInFrontPageSaveButton();
        methods.Wait(5000);
        getUrl.driverGet();
        // проверка на соответствия данных созданного блога и блога, отображающегося на сайте
        Assert.assertEquals(publishing.getMainPagePublicationTitle(), "FirstMessage");
        Assert.assertEquals(publishing.getMainPagePublicationSubtitle(), "SecondMessage");
        Assert.assertEquals(publishing.getMainPagePublicationTagOnTheCover(), "БЛОГИ");
    }

    @Test
    public void addingAuthor(){
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        adminLogin = new AdminLoginTest(driver);
        author = new AdminAddingAuthorTest(driver);
        adminLogin = new AdminLoginTest(driver);

        // получение рандомной строки для двух фамилий
        int a = 0;
        int b = 10000;
        int firstrandomNumber = a + (int) (Math.random() * b);
        String firstSurname = "яяяяя"+firstrandomNumber;
        int secondrandomNumber = a + (int) (Math.random() * b);
        String secondSurname = "яяяяя"+secondrandomNumber;
        // запись в строку рандомного email
        String email = methods.generateStr();
        // два имени автора
        String firstNameOfTheAuthor = "firstName";
        String secondNameOfTheAuthor = "secondName";

        String aboutAuthor = "AboutAuthor";

        getUrl.driverGetAdminUrl();
        adminLogin.adminAuthorisation("test@example.com", "123qwe");
        // создание автора
        author.addingNewAuthor(firstNameOfTheAuthor,firstSurname,email,aboutAuthor);
        methods.Wait(1000);
        // нажатие на кнопку сортировки по фамилии и имени в обратном порядке
        author.clickInSortArrowButton();
        // запись в строку имени и фамилии автора
        String nameAndSurnameAuthor = firstSurname + firstNameOfTheAuthor;
        // запись в строку второго и третьего автора по сортированному списку
        String secondAuthor = author.getTextFromTheAuthorField(author.secondAuthor);
        String thirdAuthor = author.getTextFromTheAuthorField(author.thirdAuthor);
        // добавление авторов в массив
        String authors[]= {nameAndSurnameAuthor,secondAuthor,thirdAuthor};
        // проверка на правильность сортировки по фамилии и имени
        author.compareAuthorsAfterSort(authors);
        // редактирование автора с изменением имени и фамилии
        author.editAuthor(secondNameOfTheAuthor, secondSurname);
        // сравнение ожидаемого и фактического имени автора
        if(nameAndSurnameAuthor.equals(author.getAuthorNameAndSurname())){
            Assert.fail("Not editing works of the author");
        }

        String parentWindowId = driver.getWindowHandle();
        final Set<String> oldWindowsSet = driver.getWindowHandles();
        author.clickInSortArrowButton();
        methods.Wait(2000);

        author.clickInOpenInNewPageButton();
        methods.moveFocusToTheNewWindow(oldWindowsSet);
        methods.Wait(200);
        // проверка созданного автора в админке и фактического результата на сайте
        Assert.assertEquals(driver.getTitle(), secondNameOfTheAuthor + " " + secondSurname + " | Мел");
        Assert.assertEquals(author.getAuthorNameAndSurnameInSite(), secondNameOfTheAuthor + " " + secondSurname);
        Assert.assertEquals(author.getAboutAuthorInSite(), aboutAuthor);
        driver.switchTo().window(parentWindowId);
        // нажатие на кнопку сортировки по публикациям
        author.clickInsortingPublicationButton();
        // запись количества публикаций для сравнения
        int firstNumberPublication = author.convertSelectorToNumber(author.firstPublicationCount);
        int secondNumberPublication = author.convertSelectorToNumber(author.secondPublicationcount);
        // проверка на сортировку по публикациям
        author.compareTheNumbers(firstNumberPublication,secondNumberPublication);
        // нажатие на кнопку сортировки по подписчикам
        author.clickInsortingSubscribersButton();
        // запись количества подписчиков
        int firstNumberSubscribers = author.convertSelectorToNumber(author.firstSubscribersCount);
        int secondNumberSubscribers = author.convertSelectorToNumber(author.secondSubscribersCount);
        // проверка на сортировку по числу подписчиков
        author.compareTheNumbers(firstNumberSubscribers,secondNumberSubscribers);
        // нахождение и удаление блоггера
        methods.Wait(200);
        author.clickInSortArrowButton();
        methods.Wait(200);
        author.clickInSortArrowButton();
        methods.Wait(1000);
        author.clickIndropdownButton();
        methods.Wait(1000);
        author.clickInDeleteButtons();
        methods.Wait(200);
        author.clickInSortArrowButton();
        // проврека на наличие блогера после удаления
        if(author.getAuthorNameAndSurname().equals(secondSurname + secondNameOfTheAuthor)){
            org.junit.Assert.fail("Sorting not working");
        }
    }
}
