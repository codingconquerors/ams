package com.akk.growbiz.userapp.service.impl;

import com.akk.growbiz.userapp.entity.UserEntity;
import com.akk.growbiz.userapp.mapper.UserMapper;
import com.akk.growbiz.userapp.model.User;
import com.akk.growbiz.userapp.repository.UserMongoTemplateRepository;
import com.akk.growbiz.userapp.repository.UserRepo;
import com.akk.growbiz.userapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;
    private UserMongoTemplateRepository userMongoTemplateRepository;

    @Autowired
    UserMapper userMapper;

    public UserServiceImpl(UserRepo userRepo, UserMongoTemplateRepository userMongoTemplateRepository) {
        this.userRepo = userRepo;
        this.userMongoTemplateRepository = userMongoTemplateRepository;
    }

    @Override
    public User createUser(User user) {

        UserEntity userEntity = userMapper.toEntity(user);

        return userMapper.toUser(userRepo.save(userEntity));

    }

    @Override
    public List<User> createListUser(List<User> user) {
        List<UserEntity> userEntity = userMapper.toEntityList(user);

        return userMapper.toUserList(userRepo.saveAll(userEntity));
    }

    @Override
    public User getUser(String userName) {

        return userMapper.toUser(userRepo.findUserByUserName(userName));

    }

    @Override
    public User updateUser(String userName, User user) {
        UserEntity userEntity = userMapper.toEntity(user);
        return userMongoTemplateRepository.updateUsingFindAndReplace(userName, userEntity);

    }

    @Override
    public Long deleteUser(String userName) {
        return userMongoTemplateRepository.deleteUserUsingRemove(userName);
    }
}
