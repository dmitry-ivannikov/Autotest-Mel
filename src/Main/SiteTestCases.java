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
    private LandingHasbro hasbro;

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
    public void authorisation() throws IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        autoLogin = new LoginTest(driver);
        logout = new LogoutTest(driver);

        getUrl.driverGet();
        autoLogin.authorisation("estendr@gmail.com", "12345678");
        methods.Wait(1000);
        Assert.assertEquals(autoLogin.getHomePageDashboardName(), "Vladimir Petrov");
        methods.exit();
        Assert.assertEquals(logout.checkEnterButton(), "ВХОД");
    }

    @Test
    public void registration() throws IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        registration = new RegistrationTest(driver);
        logout = new LogoutTest(driver);

        getUrl.driverGet();
        // регистрация с нажатием кнопки "Вход"
        registration.firstUserRegistration("!#$%&'*+-/=?^_`{|}~", "LastName", methods.generateStr(), "12345678");
        registration.pressInRegistrationButton();
        // регистрация с невалидными данными
        registration.registrationWithInvalidData("FirstName", "!#$%&'*+-/=?^_`{|}~", methods.generateStr(), "12345678");
        registration.registrationWithInvalidData("FirstName", "LastName", "ab(c)d,e:f;g<h>i[jk]l@example.com", "12345678");
        // регистрация с валидными данными
        registration.registrationWithValidData("FirstName", "LastName", methods.generateStr(), "12345678");
        methods.Wait(3000);
        Assert.assertEquals(registration.getHeaderUserName(), "FirstName LastName");
        methods.exit();
        Assert.assertEquals(logout.checkEnterButton(), "ВХОД");
    }

    @Test
    public void addAndDeleteComment() throws  IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        comment = new AddCommentTest(driver);
        autoLogin = new LoginTest(driver);
        logout = new LogoutTest(driver);

        getUrl.driverGet();
        autoLogin.authorisation("estendr@gmail.com", "12345678");
        methods.Wait(1000);
        Assert.assertEquals(autoLogin.getHomePageDashboardName(), "Vladimir Petrov");
        comment.insertAndAddComment("TestComment");
        comment.deleteComment();
        methods.exit();
        Assert.assertEquals(logout.checkEnterButton(), "ВХОД");
    }

    @Test
    public void authorisationSocial() throws  IOException {
        methods = new AdditionalMethods(driver);
        authorisationSocial = new SocialNetworksAuthorisationTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        methods.Wait(1000);
        //Facebook
        authorisationSocial.facebookAuthorisation("easy2rider2@gmail.com", "knock705b");
        Assert.assertEquals(authorisationSocial.getHomePageDashboardName(), "Eero Ettala");
        methods.outputFromAnAccountSocialLogin();

        methods.Wait(1000);
        //Vk
        authorisationSocial.vkAuthorisation("89164948378", "123qwe");
        Assert.assertEquals(authorisationSocial.getHomePageDashboardName(), "Ваня");
        methods.outputFromAnAccountSocialLogin();

        // Assert.assertEquals(logout.CheckEnterButton(), "ВХОД");
        // Google+
        getUrl.getExternalUrl();
        authorisationSocial.googleAuthorisation("test153153153", "test153123qwe");
        methods.Wait(2000);
        Assert.assertEquals(authorisationSocial.getHomePageDashboardName(), "Heikki Sorsa");
        methods.outputFromAnAccountSocialLogin();
    }

    @Test
    public void moreArticle() throws  IOException {
        article = new DownloadArticlesTest(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        ArrayList articles = new ArrayList();
        articles.add(article.article1);
        articles.add(article.article2);
        articles.add(article.article3);
        articles.add(article.article4);

        for(int i=0; i<articles.size(); i++) {
            article.pressInArticleMore();
            article.pressInArticle((By) articles.get(i));
            Assert.assertTrue(driver.findElement(article.articles).isDisplayed());
            driver.navigate().back();
        }
        //methods.getBrowserLogs();
    }

    @Test
    public void sendMessageInBlog() throws  IOException {
        methods = new AdditionalMethods(driver);
        message = new SendMessageInBlogTest(driver);
        getUrl = new GetUrl(driver);
        registration = new RegistrationTest(driver);

        getUrl.driverGet();
        registration.firstUserRegistration("FirstName", "LastName", methods.generateStr(), "12345678");
        methods.Wait(1500);
        message.enterBlogText("FirstMessage", "SecondMessage", "ThirdMessage", " TextMessage", "TagMessage");
        Assert.assertEquals(message.getTitleText(), "FirstMessage");
        Assert.assertEquals(message.getSubtitleText(), "SecondMessage");
        Assert.assertEquals(message.getText(), "TextMessage");

        message.checkImage();
        methods.Wait(3500);
        Assert.assertEquals(message.getImageClass(), "img");
        methods.exit();
       // methods.getBrowserLogs();
    }

    @Test
    public void sharingArticle() throws IOException, InterruptedException {
        methods = new AdditionalMethods(driver);
        sharingArticle = new SharingArticleTest(driver);
        getUrl = new GetUrl(driver);
        ArrayList<String> expectedResult = new ArrayList();
        expectedResult.add("easy2rider2@gmail.com");
        expectedResult.add("knock705b");
        expectedResult.add("9 фраз, которые мы напрасно не говорим детям");
        expectedResult.add("89164948378");
        expectedResult.add("123qwe");
        expectedResult.add("89161604481");
        
        //Sharing Fb
        driver.get(getUrl.driverGetStr()+"2016/04/26/9_phrase");
        sharingArticle.sharingFb(expectedResult);

        // Sharing Vk
        driver.navigate().refresh();
        sharingArticle.sharingVk(expectedResult);

        // Sharing Ok
        driver.navigate().refresh();
        sharingArticle.sharingOk(expectedResult);

        // Sharing twitter
        driver.navigate().refresh();
        sharingArticle.twitterSharing(expectedResult);
    }

    @Test
    public void subscribeNewsLetter() throws  IOException {
        methods = new AdditionalMethods(driver);
        subscribeNewsLetter = new SubscribeNewsLetterTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        methods.Wait(500);

        String str1 = "testpablo";
        String str2 = "@binka.me";
        Random r = new Random(System.currentTimeMillis());
        int q = r.nextInt(1111) - 100;
        Random r1 = new Random(System.currentTimeMillis());
        int w = r1.nextInt(111) - 10;
        String email = w + str1 + q + str2;
        String email2 = w + str1 + q;

        subscribeNewsLetter.subscribe(email);
        Assert.assertEquals(driver.getTitle(), "Рассылка mel.fm");
        subscribeNewsLetter.pressInReturnButton();
        methods.Wait(500);
        Assert.assertEquals(driver.getCurrentUrl(), "http://mel.fm/");
        driver.get("https://temp-mail.ru/option/change");
        methods.Wait(500);
        subscribeNewsLetter.confirmSubscribe(email2);
        Assert.assertEquals(subscribeNewsLetter.getSubjectName(), "Рассылка mel.fm: Подтверждение подписки");
        Assert.assertEquals(subscribeNewsLetter.getTitleName(), "Рассылка mel.fm");
        methods.Wait(500);
        subscribeNewsLetter.confirm();
        methods.Wait(500);
        String parentHandle = driver.getWindowHandle();
        for (String childHandle : driver.getWindowHandles()) {
            if (!childHandle.equals(parentHandle)) {
                driver.switchTo().window(childHandle);
            }
        }
        methods.Wait(500);
        Assert.assertEquals(subscribeNewsLetter.getConfirmPageName(), "Рассылка mel.fm");
        subscribeNewsLetter.pressInContinueButton();
        Assert.assertEquals(driver.getCurrentUrl(), "http://mel.fm/");
    }

    @Test
    public void footer() throws IOException {
        methods = new AdditionalMethods(driver);
        footer = new FooterTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        footer.checkFooterRubric(getUrl.driverGetStr()+"rubric/school",
                                 getUrl.driverGetStr()+"rubric/highschool",
                                 getUrl.driverGetStr()+"rubric/fun",
                                 getUrl.driverGetStr()+"rubric/family",
                                 getUrl.driverGetStr()+"rubric/games",
                                 getUrl.driverGetStr()+"blogs",
                                 getUrl.driverGetStr()+"author/afisha-mela");
        footer.checkFooterContacts(getUrl.driverGetStr()+"page/contacts",
                                   "Контакты",
                                   getUrl.driverGetStr()+"page/advertising-proposal",
                                   "Реклама");
        footer.checkFooterMediakit("https://www.dropbox.com/s/p2rizp286zu7kcm/Mel_Mediakit.pdf?dl=0","https://www.dropbox.com/s/zm1jzitag2umqc5/Mel_Pricelist.pdf?dl=0");
        footer.checkFooterLinks(getUrl.driverGetStr()+"page/terms-of-use",
                                "Пользовательское соглашение",
                                "https://www.facebook.com/melfmru",
                                "https://vk.com/melfmru",
                                "https://twitter.com/melfmru",
                                "https://ok.ru/group/57557432139808");
        //methods.getBrowserLogs();
    }

    @Test
    public void profile() throws IOException {
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);
        profile = new ProfileTest(driver);

        getUrl.driverGet();
        registration = new RegistrationTest(driver);
        registration.firstUserRegistration("testname", "testlastname", methods.generateStr() , "12345678");
        methods.Wait(1500);
        profile.openProfile();
        Assert.assertEquals(profile.getFirstname(),"testname");
        Assert.assertEquals(profile.getLastname(),"testlastname");
        profile.editFirstnameLastname("newtestname", "newtestlastname");
        methods.Wait(500);
        Assert.assertEquals(profile.getFirstname(),"newtestname");
        Assert.assertEquals(profile.getLastname(),"newtestlastname");
        methods.Wait(500);
        Assert.assertEquals(profile.getHeaderUserName(),"newtestname newtestlastname");
        profile.openBlog();
        Assert.assertEquals(profile.getAuthorNameInBlog(),"newtestname newtestlastname");
        profile.openProfile();
        profile.editUserNameAndAbout("username","Люблю читать мел");
        methods.Wait(500);
        Assert.assertEquals(profile.getHeaderUserName(),"username");
        profile.openBlog();
        Assert.assertEquals(profile.getAuthorNameInBlog(),"username");
        Assert.assertEquals(profile.getAuthorQuoteInBlog(),"Люблю читать мел");
        profile.openProfile();

        String str5 = profile.getEmail();
        profile.changeEmail(methods.generateStr());
        String str6 = profile.getEmail();
        methods.Wait(500);
        profile.logout();
        autoLogin = new LoginTest(driver);
        methods.Wait(500);
        autoLogin.authorisation(str5,"12345678");
        profile.clickOnCloseButton();
        autoLogin.authorisation(str6, "12345678");
        methods.Wait(500);
        profile.openProfile();

        profile.editPhoneAndBirthdate("89165554433","01.01.1990");
        Assert.assertEquals(profile.getPhone(),"89165554433");
        Assert.assertEquals(profile.getBirthdate(),"01.01.1990");
        methods.Wait(500);
        profile.selectRoleAndGender();
        methods.Wait(500);
        Assert.assertEquals(profile.getRoleInput(),"Учитель");
        Assert.assertEquals(profile.getGenderInput(),"Женский");

        profile.downloadAvatar();
        methods.Wait(1000);
        Assert.assertEquals(profile.getImageClass(), "img");
        profile.deleteAvatar();
        methods.Wait(2000);
        Assert.assertEquals(profile.checkDeleteAvatar(),"Загрузить фото");

        methods.Wait(500);
        profile.addLinkInProfile("mel.fm","facebook.com","vk.com","twitter.com");
        methods.Wait(500);
        profile.openBlog();

        Assert.assertEquals(profile.getSiteUrl(),"mel.fm");
        Assert.assertEquals(profile.getFbUrl(),"facebook");
        Assert.assertEquals(profile.getVkUrl(),"вконтакте");
        Assert.assertEquals(profile.getTwitterUrl(),"twitter");

        //methods.getBrowserLogs();
    }

    @Test
    public void directiveTest(){
        directiveTest = new DirectiveTest(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGetCurrentUrl("sitemap");
        Assert.assertTrue(driver.findElement(directiveTest.siteMapText).isDisplayed());
        Assert.assertEquals(directiveTest.getSiteMapText(), "http://www.sitemaps.org/schemas/sitemap/0.9");

        getUrl.driverGetCurrentUrl("rss/default-all");
        Assert.assertTrue(driver.findElement(directiveTest.rssText).isDisplayed());

        getUrl.driverGetCurrentUrl("robots.txt");
        Assert.assertTrue(driver.findElement(directiveTest.robotTxtText).isDisplayed());
        Assert.assertEquals(directiveTest.getRobotTxtText(), "User-agent: Yandex\n" +
                "Disallow: ?amp\n" +
                "Disallow: ?nomr\n" +
                "Disallow: ?ext\n" +
                "Disallow: &amp\n" +
                "Disallow: &nomr\n" +
                "Disallow: &ext\n" +
                "Disallow: ?ogimage\n" +
                "Clean-param: #!\n" +
                "Crawl-delay: 1\n" +
                "Host: mel.fm\n" +
                "\n" +
                "User-agent:*\n" +
                "Disallow: ?amp\n" +
                "Disallow: ?nomr\n" +
                "Disallow: ?ext\n" +
                "Disallow: &amp\n" +
                "Disallow: &nomr\n" +
                "Disallow: &ext\n" +
                "Disallow: ?ogimage\n" +
                "Sitemap: http://mel.fm/sitemap");
       // methods.getBrowserLogs();
    }

    @Test
    public void search() throws IOException {
        methods = new AdditionalMethods(driver);
        search = new SearchTest(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGet();
        search.sendValidSearchQuery("новости");
        search.checkArticlesInListSearchResult();
        getUrl.driverGet();
        search.sendInvalidSearchQuery("!#$%&'*+-/=?^_`{|}~");
        Assert.assertEquals(search.getEmptySearchTitle(),"Ничего не найдено, повторите запрос или посмотрите три наших самых свежих материала:");
        search.checkDeleteInSearchResultInput("мяу");
        Assert.assertEquals(search.getTextInSearchResultInput(),"мяу");
        getUrl.driverGet();
        search.checkCloseSearch();
        methods.Wait(4000);
        Assert.assertEquals(search.getClassSearchBarHidden(),"i-layout__search-bar-container i-layout__search-bar-container_transition i-utils__hidden");
        // Assert.assertTrue(driver.findElement(search.SearchBarHidden).isDisplayed());
        //methods.getBrowserLogs();
    }

    @Test
    public void tagSubscribe() throws IOException {
        methods = new AdditionalMethods(driver);
        tagSubscribe = new TagSubscribeTest(driver);
        getUrl = new GetUrl(driver);
        registration = new RegistrationTest(driver);

        getUrl.driverGet();
        tagSubscribe.subscribeOnTagGuest();
        registration.firstUserRegistration("FirstName", "LastName", methods.generateStr(), "12345678");
        methods.Wait(3000);

        tagSubscribe.subscribeOnTagUser();
        methods.Wait(3000);
        Assert.assertEquals(tagSubscribe.getTagNameFromMySubscribers(), "Новости");

        tagSubscribe.unsubscribeFromTagUser();
        methods.Wait(1000);
        Assert.assertEquals(tagSubscribe.getTagNameButton(), "Подписаться");

        tagSubscribe.checkUnsubscribe();
        Assert.assertEquals(tagSubscribe.getTextSubscribePage(),"Мой Мел – ваша персональная лента материалов об образовании");

        String str1 = tagSubscribe.getTextFirstButton();
        tagSubscribe.checkTagUpdate();
        methods.Wait(500);
        String str2 = tagSubscribe.getTextSecondButton();
        tagSubscribe.isStringEquals(str1, str2);
        //methods.getBrowserLogs();
    }

    @Test
    public void landingHasbro(){
        hasbro = new LandingHasbro(driver);
        methods = new AdditionalMethods(driver);
        getUrl = new GetUrl(driver);

        getUrl.driverGetCurrentUrl("hasbro/");
        Assert.assertEquals(driver.getTitle(),"Hasbro");

        ArrayList<String> expectedResult = new ArrayList();
        expectedResult.add("Hasbro Gaming - Главная");
        expectedResult.add("Образовательный портал Мел.ФМ. Статьи о школе и обучении в России и по всему миру");
        expectedResult.add("Hasbro Gaming - Игры");
        expectedResult.add("Hasbro Gaming - Монополия: Россия");
        expectedResult.add("Hasbro Gaming - Главная");
        expectedResult.add("Hasbro Gaming - Пирог в лицо – Игры Hasbro");
        expectedResult.add("Hasbro Gaming - Игры");
        expectedResult.add("Игрушки Hasbro - купить игры и игрушки Хасбро, цены в интернет-магазине игрушек Hasbro - Детский мир");
        expectedResult.add("Hasbro Игры | ВКонтакте");
        expectedResult.add("Страница не найдена | Facebook");
        expectedResult.add("Hasbro Gaming - Главная");

        hasbro.checkButtons(expectedResult);
    }
}
