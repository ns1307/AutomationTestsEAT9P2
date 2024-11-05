package com.eatP2.customer.info;

import com.eatP2.BaseTest;
import com.eatP2.DAO.CustomerDAO;
import com.eatP2.pages.customer.CustomerInfoPage;
import org.junit.Test;
import org.openqa.selenium.By;


import static org.junit.Assert.*;

public class CustomerDeleteTest extends BaseTest {// TS5-Customer Info Delete


    @Test
    public void testCustomerInfoDeleteConfirmationPopupDisplay() {
        // Test Case ID: TC 01
        // Test Case Name: Customer Info Delete Confirmation Popup Display
        // Test Description: When the user clicks on the trash icon on the "Customer Info" page,
        // the confirmation pop-up saying "Are you sure to delete this customer?" will be tested.
        CustomerInfoPage customerInfoPage = new CustomerInfoPage(driver);
        customerInfoPage.navigateToCustomerInfoPage();

        customerInfoPage.clickTrashIcon();

        // Expected Result: A confirmation popup containing the message"Are you sure to delete this customer?"
        // should appear with "Yes" and "No" options.
        if(customerInfoPage.isConfirmationPopupDisplayed()){
            try {
                assertTrue("Confirmation message is not correct",customerInfoPage.verifyConfirmationMessage());
                assertTrue("Yes button not displayed",customerInfoPage.verifyYesBtnDisplayed());
                assertTrue("Cancel button not displayed",customerInfoPage.verifyCancelBtnDisplayed());
            }
            catch (Exception e) {
                fail("Error in confirmation message");
            }
        }
        else{
            fail("Confirmation popup did not appear.");
        }
    }

    @Test
    public void testCustomerInfoDeletionCancel() {
        // Test Case ID: TC 02
        // Test Case Name: Customer Info Deletion - Cancel
        // Test Description: Verify that clicking 'Cancel' on the confirmation popup does not delete the customer info.

        CustomerInfoPage customerInfoPage = new CustomerInfoPage(driver);
        customerInfoPage.navigateToCustomerInfoPage();

        customerInfoPage.clickTrashIcon();

        // Step 3: Click 'Cancel' on the confirmation popup


        // Expected Result: The customer record should not be deleted, and the popup should close.
        // After the popup is closed, the "Customer Info" page should be displayed again.
        assertTrue("Confirmation message is not correct",customerInfoPage.verifyConfirmationMessage());
        customerInfoPage.clickCancelBtn();
        assertFalse("Confirmation popup not closed.",customerInfoPage.isConfirmationPopupDisplayed());
        assertFalse("Customer deleted from database despite clicking cancel", customerInfoPage.isCustomerDeletedFromDatabase());

    }

    @Test
    public void testDeletionOfCustomerWithActiveProducts() {
        // Test Case ID: TC 03
        // Test Case Name: Deletion of Customer with Active Products
        // Test Description: Verify that attempting to delete a customer with active products displays an error message.
        CustomerDAO customerDAO = new CustomerDAO();

        CustomerInfoPage customerInfoPage = new CustomerInfoPage(driver,customerDAO.getOneCustomerWithBillingAccount());
        customerInfoPage.navigateToCustomerInfoPage();

        customerInfoPage.clickTrashIcon();
        // Step 3: Click 'Yes' on the confirmation popup
        customerInfoPage.clickYesBtn();


        // Expected Result: The system should check whether the customer has active products.
        // If the customer has active products, a warning message saying "Since the customer has active products, the customer cannot be deleted." should be displayed.
        // The customer record should not be deleted, and the `deleted_date` field in the database should remain null.
        assertFalse("Customer deleted from database despite he has active products.", customerInfoPage.isCustomerDeletedFromDatabase());
        if(customerInfoPage.isErrorMessageDisplayed()){
            assertTrue("Error message incorrect.",customerInfoPage.verifyErrorMessage());
            customerInfoPage.clickOkBtn();
        }
        else{
            fail("Error message not displayed.");
        }

    }

    @Test
    public void testCustomerInfoDeletionSuccess() {
        // Test Case ID: TC 04
        // Test Case Name: Successful Customer Info Deletion
        // Test Description: Verify that deleting customer info without any active products successfully removes the customer.

        CustomerDAO customerDAO = new CustomerDAO();

        CustomerInfoPage customerInfoPage = new CustomerInfoPage(driver,customerDAO.getOneCustomerWithoutBillingAccount());
        customerInfoPage.navigateToCustomerInfoPage();

        customerInfoPage.clickTrashIcon();
        // Step 3: Click 'Yes' on the confirmation popup
        customerInfoPage.clickYesBtn();
        // Expected Result: The system should check whether the customer has any active products.
        //Since the customer has no active products, the customer record should be soft-deleted, and the `deleted_date` field should be updated with the current date.
        //A "Customer Deleted" message should be displayed to the user with "OK" button.
        assertTrue("Customer was not deleted successfully from database.", customerInfoPage.isCustomerDeletedFromDatabase());
        if(customerInfoPage.isSuccessMessageDisplayed()){
            assertTrue("Success message incorrect.",customerInfoPage.verifySuccessMessage());
            assertFalse("Customer info was not deleted in frontend.",customerInfoPage.isDisplayed(By.id("customer-info")));
            assertTrue("Did not route to home page after successful operation.",customerInfoPage.verifyRouteAfterSuccess());
        }
        else{
            fail("Success message not displayed.");
        }
    }

}
