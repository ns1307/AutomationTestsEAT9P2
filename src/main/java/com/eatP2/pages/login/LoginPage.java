package com.eatP2.pages.login;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.DAO.UserDAO;
import com.eatP2.models.User;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class LoginPage extends BasePage {
    By emailField = By.id("floatingEmail");
    By passwordField = By.id("floatingPassword");
    By loginBtn = By.xpath("/html/body/app-root/app-login/div/form/div[3]/div/button");
    By forgetPasswordBtn = By.id("forget-password-button");
    By passwordMaskBtn = By.id("password-eye");
    public LoginPage(WebDriver driver) {
        super(driver);
        this.errorMessage = By.xpath("/html/body/app-root/app-login/div/form/div[2]/div[2]/small[1]");
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
    public boolean verifyCapsLockMessage(){
        return verifyWarningMessage(Constants.CAPS_LOCK_ON);
    }


    public boolean isLoginButtonEnabled(){
        return driver.findElement(loginBtn).isEnabled();
    }

    public void clickPasswordEyeButton() {
        clickButton(passwordMaskBtn);
    }
    public boolean isPasswordEyeDisplayed() {
        return isDisplayed(passwordMaskBtn);
    }

    public String getPasswordFieldType(){
        return driver.findElement(passwordField).getAttribute("type");
    }
    public User getUserInstanceFromDatabase(){
        UserDAO userDAO= new UserDAO();
        return userDAO.getUserFromDatabase();
    }
    public boolean verifySuccessfulRouting(){
        return verifyPageUrl(Config.SEARCH_PAGE_URL);
    }

    public boolean isCapsLockSupported() {
        enterPassword("PASSWORD");
        return isWarningMessageDisplayed();
    }


}
