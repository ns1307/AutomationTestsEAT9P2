package com.eatP2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
    static Connection connection;
    public void connectToDatabase(){
        try {
            // Set up database connection
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/identitydb", "postgres", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void connectToUsersDatabase(){
        try {
            // Set up database connection
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/identitydb", "postgres", "test");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
