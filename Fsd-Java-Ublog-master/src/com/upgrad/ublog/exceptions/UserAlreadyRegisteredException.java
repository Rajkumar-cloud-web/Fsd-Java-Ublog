package com.upgrad.ublog.exceptions;

/**
 * TODO: 2.5. Convert this class to a custom Exception class.
 * TODO: 2.6. Provide a constructor which accepts a custom message as input
 *  parameter and passes it to its base class.
 */

public class UserAlreadyRegisteredException extends Exception{

    public UserAlreadyRegisteredException (String message){
        super(message);
    }

    public static void main(String[] args) {
        try {
            throw new UserAlreadyRegisteredException("Custom Message");
        } catch (UserAlreadyRegisteredException e) {
            System.out.println(e.getMessage());
        }

        /**
         * Your output should look like this.
         * Custom Message
         */
    }
}