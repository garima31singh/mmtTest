package org.mmt.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.io.FileWriter;

import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class SearchFlights {
     public ChromeDriver driver;
   public Properties pr;

    public SearchFlights(ChromeDriver driver, Properties pr) {
        this.driver = driver;
        this.pr = pr;

    }
    @Test
    public void performSearch() throws InterruptedException {
        //**Implicit wait
        driver.manage().timeouts().implicitlyWait(5000, TimeUnit.MILLISECONDS);

        //**Close language pop-up
        driver.findElement(By.className("langCardClose")).click();
        Thread.sleep(2000);

        //**click pop-up
        driver.findElement(By.tagName("div")).click();
        Thread.sleep(1000);

        //**Clicking To city
        driver.findElementByXPath(pr.getProperty("from")).click();
        WebElement fromCity = driver.findElementByXPath(pr.getProperty("fromCity"));
        fromCity.sendKeys("Delhi");
        Thread.sleep(1000);
        fromCity.sendKeys(Keys.ARROW_DOWN);
        fromCity.sendKeys(Keys.ENTER);

        //***Clicking From City
        driver.findElementByXPath(pr.getProperty("to")).click();
        WebElement toCity = driver.findElementByXPath(pr.getProperty("toCity"));
        toCity.sendKeys("Bengaluru");
        Thread.sleep(1000);
        toCity.sendKeys(Keys.ARROW_DOWN);
        toCity.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        //**Pick Today's Date
        driver.findElementByXPath(pr.getProperty("Date")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath(pr.getProperty("todayDate"))).click();

        //***choose travellers and class
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath(pr.getProperty("travellers"))).click();
        driver.findElement(By.xpath(pr.getProperty("passengers"))).click();
        WebElement accept = driver.findElement(By.xpath(pr.getProperty("submitdetail")));
        accept.click();

        //***CLick Search Button
        WebElement search = driver.findElement(By.xpath(pr.getProperty("click")));
        search.click();

        WebElement btn = driver.findElement(By.xpath(pr.getProperty("seepricedetail")));
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(btn));

        //**Fetch Data from Airline Details Page
        List<WebElement> data = driver.findElements(By.xpath(pr.getProperty("airlineDetails")));
        for (WebElement scrapedData : data) {
            String airlineTimings = scrapedData.getText();

            System.out.println(airlineTimings);
        }
    }
}

