package Main;

import Helper.AdditionalMethods;
import Helper.GetUrl;
import TestClasses.*;
import org.junit.After;
import org.junit.Before;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import static org.testng.AssertJUnit.assertEquals;

public class SiteTestCases {

    private WebDriver driver;
    private LoginTest autoLogin;
    private RegistrationTest registration;
    private AdditionalMethods methods;
    private LogoutTest logout;
    private AddCommentTest comment;
    private SocialNetworksAuthorisationTest authorisationSocial;
    private FooterTest footer;
    private DownloadArticlesTest article;
    private SendMessageInBlogTest message;
    private SharingArticleTest sharingArticle;
    private SubscribeNewsLetterTest subscribeNewsLetter;
    private ProfileTest profile;
    private DirectiveTest directiveTest;
    private SearchTest search;
    private TagSubscribeTest tagSubscribe;
    private GetUrl getUrl;

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
    public  void Authorisation() throws IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        autoLogin = new LoginTest(driver);
        getUrl.driverGet();
        autoLogin.Authorisation("estendr@gmail.com", "12345678");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(autoLogin.getHomePageDashboardName(), "Vladimir Petrov");
        //Assert.assertEquals(logout.CheckEnterButton(), "ВХОД");
    }

    @Test
    public void Registration() throws IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        registration = new RegistrationTest(driver);
        getUrl.driverGet();

        //Invalid registration
        registration.FirstUserRegistration("!#$%&'*+-/=?^_`{|}~", "LastName", methods.GenerateStr(), "12345678");
        registration.PressInRegistrationButton();
        registration.RegistrationWithInvalidData("FirstName", "!#$%&'*+-/=?^_`{|}~", methods.GenerateStr(), "12345678");
        registration.RegistrationWithInvalidData("FirstName", "LastName", "ab(c)d,e:f;g<h>i[jk]l@example.com", "12345678");

        // Valid registration
        registration.RegistrationWithValidData("FirstName", "LastName", methods.GenerateStr(), "12345678");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(registration.GetHeaderUserName(), "FirstName LastName");
        methods.Exit();
        //Assert.assertEquals(logout.CheckEnterButton(), "ВХОД");
    }

    @Test
    public void AddAndDeleteComment() throws  IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        comment = new AddCommentTest(driver);
        autoLogin = new LoginTest(driver);

        getUrl.driverGet();
        autoLogin.Authorisation("estendr@gmail.com", "12345678");
        methods.Wait();
        Assert.assertEquals(autoLogin.getHomePageDashboardName(), "Vladimir Petrov");
        //driver.get("http://pablo-mel.qa.lan/olimpiady/2790854-sirius");
        comment.InsertAndAddComment("TestComment", "TestAnswer");
        comment.DeleteComment();
        methods.Wait();
        methods.Exit();
        methods.Wait();
        // Assert.assertEquals(logout.CheckEnterButton(), "ВХОД");
    }

    @Test
    public void AuthorisationSocial() throws  IOException {
        methods = new AdditionalMethods(driver);
        authorisationSocial = new SocialNetworksAuthorisationTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        //Facebook
        authorisationSocial.FacebookAuthorisation("easy2rider2@gmail.com", "knock705b");
        methods.Wait();
        Assert.assertEquals(authorisationSocial.getHomePageDashboardName(), "Eero Ettala");
        methods.OutputFromAnAccountSocialLogin();
        //Vk
        methods.Wait();
        authorisationSocial.VkAuthorisation("89164948378", "123qwe");
        Assert.assertEquals(authorisationSocial.getHomePageDashboardName(), "Ваня");
        methods.Wait();
        methods.OutputFromAnAccountSocialLogin();
        methods.Wait();
        // Assert.assertEquals(logout.CheckEnterButton(), "ВХОД");
        // Google+
        //        driver.get("http://qa.mel.fm/");
        //        // Wait();
        //        loginGoogleToAutho("test153153153", "test153123qwe");
        //        secondaryFunctions.Wait();
        //        try {
        //            Assert.assertEquals(getHomePageDashboardName(), "???");
        //        } catch (Exception e) {
        //            secondaryFunctions.text("На странице не найден авторизованный пользователь через Google+\n");
        //        }
        //        secondaryFunctions.Wait();
        //       // Screenshot("Авторизация-соц-сети-гугл_");
        //       // Wait();
        //        try {
        //            logout.ExitToAuto();
        //        } catch (Exception e) {
        //            secondaryFunctions.text("\nНе удалось выполнить выход из аккаунта Google+");
        //        }
        //        secondaryFunctions.Wait();
    }

    @Test
    public void MoreArticle() throws  IOException {
        article = new DownloadArticlesTest(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        ArrayList articles = new ArrayList();
        articles.add(article.Article1);
        articles.add(article.Article2);
        articles.add(article.Article3);
        articles.add(article.Article4);
        articles.add(article.Article5);

        for(int i=0; i<articles.size(); i++) {
            article.PressInArticleMore();
            article.PressInArticle((By) articles.get(i));
            Assert.assertTrue(driver.findElement(article.Articles).isDisplayed());
            driver.navigate().back();
        }
    }

    @Test
    public void SendMessageInBlog() throws  IOException {
        methods = new AdditionalMethods(driver);
        message = new SendMessageInBlogTest(driver);
        getUrl = new GetUrl(driver);
        registration = new RegistrationTest(driver);

        getUrl.driverGet();
        registration.FirstUserRegistration("FirstName", "LastName", methods.GenerateStr(), "12345678");
        methods.Wait();
        message.EnterBlogText("FirstMessage", "SecondMessage", "ThirdMessage", "TTextMessage", "TagMessage");
        Assert.assertEquals(message.GetTitleText(), "FirstMessage");
        Assert.assertEquals(message.GetSubtitleText(), "SecondMessage");
        Assert.assertEquals(message.GetText(), "TextMessage");

        message.CheckImage();
        Assert.assertEquals(message.GetImageClass(), "img");
        methods.Wait();
        methods.Exit();
    }

    @Test
    public void SharingArticle() throws IOException, InterruptedException {
        methods = new AdditionalMethods(driver);
        sharingArticle = new SharingArticleTest(driver);
        getUrl = new GetUrl(driver);
        //Sharing Fb
        driver.get(getUrl.driverGetStr()+"2016/04/26/9_phrase");
        //driver.get("http://pablo-mel2.qa.lan/2016/04/26/9_phrase");
        sharingArticle.FbSharing("easy2rider2@gmail.com", "knock705b");

//        driver.get("https://www.facebook.com/eero.ettala.1");
//
//        Robot robot = null;
//        try {
//            robot = new Robot();
//        } catch (AWTException e) {
//            e.printStackTrace();
//        }
//        robot.keyPress(KeyEvent.VK_TAB);
//        robot.keyRelease(KeyEvent.VK_TAB);
//        robot.keyPress(KeyEvent.VK_ENTER);
//        robot.keyRelease(KeyEvent.VK_ENTER);
//
//        JavascriptExecutor jse1 = (JavascriptExecutor) driver;
//        jse1.executeScript("scroll(0,550)", "");
        //Assert.assertEquals(sharingArticle.getTextFb(), "9 фраз, которые мы напрасно не говорим детям");
        driver.get(getUrl.driverGetStr()+"2016/04/26/9_phrase");


        // Sharing Vk. Sharing without picture ( 25.01 )
        sharingArticle.VkSharing("89164948378", "123qwe");
//        driver.get("https://vk.com/id365295131");
//
//        JavascriptExecutor jse2 = (JavascriptExecutor) driver;
//        jse2.executeScript("scroll(0,500)", "");
        // Assert.assertEquals(sharingArticle.getTextVk(), "9 фраз, которые мы напрасно не говорим детям");

        // NOT WORK

        //Sharing twitter
        //        driver.get("http://pablo-mel.qa.lan/2016/04/26/9_phrase");
        //        secondaryFunctions.Wait();
        //        TwitterSharing("test153153153@gmail.com", "123qwe");
        //        secondaryFunctions.Wait();
        //
        //        // Sharing Ok
        //        driver.get("http://pablo-mel.qa.lan/2016/04/26/9_phrase");
        //        secondaryFunctions.Wait();
        //        OkSharing("89161604481","123qwe");

    }

    @Test
    public void SubscribeNewsLetter() throws  IOException {
        methods = new AdditionalMethods(driver);
        subscribeNewsLetter = new SubscribeNewsLetterTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        methods.Wait();

        String str1 = "testpablo";
        String str2 = "@binka.me";
        Random r = new Random(System.currentTimeMillis());
        int q = r.nextInt(1111) - 100;
        Random r1 = new Random(System.currentTimeMillis());
        int w = r1.nextInt(111) - 10;
        String str3 = w + str1 + q + str2;
        String str4 = w + str1 + q;

        subscribeNewsLetter.Subscribe(str3);
        assertEquals(driver.getTitle(), "Рассылка mel.fm");
        subscribeNewsLetter.PressInReturnButton();
        methods.Wait();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), "http://mel.fm/");
        methods.Wait();
        driver.get("https://temp-mail.ru/option/change");
        methods.Wait();
        subscribeNewsLetter.ConfirmSubscribe(str4);
        methods.Wait();
        org.testng.Assert.assertEquals(subscribeNewsLetter.getSubjectName(), "Рассылка mel.fm: Подтверждение подписки");
        org.testng.Assert.assertEquals(subscribeNewsLetter.getTitleName(), "Рассылка mel.fm");
        methods.Wait();
        subscribeNewsLetter.Confirm();
        methods.Wait();
        String parentHandle = driver.getWindowHandle();
        for (String childHandle : driver.getWindowHandles()) {
            if (!childHandle.equals(parentHandle)) {
                driver.switchTo().window(childHandle);
            }
        }
        methods.Wait();
        org.testng.Assert.assertEquals(subscribeNewsLetter.getConfirmPageName(), "Рассылка mel.fm");
        subscribeNewsLetter.PressInContinueButton();
        org.testng.Assert.assertEquals(driver.getCurrentUrl(), "http://mel.fm/");
    }

    @Test
    public void Footer() throws IOException {
        methods = new AdditionalMethods(driver);
        footer = new FooterTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        methods.Wait();
        footer.CheckFooter();
    }

    @Test
    public void Profile() throws IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        registration = new RegistrationTest(driver);
        registration.FirstUserRegistration("testname", "testlastname", methods.GenerateStr() , "12345678");
        profile = new ProfileTest(driver);
        methods.Wait();
        profile.OpenProfile();
        Assert.assertEquals(profile.getFirstname(),"testname");
        Assert.assertEquals(profile.getLastname(),"testlastname");
        profile.EditFirstnameLastname("newtestname", "newtestlastname");
        methods.Wait();
        Assert.assertEquals(profile.getFirstname(),"newtestname");
        Assert.assertEquals(profile.getLastname(),"newtestlastname");
        methods.Wait();
        Assert.assertEquals(profile.getHeaderUserName(),"newtestname newtestlastname");
        profile.OpenBlog();
        Assert.assertEquals(profile.getAuthorNameInBlog(),"newtestname newtestlastname");
        profile.OpenProfile();
        profile.EditUserNameAndAbout("username","Люблю читать мел");
        methods.Wait();
        Assert.assertEquals(profile.getHeaderUserName(),"username");
        profile.OpenBlog();
        Assert.assertEquals(profile.getAuthorNameInBlog(),"username");
        Assert.assertEquals(profile.getAuthorQuoteInBlog(),"Люблю читать мел");
        profile.OpenProfile();

        String str5 = profile.getEmail();
        profile.ChangeEmail(methods.GenerateStr());
        String str6 = profile.getEmail();
        methods.Wait();
        profile.Logout();
        autoLogin = new LoginTest(driver);
        methods.Wait();
        autoLogin.Authorisation(str5,"12345678");
        profile.ClickOnCloseButton();
        autoLogin.Authorisation(str6, "12345678");
        methods.Wait();
        profile.OpenProfile();

        profile.EditPhoneAndBirthdate("89165554433","01.01.1990");
        Assert.assertEquals(profile.getPhone(),"89165554433");
        Assert.assertEquals(profile.getBirthdate(),"01.01.1990");
        methods.Wait();
        profile.SelectRoleAndGender();
        methods.Wait();
        Assert.assertEquals(profile.getRoleInput(),"Учитель");
        Assert.assertEquals(profile.getGenderInput(),"Женский");

        profile.DownloadAvatar();
        Assert.assertEquals(profile.GetImageClass(), "img");
        profile.DeleteAvatar();
        methods.Wait();
        Assert.assertEquals(profile.CheckDeleteAvatar(),"Загрузить фото");

        methods.Wait();
        profile.AddLinkInProfile("mel.fm","facebook.com","vk.com","twitter.com");
        methods.Wait();
        profile.OpenBlog();

        Assert.assertEquals(profile.getSiteUrl(),"mel.fm");
        Assert.assertEquals(profile.getFbUrl(),"facebook");
        Assert.assertEquals(profile.getVkUrl(),"вконтакте");
        Assert.assertEquals(profile.getTwitterUrl(),"twitter");
    }

    @Test
    public void DirectiveTest(){
        directiveTest = new DirectiveTest(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGetCurrentUrl("sitemap");
        Assert.assertTrue(driver.findElement(directiveTest.SiteMapText).isDisplayed());
        Assert.assertEquals(directiveTest.GetSiteMapText(), "This XML file does not appear to have any style information associated with it. The document tree is shown below.");

        getUrl.driverGetCurrentUrl("rss/default-all");
        Assert.assertTrue(driver.findElement(directiveTest.RSSText).isDisplayed());

        getUrl.driverGetCurrentUrl("robots.txt");
        Assert.assertTrue(driver.findElement(directiveTest.RobotTxtText).isDisplayed());
        Assert.assertEquals(directiveTest.GetRobotTxtText(), "User-agent: Yandex\n" +
                "Disallow: /*?amp\n" +
                "Disallow: /*?nomr\n" +
                "Disallow: /*?ext\n" +
                "Disallow: /*&amp\n" +
                "Disallow: /*&nomr\n" +
                "Disallow: /*&ext\n" +
                "Disallow: /*?ogimage\n" +
                "Clean-param: #!\n" +
                "Crawl-delay: 1\n" +
                "Host: mel.fm\n" +
                "\n" +
                "User-agent:*\n" +
                "Disallow: /*?amp\n" +
                "Disallow: /*?nomr\n" +
                "Disallow: /*?ext\n" +
                "Disallow: /*&amp\n" +
                "Disallow: /*&nomr\n" +
                "Disallow: /*&ext\n" +
                "Disallow: /*?ogimage\n" +
                "Sitemap: http://mel.fm/sitemap");
    }

    @Test
    public void Search() throws IOException {
        methods = new AdditionalMethods(driver);
        search = new SearchTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        search.SendValidSearchQuery("новости");
        search.CheckArticlesInListSearchResult();
        getUrl.driverGet();
        search.SendInvalidSearchQuery("!#$%&'*+-/=?^_`{|}~");
        Assert.assertEquals(search.getEmptySearchTitle(),"Ничего не найдено");
        search.CheckDeleteInSearchResultInput("мяу");
        Assert.assertEquals(search.GetTextInSearchResultInput(),"мяу");
        getUrl.driverGet();
        search.CheckCloseSearch();
        methods.Wait();
        Assert.assertEquals(search.GetClassSearchBarHidden(),"i-layout__search-bar-container i-layout__search-bar-container_transition i-utils__hidden");
        // Assert.assertTrue(driver.findElement(search.SearchBarHidden).isDisplayed());
    }

    @Test
    public void TagSubscribe() throws IOException {
        setup();
        methods = new AdditionalMethods(driver);
        tagSubscribe = new TagSubscribeTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        tagSubscribe.subscribeOnTagGuest();
        registration = new RegistrationTest(driver);
        registration.FirstUserRegistration("FirstName", "LastName", methods.GenerateStr(), "12345678");
        methods.Wait();

        tagSubscribe.subscribeOnTagUser();
        Assert.assertEquals(tagSubscribe.getTagNameFromMySubscribers(), "Новости");

        tagSubscribe.unsubscribeFromTagUser();
        methods.Wait();
        Assert.assertEquals(tagSubscribe.getTagNameButton(), "Подписаться");

        tagSubscribe.checkUnsubscribe();
        Assert.assertEquals(tagSubscribe.getTextSubscribePage(),"Мой Мел – ваша персональная лента материалов об образовании");

        String str1 = tagSubscribe.getTextFirstButton();
        tagSubscribe.checkTagUpdate();
        methods.Wait();
        String str2 = tagSubscribe.getTextSecondButton();
        tagSubscribe.isStringEquals(str1, str2);
        driver.quit();
    }
}
