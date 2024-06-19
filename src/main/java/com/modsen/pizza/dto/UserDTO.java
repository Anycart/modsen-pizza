package com.modsen.pizza.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.sql.Date;

@Data
public class UserDTO {

    @NotEmpty
    private String username;
    @NotEmpty
    private String email;
    @NotEmpty
    @Size(min = 3, message = "Length must be more than 3")
    private String password;
    @NotEmpty
    private String fullName;
    @NotEmpty
    private String sex;
    private Date dateOfBirth;

}
