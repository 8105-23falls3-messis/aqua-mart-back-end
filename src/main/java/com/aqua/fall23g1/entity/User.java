package com.aqua.fall23g1.entity;

import java.sql.Date;

//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description ="User object")
public class User {
	@Schema(description ="id")
    private int id;
	@Schema(description ="firstName")
    private String firstName;
	@Schema(description ="midName")
    private String midName;
    @Schema(description ="lastName")
    private String lastName;
    @Schema(description ="email")
    private String email;
    @Schema(description ="date of birth")
    private Date dateOfBirth;
    @Schema(description ="address")
    private String address;
    @Schema(description ="city")
    private String city;
    @Schema(description ="province")
    private String province;
    @Schema(description ="country")
    private String country;
    @Schema(description ="postalCode")
    private String postalCode;
    @Schema(description ="password")
    private String password;
    @Schema(description ="role id, get it from other interface")
    private int idRole;
}
