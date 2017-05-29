package AdminTestClasses;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminAutosaveTest {
    WebDriver driver;

    private By publicationSaveButton = By.cssSelector(".b-control-panel-draft__save-button > div");
    private By publicationSaveTime = By.cssSelector(".b-control-panel-draft__saving-info-display");

    public AdminAutosaveTest(WebDriver driver) {
        this.driver = driver;
    }

    public String getPublicationSaveTime() {
        String str = driver.findElement(publicationSaveTime).getText();
        return str;
    }

    public void clickInPublicationSaveButton(){
        driver.findElement(publicationSaveButton).click();
    }

    public void comparisonPublicationTime(String firstTime, String lastTime) {
        if (firstTime.equals(lastTime)) {
            Assert.fail("Autosave is not working");
        }
    }
}
