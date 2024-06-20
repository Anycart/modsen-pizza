package com.modsen.pizza.model;

import com.modsen.pizza.enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.Date;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @NaturalId
    private String username;
    private String email;
    private String password;
    private String fullName;
    private String sex;
    private Date dateOfBirth;
    @Enumerated(value = EnumType.STRING)
    private Role role;
}