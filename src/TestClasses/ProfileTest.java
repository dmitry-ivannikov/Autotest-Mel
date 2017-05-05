package TestClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Helper.AdditionalMethods;

/**
 * Created by Mary on 16.04.2017.
 */
public class ProfileTest {
    private WebDriver driver;
    private AdditionalMethods methods;
    //HeaderDropdown
    private By HeaderDropdownIcon = By.cssSelector(".b-header__dropdown-icon");
    private By ProfileButtonInList = By.cssSelector(".g-list_dropdown-menu-theme > a:nth-child(1)");
    private By BlogButtonInList = By.cssSelector(".g-list_pablo.g-list_dropdown-menu-theme > a:nth-child(3)");
    private By LogoutButtonInList = By.cssSelector(".b-header__logout-button");
    //ProfileTab
    private By FirstnameInput = By.cssSelector(".b-profile__input-firstname .g-input__input");
    private By LastnameInput = By.cssSelector(".b-profile__input-lastname > input");
    private By SaveButton = By.cssSelector(".b-profile__save");
    private By HeaderUserName = By.cssSelector(".b-header__user-name");
    private By UserNameInput = By.cssSelector(".b-profile__input-displayname > input");
    private By AboutTextarea = By.cssSelector(".b-profile__about > div > textarea");
    private By EmailInput = By.cssSelector(".b-profile__input-email > input");
    private By PhoneInput = By.cssSelector(".b-profile__input-phone > input");
    private By BirthdateInput = By.cssSelector(".b-profile__input-birthdate > input");
    private By SelectRoleDropdownIcon = By.cssSelector(".b-profile__select-role> .g-dropdown__opener");
    private By SelectGenderDropdownIcon = By.cssSelector(".b-profile__select-gender> .g-dropdown__opener");
    private By RoleInList = By.xpath("/html/body/div[1]/div[4]/div/div/div[1]/div/div[2]/div[2]/div[7]/div[2]/div[2]/div/div[2]");
    private By GenderInList = By.xpath("/html/body/div[1]/div[4]/div/div/div[1]/div/div[2]/div[2]/div[9]/div[2]/div[2]/div/div[2]");
    private By RoleInput = By.cssSelector(".b-profile__select-role >.g-dropdown__opener");
    private By GenderInput = By.cssSelector(".b-profile__select-gender >.g-dropdown__opener");
    //Blog
    private By AuthorNameInBlog = By.cssSelector(".b-pb-author__name");
    private By AuthorQuoteInBlog = By.cssSelector(".b-pb-author__quote");
    //Avatar
    private By DownloadAvatarButton = By.cssSelector(".b-profile__avatar >.b-profile__avatar-control-btn");
    private By ImageClass = By.cssSelector(".b-profile__avatar > img");
    private By DeleteAvatarButton = By.cssSelector(".b-profile__avatar > div");
    private By CloseButtonInLoginPopup= By.cssSelector(".g-modal__close-icon > div");
    //SocialNetworksTab
    private By SocialNetworksTab = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By SiteUrlInput = By.cssSelector(".b-profile__input-siteurl > input");
    private By FbInput = By.cssSelector(".b-profile__input-fb > input");
    private By VkInput = By.cssSelector(".b-profile__input-vk > input");
    private By TwitterInput = By.cssSelector(".b-profile__input-tw > input");
    private By SaveButtonInSocialNetworksTab = By.cssSelector(".b-profile__save");
    private By SiteUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(1)");
    private By FbUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(2)");
    private By VkUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(3)");
    private By TwitterUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(4)");


    public ProfileTest(WebDriver driver) {
        this.driver = driver;
    }
    private void ClickOnHeaderDropdownIcon() {
        driver.findElement(HeaderDropdownIcon).click();
    }

    private void ClickOnProfileButtonInList() {
        driver.findElement(ProfileButtonInList).click();
    }

    public String getFirstname() {
        String str = driver.findElement(FirstnameInput).getAttribute("value");
        return str;
    }

    public String getLastname() {
        String str = driver.findElement(LastnameInput).getAttribute("value");
        return str;
    }

    private void ClickOnSaveButton(){
        driver.findElement(SaveButton).click();
    }

    public  String getHeaderUserName() {
        String str = driver.findElement(HeaderUserName).getText();
        return str;
    }

    public String getAuthorNameInBlog(){
        String str = driver.findElement(AuthorNameInBlog).getText();
        return str;
    }

    public String getAuthorQuoteInBlog(){
        String str = driver.findElement(AuthorQuoteInBlog).getText();
        return str;
    }
    public String getEmail() {
        String str = driver.findElement(EmailInput).getAttribute("value");
        return str;
    }

    public String getPhone() {
        String str = driver.findElement(PhoneInput).getAttribute("value");
        return str;
    }
    public  String getBirthdate() {
        String str = driver.findElement(BirthdateInput).getAttribute("value");
        return str;
    }

    public String getRoleInput() {
        String str = driver.findElement(RoleInput).getText();
        return str;
    }

    public String getGenderInput() {
        String str = driver.findElement(GenderInput).getText();
        return str;
    }

    public void ClickOnCloseButton(){
        driver.findElement(CloseButtonInLoginPopup).click();
    }

    private void ClearInputs() {
        driver.findElement(FirstnameInput).clear();
        driver.findElement(LastnameInput).clear();
    }

    public  String getSiteUrl() {
        String str = driver.findElement(SiteUrlInBlog).getText();
        return str;
    }
    public  String getFbUrl() {
        String str = driver.findElement(FbUrlInBlog).getText();
        return str;
    }
    public  String getVkUrl() {
        String str = driver.findElement(VkUrlInBlog).getText();
        return str;
    }
    public  String getTwitterUrl() {
        String str = driver.findElement(TwitterUrlInBlog).getText();
        return str;
    }

    public void OpenProfile(){
        driver.findElement(HeaderDropdownIcon).click();
        ClickOnProfileButtonInList();
    }

    public void EditFirstnameLastname(String strFirstnameInput, String strLastnameInput){
        ClearInputs();
        driver.findElement(FirstnameInput).click();
        driver.findElement(FirstnameInput).sendKeys(strFirstnameInput);
        driver.findElement(LastnameInput).click();
        driver.findElement(LastnameInput).sendKeys(strLastnameInput);
        ClickOnSaveButton();}

    public void OpenBlog(){
        ClickOnHeaderDropdownIcon();
        driver.findElement(BlogButtonInList).click();
    }

    public void EditUserNameAndAbout(String strUserNameInput, String strAboutTextarea){
        driver.findElement(UserNameInput).click();
        driver.findElement(UserNameInput).sendKeys(strUserNameInput);
        driver.findElement(AboutTextarea).click();
        driver.findElement(AboutTextarea).sendKeys(strAboutTextarea);
        ClickOnSaveButton();
    }

    public void ChangeEmail(String strEmailInput){
        driver.findElement(EmailInput).click();
        driver.findElement(EmailInput).clear();
        driver.findElement(EmailInput).sendKeys(strEmailInput);
        ClickOnSaveButton();
    }

    public void Logout(){
        ClickOnHeaderDropdownIcon();
        driver.findElement(LogoutButtonInList).click();
    }

    public void EditPhoneAndBirthdate(String strPhoneInput, String strBirthdateInput){
        driver.findElement(PhoneInput).click();
        driver.findElement(PhoneInput).sendKeys(strPhoneInput);
        driver.findElement(BirthdateInput).click();
        driver.findElement(BirthdateInput).sendKeys(strBirthdateInput);
        ClickOnSaveButton();
    }

    public void SelectRoleAndGender(){
        driver.findElement(SelectRoleDropdownIcon).click();
        driver.findElement(RoleInList).click();
        driver.findElement(SelectGenderDropdownIcon).click();
        driver.findElement(GenderInList).click();
        ClickOnSaveButton();
    }

    public void DownloadAvatar() {
        methods = new AdditionalMethods(driver);
        driver.findElement(DownloadAvatarButton).click();
        methods.ImgageDownload();
        driver.findElement(DeleteAvatarButton);
    }
    //check download avatar
    public String GetImageClass() {
        String str = driver.findElement(ImageClass).getTagName();
        return str;
    }

    public void DeleteAvatar(){
        driver.findElement(DeleteAvatarButton).click();
    }

    public String CheckDeleteAvatar(){
        String str = driver.findElement(DownloadAvatarButton).getText();
        return str;
    }

    public void AddLinkInProfile(String strSiteUrlInput,String strFbInput,String strVkInput,String strTwitterInput){
        driver.findElement(SocialNetworksTab).click();
        driver.findElement(SiteUrlInput).click();
        driver.findElement(SiteUrlInput).sendKeys(strSiteUrlInput);
        driver.findElement(FbInput).click();
        driver.findElement(FbInput).sendKeys(strFbInput);
        driver.findElement(VkInput).click();
        driver.findElement(VkInput).sendKeys(strVkInput);
        driver.findElement(TwitterInput).click();
        driver.findElement(TwitterInput).sendKeys(strTwitterInput);
        driver.findElement(SaveButtonInSocialNetworksTab).click();
    }

}

