package Helper;

import TestClasses.LogoutTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.*;
import java.util.logging.Level;


public class AdditionalMethods {
    WebDriver driver;
    LogoutTest logout;

    public AdditionalMethods(WebDriver driver) {
        this.driver = driver;
    }

    // метод, который ждет наступления какого-то события, прежде чем продолжит выполнение команд
    public void Wait(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();

        }
    }

    public void exit() {
        logout = new LogoutTest(driver);
        logout.exitFromAccount();
        Wait(1000);
    }

    public void outputFromAnAccountSocialLogin() {
        logout = new LogoutTest(driver);
        logout.exitToAuthorisationSocial();
    }

    // получение рандомной строки
    public String generateStr(){
        String str1 = "testpablo";
        String str2 = "@rootfest.net";

        int a = 0;
        int b = 1000;
        int FirstRandomNumber = a + (int) (Math.random() * b);
        int SecondRandomNumber = a + (int) (Math.random() * b);

        String str3 = FirstRandomNumber + str1 + SecondRandomNumber + str2;
        return str3;
    }

    // загрузка картинки по абсолютному пути
    public void imgageDownload() {
        Robot r = null;
        try {
            r = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        r.keyPress(KeyEvent.VK_C);
        r.keyRelease(KeyEvent.VK_C);

        r.keyPress(KeyEvent.VK_SHIFT);
        r.keyPress(KeyEvent.VK_SEMICOLON);
        r.keyRelease(KeyEvent.VK_SEMICOLON);
        r.keyRelease(KeyEvent.VK_SHIFT);

        ArrayList<Integer> robots = new ArrayList();
        robots.add(KeyEvent.VK_BACK_SLASH);
        robots.add(KeyEvent.VK_1);
        robots.add(KeyEvent.VK_PERIOD);
        robots.add(KeyEvent.VK_J);
        robots.add(KeyEvent.VK_P);
        robots.add(KeyEvent.VK_G);
        robots.add(KeyEvent.VK_ENTER);

        for (int i=0; i< robots.size(); i++) {
            r.keyPress(robots.get(i));
            r.keyRelease(robots.get(i));
        }
    }

    // изменение фокуса с одной страницы на вторую
    public void moveFocucToTheNewWindow(final Set<String> oldWindowsSet){
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

    // обработка консольных ошибок
    public void getBrowserLogs(){
        for (LogEntry logEntry : driver.manage().logs().get("browser").filter(Level.SEVERE)) {
            System.out.println(logEntry);
            Assert.fail();
        }
    }
}
