package com.eatP2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
    static Connection usersDBconnection;

    public void connectToDatabase(String url,String user,String password){
        try {
            // Set up database connection
            usersDBconnection = DriverManager.getConnection(
                    url,
                    user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void connectToUsersDatabase() {
        connectToDatabase("jdbc:postgresql://localhost:5433/identitydb", "postgres", "test"
        );
    }
}
