package com.akk.growbiz.userapp.repository;


import com.akk.growbiz.userapp.entity.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface UserRepo extends MongoRepository<UserEntity, String> {

    @Query("{userName:'?0'}")
    UserEntity findUserByUserName(String userName);

    @Query(value = "{category:'?0'}", fields = "{'name' : 1, 'quantity' : 1}")
    List<UserEntity> findAll(String category);

    long count();
}
