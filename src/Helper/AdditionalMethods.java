package Helper;

import TestClasses.LogoutTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Set;


public class AdditionalMethods {

    WebDriver driver;
    LogoutTest logout;

    public AdditionalMethods(WebDriver driver) {
        this.driver = driver;
    }

    public void Wait() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    // function logout
    public void Exit() {
        logout = new LogoutTest(driver);
        logout.ExitFromAccount();
    }

    public void OutputFromAnAccountSocialLogin() {
        logout = new LogoutTest(driver);
        logout.ExitToAuthorisationSocial();
    }

    public String GenerateStr(){
        String str1 = "testpablo";
        String str2 = "@rootfest.net";

        int a = 0; // initial range
        int b = 1000; // the final value of the range
        int FirstRandomNumber = a + (int) (Math.random() * b);
        int SecondRandomNumber = a + (int) (Math.random() * b);

        String str3 = FirstRandomNumber + str1 + SecondRandomNumber + str2;
        return str3;
    }

    public void ImgageDownload() {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        r.keyPress(KeyEvent.VK_C);        // C
        r.keyRelease(KeyEvent.VK_C);

        r.keyPress(KeyEvent.VK_SHIFT);      // :
        r.keyPress(KeyEvent.VK_SEMICOLON);
        r.keyRelease(KeyEvent.VK_SEMICOLON);
        r.keyRelease(KeyEvent.VK_SHIFT);

        r.keyPress(KeyEvent.VK_BACK_SLASH);    // / (slash)
        r.keyRelease(KeyEvent.VK_BACK_SLASH);

        r.keyPress(KeyEvent.VK_1);    // 1
        r.keyRelease(KeyEvent.VK_1);

        r.keyPress(KeyEvent.VK_PERIOD);    // .
        r.keyRelease(KeyEvent.VK_PERIOD);

        r.keyPress(KeyEvent.VK_J);    // j
        r.keyRelease(KeyEvent.VK_J);

        r.keyPress(KeyEvent.VK_P);    // p
        r.keyRelease(KeyEvent.VK_P);

        r.keyPress(KeyEvent.VK_G);    // g
        r.keyRelease(KeyEvent.VK_G);

        r.keyPress(KeyEvent.VK_ENTER);    // confirm by pressing Enter in the end
        r.keyRelease(KeyEvent.VK_ENTER);
    }

    public void MoveFocucToTheNewWindow(final Set<String> oldWindowsSet){
        String newWindos = (new WebDriverWait(driver, 10)).until(new ExpectedCondition<String>()
        {
            @Override
            public String apply(WebDriver webDriver) {
                Set<String> newWindosSet = webDriver.getWindowHandles();
                newWindosSet.removeAll(oldWindowsSet);
                return newWindosSet.size() > 0 ?
                        newWindosSet.iterator().next() : null;
            }
        });
        driver.switchTo().window(newWindos);
    }
}
