package com.example.events_tracker_backend.service;

import com.example.events_tracker_backend.entity.User;
import com.example.events_tracker_backend.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(String userName, String rawPassword){
        Optional<User> existingUser =userRepository.findByUserName(userName);

        if(existingUser.isPresent()){
            return existingUser.get();
        } else{
            String hashed = passwordEncoder.encode(rawPassword);
            User newUser = new User();
            newUser.setUserName(userName);
            newUser.setPassword(hashed);
            userRepository.save(newUser);
            return newUser;
        }
    }

    public Optional<User> getByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }
}
