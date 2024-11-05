package com.eatP2.pages.newsale;

import com.eatP2.Config;
import com.eatP2.DAO.BillingAccountDAO;
import com.eatP2.DAO.CustomerDAO;
import com.eatP2.DAO.NewSaleDAO;
import com.eatP2.models.BillingAccount;
import com.eatP2.models.Campaign;
import com.eatP2.models.IndividualCustomer;
import com.eatP2.models.Offer;
import com.eatP2.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class NewSalePage extends BasePage {
    By offerID =By.id("offer-id");
    By offerName =By.id("offer-name");
    By campaignID =By.id("campaign-id");
    By campaignName =By.id("campaign-name");

    By city =By.id("city");
    By district =By.id("state");
    By postalCode =By.id("postal-code");
    By addressDescription =By.id("address-description");


    By row =By.xpath("//tr");
    By newSaleBtn =By.id("new-sale");
    IndividualCustomer customer;
    Offer offer;
    Campaign campaign;
    BillingAccount billingAcc;
    public NewSalePage(WebDriver driver) {
        super(driver);
        CustomerDAO customerDAO = new CustomerDAO();
        this.customer= customerDAO.getCustomerInstanceFromDatabase();
        NewSaleDAO newSaleDAO = new NewSaleDAO();
        this.offer=newSaleDAO.getOfferInstanceFromDatabase();
        this.campaign=newSaleDAO.getCampaignInstanceFromDatabase();
    }

    public String getOfferIDValue() {
        return getValue(offerID);
    }
    public String getOfferNameValue() {
        return getValue(offerName);
    }


    private void navigateToAccountsPage(String customerID){
        driver.get(Config.CUST_INFO_PAGE_URL +customerID); // Replace with the actual URL
        WebElement customerAccountButton = find(By.id("customerAccount"));
        customerAccountButton.click();
    }
    public Boolean navigateToNewSalePage(String accName) {
        login();

        if(createNewBillingAccount(accName)){
            List<WebElement> rows = findAll(row);
            String billingAccountID =null;
            // Find row with name accName and click new sale
            if (rows.size() > 0) {
                for (WebElement row : rows) {
                    if (row.getText().contains(accName)) {
                        billingAccountID = row.findElement(By.id("acc-id")).getText();
                        row.findElement(newSaleBtn).click();
                        BillingAccountDAO billingAccountDAO = new BillingAccountDAO();
                        this.billingAcc=billingAccountDAO.getBillingAccountFromDatabase(billingAccountID);
                        return true;
                    }
                }
            }
        }
        return false;




    }
    private boolean createNewBillingAccount(String accName) {
        navigateToAccountsPage(customer.getId());
        String accDesc="TS17 Prod Config Tests ";
        BillingAccount billingAccount=new BillingAccount(accName,"Active","BillingAccount",accDesc);
        return createNewBillingAccount(billingAccount);

    }


    public void addOfferToBasket(Offer offer) {
        this.offer=offer;
        find(By.id("offer")).click();//Click Offer section
        find(By.id("offer-search")).sendKeys(offer.getId());//Fill offer search id section
        find(By.id("search-button")).click();//Click search button
        List<WebElement> offerElements = findAll(By.id("offer-id"));

        // Click where offer-id value is equal to offer id.
        for (WebElement offerElement : offerElements) {
            if (offerElement.getAttribute("offer-id").equals(offer.getId())) {
                offerElement.click();
                break;
            }
        }
        find(By.id("add")).click();//Click add button
    }




    public void addCampaignToBasket(Campaign campaign) {
        this.campaign=campaign;
        find(By.id("camapign")).click();//Click Offer section
        find(By.id("camapign-search")).sendKeys(campaign.getId());//Fill offer search id section
        find(By.id("search-button")).click();//Click search button
        List<WebElement> offerElements = findAll(By.id("campaign-id"));

        // Click where offer-id value is equal to offer id.
        for (WebElement offerElement : offerElements) {
            if (offerElement.getAttribute("campaign-id").equals(campaign.getId())) {
                offerElement.click();
                break;
            }
        }
        find(By.id("add")).click();//Click add button
    }

    public boolean createNewBillingAccount(BillingAccount billingAccount){
        WebElement accName= find(By.xpath("//input[@id='accountName']"));
        WebElement accStatus = find(By.xpath("//input[@id='accountStatus']"));
        WebElement accDescription = find(By.xpath("//input[@id='accountDescription']"));
        accName.clear();
        accName.sendKeys(billingAccount.getAccName());
        Select dropdown = new Select(accStatus);
        dropdown.selectByValue(billingAccount.getAccStatus());
        accDescription.clear();
        accDescription.sendKeys(billingAccount.getAccDescription());
        WebElement saveButton= find(By.xpath("//input[@id='saveButton']"));
        saveButton.click();
        WebElement successMessage= driver.findElement(By.xpath("//input[@id='successMessage']"));
        return successMessage.getText().equals("Billing account information is created!");

    }

    public Boolean verifyOfferID() {
        return getValue(offerID).equals(offer.getId());
    }
    public Boolean verifyOfferName() {
        return getValue(offerName).equals(offer.getName());
    }
    public Boolean verifyCampaignID() {
        return getValue(campaignID).equals(campaign.getId());
    }
    public Boolean verifyCampaignName() {
        return getValue(campaignName).equals(campaign.getName());
    }

    public Boolean verifyCity(){
        return getValue(city).equals(billingAcc.getAddress().getCity());
    }
    public Boolean verifyDistrict(){
        return getValue(district).equals(billingAcc.getAddress().getDistrict());
    }
    public Boolean verifyPostalCode(){
        return getValue(postalCode).equals(billingAcc.getAddress().getPostalCode());
    }
    public Boolean verifyAddressDescription(){
        return getValue(addressDescription).equals(billingAcc.getAddress().getDescription());
    }

}
