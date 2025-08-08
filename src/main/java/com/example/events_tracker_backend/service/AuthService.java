package com.example.events_tracker_backend.service;

import com.example.events_tracker_backend.entity.User;
import com.example.events_tracker_backend.repository.UserRepository;
import com.example.events_tracker_backend.util.JwtUtil;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
//public class AuthService {
//    private final UserRepository userRepository;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtUtil jwtUtil;
//
//    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//        this.jwtUtil = jwtUtil;
//    }
//
//    public String login(String userName, String password) {
//        // fetch user
//        // check password
//        // generate JWT
//        // return token
//        Optional<User> existingUserOpt = userRepository.findByUserName(userName);
//
//        if (existingUserOpt.isEmpty()) {
//            throw new UsernameNotFoundException("User not found");
//        }
//
//        User existingUser = existingUserOpt.get();
//
//        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
//            throw new BadCredentialsException("Invalid password");
//        }
//
//        return jwtUtil.generateToken(userName);
//    }
//
//}

public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public String login(String userName, String password) {
        // Fetch user from DB
        Optional<User> existingUser = userRepository.findByUserName(userName);

        if (existingUser.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }

        // Validate password
        if (!passwordEncoder.matches(password, existingUser.get().getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        // Generate and return JWT token
        return jwtUtil.generateToken(userName);
    }
}
