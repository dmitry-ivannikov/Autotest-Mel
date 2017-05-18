package AdminTestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminAddingUserTest {
    WebDriver driver;
    AdditionalMethods methods;

    private By Users = By.cssSelector(".i-layout__menu > div > div:nth-child(8)");
    private By NewUserButton = By.cssSelector(".b-user-list__new-user-button > div");
    private By NewUserNameInput = By.cssSelector(".b-user-list__first-name-input > div > input");
    private By NewUserSurnameInput = By.cssSelector(".b-user-list__last-name-input > div > input");
    private By NewUserEmailInput = By.cssSelector(".b-user-list__email-input > div > input");
    private By NewUserSaveButton = By.cssSelector(".b-user-list__save-button > div");

    private By MailEmailInput = By.cssSelector("#mailbox__login");
    private By MailPasswordInput = By.cssSelector("#mailbox__password");
    private By MailEnterButton = By.cssSelector("#mailbox__auth__button");
    private By MailBoxLetter = By.cssSelector(".b-datalist__item__subj");
    private By MailBoxLetterRegistrationButton = By.linkText("Зарегистрироваться");

    private By AdminRegistrationName = By.cssSelector(".i-layout__first-name-input > div > input");
    private By AdminRegistrationSurname = By.cssSelector(".i-layout__last-name-input > div > input");
    private By AdminRegistraionEmail = By.cssSelector(".i-layout__email-input > div > input");
    private By AdminRegistrationPassword = By.cssSelector(".i-layout__password-input > div > input");
    private By AdminRegistrationPasswordConfirm = By.cssSelector(".i-layout__same-password-input > div > input");
    private By AdminRegistrationButton = By.cssSelector(".i-layout__create-button > div");

    public AdminAddingUserTest(WebDriver driver) {
        this.driver = driver;
    }

    public void AddingNewUser(String UserName, String UserSurname, String UserEmail){
        driver.findElement(Users).click();
        driver.findElement(NewUserButton).click();
        driver.findElement(NewUserNameInput).sendKeys(UserName);
        driver.findElement(NewUserSurnameInput).sendKeys(UserSurname);
        driver.findElement(NewUserEmailInput).sendKeys(UserEmail);
        driver.findElement(NewUserSaveButton).click();
    }

    public void EmailAuthorisation(String Email, String Password){
        methods = new AdditionalMethods(driver);

        driver.findElement(MailEmailInput).sendKeys(Email);
        driver.findElement(MailPasswordInput).sendKeys(Password);
        driver.findElement(MailEnterButton).click();
        methods.Wait();
        driver.findElement(MailBoxLetter).click();
    }

    public void RegistrateUser() {
        driver.findElement(MailBoxLetterRegistrationButton).click();
    }

    public String getRegistrationName() {
        String str = driver.findElement(AdminRegistrationName).getAttribute("value");
        return str;
    }

    public String getRegistrationSurname() {
        String str = driver.findElement(AdminRegistrationSurname).getAttribute("value");
        return str;
    }

    public String getRegistrationEmail() {
        String str = driver.findElement(AdminRegistraionEmail).getAttribute("value");
        return str;
    }

    public void EnterPasswordAndConfirm(String firstPassword, String lastPassword){
        driver.findElement(AdminRegistrationPassword).click();
        driver.findElement(AdminRegistrationPassword).sendKeys(firstPassword);
        driver.findElement(AdminRegistrationPasswordConfirm).sendKeys(lastPassword);
        driver.findElement(AdminRegistrationButton).click();
    }
}
