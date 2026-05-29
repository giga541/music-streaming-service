package com.solvd.musicstreamingservice.service.impl;

import com.solvd.musicstreamingservice.factory.RepositoryFactory;
import com.solvd.musicstreamingservice.model.User;
import com.solvd.musicstreamingservice.persistence.UserRepository;
import com.solvd.musicstreamingservice.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(RepositoryFactory factory) {
        this.userRepository = factory.createUserRepository();
    }

    @Override
    public User create(User user) {
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new IllegalArgumentException("User email cannot be empty.");
        }
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("User with this email already exists.");
        }
        return userRepository.create(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User update(User user) {
        return userRepository.update(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.delete(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllPremium() {
        return userRepository.findAllPremium();
    }

    @Override
    public List<User> findAllWithDetails() {
        return userRepository.findAllWithDetails();
    }
}