package com.eatP2.DAO;

import com.eatP2.models.Campaign;
import com.eatP2.models.Offer;

import java.sql.*;

public class NewSaleDAO extends BaseDAO {

    public  Campaign getCampaignInstanceFromDatabase() {
        connectToDatabase("url","username","password");
        String campaignID=null;
        String name=null;
        String startDate=null;
        String endDate=null;
        String status=null;

        try {
            String query = "SELECT * FROM Campaigns ORDER BY RANDOM() LIMIT 1;";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    campaignID = resultSet.getString("ID");
                    name = resultSet.getString("name");
                    startDate = resultSet.getString("start_date");
                    endDate = resultSet.getString("end_date");
                    status = resultSet.getString("status");
                } else {
                    throw new SQLException("No campaign found in database.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (campaignID != null || startDate != null || endDate != null || name != null || status != null ) {
            return new Campaign( campaignID, name,startDate, endDate,status);
        } else {
            return null;
        }
    }
    public Offer getOfferInstanceFromDatabase() {
        connectToDatabase("url","username","password");
        String offerID=null;
        String name=null;
        String startDate=null;
        String endDate=null;
        try {
            String query = "SELECT * FROM Offers ORDER BY RANDOM() LIMIT 1;";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    offerID = resultSet.getString("ID");
                    name = resultSet.getString("name");
                    startDate = resultSet.getString("start_date");
                    endDate = resultSet.getString("end_date");
                } else {
                    throw new SQLException("No offer found in database.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (offerID != null || startDate != null || endDate != null || name != null ) {
            return new Offer( offerID, name,startDate, endDate);
        } else {
            return null;
        }
    }
}
