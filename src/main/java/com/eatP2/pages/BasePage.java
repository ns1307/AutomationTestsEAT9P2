package com.eatP2.pages;

import com.eatP2.Config;
import com.eatP2.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected By successMessage = By.id("success-message");
    protected By errorMessage = By.id("error-message");
    protected By warningMessage = By.id("error-message");
    protected By confirmationPopup =By.id("confirmation-popup");

    protected By cancelBtn =By.id("cancel-button");
    protected By okBtn =By.id("ok-button");
    protected By yesBtn =By.id("yes-button");
    protected By noBtn =By.id("no-button");

    protected By prevBtn =By.id("previous-button");
    protected By nextBtn=By.id("next-button");


    protected By saveBtn =By.id("saveButton");
    protected By submitBtn =By.id("submitButton");
    protected By trashBtn=By.id("trashButton");

    protected By usernameField = By.id("username");
    protected By passwordField = By.id("password");
    protected By loginBtn = By.id("login-button");
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
    }


    public boolean isElementPresent( By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }


    public void login(){
        // Navigate to Login page
        driver.get(Config.LOGIN_PAGE_URL);

        // Perform login
        WebElement usernameField = driver.findElement(this.usernameField);
        WebElement passwordField = driver.findElement(this.passwordField);
        WebElement loginButton = driver.findElement(loginBtn);

        usernameField.sendKeys(Constants.username);
        passwordField.sendKeys(Constants.password);
        loginButton.click();
    }

    public  boolean checkDisabledSaveButton() {
        WebElement saveButton = driver.findElement(saveBtn);
        return saveButton.isEnabled();
    }


    public String getErrorMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement warningElement = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        return warningElement.getText();

    }
    public String getWarningMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        WebElement warningElement = wait.until(ExpectedConditions.visibilityOfElementLocated(warningMessage));
        return warningElement.getText();
    }
    public String getConfirmationMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationPopup)).getText();
    }

    public boolean checkErrorMessage(String fieldName){
        return driver.findElement(errorMessage).getText().equals(fieldName+" must be valid." );
    }

    public void clickButton(By id){
        driver.findElement(id).click();
    }

    public void clickEditIcon() {
        // Click the trash bin icon to delete customer info
        driver.findElement(By.id("edit")).click();
    }
    public void clickCancelButton() {
            driver.findElement(cancelBtn).click();

    }

    public void clickCancelBtn(){
        clickButton(cancelBtn);
    }

    public void clickOkBtn() {
        clickButton(okBtn);
    }
    public void clickYesBtn() {
        clickButton(yesBtn);
    }
    public void clickNoBtn() {
        clickButton(noBtn);
    }

    public void clickTrashIcon() {
        clickButton(trashBtn);
    }
    public boolean verifyPageUrl(String url){
        return driver.getCurrentUrl().equals(url);
    }


    public boolean isSuccessMessageDisplayed() {
        return isDisplayed(successMessage);
    }
    public boolean isConfirmationPopupDisplayed() {
        return isDisplayed(confirmationPopup);
    }

    public boolean isErrorMessageDisplayed() {
        return isDisplayed(errorMessage);
    }

    public boolean isDisplayed(By locator){
        if(isElementPresent(locator)) {
            return driver.findElement(errorMessage).isDisplayed()&&driver.findElement(errorMessage).isEnabled();
        }
        else{
            return false;
        }
    }
    public boolean verifySuccessMessage(String message) {
        return driver.findElement(successMessage).getText().equals(message);
    }
    public boolean verifyErrorMessage(String message){
        return driver.findElement(errorMessage).getText().equals(message);
    }

    protected boolean verifyWarningMessage(String message) {
        return driver.findElement(warningMessage).getText().equals(message);
    }
    public boolean verifyConfirmationMessage(String message) {
        return driver.findElement(confirmationPopup).getText().equals(message);
    }

    public boolean isEnabled(By locator){
        return  driver.findElement(locator).isEnabled();
    }
    protected void navigateToURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }
    protected String getValue(By locator) {
        return driver.findElement(locator).getText();
    }
    protected void setValue(By locator, String value) {
        driver.findElement(locator).clear();
        driver.findElement(locator).sendKeys(value);
    }
    protected void chooseFromDropdown(By locator, String value) {
        Select dropdown = new Select(driver.findElement(locator));
        dropdown.selectByValue(value);
    }

    public void clickSaveButton() {
        driver.findElement(saveBtn).click();
    }
    public void clickSubmitButton(){
        driver.findElement(submitBtn).click();
    }

    public void clickNextButton() {
        driver.findElement(nextBtn).click();
    }

    public void clickPreviousButton() {
        driver.findElement(prevBtn).click();
    }

    public boolean checkPreviousButton() {
        return isDisplayed(prevBtn);
    }
    public boolean checkNextButton() {
        return isDisplayed(nextBtn);
    }
}
