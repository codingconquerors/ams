package com.akk.growbiz.ams.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Client
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document
public class ClientEntity {

    private String clientFirstName;

    private String clientLastName;

    private String email;

    private String phone;

    private List<AddressEntity> address;

    public ClientEntity clientFirstName(String clientFirstName) {
        this.clientFirstName = clientFirstName;
        return this;
    }

}

