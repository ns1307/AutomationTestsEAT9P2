package com.eatP2.customer;

import com.eatP2.BaseTest;
import com.eatP2.pages.BasePage;
import com.eatP2.pages.customer.CreateCustomerPage;
import com.eatP2.pages.searchcustomer.SearchCustomerPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SearchCustomerTests extends BaseTest {// TS-02 - Customer Search and Display
    SearchCustomerPage searchCustomerPage;

    @Before
    public void setUp() {
        super.setUp();
        this.searchCustomerPage = new SearchCustomerPage(driver);
        searchCustomerPage.login();
    }

    @After
    public void tearDown() {
        searchCustomerPage.delay(0.5);
        super.tearDown();
    }
/*
    @Test //TC-01
    public void testFiltersHeaderIsVisible() {

        softAssertions.assertThat(searchCustomerPage.isFiltersHeaderDisplayed())
                .isTrue();

    }

    @Test //TC-02
    public void testSearchByIdNumber() {

        searchCustomerPage.setIdNumber("12345678901");

        searchCustomerPage.clickSearchButton();

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Result table should be displayed")
                .isTrue();
    }
*/
    @Test //TC-03
    public void testSearchByCustomerId() {
        searchCustomerPage.setCustomerId("a2183bed-230e-4104-98bc-a8a76484b550");

        searchCustomerPage.clickSearchButton();

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Result table should be displayed")
                .isTrue();

    }

    @Test //TC-04
    public void testSearchByFirstName() {

        searchCustomerPage.setFirstName("First");

        searchCustomerPage.clickSearchButton();
        String actualResult = searchCustomerPage.getNameFieldMessage();

        softAssertions.assertThat(actualResult)
            .as("Expected message should indicate that both names must be filled together.")
            .contains("Both First Name and Last Name must be filled together.");

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
            .as("Results table should not be displayed when only First Name is filled.")
            .isFalse();

    }
/*
    @Test //TC-05
    public void testSearchByLastName() {

        searchCustomerPage.setLastName("Last");

        searchCustomerPage.clickSearchButton();
        String actualResult = searchCustomerPage.getNameFieldMessage();

        softAssertions.assertThat(actualResult)
                .as("Expected message should indicate that both names must be filled together.")
                .contains("Both First Name and Last Name must be filled together.");

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Results table should not be displayed when only Last Name is filled.")
                .isFalse();
    }
*/
    @Test //TC-06
    public void testClearButton() {

        searchCustomerPage.setCustomerId("a2183bed-230e-4104-98bc-a8a76484b550");

        searchCustomerPage.clickClearButton();

        softAssertions.assertThat(searchCustomerPage.getIdNumber()).isEqualTo("");
        softAssertions.assertThat(searchCustomerPage.getFirstName()).isEqualTo("");
        softAssertions.assertThat(searchCustomerPage.getLastName()).isEqualTo("");
        softAssertions.assertThat(searchCustomerPage.getCustomerId()).isEqualTo("");
        softAssertions.assertThat(searchCustomerPage.getAccountNumber()).isEqualTo("");
        softAssertions.assertThat(searchCustomerPage.getGsmNumber()).isEqualTo("");

    }
    /*
        @Test //TC-07
        public void testSearchByGsmNumber() {

            searchCustomerPage.setGsmNumber("5551234567");

            searchCustomerPage.clickSearchButton();

            softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                    .as("Result table should be displayed")
                    .isTrue();

        }
    */
    @Test //TC-08
    public void testSearchWithEmptyFields() {

        searchCustomerPage.setIdNumber("");
        searchCustomerPage.setCustomerId("");
        searchCustomerPage.setAccountNumber("");
        searchCustomerPage.setGsmNumber("");
        searchCustomerPage.setFirstName("");
        searchCustomerPage.setLastName("");


        softAssertions.assertThat(searchCustomerPage.isSearchButtonEnabled())
                .as("Search button should be inactive when fields are empty.")
                .isFalse();
    }

    @Test
    public void testCreateCustomerButtonNavigatesToCreateCustomerPage() {

        searchCustomerPage.setFirstName("Test");
        searchCustomerPage.setLastName("User");

        searchCustomerPage.clickSearchButton();

        CreateCustomerPage createCustomerPage = searchCustomerPage.clickCreateCustomerButton();

        softAssertions.assertThat(createCustomerPage)
                .as("Should navigate to Create Customer page")
                .isNotNull();

        String expectedTitle = "Create Customer";
        softAssertions.assertThat(createCustomerPage.getCreateCustomerTitle())
                .as("Page title should be 'Create Customer'")
                .isEqualTo(expectedTitle);

    }


}




