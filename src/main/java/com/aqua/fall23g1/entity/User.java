package com.aqua.fall23g1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String firstName;
    private String midName;
    private String lastName;
    private String email;
    private Date dateOfBirth;
    private String address;
    private String city;
    private String postalCode;
    private String password;
    private int idRole;
}
