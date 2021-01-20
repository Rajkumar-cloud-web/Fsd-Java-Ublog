package com.upgrad.ublog.dao;

import com.upgrad.ublog.dtos.User;

import java.sql.SQLException;

public interface UserDAO {
    public User create(User user) throws SQLException;
    public User findByEmailId(String emailId) throws SQLException;
}
