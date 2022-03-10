package org.mmt.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class testclass {
    public static ChromeDriver driver;
    public static void main(String[] args) throws InterruptedException {

        //**launch the browser
        System.setProperty("webdriver.chrome.driver","./Driver/chromedriver");
        driver= new ChromeDriver();
        driver.get("https://www.makemytrip.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);

        //**Implicit wait
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        //**Search flight from Delhi to Bangalore

        //**Close language pop-up
        driver.findElement(By.className("langCardClose")).click();
        Thread.sleep(2000);

        //**click pop-up
        driver.findElement(By.tagName("div")).click();
        Thread.sleep(1000);

//        Actions action= new Actions(driver);
//        WebElement fromCity= driver.findElement(By.xpath("//span[contains(text(),'From')]"));
//        fromCity.click();
//
        driver.findElementByXPath("//span[contains(text(),'From')]").click();
        WebElement fromCity= driver.findElementByXPath("//input[@placeholder='From']");
        fromCity.sendKeys("Delhi");
        Thread.sleep(1000);
        fromCity.sendKeys(Keys.ARROW_DOWN);
        fromCity.sendKeys(Keys.ENTER);


//        fromCity.sendKeys("delhi");
        Thread.sleep(1000);


        driver.findElementByXPath("//span[contains(text(),'To')]").click();
        WebElement toCity= driver.findElementByXPath("//input[@placeholder='To']");
        toCity.sendKeys("Bengaluru");
        Thread.sleep(1000);
        toCity.sendKeys(Keys.ARROW_DOWN);
        toCity.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //*** pick today's date
        driver.findElementByXPath("//span[contains(text(),'DEPARTURE')]").click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[@class=\"DayPicker-Day DayPicker-Day--today\"][contains(@aria-label,'Thu Mar 10 2022')]"))
                .click();

        //***choose travellers and class
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.findElement(By.xpath("//span[contains(text(),'Travellers & CLASS')]")).click();
        driver.findElement(By.xpath("//div[@class=\"travellers gbTravellers\"]/div/ul[@class=\"guestCounter font12 darkText gbCounter\"]/li[2]")).click();
        WebElement accept= driver.findElement(By.xpath("//button[contains(text(),'APPLY')]"));
        accept.click();

        //*** perform Search

        WebElement search= driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
       search.click();

       WebElement btn=  driver.findElement(By.xpath("//div[@class=\"makeFlex top gap-x-10\"]/button[@class=\"ViewFareBtn  text-uppercase \"]"));
        WebDriverWait wait= new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(btn));

        //**scrape data of airline
//       List<WebElement> findAirlines= driver.findElements(By.xpath("//span[@class=\"boldFont blackText airlineName\"]"));
//       for(WebElement airlines:findAirlines){
//           String airlinesDetails= airlines.getText();
//           System.out.println(airlinesDetails);
//       }

       List<WebElement> data = driver.findElements(By.xpath("//div[@class=\"listingCard\"]"));
       for(WebElement scrapedData:data){
           String airlineTimings= scrapedData.getText();
           System.out.println(airlineTimings);

       }
        driver.quit();

    }
}
