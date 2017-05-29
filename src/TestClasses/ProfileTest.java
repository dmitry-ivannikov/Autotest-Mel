package TestClasses;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import Helper.AdditionalMethods;

public class ProfileTest {
    private WebDriver driver;
    private AdditionalMethods methods;

    //HeaderDropdown
    private By headerDropdownIcon = By.cssSelector(".b-header__dropdown-icon");
    private By profileButtonInList = By.cssSelector(".g-list_dropdown-menu-theme > a:nth-child(1)");
    private By blogButtonInList = By.cssSelector(".g-list_pablo.g-list_dropdown-menu-theme > a:nth-child(3)");
    private By logoutButtonInList = By.cssSelector(".b-header__logout-button");
    //ProfileTab
    private By firstnameInput = By.cssSelector(".b-profile__input-firstname .g-input__input");
    private By lastnameInput = By.cssSelector(".b-profile__input-lastname > input");
    private By saveButton = By.cssSelector(".b-profile__save");
    private By headerUserName = By.cssSelector(".b-header__user-name");
    private By userNameInput = By.cssSelector(".b-profile__input-displayname > input");
    private By aboutTextarea = By.cssSelector(".b-profile__about > div > textarea");
    private By emailInput = By.cssSelector(".b-profile__input-email > input");
    private By phoneInput = By.cssSelector(".b-profile__input-phone > input");
    private By birthdateInput = By.cssSelector(".b-profile__input-birthdate > input");
    private By selectRoleDropdownIcon = By.cssSelector(".b-profile__select-role> .g-dropdown__opener");
    private By selectGenderDropdownIcon = By.cssSelector(".b-profile__select-gender> .g-dropdown__opener");
    private By roleInList = By.xpath("/html/body/div[2]/div[4]/div/div/div[1]/div/div[2]/div[2]/div[7]/div[2]/div[2]/div/div[2]");
    private By genderInList = By.xpath("/html/body/div[2]/div[4]/div/div/div[1]/div/div[2]/div[2]/div[9]/div[2]/div[2]/div/div[2]");
    private By roleInput = By.cssSelector(".b-profile__select-role >.g-dropdown__opener");
    private By genderInput = By.cssSelector(".b-profile__select-gender >.g-dropdown__opener");
    //Blog
    private By authorNameInBlog = By.cssSelector(".b-pb-author__name");
    private By authorQuoteInBlog = By.cssSelector(".b-pb-author__quote");
    //Avatar
    private By downloadAvatarButton = By.cssSelector(".b-profile__avatar >.b-profile__avatar-control-btn");
    private By imageClass = By.cssSelector(".b-profile__avatar > img");
    private By deleteAvatarButton = By.cssSelector(".b-profile__avatar > div");
    private By closeButtonInLoginPopup= By.cssSelector(".g-modal__close-icon > div");
    //SocialNetworksTab
    private By socialNetworksTab = By.cssSelector(".g-tab__tabs > div:nth-child(2)");
    private By siteUrlInput = By.cssSelector(".b-profile__input-siteurl > input");
    private By fbInput = By.cssSelector(".b-profile__input-fb > input");
    private By vkInput = By.cssSelector(".b-profile__input-vk > input");
    private By twitterInput = By.cssSelector(".b-profile__input-tw > input");
    private By saveButtonInSocialNetworksTab = By.cssSelector(".b-profile__save");
    private By siteUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(1)");
    private By fbUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(2)");
    private By vkUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(3)");
    private By twitterUrlInBlog = By.cssSelector(".b-pb-author__sites > a:nth-child(4)");


    public ProfileTest(WebDriver driver) {
        this.driver = driver;
    }

    private void clickOnHeaderDropdownIcon() {
        driver.findElement(headerDropdownIcon).click();
    }

    private void clickOnProfileButtonInList() {
        driver.findElement(profileButtonInList).click();
    }

    public String getFirstname() {
        String str = driver.findElement(firstnameInput).getAttribute("value");
        return str;
    }

    public String getLastname() {
        String str = driver.findElement(lastnameInput).getAttribute("value");
        return str;
    }

    private void clickOnSaveButton(){
        driver.findElement(saveButton).click();
    }

    public  String getHeaderUserName() {
        String str = driver.findElement(headerUserName).getText();
        return str;
    }

    public String getAuthorNameInBlog(){
        String str = driver.findElement(authorNameInBlog).getText();
        return str;
    }

    public String getAuthorQuoteInBlog(){
        String str = driver.findElement(authorQuoteInBlog).getText();
        return str;
    }
    public String getEmail() {
        String str = driver.findElement(emailInput).getAttribute("value");
        return str;
    }

    public String getPhone() {
        String str = driver.findElement(phoneInput).getAttribute("value");
        return str;
    }
    public  String getBirthdate() {
        String str = driver.findElement(birthdateInput).getAttribute("value");
        return str;
    }

    public String getRoleInput() {
        String str = driver.findElement(roleInput).getText();
        return str;
    }

    public String getGenderInput() {
        String str = driver.findElement(genderInput).getText();
        return str;
    }

    public void clickOnCloseButton(){
        driver.findElement(closeButtonInLoginPopup).click();
    }

    private void clearInputs() {
        driver.findElement(firstnameInput).clear();
        driver.findElement(lastnameInput).clear();
    }

    public String getSiteUrl() {
        String str = driver.findElement(siteUrlInBlog).getText();
        return str;
    }
    public String getFbUrl() {
        String str = driver.findElement(fbUrlInBlog).getText();
        return str;
    }
    public String getVkUrl() {
        String str = driver.findElement(vkUrlInBlog).getText();
        return str;
    }
    public String getTwitterUrl() {
        String str = driver.findElement(twitterUrlInBlog).getText();
        return str;
    }

    public void openProfile(){
        driver.findElement(headerDropdownIcon).click();
        clickOnProfileButtonInList();
    }

    public void editFirstnameLastname(String firstname, String lastname){
        clearInputs();
        driver.findElement(firstnameInput).click();
        driver.findElement(firstnameInput).sendKeys(firstname);
        driver.findElement(lastnameInput).click();
        driver.findElement(lastnameInput).sendKeys(lastname);
        clickOnSaveButton();
    }

    public void openBlog(){
        clickOnHeaderDropdownIcon();
        driver.findElement(blogButtonInList).click();
    }

    public void editUserNameAndAbout(String userName, String Textarea){
        driver.findElement(userNameInput).click();
        driver.findElement(userNameInput).sendKeys(userName);
        driver.findElement(aboutTextarea).click();
        driver.findElement(aboutTextarea).sendKeys(Textarea);
        clickOnSaveButton();
    }

    public void changeEmail(String email){
        driver.findElement(emailInput).click();
        driver.findElement(emailInput).clear();
        driver.findElement(emailInput).sendKeys(email);
        clickOnSaveButton();
    }

    public void logout(){
        clickOnHeaderDropdownIcon();
        driver.findElement(logoutButtonInList).click();
    }

    public void editPhoneAndBirthdate(String phone, String birthdate){
        driver.findElement(phoneInput).click();
        driver.findElement(phoneInput).sendKeys(phone);
        driver.findElement(birthdateInput).click();
        driver.findElement(birthdateInput).sendKeys(birthdate);
        clickOnSaveButton();
    }

    public void selectRoleAndGender(){
        driver.findElement(selectRoleDropdownIcon).click();
        driver.findElement(roleInList).click();
        driver.findElement(selectGenderDropdownIcon).click();
        driver.findElement(genderInList).click();
        clickOnSaveButton();
    }

    public void downloadAvatar() {
        methods = new AdditionalMethods(driver);
        driver.findElement(downloadAvatarButton).click();
        methods.imgageDownload();
        driver.findElement(deleteAvatarButton);
    }
    //check download avatar
    public String getImageClass() {
        String str = driver.findElement(imageClass).getTagName();
        return str;
    }

    public void deleteAvatar(){
        driver.findElement(deleteAvatarButton).click();
    }

    public String checkDeleteAvatar(){
        String str = driver.findElement(downloadAvatarButton).getText();
        return str;
    }

    public void addLinkInProfile(String SiteUrl,String fbText,String vkText,String twitterText){
        driver.findElement(socialNetworksTab).click();
        driver.findElement(siteUrlInput).click();
        driver.findElement(siteUrlInput).sendKeys(SiteUrl);
        driver.findElement(fbInput).click();
        driver.findElement(fbInput).sendKeys(fbText);
        driver.findElement(vkInput).click();
        driver.findElement(vkInput).sendKeys(vkText);
        driver.findElement(twitterInput).click();
        driver.findElement(twitterInput).sendKeys(twitterText);
        driver.findElement(saveButtonInSocialNetworksTab).click();
    }

}

