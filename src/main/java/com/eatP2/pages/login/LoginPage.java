package com.eatP2.pages.login;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage extends BasePage {
    By emailField = By.id("floatingEmail");
    By passwordField = By.id("floatingPassword");
    By loginBtn = By.id("login-button");
    By forgetPasswordBtn = By.id("forget-password-button");
    By passwordMaskBtn = By.id("password-eye");
    public LoginPage(WebDriver driver) {
        super(driver);
    }


    public void enterEmail(String email){
        setValue(emailField, email);
    }

    public void enterPassword(String password) {
        setValue(passwordField, password);
    }

    public void clickLogin() {
        clickButton(loginBtn);
    }

    public void clickForgetPassword() {
        clickButton(forgetPasswordBtn);
    }



    public void navigateToLoginPage() {
        navigateToURL(Config.LOGIN_PAGE_URL);
        // Navigate to the login page
    }

    public void loginWithCredentials(String email, String password) {
        // Locate email and password fields and sign in button
        enterEmail(email);
        enterPassword(password);
        clickLogin();
    }
    public  boolean verifyInvalidEmailMessage() {
        return verifyErrorMessage(Constants.INVALID_EMAIL);

    }

    public  boolean verifyUnsuccessfulLoginMessage() {
        return verifyErrorMessage(Constants.UNSUCCESSFULL_LOGIN);
    }


    public boolean isLoginButtonEnabled(){
        return driver.findElement(loginBtn).isEnabled();
    }

    public void clickPasswordEyeButton() {
        driver.findElement(passwordMaskBtn).click();
    }

    public String getPasswordFieldType(){
        return driver.findElement(passwordField).getAttribute("type");
    }
}
