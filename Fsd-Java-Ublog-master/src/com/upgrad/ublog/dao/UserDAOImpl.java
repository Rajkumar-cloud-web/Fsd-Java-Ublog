package com.upgrad.ublog.dao;

import com.upgrad.ublog.db.Database;
import com.upgrad.ublog.dtos.User;
import com.upgrad.ublog.services.UserServiceImpl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * TODO: 3.5. Implement the UserDAO interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserDAOImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.6. findByEmailId() method should take email id as an input parameter and
 *  return the user details corresponding to the email id from the USER table defined
 *  in the database. (Hint: You should get the connection using the Database class)
 * TODO: 3.7. create() method should take user details as input and insert these details
 *  into the USER table. Return the same User object which was passed as an input
 *  argument. (Hint: You should get the connection using the Database class)
 */

public class UserDAOImpl implements UserDAO{

    private static UserDAOImpl instance = new UserDAOImpl();

    private UserDAOImpl(){

    }

    public static UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAOImpl();
        }
        return instance;
    }

    @Override
    public User create(User user) throws SQLException {

        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "INSERT INTO user (userId, emailId, password) VALUES (" +
                user.getUserId() + ", '" +
                user.getEmailId() + "', '" +
                user.getPassword()+
                "')";

        statement.executeUpdate(sql);

        return user;
    }

    @Override
    public User findByEmailId(String emailId) throws SQLException {

        Connection connection = Database.getConnection();

        Statement statement = connection.createStatement();

        String sql = "SELECT * FROM user WHERE emailId = '" + emailId + "'";

        ResultSet resultSet = statement.executeQuery(sql);

        if(resultSet.next()){
            User user = new User();
            user.setUserId(resultSet.getInt("userId"));
            user.setEmailId(resultSet.getString("emailId"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
        else
            return null;

    }


    public static void main(String[] args) {
        try {
            UserDAO userDAO = new UserDAOImpl();
            User temp = new User();
            temp.setUserId(1);
            temp.setEmailId("temp@temp.temp");
            temp.setPassword("temp");
            userDAO.create(temp);
            System.out.println(userDAO.findByEmailId("temp@temp.temp"));
        } catch (Exception e) {
            System.out.println("FAILED");
        }

    /**
     * Following should be the desired output of the main method.
     * User{userId=1, emailId='temp@temp.temp', password='temp'}
     */
   }
}