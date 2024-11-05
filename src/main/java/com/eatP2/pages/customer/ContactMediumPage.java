package com.eatP2.pages.customer;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.DAO.ContactMediumDAO;
import com.eatP2.DAO.CustomerDAO;
import com.eatP2.models.ContactMedium;
import com.eatP2.models.IndividualCustomer;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ContactMediumPage extends BasePage {
    IndividualCustomer customer;
    By emailField = By.id("email");
    By homephoneField = By.id("homephone");
    By mobilephoneField = By.id("mobilephone");
    By faxField = By.id("fax");

    public ContactMediumPage(WebDriver driver) {
        super(driver);
        CustomerDAO customerDAO = new CustomerDAO();
        this.customer=customerDAO.getCustomerInstanceFromDatabase();
    }


    public void navigateToContactMediumUpdate() {
        login();
        navigateToURL(Config.CUST_INFO_PAGE_URL+customer.getId());
        clickEditIcon();
    }

    public boolean validateAllFields() {
        return isDisplayed(emailField)&&
                isDisplayed(homephoneField)&&
                isDisplayed(mobilephoneField)&&
                isDisplayed(faxField)&&
                isDisplayed(saveBtn);
    }

    public String getEmailValue() {
        return driver.findElement(emailField).getText();
    }
    public String getHomePhoneValue() {
        return driver.findElement(homephoneField).getText();
    }
    public String getMobilePhoneValue() {
        return driver.findElement(mobilephoneField).getText();
    }
    public String getFaxValue() {
        return driver.findElement(faxField).getText();
    }

    public void enterEmail(String email) {
        setValue(this.emailField, email);
    }
    public void enterHomephone(String homephone) {
        setValue(mobilephoneField,homephone);
    }
    public void enterMobilephone(String mobilephone) {
        setValue(mobilephoneField,mobilephone);
    }
    public void enterFax(String fax) {
        setValue(faxField,fax);
    }
    public IndividualCustomer getCustomer() {
        return customer;
    }
    public ContactMedium getContactMediumFromDatabase() {
        ContactMediumDAO contactMediumDAO = new ContactMediumDAO();
        return contactMediumDAO.getContactMediumFromDatabase(customer.getId());
    }

    public boolean verifySuccessMessage(){
        return super.verifySuccessMessage(Constants.SUCCESS_UPDATE_CONTACT);
    }


    public boolean isEmailNullErrorDisplayed() {
        return super.verifyErrorMessage(Constants.WARNING_EMPTY_EMAIL);
    }
    public boolean isPhoneNullErrorDisplayed() {
        return super.verifyErrorMessage(Constants.WARNING_EMPTY_MOBILE);
    }

    public boolean isEmailInvalidErrorDisplayed() {
        return super.verifyErrorMessage(Constants.INVALID_EMAIL);
    }

    public boolean verifyInvalidFieldErrorDisplayed(String fieldName) {
        return super.verifyErrorMessage(fieldName+Constants.INVALID_FIELD);
    }
}
