package com.eatP2.pages.login;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class ForgetPasswordPage extends BasePage {
    By email = By.id("floatingUsername");
    By backBtn = By.xpath("/html/body/app-root/app-forgot-password/div/form/div[2]/a");
    // Note: forget password button is displayed in login page.
    By forgetPswdBtn = By.xpath("/html/body/app-root/app-login/div/form/a");

    public ForgetPasswordPage(WebDriver driver) {
        super(driver);
        this.submitBtn=By.xpath("/html/body/app-root/app-forgot-password/div/form/button");
        this.errorMessage=By.xpath("/html/body/app-root/app-forgot-password/div/form/div[1]/small");
    }

    public void navigateToForgotPassword() {
        clickForgetPswdButton();
    }

    private void clickForgetPswdButton() {
        clickButton(forgetPswdBtn);
    }

    public boolean verifyPageUrl() {
        return super.verifyPageUrl(Config.FORGOT_PSW_PAGE_URL);
    }
    public void enterEmail(String email) {
        setValue(this.email, email);
    }
    public void clickBackBtn() {
        clickButtonWithJS(backBtn);
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
        return verifyPageUrl(Config.LOGIN_PAGE_URL+"/login");
    }
    public boolean verifySuccessTickOnButton() {
        return driver.findElement(submitBtn).getAttribute("class").contains("success");
    }


}
