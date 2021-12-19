package com.example.NaTour21.User.Repository;

import com.example.NaTour21.User.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {

    User findByEmail(String email);
}
