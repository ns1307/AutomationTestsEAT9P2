package com.eatP2.loginpage;

import com.eatP2.Config;
import com.eatP2.models.User;
import com.eatP2.pages.login.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;


public class LoginTests {// TS1-Login to the system
    protected WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(Config.LOGIN_PAGE_URL);

    }

    @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testSuccessfulLogin() {
        // Test Case ID: TC 01
        // Test Case Name: Successful login to the system
        // Test Description: It will be tested whether the user can log into the system with the correct email and password.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.loginWithCredentials("testuser@test.com","Password1234");
        // Wait for successful login
        // Expected Result: The user logs into the system and is redirected to the Customer Search screen.
        assertTrue("User should be redirected to the dashboard page after successful login",loginPage.verifySuccessfulRouting());
    }
    @Test
    public void testUnsuccessfulLoginInvalidEmail() {
        // Test Case ID: TC 02
        // Test Case Name: Login with Invalid Email
        // Test Description: It will be tested whether the user logs into the system with an incorrect Email.
        LoginPage loginPage = new LoginPage(driver);
        String email="invalid-email";
        String password="validpassword";
        loginPage.navigateToLoginPage();
        loginPage.loginWithCredentials(email , password);
        assertTrue("No error message displayed after login attempt with Invalid Email",loginPage.isErrorMessageDisplayed());
        // Expected Result: The system displays the warning message, "Invalid email!"
        assertTrue("Invalid email message text is incorrect.",loginPage.verifyInvalidEmailMessage());
    }
    @Test
    public void testUnsuccessfulLoginWrongPassword() {
        // Test Case ID: TC 03
        // Test Case Name: Login with Wrong Email
        // Test Description: It will be tested whether the user can log in to the system with an incorrect password.
        LoginPage loginPage = new LoginPage(driver);
        String email="testuser@test.com";
        String password="wrongpassword";
        loginPage.navigateToLoginPage();
        loginPage.loginWithCredentials(email ,password );
        assertTrue("Error message not displayed after login attempt with Wrong Email",loginPage.isErrorMessageDisplayed());
        // Expected Result: The system displays the warning message, "Wrong email or password. Please try again!"
        assertTrue("Unsuccessful message text is incorrect.",loginPage.verifyUnsuccessfulLoginMessage());
    }


    @Test
    public void testEmptyCredentialsLogin() {
        // Test Case ID: TC 04
        // Test Case Name: Login with Empty Fields
        // Test Description: It will be tested whether the user can log in without a email and password.
        LoginPage loginPage = new LoginPage(driver);
        String email="";
        String password="";
        loginPage.navigateToLoginPage();
        loginPage.enterEmail(email);
        loginPage.enterPassword(password);


        // Expected Result: The system displays the warning message, "Wrong email or password. Please try again!"
        // Verify that the login button is disabled
        assertFalse("Login button should be disabled when email and password are empty", loginPage.isLoginButtonEnabled());

    }
    @Test
    public void testPasswordMasking() {
        // Test Case ID: TC05
        // Test Case Name: Password Masking
        // Test Description: It will be tested whether the password is masked while the user is entering it.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();
        loginPage.enterPassword("passwordMask");

        //if type is password, input is hidden
        // Expected Result: The password should be masked.
        assertEquals("Password field should be masked", "password", loginPage.getPasswordFieldType());

        if(loginPage.isPasswordEyeDisplayed()){
            loginPage.clickPasswordEyeButton();
        }
        else {
            fail("Password eye button does not exist.");
        }


        //if type is text, input is not hidden
        // Expected Result: The password mask should be removed.
        assertEquals("Password field should be not masked when icon is clicked" , "text", loginPage.getPasswordFieldType());

        loginPage.clickPasswordEyeButton();

        // Expected Result: The password mask should be removed.
        assertEquals("Password field should be masked again when icon is clicked again", "password", loginPage.getPasswordFieldType());

    }

    @Test
    public void testCapsLockWarning() {
        // Test Case ID: TC06
        // Test Case Name: Caps Lock Warning
        // Test Description: It will be tested whether a warning message is displayed on the screen when the user enters their information with Caps Lock turned on.

        //Since there is Selenium is not supported Keys.CAPS_LOCK we will simulate this test manually.
        //Below test may not work properly for automation.
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage();


        assertTrue("Caps Lock Warning not displayed", loginPage.isCapsLockSupported());

        // Expected Result: The system displays the warning message, “Caps Lock ON!”
        assertTrue("Caps Lock message is incorrect.",loginPage.verifyCapsLockMessage());

    }

}
