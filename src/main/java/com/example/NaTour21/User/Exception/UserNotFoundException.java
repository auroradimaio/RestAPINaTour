package com.example.NaTour21.User.Exception;

 public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(String email) {
        super("Could not find user " + email);
    }
}
