package com.akk.growbiz.userapp.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {

    @Id
    private String id;

    private String userName;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private List<AddressEntity> address;

    private Integer userStatus;
}
