package com.eatP2.components;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;
public class HeaderTests { // TS-17 Routing, Visibility and Functionality of Header Buttons
    private WebDriver driver;

    @Before
    public void setUp() {

        // Set the path to the chromedriver executable
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    public void testHeaderVisibility() {// TC01-The visibility of the Header on on every page
        login();
        assertTrue("Not redirected to correct page after login.",driver.getCurrentUrl().equals("http://example.com/search-customer"));
        checkHeaderVisibility();
        WebElement searchIDField = driver.findElement(By.id("search-id"));
        searchIDField.sendKeys("12345678901");
        checkHeaderVisibility();
        driver.findElement(By.id("new-customer")).click();
        checkHeaderVisibility();
        assertTrue("Not redirected to correct page for create customer after user not found.",driver.getCurrentUrl().equals("http://example.com/create-customer"));
        //Create unique customer
        checkHeaderVisibility();
        driver.findElement(By.id("id")).sendKeys("12345678901");
        driver.findElement(By.id("next")).click();
        // Fill customer info field
        checkHeaderVisibility();
        driver.findElement(By.id("first-name")).sendKeys("12345678901");
        driver.findElement(By.id("last-name")).sendKeys("12345678901");
        Select dropdown = new Select(driver.findElement(By.id("gender")));
        dropdown.selectByValue("Male");
        driver.findElement(By.id("birthdate")).click();
        driver.findElement(By.xpath("//td[@data-date='2024-11-01']")).click();
        driver.findElement(By.id("next")).click();

        // Fill customer adress fields
        checkHeaderVisibility();
        dropdown = new Select(driver.findElement(By.id("city")));
        dropdown.selectByValue("İzmir");
        dropdown = new Select(driver.findElement(By.id("district")));
        dropdown.selectByValue("Karşıyaka");
        driver.findElement(By.id("postalCode")).sendKeys("35560");
        driver.findElement(By.id("addressDescription")).sendKeys("İzmir Karşıyaka Test Mah. X");
        driver.findElement(By.id("next")).click();

        // Fill contact medium fields
        checkHeaderVisibility();
        driver.findElement(By.id("email")).sendKeys("testuser@test.com");
        driver.findElement(By.id("mobilephone")).sendKeys("0555 555 55 55");
        driver.findElement(By.id("create")).click();

        // Redirected to customer info page.
        checkHeaderVisibility();
        driver.findElement(By.id("address")).click();

        // Redirected to address page.
        checkHeaderVisibility();
        driver.findElement(By.id("contact-medium")).click();
        // Redirected to contact medium page.
        checkHeaderVisibility();
        driver.findElement(By.id("customer-account")).click();
        // Redirected to customer account page.
        checkHeaderVisibility();
        driver.findElement(By.id("new-account")).click();
        // Redirected to create new billing account.
        checkHeaderVisibility();
        driver.findElement(By.id("accName")).sendKeys("testAcc");
        dropdown = new Select(driver.findElement(By.id("accStatus")));
        dropdown.selectByValue("Active");
        dropdown = new Select(driver.findElement(By.id("accType")));
        dropdown.selectByValue("Billing Account");
        dropdown = new Select(driver.findElement(By.id("address")));
        dropdown.selectByIndex(0);
        driver.findElement(By.id("create")).click();
        // Redirected to customer account page after created billing account.
        checkHeaderVisibility();
        List<WebElement> rows = driver.findElements(By.xpath("//tr"));
        // Find row with name "testAcc" and click pencil button
        for (WebElement row : rows) {
            if (row.getText().contains("testAcc")) {
                row.findElement(By.id("new-sale")).click();
                break;
            }
        }
        // Redirected to start new sale page
        checkHeaderVisibility();
        //ADD SOME PRODUCTS TO CART
        driver.findElement(By.id("next")).click();
        // Redirected to product config page
        checkHeaderVisibility();
        driver.findElement(By.id("next")).click();
        // Redirected to submit order page
        checkHeaderVisibility();
        driver.findElement(By.id("submit")).click();

        // Redirected to customer account page after new sale.
        checkHeaderVisibility();
        rows = driver.findElements(By.xpath("//tr"));
        // Find row with name "1537894562" and click pencil button
        for (WebElement row : rows) {
            if (row.getText().contains("testAcc")) {
                row.findElement(By.xpath(".//button[contains(@class, 'edit')]")).click();
                break;
            }
        }

        // Redirected to update billing acc page.
        checkHeaderVisibility();
        driver.findElement(By.id("save")).click();
        // Redirected to customer account page after billing account update.
        checkHeaderVisibility();
        driver.findElement(By.id("customer-info")).click();
        // Redirected to customer info page.
        checkHeaderVisibility();
        driver.findElement(By.id("edit")).click();
        // Redirected to update customer info page.
        checkHeaderVisibility();
        driver.findElement(By.id("save")).click();
        // Redirected to customer info page after update
        checkHeaderVisibility();
    }
    @Test
    public void testCreateCustomerButton() {// TC02-The functionality of the Create Customer button in the Header
        login();
        By createCustomerLocator=By.id("save");
        if (isElementPresent(createCustomerLocator)){
            driver.findElement(createCustomerLocator).click();
            assertTrue("Create Customer button not directed correctly.",driver.getCurrentUrl().equals("http://example.com/new-customer"));
        }
        else{
            fail("Create Customer button does not exist.");
        }
    }



    @Test
    public void testSearchCustomerButton() {// TC03-The functionality of the Search Customer button in the Header
        login();
        By createCustomerLocator=By.id("new-customer");
        if (isElementPresent(createCustomerLocator)){
            driver.findElement(createCustomerLocator).click();
            assertTrue("Create Customer button not directed correctly.",driver.getCurrentUrl().equals("http://example.com/new-customer"));
        }
        else{
            fail("Create Customer button does not exist.");
        }
        By searchCustomerLocator=By.id("search-customer");
        if (isElementPresent(searchCustomerLocator)){
            driver.findElement(searchCustomerLocator).click();
            assertTrue("Search Customer button not directed correctly.",driver.getCurrentUrl().equals("http://example.com/search-customer"));
        }
        else{
            fail("Search Customer button does not exist.");
        }
    }

    @Test
    public void testLanguageButton() {// TC04-The functionality of the Language button in the Header
        login();
        By langLocatorTR=By.id("lang-tr");
        if (isElementPresent(langLocatorTR)){
            driver.findElement(langLocatorTR).click();
            assertTrue("TR Language button does not work properly.",driver.findElement(By.id("header")).getText().equals("Müşteri Arama")&&driver.findElement(By.id("clear")).getText().equals("Temizle")&&driver.findElement(By.id("search")).getText().equals("Ara"));
        }
        else{
            fail("TR Language button does not exist.");
        }
        By langLocatorEN=By.id("lang-en");
        if (isElementPresent(langLocatorEN)){
            driver.findElement(langLocatorEN).click();
            assertTrue("EN Language button does not work properly.",driver.findElement(By.id("header")).getText().equals("Search Customer")&&driver.findElement(By.id("clear")).getText().equals("Clear")&&driver.findElement(By.id("search")).getText().equals("Search"));
        }
        else{
            fail("EN Language button does not exist.");
        }
    }
    @Test
    public void testClickLogo() {// TC05-Clicking on the Logo in the Header
        login();
        By createCustomerLocator=By.id("new-customer");
        if (isElementPresent(createCustomerLocator)){
            driver.findElement(createCustomerLocator).click();
            assertTrue("Create Customer button not directed correctly.",driver.getCurrentUrl().equals("http://example.com/new-customer"));
        }
        else{
            fail("Create Customer button does not exist.");
        }
        By logoLocator=By.id("logo");
        if (isElementPresent(logoLocator)){
            driver.findElement(logoLocator).click();
            assertTrue("The logo was not redirected correctly after clicking.",driver.getCurrentUrl().equals("http://example.com/search-customer"));
        }
        else{
            fail("The logo does not exists.");
        }
    }
    @Test
    public void testLogoutButton() {// TC06-The functionality of the "Log out" button in the Header
        login();
        By logoutLocator=By.id("logout");
        if (isElementPresent(logoutLocator)){
            driver.findElement(logoutLocator).click();
            //Cleaning of cookies can be tested er
            assertTrue("Logout button not directed correctly.",driver.getCurrentUrl().equals("http://example.com/login"));
        }
        else{
            fail("Logout button does not exist.");
        }
    }

    private void checkHeaderVisibility() {
        assertTrue("Header not displayed.",driver.findElement(By.id("header")).isDisplayed());
        assertTrue("Header not enabled.",driver.findElement(By.id("header")).isEnabled());
    }


    private void login (){
        // Navigate to Login page
        driver.get("http://example.com/login");

        // Perform login
        WebElement usernameField = driver.findElement(By.id("username"));
        WebElement passwordField = driver.findElement(By.id("password"));
        WebElement loginButton = driver.findElement(By.id("login-button"));

        usernameField.sendKeys("testuser");
        passwordField.sendKeys("password123");
        loginButton.click();
    }
    private boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }
}
