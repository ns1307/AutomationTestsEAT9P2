package com.eatP2.DAO;

import com.eatP2.models.IndividualCustomer;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerDAO extends BaseDAO{

    public boolean isCustomerDeletedFromDatabase(String customerId) {
        connectToDatabase("url","username","password");
        try {
            String query = "SELECT COUNT(*) FROM customers WHERE customer_id = ?";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            preparedStatement.setString(1, customerId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    if (resultSet.getDate("deleted_date") == null) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    throw new SQLException("Customer with id " + customerId + " was not found.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        return false;
    }

    public IndividualCustomer getCustomerInstanceFromDatabase() {
        connectToDatabase("url","username","password");
        String id = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String gender = null;
        String birthDate = null;
        String fatherName = null;
        String motherName = null;
        String nationalityID = null;
        try {
            String query = "SELECT * FROM IndividualCustomers ORDER BY RANDOM() LIMIT 1;";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getString("ID");
                    firstName = resultSet.getString("first_name");
                    middleName = resultSet.getString("middle_name");
                    lastName = resultSet.getString("last_name");
                    gender = resultSet.getString("gender");
                    birthDate = resultSet.getString("birth_date");
                    fatherName = resultSet.getString("father_name");
                    motherName = resultSet.getString("mother_name");
                    nationalityID = resultSet.getString("nationality_id");
                } else {
                    throw new SQLException("No offer found in database.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (id != null || firstName != null || middleName != null || lastName != null || gender != null  || birthDate!=null || fatherName != null || motherName != null || nationalityID != null ) {
            return new IndividualCustomer(id,firstName,middleName,lastName,gender,birthDate,fatherName,motherName,nationalityID);
        } else {
            return null;
        }
    }
    public IndividualCustomer getOneCustomerWithoutBillingAccount() {
        connectToDatabase("url","username","password");
        String id = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String gender = null;
        String birthDate = null;
        String fatherName = null;
        String motherName = null;
        String nationalityID = null;
        try {
            String query = "SELECT TOP 1 FROM Customer c LEFT JOIN BillingAccount b ON c.customerID = b.customerID WHERE b.customerID IS NULL;";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getString("ID");
                    firstName = resultSet.getString("first_name");
                    middleName = resultSet.getString("middle_name");
                    lastName = resultSet.getString("last_name");
                    gender = resultSet.getString("gender");
                    birthDate = resultSet.getString("birth_date");
                    fatherName = resultSet.getString("father_name");
                    motherName = resultSet.getString("mother_name");
                    nationalityID = resultSet.getString("nationality_id");
                } else {
                    throw new SQLException("No offer found in database.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (id != null || firstName != null || middleName != null || lastName != null || gender != null  || birthDate!=null || fatherName != null || motherName != null || nationalityID != null ) {
            return new IndividualCustomer(id,firstName,middleName,lastName,gender,birthDate,fatherName,motherName,nationalityID);
        } else {
            return null;
        }
    }



    public IndividualCustomer getOneCustomerWithBillingAccount() {
        connectToDatabase("url","username","password");
        String id = null;
        String firstName = null;
        String middleName = null;
        String lastName = null;
        String gender = null;
        String birthDate = null;
        String fatherName = null;
        String motherName = null;
        String nationalityID = null;
        try {
            String query = "SELECT c.* FROM Customer c JOIN BillingAccount b ON c.ID = b.customerID LIMIT 1;";
            PreparedStatement preparedStatement = usersDBconnection.prepareStatement(query);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    id = resultSet.getString("ID");
                    firstName = resultSet.getString("first_name");
                    middleName = resultSet.getString("middle_name");
                    lastName = resultSet.getString("last_name");
                    gender = resultSet.getString("gender");
                    birthDate = resultSet.getString("birth_date");
                    fatherName = resultSet.getString("father_name");
                    motherName = resultSet.getString("mother_name");
                    nationalityID = resultSet.getString("nationality_id");
                } else {
                    throw new SQLException("No offer found in database.");
                }
            }
        } catch (Exception e) {
            e.getMessage();
        }
        if (id != null || firstName != null || middleName != null || lastName != null || gender != null  || birthDate!=null || fatherName != null || motherName != null || nationalityID != null ) {
            return new IndividualCustomer(id,firstName,middleName,lastName,gender,birthDate,fatherName,motherName,nationalityID);
        } else {
            return null;
        }
    }


}
