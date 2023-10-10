package com.akk.growbiz.userapp.service;

import com.akk.growbiz.userapp.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    List<User> createListUser(List<User> user);

    User getUser(String userName);
    Long deleteUser(String userName);

    User updateUser(String userName, User user);
}
