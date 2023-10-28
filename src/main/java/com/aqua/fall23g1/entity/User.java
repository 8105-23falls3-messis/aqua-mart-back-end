package com.aqua.fall23g1.entity;

import java.sql.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("User object")
public class User {
    @ApiModelProperty("id")
    private int id;
    @ApiModelProperty("firstName")
    private String firstName;
    @ApiModelProperty("midName")
    private String midName;
    @ApiModelProperty("lastName")
    private String lastName;
    @ApiModelProperty("email")
    private String email;
    @ApiModelProperty("date of birth")
    private Date dateOfBirth;
    @ApiModelProperty("address")
    private String address;
    @ApiModelProperty("city")
    private String city;
    @ApiModelProperty("province")
    private String province;
    @ApiModelProperty("country")
    private String country;
    @ApiModelProperty("postalCode")
    private String postalCode;
    @ApiModelProperty("password")
    private String password;
    @ApiModelProperty("role id, get it from other interface")
    private int idRole;
}
