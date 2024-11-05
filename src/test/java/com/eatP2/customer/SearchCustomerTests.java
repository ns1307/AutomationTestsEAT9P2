package com.eatP2.customer;

import com.eatP2.BaseTest;
import com.eatP2.pages.customer.CreateCustomerPage;
import com.eatP2.pages.searchcustomer.SearchCustomerPage;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;




public class SearchCustomerTests extends BaseTest {
    SearchCustomerPage searchCustomerPage;
    @Before
    public void setUp(){
        super.setUp();
        this.searchCustomerPage= new SearchCustomerPage(driver);
        searchCustomerPage.login();
    }

    @Test
    public void testFiltersHeaderIsVisible() {

        softAssertions.assertThat(searchCustomerPage.isFiltersHeaderDisplayed())
                .isTrue();

    }

    @Test
    public void testSearchByIdNumber() {

        //Enter Id Number
        searchCustomerPage.setIdNumber("12345678901");
        //Wait and scroll

        searchCustomerPage.clickSearchButton();

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Result table should be displayed")
                .isTrue();
    }

    @Test
    public void testSearchByCustomerId() {

        searchCustomerPage.setCustomerId("a2183bed-230e-4104-98bc-a8a76484b550");

        searchCustomerPage.clickSearchButton();

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Result table should be displayed")
                .isTrue();

    }

    @Test
    public void testSearchByFirstName(){

        searchCustomerPage.setFirstName("First");

        searchCustomerPage.clickSearchButton();
        String actualResult=searchCustomerPage.getNameFieldMessage();

        softAssertions.assertThat(actualResult)
                .as("Expected message should indicate that both names must be filled together.")
                .contains("Both First Name and Last Name must be filled together.");

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Results table should not be displayed when only First Name is filled.")
                .isFalse();
    }

    @Test
    public void testSearchByLastName(){

        searchCustomerPage.setLastName("Last");

        searchCustomerPage.clickSearchButton();
        String actualResult=searchCustomerPage.getNameFieldMessage();

        softAssertions.assertThat(actualResult)
                .as("Expected message should indicate that both names must be filled together.")
                .contains("Both First Name and Last Name must be filled together.");

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Results table should not be displayed when only Last Name is filled.")
                .isFalse();
    }

    @Test
    public void testClearButton(){

        searchCustomerPage.setCustomerId("a2183bed-230e-4104-98bc-a8a76484b550");


        searchCustomerPage.clickClearButton();

        Assert.assertEquals("", searchCustomerPage.getIdNumber());
        Assert.assertEquals("", searchCustomerPage.getCustomerId());
        Assert.assertEquals("", searchCustomerPage.getAccountNumber());
        Assert.assertEquals("", searchCustomerPage.getGsmNumber());
        Assert.assertEquals("", searchCustomerPage.getFirstName());
        Assert.assertEquals("", searchCustomerPage.getLastName());

    }

    @Test
    public void testSearchByGsmNumber(){

        searchCustomerPage.setGsmNumber("5551234567");

        searchCustomerPage.clickSearchButton();

        softAssertions.assertThat(searchCustomerPage.isResultsTableDisplayed())
                .as("Result table should be displayed")
                .isTrue();

    }

    @Test
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

        searchCustomerPage.setFirstName("Test"); // İlk ad
        searchCustomerPage.setLastName("User");  // Soyad

        searchCustomerPage.clickSearchButton();

        // Create Customer butonuna tıklayın
        CreateCustomerPage createCustomerPage = searchCustomerPage.clickCreateCustomerButton();

        // Müşteri oluşturma sayfasının yüklendiğini kontrol et
        softAssertions.assertThat(createCustomerPage)
                .as("Should navigate to Create Customer page")
                .isNotNull();

        // Sayfanın başlığını kontrol et ya da başka bir özellik
        String expectedTitle = "Create Customer"; // Beklenen sayfa başlığını burada güncelleyin
        softAssertions.assertThat(createCustomerPage.getCreateCustomerTitle()) // getPageTitle() metodunu CreateCustomerPage'de oluşturduğunuzdan emin olun
                .as("Page title should be 'Create Customer'")
                .isEqualTo(expectedTitle);

        // Soft assertions'ı kontrol et
        softAssertions.assertAll();
    }


}




