package com.example.NaTour21.User.Service;

import com.example.NaTour21.User.Entity.User;
import com.example.NaTour21.User.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service @RequiredArgsConstructor @Transactional @Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);
        if(user == null)
        {
            throw  new UsernameNotFoundException("L'email inserita non risulta registrata.");
        }
        Collection<SimpleGrantedAuthority> authotity = new ArrayList<>();
        authotity.add(new SimpleGrantedAuthority(user.getRole()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(), authotity);
    }

    public User saveUser(User user) throws Exception {
        if (!userRepository.existsById(user.getEmail())) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }
        throw new Exception("Email già registrata");
    }

    public User getUser(String email) {
        if (userRepository.existsById(email)) {
            return userRepository.getById(email);
        }
        throw new UsernameNotFoundException("Email non registrata");
    }

    public List<User> getUsers()
    {
        return userRepository.findAll();
    }

}