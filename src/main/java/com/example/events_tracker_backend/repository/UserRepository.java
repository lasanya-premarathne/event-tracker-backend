package com.example.events_tracker_backend.repository;

import com.example.events_tracker_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    /**
     * Checks if a user exists with the given username.
     * @param userName The userName to search for
     * @return User if a user with the given username exists, null otherwise
     */
    Optional<User> findByUserName(String userName);
}
