package com.akk.growbiz.userapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.akk.growbiz.userapp.repository")
public class UserappApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserappApplication.class, args);
    }

}
