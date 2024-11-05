package com.eatP2.customer.accounts;

import com.eatP2.BaseTest;
import com.eatP2.models.Address;
import com.eatP2.models.BillingAccount;
import com.eatP2.pages.customer.BillingAccountPage;
import org.junit.Test;

import static org.junit.Assert.*;

public class BillingAccountUpdateTests extends BaseTest {//TS12-Billing Account Update

    @Test
    public void testUpdateInvoiceAccountWithValidData() {
        // Test Case ID: TC 01
        // Test Case Name: Updating the invoice account with valid data
        // Pre-Condition: On the "Customer Accounts" page, there must be an existing customer account.

        BillingAccountPage billingAccountPage= new BillingAccountPage(driver);
        billingAccountPage.navigateToBillingAccountPage();

        if(billingAccountPage.validateAllFields()){

            BillingAccount actualBillingAcc= billingAccountPage.getBillingAccountFromDatabase();;
            assertNotNull("Billing account does not exist.", actualBillingAcc);

            BillingAccount displayedBillingAcc= new BillingAccount(billingAccountPage.getAccNameValue(),billingAccountPage.getAccStatusValue(),billingAccountPage.getAccTypeValue(),billingAccountPage.getAccDescriptionValue(), new Address(billingAccountPage.getCityValue(),billingAccountPage.getDistrictValue(),billingAccountPage.getPostalCodeValue(),billingAccountPage.getAddressDescriptionValue()));
            assertTrue("Displayed billing account is not same with database.",actualBillingAcc.equals(displayedBillingAcc));
            try {

                billingAccountPage.enterAccNameValue("Updated Acc");
                billingAccountPage.enterAccStatusValue("Active");
                billingAccountPage.enterAccDescriptionValue("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium.");
                billingAccountPage.clickSaveButton();


                if(billingAccountPage.isSuccessMessageDisplayed()){
                    //Expected Result: The message "Billing account information is updated successfully!" is displayed.
                    assertTrue("Success message is incorrect",billingAccountPage.verifySuccessMessage());
                }
                else{
                    fail("Success message is not displayed.");
                }


            }
            catch (Exception e){
                fail("Some text fields are not editable.");
            }


            BillingAccount updatedBillingAccount= billingAccountPage.getBillingAccountFromDatabase();
            assertTrue("Updated billing account does not exist.",updatedBillingAccount!=null);
            //The "Contact Medium Update" screen should open, and the fields should be pre-filled with the customer's current information (email, home phone, mobile phone, fax).
            assertFalse("The billing account could not updated succesfully.",actualBillingAcc.equals(updatedBillingAccount));

        }
    }

    @Test
    public void testValidationAccountStatusDropdown(){
        // Test Case ID: TC 02
        // Test Case Name: Validation of "Account Status" dropdown
        // Pre-Condition: On the "Customer Accounts" page, there must be an existing customer account.
        BillingAccountPage billingAccountPage= new BillingAccountPage(driver);
        billingAccountPage.navigateToBillingAccountPage();


        BillingAccount actualBillingAcc= billingAccountPage.getBillingAccountFromDatabase();;
        assertNotNull("Billing account does not exist.", actualBillingAcc);

        BillingAccount displayedBillingAcc= new BillingAccount(billingAccountPage.getAccNameValue(),billingAccountPage.getAccStatusValue(),billingAccountPage.getAccTypeValue(),billingAccountPage.getAccDescriptionValue(), new Address(billingAccountPage.getCityValue(),billingAccountPage.getDistrictValue(),billingAccountPage.getPostalCodeValue(),billingAccountPage.getAddressDescriptionValue()));
        assertTrue("Displayed billing account is not same with database.",actualBillingAcc.equals(displayedBillingAcc));

        // Step 2 : The user selects the "Active" option from the "Account Status" dropdown menu.
        // Expected Result: The account status option should be selectable as "Active."
        try {
            billingAccountPage.enterAccStatusValue("Active");
        }
        catch (Exception e){
            fail("Active option can not be selected.");
        }

        // Step 3 : The user selects the "Inactive" option from the "Account Status" dropdown menu.
        // Expected Result: The account status option should be selectable as "Inactive."
        try {
            billingAccountPage.enterAccStatusValue("Inactive");
        }
        catch (Exception e){
            fail("Inactive option can not be selected.");
        }


        // Step 4 : The user selects the "Cancelled" option from the "Account Status" dropdown menu.
        // Expected Result: The account status option should be selectable as "Cancelled."
        try {
            billingAccountPage.enterAccStatusValue("Cancelled option can not be selected.");
        }
        catch (Exception e){
            fail("Inactive option can not be selected.");
        }
        billingAccountPage.clickSaveButton();

        if(billingAccountPage.isSuccessMessageDisplayed()){
            //Expected Result: The message "Billing account information is updated successfully!" is displayed.
            assertTrue("Success message is incorrect", billingAccountPage.verifySuccessMessage());
        }
        else{
            fail("Success message is not displayed.");
        }

    }

    @Test
    public void testAttemptToUpdateWithEmptyRequiredFields() {
        // Test Case ID: TC 03
        // Test Case Name: Attempting to update while leaving required fields empty
        // Pre-Condition: On the "Customer Accounts" page, there must be an existing customer account.
        BillingAccountPage billingAccountPage= new BillingAccountPage(driver);
        billingAccountPage.navigateToBillingAccountPage();


        BillingAccount actualBillingAcc= billingAccountPage.getBillingAccountFromDatabase();;
        assertNotNull("Billing account does not exist.", actualBillingAcc);

        BillingAccount displayedBillingAcc= new BillingAccount(billingAccountPage.getAccNameValue(),billingAccountPage.getAccStatusValue(),billingAccountPage.getAccTypeValue(),billingAccountPage.getAccDescriptionValue(), new Address(billingAccountPage.getCityValue(),billingAccountPage.getDistrictValue(),billingAccountPage.getPostalCodeValue(),billingAccountPage.getAddressDescriptionValue()));
        assertTrue("Displayed billing account is not same with database.",actualBillingAcc.equals(displayedBillingAcc));

        billingAccountPage.enterAccNameValue("");
        billingAccountPage.enterAccDescriptionValue("");

        // Expected Result: Save button should be disabled.
        if(!billingAccountPage.isSaveButtonEnabled()){
            // Expected Result: Error message should be displayed indicating required fields are empty.
            assertTrue("Account Name can is null warning not displayed.",billingAccountPage.verifyNullAccNameMessage());
            assertTrue("Account Description can is null warning not displayed.",billingAccountPage.verifyNullAccDescriptionMessage());
        }
        else{
            fail("Save button should not be enabled.");
        }

        // Expected Result: Billing account should not be updated in database.
        BillingAccount updatedBillingAccount= billingAccountPage.getBillingAccountFromDatabase();
        assertTrue("Updated billing account does not exist.",updatedBillingAccount!=null);
        assertTrue("The billing account updated in database when it shouldnt have been.",actualBillingAcc.equals(updatedBillingAccount));

    }

    @Test
    public void testCancelUpdateProcess() {
        // Test Case ID: TC 04
        // Test Case Name: Canceling the update process
        // Pre-Condition: On the "Customer Accounts" page, there must be an existing customer account.
        BillingAccountPage billingAccountPage= new BillingAccountPage(driver);
        billingAccountPage.navigateToBillingAccountPage();


        BillingAccount actualBillingAcc= billingAccountPage.getBillingAccountFromDatabase();;
        assertNotNull("Billing account does not exist.", actualBillingAcc);

        BillingAccount displayedBillingAcc= new BillingAccount(billingAccountPage.getAccNameValue(),billingAccountPage.getAccStatusValue(),billingAccountPage.getAccTypeValue(),billingAccountPage.getAccDescriptionValue(), new Address(billingAccountPage.getCityValue(),billingAccountPage.getDistrictValue(),billingAccountPage.getPostalCodeValue(),billingAccountPage.getAddressDescriptionValue()));
        assertTrue("Displayed billing account is not same with database.",actualBillingAcc.equals(displayedBillingAcc));

        billingAccountPage.enterAccNameValue("Temp Name");
        billingAccountPage.enterAccStatusValue("Cancelled");
        billingAccountPage.enterAccDescriptionValue("Lorem ipsum dolor sit amet");

        billingAccountPage.clickCancelBtn();

        // Expected Result: The user is redirected back to the Customer Account page.
        // Changes should not be saved, and there should be no changes on the Customer Account page.
        assertTrue("Not redirected to correct url after cancellation",billingAccountPage.verifyRoutingCancelButton());;
        BillingAccount updatedBillingAccount= billingAccountPage.getBillingAccountFromDatabase();
        assertTrue("The billing account updated in database when it shouldnt have been.",actualBillingAcc.equals(updatedBillingAccount));

    }

}

