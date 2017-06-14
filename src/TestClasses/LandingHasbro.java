package TestClasses;


import Helper.AdditionalMethods;
import Helper.GetUrl;
import Main.SiteTestCases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Set;

public class LandingHasbro extends SiteTestCases{
    WebDriver driver;
    private AdditionalMethods methods;

    private By hasbroLogo = By.cssSelector(".b-header__hasbro-logo");
    private By hasbroMelButton = By.cssSelector(".b-header__mel-logo");
    private By firstChoiseGameButton = By.cssSelector(".b-header__chose-game-button-wrapper > div");
    private By firstGame = By.cssSelector(".b-game-types__game-previews-first-row > a:nth-child(2)");
    private By lastGame = By.cssSelector(".b-game-types__game-previews-second-row > a:nth-child(3)");
    private By lastChoiseGameButton = By.cssSelector(".b-image-slider__image-slider-button");
    private By firstWhereBuyButton = By.cssSelector(".l-shops-cards__cards-wrapper > div:nth-child(1) > div > a > div > div.b-single-card__button-wrapper > div");
    private By vkButton = By.cssSelector(".b-footer__learn-more-buttons-wrapper > div:nth-child(1)");
    private By facebookButton = By.cssSelector(".b-footer__learn-more-buttons-wrapper > div:nth-child(2)");
    private By hasbroFooterButton = By.cssSelector(".b-footer__learn-more-buttons-wrapper > div:nth-child(3)");

    public LandingHasbro(WebDriver driver) {
        this.driver = driver;
    }

    public void checkButtons(ArrayList<String> expectedResult){
        methods = new AdditionalMethods(driver);

        ArrayList buttons = new ArrayList();
        buttons.add(hasbroLogo);
        buttons.add(hasbroMelButton);
        buttons.add(firstChoiseGameButton);
        buttons.add(firstGame);
        buttons.add(hasbroLogo);
        buttons.add(lastGame);
        buttons.add(lastChoiseGameButton);
        buttons.add(firstWhereBuyButton);
        buttons.add(vkButton);
        buttons.add(facebookButton);
        buttons.add(hasbroFooterButton);

        for(int i=0; i<buttons.size(); i++){
            String parentWindowId = driver.getWindowHandle();
            final Set<String> oldWindowsSet = driver.getWindowHandles();
            methods.Wait(200);
            driver.findElement((By)buttons.get(i)).click();
            methods.Wait(200);
            methods.moveFocusToTheNewWindow(oldWindowsSet);
            Assert.assertEquals(driver.getTitle(), expectedResult.get(i));
            driver.close();
            driver.switchTo().window(parentWindowId);
        }
    }
}
