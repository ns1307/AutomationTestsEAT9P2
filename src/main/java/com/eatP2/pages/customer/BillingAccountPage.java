package com.eatP2.pages.customer;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.DAO.BillingAccountDAO;
import com.eatP2.DAO.CustomerDAO;
import com.eatP2.models.BillingAccount;
import com.eatP2.models.IndividualCustomer;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BillingAccountPage extends BasePage {
    IndividualCustomer individualCustomer;
    BillingAccount billingAccount;

    By row = By.xpath("//tr");
    By editBtn =By.xpath(".//button[contains(@class, 'edit')]");
    By accNameField = By.xpath("//input[@id='accountName']");
    By accStatusDropdown = By.xpath("//input[@id='accountStatus']");
    By accTypeDropdown = By.xpath("//input[@id='accountType']");
    By accDescriptionField = By.xpath("//input[@id='accountDescription']");
    By city =By.xpath("//input[@id='city']");
    By district =By.xpath("//input[@id='district']");
    By postalCode = By.xpath("//input[@id='postalCode']");
    By addressDescription =By.xpath("//input[@id='addressDescription']");

    public BillingAccountPage(WebDriver driver) {
        super(driver);
        CustomerDAO customerDAO = new CustomerDAO();
        this.individualCustomer= customerDAO.getOneCustomerWithBillingAccount();
        BillingAccountDAO billingAccountDAO = new BillingAccountDAO();
        this.billingAccount = billingAccountDAO.getBillingAccountInstanceOfUserFromDatabase(individualCustomer.getId());
    }

    public void navigateToBillingAccountPage() {
        login();
        // Reusable method to navigate to the "Customer Accounts" page.

        navigateToURL(Config.ACCOUNTS_PAGE_URL+individualCustomer.getId());


        List<WebElement> rows = findAll(row);

        // Find row with ours name and click pencil button
        for (WebElement row : rows) {
            if (row.getText().contains(billingAccount.getAccName())) {
                row.findElement(editBtn).click();
                break;
            }
        }
    }

    public String getAccNameValue() {
        return getValue(accNameField);
    }

    public void enterAccNameValue(String accNameField) {
        setValue(this.accNameField, accNameField);
    }
    public String getAccStatusValue() {
        return getValue(accStatusDropdown);
    }
    public void enterAccStatusValue(String accStatus) {
        chooseFromDropdown(accStatusDropdown, accStatus);
    }

    public String getAccTypeValue() {
        return getValue(accTypeDropdown);
    }
    public void enterAccTypeValue(String accType) {
        chooseFromDropdown(accTypeDropdown, accType);
    }
    public String getAccDescriptionValue() {
        return getValue(accDescriptionField);
    }
    public void enterAccDescriptionValue(String accDescription) {
        setValue(accDescriptionField, accDescription);
    }
    public String getCityValue() {
        return getValue(city);
    }

    public String getDistrictValue() {
        return getValue(district);
    }

    public String getPostalCodeValue() {
        return getValue(postalCode);
    }

    public String getAddressDescriptionValue() {
        return getValue(addressDescription);
    }


    public BillingAccount getBillingAccountFromDatabase() {
        BillingAccountDAO billingAccountDAO=new BillingAccountDAO();
        return billingAccountDAO.getBillingAccountFromDatabase(billingAccount.getId());
    }

    public boolean verifySuccessMessage() {
        return super.verifySuccessMessage(Constants.SUCCESS_UPDATE_BILLING_ACC);
    }
    public boolean verifyNullAccNameMessage() {
        return verifyWarningMessage(Constants.NULL_ACC_NAME);
    }


    public boolean verifyNullAccDescriptionMessage() {
        return verifyWarningMessage(Constants.NULL_ACC_DESCRIPTION);
    }
    public boolean isSaveButtonEnabled() {
        return find(saveBtn).isEnabled();
    }

    public boolean verifyRoutingCancelButton() {
        return verifyPageUrl(Config.ACCOUNTS_PAGE_URL+individualCustomer.getId());

    }

    public boolean validateAllFields() {

        return  isDisplayed(accNameField)&&
                isDisplayed(accStatusDropdown)&&
                isDisplayed(accTypeDropdown)&&
                isDisplayed(accDescriptionField)&&
                isDisplayed(city)&&
                isDisplayed(district)&&
                isDisplayed(postalCode)&&
                isDisplayed(addressDescription)&&
                isSaveButtonEnabled();
    }
}
