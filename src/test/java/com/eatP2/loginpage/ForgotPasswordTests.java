package com.eatP2.loginpage;

import com.eatP2.Config;
import com.eatP2.pages.login.ForgetPasswordPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

public class ForgotPasswordTests {// TS17-Forget Password

    private WebDriver driver;

    @Before
    public void setUp() {
        // Set the path to the chromedriver executable
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(Config.LOGIN_PAGE_URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testForgotPasswordRouting() {
        // Test Case ID: TC 01
        // Test Case Name: Forgot Password Routing
        // Test Description: It will be tested whether the "Forgot Password" button routes to the password recovery page.

        // Pre-Condition: The system login page should open correctly
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.navigateToForgotPassword();

        // Expected Result: The user should be redirected to the password recovery page
         // Replace with actual URL
        assertTrue("Not redirected to Forget Password from Login", forgetPasswordPage.verifyPageUrl());
    }

    @Test
    public void testForgotPasswordValidEmail() {
        // Test Case ID: TC 02
        // Test Description: It will be tested whether a password reset request is successfully created on the "Forgot Password" page.

        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.navigateToForgotPassword();

        forgetPasswordPage.enterEmail("testuser@test.com");
        forgetPasswordPage.clickSubmitButton();

        // Expected Result: Success message should be displayed or user redirected to confirmation page
        assertTrue("Password reset success tick not displayed.", forgetPasswordPage.verifySuccessTickOnButton());


    }

    @Test
    public void testForgotPasswordInvalidEmail() {
        // Test Case ID: TC 03
        // Test Description: A password reset attempt with an invalid email address will be tested.

        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.navigateToForgotPassword();

        forgetPasswordPage.enterEmail("testuser");
        forgetPasswordPage.clickSubmitButton();


        // Expected Result: Error message should be displayed
        assertFalse("Password reset success tick should not be displayed.", forgetPasswordPage.verifySuccessTickOnButton());

        assertTrue("Error message not displayed.",forgetPasswordPage.isErrorMessageDisplayed());
        assertTrue("Error message text is incorrect",forgetPasswordPage.verifyInvalidEmailMessage());
    }


    @Test
    public void testForgotPasswordNonExistentEmail() {
        // Test Case ID: TC 04
        // Test Description: A password reset attempt with an email address not registered in the system will be tested.

        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.navigateToForgotPassword();

        forgetPasswordPage.enterEmail("nonexist@test.com");
        forgetPasswordPage.clickSubmitButton();

        // Expected Result: The system should display the message "Password reset request sent!" but no password reset request should be sent.
        assertTrue("Password reset success tick not displayed.", forgetPasswordPage.verifySuccessTickOnButton());


    }

    @Test
    public void testRouteBackToLogin() {
        // Test Case ID: TC 05
        // Test Description: A password reset attempt with an email address not registered in the system will be tested.
        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.navigateToForgotPassword();

        forgetPasswordPage.enterEmail("BackButtonWillBeClicked");
        forgetPasswordPage.clickBackBtn();
        //Expected Result:  The system should redirect the user back to the login screen.
        assertTrue("Not redirected to login page after back button is clicked",
                forgetPasswordPage.verifyRoutingBackButton());
    }



    @Test
    public void testForgotPasswordEmptyEmail() {
        // Test Case ID: TC 06
        // Test Description: A password reset attempt with an empty email address will be tested.

        ForgetPasswordPage forgetPasswordPage = new ForgetPasswordPage(driver);
        forgetPasswordPage.navigateToForgotPassword();

        forgetPasswordPage.enterEmail("");

        // Verify that the login button is disabled
        assertFalse("Submit button should be disabled when email field is empty.", forgetPasswordPage.isSubmitBtnEnabled());

    }



}
