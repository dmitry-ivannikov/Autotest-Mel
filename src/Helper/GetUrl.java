package Helper;

import org.openqa.selenium.WebDriver;

public class GetUrl {

    WebDriver driver;

    public GetUrl(WebDriver driver) {
        this.driver = driver;
    }

    public void driverGet() {
        driver.get("http://pablo-mel.qa.lan/");
    }

    public String driverGetStr() {
        String str = "http://pablo-mel.qa.lan/";
        return str;
    }

    public void driverGetCurrentUrl(String str) {
        driver.get("http://pablo-mel.qa.lan/"+str);
    }

    public void driverGetAdminUrl(){
        driver.get("http://admin.pablo-mel.qa.lan/");
    }

    public void driverGetCurrentAdminUrl(String str){
        driver.get("http://admin.pablo-mel.qa.lan/" + str);
    }
}
