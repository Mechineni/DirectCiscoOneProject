package config;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static executionEngine.DriverScript.OR;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import executionEngine.DriverScript;
import org.openqa.selenium.support.ui.Select;
import utility.Log;

import com.gurock.testrail.*;
import org.json.simple.*;

public class ActionKeywords {

    public static WebDriver driver;
    private static APIClient client;

    public static void openBrowser(String object,String data){
        Log.info("Opening Browser");
        try{
            if(data.equalsIgnoreCase("Mozilla")){
                System.setProperty("webdriver.gecko.driver","C:\\Selenium WorkSpace\\Gecko Driver\\geckodriver.exe");
                driver=new FirefoxDriver();
                Log.info("Mozilla browser started");
            }
            else if(data.equals("IE")){
                //Dummy Code, Implement you own code
                driver=new InternetExplorerDriver();
                Log.info("IE browser started");
            }
            else if(data.equals("Chrome")){
                //Dummy Code, Implement you own code
                driver=new ChromeDriver();
                Log.info("Chrome browser started");
            }

            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
        }catch (Exception e){
            Log.info("Not able to open the Browser --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void navigate(String object, String data){
        try{
            Log.info("Navigating to URL");
            driver.get(Constants.URL);
            driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);
            System.out.print("navigate "+DriverScript.bResult);
        }catch(Exception e){
            Log.info("Not able to navigate --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void click(String object, String data){
        try{
            Log.info("Clicking on Webelement "+ object);
            driver.findElement(By.xpath(OR.getProperty(object))).click();
            Thread.sleep(2000);
        }catch(Exception e){
            Log.error("Not able to click --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void select(String object, String data){

        try{
            Log.info("Selecting value from list box "+ object);

            //WebElement element = driver.findElement(By.xpath(OR.getProperty(object)));
            Select dropdown = new Select(driver.findElement(By.xpath(OR.getProperty(object))));
            dropdown.selectByVisibleText(data);
        }catch(Exception e){
            Log.error("Not able to select --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void input(String object, String data){
        try{
            Log.info("Entering the text in " + object);
            driver.findElement(By.xpath(OR.getProperty(object))).sendKeys(data);
        }catch(Exception e){
            Log.error("Not able to Enter UserName --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void waitFor(String object, String data) throws Exception{
        try{
            Log.info("Wait for 5 seconds");
            Thread.sleep(5000);
        }catch(Exception e){
            Log.error("Not able to Wait --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void closeBrowser(String object, String data){
        try{
            Log.info("Closing the browser");
            //driver.quit();
        }catch(Exception e){
            Log.error("Not able to Close the Browser --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

    public static void getCase() throws IOException, APIException {
        APIClient client = new APIClient("https://mechineni.testrail.net/index.php?/auth/login");
        client.setUser("mamata.mechineni@dimensiondata.com");
        client.setPassword("abcdef123");
        JSONObject c = (JSONObject)client.sendGet("get_case/1");
        System.out.println(c.get("title"));
    }

    public static void add_result_for_case()throws IOException, APIException{

        Map data = new HashMap();
        data.put("status_id", new Integer(1));
        data.put("comment", "This test worked fine!");
        JSONObject r = (JSONObject)client.sendPost("add_result_for_case/1/1", data);
    }

}
