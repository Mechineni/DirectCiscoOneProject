package config;

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

public class VerifyMethods {
    public static WebDriver driver;

    public static void Verify_BuyeName(String object, String data){
        try{
            Log.info("Buyername found in verify methods");
            System.out.print("Enterred for buyer verification");

        }catch(Exception e){
            Log.info("Not able to navigate --- " + e.getMessage());
            DriverScript.bResult = false;
        }
    }

}
