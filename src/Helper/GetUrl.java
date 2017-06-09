package Helper;

import org.openqa.selenium.WebDriver;

public class GetUrl {

    private WebDriver driver;

    public GetUrl(WebDriver driver) {
        this.driver = driver;
    }

    public String choiceStand() {
        int stand = 2; // in this line indicate stand for testing

        if (stand == 2) {
            String str = String.valueOf(stand);
            return str;
        }
        return "";
    }

    public void driverGet() {
        driver.get("http://pablo-mel"+choiceStand()+".qa.lan/");
    }

    public String driverGetStr() {
        String str = "http://pablo-mel"+choiceStand()+".qa.lan/";
        return str;
    }

    public void driverGetCurrentUrl(String str) {
        driver.get("http://pablo-mel"+choiceStand()+".qa.lan/"+str);
    }

    public void driverGetAdminUrl(){
        driver.get("http://admin.pablo-mel"+choiceStand()+".qa.lan/");
    }

    public void driverGetCurrentAdminUrl(String str){
        driver.get("http://admin.pablo-mel"+choiceStand()+".qa.lan/" + str);
    }

    public void getExternalUrl(){
        driver.get("http://qa"+choiceStand()+".mel.fm/");
    }
}
