package com.eatP2.newsale;
import com.eatP2.BaseTest;
import com.eatP2.DAO.NewSaleDAO;
import com.eatP2.pages.newsale.NewSalePage;

import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.*;

public class ProductConfigurationTests extends BaseTest { // TS15-Configuration of Products in the Cart



    @Test
    public void testDisplayOfferInfo() {// TC01-Display of Offer Information
        NewSalePage newSalePage=new NewSalePage(driver);
        if(newSalePage.navigateToNewSalePage("TS17-TC01")){
            NewSaleDAO newSaleDAO=new NewSaleDAO();
            newSalePage.addOfferToBasket(newSaleDAO.getOfferInstanceFromDatabase());
            newSalePage.clickNextButton();//Directs to Prod Config page

            assertTrue("Prod Offer ID is incorrect.",newSalePage.verifyOfferID());
            assertTrue("Prod Offer Name field is incoorect.",newSalePage.verifyOfferName());

        }
        else{
            fail("New billing account can not be created for test.");
        }
    }



    @Test
    public void testDisplayCampaignInfo() {// TC02-Display of Campaign Information
        NewSalePage newSalePage=new NewSalePage(driver);
        if(newSalePage.navigateToNewSalePage("TS17-TC02")){
            NewSaleDAO newSaleDAO=new NewSaleDAO();
            newSalePage.addCampaignToBasket(newSaleDAO.getCampaignInstanceFromDatabase());
            newSalePage.clickNextButton();//Directs to Prod Config page

            assertTrue("Campaign ID is incorrect.",newSalePage.verifyCampaignID());
            assertTrue("Campaign Name is incoorect.",newSalePage.verifyCampaignName());

        }
        else{
            fail("New billing account can not be created for test.");
        }
    }


    @Test
    public void testAddressInfo() {// TC03-Address Information Display
        NewSalePage newSalePage=new NewSalePage(driver);
        if(newSalePage.navigateToNewSalePage("TS17-TC03")){
            NewSaleDAO newSaleDAO=new NewSaleDAO();
            newSalePage.addCampaignToBasket(newSaleDAO.getCampaignInstanceFromDatabase());
            newSalePage.clickNextButton();//Directs to Prod Config page
            assertTrue("Title is incorrect",driver.findElement(By.id("addressTitle")).getText().equals("Address Info"));
            assertTrue("City is incorrect.", newSalePage.verifyCity());
            assertTrue("District is incorrect.",newSalePage.verifyDistrict());
            assertTrue("Postal code is incorrect.",newSalePage.verifyPostalCode());
            assertTrue("Address description is incorrect.",newSalePage.verifyAddressDescription());

        }
        else{
            fail("New billing account can not be created for test.");
        }

    }
    @Test
    public void testNextButton() {// TC04-Functionality of the "Next" button on the "Product Configuration" page.
        NewSalePage newSalePage=new NewSalePage(driver);
        if(newSalePage.navigateToNewSalePage("TS17-TC03")){
            NewSaleDAO newSaleDAO=new NewSaleDAO();
            newSalePage.addCampaignToBasket(newSaleDAO.getCampaignInstanceFromDatabase());
            newSalePage.clickNextButton();//Directs to Prod Config page

            newSalePage.clickNextButton();//Testing "Next"  button in Prod Config page

            assertTrue("Next button text is incorrect.",newSalePage.checkNextButton());
            assertTrue("Not redirected to correct page.",driver.findElement(By.id("title")).getText().equals("Submit Order"));

        }
        else{
            fail("New billing account can not be created for test.");
        }


    }
    @Test
    public void testPreviousButton() {// TC05-Functionality of the "Previous" button on the "Product Configuration" page
        NewSalePage newSalePage=new NewSalePage(driver);
        if(newSalePage.navigateToNewSalePage("TS17-TC03")){
            NewSaleDAO newSaleDAO=new NewSaleDAO();
            newSalePage.addCampaignToBasket(newSaleDAO.getCampaignInstanceFromDatabase());
            newSalePage.clickNextButton();//Directs to Prod Config page

            newSalePage.clickPreviousButton();//Testing "Next"  button in Prod Config page

            assertTrue("Next button text is incorrect.",newSalePage.checkPreviousButton());
            assertTrue("Not redirected to correct page.",driver.findElement(By.id("title")).getText().equals("Submit Order"));

        }
        else{
            fail("New billing account can not be created for test.");
        }

    }





}
