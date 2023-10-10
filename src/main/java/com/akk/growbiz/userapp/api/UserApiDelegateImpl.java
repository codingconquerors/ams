package com.akk.growbiz.userapp.api;


import com.akk.growbiz.userapp.controller.UserApiDelegate;
import com.akk.growbiz.userapp.model.User;
import com.akk.growbiz.userapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class UserApiDelegateImpl implements UserApiDelegate {

    private UserService userService;

    public UserApiDelegateImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public ResponseEntity<User> getUserByName(String username) {
        log.info("username", username);
        return ResponseEntity.ok(userService.getUser(username));
    }

    @Override
    public ResponseEntity<User> createUser(User user) {
        return ResponseEntity.ok(userService.createUser(user));
    }

    @Override
    public ResponseEntity<List<User>> createUsersWithListInput(List<User> users) {
        return ResponseEntity.ok(userService.createListUser(users));
    }

    @Override
    public ResponseEntity<User> updateUser(String userName, User user) {
        return ResponseEntity.ok(userService.updateUser(userName, user));
    }

    @Override
    public ResponseEntity<String> deleteUser(String username) {
        return ResponseEntity.ok(userService.deleteUser(username).toString());
    }
}
