package com.eatP2.pages.login;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgetPasswordPage extends BasePage {
    By email = By.id("email");
    By backBtn = By.id("back");
    By submitBtn = By.id("submit");
    By forgetPswdField = By.linkText("Forgot Password");
    public ForgetPasswordPage(WebDriver driver) {
        super(driver);
    }

    public void navigateToForgotPassword() {
        navigateToURL(Config.FORGOT_PSW_PAGE_URL);
    }

    public boolean verifyPageUrl() {
        return super.verifyPageUrl(Config.FORGOT_PSW_PAGE_URL);
    }
    public void enterEmail(String email) {
        setValue(this.email, email);
    }
    public void clickBackBtn() {
        clickButton(backBtn);
    }
    public void clickSubmitBtn() {
        clickButton(submitBtn);
    }


    public boolean verifySuccessMessage() {
        return super.verifySuccessMessage(Constants.SUCCESS_PASSWORD_RESET);
    }

    public boolean verifyInvalidEmailMessage() {
        return super.verifyErrorMessage(Constants.INVALID_EMAIL);
    }

    public boolean isSubmitBtnEnabled() {
        return isEnabled(submitBtn);
    }

    public boolean verifyRoutingBackButton() {
        return verifyPageUrl(Config.LOGIN_PAGE_URL);
    }
}
