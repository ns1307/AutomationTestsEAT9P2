package com.eatP2.DAO;

import com.eatP2.models.Address;

import java.sql.*;

public class AddressDAO extends BaseDAO {

    public Address getAdresFromDatabase(String addressID) {
        connectToDatabase("url","username","password");
        String cityName=null;
        String districtName=null;
        String postalCode=null;
        String description=null;
        try {
            String query = "SELECT a.ID, c.name AS city_name, d.name AS district_name, a.postal_code, a.description "
                    + "FROM Addresses a "
                    + "JOIN District d ON a.district_id = d.ID "
                    + "JOIN City c ON d.city_id = c.ID "
                    + "WHERE a.ID = ?";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            preparedStatement.setString(1, addressID);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    cityName = resultSet.getString("city_name");
                    districtName = resultSet.getString("district_name");
                    postalCode = resultSet.getString("postal_code");
                    description = resultSet.getString("description");
                } else {
                    throw new SQLException("Address with id " + addressID + " was not found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (cityName != null || districtName != null || postalCode != null || description != null || addressID!=null ) {
            return new Address(cityName, districtName, postalCode, description);
        } else {
            return null;
        }
    }
}
