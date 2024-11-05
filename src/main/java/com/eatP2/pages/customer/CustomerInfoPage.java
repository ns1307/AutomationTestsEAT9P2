package com.eatP2.pages.customer;

import com.eatP2.Config;
import com.eatP2.Constants;
import com.eatP2.DAO.CustomerDAO;
import com.eatP2.models.IndividualCustomer;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.WebDriver;

public class CustomerInfoPage extends BasePage {
    IndividualCustomer individualCustomer;

    public CustomerInfoPage(WebDriver driver) {
        super(driver);
        CustomerDAO customerDAO = new CustomerDAO();
        this.individualCustomer= customerDAO.getCustomerInstanceFromDatabase();
    }
    public CustomerInfoPage(WebDriver driver, IndividualCustomer individualCustomer) {
        super(driver);
        this.individualCustomer = individualCustomer;
    }

    public void navigateToCustomerInfoPage() {
        login();

        // Navigate to Customer Info page after login
        driver.get(Config.CUST_INFO_PAGE_URL +individualCustomer.getId());
    }


    public boolean verifyConfirmationMessage() {
        return getConfirmationMessage().equals(Constants.CONFIRM_DELETE);
    }
    public boolean verifyYesBtnDisplayed() {
        return isDisplayed(yesBtn);
    }
    public boolean verifyCancelBtnDisplayed() {
        return isDisplayed(cancelBtn);
    }

    public boolean isCustomerDeletedFromDatabase() {
        CustomerDAO customerDAO = new CustomerDAO();
        return customerDAO.isCustomerDeletedFromDatabase(individualCustomer.getId());
    }

    public boolean verifySuccessMessage() {
        return super.verifySuccessMessage(Constants.SUCCESS_DELETE);
    }

    public boolean verifyErrorMessage() {
        return super.verifyErrorMessage(Constants.NOT_DELETABLE_CUST);
    }

    public boolean verifyRouteAfterSuccess() {
        return verifyPageUrl(Config.HOME_PAGE_URL);
    }
}
