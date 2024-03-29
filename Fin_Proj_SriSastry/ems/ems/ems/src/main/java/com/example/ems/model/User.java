package com.example.ems.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "user")
public class User {
    @Id
    private String id;
    private String fullName;
    private String email;
    private String phoneNumber;
    private String userType;

}
