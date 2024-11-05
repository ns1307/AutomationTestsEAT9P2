package com.eatP2.DAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class BaseDAO {
    static Connection connection;
    public void connectToDatabase(){
        try {
            // Set up database connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
