package com.akk.growbiz.ams;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = "com.akk.growbiz.ams.repository")
public class AMSappApplication {

    public static void main(String[] args) {
        SpringApplication.run(AMSappApplication.class, args);
    }

}
