package com.example.NaTour21.User.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"user\"")
public class User {

    private String username;
    @Id
    private String email;
    private String password;
    private String auth;
    private String role;

}
