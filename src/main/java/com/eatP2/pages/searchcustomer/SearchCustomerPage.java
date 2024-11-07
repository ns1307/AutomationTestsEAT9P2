package com.eatP2.pages.searchcustomer;

import com.eatP2.pages.BasePage;
import com.eatP2.pages.customer.CreateCustomerPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchCustomerPage extends BasePage {
    private By filtersHeader=By.xpath("//div[@class='filters']//h2");
    private By idNumberField=By.id("natId");
    private By customerIdField=By.id("customerId");
    private By accountNumberField=By.id("accNumber");
    private By gsmNumberField=By.id("mobilePhone");
    private By firstNameField=By.id("firstName");
    private By lastNameField=By.id("lastName");
    private By searchButton=By.xpath("//div[@class='buttons']//button[@type='submit']");
    private By clearButton=By.xpath("//div[@class='buttons']//button[@type='button']");
    private By resultsTable=By.xpath("/html/body/app-root/app-customer-search/body/form/div[1]/div[2]/table");
    private By createCustomerButton=By.xpath("/html/body/app-root/app-customer-search/body/form/div[1]/div[2]/div/button");
    private By nameFieldMessage=By.xpath("/html/body/app-root/app-customer-search/body/form/div[1]/div[1]/div[1]");

    public SearchCustomerPage(WebDriver driver) {
        super(driver);
    }

    public String getIdNumber(){
        return find(idNumberField).getText();
    }
    public void setIdNumber(String idNumber){
        setValue(idNumberField,idNumber);
    }

    public String getCustomerId(){
        return find(customerIdField).getText();
    }
    public void setCustomerId(String customerId){
        setValue(customerIdField,customerId);
    }
    public String getAccountNumber(){
        return find(accountNumberField).getText();
    }
    public void setAccountNumber(String accountNumber){
        setValue(accountNumberField,accountNumber);
    }

    public String getGsmNumber(){
        return find(gsmNumberField).getText();
    }
    public void setGsmNumber(String gsmNumber){
        setValue(gsmNumberField,gsmNumber);
    }

    public String getFirstName(){
        return find(firstNameField).getText();
    }
    public void setFirstName(String firstName){
        setValue(firstNameField,firstName);
    }

    public String getLastName(){
        return find(lastNameField).getText();
    }
    public void setLastName(String lastName){
        setValue(lastNameField,lastName);
    }

    public  void clickSearchButton() {
        clickButtonWithScroll(searchButton,false);
    }
    public void clickClearButton() {
        clickButtonWithScroll(clearButton,false);
    }

    public By getSearchButton() {
        return searchButton;
    }


    public String getNameFieldMessage() {
        //return wait.until(ExpectedConditions.visibilityOfElementLocated(nameFieldMessage)).getText();
        return find(nameFieldMessage).getText();
    }

    //Transition method
    public CreateCustomerPage clickCreateCustomerButton() {
        clickButton(createCustomerButton);
        return new CreateCustomerPage(driver);
    }


    public boolean isResultsTableDisplayed() {
        return find(resultsTable).isDisplayed();

    }

    public boolean isFiltersHeaderDisplayed() {
        return find(filtersHeader).isDisplayed();
    }


    public boolean isSearchButtonEnabled() {
        return isEnabled(searchButton);
    }
}
