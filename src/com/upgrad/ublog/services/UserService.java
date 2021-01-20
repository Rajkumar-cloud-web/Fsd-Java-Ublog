package com.upgrad.ublog.services;

import com.upgrad.ublog.dtos.User;

public interface UserService {
    public boolean login(User user) throws Exception;
    public boolean register(User user) throws Exception;
}
