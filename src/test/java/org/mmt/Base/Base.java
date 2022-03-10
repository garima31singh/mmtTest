package org.mmt.Base;


import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Base {
    public static ChromeDriver driver;
    public Properties pr;

    @BeforeMethod
    public void BrowserLaunch() throws IOException {
        // Load Properties File
        File f= new File("./locators.properties");
        FileInputStream fi= new FileInputStream(f);
        pr= new Properties();
        pr.load(fi);
        System.setProperty("webdriver.chrome.driver","./Driver/chromedriver");
        driver= new ChromeDriver();
        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
    }
    @AfterMethod
    public void CloseBrowser(){
        driver.quit();
    }



}
