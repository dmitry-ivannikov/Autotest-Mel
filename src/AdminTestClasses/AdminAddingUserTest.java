package AdminTestClasses;

import Helper.AdditionalMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminAddingUserTest {
    WebDriver driver;
    AdditionalMethods methods;

    private By users = By.cssSelector(".i-layout__menu > div > div:nth-child(8)");
    private By newUserButton = By.cssSelector(".b-user-list__new-user-button > div");
    private By newUserNameInput = By.cssSelector(".b-user-list__first-name-input > div > input");
    private By newUserSurnameInput = By.cssSelector(".b-user-list__last-name-input > div > input");
    private By newUserEmailInput = By.cssSelector(".b-user-list__email-input > div > input");
    private By newUserSaveButton = By.cssSelector(".b-user-list__save-button > div");

    private By mailEmailInput = By.cssSelector("#mailbox__login");
    private By mailPasswordInput = By.cssSelector("#mailbox__password");
    private By mailEnterButton = By.cssSelector("#mailbox__auth__button");
    private By mailBoxLetter = By.cssSelector(".b-datalist__item__subj");
    private By mailBoxLetterRegistrationButton = By.linkText("Зарегистрироваться");

    private By adminRegistrationName = By.cssSelector(".i-layout__first-name-input > div > input");
    private By adminRegistrationSurname = By.cssSelector(".i-layout__last-name-input > div > input");
    private By adminRegistraionEmail = By.cssSelector(".i-layout__email-input > div > input");
    private By adminRegistrationPassword = By.cssSelector(".i-layout__password-input > div > input");
    private By adminRegistrationPasswordConfirm = By.cssSelector(".i-layout__same-password-input > div > input");
    private By adminRegistrationButton = By.cssSelector(".i-layout__create-button > div");

    public AdminAddingUserTest(WebDriver driver) {
        this.driver = driver;
    }

    public void addingNewUser(String userName, String userSurname, String userEmail){
        driver.findElement(users).click();
        driver.findElement(newUserButton).click();
        driver.findElement(newUserNameInput).sendKeys(userName);
        driver.findElement(newUserSurnameInput).sendKeys(userSurname);
        driver.findElement(newUserEmailInput).sendKeys(userEmail);
        driver.findElement(newUserSaveButton).click();
    }

    public void emailAuthorisation(String email, String password){
        methods = new AdditionalMethods(driver);

        driver.findElement(mailEmailInput).sendKeys(email);
        driver.findElement(mailPasswordInput).sendKeys(password);
        driver.findElement(mailEnterButton).click();
        methods.Wait(4000);
        driver.findElement(mailBoxLetter).click();
    }

    public void registrateUser() {
        driver.findElement(mailBoxLetterRegistrationButton).click();
    }

    public String getRegistrationName() {
        String str = driver.findElement(adminRegistrationName).getAttribute("value");
        return str;
    }

    public String getRegistrationSurname() {
        String str = driver.findElement(adminRegistrationSurname).getAttribute("value");
        return str;
    }

    public String getRegistrationEmail() {
        String str = driver.findElement(adminRegistraionEmail).getAttribute("value");
        return str;
    }

    public void enterPasswordAndConfirm(String firstPassword, String lastPassword){
        driver.findElement(adminRegistrationPassword).click();
        driver.findElement(adminRegistrationPassword).sendKeys(firstPassword);
        driver.findElement(adminRegistrationPasswordConfirm).sendKeys(lastPassword);
        driver.findElement(adminRegistrationButton).click();
    }
}
