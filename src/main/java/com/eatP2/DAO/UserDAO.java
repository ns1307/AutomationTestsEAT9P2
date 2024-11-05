package com.eatP2.DAO;

import com.eatP2.models.Address;
import com.eatP2.models.BillingAccount;
import com.eatP2.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO{

    User getUserFromDatabase(){
        connectToDatabase();
        String id=null;
        String firstName = null;
        String lastName = null;
        String email = null;
        String password = null;

        try {
            String query = "SELECT * FROM users LIMIT 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id=resultSet.getString("id");
                    firstName = resultSet.getString("firstname");
                    lastName = resultSet.getString("lastname");
                    email = resultSet.getString("email");
                    password = resultSet.getString("password");
                } else {
                    throw new SQLException("No user found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (id!=null || firstName != null || lastName != null || email != null || password != null ) {
            return new User(id,firstName,lastName,email,password);
        } else {
            return null;
        }
    }
}
