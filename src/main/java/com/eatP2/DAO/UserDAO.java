package com.eatP2.DAO;

import com.eatP2.models.Address;
import com.eatP2.models.BillingAccount;
import com.eatP2.models.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO extends BaseDAO{

    public User getUserFromDatabase(){
        connectToUsersDatabase();
        String id=null;
        String name = null;
        String lastName = null;
        String email = null;
        String password = null;
        String identityNo = null;

        try {
            String query = "SELECT * FROM users LIMIT 1;";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    email = resultSet.getString("email");
                    password = resultSet.getString("password");
                    id=resultSet.getString("id");
                    identityNo = resultSet.getString("identityno");
                    name = resultSet.getString("name");
                } else {
                    throw new SQLException("No user found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (id!=null || email!=null || password != null|| name != null || identityNo != null || name != null ) {
            return new User(id,name,identityNo,email,password);
        } else {
            return null;
        }
    }
}
