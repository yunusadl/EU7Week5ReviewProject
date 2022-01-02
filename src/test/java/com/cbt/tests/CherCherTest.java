package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

/*
Task1:
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Click me, to Open an alert after 5 seconds"
    3. Explicitly wait until alert is present
    4. Then handle the Javascript alert
 */

public class CherCherTest {

    WebDriver driver; // Declare our reference for the object
    WebDriverWait wait;


    @BeforeMethod

    public void setUp(){

        driver = WebDriverFactory.getDriver("CHROME");

        driver.manage().window().maximize();

        // implicitly wait, this is going to apply to all test cases and elements

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver");




    }

    @AfterMethod

    public void tearDown(){

        driver.quit();



    }

    @Test

    public void alertPresentTest(){

        WebElement alertButton = driver.findElement(By.cssSelector("#alert"));

        alertButton.click();


        wait = new WebDriverWait(driver,10);

        wait.until(ExpectedConditions.alertIsPresent());

        Alert alert = driver.switchTo().alert();

        alert.accept();









    }

    /*
       Task2:
    1. Go to https://chercher.tech/practice/explicit-wait-sample-selenium-webdriver
    2. Click on "Enable button after 10 seconds"
    3. Explicitly wait until the button is enabled
    4. Then verify the button is enabled
     */

    @Test

    public void buttonEnabledTest(){

        driver.findElement(By.id("enable-button")).click();

        wait = new WebDriverWait(driver,15);

        WebElement disableButton = driver.findElement(By.id("disable"));

        System.out.println("disableButton.isEnabled() = " + disableButton.isEnabled());


        wait.until(ExpectedConditions.elementToBeClickable(disableButton));

        System.out.println("disableButton.isEnabled() = " + disableButton.isEnabled());

        Assert.assertTrue(disableButton.isEnabled());



        disableButton.click();




    }
}
