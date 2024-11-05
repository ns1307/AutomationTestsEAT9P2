package com.eatP2.DAO;

import com.eatP2.models.Address;
import com.eatP2.models.BillingAccount;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.sql.*;


public class BillingAccountDAO extends BaseDAO {

    public BillingAccount getBillingAccountFromDatabase(String billingAccID) {
        connectToDatabase();
        String accId=null;
        String accName = null;
        String accStatus = null;
        String accType= null;
        String accDescription = null;
        Address address=null;
        try {
            String query = "SELECT account_name,account_status, account_type, account_description,address_id FROM BillingAccount WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, billingAccID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    accId=resultSet.getString("id");
                    accName = resultSet.getString("account_name");
                    accStatus = resultSet.getString("account_status");
                    accType = resultSet.getString("account_type");
                    accDescription = resultSet.getString("account_description");
                    String addressID = resultSet.getString("address_id");
                    AddressDAO addressDAO = new AddressDAO();
                    address= addressDAO.getAdresFromDatabase(addressID);
                } else {
                    throw new SQLException("Billing account with id " + billingAccID + " was not found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (accId!=null ||accName != null || accStatus != null || accType != null || accDescription != null || address!=null ) {
            return new BillingAccount(accId,accName, accStatus, accType, accDescription,address);
        } else {
            return null;
        }
    }

    public BillingAccount getBillingAccountInstanceOfUserFromDatabase(String customerID) {
        connectToDatabase();
        String accName = null;
        String accStatus = null;
        String accType= null;
        String accDescription = null;
        Address address=null;
        try {
            String query = "SELECT account_name,account_status, account_type, account_description,address_id FROM BillingAccount WHERE customer_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, customerID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    accName = resultSet.getString("account_name");
                    accStatus = resultSet.getString("account_status");
                    accType = resultSet.getString("account_type");
                    accDescription = resultSet.getString("account_description");
                    String addressID = resultSet.getString("address_id");
                    AddressDAO addressDAO = new AddressDAO();
                    address= addressDAO.getAdresFromDatabase(addressID);
                } else {
                    throw new SQLException("Billing account for customer " + customerID + " was not found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (accName != null || accStatus != null || accType != null || accDescription != null || address!=null ) {
            return new BillingAccount(accName, accStatus, accType, accDescription,address);
        } else {
            return null;
        }
    }

}
