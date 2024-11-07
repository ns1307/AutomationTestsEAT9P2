package com.eatP2.DAO;

import com.eatP2.models.ContactMedium;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContactMediumDAO extends BaseDAO {

    public ContactMedium getContactMediumFromDatabase(String customerId) {
        connectToDatabase("url","username","password");
        String email = null;
        String homePhone = null;
        String mobilePhone = null;
        String fax = null;
        try {
            String query = "SELECT email, homephone, mobilephone, fax FROM ContactMedium WHERE customer_id = ?";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            preparedStatement.setString(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getDate("deleted_date") == null) {
                        email = resultSet.getString("email");
                        homePhone = resultSet.getString("homephone");
                        mobilePhone = resultSet.getString("mobilephone");
                        fax = resultSet.getString("fax");
                    }
                } else {
                    throw new SQLException("Customer with id " + customerId + " was not found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (email != null || homePhone != null || mobilePhone != null || fax != null) {
            return new ContactMedium(email, homePhone, mobilePhone, fax);
        } else {
            return null;
        }
    }
}
