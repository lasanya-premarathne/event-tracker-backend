package com.example.events_tracker_backend.repository;

import com.example.events_tracker_backend.entity.Event;
import com.example.events_tracker_backend.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    /**
     * Finds all the Events by the given username.
     * @param user The userId to search for
     * @return a list of Events if events exist for the given user, empty list otherwise
     */
    List<Event> findAllByCreatedByIs(User user);
}
