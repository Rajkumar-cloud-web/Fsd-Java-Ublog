package com.upgrad.ublog.services;

import com.upgrad.ublog.dao.DAOFactory;
import com.upgrad.ublog.dao.UserDAO;
import com.upgrad.ublog.dtos.User;
import com.upgrad.ublog.exceptions.IncorrectPasswordException;
import com.upgrad.ublog.exceptions.UserAlreadyRegisteredException;
import com.upgrad.ublog.exceptions.UserNotFoundException;

import java.sql.SQLException;

/**
 * TODO: 3.10. Implement the UserService interface and implement this class using the Singleton pattern.
 *  (Hint: Should have a private no-arg Constructor, a private static instance attribute of type
 *  UserServiceImpl and a public static getInstance() method which returns the instance attribute.)
 * TODO: 3.11. Provide an attribute of type UserDAO and instantiate it using the DAOFactory class.
 *  Note: You should not have any reference to UserDAOImpl in this class.
 * TODO: 3.12. The login() method should take a User object as an input parameter and return true if the
 *  user was successfully logged in, otherwise return false. This method will make use of findByEmailId()
 *  method of the UserDao interface. If the emailId, which was passed via User argument, was not found in
 *  the database, then throw UserNotFoundException a with message saying "No user registered with the given
 *  email address!". If the emailId was present in the database, but the password is incorrect, then throw
 *  IncorrectPasswordException with a message saying "Password is not correct.". If the email id is present
 *  in the database and password is correct, return true.
 *  Note: The exception passed by DAO layer should not be passed to the presentation layer. Print the stack
 *  trace corresponding to the exception passed by DAO layer and throw a new exception of type Exception
 *  with a message "Some unexpected error occurred!"
 * TODO: 3.13. The register() method should take a User object as an input parameter and return true if the
 *  user was successfully registered, otherwise return false. This method will make use of findByEmailId()
 *  method of the UserDao interface. If the emailId, which was passed via User argument, was found in
 *  the database, then throw UserAlreadyRegisteredException with message saying "A user with this email
 *  address already exists!". If the email id is not present in the database, create a new user in the database
 *  using the create() method of the UserDao interface and return true.
 *  Note: The exception passed by DAO layer should not be passed to the presentation layer. Print the stack
 *  trace corresponding to the exception passed by DAO layer and throw a new exception of type Exception
 *  with a message "Some unexpected error occurred!"
 */

public class UserServiceImpl implements UserService {
    private static UserServiceImpl instance = new UserServiceImpl();

    private DAOFactory daoFactory;
    private UserDAO userDAO;

    private UserServiceImpl() {
        daoFactory = new DAOFactory();
        userDAO = daoFactory.getUserDAO();

    }

    public boolean login (User user) throws Exception {
        if (user == null) {
            throw new NullPointerException("User object was null");
        }

        User temp = null;
        try {
            temp = userDAO.findByEmailId(user.getEmailId());
        } catch (SQLException e) {
            throw new Exception("Some unexpected exception occurred.");
        }

        if (temp == null) {
            throw new UserNotFoundException("User no doesn't exist.");
        } else if (!temp.getPassword().equals(user.getPassword())) {
            throw new IncorrectPasswordException("Password is not correct.");
        } else {
            return true;
        }
    }

    public boolean register (User user) throws Exception {
        if (user == null) {
            throw new NullPointerException("User object was null");
        }
        User temp = null;
        try {
            temp = userDAO.findByEmailId(user.getEmailId());
        } catch (SQLException e) {
            throw new Exception ("Some unexpected exception occurred");
        }

        if (temp != null) {
            throw new UserAlreadyRegisteredException("User no already registered.");
        } else {
            userDAO.create(user);
            return true;
        }
    }

    public static UserServiceImpl getInstance() {
        if (instance == null) {
            instance = new UserServiceImpl();
        }
        return instance;
    }
}
