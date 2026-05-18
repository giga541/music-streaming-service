package com.solvd.musicstreamingservice.persistence;

import com.solvd.musicstreamingservice.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    User create(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    User update(User user);

    void delete(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAllPremium();

    List<User> findAllWithDetails();
}