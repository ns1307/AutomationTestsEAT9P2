package com.eatP2.customer.contactmedium;

import com.eatP2.BaseTest;
import com.eatP2.models.ContactMedium;
import com.eatP2.pages.customer.ContactMediumPage;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContactMediumUpdateTests extends BaseTest { //TS9-Customer Info Contact Update


    @Test
    public void testContactMediumUpdateScreenShouldOpen() {

        // Test Case ID: TC 01

        // Expected Result: The Contact Medium Update screen should be displayed in correct form
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();


        if (contactMediumPage.validateAllFields()){

            ContactMedium actualContactMedium= contactMediumPage.getContactMediumFromDatabase();
            assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

            ContactMedium displayedContactMedium= new ContactMedium(contactMediumPage.getEmailValue(),contactMediumPage.getHomePhoneValue(), contactMediumPage.getMobilePhoneValue(),contactMediumPage.getFaxValue());
            assertTrue("Contact info is not same with database.",actualContactMedium.equals(displayedContactMedium));

            ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
            assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
            //The "Contact Medium Update" screen should open, and the fields should be pre-filled with the customer's current information (email, home phone, mobile phone, fax).
            assertTrue("The customer's contact information was updated when it shouldn't have been.",updatedContactMedium.equals(actualContactMedium));


        }
        else{
            fail("Some fields are not found.");
        }

    }


    @Test
    public void testMandatoryAndOptionalFieldsValidation() {
        // Test Case ID: TC 02
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        if (contactMediumPage.validateAllFields()){
            //Expected Result: All fields should be populated with the existing data, and the user should be able to modify these values to update the information.
            try {
                contactMediumPage.enterEmail("test@example.com");
                contactMediumPage.enterMobilephone("1234567890");
                contactMediumPage.enterHomephone("9087654321");
                contactMediumPage.enterFax("9012345678");
            }
            catch (Exception e){
                fail("Text areas are not editable.");
            }

        }
        else{
            fail("Some fields are not valid.");
        }

    }
    @Test
    public void testCancellingTheUpdate() {
        // Expected Result: The confirmation dialog should be displayed after clicking the cancel button.
        // Test Case ID: TC 03
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        contactMediumPage.enterEmail("test@example.com");
        contactMediumPage.enterMobilephone("1234567890");
        contactMediumPage.enterHomephone("9087654321");
        contactMediumPage.enterFax("9012345678");

        contactMediumPage.clickCancelBtn();


        //Expected Result: The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",updatedContactMedium.equals(actualContactMedium));
    }



    @Test
    public void testSuccessfulUpdateAndSave() {
        // Expected Result: A success message should be displayed after saving the updated contact medium details.
        // Test Case ID: TC 04
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        contactMediumPage.enterEmail("valid@example.com");
        contactMediumPage.enterMobilephone("1234567890");
        contactMediumPage.enterHomephone("9087654321");
        contactMediumPage.enterFax("9012345678");

        contactMediumPage.clickSaveButton();
        //Expected Result: The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertFalse("The customer's contact information is not updated in database.",actualContactMedium.equals(updatedContactMedium));

        //The "Save" button should be enabled, and when clicked, a message saying "Contact medium information saved successfully." should be displayed.
        assertTrue("Success message not displayed.",contactMediumPage.isSuccessMessageDisplayed());
        assertTrue("Success message is incorrect.", contactMediumPage.verifySuccessMessage());

    }



    @Test
    public void testMandatoryFieldLeftBlankEmail() {
        // Expected Result: An error message should be displayed indicating that the email field cannot be left blank.
        // Test Case ID: TC 05

        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        contactMediumPage.enterEmail("");
        contactMediumPage.enterMobilephone("1234567890");
        contactMediumPage.enterHomephone("9087654321");
        contactMediumPage.enterFax("9012345678");

        contactMediumPage.clickSaveButton();

        assertTrue("Error message is not displayed.",contactMediumPage.isErrorMessageDisplayed());
        //Expected Result: The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("Error message is incorrect.", contactMediumPage.isEmailNullErrorDisplayed());


        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",actualContactMedium.equals(updatedContactMedium));

    }

    @Test
    public void testMandatoryFieldLeftBlankMobilePhone() {
        // Expected Result: An error message should be displayed indicating that the mobile phone field cannot be left blank.
        // Test Case ID: TC 06
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        contactMediumPage.enterEmail("test@example.com");
        contactMediumPage.enterMobilephone("");
        contactMediumPage.enterHomephone("9087654321");
        contactMediumPage.enterFax("9012345678");

        contactMediumPage.clickSaveButton();

        assertTrue("Error message is not displayed.",contactMediumPage.isErrorMessageDisplayed());
        //Expected Result: The "Save" button should be disabled.A warning message saying "Mobile phone cannot be empty." should appear below the mobile phone field.The mobile phone text field should be highlighted with a red border.
        assertTrue("Error message is incorrect.", contactMediumPage.isPhoneNullErrorDisplayed());



        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",actualContactMedium.equals(updatedContactMedium));

    }

    @Test
    public void testMandatoryFieldsLeftBlankEmailAndMobilePhone() {
        // Expected Result: Error messages should be displayed indicating that both email and mobile phone fields cannot be left blank.
        // Test Case ID: TC 07
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        contactMediumPage.enterEmail("");
        contactMediumPage.enterMobilephone("");
        contactMediumPage.enterHomephone("9087654321");
        contactMediumPage.enterFax("9012345678");

        //Expected Result: The "Save" button should be disabled. The mobile phone and email text fields should be highlighted with a red border. Below the email field, the message "Email can not be empty." should be displayed, and below the mobile phone field, the message "Mobile phone can not be empty." should be shown.

        assertTrue("Save button should not be active.",contactMediumPage.checkDisabledSaveButton());


        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",actualContactMedium.equals(updatedContactMedium));

    }

    @Test
    public void testValidationOfEmailFormat() {
        // Expected Result: An error message should be displayed indicating that the email format is invalid.
        // Test Case ID: TC 08
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        contactMediumPage.enterEmail("testexample.com");
        contactMediumPage.enterMobilephone("1234567890");
        contactMediumPage.enterHomephone("9087654321");
        contactMediumPage.enterFax("9012345678");

        contactMediumPage.clickSaveButton();


        //Expected Result: The "Save" button should be disabled, and the message "Email must be a valid email address." should be displayed below the email field. The email text field should be highlighted with a red border.
        assertTrue("Error message is incorrect.", contactMediumPage.isEmailInvalidErrorDisplayed());


        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",actualContactMedium.equals(updatedContactMedium));

    }

    @Test
    public void testNonNumericValueInMobilePhone() {
        // Expected Result: An error message should be displayed indicating that the mobile phone field must contain only numeric values.
        // Test Case ID: TC 09
        checkNonNumericField("Mobile phone");

    }


    @Test
    public void testLongValueInMobilePhone() {
        // Expected Result: An error message should be displayed indicating that the mobile phone number exceeds the allowed length.
        // Test Case ID: TC 10
        checkLongValue("Mobile phone");
    }

    @Test
    public void testShortValueInMobilePhone() {
        // Expected Result: An error message should be displayed indicating that the mobile phone number is too short.
        // Test Case ID: TC 11
        checkShortValue("Mobile phone");
    }

    @Test
    public void testNonNumericValueInHomePhone() {
        // Expected Result: An error message should be displayed indicating that the home phone field must contain only numeric values.
        // Test Case ID: TC 12
        checkNonNumericField("Home phone");
    }

    @Test
    public void testLongValueInHomePhone() {
        // Expected Result: An error message should be displayed indicating that the home phone number exceeds the allowed length.
        // Test Case ID: TC 13
        checkLongValue("Home phone");
    }

    @Test
    public void testShortValueInHomePhone() {
        // Expected Result: An error message should be displayed indicating that the home phone number is too short.
        // Test Case ID: TC 14
        checkShortValue("Home phone");
    }

    @Test
    public void testNonNumericValueInFaxField() {
        // Expected Result: An error message should be displayed indicating that the fax field must contain only numeric values.
        // Test Case ID: TC 15
        checkNonNumericField("Fax");
    }

    @Test
    public void testLongValueInFaxField() {
        // Expected Result: An error message should be displayed indicating that the fax number exceeds the allowed length.
        // Test Case ID: TC 16
        checkLongValue("Fax");
    }

    @Test
    public void testShortValueInFaxField() {
        // Expected Result: An error message should be displayed indicating that the fax number is too short.
        // Test Case ID: TC 17
        checkShortValue("Fax");
    }
    public void checkNonNumericField(String fieldName) {
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        String testInput="9.qwe93+abcde";
        String expectedOutput="993";
        String actualOutput="";
        switch (fieldName) {
            case "Mobile phone":
                contactMediumPage.enterMobilephone(testInput);
                actualOutput=contactMediumPage.getMobilePhoneValue();
                break;
            case "Home phone":
                contactMediumPage.enterHomephone(testInput);
                actualOutput=contactMediumPage.getHomePhoneValue();
                break;
            case "Fax":
                contactMediumPage.enterFax(testInput);
                actualOutput=contactMediumPage.getFaxValue();
                break;
            default:
                fail("Field does not exists:" + fieldName);
        }


        //Expected Result: Only the value "993" should be displayed in the field.
        assertTrue("Non numeric values displayed in "+fieldName+" field.",actualOutput.equals(expectedOutput));
        //Expected Result: The "Save" button should be disabled,
        assertTrue("Save button should not be active.",contactMediumPage.checkDisabledSaveButton());

        assertTrue("Error message is incorrect.", contactMediumPage.verifyInvalidFieldErrorDisplayed(fieldName));


        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",actualContactMedium.equals(updatedContactMedium));

    }
    private void checkLongValue(String fieldName){
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        String testInput="0123456789012345";
        String expectedOutput="012345678901";
        String actualOutput="";
        switch (fieldName) {
            case "Mobile phone":
                contactMediumPage.enterMobilephone(testInput);
                actualOutput=contactMediumPage.getMobilePhoneValue();
                break;
            case "Home phone":
                contactMediumPage.enterHomephone(testInput);
                actualOutput=contactMediumPage.getHomePhoneValue();
                break;
            case "Fax":
                contactMediumPage.enterFax(testInput);
                actualOutput=contactMediumPage.getFaxValue();
                break;
            default:
                fail("Field does not exists:" + fieldName);
        }


        //Expected Result: The mobile phone field should display the value "012345678901".
        assertTrue("Values are not same as expected for "+fieldName,actualOutput.equals(expectedOutput));
        //Expected Result: The "Save" button should be enabled, and the values should be saved as they are displayed on the screen.
        contactMediumPage.clickSaveButton();

        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertFalse("The customer's contact information not updated in database despite it should have been.",actualOutput.equals(updatedContactMedium));


    }

    private void checkShortValue(String fieldName){
        ContactMediumPage contactMediumPage= new ContactMediumPage(driver);
        contactMediumPage.navigateToContactMediumUpdate();

        ContactMedium actualContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Customer contact medium does not exist.",actualContactMedium!=null);

        String testInput="0123456789012345";
        String expectedOutput="012345678901";
        String actualOutput="";
        switch (fieldName) {
            case "Mobile phone":
                contactMediumPage.enterMobilephone(testInput);
                actualOutput=contactMediumPage.getMobilePhoneValue();
                break;
            case "Home phone":
                contactMediumPage.enterHomephone(testInput);
                actualOutput=contactMediumPage.getHomePhoneValue();
                break;
            case "Fax":
                contactMediumPage.enterFax(testInput);
                actualOutput=contactMediumPage.getFaxValue();
                break;
            default:
                fail("Field does not exists:" + fieldName);
        }


        assertTrue("Values not displayed correctly for "+fieldName,actualOutput.equals(expectedOutput));
        //Expected Result: The "Save" button should be disabled, and the message "Mobile phone must be valid." should be displayed below the mobile phone field. The mobile phone text field should be highlighted with a red border.

        assertTrue("Save button should not be active.",contactMediumPage.checkDisabledSaveButton());

        assertTrue("Error message is incorrect.", contactMediumPage.verifyInvalidFieldErrorDisplayed(fieldName));



        ContactMedium updatedContactMedium=contactMediumPage.getContactMediumFromDatabase();
        assertTrue("Updated customer contact medium does not exist.",updatedContactMedium!=null);
        //The changes should not be saved, and the "Contact Medium" page should be displayed with the original (unchanged) information.
        assertTrue("The customer's contact information was updated when it shouldn't have been.",actualContactMedium.equals(updatedContactMedium));



    }
}
