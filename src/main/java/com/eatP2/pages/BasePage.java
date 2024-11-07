package com.eatP2.pages;

import com.eatP2.Config;
import com.eatP2.Constants;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    protected WebDriver driver;
    WebDriverWait wait ;
    protected By successMessage = By.id("success-message");
    protected By errorMessage = By.id("error-message");
    protected By warningMessage = By.id("warning-message");
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

    protected By emailField = By.id("floatingEmail");
    protected By passwordField = By.id("floatingPassword");
    protected By loginBtn = By.xpath("/html/body/app-root/app-login/div/form/div[3]/div/button");
    public BasePage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public boolean isElementPresent( By id) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(id));
            find(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void login(){
        // Navigate to Login page
        driver.get(Config.LOGIN_PAGE_URL);

        // Perform login
        WebElement usernameField = find(this.emailField);
        WebElement passwordField = find(this.passwordField);
        WebElement loginButton = driver.findElement(loginBtn);

        usernameField.sendKeys(Constants.username);
        passwordField.sendKeys(Constants.password);
        clickButtonWithJS(loginBtn);
    }

    public  boolean checkDisabledSaveButton() {
        WebElement saveButton = find(saveBtn);
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
        return find(errorMessage).getText().equals(fieldName+" must be valid." );
    }

    public void clickButton(By id){
        delay(1);
        wait.until(ExpectedConditions.elementToBeClickable(id)).click();
    }
    public void clickButtonWithJS(By id){
        delay(1);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", find(id));

    }
    public void clickButtonWithScroll(By id,boolean scroll){
        delay(1);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView("+scroll+");",
                wait.until(ExpectedConditions.elementToBeClickable(id)));
        clickButton(id);
    }

    public void clickEditIcon() {
        // Click the trash bin icon to delete customer info
        clickButton(By.id("edit"));
    }


    public void clickCancelBtn(){
        clickButton(cancelBtn);
    }

    public void clickOkButton() {
        clickButton(okBtn);
    }
    public void clickYesButton() {
        clickButton(yesBtn);
    }
    public void clickNoButton() {
        clickButton(noBtn);
    }

    public void clickTrashIcon() {
        clickButton(trashBtn);
    }
    public boolean verifyPageUrl(String url){
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            return wait.until(ExpectedConditions.urlToBe(url));
        }
        catch (Exception e){
            return false;
        }
    }
    protected WebElement find(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    protected List<WebElement> findAll(By id) {
        return driver.findElements(id);
    }
    protected boolean isWarningMessageDisplayed() {
        return isDisplayed(warningMessage);
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
            return find(errorMessage).isDisplayed();
        }
        else{
            return false;
        }
    }
    public boolean verifySuccessMessage(String message) {
        return find(successMessage).getText().equals(message);
    }
    public boolean verifyErrorMessage(String message){
        return find(errorMessage).getText().equals(message);
    }

    protected boolean verifyWarningMessage(String message) {
        return find(warningMessage).getText().equals(message);
    }
    public boolean verifyConfirmationMessage(String message) {
        return find(confirmationPopup).getText().equals(message);
    }

    public boolean isEnabled(By locator){
        return  find(locator).isEnabled();
    }
    protected void navigateToURL(String url) {
        driver.get(url);
        driver.manage().window().maximize();
    }
    protected String getValue(By locator) {
        return find(locator).getText();
    }
    protected void setValue(By locator, String value) {
        delay(0.5);
        find(locator).clear();
        find(locator).sendKeys(value);

    }
    protected void chooseFromDropdown(By locator, String value) {
        Select dropdown = new Select(find(locator));
        dropdown.selectByValue(value);
    }

    public void clickSaveButton() {
        clickButton(saveBtn);
    }
    public void clickSubmitButton(){
        clickButton(submitBtn);
    }

    public void clickNextButton() {
        clickButton(nextBtn);
    }

    public void clickPreviousButton() {
        clickButton(prevBtn);
    }

    public boolean checkPreviousButton() {
        return isDisplayed(prevBtn);
    }
    public boolean checkNextButton() {
        return isDisplayed(nextBtn);
    }
    public void delay(double second) {
        try {
            Thread.sleep((long) (second* 1000));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
