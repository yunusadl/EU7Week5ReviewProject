package com.cbt.tests;

import com.cbt.utilities.WebDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class DropDownTest {

    /*
      Task3:
    1. Go to:  http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx
    2. Login with username: Tester, password: test
    3. Click  Order button
    4. Verify under Product Information, selected option is “MyMoney”
    5. Then select FamilyAlbum, make quantity 2, and click Calculate,
    6. Then verify Total is equal to Quantity*PricePerUnit
     */

    WebDriver driver; // Declare our reference for the object
    WebDriverWait wait;


    @BeforeMethod

    public void setUp(){

        driver = WebDriverFactory.getDriver("CHROME");

        driver.manage().window().maximize();

        // implicitly wait, this is going to apply to all test cases and elements

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/WebOrders/login.aspx");




    }

    @AfterMethod

    public void tearDown(){

        driver.quit();



    }

    @Test

    public void test1(){

        WebElement userInputBox = driver.findElement(By.id("ctl00_MainContent_username"));

        userInputBox.sendKeys("tester");

        WebElement passwordBox = driver.findElement(By.id("ctl00_MainContent_password"));

        passwordBox.sendKeys("test" + Keys.ENTER); // Instead of locating submit button and giving click action.

        WebElement orderButton = driver.findElement(By.linkText("Order"));

        orderButton.click();

        String expectedSelectedOption = "MyMoney";

        WebElement productDropDownElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));

        Select productDropDown = new Select(productDropDownElement);

        String actualSelectedOption = productDropDown.getFirstSelectedOption().getText();

        Assert.assertEquals(expectedSelectedOption,actualSelectedOption);

        //Select familyAlbum

        productDropDown.selectByIndex(1);

        //Make quantity 2


        WebElement quantityBox = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));

        quantityBox.clear();


        quantityBox.sendKeys("2");

        //Click calculate button

        WebElement calculateButton = driver.findElement(By.xpath("//input[@onclick='productsChanged(); return false;']"));

        calculateButton.click();

        // Then verify total is equal to Quantity*PricePerUnit


        int expectedPrice = 160;

        WebElement totalPriceElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));

        int actualPrice = Integer.parseInt(totalPriceElement.getAttribute("value"));

        Assert.assertEquals(actualPrice,expectedPrice);


    }
}
