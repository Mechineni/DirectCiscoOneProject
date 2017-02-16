package pageObjects;
import config.*;
import org.openqa.selenium.WebDriver;

public class DirectLogIn {
    public static WebDriver driver;

    public static void DirectLogIn() throws Exception {

        ActionKeywords.openBrowser("openBrowser", "Mozilla");
        ActionKeywords.navigate("openBrowser", "Mozilla");
        ActionKeywords.input("txtbx_UserName", "mamata");
        ActionKeywords.input("txtbx_Password", "test@12345");
        ActionKeywords.click("btn_LogIn","test");
        ActionKeywords.waitFor("Wiatfor","Test");
        //Thread.sleep(6000);

    }




}
